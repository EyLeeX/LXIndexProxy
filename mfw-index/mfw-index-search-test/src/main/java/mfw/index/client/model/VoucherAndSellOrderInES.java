/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mfw.index.client.model;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 销售单信息和券信息需要往ES那边存的
 * @author lcm
 */
@Getter
@Setter
public class VoucherAndSellOrderInES {
    
	
	
	/**
	 * 券主键id
	 * */
	private long id;
	
	/**
	 * 券id	√
	 * */
    private String voucherId;
	
	/**
	 * 券序列号	√
	 * */
    private Integer voucherSerial;
	
  
    
    /**
	 * 券状态名称	√
	 * */
   private String voucherStateName;
	
     /**
 	 * 销售单id  √
 	 * */
     private String sellOrderId;
       
    /**
	 * 券类型编号	√
	 * */
    private Long voucherTypeNo;
    
    /**
	 * 券类型名称	√
	 * */
    private String voucherTypeName;
    
    /**
   	 * 回兑日期	√
   	 * */
     private LocalDateTime reexchangeTime;
     
     /**
 	 * 回兑影院名称		√
 	 * */
 	private String reexchangeCimaName;
    /**
     * 回兑影院的内码
     */
    private String reexchangeCinemaCode;
 	
 	/**
 	 * 售票员		√
 	 * */
 	private String drawer;
 	
    /**
    * 出票价格 √
    * */
   private Integer outTicketMoney;
      
     /**
  	 * 兑换影片	√
  	 * */
    private String filmName;
      
    /**
     * 放映时间		√
     * */
    private LocalDateTime showTime;
    
    /**
   	 * 券条码	 	√
   	 * */
    private String voucherBarcode;
    
    /**
   	 * 订单级别名称	√
   	 * */
    private String orderLevelName;
	
	
	/**
	 * 客户名称	√
	 * */
	private String customerName;
	/**
	 * 客户编号
	 * */
	private String customerId;
	
	/**
	 * 券单价	  √
	 * */
	private Integer voucherSingleMoney;
	
	/**
	 * 是否是实物券1实物2电子  √
	 * */
	private Integer yesOrNoPhysicalVoucher;
	
	/**
	 * 销售员	√
	 * */
	private String userName;
	
	/**
	 * 区域ID		√
	 * */
	private String areaId;
	
	/**
	 * 区域名称	√
	 * */
	private String areaName;
	
	/**
	 * 影院ID		√
	 * */
	private String cinemaInnerCode;
	
	/**
	 * 影院名称	√
	 * */
	private String cinemaName;
	
	/**
	 * 券类别名称	√
	 * */
	private String voucherCategoryName;
	
	/**
   	 * 订单级别
   	 * */
    private Integer orderLevel;
    
    /**
	 * 券状态
	 * */
    private Integer voucherState;
    
    /**
	 * 券类别
	 * */
	private Integer voucherCategory;
	
	/**
	 * 兑换码:打印码Encrypt	√
	 * */
	private String printCode;
	
	
	/**
	 * 销售单分类	√
	 * */
	private String vouSellOrderCategory;
	
	/**
	 * 销售单分类名称√
	 * */
	private String vouSellOrderCategoryName;
	
	/**
     * 销售单日期
     * */
    private LocalDateTime sellOrderTime;

	/**
	 * 券到期日期
	 */
	private LocalDateTime expiryDate;

	/**
	 * 制券类别
	 */
	private String makeVoucherS;
}
