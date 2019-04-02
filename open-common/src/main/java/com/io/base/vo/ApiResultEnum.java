package com.io.base.vo;

/**
 * project - GitHub整理
 *
 * @author guodd
 * @version 3.0
 * @date 日期:2018/7/9 时间:20:43
 * @JDK 1.8
 * @Description 功能模块：自定义错误码
 */
public enum ApiResultEnum {

    /**
     *
     */
    SUCCESS_VALUE(200, "成功！"),
    ERROR_VALUE(-200, "失败！"),
    APPERROR_VALUE(-200, "token不能为空！"),
    APPTOKEN_VALUE(-200, "token失效，请重新登录！"),
    SERVICE_ERROR(500, "服务异常，请联系管理员！"),
    XYSERVICE_ERROR(600, "IO异常！");


    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    ApiResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
