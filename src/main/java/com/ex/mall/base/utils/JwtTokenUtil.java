package com.ex.mall.base.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
* @Package: com.ex.mall.base.utils
* @ClassName: JwtTokenUtil
* @Description: util
 *              -- Jwt
* @Author: mbm
* @date: 2020/6/27 13:54
* @Version: 1.0
*/
@Component
public class JwtTokenUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);
    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATE = "created";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * @author mbm X
     * @methodname : generateExpirationDate
     * @description : Token过期时间
     * @return : java.util.Date
     * @date : 2020/6/27 13:59
     */
    public Date generateExpirationDate(){
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * @author mbm X
     * @methodname : generateToken
     * @description : 生成Token
     * @param claims :
     * @return : java.lang.String
     * @date : 2020/6/27 14:00
     */
    public String generateToken(Map<String,Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }

    /**
     * @author mbm X
     * @methodname : getClaimsFromToken
     * @description : 获取 Token 中的负载
     * @param token :
     * @return : io.jsonwebtoken.Claims
     * @date : 2020/6/27 14:02
     */
    private Claims getClaimsFromToken(String token){
        Claims claims = null;
        try{
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            logger.info("Jwt 格斯验证失败:{}",token);
        }
        return claims;
    }

    /**
     * @author mbm X
     * @methodname : getUsernameFromToken
     * @description : 从 Token 中获取用户名
     * @param token :
     * @return : java.lang.String
     * @date : 2020/6/27 14:05
     */
    public String getUsernameFromToken(String token){
        String username;
        try{
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e){
            username = null;
        }
        return username;
    }

    /**
     * @author mbm X
     * @methodname : getExpiredDateFtomToken
     * @description : 从 Token 中获取过期时间
     * @param token :
     * @return : java.util.Date
     * @date : 2020/6/27 14:08
     */
    private Date getExpiredDateFtomToken(String token){
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    /**
     * @author mbm X
     * @methodname : isTokenExpired
     * @description : 判断 Token 是否失效
     * @param token :
     * @return : java.lang.Boolean
     * @date : 2020/6/27 14:09
     */
    private Boolean isTokenExpired(String token){
        Date expiredDate = getExpiredDateFtomToken(token);
        return expiredDate.before(new Date());
    }


    /**
     * @author mbm X
     * @methodname : vaildateToken
     * @description : 判断 Tokens 是否过期
     * @param token :
     * @param userDetails :
     * @return : java.lang.Boolean
     * @date : 2020/6/27 14:10
     */
    public Boolean vaildateToken(String token,UserDetails userDetails){
        String username = getUsernameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * @author mbm X
     * @methodname : generateToken
     * @description : 根据用户信息生成 Token
     * @param userDetails :
     * @return : java.lang.String
     * @date : 2020/6/27 14:13
     */
    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME,userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATE,new Date());
        return generateToken(claims);
    }

    /**
     * @author mbm X
     * @methodname : canRefresh
     * @description : 判断 Token 是否可以刷新
     * @param token :
     * @return : boolean
     * @date : 2020/6/27 14:14
     */
    public boolean canRefresh(String token){
        return !isTokenExpired(token);
    }

    /**
     * @author mbm X
     * @methodname : refreshToken
     * @description : 刷新 Token
     * @param token :
     * @return : java.lang.String
     * @date : 2020/6/27 14:15
     */
    public String refreshToken(String token){
        Claims claims = getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATE,new Date());
        return generateToken(claims);
    }

}
