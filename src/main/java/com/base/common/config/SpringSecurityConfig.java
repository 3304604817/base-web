package com.base.common.config;

import com.base.common.util.jwt.JwtAuthenticationFilter;
import com.base.common.util.jwt.annotation.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

/**
 * @author gaoyang
 * 登录认证配置
 */
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private RequestMappingHandlerMapping handlerMapping;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    /**
     * 使用自定义注解实现注解接口不走 Spring Security 过滤器链
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = handlerMapping.getHandlerMethods();
        handlerMethods.forEach((info, method) -> {
            // 获取请求控制层注解，有 Access 注解且 accessNoToken = true 不走 Spring Security 过滤器链
            Access access = method.getMethodAnnotation(Access.class);
            if (Objects.nonNull(access) && access.accessNoToken()){
                info.getMethodsCondition().getMethods().forEach(requestMethod -> {
                    switch (requestMethod) {
                        case GET:
                            info.getPatternsCondition().getPatterns().forEach(pattern -> {
                                web.ignoring().antMatchers(HttpMethod.GET, pattern);
                            });
                            break;
                        case POST:
                            info.getPatternsCondition().getPatterns().forEach(pattern -> {
                                web.ignoring().antMatchers(HttpMethod.POST, pattern);
                            });
                            break;
                        case DELETE:
                            info.getPatternsCondition().getPatterns().forEach(pattern -> {
                                web.ignoring().antMatchers(HttpMethod.DELETE, pattern);
                            });
                            break;
                        case PUT:
                            info.getPatternsCondition().getPatterns().forEach(pattern -> {
                                web.ignoring().antMatchers(HttpMethod.PUT, pattern);
                            });
                            break;
                        default:
                            break;
                    }
                });
            }
        });
    }

    /**
     * 安全拦截机制配置
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 关闭csrf防护 >只有关闭了,来自表单的请求才能进来
        http.csrf().disable();
        // 防止iframe
        http.headers().frameOptions().disable();
        // 禁用session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 表单认证
        http.formLogin()
                /**
                 * 设置登录页面
                 * 1、当请求未登录时会自动请求这个url
                 * 2、输入网站地址也会自动请求这个url
                 */
                .loginPage("/page/login-0.html")
                // 登录页面表单的提交地址
                .loginProcessingUrl("/security-login")
                // 登录成功后请求地址 请求方法必须是post的
                .successForwardUrl("/login/index");
        // url拦截认证  > 所有请求都必须被认证 必须登录后才可以访问
        http.authorizeRequests()
                // 设置不需要拦截的页面-这里需要注意的是把登录的页面和一些静态资源设置为不需要拦截
                .antMatchers(
                        "/**/login/**",
                        "/**/roms/**", "/**/api/**", "/**/css/**", "/**/images/**", "/**/js/**", "/**/lib/**", "/**/page/**", "/**/**.html",
                        "/**/error",
                        "/**/swagger-resources/**", "/**/swagger-ui/**", "/**/api-docs/**"
                ).permitAll()
                // 所有请求都必须被认证,必须登录后才能访问
                .anyRequest().authenticated();
        // 添加自定义过滤器
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * 密码编码器
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
