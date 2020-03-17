package com.cy.ruoyi.common.sms.vo;

import lombok.Data;

import java.io.Serializable;

/**
 *  发送邮件接受者信息
 */
@Data
public class MailReceiveVo implements Serializable {

    //邮件接收人
    private String recipient;

    //邮件主题
    private String subject;

    //邮件内容
    private String content;

}
