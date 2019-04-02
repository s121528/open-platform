package com.io.vo;

import com.io.base.encryp.EncryptField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * project -
 *
 * @author guodd
 * @version 1.0
 * @date 日期:2019/4/2 时间:14:59
 * @JDK 1.8
 * @Description 功能模块：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVoReq {
    /**
     * 属性描述：姓名
     */
    private String name;

    /**
     * 属性描述：手机号码
     */
    @EncryptField
    private String mobile;

    /**
     * 属性描述：身份证号码
     */
    @EncryptField
    private String idCard;

    /**
     * 属性描述：年龄
     */
    private Integer age;
}
