package com.cy.ruoyi.user.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cy.ruoyi.user.api.entity.SysConfig;
import com.cy.ruoyi.user.api.entity.SysDept;
import com.cy.ruoyi.user.api.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * 部门管理 数据层
 */
@Mapper
public interface SysDeptMapper extends BaseMapper<SysDept>
{
    /**
     * 查询部门人数
     * 
     * @param dept 部门信息
     * @return 结果
     */
    int selectDeptCount(SysDept dept);

    /**
     * 查询部门是否存在用户
     * 
     * @param deptId 部门ID
     * @return 结果
     */
    int checkDeptExistUser(Long deptId);

    /**
     * 查询部门管理数据
     * 
     * @param dept 部门信息
     * @return 部门信息集合
     */
//    List<SysDept> selectDeptList(SysDept dept);

    /**
     * 删除部门管理信息
     * 
     * @param deptId 部门ID
     * @return 结果
     */
    int deleteDeptById(Long deptId);

    /**
     * 修改子元素关系
     * 
     * @param depts 子元素
     * @return 结果
     */
    int updateDeptChildren(@Param("depts") List<SysDept> depts);

    /**
     * 根据部门ID查询信息
     * 
     * @param deptId 部门ID
     * @return 部门信息
     */
    SysDept selectDeptById(Long deptId);

    /**
     * 校验部门名称是否唯一
     * 
     * @param deptName 部门名称
     * @param parentId 父部门ID
     * @return 结果
     */
    SysDept checkDeptNameUnique(@Param("deptName") String deptName, @Param("parentId") Long parentId);

    /**
     * 根据角色ID查询部门
     *
     * @param roleId 角色ID
     * @return 部门列表
     */
    List<String> selectRoleDeptTree(Long roleId);

    /**
     * 修改所在部门的父级部门状态
     * 
     * @param dept 部门
     */
    void updateDeptStatus(SysDept dept);

    /**
     * 根据ID查询所有子部门
     * @param id
     * @return
     */
    List<SysDept> selectChildrenDeptById(Long id);

    /**
     * 根据角色编号查询所有部门ID
     * @param roleId
     * @return
     * @author zmr
     */
    Set<String> selectRoleDeptIds(Long roleId);

    /**
     * 查询部门管理数据
     * @param page
     * @param dept
     * @return
     */
    IPage<SysDept> selectDeptList(Page page, @Param("dept") SysDept dept);
}
