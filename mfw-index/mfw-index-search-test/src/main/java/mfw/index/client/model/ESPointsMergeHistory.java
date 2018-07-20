package mfw.index.client.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 会员积分历史
 * Created by Mtime on 2016/10/9.
 */
@Setter
@Getter
public class ESPointsMergeHistory {
    private String id;
    private Long batchNo; //合并批次号
    private String memberNo;
    private String cinemaCode;
    private int operType;//操作类型
    private int pointsChangeReasonType;//积分调整原因类型
    private int pointsChangeType;//1,增加。2减少
    private int consumeChangeVal;
    private int activityChangeVal;
    private int curPoints;
    private String memo;
    private LocalDateTime invalidDate;
    private String sourceId;
    private String staffCode; //员工编码
    private String channelCode; //渠道编码
    private String createUser;
    private LocalDateTime createTime;//获得时间/兑换时间
    private int exchangePointsType;
    private String goodsName;
    private boolean mergeSuccess = true;
}