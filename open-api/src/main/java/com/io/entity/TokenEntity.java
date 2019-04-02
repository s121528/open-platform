/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.io.entity;

import com.io.base.entity.BaseEntiy;
import lombok.Data;

import javax.persistence.Entity;
import java.util.Date;


/**
 * 用户Token
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
@Entity(name = "tb_app_token")
public class TokenEntity extends BaseEntiy {
    private String token;
    /**
     * 过期时间
     */
    private Date expireTime;
}
