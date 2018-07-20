package mfw.index.client;

import mfw.index.client.indexer.IndexerMsger;
import mfw.index.comm.constants.IndexConstants;
import mfw.index.comm.dto.IndexMsg;
import mfw.index.comm.util.Mapper;
import mfw.index.comm.util.Utils;
import mfw.index.search.service.dto.SearchDto.IndexRequest;
import mfw.index.search.service.iface.SearchAdminService;
import mfw.index.client.indexer.IndexerMsger;

import java.util.List;
import java.util.Set;

/**
 *
 * 更新索引客户端
 */
public class IndexerClient {
    
    private static final String UNIQUEVALERRORSTR = "唯一键值没有设定！";
	private static final String ERRORSTR = "需要索引的类没有设定！";
	private static final String INDEXSIZEERRORSTR = "index obj List is null or size = 0";
	private static final String INDEXERRORSTR = "index obj is null";
	private static final String UNIQUEERRORSTR = "唯一键名没有设定！";


    private IndexerClient(){}

    /**
     * 异步创建索引
     * @param obj
     * @param <T>
     */
    public static <T> void createIndexAsy(T obj) {
        if (obj == null){
            throw new FaultException(INDEXERRORSTR);
        }
        String mapping = Mapper.getEntityMappingJson(obj.getClass());
        IndexMsg<T> indexMsg = IndexerMsger.createMsg(
                mapping,
                IndexMsg.INDEXTYPE.CREATE,
                obj,
                null);

        //把对象序列化成json并发送到消息队列mq
        sendMsg(indexMsg);
    }
    /**
     * 同步创建索引
     * @param obj
     * @param <T>
     */
    public static <T> void createIndex(T obj) {
        if (obj == null){
            throw new FaultException(INDEXERRORSTR);
        }
        String mapping = Mapper.getEntityMappingJson(obj.getClass());
        IndexMsg<T> indexMsg = IndexerMsger.createMsg(
                mapping,
                IndexMsg.INDEXTYPE.CREATE,
                obj,
                null);
        
        //把对象序列化成json并发送到消息队列mq
        writeMsg(indexMsg);
    }

    /**
     * 异步批量创建索引
     * @param objs
     * @param <T>
     */
    public static <T> void createIndexListAsy(List<T> objs) {
        if (objs == null || objs.isEmpty()){
            throw new FaultException(INDEXSIZEERRORSTR);
        }
        String mapping = Mapper.getEntityMappingJson(objs.get(0).getClass());
        IndexMsg<List<T>> indexMsg = IndexerMsger.createMsg(
                mapping,
                IndexMsg.INDEXTYPE.CREATELIST,
                objs,
                null);

        //把对象序列化成json并发送到消息队列mq
        sendMsg(indexMsg);
    }
    /**
     * 同步批量创建索引
     * @param objs
     * @param <T>
     */
    public static <T> void createIndexList(List<T> objs) {
        if (objs == null || objs.isEmpty()){
            throw new FaultException(INDEXSIZEERRORSTR);
        }
        String mapping = Mapper.getEntityMappingJson(objs.get(0).getClass());
        IndexMsg<List<T>> indexMsg = IndexerMsger.createMsg(
                mapping,
                IndexMsg.INDEXTYPE.CREATELIST,
                objs,
                null);
        
        //把对象序列化成json并发送到消息队列mq
        writeMsg(indexMsg);
    }

    /**
     * 异步更新索引
     * @param obj
     * @param needNullFields 需要更新为NULL的字段名称
     * @param <T>
     */
    public static <T> void updateIndexAsy(T obj,Set<String> needNullFields) {
        if (obj == null){
            throw new FaultException(INDEXERRORSTR);
        }
        String mapping = Mapper.getEntityMappingJson(obj.getClass());
        IndexMsg<T> indexMsg = IndexerMsger.createMsg(
                mapping,
                IndexMsg.INDEXTYPE.UPDATE,
                obj,
                needNullFields);

        //把对象序列化成json并发送到消息队列mq
        sendMsg(indexMsg);
    }
    
