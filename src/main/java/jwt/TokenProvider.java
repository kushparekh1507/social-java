/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jwt;

import helper.SecurityData;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.joining;
import javax.annotation.PostConstruct;

/**
 *
 * @author DELL
 */
public class TokenProvider implements Serializable {
    
    private String secret_key;
    private long tokenvalidity;
    private long tokenValidity_rememberMe;
    
    @PostConstruct
    public void init() {
        this.secret_key = "my-secret-jwt-key";
        this.tokenvalidity = TimeUnit.HOURS.toMillis(Constants.TOKEN_VALIDITY_HOURS);
        this.tokenValidity_rememberMe = TimeUnit.SECONDS.toMillis(Constants.REMEMBERME_VALIDITY_SECONDS);
    }
    
    public SecurityData createToken(String username, Set<String> authorities, Boolean rememberme) {
        
        long now = new Date().getTime();
        long token_validity = rememberme ? tokenValidity_rememberMe : tokenvalidity;
        
        System.out.println("TokenProvider - In createToken() method!!!");
        
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuer("localhost")
                .claim(Constants.AUTHORITIES_KEY, authorities.stream().collect(joining(",")))
                .signWith(SignatureAlgorithm.HS512, secret_key)
                .setExpiration(new Date(now + token_validity))
                .compact();
        return new SecurityData(token, token_validity);
        
    }
    
    public JWTCredential getCredential(String token) {
        
        Claims claims = Jwts.parser()
                .setSigningKey(secret_key)
                .parseClaimsJws(token)
                .getBody();
        
        System.out.println("TokenProvider - In getCredential() method!!!");
        
        Set<String> authorities = Arrays.asList(claims.get(Constants.AUTHORITIES_KEY).toString().split(","))
                .stream().collect(Collectors.toSet());
        
        return new JWTCredential(claims.getSubject(), authorities);
                
    }
    
    public boolean validateToken(String token) {
        
        try{
            System.out.println("In TokenProvider - In validateToken() method!!!");
            
            Jwts.parser().setSigningKey(secret_key).parseClaimsJws(token);
            return true;
        }
        catch(SignatureException ex) {
            return false;
        }
    }
    
}