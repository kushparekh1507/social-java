/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package rest;

import entity.Users;
import helper.Response;
import helper.SecurityData;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import security.Security;

/**
 * REST Web Service
 *
 * @author DELL
 */
@Path("auth")
@RequestScoped
public class AuthResource {

    @Inject
    Security security;

    /**
     * Creates a new instance of AuthResource
     */
    public AuthResource() {
    }

    /**
     * Retrieves representation of an instance of rest.AuthResource
     * @param securityData
     * @return an instance of java.lang.String
     */
    @POST
    @Path("validateuser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response<SecurityData> validateUser(SecurityData securityData){
        Response response=new Response();
        
        try {
            Users user=securityData.getUser();
            
            System.out.println("security rest : " + user.getUsername() + " " + user.getPassword());

            SecurityData resSecurity = security.validateUser(securityData);

            response.setResult(resSecurity);
            
            if (resSecurity.getStatus() == CredentialValidationResult.Status.VALID) {
                response.setMessage("User validated successfully!");
                response.setStatus(true);
            } else if (resSecurity.getStatus() == CredentialValidationResult.Status.INVALID) {
                response.setMessage("User is not validated!");
                response.setStatus(false);
            }

            System.out.println("result rest : " + response.getResult().toString());
        } catch (Exception e) {
            response.setResult(e);
            response.setMessage("Failed while validating user!");
            response.setStatus(false);
        }
        return response;
    }
    
    @POST
    @Path("validateToken")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response<SecurityData> validateToken(SecurityData requestbody) {
        Response response = new Response();
        try {
//            SecurityData data = requestbody.getData();

            SecurityData resSecurityData = security.validateToken(requestbody);

            if (resSecurityData.getStatus() == CredentialValidationResult.Status.VALID) {
                response.setMessage("Token validated successfully!");
                response.setStatus(true);
            } else if (resSecurityData.getStatus() == CredentialValidationResult.Status.INVALID) {
                response.setMessage("Token is invalid or token is expired!");
                response.setStatus(false);
            }
        } catch (Exception e) {
            response.setResult(e);
            response.setMessage("Failed while validating token!");
            response.setStatus(false);
        }
        return response;
    }
}
