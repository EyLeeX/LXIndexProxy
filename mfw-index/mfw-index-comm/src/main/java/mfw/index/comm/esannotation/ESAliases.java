package mfw.index.comm.esannotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Mtime on 2016/10/17.
 */
@Target({TYPE})
@Retention(RUNTIME)
public @interface ESAliases {

    /**
     * ElasticSearch Alias 别名
     * 可以指定多个
     * @return
     */
    String value();
}
