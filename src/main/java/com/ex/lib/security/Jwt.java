package com.ex.lib.security;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author patrick
 * @date 05/03/2018
 * create time : 10:25 PM
 */

public class Jwt {


    private static final String DEFAULT_ISSUER = "ex";
    private static final String DEFAULT_SUBJECT = "auth";
    private static final String SECRET = "#hhjkhkh&jh2432@ndsf*_erkhwek234&ewhkjwehr^hfh234$2l3j4" +
            "o32urMiOiJ3aJpc3MiOiJ3aWF0ZWMiLCJzdWIiOiJsZWdhY3kiLCJuYmYiOjE1MjAyNjE5NTgsImJzdWIiOi" +
            "JsZWdhY3kiLCJuYmYiOjE1MjAyNjQ0MjksImV4cCI6MTUyMDM1MD2l3j4o3";


    /**
     * create jwt token
     * @return token
     */
    public static String encrypt() {
        return encrypt(DEFAULT_ISSUER, DEFAULT_SUBJECT, 1);
    }

    /**
     * encrypt jwt token
     */
    private static String encrypt(String issuer, String subject, Integer expiresDays) {
        try {
            if(expiresDays == null || expiresDays <= 0){
                expiresDays = 1;
            }
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_MONTH, expiresDays);
            Date newDate = calendar.getTime();
            return com.auth0.jwt.JWT.create()
                    .withIssuer(issuer)
                    .withIssuedAt(date)
                    .withSubject(subject)
                    .withNotBefore(date)
                    .withExpiresAt(newDate)
                    .sign(Algorithm.HMAC256(SECRET));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static Data decrypt(String token) {
        return decrypt(token, DEFAULT_ISSUER, DEFAULT_SUBJECT);
    }

    /**
     * decrypt
     */
    public static Data decrypt(String token, String issuer, String subject){
        try {
            JWTVerifier verifier = com.auth0.jwt.JWT
                    .require(Algorithm.HMAC256(SECRET))
                    .withIssuer(issuer)
                    .withSubject(subject)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            Data data = new Data();
            data.setIssuer(jwt.getIssuer());
            data.setSubject(jwt.getSubject());
            data.setIssuerAt(jwt.getIssuedAt());
            data.setExpiresAt(jwt.getExpiresAt());
            return data;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static class Data{
        private String issuer;
        private String subject;
        private Date issuerAt;
        private Date expiresAt;

        public String getIssuer() {
            return issuer;
        }

        public void setIssuer(String issuer) {
            this.issuer = issuer;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public Date getIssuerAt() {
            return issuerAt;
        }

        public void setIssuerAt(Date issuerAt) {
            this.issuerAt = issuerAt;
        }

        public Date getExpiresAt() {
            return expiresAt;
        }

        public void setExpiresAt(Date expiresAt) {
            this.expiresAt = expiresAt;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "issuer='" + issuer + '\'' +
                    ", subject='" + subject + '\'' +
                    ", issuerAt=" + issuerAt +
                    ", expiresAt=" + expiresAt +
                    '}';
        }
    }

}
