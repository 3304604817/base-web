package com.base.common.util.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: Jwt工具类，生成JWT和认证
 */
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    // 密钥
    private static final String SECRET = "secret";

    // 过期时间，单位为秒
    private static final long EXPIRATION = 3600L;

    /**
     * 生成用户token,设置token超时时间
     */
    public static String createToken(String username) {
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token = JWT.create()
                // 添加头部
                .withHeader(map)
                // 可以将基本信息放到claims中
                .withClaim("username", username)
                // token 过期时间
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION * 1000))
                // 签发时间
                .withIssuedAt(new Date())
                // SECRET加密，以 SECRET 作为密钥
                .sign(Algorithm.HMAC256(SECRET));
        return token;
    }

    /**
     * 校验token并解析token
     */
    public static Map<String, Claim> verifyToken(String token) {
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error("token解码异常");
            //解码异常则抛出异常
            return null;
        }
        return jwt.getClaims();
    }
}
