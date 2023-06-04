package com.base.common.annotation;

import com.base.common.autoconfigure.BaseWebAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(BaseWebAutoConfiguration.class)
public @interface EnableBaseWeb {
}
