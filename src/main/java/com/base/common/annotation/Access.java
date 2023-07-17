package com.base.common.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Access {
    /**
     * 不登陆也能访问
     * @return
     */
    boolean accessNoToken() default false;

    /**
     * 只要登录就能访问
     * @return
     */
    boolean accessToken() default false;

    /**
     * 需要权限才能访问
     * @return
     */
    boolean accessPermissions() default true;
}
