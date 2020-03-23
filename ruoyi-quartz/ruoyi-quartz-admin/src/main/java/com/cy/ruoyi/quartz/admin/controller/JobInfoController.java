package com.cy.ruoyi.quartz.admin.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.cy.ruoyi.common.job.biz.model.ReturnT;
import com.cy.ruoyi.common.job.enums.ExecutorBlockStrategyEnum;
import com.cy.ruoyi.common.job.glue.GlueTypeEnum;
import com.cy.ruoyi.common.job.util.DateUtil;
import com.cy.ruoyi.quartz.admin.core.cron.CronExpression;
import com.cy.ruoyi.quartz.admin.core.exception.XxlJobException;
import com.cy.ruoyi.quartz.admin.core.model.XxlJobGroup;
import com.cy.ruoyi.quartz.admin.core.model.XxlJobInfo;
import com.cy.ruoyi.quartz.admin.core.model.XxlJobUser;
import com.cy.ruoyi.quartz.admin.core.route.ExecutorRouteStrategyEnum;
import com.cy.ruoyi.quartz.admin.core.thread.JobTriggerPoolHelper;
import com.cy.ruoyi.quartz.admin.core.trigger.TriggerTypeEnum;
import com.cy.ruoyi.quartz.admin.core.util.I18nUtil;
import com.cy.ruoyi.quartz.admin.dao.XxlJobGroupDao;
import com.cy.ruoyi.quartz.admin.service.LoginService;
import com.cy.ruoyi.quartz.admin.service.XxlJobService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.*;

/**
 * index controller
 * @author xuxueli 2015-12-19 16:13:16
 */
@Controller
@RequestMapping("/jobinfo")
public class JobInfoController {

	@Resource
	private XxlJobGroupDao xxlJobGroupDao;
	@Resource
	private XxlJobService xxlJobService;
	
	@RequestMapping
	public String index(HttpServletRequest request, Model model, @RequestParam(required = false, defaultValue = "-1") int jobGroup) {

		// 枚举-字典
		model.addAttribute("ExecutorRouteStrategyEnum", ExecutorRouteStrategyEnum.values());	    // 路由策略-列表
		model.addAttribute("GlueTypeEnum", GlueTypeEnum.values());								// Glue类型-字典
		model.addAttribute("ExecutorBlockStrategyEnum", ExecutorBlockStrategyEnum.values());	    // 阻塞处理策略-字典

		// 执行器列表
		List<XxlJobGroup> jobGroupList_all =  xxlJobGroupDao.findAll();

		// filter group
		List<XxlJobGroup> jobGroupList = filterJobGroupByRole(request, jobGroupList_all);
		if (jobGroupList==null || jobGroupList.size()==0) {
			throw new XxlJobException(I18nUtil.getString("jobgroup_empty"));
		}

		model.addAttribute("JobGroupList", jobGroupList);
		model.addAttribute("jobGroup", jobGroup);

		return "jobinfo/jobinfo.index";
	}

	public static List<XxlJobGroup> filterJobGroupByRole(HttpServletRequest request, List<XxlJobGroup> jobGroupList_all){
		List<XxlJobGroup> jobGroupList = new ArrayList<>();
		if (jobGroupList_all!=null && jobGroupList_all.size()>0) {
			XxlJobUser loginUser = (XxlJobUser) request.getAttribute(LoginService.LOGIN_IDENTITY_KEY);
			if (loginUser.getRole() == 1) {
				jobGroupList = jobGroupList_all;
			} else {
				List<String> groupIdStrs = new ArrayList<>();
				if (loginUser.getPermission()!=null && loginUser.getPermission().trim().length()>0) {
					groupIdStrs = Arrays.asList(loginUser.getPermission().trim().split(","));
				}
				for (XxlJobGroup groupItem:jobGroupList_all) {
					if (groupIdStrs.contains(String.valueOf(groupItem.getId()))) {
						jobGroupList.add(groupItem);
					}
				}
			}
		}
		return jobGroupList;
	}
	public static void validPermission(HttpServletRequest request, int jobGroup) {
		XxlJobUser loginUser = (XxlJobUser) request.getAttribute(LoginService.LOGIN_IDENTITY_KEY);
		if (!loginUser.validPermission(jobGroup)) {
			throw new RuntimeException(I18nUtil.getString("system_permission_limit") + "[username="+ loginUser.getUsername() +"]");
		}
	}
	
	@RequestMapping("/pageList")
	@ResponseBody
	@SentinelResource("pageList")
	public Map<String, Object> pageList(@RequestParam(required = false, defaultValue = "0") int start,  
			@RequestParam(required = false, defaultValue = "10") int length,
			int jobGroup, int triggerStatus, String jobDesc, String executorHandler, String author) {
		
		return xxlJobService.pageList(start, length, jobGroup, triggerStatus, jobDesc, executorHandler, author);
	}
	
	@RequestMapping("/add")
	@ResponseBody
	@SentinelResource("add")
	public ReturnT<String> add(XxlJobInfo jobInfo) {
		return xxlJobService.add(jobInfo);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	@SentinelResource("update")
	public ReturnT<String> update(XxlJobInfo jobInfo) {
		return xxlJobService.update(jobInfo);
	}
	
	@RequestMapping("/remove")
	@ResponseBody
	@SentinelResource("remove")
	public ReturnT<String> remove(int id) {
		return xxlJobService.remove(id);
	}
	
	@RequestMapping("/stop")
	@ResponseBody
	@SentinelResource("stop")
	public ReturnT<String> pause(int id) {
		return xxlJobService.stop(id);
	}
	
	@RequestMapping("/start")
	@ResponseBody
	@SentinelResource("start")
	public ReturnT<String> start(int id) {
		return xxlJobService.start(id);
	}
	
	@RequestMapping("/trigger")
	@ResponseBody
	@SentinelResource("trigger")
	//@PermissionLimit(limit = false)
	public ReturnT<String> triggerJob(int id, String executorParam) {
		// force cover job param
		if (executorParam == null) {
			executorParam = "";
		}

		JobTriggerPoolHelper.trigger(id, TriggerTypeEnum.MANUAL, -1, null, executorParam);
		return ReturnT.SUCCESS;
	}

	@RequestMapping("/nextTriggerTime")
	@ResponseBody
	@SentinelResource("nextTriggerTime")
	public ReturnT<List<String>> nextTriggerTime(String cron) {
		List<String> result = new ArrayList<>();
		try {
			CronExpression cronExpression = new CronExpression(cron);
			Date lastTime = new Date();
			for (int i = 0; i < 5; i++) {
				lastTime = cronExpression.getNextValidTimeAfter(lastTime);
				if (lastTime != null) {
					result.add(DateUtil.formatDateTime(lastTime));
				} else {
					break;
				}
			}
		} catch (ParseException e) {
			return new ReturnT<List<String>>(ReturnT.FAIL_CODE, I18nUtil.getString("jobinfo_field_cron_unvalid"));
		}
		return new ReturnT<List<String>>(result);
	}
	
}
