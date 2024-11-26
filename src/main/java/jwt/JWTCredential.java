/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jwt;

import java.io.Serializable;
import java.util.Set;
import javax.security.enterprise.credential.Credential;

/**
 *
 * @author DELL
 */
public class JWTCredential implements Credential, Serializable {
    
    private final String Principal;
    private final Set<String> authorities;

    public JWTCredential(String Principal, Set<String> authorities) {
        this.Principal = Principal;
        this.authorities = authorities;
    }

    public String getPrincipal() {
        return Principal;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }
}
