package com.io.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.io.base.log.SysLog;
import com.io.base.log.SysLogInfoEntity;
import com.io.base.vo.ApiResult;
import com.io.dao.LogDaoI;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * project -
 *
 * @author guodd
 * @version 1.0
 * @date 日期:2019/4/2 时间:15:39
 * @JDK 1.8
 * @Description 功能模块：
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    @Autowired
    private LogDaoI logDaoI;

    @Autowired
    private ObjectMapper mapper;

    /**
     * 日志注解声明切点
     */
    @Pointcut("@annotation(com.io.base.log.SysLog)")
    public void annotationPointCutLog() {
    }


    @Around("annotationPointCutLog()")
    public Object aroundLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result;
        ApiResult j;
        result = joinPoint.proceed();
        j = (ApiResult) result;
        //方法执行失败，则直接返回
        if ("success".equals(j.get("success"))) {
            return result;
        }
        //保存系统日志
        this.saveSysLog(joinPoint, result);
        return result;
    }

    /**
     * 保存系统日志信息
     */
    private void saveSysLog(ProceedingJoinPoint joinPoint, Object result) throws Exception {
        SysLogInfoEntity entity = new SysLogInfoEntity();
        //设置操作人信息，可以从redis中获取
        if (true) {
            entity.setOperatorId("123456");
            entity.setOperator("郭冬冬");
        }
        entity.setUpdateTime(LocalDateTime.now());
        //获取系统日志注解，并设置操作类型和操作描述
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        SysLog sysLog = signature.getMethod().getAnnotation(SysLog.class);
        entity.setModule(sysLog.module());
        entity.setType(sysLog.type());
        entity.setTitle(sysLog.name());
        //请求参数和返回结果
        List<Object> params = new ArrayList<>();
        for (Object param : joinPoint.getArgs()) {
            if (param instanceof MultipartFile) {
                MultipartFile file = (MultipartFile) param;
                params.add(file.getOriginalFilename());
                continue;
            }
            params.add(param);
        }
        entity.setReqParam(mapper.writeValueAsString(params));
        entity.setResult(mapper.writeValueAsString(result));
        entity.setCreateTime(LocalDateTime.now());
        entity.setUpdateTime(LocalDateTime.now());
        entity.setRemove(false);
        //持久化到库
        this.logDaoI.save(entity);
    }
}