package com.io.exception;

import com.io.base.vo.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * project - GitHub整理 Gitee开源系统
 *
 * @author dong
 * @version 3.0
 * @date 日期:2018/7/9 时间:20:40
 * @JDK 1.8
 * @Description 功能模块：
 */
@RestControllerAdvice
@Component
public class ExceptionHandle {
    /**
     * 日志输出
     */
    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    /**
     * 功能描述：自定义异常和全局异常捕捉处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ApiResult handle(Exception e) {
        if (e instanceof SelfException) {
            SelfException selfException = (SelfException) e;
            return ApiResult.error(selfException.getCode(), selfException.getMessage());
        } else {
            logger.error("系统异常[{}]", (Object) e.getStackTrace());
            return ApiResult.error(500, "系统异常");
        }
    }

    /**
     * 功能描述：from数据校验异常
     *
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    public ApiResult valid(BindException e, HttpServletRequest request) {
        printlnException(request, e);
        ApiResult j = new ApiResult();
        j.put("success", false);
        j.put("code", -406);
        j.put("message", "http状态码406，不接受From数据校验异常。");
        if (!e.getBindingResult().hasErrors()) {
            j.put("message", "没有找到对应校验异常!");
            return j;
        }
        j.put("message", e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return j;
    }


    /**
     * 功能描述：Json数据校验异常
     *
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ApiResult valid(MethodArgumentNotValidException e, HttpServletRequest request) {
        printlnException(request, e);
        ApiResult j = new ApiResult();
        j.put("success", false);
        j.put("code", -406);
        j.put("data", "http状态码406，不接受Json数据校验异常。");
        if (!e.getBindingResult().hasErrors()) {
            j.put("message", "没有找到校验异常!");
            return j;
        }
        j.put("message", e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return j;
    }


    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ApiResult valid(DataIntegrityViolationException e, HttpServletRequest request) {
        printlnException(request, e);
        ApiResult j = new ApiResult();
        j.put("success", false);
        j.put("code", -407);
        j.put("data", "http状态码407，数据库中该字段是唯一索引，不能重复。");
        j.put("message", e.getLocalizedMessage());
        return j;
    }

    /**
     * 功能描述：异常数据的打印
     *
     * @param request
     * @param e
     * @throws IOException
     */
    private void printlnException(HttpServletRequest request, Throwable e) {
        // 相关记录
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        // 日志打印
        logger.error("******************************");
        logger.error("出错详细日志：url:[{}],method:[{}],uri:[{}],params:[{}]", url, method, uri, queryString);
        logger.error("出错异常:", e);
        logger.error("******************************");
    }
}