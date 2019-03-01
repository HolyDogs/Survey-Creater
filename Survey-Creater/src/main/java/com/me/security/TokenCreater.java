package com.me.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;

/**
 * @ClassName TokenCreater
 * @Description TODO
 * @Author xufeng
 * @Data 2019/3/1 10:26
 * @Version 1.0
 **/
public class TokenCreater {

    public static String createJWT(String id, String issuer, String subject, long ttlMillis){
        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("xufeng7788");
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(id)
	    	    .setIssuedAt(now)
	        	.setSubject(subject)
                .setIssuer(issuer)
		        .signWith(signatureAlgorithm, signingKey);

        //if it has been specified, let's add the expiration
        if (ttlMillis >= 0) {
		    long expMillis = nowMillis + ttlMillis;
		    Date exp = new Date(expMillis);
		    builder.setExpiration(exp);
        }

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    /**
     * 解密
     * @param jwt
     */
    public static HashMap parseJWT(String jwt) {
        //This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parser()
            .setSigningKey(DatatypeConverter.parseBase64Binary("xufeng7788"))
		    .parseClaimsJws(jwt).getBody();

        HashMap<String,String> map = new HashMap<>(16);
        System.out.println("ID: " + claims.getId());
        System.out.println("Subject: " + claims.getSubject());
        System.out.println("Issuer: " + claims.getIssuer());
        System.out.println("Expiration: " + claims.getExpiration());

        map.put("id",claims.getId());
        map.put("name",claims.getSubject());

        return map;
    }

}
