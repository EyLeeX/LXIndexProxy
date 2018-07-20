package mfw.index.client.model;
import mfw.index.comm.esannotation.SearchId;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CardInfoToES {
	/**
	 * 卡号
	 * */
	@SearchId
    private String cardId;
    /**
     * 证件类型
     */
    private Integer certificatesType;
   
    /**
     * 证件号
     */
    private String certificatesNo;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 手机号
     */
    private String phoneNo;
    /**
     * QQ号
     */
    private String qq;
    /**
     * 卡状态
     */
    private Integer cardState;
    /**
     * 卡类型编号
     */
    private Long cardTypeNo;
   
    /**
     * 卡形式名称
     * */
    private String cardForm;
    
    /**
     * 是否是刮刮卡
     * */
    private String isScratchCard;
    
    /**
     * 区域id
     * */
    private String areaId;
    
    /**
     * 影院id
     * */
    private String cinemaInnerCode;
    
    /**
     * 卡状态名称
     * */
    private String cardStateName;
    /**
     * 证件类型名称
     */
    private String certificatesTypeName;
    
    
    /**
     * 有效期截止日期
     */
    private LocalDateTime effectiveEnd;
    
    /**
     * 邮箱
     */
    private String email;
    
    
    /**
     * 邮编
     */
    private String postCode;

    /**
     * 卡类别
     */
    private Integer cardSort;

    /**
     * 卡类别名称
     */
    private String cardSortName;

    /**
     * 卡余额
     */
    private Integer balance;
    
    
    /**
     * 发卡时间
     */
    private LocalDateTime issueTime;

    /**
     * 最后更新时间
     */
    private LocalDateTime lastTime;
    
    
}