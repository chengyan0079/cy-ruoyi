package com.cy.ruoyi.tool.activiti.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.ruoyi.common.core.util.page.PageDomain;
import com.cy.ruoyi.common.core.util.page.PageUtils;
import com.cy.ruoyi.common.core.util.page.Query;
import com.cy.ruoyi.common.utils.text.Convert;
import com.cy.ruoyi.common.utils.util.RegexUtil;
import com.cy.ruoyi.tool.activiti.VO.HiTaskVo;
import com.cy.ruoyi.tool.activiti.entity.BizLeave;
import com.cy.ruoyi.tool.activiti.mapper.BizLeaveMapper;
import com.cy.ruoyi.tool.activiti.service.IBizLeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 请假Service业务层处理
 */
@Service
public class BizLeaveServiceImpl extends ServiceImpl<BizLeaveMapper, BizLeave> implements IBizLeaveService
{
    @Autowired
    private BizLeaveMapper leaveMapper;

    /**
     * 查询请假
     *
     * @param id 请假ID
     * @return 请假
     */
    @Override
    public BizLeave selectBizLeaveById(String id)
    {
        return leaveMapper.selectBizLeaveById(id);
    }

    /**
     * 查询请假列表
     * 
     * @param bizLeave 请假
     * @return 请假
     */
    @Override
    public List<BizLeave> selectBizLeaveList(BizLeave bizLeave)
    {
        return leaveMapper.selectBizLeaveList(bizLeave);
    }

    /**
     * 新增请假
     *
     * @param bizLeave 请假
     * @return 结果
     */
    @Override
    public int insertBizLeave(BizLeave bizLeave)
    {
        return leaveMapper.insertBizLeave(bizLeave);
    }

    /**
     * 修改请假
     *
     * @param bizLeave 请假
     * @return 结果
     */
    @Override
    public int updateBizLeave(BizLeave bizLeave)
    {
        return leaveMapper.updateBizLeave(bizLeave);
    }

    /**
     * 删除请假对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBizLeaveByIds(String ids)
    {
        return leaveMapper.deleteBizLeaveByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除请假信息
     *
     * @param id 请假ID
     * @return 结果
     */
    @Override
    public int deleteBizLeaveById(String id)
    {
        return leaveMapper.deleteBizLeaveById(id);
    }

    @Override
    public PageUtils selectBizLeaveList(PageDomain pageDomain, BizLeave bizLeave)
    {
        if(RegexUtil.isNull(bizLeave)){
            bizLeave = new BizLeave();
        }
        IPage<BizLeave> page = leaveMapper.selectBizLeaveList(new Query<BizLeave>(pageDomain).getPage(), bizLeave);
        return new PageUtils(page);
    }
}
