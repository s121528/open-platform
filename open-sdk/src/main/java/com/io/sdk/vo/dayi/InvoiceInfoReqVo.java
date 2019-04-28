package com.io.sdk.vo.dayi;

/**
 * project -
 *
 * @author guodd
 * @version 1.0
 * @date 日期:2019/4/27 时间:13:38
 * @JDK 1.8
 * @Description 功能模块：
 */
public class InvoiceInfoReqVo {
    private String waybillNum;
    private String companyNum;
    private String clearDate;

    public String getWaybillNum() {
        return waybillNum;
    }

    public void setWaybillNum(String waybillNum) {
        this.waybillNum = waybillNum;
    }

    public String getCompanyNum() {
        return companyNum;
    }

    public void setCompanyNum(String companyNum) {
        this.companyNum = companyNum;
    }

    public String getClearDate() {
        return clearDate;
    }

    public void setClearDate(String clearDate) {
        this.clearDate = clearDate;
    }
}
