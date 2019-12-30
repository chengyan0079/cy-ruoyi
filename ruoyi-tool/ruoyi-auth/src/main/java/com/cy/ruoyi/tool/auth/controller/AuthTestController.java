package com.cy.ruoyi.tool.auth.controller;

import com.cy.ruoyi.user.api.service.TestService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class AuthTestController {

    @Reference(version = "${dubbo.consumer.TestService.version}")
    private TestService testService;

    @PostMapping("/echo/{msg}")
    public String echo(@PathVariable String msg){
        return "Hello, Auth Test Service! " + testService.echo(msg);
    }

}
