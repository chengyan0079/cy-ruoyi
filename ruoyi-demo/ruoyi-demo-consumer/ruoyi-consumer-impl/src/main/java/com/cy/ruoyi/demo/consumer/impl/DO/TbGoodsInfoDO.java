package com.cy.ruoyi.demo.consumer.impl.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cy.ruoyi.common.core.basic.entity.BaseDO;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品信息表
 */
@TableName("tb_goods_info")
@Data
public class TbGoodsInfoDO extends BaseDO implements Serializable {


    /** 主键*/
	@TableId(value="ID", type= IdType.AUTO)
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
}
