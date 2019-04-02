package com.io.service;

import com.io.base.vo.ApiResult;
import com.io.vo.UserVoReq;

/**
 * project -
 *
 * @author guodd
 * @version 1.0
 * @date 日期:2019/4/2 时间:14:53
 * @JDK 1.8
 * @Description 功能模块：
 */
public interface UserServiceI {
    ApiResult saveUser(UserVoReq userVoReq);

    ApiResult getAll();

}
