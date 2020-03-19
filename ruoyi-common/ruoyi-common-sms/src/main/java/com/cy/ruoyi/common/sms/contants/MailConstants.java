package com.cy.ruoyi.common.sms.contants;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *  MailConstants
 * @author chengyan
 * @date 2018/11/27 17:15
 */
@Component
@ConfigurationProperties(prefix = "spring")
@Data
public class MailConstants {

    @Value("${spring.mail.username}")
    private String username;

}
