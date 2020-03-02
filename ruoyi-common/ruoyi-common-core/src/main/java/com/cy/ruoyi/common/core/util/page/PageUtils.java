package com.cy.ruoyi.common.core.util.page;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页工具类
 */
@Data
public class PageUtils implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 总记录数
	 */
	private long total;

	/**
	 *  当前页
	 */
	private long pageNum;
	/**
	 * 列表数据
	 */
	private List<?> rows;
	
//	/**
//	 * 分页
//	 * @param list        列表数据
//	 * @param totalCount  总记录数
//	 * @param pageSize    每页记录数
//	 * @param currPage    当前页数
//	 */
//	public PageUtils(List<?> list, int totalCount, int pageSize, int currPage) {
//		this.list = list;
//		this.totalCount = totalCount;
//		this.pageSize = pageSize;
//		this.currPage = currPage;
//		this.totalPage = (int) Math.ceil((double)totalCount/pageSize);
//	}

	/**
	 * 分页
	 */
	public PageUtils(IPage<?> page) {
		this.rows = page.getRecords();
		this.total = page.getTotal();
		this.pageNum = page.getCurrent();
	}

}
