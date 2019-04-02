package com.io.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.io.base.encryp.EncryptField;
import com.io.base.vo.ApiResult;
import com.io.util.AseUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;

/**
 * project -
 *
 * @author yanfa07
 * @version 1.0
 * @date 日期:2019/4/2 时间:16:10
 * @JDK 1.8
 * @Description 功能模块：
 */
@Aspect
@Component
@Slf4j
public class EncryptAspect {
    @Value("${secret.key}")
    private String secretKey;

    /**
     * 加密注解声明切点
     */
    @Pointcut("@annotation(com.io.base.encryp.EncryptMethod)")
    public void annotationPointCutEncrypt() {
    }

    /**
     * 功能描述：敏感数据处理
     *
     * @param joinPoint
     * @return
     */
    @Around("annotationPointCutEncrypt()")
    public Object around(ProceedingJoinPoint joinPoint) {
        Object responseObj = null;
        try {
            if (StringUtils.isEmpty(joinPoint.getArgs())) {
                Object requestObj = joinPoint.getArgs()[0];
                handleEncrypt(requestObj);
            }
            responseObj = joinPoint.proceed();
            handleDecrypt(responseObj);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            log.error("SecureFieldAop处理出现异常[{}]", throwable);
        }
        return responseObj;
    }

    /**
     * 处理加密
     *
     * @param requestObj
     */
    private void handleEncrypt(Object requestObj) throws IllegalAccessException {
        if (Objects.isNull(requestObj)) {
            return;
        }
        Field[] fields = requestObj.getClass().getDeclaredFields();
        for (Field field : fields) {
            boolean hasSecureField = field.isAnnotationPresent(EncryptField.class);
            if (hasSecureField) {
                field.setAccessible(true);
                String plaintextValue = (String) field.get(requestObj);
                String encryptValue = AseUtil.encrypt(plaintextValue, secretKey);
                field.set(requestObj, encryptValue);
            }
        }
    }

    /**
     * 处理解密
     *
     * @param responseObj
     */
    private Object handleDecrypt(Object responseObj) throws IllegalAccessException {
        if (Objects.isNull(responseObj)) {
            return null;
        }
        ApiResult apiResult = (ApiResult) responseObj;
        String data = JSON.toJSONString(apiResult.get("data"));
        if (data.contains("},")) {
            JSONArray dataArrays = JSON.parseArray(JSON.toJSONString(apiResult.get("data")));
            for (Object dataArray : dataArrays) {
                Map map = (Map) dataArray;
                for (Object key : map.keySet()) {
                    System.out.println(key + " = " + map.get(key));
                }
                Field[] fields = dataArray.getClass().getDeclaredFields();
                for (Field field : fields) {
                    boolean hasSecureField = field.isAnnotationPresent(EncryptField.class);
                    if (hasSecureField) {
                        field.setAccessible(true);
                        String encryptValue = (String) field.get(dataArray);
                        String plaintextValue = AseUtil.decrypt(encryptValue, secretKey);
                        field.set(dataArray, plaintextValue);
                    }
                }
            }
        } else {
            Field[] fields = apiResult.get("data").getClass().getDeclaredFields();
            for (Field field : fields) {
                boolean hasSecureField = field.isAnnotationPresent(EncryptField.class);
                if (hasSecureField) {
                    // 进行解密处理
                    field.setAccessible(true);
                    String encryptValue = (String) field.get(apiResult.get("data"));
                    String plaintextValue = AseUtil.decrypt(encryptValue, secretKey);
                    field.set(apiResult.get("data"), plaintextValue);
                }
            }
        }
        return responseObj;
    }
}
