package com.cy.ruoyi.user.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.ruoyi.common.core.util.page.PageDomain;
import com.cy.ruoyi.common.core.util.page.PageUtils;
import com.cy.ruoyi.user.api.entity.Districts;
import com.cy.ruoyi.user.api.entity.SysConfig;

import java.util.List;

/**
 * 地区 服务层
 */
public interface IDistrictsService extends IService<Districts>
{
	/**
     * 查询地区信息
     * 
     * @param id 地区ID
     * @return 地区信息
     */
	Districts selectDistrictsById(Integer id);
	
	/**
     * 查询地区列表
     * 
     * @param districts 地区信息
     * @return 地区集合
     */
	List<Districts> selectDistrictsList(Districts districts);
	
	/**
     * 新增地区
     * 
     * @param districts 地区信息
     * @return 结果
     */
	int insertDistricts(Districts districts);
	
	/**
     * 修改地区
     * 
     * @param districts 地区信息
     * @return 结果
     */
	int updateDistricts(Districts districts);
		
	/**
     * 删除地区信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	int deleteDistrictsByIds(String ids);

	//*************************************************************************************
	/**
	 *  分页查询
	 * @param pageDomain
	 * @param districts
	 * @return
	 */
	PageUtils selectDistrictsList(PageDomain pageDomain, Districts districts);

}
