package com.cy.ruoyi.admin.sys.controller;

import com.cy.ruoyi.common.core.basic.controller.BaseController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 通知公告 提供者
 */
@RestController
@RequestMapping("notice")
@Api(value = "SysNoticeClient",description = "通知公告")
public class SysNoticeClient extends BaseController
{
//	private static final Log log = LogFactory.get();
//
//	@Autowired
//	private ISysNoticeService sysNoticeService;
//
//	/**
//	 * 查询通知公告
//	 */
//	@GetMapping("get/{noticeId}")
//	@ApiOperation(value = "get/{noticeId}")
//	public SysNotice get(@PathVariable("noticeId") Long noticeId)
//	{
//		return sysNoticeService.selectNoticeById(noticeId);
//
//	}
//
//	/**
//	 * 查询通知公告列表
//	 */
//	@PostMapping("list")
//	@ApiOperation(value = "查询通知公告列表")
//	public List<SysNotice> list(SysNotice sysNotice, PageDomain page)
//	{
//		PageDomain pageDomain = getPageInfo();
//		log.info("开始查询第[{}]页[{}]条的数据!",pageDomain.getPageNum(), pageDomain.getPageSize());
//		PageUtils page = sysNoticeService.selectNoticeList(pageDomain, sysNotice);
//		return R.ok(page);
//	}
//
//
//	/**
//	 * 新增保存通知公告
//	 */
//	@PostMapping("save")
//	@ApiOperation(value = "新增保存通知公告")
//	public int addSave(SysNotice sysNotice)
//	{
//		return sysNoticeService.insertNotice(sysNotice);
//	}
//
//	/**
//	 * 修改保存通知公告
//	 */
//	@PostMapping("update")
//	@ApiOperation(value = "修改保存通知公告")
//	public int editSave(SysNotice sysNotice)
//	{
//		return sysNoticeService.updateNotice(sysNotice);
//	}
//
//	/**
//	 * 删除通知公告
//	 */
//	@PostMapping("remove")
//	@ApiOperation(value = "删除通知公告")
//	public int remove(String ids)
//	{
//		return sysNoticeService.deleteNoticeByIds(ids);
//	}
	
}
