package com.cy.ruoyi.admin.sys.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cy.ruoyi.common.core.basic.entity.BaseDO;
import lombok.Data;

import java.io.Serializable;

/**
 * 通知公告表 sys_notice
 * 
 */
@Data
@TableName("Sys_Notice")
public class SysNotice extends BaseDO implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 公告ID */
    @TableId(value="notice_Id", type= IdType.AUTO)
    private Long noticeId;

    /** 公告标题 */
    private String noticeTitle;

    /** 公告类型（1通知 2公告） */
    private String noticeType;

    /** 公告内容 */
    private String noticeContent;

    /** 公告状态（0正常 1关闭） */
    private String status;

}
