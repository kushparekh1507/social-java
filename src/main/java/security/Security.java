/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package security;

import ejb.UserBeanLocal;
import entity.Users;
import helper.SecurityData;
import io.jsonwebtoken.ExpiredJwtException;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStoreHandler;
import jwt.TokenProvider;

/**
 *
 * @author DELL
 */
public class Security {
    @EJB
    UserBeanLocal ubl;
    
    @Inject
    IdentityStoreHandler handler;
    
    @Inject
    TokenProvider tokenProvider;
    
    public SecurityData validateUser(SecurityData securitydata) {

        Users user = securitydata.getUser();

        System.out.println("EJB " + user.getUsername());

        Credential credential = new UsernamePasswordCredential(user.getUsername(), new Password(user.getPassword()));
        CredentialValidationResult result = handler.validate(credential);
        System.out.println("result ejb " + result.getStatus());
        
        if (result.getStatus() == CredentialValidationResult.Status.VALID) {
            
            //generate token
            SecurityData data = tokenProvider.createToken(result.getCallerPrincipal().getName(), result.getCallerGroups(), securitydata.isRememberMe());
            
            System.out.println("Token: " + data.getToken());
            System.out.println("Validity: " + data.getCredentialValidity());
            
            Users validatedUser = ubl.getUserByUsername(result.getCallerPrincipal().getName());
            
            SecurityData security = new SecurityData(result.getStatus(), data.getToken(), validatedUser, data.getCredentialValidity());
            return security;
        }
        else{
            SecurityData security = new SecurityData(result.getStatus());
            return security;
        }
    }
    
    public SecurityData validateToken(SecurityData securityData) {

        try {
            if (tokenProvider.validateToken(securityData.getToken())) {
//                JWTCredential credential = tokenProvider.getCredential(securityData.getToken());
                System.out.println("In SecureAuthentication - In validateToken() method - Token Validated!!!");
                return new SecurityData(CredentialValidationResult.Status.VALID);
            }
            //token invalid
            return new SecurityData(CredentialValidationResult.Status.INVALID);
//            return context.responseUnauthorized();
        } catch (ExpiredJwtException ex) {
            return new SecurityData(CredentialValidationResult.Status.INVALID);
        }
    }
}
