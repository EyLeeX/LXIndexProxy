package mfw.index.comm.esmeta;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Mtime on 2016/10/18.
 *
 * es 索引基本信息
 */

@Getter
@Setter
public class ElasticIndex {
    public ElasticIndex() {
    }

    String IndexName;

    String IndexType;
}
