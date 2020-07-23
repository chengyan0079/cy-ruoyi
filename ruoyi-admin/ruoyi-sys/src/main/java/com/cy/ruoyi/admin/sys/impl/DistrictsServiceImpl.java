package com.cy.ruoyi.admin.sys.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.ruoyi.admin.sys.base.entity.Districts;
import com.cy.ruoyi.admin.sys.base.mapper.DistrictsMapper;
import com.cy.ruoyi.admin.sys.base.service.IDistrictsService;
import com.cy.ruoyi.common.sql.page.PageDomain;
import com.cy.ruoyi.common.sql.page.PageUtils;
import com.cy.ruoyi.common.sql.page.Query;
import com.cy.ruoyi.common.utils.text.Convert;
import com.cy.ruoyi.common.utils.util.RegexUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 地区 服务层实现
 * 
 */
@Service
@org.apache.dubbo.config.annotation.Service(validation = "true", version = "${dubbo.provider.IDistrictsService.version}")
public class DistrictsServiceImpl extends ServiceImpl<DistrictsMapper, Districts> implements IDistrictsService
{
	@Autowired
	private DistrictsMapper districtsMapper;

	/**
     * 查询地区信息
     * 
     * @param id 地区ID
     * @return 地区信息
     */
    @Override
	public Districts selectDistrictsById(Integer id)
	{
	    return districtsMapper.selectDistrictsById(id);
	}
	
	/**
     * 查询地区列表
     * 
     * @param districts 地区信息
     * @return 地区集合
     */
	@Override
	public List<Districts> selectDistrictsList(Districts districts)
	{
	    return districtsMapper.selectDistrictsList(districts);
	}
	
    /**
     * 新增地区
     * 
     * @param districts 地区信息
     * @return 结果
     */
	@Override
	public int insertDistricts(Districts districts)
	{
	    return districtsMapper.insertDistricts(districts);
	}
	
	/**
     * 修改地区
     * 
     * @param districts 地区信息
     * @return 结果
     */
	@Override
	public int updateDistricts(Districts districts)
	{
	    return districtsMapper.updateDistricts(districts);
	}

	/**
     * 删除地区对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteDistrictsByIds(String ids)
	{
		return districtsMapper.deleteDistrictsByIds(Convert.toStrArray(ids));
	}

	/**
	 * 根据条件分页查询列表
	 */
	@Override
	public PageUtils selectDistrictsList(PageDomain pageDomain, Districts districts)
	{
		if (RegexUtil.isNull(districts)) {
			districts = new Districts();
		}
		IPage<Districts> page = districtsMapper.selectDistrictsList(new Query<Districts>(pageDomain).getPage(), districts);
		return new PageUtils(page);
	}

}
