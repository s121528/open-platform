package com.io.sdk.vo.dayi;

import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.Map;

/**
 * project - GitHub整理 SSH项目
 *
 * @author guodd
 * @version 1.0
 * @date 日期:2019/2/28 时间:9:12
 * @JDK 1.8
 * @Description 功能模块：统一数据返回格式类
 */
public class ApiResult extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    public ApiResult() {
        put("code", 200);
        put("message", "成功");
        put("success", true);
    }

    public static ApiResult error(int code, String msg) {
        ApiResult apiResult = new ApiResult();
        apiResult.put("code", code);
        apiResult.put("message", msg);
        apiResult.put("success", false);
        return apiResult;
    }

    public static ApiResult error() {
        return error(500, "");
    }

    public static ApiResult error(String msg) {
        return error(500, msg);
    }

    public static ApiResult ok(String msg) {
        ApiResult apiResult = new ApiResult();
        apiResult.put("message", msg);
        return apiResult;
    }

    public static ApiResult ok(Map<String, Object> map) {
        ApiResult apiResult = new ApiResult();
        apiResult.putAll(map);
        return apiResult;
    }

    public static ApiResult ok(Object data) {
        ApiResult apiResult = new ApiResult();
        apiResult.put("result", data);
        return apiResult;
    }

    public static ApiResult ok(Object data, Integer total) {
        ApiResult apiResult = new ApiResult();
        apiResult.put("result", data);
        apiResult.put("total", total);
        return apiResult;
    }

    public static ApiResult ok() {
        return new ApiResult();
    }

    public static ApiResult okPage(Page page, Object data) {
        ApiResult apiResult = new ApiResult();
        apiResult.put("result", data);
        apiResult.put("total", page.getNumberOfElements());
        return apiResult;
    }

    public ApiResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public ApiResult data(Object obj) {
        this.put("result", obj);
        return this;
    }

    public ApiResult token(String token) {
        this.put("token", token);
        return this;
    }
}