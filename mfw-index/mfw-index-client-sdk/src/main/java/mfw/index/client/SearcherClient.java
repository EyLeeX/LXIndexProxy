package mfw.index.client;

import mfw.index.comm.constants.IndexConstants;
import mfw.index.search.service.iface.SearchAdminService;
import mtime.lark.util.config.ConfigProperties;
import mtime.lark.util.log.Logger;
import mtime.lark.util.log.LoggerManager;
import mtime.lark.util.msg.Publisher;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jinweile on 2016/1/18.
 * <p>
 * Es 搜索 client
 */
public class SearcherClient extends SearcherBaseClient<SearchAdminService> {
    private Logger logger = LoggerManager.getLogger(SearcherClient.class);


    public SearcherClient(){
        router = ConfigProperties.getProperty(IndexConstants.CLIENT_ES_CLUSTER_KEY);
        logger.info("获取到客户端的es.cluster.router值为:{}",router);
    }


    /**
     * 使用完scroll 方法后异步释放资源
     *
     * @param scrollId
     */
    @Override
    protected void cleanScroll(String scrollId) {
        Map<String,String> map = new HashMap<>();
        map.put("scrollId",scrollId);
        map.put("cluster","admin");
        map.put("router",router);
        Publisher.get().publish(IndexConstants.CMC_ES_SCROLL_CLEAN_TOPIC, map);
    }

}
