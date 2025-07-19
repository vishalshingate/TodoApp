package com.example.todoapi.Contollers;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Aspect
@Component

public class TimeMonitorAspect {
    private static Logger logger = LoggerFactory.getLogger(TimeMonitor.class);
    @Around("@annotation(TimeMonitor)")
    public void logtime(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.warn("Started execution");
        long startTime = System.currentTimeMillis();
        try{
            joinPoint.proceed();
        }catch(Throwable e){
           logger.warn("Exception in execution "+ e.getMessage() );
        }
        finally{
            long endTime = System.currentTimeMillis();

          logger.warn("Execution time: " + (endTime - startTime) + "ms");
        }



    }


}
