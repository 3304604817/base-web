package com.base.common.util.jwt;

import com.auth0.jwt.interfaces.Claim;
import com.base.basic.domain.vo.v0.CurrentUserVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * JWT鉴权过滤器
 * OncePerRequestFilter：过滤器只执行一次
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        // 从请求头中取出 token  这里需要和前端约定好把jwt放到请求头一个叫token的地方
        String token = null;
        Cookie[] cookies = null;
        if(null != (cookies = httpServletRequest.getCookies())){
            for(Cookie cookie:cookies){
                if(StringUtils.equals("token", cookie.getName())){
                    token = cookie.getValue();
                }
            }
        }

        if (Objects.isNull(token)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        // 解析token
        Map<String, Claim> claimMap = JwtUtils.verifyToken(token);
        if(null == claimMap){
            // claimMap 解析为空说明 token 已经过期或者 token 不正确
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        Claim username = claimMap.get("username");
        Claim realName = claimMap.get("realName");
        Claim phone = claimMap.get("phone");
        Claim email = claimMap.get("email");

        // 将Token可以解析出来的当前用户设置为 可访问
        CurrentUserVO currentUser = new CurrentUserVO(username.asString(), realName.asString(), null, phone.asString(), email.asString());
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(currentUser, null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
