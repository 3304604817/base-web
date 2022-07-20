package com.base.common.util.jwt.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Access {
    boolean accessNoToken() default false;
}
