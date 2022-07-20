package com.base.common.config;

import com.base.common.util.audit.AuditHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author gaoyang
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 审计拦截器
     */
    @Autowired
    private AuditHandlerInterceptor auditHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         * addPathPatterns 用于添加拦截规则
         * excludePathPatterns 用户排除拦截
         */
        registry.addInterceptor(auditHandlerInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/static/**", "/js/**", "/lib/**", "/images/**",
                        "/**/error",
                        "/**/*.html", "/**/*.css", "/**/*.png"
                );
    }
}
