package com.logicwu.zhihu.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class LogAspect {


    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Before("execution(* com.logicwu.zhihu.controller.*Controller.*(..))")
    public void beforeMethod(JoinPoint joinPoint) {
        StringBuilder sb = new StringBuilder();
        for (Object arg : joinPoint.getArgs()) {
            if (arg != null) {
                sb.append("arg:" + arg.toString() + "|");
            }
        }
        logger.info("before method:" + sb.toString());
    }

    @After("execution(* com.logicwu.zhihu.controller.IndexController.*(..))")
    public void afterMethod() {
        logger.info("after method" + new Date());
    }
}
