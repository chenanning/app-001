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

    public static String generateToken(Long id) {
        Date issuedAt = new Date();
        Date expiresAt = new Date(issuedAt.getTime() + 24 * 60 * 60 * 1000); // 24 hours

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", id);

        return JWT.create()
                .withSubject("system")
                .withIssuer("app-001")
                .withAudience("app")
                .withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt)
                .withClaim("id", id)
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    public static Long verifyToken(String token) {
        try {
            DecodedJWT jwt = JWT.require(Algorithm.HMAC256(SECRET_KEY)).build().verify(token);
            Claim idClaim = jwt.getClaim("id");
            return idClaim.asLong();
        } catch (Exception e) {
            // 验证失败或过期
            return null;
        }
    }

    public static Long getIdFromToken(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            Claim idClaim = jwt.getClaim("id");
            return idClaim.asLong();
        } catch (JWTDecodeException e) {
            // 解码失败，token 格式错误
            return null;
        }
    }

}
