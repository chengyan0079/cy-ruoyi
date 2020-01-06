package com.cy.ruoyi.user.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cy.ruoyi.common.core.basic.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * 参数配置表 sys_config
 * 
 * @author ruoyi
 */
@Data
@TableName("sys_config")
public class SysConfig extends BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 参数主键 */
    @TableId(value="config_Id", type= IdType.AUTO)
    private Long configId;

    /** 参数名称 */
    private String configName;

    /** 参数键名 */
    private String configKey;

    /** 参数键值 */
    private String configValue;

    /** 系统内置（Y是 N否） */
    private String configType;

}
