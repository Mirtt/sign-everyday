package com.mirt.sign.util;

import com.mirt.sign.model.User;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * 生成JWT的工具类
 *
 * @author Mirt Zhang
 * @date 2018/9/3
 */
@Component
public class JwtUtil {

    @Value("${jwt.algorithm}")
    public String alg;

    private static String algS;

    @Value("${jwt.secret}")
    public String secret;

    private static String secretS;

    @Value("${jwt.expires}")
    public Long expires;

    private static Long expiresS;

    private JwtUtil() {
    }

    @PostConstruct
    public void init() {
        algS = alg;
        secretS = secret;
        expiresS = expires;
    }

    /**
     * - iss：该JWT的签发者
     * - sub: 该JWT所面向的用户
     * - aud: 接收该JWT的一方
     * - exp(expires): 什么时候过期，这里是一个Unix时间戳
     * - iat(issued at): 在什么时候签发的
     */
    public static String createJwt(User user) {
        SignatureAlgorithm algorithm = SignatureAlgorithm.forName(algS);

        //生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretS);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, algorithm.getJcaName());

        JwtBuilder builder = Jwts.builder();
        builder.setHeaderParam("typ", "JWT");
        builder.claim("user-email", user.getEmail());
        builder.setIssuer("Mirt");
        builder.setAudience(user.getEmail());
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expireTime = now.plusSeconds(expiresS);
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = expireTime.atZone(zoneId);
        Date date = Date.from(zdt.toInstant());
        builder.setExpiration(date);
        builder.setExpiration(new Date());
        builder.signWith(algorithm, signingKey);

        return builder.compact();
    }
}
