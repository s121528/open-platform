package com.io.base.vo;

import java.util.HashMap;

/**
 * project - GitHub整理
 *
 * @author guodd
 * @version 1.0
 * @date 日期:2019/2/28 时间:17:23
 * @JDK 1.8
 * @Description 功能模块：
 */
public class MapData extends HashMap {
    public MapData addObj(String key, Object value) {
        this.put(key, value);
        return this;
    }

    public MapData addChat(String chat) {
        this.put("chat", chat);
        return this;
    }

    public MapData addAmount(String amount) {
        this.put("amount", amount);
        return this;
    }

    public MapData addOutTradeNo(String outTradeNo) {
        this.put("outTradeNo", outTradeNo);
        return this;
    }

    public MapData addActualProfit(String actualProfit) {
        this.put("actualProfit", actualProfit);
        return this;
    }

    public MapData addTradeStatus(String tradeStatus) {
        this.put("tradeStatus", tradeStatus);
        return this;
    }

    public MapData addPresentGiftUuid(String presentGiftUuid) {
        this.put("presentGiftUuid", presentGiftUuid);
        return this;
    }
}
