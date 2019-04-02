package com.io.service;

import com.io.base.vo.ApiResult;
import com.io.entity.TokenEntity;

/**
 * project -
 *
 * @author guodd
 * @version 1.0
 * @date 日期:2019/4/2 时间:9:08
 * @JDK 1.8
 * @Description 功能模块：
 */
public interface TokenServiceI {
    /**
     * 通过令牌查询
     *
     * @param token
     * @return
     */
    TokenEntity queryByToken(String token);

    /**
     * 生成token
     *
     * @param id 用户ID
     * @return 返回token信息
     */
    TokenEntity createToken(Integer id);

    /**
     * 设置token过期
     *
     * @param id 用户ID
     */
    ApiResult expireToken(Integer id);
}
