package mfw.index.client;

import cmc.index.client.searcher.SearchResult;
import cmc.index.client.searcher.SearcherMsger;
import mfw.index.comm.constants.IndexConstants;
import mfw.index.comm.dto.IndexQuery;
import mfw.index.comm.util.Mapper;
import mfw.index.comm.util.serializer.TypeRef;
import mfw.index.search.service.constant.SearchService;
import mfw.index.search.service.dto.SearchDto;
import mfw.index.client.searcher.SearchResult;
import mfw.index.client.searcher.SearcherMsger;
import mtime.lark.pb.utils.StringUtils;
import mtime.lark.util.data.PageInfoSupport;
import mtime.lark.util.lang.FaultException;
import mtime.lark.util.log.Logger;
import mtime.lark.util.log.LoggerManager;
import mtime.lark.util.msg.Publisher;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.function.Predicate;

/**
 * Created by jinweile on 2016/1/18.
 * <p>
 * Es 搜索 client
 */
public class SearcherBaseClient<S extends SearchService> {
    private Logger logger = LoggerManager.getLogger(SearcherBaseClient.class);


    protected String router;



    @Autowired
    S searchService;

    /**
     * 检索
     *
     * @param typeRef    反序列化类型
     * @param tClass     索引class
     * @param indexQuery 搜索条件
     * @param pageIndex  pageIndex
     * @param pageSize   pageSize
     * @return 查询结果
     */
    public <T> SearchResult<T> search(TypeRef<List<T>> typeRef, Class<T> tClass,
                                      IndexQuery indexQuery, int pageIndex, int pageSize) {
        int maxValue = 200000;
        if (pageIndex ==0  ){
            throw new FaultException("pageIndex 需要从1开始");
        }else if (pageSize*pageIndex > maxValue){
            throw new FaultException("pageIndex * pageSize 超过了"+maxValue+"条，请使用 scrollSearch api");
        }
        indexQueryInit(indexQuery);
        String mapping = Mapper.getEntityMappingJson(tClass);
        SearchDto.SearchRequest searchRequest = SearcherMsger.createRequest(
                mapping,
                indexQuery,
                pageIndex,
                pageSize);
        SearchDto.SearchResponse searchResponse = searchService.search(searchRequest);
        SearchResult<T> searchResult = SearcherMsger.parseSearchResult(typeRef, searchResponse);
        if (logger.isDebugEnabled()) {
            logger.debug("" + searchResult);
        }
        return searchResult;
    }



    private <T> SearchResult<T> scrollSearch(TypeRef<List<T>> typeRef, Class<T> tClass,
                                             IndexQuery indexQuery, String scrollId, int pageSize) {
        //构造消息体
        indexQueryInit(indexQuery);
        String mapping = Mapper.getEntityMappingJson(tClass);
        SearchDto.ScrollSearchRequest searchRequest = SearcherMsger.createRequest(
                mapping,
                indexQuery,
                scrollId,
                pageSize);
        SearchDto.ScrollSearchResponse searchResponse = searchService.scrollSearch(searchRequest);
        SearchResult<T> searchResult = SearcherMsger.parseSearchResult(typeRef, searchResponse);
        if (logger.isDebugEnabled()) {
            logger.debug("" + searchResult);
        }
        return searchResult;
    }

    /**
     * 全量检索
     *
     * @param tClass     索引class
     * @param indexQuery 搜索条件
     * @param pageSize   pageSize
     *                   IndexQuery indexQuery = new IndexQuery();
     *                   scrollSearch( Member.class,indexQuery,900,res -> {
     *                   //TOTO 处理你的业务
     *                   return true;
     *                   });
     * @return 查询结果
     */
    public <T> void scrollSearch(TypeRef<List<T>> typeRef, Class<T> tClass,
                                 IndexQuery indexQuery, int pageSize, Predicate<SearchResult<T>> action) {
        SearchResult<T> result = scrollSearch(typeRef, tClass, indexQuery, null, pageSize);
        String scrollId = null;
        try {
            do {
                scrollId = result.getScrollId();
                boolean apply = action.test(result);
                if (!apply ||
                        result.getSearchList()==null ||
                        result.getSearchList().isEmpty() ||
                        result.getSearchList().size()== result.getSearchCount()) {
                    break;
                }
                result = scrollSearch(typeRef, tClass, indexQuery, scrollId, pageSize);
                scrollId = result.getScrollId();
            } while (result.getSearchList() != null && !result.getSearchList().isEmpty());
        } catch (FaultException e) {
            throw e;
        } finally {
            cleanScroll(scrollId);
        }
    }

    /**
     * 全量检索 暴露scrollId
     *
     * @param tClass     索引class
     * @param indexQuery 搜索条件
     * @param pageSize   pageSize
     *                   IndexQuery indexQuery = new IndexQuery();
     *                   scrollSearch( Member.class,indexQuery,900,res -> {
     *                   //TOTO 处理你的业务
     *                   return true;
     *                   });
     * @return 查询结果
     */
    public <T> SearchResult<T> searchByScrollId(TypeRef<List<T>> typeRef, Class<T> tClass,
                                 IndexQuery indexQuery, int pageSize,String scrollId) {
        return scrollSearch(typeRef, tClass, indexQuery, scrollId, pageSize);
    }

    /**
     * 分页或者全量
     * 该方法为 需要查询全量，但数据量又不是特别大的情况下使用。
     * 如果数据量比较大， 建议使用scrollSearch 接口。
     *
     * 当pageInfo 为null 时，查全量，其他情况分页查询
     *
     * @param typeRef 反序列化的类型
     * @param tClass  查询es的 model类
     * @param indexQuery  查询的条件
     * @param pageInfo  分页信息
     * @return 返回结果
     */
    public <T> SearchResult<T> search(TypeRef<List<T>> typeRef, Class<T> tClass,
                                      IndexQuery indexQuery , PageInfoSupport pageInfo){
        if (pageInfo != null){
            return search(typeRef,tClass, indexQuery,pageInfo.getPageIndex(),pageInfo.getPageSize());
        }else{
            SearchResult<T> result = scrollSearch(typeRef, tClass, indexQuery, null, 500);
            SearchResult<T> res = result;
            String scrollId = null;
            try {
                while (result.getSearchList() != null && !result.getSearchList().isEmpty()){
                    scrollId = result.getScrollId();
                    result = scrollSearch(typeRef, tClass, indexQuery, scrollId, 500);
                    if (result.getSearchList()==null || result.getSearchList().isEmpty()) {
                        break;
                    }
                    res.getSearchList().addAll(result.getSearchList());
                    scrollId = result.getScrollId();
                }
            } catch (FaultException e) {
                throw e;
            } finally {
                cleanScroll(scrollId);
            }
            return res;
        }

    }


    /**
     * 使用完scroll 方法后异步释放资源
     *
     * @param scrollId
     */
    protected void cleanScroll(String scrollId) {
//        SearchDto.CloseScrollRequest request = new SearchDto.CloseScrollRequest();
//        request.setScrollId(scrollId);
//        this.searchService.closeScroll(request);
        Publisher.get().publish(IndexConstants.CMC_ES_SCROLL_CLEAN_TOPIC, scrollId);
    }


    private void indexQueryInit(IndexQuery indexQuery){
        if (StringUtils.isEmpty(router)){
            throw new FaultException(IndexConstants.CLIENT_ES_CLUSTER_KEY +" 为空,无法找到ES 映射地址");
        }
        if (indexQuery == null){
            indexQuery = new IndexQuery();
        }
        indexQuery.setClusterRouter(router);
    }

}
