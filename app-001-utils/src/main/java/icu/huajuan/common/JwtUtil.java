package icu.huajuan.common;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    private static final String SECRET_KEY = "MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjYyN2I0ZjY"; // 修改为你自己的密钥

    /**
     * 生成JWT令牌
     *
     * @param id 用户ID
     * @return JWT令牌
     */
    public static String generateToken(Long id) {
        // 设置令牌的签发时间和过期时间
        Date issuedAt = new Date();
        Date expiresAt = new Date(issuedAt.getTime() + 24 * 60 * 60 * 1000); // 24小时后过期

        // 设置令牌的声明，可以存储用户ID等相关信息
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", id);

        // 使用密钥对令牌进行签名并返回
        return JWT.create()
                .withSubject("system") // 设置主题
                .withIssuer("app-001") // 设置签发者
                .withAudience("app") // 设置接收者
                .withIssuedAt(issuedAt) // 设置签发时间
                .withExpiresAt(expiresAt) // 设置过期时间
                .withClaim("id", id) // 设置自定义声明
                .sign(Algorithm.HMAC256(SECRET_KEY)); // 使用HMAC256算法和密钥进行签名
    }

    /**
     * 验证JWT令牌，并返回用户ID
     *
     * @param token JWT令牌
     * @return 用户ID，验证失败或过期时返回null
     */
    public static Long verifyToken(String token) {
        try {
            // 验证令牌的签名，并获取用户ID
            DecodedJWT jwt = JWT.require(Algorithm.HMAC256(SECRET_KEY)).build().verify(token);
            Claim idClaim = jwt.getClaim("id");
            return idClaim.asLong();
        } catch (Exception e) {
            // 验证失败或过期
            return null;
        }
    }

    /**
     * 从JWT令牌中解析并返回用户ID
     *
     * @param token JWT令牌
     * @return 用户ID，解码失败或令牌格式错误时返回null
     */
    public static Long getIdFromToken(String token) {
        try {
            // 解码令牌，并获取用户ID
            DecodedJWT jwt = JWT.decode(token);
            Claim idClaim = jwt.getClaim("id");
            return idClaim.asLong();
        } catch (JWTDecodeException e) {
            // 解码失败，token 格式错误
            return null;
        }
    }

    public static void main(String[] args) {
        String token = generateToken(1L);
        System.out.println(token);
        Long id = verifyToken(token);
        System.out.println(id);
        id = getIdFromToken(token);
        System.out.println(id);
    }

}
