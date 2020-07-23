package com.cy.ruoyi.admin.activiti.config;

import org.activiti.engine.impl.cfg.IdGenerator;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 */
@Component
public class MyIdGenerator implements IdGenerator
{
    @Override
    public String getNextId()
    {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }
}