package com.mycompany.social.media.resources;

import javax.enterprise.inject.Default;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author 
 */
@Path("rest")
public class JakartaEE8Resource {
    
    @GET
    @Default
    public String sayHello(){
        return "Hello world";
    }
}
