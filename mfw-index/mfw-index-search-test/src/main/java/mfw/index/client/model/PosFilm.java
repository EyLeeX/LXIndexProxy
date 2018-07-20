package mfw.index.client.model;

import mfw.index.comm.esannotation.ESIndex;
import mfw.index.comm.esannotation.ESType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Created by Sunw on 2017/2/27.
 */
@Setter
@Getter
@ESIndex("logstash-mx.tc.show.mq.handler-*")
@ESType("logs")
public class PosFilm {

    private String cinemaId;    // 影院ID

    private String showId;      // 场次ID

    private int seq;            // 影片序号

    private String posFilmId;       // 网关影片ID

    private String stdCode;         // 影片专资编码

    private String name;            // 影片名称

    private int duration;       // 影片时长

    private String lang;            // 影片语言

    private String edition;         // 影片版本

    private LocalDateTime showTime;     // 放映时间

}
