package com.cy.ruoyi.admin.auth.feign.factory;

import com.cy.ruoyi.admin.auth.feign.RemoteUserService;
import com.cy.ruoyi.common.auth.DTO.SysDeptDTO;
import com.cy.ruoyi.common.auth.DTO.SysUserDTO;
import com.cy.ruoyi.common.utils.util.R;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@Component
public class RemoteUserFallbackFactory implements FallbackFactory<RemoteUserService>
{
    @Override
    public RemoteUserService create(Throwable throwable)
    {
        log.error(throwable.getMessage());
        return new RemoteUserService()
        {
            @Override
            public SysUserDTO selectSysUserByUsername(String username)
            {
                return null;
            }

            @Override
            public R updateUserLoginRecord(SysUserDTO user)
            {
                return R.error();
            }

            @Override
            public SysUserDTO selectSysUserByUserId(long userId)
            {
                SysUserDTO user = new SysUserDTO();
                user.setUserId(0l);
                user.setLoginName("no user");
                return user;
            }

            @Override
            public Set<Long> selectUserIdsHasRoles(String roleId)
            {
                return null;
            }

            @Override
            public Set<Long> selectUserIdsInDepts(String deptIds)
            {
                return null;
            }

            @Override
            public SysDeptDTO selectSysDeptByDeptId(long deptId)
            {
                return null;
            }
        };
    }
}
