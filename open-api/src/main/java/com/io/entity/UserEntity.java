/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.io.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.io.base.entity.BaseEntiy;
import lombok.Data;

import javax.persistence.Entity;


/**
 * 用户
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
@Entity(name = "tb_app_user")
public class UserEntity extends BaseEntiy {
    /**
     * 用户名
     */
    private String username;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 密码
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}
