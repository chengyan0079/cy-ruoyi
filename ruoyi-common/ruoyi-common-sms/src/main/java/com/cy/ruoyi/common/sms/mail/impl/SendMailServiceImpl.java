package com.cy.ruoyi.common.sms.mail.impl;

import com.cy.ruoyi.common.sms.contants.MailConstants;
import com.cy.ruoyi.common.sms.mail.SendMailService;
import com.cy.ruoyi.common.sms.vo.MailReceiveVo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("sendMailService")
@Log4j2
public class SendMailServiceImpl implements SendMailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    MailConstants mailConstants;

    @Override
    public void sendSimpleMail(MailReceiveVo mailReceiveVo) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            //邮件发送人
            simpleMailMessage.setFrom(mailConstants.getSendAddress());
            //邮件接收人
            simpleMailMessage.setTo(mailReceiveVo.getRecipient());
            //邮件主题
            simpleMailMessage.setSubject(mailReceiveVo.getSubject());
            //邮件内容
            simpleMailMessage.setText(mailReceiveVo.getContent());
            javaMailSender.send(simpleMailMessage);
        } catch (Exception e) {
            log.error("邮件发送失败", e.getMessage());
        }
    }
}
