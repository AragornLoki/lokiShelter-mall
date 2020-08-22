package tk.lokiShelter.manage.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTokenUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);
    /**
     * 根据负载生成JWT的token
     */
    public static String generateToken(Map<String, Object> claims) {

        return Jwts.builder()
                .setClaims(claims)
                .setSubject("shelter")
                .setIssuedAt(new Date(System.currentTimeMillis()))// 签发时间
                .setNotBefore(new Date(System.currentTimeMillis())) // 生效时间
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))
                .signWith(SignatureAlgorithm.HS512, "lokishelter")
                .compact();
    }

    /**
     * 根据用户信息生成token
     */
    public static String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("u", username);
        return generateToken(claims);
    }

    /**
     * 从token中获取JWT中的负载
     */
    private static Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey("lokishelter")
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            LOGGER.info("JWT格式验证失败:{}", token);
        }
        return claims;
    }

    /**
     * 从token中获取登录用户名
     */
    public static String getUserNameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = (String)claims.get("u");
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 判断token是否已经失效
     */
    private static boolean isTokenExpired(String token) {
        Claims claims = getClaimsFromToken(token);
        Date expiredDate=claims.getExpiration();
        return expiredDate.before(new Date());
    }

    /**
     * 验证token是否还有效
     *
     * @param token       客户端传入的token
     * @param userDetails 从数据库中查询出来的用户信息
     */
    public static boolean validateToken(String token, UserDetails userDetails) {

        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = (String)claims.get("u");
        } catch (Exception e) {
            username = null;
        }
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }
}

