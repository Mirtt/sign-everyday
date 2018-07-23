package com.mirt.sign.aspect;

import com.mirt.sign.common.ResultJson;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 记录日志切面
 *
 * @author Mirt
 * @date 2018/7/5.
 */

@Aspect
@Component
public class LogAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("execution(public * com.mirt.sign.controller.*.*(..))")
    public void controllerLog() {
    }

    @Before("controllerLog()")
    public void logBefore(JoinPoint jp) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        logger.info("=== " + request.getMethod() + ":" + request.getRequestURL() + " ===");
        logger.info("调用：" + jp.getSignature().getName() + "方法");
    }

    @AfterReturning(pointcut = "controllerLog()", returning = "result")
    public void logAfter(JoinPoint jp, ResultJson result) {
        logger.info("返回结果 :");
        logger.info(result.toString());
    }
}
