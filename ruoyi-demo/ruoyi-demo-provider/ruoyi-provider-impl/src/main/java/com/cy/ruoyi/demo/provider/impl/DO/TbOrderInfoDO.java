package com.cy.ruoyi.demo.provider.impl.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cy.ruoyi.common.core.basic.entity.BaseDO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单信息表
 */
@TableName("tb_order_info")
@Data
public class TbOrderInfoDO extends BaseDO implements Serializable {


    /** 主键*/
	@TableId(value="ID", type= IdType.AUTO)
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

}
