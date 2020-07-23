package com.cy.ruoyi.admin.sys.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cy.ruoyi.admin.sys.base.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色表 数据层
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole>
{

    /**
     * 根据用户ID查询角色
     *
     * @param userId 用户ID
     * @return 角色列表
     */
    List<SysRole> selectRolesByUserId(Long userId);

    /**
     * 通过角色ID查询角色
     *
     * @param roleId 角色ID
     * @return 角色对象信息
     */
    SysRole selectRoleById(Long roleId);

    /**
     * 通过角色ID删除角色
     *
     * @param roleId 角色ID
     * @return 结果
     */
    int deleteRoleById(Long roleId);

    /**
     * 批量角色用户信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteRoleByIds(Long[] ids);

    /**
     * 修改角色信息
     *
     * @param role 角色信息
     * @return 结果
     */
    int updateRole(SysRole role);

    /**
     * 新增角色信息
     *
     * @param role 角色信息
     * @return 结果
     */
    int insertRole(SysRole role);

    /**
     * 校验角色名称是否唯一
     *
     * @param roleName 角色名称
     * @return 角色信息
     */
    SysRole checkRoleNameUnique(String roleName);

    /**
     * 校验角色权限是否唯一
     *
     * @param roleKey 角色权限
     * @return 角色信息
     */
    SysRole checkRoleKeyUnique(String roleKey);

    //**************************************************************************************
    /**
     * 根据条件查询所有角色数据
     *
     * @param role 角色信息
     * @return 角色数据集合信息
     */
    List<SysRole> selectRoleList(@Param("role") SysRole role);

    /**
     * 根据条件分页查询角色数据
     * @param page
     * @param role
     * @return
     */
    IPage<SysRole> selectRoleList(Page page, @Param("role") SysRole role);
}
