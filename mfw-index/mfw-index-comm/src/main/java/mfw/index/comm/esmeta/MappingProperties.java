package mfw.index.comm.esmeta;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * Created by Mtime on 2016/10/17.
 *
 * ES 索引基本信息
 */

@Getter
@Setter
public class MappingProperties {

    public MappingProperties(){}

    /**
     * mapping properties
     * 结构为：
     * {
     *   field: {
     *     type:string,
     *     index:analyzed,
     *     store:true
     *   }
     * }
     */
    Map<String,Object> properties;

    /**
     * 是否动态映射字段类型
     */
    private String dynamic ="strict";

    public MappingProperties(Map<String, Object> properties) {
        this.properties = properties;
    }
}
