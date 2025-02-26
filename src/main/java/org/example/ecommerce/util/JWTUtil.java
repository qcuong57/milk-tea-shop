//package org.example.ecommerce.util;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.exceptions.JWTVerificationException;
//import com.auth0.jwt.interfaces.DecodedJWT;
//import com.auth0.jwt.interfaces.JWTVerifier;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
//@Component
//public class JWTUtil {
//    private static final String SECRET_KEY = "your_secret_key_your_secret_key"; // Ít nhất 32 ký tự
//    private static final long EXPIRATION_TIME = 86400000; // 1 ngày
//
//    private final Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
//    private final JWTVerifier verifier = JWT.require(algorithm).build();
//
//    public String generateToken(String username) {
//        return JWT.create()
//                .withSubject(username)
//                .withIssuedAt(new Date())
//                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//                .sign(algorithm);
//    }
//
//    public String extractUsername(String token) {
//        DecodedJWT jwt = verifier.verify(token);
//        return jwt.getSubject();
//    }
//
//    public boolean validateToken(String token) {
//        try {
//            verifier.verify(token);
//            return true;
//        } catch (JWTVerificationException e) {
//            return false;
//        }
//    }
//}
