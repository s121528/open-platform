package com.io.service;

import com.io.base.vo.ApiResult;
import com.io.entity.UserEntity;
import com.io.vo.LoginForm;
import com.io.vo.RegisterForm;

/**
 * project -
 *
 * @author guodd
 * @version 1.0
 * @date 日期:2019/4/2 时间:9:07
 * @JDK 1.8
 * @Description 功能模块：
 */
public interface UserServiceI {
    UserEntity queryByMobile(String mobile);

    /**
     * 用户登录
     *
     * @param form 登录表单
     * @return 返回登录信息
     */
    ApiResult login(LoginForm form);

    UserEntity getById(Integer id);

    ApiResult save(RegisterForm form);
}
