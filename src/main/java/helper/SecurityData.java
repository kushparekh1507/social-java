/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helper;

import entity.Users;
import java.io.Serializable;
import javax.security.enterprise.identitystore.CredentialValidationResult;

/**
 *
 * @author DELL
 */
public class SecurityData implements Serializable {
    
    private CredentialValidationResult.Status status;
    private String token;
    private Users user;
    private long credentialValidity;
    private boolean rememberMe;
    
    public SecurityData() {
    }

    public SecurityData(CredentialValidationResult.Status status, String token, Users user, long credentialValidity) {
        this.status = status;
        this.token = token;
        this.user = user;
        this.credentialValidity = credentialValidity;
    }

    public SecurityData(String token, long credentialValidity) {
        this.token = token;
        this.credentialValidity = credentialValidity;
    }

    public SecurityData(CredentialValidationResult.Status status) {
        this.status = status;
    }

    public CredentialValidationResult.Status getStatus() {
        return status;
    }

    public void setStatus(CredentialValidationResult.Status status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public long getCredentialValidity() {
        return credentialValidity;
    }

    public void setCredentialValidity(long credentialValidity) {
        this.credentialValidity = credentialValidity;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
}
