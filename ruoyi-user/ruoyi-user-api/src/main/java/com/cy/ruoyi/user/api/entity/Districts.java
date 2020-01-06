package com.cy.ruoyi.user.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cy.ruoyi.common.core.basic.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * 地区表 districts
 * 
 * @author ruoyi
 * @date 2018-12-19
 */
@Data
@TableName("districts")
public class Districts extends BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @TableId(value="ID", type= IdType.AUTO)
    private Integer id;

    /** 上级编号 */
    private Integer pid;

    /** 层级 */
    private Integer deep;

    /** 名称 */
    private String name;

    /** 上级名称 */
    private String pname;

    /** 拼音 */
    private String pinyin;

    /** 拼音缩写 */
    private String pinyinShor;

    /** 扩展名 */
    private String extName;

    /** 操作人 */
    private String operator;

}
