package com.cy.ruoyi.user.api.feign;

import com.cy.ruoyi.common.utils.util.R;
import com.cy.ruoyi.user.api.entity.SysDept;
import com.cy.ruoyi.user.api.entity.SysUser;
import com.cy.ruoyi.user.api.feign.factory.RemoteUserFallbackFactory;
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
    public SysUser selectSysUserByUserId(@PathVariable("userId") long userId);

    @GetMapping("user/find/{username}")
    public SysUser selectSysUserByUsername(@PathVariable("username") String username);

    @PostMapping("user/update/login")
    public R updateUserLoginRecord(@RequestBody SysUser user);

    /**
     * 查询拥有当前角色的所有用户
     * @param roleIds
     * @return
     */
    @GetMapping("user/hasRoles")
    public Set<Long> selectUserIdsHasRoles(@RequestParam("roleIds") String roleIds);

    /**
     * 查询所有当前部门中的用户
     *
     * @param deptIds
     * @return
     */
    @GetMapping("user/inDepts")
    public Set<Long> selectUserIdsInDepts(@RequestParam("deptIds") String deptIds);

    @GetMapping("dept/get/{deptId}")
    public SysDept selectSysDeptByDeptId(@PathVariable("deptId") long deptId);
}
