package mfw.index.mq.handler;

import mfw.index.comm.constants.IndexConstants;
import mfw.index.comm.util.Utils;
import mfw.index.comm.util.serializer.TypeRef;
import mfw.index.core.indexer.IndexMsgHandler;
import mfw.index.intf.IndexWrite;
import mtime.lark.util.msg.AbstractHandler;
import mtime.lark.util.msg.Message;
import mtime.lark.util.msg.MsgHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * solr 异步处理
 */
@Component
@MsgHandler(topic=IndexConstants.MX_INDEX_WRITE_ES_TOPIC,channel=IndexConstants.MX_INDEX_WRITE_ES_CHANNEL)
public class IndexWriteHandler extends AbstractHandler<String>{
	
	@Autowired
	IndexWrite indexWrite;
	/**
	 * 写索引操作的solr实现
	 */
	@Override
	protected void process(String msg, Message raw) {
		String result = Utils.parseObject(msg, new TypeRef<String>(){});
		IndexMsgHandler.indexObjHandler(result, indexWrite);
	}
	
	

	



}
