package com.cy.ruoyi.common.log.aspect;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import java.lang.reflect.Parameter;

/**
 * AOP拦截方法打印参数和返回参数
 *
 */
@Aspect
@Component
public class LoggingAspect {

    private static final Log log = LogFactory.get();

    /**
     * 拦截所有controller包下的方法
     */
    @Pointcut("execution(* com.cy.ruoyi.*.*.controller..*.*(..))")
    private void controllerMethod() {
    }

    /**
     * 拦截dubbo服务所有的方法
     */
    @Pointcut("@within(org.apache.dubbo.config.annotation.Service)")
    public void DubboServiceMethod() {
    }

    @Around("DubboServiceMethod() || controllerMethod()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        // 所在的类.方法
        String msgInfo = "@AOP日志[" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() + "]";
        String requestStr = getRequestParam(joinPoint);
        log.info(msgInfo + "start.输入参数：" + requestStr);
        long startTime = System.currentTimeMillis();
        Object result = null;
        try {
            // 执行完方法的返回值：调用proceed()方法，就会触发切入点方法执行
            result = joinPoint.proceed();
        } catch (Exception e) {
            //如果有异常继续抛
            throw e;
        } finally {
            long handleTime = System.currentTimeMillis() - startTime;
            String responseStr = result == null ? "无" : JSON.toJSONString(result);
            StringBuffer endString = new StringBuffer(100);
            endString.append(msgInfo).append("end.");
            endString.append("耗时(" + handleTime + "ms)");
            endString.append("输出结果：").append(responseStr);
            log.info(endString.toString());
        }
        return result;
    }

    /**
     * 获取请求参数
     *
     * @param point
     * @return
     */
    private String getRequestParam(ProceedingJoinPoint point) {
        Object[] methodArgs = point.getArgs();
        Parameter[] parameters = ((MethodSignature) point.getSignature()).getMethod().getParameters();
        String requestStr;
        try {
            requestStr = logParam(parameters, methodArgs);
        } catch (Exception e) {
            requestStr = "获取参数失败";
        }
        return requestStr;
    }

    /**
     * 拼接请求参数
     */
    private String logParam(Parameter[] paramsArgsName, Object[] paramsArgsValue) {
        if (ArrayUtils.isEmpty(paramsArgsName) || ArrayUtils.isEmpty(paramsArgsValue)) {
            return "";
        }
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < paramsArgsValue.length; i++) {
            //参数名
            String name = paramsArgsName[i].getName();
            //参数值
            Object value = paramsArgsValue[i];
            buffer.append(name + "=");
            if (value instanceof String) {
                buffer.append(value + ",");
            } else {
                buffer.append(JSON.toJSONString(value) + ",");
            }
        }
        return buffer.toString();
    }
}
