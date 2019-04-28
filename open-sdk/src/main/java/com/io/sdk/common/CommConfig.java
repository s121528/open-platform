package com.io.sdk.common;

import cn.hutool.core.io.resource.NoResourceException;
import cn.hutool.setting.Setting;
import com.io.exception.SelfException;
import org.springframework.util.StringUtils;

/**
 * project -
 *
 * @author guodd
 * @version 1.0
 * @date 日期:2019/4/4 时间:9:38
 * @JDK 1.8
 * @Description 功能模块：
 */
public class CommConfig {
    private String serviceURL = "https://sdts.txffp.com/sdts/app/common/binapi";
    private String tempPath = "/home/sdk/temp";
    private String authStr;
    private static CommConfig instance;

    public String getServiceURL() {
        return serviceURL;
    }

    public void setServiceURL(String serviceURL) {
        this.serviceURL = serviceURL;
    }

    public String getTempPath() {
        return tempPath;
    }

    public void setTempPath(String tempPath) {
        this.tempPath = tempPath;
    }

    String getAuthStr() {
        return authStr;
    }

    public void setAuthStr(String authStr) {
        this.authStr = authStr;
    }

    static CommConfig getInstance() {
        if (instance != null) {
            return instance;
        } else {
            instance = new CommConfig();
            try {
                Setting set = new Setting("sdk.properties");
                if (!StringUtils.isEmpty(set.get("serviceURL"))) {
                    instance.serviceURL = set.get("serviceURL").trim();
                }
                if (!StringUtils.isEmpty(set.get("authStr"))) {
                    instance.authStr = set.get("authStr").trim();
                }
                if (!StringUtils.isEmpty(set.get("tempPath"))) {
                    instance.tempPath = set.get("tempPath").trim();
                }
            } catch (NoResourceException e) {
                throw new SelfException(302, "sdk.properties配置文件不存在或文件格式不正确！");
            }
        }
        return instance;
    }
}
