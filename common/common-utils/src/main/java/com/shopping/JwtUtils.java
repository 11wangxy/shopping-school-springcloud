package com.shopping;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;
import io.jsonwebtoken.*;
import java.util.Date;
import java.util.Map;

/**
 *  generateJwt()方法用于生成JWT令牌，而parseJWT()方法用于解析JWT令牌。
 *  在这段代码中，signKey和expire是类变量，分别表示JWT签名密钥和过期时间。
 *  generateJwt()方法使用Jwts.builder()方法创建一个JWT生成器，并使用addClaims()方法添加声明（claims），
 *  使用signWith()方法设置签名算法和签名密钥，使用setExpiration()方法设置过期时间，
 *  最后使用compact()方法生成JWT字符串并返回。parseJWT()方法使用Jwts.parser()方法创建一个JWT解析器，
 *  并使用setSigningKey()方法设置签名密钥，使用parseClaimsJws()方法解析JWT字符串并返回一个Jws对象，
 *  最后使用getBody()方法获取Jws对象中的负载（payload），即声明（claims）对象。
 */
public class JwtUtils {

    private static String signKey = "wangxixaoyi";
    private static Long expire = 43200000L;

    /**
     * 生成JWT令牌
     * @param claims JWT第二部分负载 payload 中存储的内容
     * @return
     */
    public static String generateJwt(Map<String, Object> claims){
        String jwt = Jwts.builder()
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS256, signKey)
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .compact();
        return jwt;
    }

    /**
     * 解析JWT令牌
     * @param jwt JWT令牌
     * @return JWT第二部分负载 payload 中存储的内容
     */
    public static Claims parseJWT(String jwt){
        Claims claims = Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJws(jwt)
                .getBody();
        return claims;
    }

    public static String createToken(Long userId, String userName) {
        String token = Jwts.builder()
                .setSubject("USER")
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .claim("userId", userId)
                .claim("userName", userName)
                .signWith(SignatureAlgorithm.HS512, signKey)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
        return token;
    }

    public static Long getUserId(String token) {
        if(StringUtils.isEmpty(token)) return null;
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(signKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        Integer userId = (Integer)claims.get("userId");
        return userId.longValue();
        // return 1L;
    }

    public static String getUserName(String token) {
        if(StringUtils.isEmpty(token)) return "";
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(signKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return (String)claims.get("userName");
    }
}
