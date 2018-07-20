package mfw.index.client.model;

import mfw.index.comm.esannotation.ESIndex;
import mfw.index.comm.esannotation.ESType;
import lombok.Getter;
import lombok.Setter;

/**
 * logstash 日志实体
 *
 * @author 史彦磊
 * @create 2017-01-12 10:42.
 */
@Setter
@Getter
@ESIndex("logstash-mx.tc.gateway.pos.service*")
@ESType("logs")
public class LogMsg {

    private String id;
    private String message;
    private String appname;
    private String hostname;
    private String logtime;
    private String level;
    private String contextid;
    private String orderid;
    private String method;
    private String request;



}
