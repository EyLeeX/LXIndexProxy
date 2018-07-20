package mfw.index.es;

import mfw.index.comm.esmeta.ElasticIndex;
import mfw.index.comm.util.Mapper;
import mfw.index.comm.util.StrUtil;
import mfw.index.comm.util.Utils;
import mfw.index.comm.util.serializer.TypeRef;
import mfw.index.es.client.TClient;
import mfw.index.intf.IndexWrite;
import mtime.lark.util.config.ConfigProperties;
import mtime.lark.util.lang.FaultException;
import mtime.lark.util.log.Logger;
import mtime.lark.util.log.LoggerManager;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateRequestBuilder;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.IndexNotFoundException;
import org.elasticsearch.index.mapper.StrictDynamicMappingException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by Mtime on 2016/10/22.
 */
@Component
public class ESWrite implements IndexWrite {

    Logger log = LoggerManager.getLogger(ESWrite.class);

    private boolean refresh = true;

    TransportClient client;



    @Override
    public void create(String entityInfo, Map<String, Object> doc,String clusterName) {
        Mapper.EntityInfo info = getEntityInfo(entityInfo);
        try {
            add(doc,info,clusterName);
        }catch (IndexNotFoundException e){
            log.info("索引不存在，需要屏蔽掉改异常:{}",e);
            addCore(info,clusterName);
            add(doc,info,clusterName);
        }catch (StrictDynamicMappingException e){
            log.info("索引不存在，需要屏蔽掉改异常:{}",e);
            addFieldMapping(info,clusterName);
            add(doc,info,clusterName);
        }

    }

    @Override
    public void create(String entityInfo, List<Map<String, Object>> docs,String clusterName) {
        Mapper.EntityInfo info = getEntityInfo(entityInfo);
        try {
            addList(docs,info,clusterName);
        }catch (IndexNotFoundException e){
            log.info("索引不存在，需要屏蔽掉改异常:{}",e);
            addCore(info,clusterName);
            addList(docs,info,clusterName);
        }catch (StrictDynamicMappingException e){
            log.info("索引不存在，需要屏蔽掉改异常:{}",e);
            addFieldMapping(info,clusterName);
            addList(docs,info,clusterName);
        }
    }

    @Override
    public void update(String entityInfo, Map<String, Object> doc,String clusterName)  {
        Mapper.EntityInfo info = getEntityInfo(entityInfo);
        try {
            update(doc,info,clusterName);
        }catch (IndexNotFoundException e){
            log.info("索引不存在，需要屏蔽掉改异常:{}",e);
            addCore(info,clusterName);
            update(doc,info,clusterName);
        }catch (StrictDynamicMappingException e){
            log.info("索引不存在，需要屏蔽掉改异常:{}",e);
            addFieldMapping(info,clusterName);
            update(doc,info,clusterName);
        }
    }

    @Override
    public void update(String entityInfo, List<Map<String, Object>> docs,String clusterName)  {
        Mapper.EntityInfo info = getEntityInfo(entityInfo);
        try {
            updateList(docs,info,clusterName);
        }catch (IndexNotFoundException e){
            log.info("索引不存在，需要屏蔽掉改异常:{}",e);
            addCore(info,clusterName);
            updateList(docs,info,clusterName);
        }catch (StrictDynamicMappingException e){
            log.info("索引不存在，需要屏蔽掉改异常:{}",e);
            addFieldMapping(info,clusterName);
            updateList(docs,info,clusterName);
        }
    }

    @Override
    public void delete(String entityInfo, String id,String clusterName) {
        Mapper.EntityInfo info = getEntityInfo(entityInfo);
        client = TClient.getClient(clusterName);
        ElasticIndex index = info.getIndex();
        client.prepareDelete(index.getIndexName(),
                index.getIndexType(),
                id)
                .setRefresh(refresh)
                .get();
    }

    @Override
    public void delete(String entityInfo, List<String> ids,String clusterName)  {
        Mapper.EntityInfo info = getEntityInfo(entityInfo);
        client = TClient.getClient(clusterName);
        BulkRequestBuilder builder = client.prepareBulk();
        ElasticIndex index =info.getIndex();
        ids.forEach(id ->{
            DeleteRequestBuilder indexBuilder = client.prepareDelete(index.getIndexName(),
                    index.getIndexType(),
                    id);
            builder.add(indexBuilder);
        });
        BulkResponse response = builder.setRefresh(refresh).get();
        processException(response);
    }



    /**
     * 反序列化ES 映射信息
     * @param entityInfo
     * @return
     */
    private Mapper.EntityInfo getEntityInfo(String entityInfo){
        return Utils.parseObject(entityInfo,new TypeRef<Mapper.EntityInfo>(){});
    }

    /**
     * 添加索引
     * @param doc
     * @param info
     */
    private  void add(Map<String,Object> doc, Mapper.EntityInfo info,String clusterName) {
        Object id = doc.get(info.getIdColumn());
        if (StrUtil.isEmpty(id)){
            throw new FaultException("主键值不可为空");
        }
        client = TClient.getClient(clusterName);
        ElasticIndex index = info.getIndex();
        client.prepareIndex(index.getIndexName(), index.getIndexType(),id.toString() )
                .setRefresh(refresh)
                .setSource(doc).get();
    }

