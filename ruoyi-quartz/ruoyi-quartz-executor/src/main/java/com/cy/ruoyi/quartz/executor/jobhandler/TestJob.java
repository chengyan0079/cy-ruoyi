package com.cy.ruoyi.quartz.executor.jobhandler;

import com.cy.ruoyi.common.job.biz.model.ReturnT;
import com.cy.ruoyi.common.job.handler.IJobHandler;
import com.cy.ruoyi.common.job.handler.annotation.JobHandler;
import com.cy.ruoyi.common.job.handler.annotation.XxlJob;
import com.cy.ruoyi.common.job.handler.impl.MethodJobHandler;
import com.cy.ruoyi.common.job.log.XxlJobLogger;
import com.cy.ruoyi.common.job.util.ShardingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.xml.transform.Source;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * XxlJob开发示例（Bean模式）
 *
 * 开发步骤：
 * 1、在Spring Bean实例中，开发Job方法，方式格式要求为 "public ReturnT<String> execute(String param)"
 * 2、为Job方法添加注解 "@XxlJob(value="自定义jobhandler名称", init = "JobHandler初始化方法", destroy = "JobHandler销毁方法")"，注解value值对应的是调度中心新建任务的JobHandler属性的值。
 * 3、执行日志：需要通过 "XxlJobLogger.log" 打印执行日志；
 *
 * @author xuxueli 2019-12-11 21:52:51
 */
@Component
@JobHandler(value = "TestJob")
public class TestJob extends IJobHandler{
    private static Logger logger = LoggerFactory.getLogger(TestJob.class);

    @Override
    public ReturnT<String> execute(String param) throws Exception {
        XxlJobLogger.log("TEST-JOB, TEST！");
        return ReturnT.SUCCESS;
    }
}
