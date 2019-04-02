/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.io.interceptor;


import com.io.annotation.Login;
import com.io.base.vo.ApiResultEnum;
import com.io.dao.TokenDaoI;
import com.io.entity.TokenEntity;
import com.io.exception.SelfException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限(Token)验证
 *
 * @author Mark sunlightcs@gmail.com
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private TokenDaoI tokenDaoI;

    public static final String USER_KEY = "ID";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Login annotation;
        if (handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(Login.class);
        } else {
            return true;
        }

        if (annotation == null) {
            return true;
        }
        //从header中获取token
        String token = request.getHeader("token");
        //如果header中不存在token，则从参数中获取token
        if (StringUtils.isBlank(token)) {
            token = request.getParameter("token");
        }
        //token为空
        if (StringUtils.isBlank(token)) {
            throw new SelfException(ApiResultEnum.APPERROR_VALUE);
        }
        //查询token信息
        TokenEntity tokenEntity = tokenDaoI.findByToken(token);
        if (tokenEntity == null || tokenEntity.getExpireTime().getTime() < System.currentTimeMillis()) {
            throw new SelfException(ApiResultEnum.APPTOKEN_VALUE);
        }
        //设置id到request里，后续根据id，获取用户信息
        request.setAttribute(USER_KEY, tokenEntity.getId());
        return true;
    }
}
