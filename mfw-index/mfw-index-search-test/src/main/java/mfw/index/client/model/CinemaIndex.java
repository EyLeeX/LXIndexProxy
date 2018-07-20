package mfw.index.client.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 影院存入搜索引擎的字段实体 Created by liyong 2016/11/1.
 */
@Getter
@Setter
public class CinemaIndex {
	/**
	 * 影院ID
	 */
	private int id;
	/**
	 * 编码
	 */
	private String govCinemaCode;
	/**
	 * 简称
	 */
	private String shortName;
	/**
	 * 开业日期
	 */
	private LocalDate openDate;
	/**
	 * 拼码
	 */
	private String pinCode;
	/**
	 * 内部名称
	 */
	private String innerName;
	/**
	 * 外部名称
	 */
	private String outerName;
	/**
	 * 影院类型
	 */
	private String cinemaType;
	/**
	 * 区域ID
	 */
	private String regionId;
	/**
	 * 省
	 */
	private int province;
	/**
	 * 市
	 */
	private int city;
	/**
	 * 区
	 */
	private int area;
	/**
	 * 街道地址
	 */
	private int street;
	/**
	 * 门牌号
	 */
	private String house;
	/**
	 * 影院供片状态
	 */
	private String provideStatus;
	/**
	 * 影院级别
	 */
	private String cinemaLevel;
	/**
	 * 影院市场类型
	 */
	private String marketType;
	/**
	 * 开业状态
	 */
	private String openStatus;
	/**
	 * EHR系统ORGCODE
	 */
	private String ehrOrgCode;
	/**
	 * 传真
	 */
	private String fax;
	/**
	 * 邮编
	 */
	private String postCode;
	/**
	 * 收购交割日
	 */
	private LocalDate acquisitionDeliveryDate;
	/**
	 * 营业时间(起)
	 */
	private String businessStartTime;
	/**
	 * 营业时间(止)
	 */
	private String businessEndTime;
	/**
	 * 工作日黄金时间(起)
	 */
	private String workStartTime;
	/**
	 * 工作日黄金时间(止)
	 */
	private String workEndTime;
	/**
	 * 双休日黄金时间(起)
	 */
	private String weekendStartTime;
	/**
	 * 双休日黄金时间(止)
	 */
	private String weekendEndTime;
	/**
	 * 客流系统中的影城编码
	 */
	private String codeOfCfs;
	/**
	 * 预算系统中的影城编码
	 */
	private String codeOfBcs;
	/**
	 * 使用触摸屏
	 */
	private boolean touchScreen;
	/**
	 * 法人ID
	 */
	private int corporationId;
	/**
	 * 非法人分店
	 */
	private String uninCorporationStore;
	/**
	 * 是否一般纳税人
	 */
	private boolean taxpayer;
	/**
	 * 是否简易征收
	 */
	private boolean levy;
	/**
	 * 是否简易征收
	 */
	private LocalDate levyDate;
	/**
	 * IMAX分成租金
	 */
	private int imaxRatio;
	/**
	 * 营业税金率（含附加）
	 */
	private int businessTax;
	/**
	 * 电影专资比率
	 */
	private int filmCodeRatio;
	/**
	 * 影院租金比率
	 */
	private int cinemaRentRatio;
	/**
	 * 是否删除
	 */
	private boolean deleted;
	/**
	 * 版本号
	 */
	private int version;
	/**
	 * 影院下的影厅数
	 */
	private int hallCount;
	/**
	 * 物业类型
	 */
	private String propertyType;
	/**
	 * 物业出租方
	 */
	private String propertyLessor;
	/**
	 * 物业面积
	 */
	private int propertyArea;
	/**
	 * 物业费率
	 */
	private int propertyRate;
	/**
	 * 房租计算基数
	 */
	private String rentCalculationBase;
	/**
	 * 税金计算基数
	 */
	private String taxCalculationBase;
	/**
	 * 房租分成比例
	 */
	private int rentSharingRatio;
	/**
	 * 租金收取方式
	 */
	private String rentCollectionMethod;
	/**
	 * 时间轴开始时间
	 */
	private LocalDate timeAxisStartTime;
	/**
	 * 时间轴结束时间
	 */
	private LocalDate timeAxisEndTime;
	/**
	 * 默认场时间间隔
	 */
	private int defaultFieldTimeInterval;
	/**
	 * 拖动精度（多少分钟）
	 */
	private int dragPrecision;
	/**
	 * 影院内码
	 */
	private String innerCode;
	/**
	 * 可用影厅数
	 */
	private int availableHallCount;
	/**
	 * 卖品数
	 */
	private int saleCount;
	/**
	 * 排片数
	 */
	private int showtimeCount;
	/**
	 * 用户级别
	 */
	private int userRank;

	private int createUser;
	private LocalDateTime createTime;
	private int updateUser;
	private LocalDateTime updateTime;

	// 以下属性为需要排序的字段
	private int seatCount; // 座位数
	private String infoPerfectDegree;// 信息完善程度
	private String propertyTypeDisplay;// 列表页展示物业类型的名称
	private String regionDisplay;// 列表页展示区域的名称
	private String cityDisplay;// 列表页展示城市的名称
}
