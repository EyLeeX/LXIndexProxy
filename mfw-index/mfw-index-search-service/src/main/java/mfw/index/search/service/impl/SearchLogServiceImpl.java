package mfw.index.search.service.impl;

import mfw.index.comm.dto.QueryResult;
import mfw.index.comm.util.Utils;
import mfw.index.core.indexer.IndexMsgHandler;
import mfw.index.core.searcher.SearchHandler;
import mfw.index.intf.IndexRead;
import mfw.index.intf.IndexWrite;
import mfw.index.search.service.dto.SearchDto;
import mfw.index.search.service.dto.SearchDto.IndexRequest;
import mfw.index.search.service.dto.SearchDto.SearchRequest;
import mfw.index.search.service.dto.SearchDto.SearchResponse;
import mfw.index.search.service.iface.SearchLogService;
import mfw.index.search.service.impl.mapper.SearchResponseMapper;
import mtime.lark.pb.utils.StringUtils;
import mtime.lark.util.log.Logger;
import mtime.lark.util.log.LoggerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by yanlei.shi on 2016/1/18.
 */
@Component
public class SearchLogServiceImpl implements SearchLogService {

    Logger logger = LoggerManager.getLogger(SearchLogServiceImpl.class);

    @Autowired
    IndexWrite indexWrite;


    @Autowired
    IndexRead indexRead;

    


    /**
     * 搜索
     *
     * @param request
     */
    @Override
    public SearchResponse search(SearchRequest request) {
        int pageIndex = request.getPageInfo().getPageIndex();
        int pageSize = request.getPageInfo().getPageSize();
        logger.info("进行查询的条件是: [{}],分页条件为: [pageIndex:{},pageSize:{}] ",
                                        request.getKeyword(), pageIndex,pageSize);
        QueryResult queryResult = SearchHandler.handleQuery(
                request.getObjectType(),
                request.getKeyword(),
                pageIndex,
                pageSize,
                indexRead);
        if (logger.isDebugEnabled()) {
            logger.debug("查询的结果是:  {} ",Utils.toJson(queryResult));
        }
        //搜索结果转成SearchResponse
        return SearchResponseMapper.of(queryResult);
    }

    @Override
    public SearchDto.ScrollSearchResponse scrollSearch(SearchDto.ScrollSearchRequest request) {
        String scrollId = request.getScrollId();
        int pageSize = request.getPageSize();
        logger.info("进行查询的条件是: [{}],分页条件为: [scrollId:{},pageSize:{}]",
                request.getKeyword(), scrollId,pageSize);
        QueryResult queryResult = SearchHandler.handleQuery(
                request.getObjectType(),
                request.getKeyword(),
                scrollId,
                pageSize,
                indexRead);
        if (logger.isDebugEnabled()) {
            logger.debug("查询的结果是:  {} ",Utils.toJson(queryResult));
        }
        //搜索结果转成SearchResponse
        if (StringUtils.isEmpty(queryResult.getScrollId())){
            queryResult.setScrollId(scrollId);
        }
        return SearchResponseMapper.ofscroll(queryResult);
    }

    /**
     * 操作索引
     */
    @Override
    public void index(IndexRequest request) {
        String indexMsgJson = request.getIndexMsgJson();
        logger.info("需要操作的索引是:{}", indexMsgJson);
        IndexMsgHandler.indexObjHandler(indexMsgJson, indexWrite);
    }

    @Override
    public void closeScroll(SearchDto.CloseScrollRequest request) {
        String scrollId = request.getScrollId();
        indexRead.cleanScrollId(scrollId,request.getClusterRouter());
    }


}
