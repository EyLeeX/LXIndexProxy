package mfw.index.client.model;

import mfw.index.comm.esannotation.ESIndex;
import mfw.index.comm.esannotation.ESType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Sunw on 2017/2/27.
 */
@Setter
@Getter
@ESIndex("logstash-mx.tc.show.mq.handler-*")
@ESType("logs")
public class PosPrice {

    private String cinemaId;    // 影院ID

    private String showId;      // 场次ID

    private String sectionId;       // 座区ID

    private String priceName;       // 活动或者票类的名称

    private String priceCode;       // 活动或者票类的编号

    private List<String> channelIds;    // 渠道编码列表

    private int settlePrice;      // 结算价

    private int listPrice;      // 万达门市价, ps:本想将该字段作为销售价, 无奈api已经将此字段当做门市价提供给第三方了, 所以命名不标准, 见谅!

    private int memberPoint;      // 积分,细分类别为积分票时有值,默认为0

    private int differGraPrice;      // 针对积分兑换补差价金额活动部分设置为0

    private String detailType;      // 细分类别
}
