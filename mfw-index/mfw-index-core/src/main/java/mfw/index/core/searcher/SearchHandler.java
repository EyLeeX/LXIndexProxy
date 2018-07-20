package mfw.index.core.searcher;

import mfw.index.comm.constants.IndexConstants;
import mfw.index.comm.dto.IndexQuery;
import mfw.index.comm.dto.QueryResult;
import mfw.index.comm.util.Utils;
import mfw.index.comm.util.serializer.TypeRef;
import mfw.index.intf.IndexRead;
import mtime.lark.util.lang.FaultException;
import org.springframework.util.StringUtils;

/**
 * Created by jinweile on 2016/1/22.
 * 检索处理
 */
public class SearchHandler {

    private SearchHandler(){}

    /**
     * 解析检索条件，发送检索请求，并返回检索结果
     * @param mapping
     * @param query
     * @param pageIndex
     * @param pageSize
     * @param indexRead
     * @return
     */
    public static QueryResult handleQuery(String mapping,
                                          String query,
                                          int pageIndex,
                                          int pageSize,
                                          IndexRead indexRead){
        IndexQuery indexQuery = Utils.parseObject(query, new TypeRef<IndexQuery>(){});
        if (StringUtils.isEmpty(indexQuery.getClusterRouter())){
            throw new FaultException(IndexConstants.CLIENT_ES_CLUSTER_KEY +" : 不可以为空");
        }
        return search(mapping, indexQuery, pageIndex, pageSize, indexRead,indexQuery.getClusterRouter());
    }

    private static QueryResult search(String mapping,
                                      IndexQuery indexQuery,
                                      int pageIndex,
                                      int pageSize,
                                      IndexRead indexRead,String clusterName){
        return indexRead.search(mapping, indexQuery, pageIndex, pageSize,clusterName);
    }

    private static QueryResult scrollSearch(String mapping,
                                      IndexQuery indexQuery,
                                      String scrollId,
                                      int pageSize,
                                      IndexRead indexRead, String clusterName){
        return indexRead.scrollSearch(mapping, indexQuery, scrollId, pageSize,clusterName);
    }

    /**
     * 解析检索条件，发送检索请求，并返回检索结果
     * @param mapping
     * @param query
     * @param scrollId
     * @param pageSize
     * @param indexRead
     * @return
     */
    public static QueryResult handleQuery(String mapping,
                                          String query,
                                          String scrollId,
                                          int pageSize,
                                          IndexRead indexRead){
        IndexQuery indexQuery = Utils.parseObject(query, new TypeRef<IndexQuery>(){});
        if (StringUtils.isEmpty(indexQuery.getClusterRouter())){
            throw new FaultException(IndexConstants.CLIENT_ES_CLUSTER_KEY +" : 不可以为空");
        }
        return scrollSearch(mapping, indexQuery, scrollId, pageSize, indexRead,indexQuery.getClusterRouter());
    }

}
