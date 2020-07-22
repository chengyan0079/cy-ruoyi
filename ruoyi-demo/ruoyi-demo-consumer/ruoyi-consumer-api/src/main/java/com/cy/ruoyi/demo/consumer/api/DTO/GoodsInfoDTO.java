package com.cy.ruoyi.demo.consumer.api.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * 商品信息DTO
 */
@Data
public class GoodsInfoDTO implements Serializable {

	/** 主键*/
	private Integer id;

	/**
	 *  商品编号
	 */
	private String goodsNo;

	/**
	 * 商品名称
	 */
	private String goodsName;

	/**
	 * 商品状态 0-正常 1-下架
	 */
	private String status;

	/**
	 *  商品类别 0-食品 1-家电 2-小商品 3-书籍 4-生活用品 5-虚拟商品
	 */
	private String goodsType;

	/**
	 *  商品存货量
	 */
	private Integer goodsCount;

	/**
	 * 商品进货单价
	 */
	private BigDecimal buyingPrice;

	/**
	 * 商品售价
	 */
	private BigDecimal sellingPrice;

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
