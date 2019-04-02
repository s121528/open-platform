package com.io.base.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * project - GitHub整理
 *
 * @author guodd
 * @version 1.0
 * @date 日期:2019/3/5 时间:15:57
 * @JDK 1.8
 * @Description 功能模块：
 */
@Setter
@Getter
public class PageReq {
    /**
     * 功能描述：page
     */
    private Integer page;
    /**
     * 功能描述：size
     */
    private Integer size;

    public PageReq() {
    }

    public PageReq(Builder builder) {
        this.page = builder.page;
        this.size = builder.size;
    }

    public static class Builder {
        private Integer page;
        private Integer size;

        public Builder() {
        }

        public Builder page(Integer page) {
            if (page == null) {
                throw new IllegalArgumentException("");
            } else {
                this.page = page;
                return this;
            }
        }

        public Builder size(Integer size) {
            if (size < 0) {
                throw new IllegalArgumentException("");
            } else {
                this.size = size;
                return this;
            }
        }

        public PageReq builder() {
            return new PageReq(this);
        }
    }
}
