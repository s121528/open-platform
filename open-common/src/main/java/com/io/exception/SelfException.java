package com.io.exception;


import com.io.base.vo.ApiResultEnum;

/**
 * project - GitHub整理 Gitee开源系统
 *
 * @author guod
 * @version 3.0
 * @date 日期:2018/7/9 时间:20:41
 * @JDK 1.8
 * @Description 功能模块：指定自己的错误码
 */
public class SelfException extends RuntimeException {
    private Integer code;

    public SelfException(ApiResultEnum apiResultEnum) {
        super(apiResultEnum.getMessage());
        this.code = apiResultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
