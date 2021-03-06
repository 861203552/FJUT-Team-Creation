package com.lsd.no01_fjut.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fangf on 2016/5/21.
 */
public class Auth {

    public static final Key key = MacProvider.generateKey();
    public static final long expire = 7200000; //2 hours
    /**
     * 
     * @author: xiaolong
     * @Description: 测试token
     * 
     * @return: void
     * @param ： @param args
     */
    public static void main (String [] args) {
        Map<String,Object> claims = new HashMap<String, Object>();
        System.out.println(key);
        claims.put("id",1);
        claims.put("name","zeven");
        claims.put("role","admin");
        String s = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, key).compact();
        System.out.println(s);
        Claims result;
        try {
            result = Jwts.parser().setSigningKey(key).parseClaimsJws(s).getBody();
            System.out.println(result.get("id")+"|"+result.get("name")+"|"+result.get("role"));
            String sign = Jwts.parser().setSigningKey(key).parseClaimsJws(s).getSignature();
            System.out.println(sign);
            JwsHeader jhead = Jwts.parser().setSigningKey(key).parseClaimsJws(s).getHeader();
            System.out.println(jhead.toString());
        } catch (SignatureException e){
            System.out.println("401 No Authentication");
        }
    }
}
