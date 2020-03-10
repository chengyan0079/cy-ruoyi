package com.cy.ruoyi.user.impl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.ruoyi.common.core.util.page.PageDomain;
import com.cy.ruoyi.common.core.util.page.PageUtils;
import com.cy.ruoyi.common.core.util.page.Query;
import com.cy.ruoyi.common.utils.text.Convert;
import com.cy.ruoyi.common.utils.util.RegexUtil;
import com.cy.ruoyi.user.api.entity.SysLogininfor;
import com.cy.ruoyi.user.api.entity.SysUser;
import com.cy.ruoyi.user.api.mapper.SysLogininforMapper;
import com.cy.ruoyi.user.api.service.ISysLogininforService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(validation = "true", version = "${dubbo.provider.ISysLogininforService.version}")
@org.springframework.stereotype.Service
public class SysLogininforServiceImpl extends ServiceImpl<SysLogininforMapper, SysLogininfor> implements ISysLogininforService {

    @Autowired
    private SysLogininforMapper logininforMapper;

    /**
     * 新增系统登录日志
     *
     * @param logininfor 访问日志对象
     */
    @Override
    public void insertLogininfor(SysLogininfor logininfor)
    {
        logininforMapper.insertLogininfor(logininfor);
    }

    @Override
    public List<SysLogininfor> selectLogininforList(SysLogininfor logininfor) {
        return logininforMapper.selectLogininforList(logininfor);
    }

    @Override
    public int deleteLogininforByIds(String ids) {
        return logininforMapper.deleteLogininforByIds(Convert.toStrArray(ids));
    }

    @Override
    public void cleanLogininfor() {logininforMapper.cleanLogininfor();}


    @Override
    public PageUtils selectLogininforList(PageDomain pageDomain, SysLogininfor logininfor)
    {
        if (RegexUtil.isNull(logininfor)) {
            logininfor = new SysLogininfor();
        }
        IPage<SysLogininfor> page = logininforMapper.selectLogininforList(new Query<SysLogininfor>(pageDomain).getPage(), logininfor);
        return new PageUtils(page);
    }
}
