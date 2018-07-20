package mfw.index.es;

import mfw.index.comm.dto.IndexQuery;
import mfw.index.comm.dto.QueryResult;
import mfw.index.comm.esmeta.ElasticIndex;
import mfw.index.comm.util.Mapper;
import mfw.index.comm.util.Utils;
import mfw.index.comm.util.serializer.TypeRef;
import mfw.index.es.client.TClient;
import mfw.index.intf.IndexRead;
import mtime.lark.util.log.Logger;
import mtime.lark.util.log.LoggerManager;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import java.util.Map;

/**
 * 优化查询后的实现
 *
 * @author 史彦磊
 * @create 2017-03-01 16:55.
 */
public class ESRead2 implements IndexRead {

    Logger log = LoggerManager.getLogger(ESRead2.class);

    TransportClient client;
    @Override
    public QueryResult search(String mapping, IndexQuery query, int pageIndex, int pageSize, String clusterName) {
        Mapper.EntityInfo info = Utils.parseObject(mapping, new TypeRef<Mapper.EntityInfo>(){});
        client = TClient.getClient(clusterName);
        ElasticIndex index = info.getIndex();
        SearchRequestBuilder builder = client.prepareSearch(index.getIndexName())
                .setTypes(index.getIndexType())
                .setSearchType(SearchType.QUERY_THEN_FETCH)
                .setFrom((pageIndex - 1) * pageSize)
                .setSize(pageSize);
        //查询条件
        BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
        Map<String, String[]> q = query.getQuery();



        return null;
    }

    @Override
    public QueryResult scrollSearch(String mapping, IndexQuery query, String scrollId, int pageSize, String clusterName) {
        return null;
    }

    @Override
    public void cleanScrollId(String scrollId, String clusterName) {

    }
}
