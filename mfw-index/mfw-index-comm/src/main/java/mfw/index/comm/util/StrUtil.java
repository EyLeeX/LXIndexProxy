package mfw.index.comm.util;

/**
 * Created by Mtime on 2016/10/22.
 */
public class StrUtil {

    /**
     * 判断是否为空
     * @param str
     * @return
     */
    public static boolean isEmpty(Object...str){
        for (Object s : str) {
            if (s==null || "".equals(s.toString())) {
                return true;
            }
        }
        return false;
    }

}
