package com.cy.ruoyi.common.auth.feign;

import com.cy.ruoyi.common.auth.feign.factory.RemoteMenuFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

/**
 * 菜单 Feign服务层
 */
@FeignClient(name = "ruoyi-user", fallbackFactory = RemoteMenuFallbackFactory.class)
public interface RemoteMenuService
{
    @GetMapping("menu/perms/{userId}")
    Set<String> selectPermsByUserId(@PathVariable("userId") Long userId);
}
