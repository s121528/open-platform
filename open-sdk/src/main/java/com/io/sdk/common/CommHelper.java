package com.io.sdk.common;

import cn.hutool.core.util.RandomUtil;
import com.io.base.vo.BaseOperationRes;
import org.springframework.util.StringUtils;

/**
 * project -
 *
 * @author guodd
 * @version 1.0
 * @date 日期:2019/4/4 时间:9:33
 * @JDK 1.8
 * @Description 功能模块：
 */
public class CommHelper {

    public static String getFileName(BaseOperationRes request) {
        String authStr = CommConfig.getInstance().getAuthStr();
        if (StringUtils.isEmpty(authStr)) {
            throw new IllegalArgumentException("authStr is empty");
        } else {
            return RandomUtil.randomString(15);
        }
    }

}
