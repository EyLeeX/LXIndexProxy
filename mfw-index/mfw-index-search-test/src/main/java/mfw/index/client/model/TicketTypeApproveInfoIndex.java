package mfw.index.client.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Sunw on 2016/11/24.
 */
@Getter
@Setter
public class TicketTypeApproveInfoIndex {

	private Integer id;

	private Integer status;   // 审核状态
	private Integer tktId;          // 关联票类ID

	private Integer category;    // 类别
	private String code;                // 编码
	private String name;                // 票类名称
	private Integer state;      // 票类状态
	private Integer level;      // 票类级别
	private String regionId;           // 创建区域ID
	private String cinemaCode;           // 创建影院内码
	private Integer validDateType;    // 有效期类型
	private LocalDate startDate;    // 有效期开始时间
	private LocalDate endDate;      // 有效期结束时间
	private String regionStrategyId;

	private LocalDateTime applyTime;    // 申请时间

	private List<String> regionIds;   // 区域范围  [院线级专用]
	private List<String> cinemaCodes;   // 影院范围

}
