package com.cy.ruoyi.user.app.controller;

import com.cy.ruoyi.user.api.service.TestService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/test")
public class TestController {

    @Reference(validation = "true", version = "${dubbo.provider.TestService.version}")
    private TestService testService;

    @PostMapping("/echo/{msg}")
    public String echo(@PathVariable String msg){
        return testService.echo(msg);
    }
}
