package com.cy.ruoyi.common.sms.mail;


import com.cy.ruoyi.common.sms.vo.MailReceiveVo;

public interface SendMailService {

    //发送简单邮件
    void sendSimpleMail(MailReceiveVo mailReceiveVo);
}