    /**
     * 同步更新索引
     * @param obj
     * @param needNullFields 需要更新为NULL的字段名称
     * @param <T>
     */
    public static <T> void updateIndex(T obj,Set<String> needNullFields) {
        if (obj == null){
            throw new FaultException(INDEXERRORSTR);
        }
        String mapping = Mapper.getEntityMappingJson(obj.getClass());
        IndexMsg<T> indexMsg = IndexerMsger.createMsg(
                mapping,
                IndexMsg.INDEXTYPE.UPDATE,
                obj,
                needNullFields);
        
        //把对象序列化成json并发送到消息队列mq
        writeMsg(indexMsg);
    }

    /**
     * 同步更新索引 needNullFields集合中的字段在实体中确实为null才更新为null
     * @param obj
     * @param needNullFields 需要更新为NULL的字段名称
     * @param <T>
     */
    public static <T> void updateIndexIfNullField(T obj,Set<String> needNullFields) {
        if (obj == null){
            throw new FaultException(INDEXERRORSTR);
        }
        String mapping = Mapper.getEntityMappingJson(obj.getClass());
        IndexMsg<T> indexMsg = IndexerMsger.createMsg(
                mapping,
                IndexMsg.INDEXTYPE.UPDATENULL,
                obj,
                needNullFields);

        //把对象序列化成json并发送到消息队列mq
        writeMsg(indexMsg);
    }
    
    /**
     * 异步更新索引
     * @param obj
     * @param <T>
     */
    public static <T> void updateIndexAsy(T obj) {
        if (obj == null){
            throw new FaultException(INDEXERRORSTR);
        }
        String mapping = Mapper.getEntityMappingJson(obj.getClass());
        IndexMsg<T> indexMsg = IndexerMsger.createMsg(
                mapping,
                IndexMsg.INDEXTYPE.UPDATE,
                obj,
                null);
        
        //把对象序列化成json并发送到消息队列mq
        sendMsg(indexMsg);
    }
    
    /**
     * 同步更新索引
     * @param obj
     * @param <T>
     */
    public static <T> void updateIndex(T obj) {
        if (obj == null){
            throw new FaultException(INDEXERRORSTR);
        }
        String mapping = Mapper.getEntityMappingJson(obj.getClass());
        IndexMsg<T> indexMsg = IndexerMsger.createMsg(
                mapping,
                IndexMsg.INDEXTYPE.UPDATE,
                obj,
                null);
        
        //把对象序列化成json并发送到消息队列mq
        writeMsg(indexMsg);
    }

    /**
     * 异步批量更新索引
     * @param objs
     * @param <T>
     */
    public static <T> void updateIndexListAsy(List<T> objs) {
        if (objs == null || objs.isEmpty()){
            throw new FaultException(INDEXSIZEERRORSTR);
        }
        String mapping = Mapper.getEntityMappingJson(objs.get(0).getClass());
        IndexMsg<List<T>> indexMsg = IndexerMsger.createMsg(
                mapping,
                IndexMsg.INDEXTYPE.UPDATELIST,
                objs,
                null);

        //把对象序列化成json并发送到消息队列mq
        sendMsg(indexMsg);
    }
    
    /**
     * 同步批量更新索引
     * @param objs
     * @param <T>
     */
    public static <T> void updateIndexList(List<T> objs) {
        if (objs == null || objs.isEmpty()){
            throw new FaultException(INDEXSIZEERRORSTR);
        }
        String mapping = Mapper.getEntityMappingJson(objs.get(0).getClass());
        IndexMsg<List<T>> indexMsg = IndexerMsger.createMsg(
                mapping,
                IndexMsg.INDEXTYPE.UPDATELIST,
                objs,
                null);
        
        //把对象序列化成json并发送到消息队列mq
        writeMsg(indexMsg);
    }
    /**
     * 同步批量更新索引
     * @param objs
     * @param <T>
     */
    public static <T> void updateIndexIfNullFieldList(List<T> objs,Set<String> needNullFields) {
        if (objs == null || objs.isEmpty()){
            throw new FaultException(INDEXSIZEERRORSTR);
        }
        String mapping = Mapper.getEntityMappingJson(objs.get(0).getClass());
        IndexMsg<List<T>> indexMsg = IndexerMsger.createMsg(
                mapping,
                IndexMsg.INDEXTYPE.UPDATELIST,
                objs,
                needNullFields);

        //把对象序列化成json并发送到消息队列mq
        writeMsg(indexMsg);
    }

