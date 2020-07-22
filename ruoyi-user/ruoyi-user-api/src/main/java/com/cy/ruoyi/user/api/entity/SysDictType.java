package com.cy.ruoyi.user.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cy.ruoyi.common.core.basic.entity.BaseDO;
import lombok.Data;

import java.io.Serializable;

/**
 * 字典类型表 sys_dict_type
 * 
 * @author ruoyi
 */
@Data
@TableName("sys_Dict_type")
public class SysDictType extends BaseDO implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 字典主键 */
    @TableId(value="dict_Id", type= IdType.AUTO)
    private Long dictId;

    /** 字典名称 */
    private String dictName;

    /** 字典类型 */
    private String dictType;

    /** 状态（0正常 1停用） */
    private String status;

}
