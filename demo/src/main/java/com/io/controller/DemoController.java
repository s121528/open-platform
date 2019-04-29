package com.io.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.io.base.vo.ApiResult;
import com.io.sdk.config.MkRestTemplate;
import com.io.sdk.util.DESUtil;
import com.io.sdk.vo.dayi.InvoiceInfoReqVo;
import com.io.sdk.vo.dayi.RequestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * project -
 *
 * @author yanfa07
 * @version 1.0
 * @date 日期:2019/4/28 时间:16:14
 * @JDK 1.8
 * @Description 功能模块：
 */
@RestController
public class DemoController {
    @Autowired
    private MkRestTemplate mkRestTemplate;

    /**
     * 功能描述：test
     */
    @RequestMapping(value = "/test")
    public ApiResult test() throws Exception {
        InvoiceInfoReqVo invoiceInfoReqVo = new InvoiceInfoReqVo();
        invoiceInfoReqVo.setWaybillNum("1801010363760");
        invoiceInfoReqVo.setCompanyNum("38");
        String s3 = DESUtil.encode(JSONUtil.toJsonStr(invoiceInfoReqVo));
        RequestVo requestVo = new RequestVo();
        requestVo.setCompanyCode("01201901220939520001");
        requestVo.setRequestType("1");
        requestVo.setContent(s3);
        String postForObject = mkRestTemplate.postForObject("http://127.0.0.1:8108/rms/invoice/invoiceInfos", requestVo, String.class);
        JSONObject jsonObject = JSONUtil.parseObj(postForObject);
        String message = DESUtil.decode(JSONUtil.toJsonStr(jsonObject.get("message")), "eswri4l4l7j3deed749kkymj", "3213abcd");
        System.out.println("====================下面===========");
        System.out.println(message);
        return ApiResult.ok(message);
    }
}
