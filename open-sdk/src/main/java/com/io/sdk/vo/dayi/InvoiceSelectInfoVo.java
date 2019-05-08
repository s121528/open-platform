package com.io.sdk.vo.dayi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * project -
 *
 * @author guodd
 * @version 1.0
 * @date 日期:2019/4/19 时间:8:34
 * @JDK 1.8
 * @Description 功能模块：
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceSelectInfoVo {
    /**
     * 属性描述：运单号
     */
    private String waybillNum;
    /**
     * 属性描述：公司编号
     */
    private String companyNum;
}
