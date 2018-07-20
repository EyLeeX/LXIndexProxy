package mfw.index.client.model;

import mfw.index.comm.esannotation.ESIndex;
import mfw.index.comm.esannotation.ESType;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Juxin on 2017/6/15.
 */
@Getter
@Setter
@ESIndex("logstash-mx.tc.virtualcard.service-*")
@ESType("1")
public class VCardOrderTracks {

    /**
     * 索引id
     */
    private String id;
    /**
     * 订单号
     */
    private long orderId;
    /**
     * 卡操作类型
     */
    private int operation;
    /**
     * 卡操作结果
     */
    private int result;
    /**
     * 失败原因描述
     */
    private String description;
    /**
     * 操作时间
     */
    private String operateTime;
    /**
     * 服务日志索引
     */
    private String uuid;
    /**
     * 失败原因编码
     */
    private int errorCode;


//    public Map<String, Object> getLogMap() {
//        Map<String, Object> map = new HashMap();
//        map.put("id", KeyGenerator.generateOrderTrackId(orderId));
//        map.put("orderId", orderId);
//        map.put("operation", operation);
//        map.put("result", result);
//        map.put("description", description);
//        map.put("operateTime", DateKit.toMinuteString(operateTime == null ? LocalDateTime.now() : operateTime));
//        map.put("uuid", uuid);
//        map.put("errorCode", errorCode);
//        return map;
//    }

}