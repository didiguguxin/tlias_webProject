package com.leaning;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTTest {

    // 生成JWT令牌测试方法
    @Test
    public void testGenJwt() {
        // 创建map集合，存放自定义的载荷数据
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 10);         // 用户id
        claims.put("username", "tlias"); // 用户名

        // 构建JWT令牌
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "dGxpYXM=") // 指定签名算法HS256，设置签名密钥
                .addClaims(claims)                               // 添加自定义载荷
                .setExpiration(new Date(System.currentTimeMillis() + 12 * 3600 * 1000)) // 设置过期时间：12小时后过期
                .compact();                                      // 生成压缩后的jwt字符串

        // 打印输出生成的JWT令牌
        System.out.println(jwt);
    }

    // 解析JWT令牌测试方法
    @Test
    public void testParseJwt() {
        // 解析jwt，获取载荷Claims对象
        Claims claims = Jwts.parser()
                .setSigningKey("dGxpYXM=")  // 设置签名密钥，必须和生成时密钥一致，用于校验签名
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MTAsInVzZXJuYW1lIjoidGxpYXMiLCJleHAiOjE3ODY1Njc5MzJ9.5kZMh4Uhx9BdNSilrzjj6QEzxDdAaBduESygY48tOUc") // 传入jwt令牌解析
                .getBody(); // 获取jwt中的载荷部分

        // 打印解析出来的载荷数据
        System.out.println(claims);
    }
}
