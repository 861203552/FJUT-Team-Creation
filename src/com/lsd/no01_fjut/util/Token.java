package com.lsd.no01_fjut.util;

import java.util.Date;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by fangf on 2016/5/22.
 */
public class Token {

    private int userId;
    private String userName;
    private String passwords;
    private String role;
    private int err = 0;
    public static final int ExpiredJwtError = 1;
    public static final int SignatureError = 2;
    
    public static String jsonBuilder(Map<String, Object> claims){
    	String s = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, Auth.key).setExpiration(new Date(System.currentTimeMillis()+Auth.expire)).compact();
    	return s;
    }
    public Token(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(Auth.key).parseClaimsJws(token).getBody();
            System.out.println(claims.toString());
            //this.id = Integer.parseInt(claims.get("id").toString());
            //this.name = claims.get("name").toString();
            //this.role = claims.get("role").toString();
            System.out.println("================验证成功=================");
        } catch (ExpiredJwtException e){
            this.err = ExpiredJwtError;
            System.out.println("===========超时============");
        } catch (SignatureException e) {
            this.err = SignatureError;
            System.out.println("================秘钥错误============");
        }
    }

    

    public int getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public String getPasswords() {
		return passwords;
	}

	public String getRole() {
		return role;
	}

	public int getErr() {
        return err;
    }

}
