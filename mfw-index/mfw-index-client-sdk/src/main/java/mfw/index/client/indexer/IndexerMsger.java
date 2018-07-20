package mfw.index.client.indexer;

import mfw.index.comm.dto.IndexMsg;

import java.util.Set;

public class IndexerMsger {
	private IndexerMsger (){}
	
	/**
	 * 构建索引消息
	 * @param className
	 * @param indexType
	 * @param obj
	 * @param fieldName
	 * @return
	 */
    public static <T> IndexMsg<T> createMsg(String className
                                            , IndexMsg.INDEXTYPE indexType
                                            , T obj
			,Set<String> fieldName){
        IndexMsg<T> indexMsg = new IndexMsg<>();
        indexMsg.setClassName(className);
        indexMsg.setIndexType(indexType);
        indexMsg.setObj(obj);
        indexMsg.setFieldName(fieldName);

        return indexMsg;
    }

}
