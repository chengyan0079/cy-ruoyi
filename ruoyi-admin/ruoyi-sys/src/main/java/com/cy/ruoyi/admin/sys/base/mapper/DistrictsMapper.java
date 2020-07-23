package com.cy.ruoyi.admin.sys.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cy.ruoyi.admin.sys.base.entity.Districts;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 地区 数据层
 */
@Mapper
public interface DistrictsMapper extends BaseMapper<Districts>
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
	List<Districts> selectDistrictsList(@Param("dist") Districts districts);

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
	 * 删除地区
	 *
	 * @param id 地区ID
	 * @return 结果
	 */
	int deleteDistrictsById(Integer id);

	/**
	 * 批量删除地区
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	int deleteDistrictsByIds(String[] ids);

	//************************************************************************
	/**
	 * 根据条件分页查询列表
	 * @param page
	 * @param districts
	 * @return
	 */
	IPage<Districts> selectDistrictsList(Page page, @Param("dist") Districts districts);
	
}