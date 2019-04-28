package com.io.sdk.vo.dayi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * project -
 *
 * @author yanfa07
 * @version 1.0
 * @date 日期:2019/4/16 时间:10:12
 * @JDK 1.8
 * @Description 功能模块：
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WaybillStartVo {
    /**
     * 属性描述：企业编号
     */
    private String companyNum;
    /**
     * 属性描述：企业ID
     */
    private String companyId;
    /**
     * 属性描述：运单编号
     */
    private String num;
    /**
     * 属性描述：运单实际目的地址
     */
    private String realDestAddr;
    /**
     * 属性描述：运单实际结束时间
     */
    private String endTime;
    /**
     * 属性描述：开票时长
     */
    private Integer invoiceTime;
}