    /**
     * 批量添加索引
     * @param docs
     * @param info
     * @return
     */
    private BulkResponse addList(List<Map<String,Object>> docs, Mapper.EntityInfo info,String clusterName){
        client = TClient.getClient(clusterName);
        ElasticIndex index = info.getIndex();
        BulkRequestBuilder builder = client.prepareBulk();
        docs.forEach(obj ->{
            Object id = obj.get(info.getIdColumn());
            if (StrUtil.isEmpty(id)){
                throw new FaultException("主键值不可为空");
            }
            IndexRequestBuilder indexBuilder = client.prepareIndex(index.getIndexName(),
                    index.getIndexType(),
                    id.toString());
            indexBuilder.setSource(obj);
            builder.add(indexBuilder);
        });
        BulkResponse response = builder.setRefresh(refresh).get();
        processException(response);
        return response;
    }

    /**
     * 批量更新索引
     * @param docs
     * @param info
     * @return
     */
    private BulkResponse updateList(List<Map<String,Object>> docs, Mapper.EntityInfo info,String clusterName){
        client = TClient.getClient(clusterName);
        BulkRequestBuilder builder = client.prepareBulk();
        ElasticIndex index = info.getIndex();
        docs.forEach(obj ->{
            Object id = obj.get(info.getIdColumn());
            if (StrUtil.isEmpty(id)){
                throw new FaultException("主键值不可为空");
            }

            UpdateRequestBuilder updateBuilder = client.prepareUpdate(index.getIndexName(),
                    index.getIndexType(),
                    id.toString());
            updateBuilder.setDoc(obj);
            updateBuilder.setUpsert(obj);
            builder.add(updateBuilder);
        });
        BulkResponse response = builder.setRefresh(refresh).get();
        processException(response);
        return response;
    }

    /**
     * 更新索引
     * 如果发现没有该索引则新建
     * @param doc
     * @param info
     */
    private void update(Map<String,Object> doc,Mapper.EntityInfo info,String clusterName){
        Object id = doc.get(info.getIdColumn());
        if (StrUtil.isEmpty(id)){
            throw new FaultException("主键值不可为空");
        }
        client = TClient.getClient(clusterName);
        ElasticIndex index = info.getIndex();
        IndexRequest indexRequest = new IndexRequest(index.getIndexName(),
                index.getIndexType(),
                id.toString())
                .refresh(refresh)
                .source(doc);
        UpdateRequest updateRequest = new UpdateRequest(index.getIndexName(),
                index.getIndexType(),
                id.toString())
                .refresh(refresh)
                .doc(doc)
                .upsert(indexRequest);
        client.update(updateRequest).actionGet();
    }

    /**
     * 添加索引core
     * @param info
     */
    private void addCore(Mapper.EntityInfo info,String clusterName) {
        client = TClient.getClient(clusterName);
        ElasticIndex index = info.getIndex();
        int shards = Integer.parseInt(ConfigProperties.getProperty("es.number.shards"));
        int replicas = Integer.parseInt(ConfigProperties.getProperty("es.number.replicas"));
        int maxresult = Integer.parseInt(ConfigProperties.getProperty("es.max.result.window","20000"));
        Settings settings = Settings.settingsBuilder()
                .put("number_of_shards",shards)
                .put("number_of_replicas",replicas)
                .put("max_result_window",maxresult)
                .build();
        log.debug("index : {} , shards :{} ,replicas :{} ",index.getIndexName(),shards ,replicas);
        CreateIndexRequestBuilder builder = client.admin().indices()
                .prepareCreate(index.getIndexName())
                .setSettings(settings)
                .addMapping(index.getIndexType(), Utils.toJson(info.getMappings()));
        builder.get();
    }

    /**
     * 添加新加字段的mapping
     * @param info
     */
    private void addFieldMapping(Mapper.EntityInfo info,String clusterName)  {
        client = TClient.getClient(clusterName);
        ElasticIndex index = info.getIndex();
        PutMappingRequest req = new PutMappingRequest(index.getIndexName());
        req.type(index.getIndexType());
        String mapping = Utils.toJson(info.getMappings());
        req.source(mapping);
        client.admin().indices().putMapping(req).actionGet();
    }

    /**
     * 处理异常
     * @param response
     */
    private void processException(BulkResponse response){
        if(response.hasFailures()){
            for(BulkItemResponse res : response){
                if (res.isFailed()){
                    String failureMessage = res.getFailureMessage();
                    if (StringUtils.isEmpty(failureMessage)){
                        throw new FaultException(res.getFailure().getCause());
                    }
                    if (failureMessage.contains("IndexNotFoundException")){
                        throw new IndexNotFoundException("不存在该索引");
                    }else if (failureMessage.contains("StrictDynamicMappingException")){
                        throw new StrictDynamicMappingException("","");
                    }else {
                        throw new FaultException(res.getFailure().getCause());
                    }
                }
            }
        }
    }
}
