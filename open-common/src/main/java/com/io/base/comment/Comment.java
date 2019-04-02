package com.io.base.comment;


import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Comment {
    /**
     * 字段注释名称
     *
     * @return
     */
    String value() default "";
}