    /**
     * 异步删除单条索引
     * @param tclass 需要索引的类
     * @param uniqueKeyValue 唯一键值
     * @throws FaultException
     */
    public static <T> void deleteIndexAsy(Class<T> tclass, Object uniqueKeyValue) {
        if (tclass == null){
            throw new FaultException(ERRORSTR);
        }
        if(uniqueKeyValue == null){
            throw new FaultException(UNIQUEVALERRORSTR);
        }
        String mapping = Mapper.getEntityMappingJson(tclass);
        IndexMsg<Object> indexMsg = IndexerMsger.createMsg(
                mapping,
                IndexMsg.INDEXTYPE.DELETE,
                uniqueKeyValue,
                null);

        //把对象序列化成json并发送到消息队列mq
        sendMsg(indexMsg);
    }
    /**
     * 删除单条索引
     * @param tclass 需要索引的类
     * @param uniqueKeyValue 唯一键值
     * @throws FaultException
     */
    public static <T> void deleteIndex(Class<T> tclass, Object uniqueKeyValue) {
        if (tclass == null){
            throw new FaultException(ERRORSTR);
        }
        if(uniqueKeyValue == null){
            throw new FaultException(UNIQUEVALERRORSTR);
        }
        String mapping = Mapper.getEntityMappingJson(tclass);
        IndexMsg<Object> indexMsg = IndexerMsger.createMsg(
                mapping,
                IndexMsg.INDEXTYPE.DELETE,
                uniqueKeyValue,
                null);
        
        //把对象序列化成json并发送到消息队列mq
        writeMsg(indexMsg);
    }

    /**
     * 批量删除索引
     * @param tclass 需要索引的类
     * @param uniqueKeyValues 唯一键值List
     */
    public static <T> void deleteIndexListAsy(Class<T> tclass, List<Object> uniqueKeyValues) {
        if (tclass == null){
            throw new FaultException(ERRORSTR);
        }
        if(uniqueKeyValues == null || uniqueKeyValues.isEmpty()){
            throw new FaultException(UNIQUEVALERRORSTR);
        }
        String mapping = Mapper.getEntityMappingJson(tclass);
        IndexMsg<List<Object>> indexMsg = IndexerMsger.createMsg(
                mapping,
                IndexMsg.INDEXTYPE.DELETELIST,
                uniqueKeyValues,
                null);

        //把对象序列化成json并发送到消息队列mq
        sendMsg(indexMsg);
    }
    
    /**
     * 同步批量删除索引
     * @param tclass 需要索引的类
     * @param uniqueKeyValues 唯一键值List
     */
    public static <T> void deleteIndexList(Class<T> tclass, List<Object> uniqueKeyValues) {
        if (tclass == null){
            throw new FaultException(ERRORSTR);
        }
        if(uniqueKeyValues == null || uniqueKeyValues.isEmpty()){
            throw new FaultException(UNIQUEVALERRORSTR);
        }
        String mapping = Mapper.getEntityMappingJson(tclass);
        IndexMsg<List<Object>> indexMsg = IndexerMsger.createMsg(
                mapping,
                IndexMsg.INDEXTYPE.DELETELIST,
                uniqueKeyValues,
                null);
        
        //把对象序列化成json并发送到消息队列mq
        writeMsg(indexMsg);
    }
    
    private static <T> void sendMsg(IndexMsg<T> indexMsg) {
        if (StringUtils.isEmpty(ROUTER)){
            throw new FaultException(IndexConstants.CLIENT_ES_CLUSTER_KEY +" : 不可以为空");
        }
        indexMsg.setClusterRouter(ROUTER);
		String indexMsgJson = Utils.toJson(indexMsg);
        Publisher.get().publish(IndexConstants.MX_INDEX_WRITE_ES_TOPIC, indexMsgJson);
	}
    
    private static <T> void writeMsg(IndexMsg<T> indexMsg) {
        if (StringUtils.isEmpty(ROUTER)){
            throw new FaultException(IndexConstants.CLIENT_ES_CLUSTER_KEY +" : 不可以为空");
        }
        indexMsg.setClusterRouter(ROUTER);
        String indexMsgJson = Utils.toJson(indexMsg);
        IndexRequest request =new IndexRequest();
        request.setIndexMsgJson(indexMsgJson);
        searchService.index(request);
    }

}
