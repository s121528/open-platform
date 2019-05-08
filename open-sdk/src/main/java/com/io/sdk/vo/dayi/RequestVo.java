package com.io.sdk.vo.dayi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * project -
 *
 * @author guodd
 * @version 1.0
 * @date 日期:2019/4/25 时间:9:01
 * @JDK 1.8
 * @Description 功能模块：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestVo {
    private String companyCode;
    private String requestType;
    private String content;
}
