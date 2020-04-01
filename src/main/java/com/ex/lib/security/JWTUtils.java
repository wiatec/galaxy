package com.ex.lib.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author patrick
 * @date 05/03/2018
 * create time : 10:25 PM
 */

public class JWTUtils {


    private static final String DEFAULT_ISSUER = "wiatec";
    private static final String DEFAULT_SUBJECT = "auth";
    private static final String SECRET = "#hhjkhkh&jh2432@ndsf*_erkhwek234&ewhkjwehr^hfh234$2l3j4" +
            "o32urMiOiJ3aJpc3MiOiJ3aWF0ZWMiLCJzdWIiOiJsZWdhY3kiLCJuYmYiOjE1MjAyNjE5NTgsImJzdWIiOi" +
            "JsZWdhY3kiLCJuYmYiOjE1MjAyNjQ0MjksImV4cCI6MTUyMDM1MD2l3j4o3";

    private static Algorithm algorithm;
    private static JWTVerifier verifier;

    static {
        try {
            algorithm = Algorithm.HMAC256(SECRET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        verifier = JWT.require(algorithm)
                .withIssuer(DEFAULT_ISSUER)
                .withSubject(DEFAULT_SUBJECT)
                .build();
    }


    /**
     * create jwt token
     * @return token
     */
    public static String create() {
        try {
            return encode(DEFAULT_ISSUER, DEFAULT_SUBJECT, 1);
        } catch (UnsupportedEncodingException e) {
            //TODO
        }
        return "";
    }

    /**
     * encode jwt token
     * @return token
     * @throws UnsupportedEncodingException UnsupportedEncodingException
     */
    private static String encode(String issuer, String subject, Integer expiresDays) throws UnsupportedEncodingException {
        if(expiresDays == null || expiresDays <= 0){
            expiresDays = 1;
        }
        if(StringUtils.isEmpty(issuer)){
            issuer = DEFAULT_ISSUER;
        }
        if(StringUtils.isEmpty(subject)){
            issuer = DEFAULT_SUBJECT;
        }
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, expiresDays);
        Date newDate = calendar.getTime();
        return JWT.create()
                .withIssuer(issuer)
                .withIssuedAt(date)
                .withSubject(subject)
                .withNotBefore(date)
                .withExpiresAt(newDate)
                .sign(algorithm);
    }

    /**
     * decode
     * @param token token
     * @return DecodedJWT
     */
    private static DecodedJWT decode(String token){
        DecodedJWT jwt;
        try {
            jwt = verifier.verify(token);
        }catch (Exception e){
            //TODO
            jwt = null;
        }
        return jwt;
    }

    /**
     * validate jwt token expiration
     * @param token token
     * @return is out expires
     */
    public static boolean verify(String token){
        DecodedJWT jwt = decode(token);
        Date expiresAt = jwt.getExpiresAt();
        if(!expiresAt.after(new Date())){
            //TODO
        }
        return true;
    }

    /**
     * main test method
     */
    public static void main (String [] args) {
        String token = create();
        System.out.println(token);
        DecodedJWT jwt = decode("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ3aWF0ZWMiLCJpYXQiOjE1MzM1MjEyNDcsInN1YiI6ImF1dGgiLCJuYmYiOjE1MzM1MjEyNDcsImV4cCI6MTUzMzYwNzY0N30.jhHa5wZeQZ0UYlv4eeiCh8ZyRygQHf0wjnIJk4JXjOA");
        System.out.println(jwt.getIssuer());
        System.out.println(jwt.getSubject());
        System.out.println(jwt.getIssuedAt());
        System.out.println(jwt.getExpiresAt());
        boolean b = verify(token);
        System.out.println(b);
    }

}
