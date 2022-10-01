package com.base.common.util.audit;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.Claim;
import com.base.basic.domain.entity.v1.AuditLog;
import com.base.basic.infra.mapper.AuditLogMapper;
import com.base.common.util.jwt.JwtUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

/**
 * 接口审计
 * @author gaoyang
 * @Before：通知方法会在目标方法调用之前执行
 * @After：通知方法会在目标方法调用后执行
 * @AfterReturning：通知方法会在目标方法返回后执行
 * @AfterThrowing：通知方法会在目标方法抛出异常后执行
 * @Around：把整个目标方法包裹起来，在被调用前和调用之后分别执行通知方法
 */
@Aspect
@Component
public class ApiAuditAspect {

    private Logger logger = LoggerFactory.getLogger(ApiAuditAspect.class);

    @Autowired
    @SuppressWarnings("all")
    private AuditLogMapper auditLogMapper;

    @Around("@annotation(org.springframework.web.bind.annotation.GetMapping) ||" +
            "@annotation(org.springframework.web.bind.annotation.PostMapping) ||" +
            "@annotation(org.springframework.web.bind.annotation.PutMapping) ||" +
            "@annotation(org.springframework.web.bind.annotation.DeleteMapping) ||" +
            "@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public Object doAround(ProceedingJoinPoint point) throws Throwable {
        /**
         * 方法执行前逻辑
         */
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

        String className = point.getTarget().getClass().getName();
        logger.info("获取类名：{}", className);

        String methodName = point.getSignature().getName();
        logger.info("获取执行的方法名称：{}", methodName);

        String url = request.getRequestURL().toString();
        logger.info("请求地址：{}", url);

        String method = request.getMethod();
        logger.info("请求类型：{}", method);

        String queryString = request.getQueryString();
        logger.info("请求参数:{}", queryString);

        String body = new RequestWrapper(request).getBody();
        logger.info("请求体：{}", body);

        String remoteAddr = request.getRemoteAddr();
        logger.info("远程请求地址：{}", remoteAddr);

        int remotePort = request.getRemotePort();
        logger.info("远程请求端口：{}", remotePort);

        // 从Cookie中取出 token 这里需要和前端约定好把jwt放到请求头一个叫token的地方
        String token = null;
        Cookie[] cookies = null;
        if(null != (cookies = request.getCookies())){
            for(Cookie cookie:cookies){
                if(StringUtils.equals("token", cookie.getName())){
                    token = cookie.getValue();
                    break;
                }
            }
        }
        String username = "Public";
        if (Objects.nonNull(token)) {
            // 解析token
            Map<String, Claim> claimMap = JwtUtils.verifyToken(token);
            if(Objects.nonNull(claimMap)){
            }
            username = claimMap.get("username").asString();
        }
        logger.info("用户名：{}", username);

        /**
         * 执行方法
         */
        Object result = point.proceed();

        /**
         * 方法执行后逻辑
         */
        AuditLog auditLog = new AuditLog();
        auditLog.setUrl(StringUtils.isNotEmpty(queryString) ? new StringBuilder(url).append("?").append(queryString).toString() : url);
        auditLog.setMethod(method);
        auditLog.setRequestBody(body);
        auditLog.setRemoteAddr(remoteAddr);
        auditLog.setRemotePort(new Long(remotePort));
        auditLog.setStatus(new Long(response.getStatus()));
        auditLog.setResponseBody(JSONObject.toJSONString(result));
        auditLog.setLoginName(username);
        auditLogMapper.insertSelective(auditLog);

        return result;
    }
}
