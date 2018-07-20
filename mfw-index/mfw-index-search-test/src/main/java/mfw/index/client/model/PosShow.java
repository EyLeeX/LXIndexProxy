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
public class PosShow {

    private String id;              // 场次ID

    private String showDate;        // 营业日期

    private String stdCode;         // 场次专资码

    private String hallId;          // 影厅ID

    private String hallName;        // 影厅名称

    private String cinemaId;        // 影院ID

    private LocalDateTime showTime;     // 场次时间

    private boolean through;      // 是否连场

    private int cinemaPrice;    // 影院门市价(单位:分)

    private int lowestPrice;        // 限制最低价(等于0则表际不限,单位:分)

    private int hallFeePrice;       // 影厅服务费(单位:分)
}
