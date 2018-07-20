package mfw.index.comm.util.serializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.parser.deserializer.AbstractDateDeserializer;
import mtime.lark.util.log.Logger;
import mtime.lark.util.log.LoggerManager;

import java.lang.reflect.Type;
import java.time.LocalDateTime;

/**
 * Created by jinweile on 16/1/21.
 */
public class LocalDateTimeDeserializer extends AbstractDateDeserializer {
	
	Logger logger = LoggerManager.getLogger(LocalDateTimeDeserializer.class);
	
	/**
	 * 日期转换
	 */
    @Override
    protected <T> T cast(DefaultJSONParser parser, Type clazz, Object fieldName, Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof String) {
            String strVal = (String) value;
            if (strVal.length() == 0) {
                return null;
            }
            LocalDateTime localDateTime = null;
            try {
                localDateTime = LocalDateTime.parse(strVal);
            } catch (Exception e) {
                logger.debug("把日期解析为localDateTime类型的失败，当做以Z结尾来处理：strVal:{},e:{}",strVal,e);
                localDateTime =LocalDateTime.parse(strVal.replace("Z",""));
            }
            return (T) localDateTime;
        }

        throw new JSONException("parse error");
    }

    @Override
    public int getFastMatchToken() {
        return JSONToken.LITERAL_INT;
    }

}
