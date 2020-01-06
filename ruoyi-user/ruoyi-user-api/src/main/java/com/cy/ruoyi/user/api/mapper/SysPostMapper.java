package com.cy.ruoyi.user.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cy.ruoyi.user.api.entity.SysPost;
import java.util.List;

/**
 * 岗位信息 数据层
 * 
 * @author ruoyi
 */
public interface SysPostMapper extends BaseMapper<SysPost>
{
    /**
     * 根据用户ID查询岗位
     * 
     * @param userId 用户ID
     * @return 岗位列表
     */
    List<SysPost> selectPostsByUserId(Long userId);

    /**
     * 批量删除岗位信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deletePostByIds(Long[] ids);

    /**
     * 校验岗位名称
     * 
     * @param postName 岗位名称
     * @return 结果
     */
    SysPost checkPostNameUnique(String postName);

    /**
     * 校验岗位编码
     * 
     * @param postCode 岗位编码
     * @return 结果
     */
    SysPost checkPostCodeUnique(String postCode);
}
