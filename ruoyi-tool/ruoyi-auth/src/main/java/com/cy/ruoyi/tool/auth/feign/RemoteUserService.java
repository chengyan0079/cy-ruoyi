package com.cy.ruoyi.tool.auth.feign;

import com.cy.ruoyi.common.auth.DTO.SysDeptDTO;
import com.cy.ruoyi.common.auth.DTO.SysUserDTO;
import com.cy.ruoyi.common.utils.util.R;
import com.cy.ruoyi.tool.auth.feign.factory.RemoteUserFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * 用户 Feign服务层
 */
@FeignClient(name = "ruoyi-user", fallbackFactory = RemoteUserFallbackFactory.class)
public interface RemoteUserService
{
    @GetMapping("user/get/{userId}")
    SysUserDTO selectSysUserByUserId(@PathVariable("userId") long userId);

    @GetMapping("user/find/{username}")
    SysUserDTO selectSysUserByUsername(@PathVariable("username") String username);

    @PostMapping("user/update/login")
    R updateUserLoginRecord(@RequestBody SysUserDTO user);

    /**
     * 查询拥有当前角色的所有用户
     * @param roleIds
     * @return
     */
    @GetMapping("user/hasRoles")
    Set<Long> selectUserIdsHasRoles(@RequestParam("roleIds") String roleIds);

    /**
     * 查询所有当前部门中的用户
     *
     * @param deptIds
     * @return
     */
    @GetMapping("user/inDepts")
    Set<Long> selectUserIdsInDepts(@RequestParam("deptIds") String deptIds);

    @GetMapping("dept/get/{deptId}")
    SysDeptDTO selectSysDeptByDeptId(@PathVariable("deptId") long deptId);
}
