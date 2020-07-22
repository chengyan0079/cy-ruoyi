package com.cy.ruoyi.demo.provider.app.PO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * 订单信息PO
 */
@Data
public class OrderInfoPO implements Serializable {

    /** 主键*/
	private Integer id;

    /** 用户表id*/
	private Integer userId;

    /** 交易金额*/
	private BigDecimal payAmt;

    /** 订单编号*/
	private String orderNo;

    /** 订单交易状态 0-未支付 1-支付中 2-支付成功 3-支付失败*/
	private String payStatus;

    /** 支付订单号*/
	private String payOrderNo;

    /** 订单支付日期*/
	@JsonFormat(pattern = "yyyy-MM-dd")
	private String payDate;

	/** 订单支付时间*/
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String payTime;

	/** 创建者 */
	private String createBy;

	/** 创建时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/** 更新者 */
	private String updateBy;

	/** 更新时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

	/** 备注 */
	private String remark;

	private String beginTime;

	private String endTime;

	/** 请求参数 */
	private Map<String, Object> params;

}
