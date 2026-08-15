package com.leaning.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

/**
 * JWT工具类
 */
public class JwtUtils {

    // JWT签名密钥
    private static final String SIGN_KEY = "dGxpYXM=";
    // 令牌有效期 12小时，单位毫秒
    private static final long EXPIRE_TIME = 12 * 3600 * 1000L;

    /**
     * 生成JWT令牌
     * @param claims 自定义载荷数据
     * @return JWT字符串
     */
    public static String generateJwt(Map<String, Object> claims){
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, SIGN_KEY) // 指定HS256签名算法、秘钥
                .addClaims(claims) // 设置自定义载荷
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME)) // 设置过期时间12小时
                .compact();
        return jwt;
    }

    /**
     * 解析JWT令牌
     * @param jwt JWT令牌字符串
     * @return 载荷Claims对象
     */
    public static Claims parseJwt(String jwt){
        Claims claims = Jwts.parser()
                .setSigningKey(SIGN_KEY) // 设置签名秘钥，校验签名
                .parseClaimsJws(jwt)
                .getBody();
        return claims;
    }

}
