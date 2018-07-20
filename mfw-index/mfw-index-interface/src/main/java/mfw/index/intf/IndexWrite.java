package mfw.index.intf;

import java.util.List;
import java.util.Map;

/**
 * Created by jinweile on 2016/1/20.
 * 写索引操作
 */
public interface IndexWrite {

    /**
     * 创建索引
     * @param mapping
     * @param doc
     */
    void create(String mapping, Map<String, Object> doc,String clusterName) ;
    /**
     * 批量创建索引
     * @param mapping
     * @param docs
     */
    void create(String mapping, List<Map<String, Object>> docs,String clusterName) ;

    /**
     * 新增或者更新索引
     * @param mapping 索引服务器url
     * @param doc 索引文档
     */
    void update(String mapping, Map<String, Object> doc,String clusterName) ;
    /**
     * 批量新增或者更新索引
     * @param mapping 索引服务器url
     * @param docs 索引文档
     */
    void update(String mapping, List<Map<String, Object>> docs,String clusterName) ;

    /**
     * 删除索引
     * @param mapping 索引服务器url
     * @param uniqueKey 唯一键值
     */
    void delete(String mapping, String uniqueKey,String clusterName);
    /**
     * 删除索引
     * @param mapping 索引服务器url
     * @param uniqueKeys 唯一键值 列表
     */
    void delete(String mapping, List<String> uniqueKeys,String clusterName);

}
