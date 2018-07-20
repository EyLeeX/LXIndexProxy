package mfw.index.mq.handler;

import mfw.index.comm.constants.IndexConstants;
import mfw.index.intf.IndexRead;
import mtime.lark.util.log.Logger;
import mtime.lark.util.log.LoggerManager;
import mtime.lark.util.msg.AbstractHandler;
import mtime.lark.util.msg.Message;
import mtime.lark.util.msg.MsgHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * solr 异步清除es scrollId
 */
@Component
@MsgHandler(topic= IndexConstants.CMC_ES_SCROLL_CLEAN_TOPIC,channel=IndexConstants.CMC_ES_SCROLL_CLEAN_CHANNEL)
public class CleanScrollIdHandler  extends AbstractHandler<HashMap<String,String>> {

    private Logger log = LoggerManager.getLogger(CleanScrollIdHandler.class);


    @Autowired
    IndexRead indexRead;


    @Override
    protected void process(HashMap<String,String> map, Message message) {
        log.debug("需要清除的scrollId:{}",map);
        String scrollId = map.get("scrollId");
        String clusterName = map.get("router");
        this.indexRead.cleanScrollId(scrollId,clusterName);
    }
}
