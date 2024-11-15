/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package rest;

import ejb.UserBeanLocal;
import entity.Users;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author DELL
 */
@Path("users")
@RequestScoped
public class UserResource {

    @EJB
    UserBeanLocal ubl;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UserResource
     */
    public UserResource() {
    }

    /**
     * Retrieves representation of an instance of
     * com.mycompany.social.media.resources.UserResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Default
    @Produces(MediaType.TEXT_HTML)
    public String helloFromUser() {
        return "<h1>Hello from User</h1>";
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Users> getAllUsers() {
        return ubl.getAllUsers();
    }

    @GET
    @Path("/id/{uid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Users getUserById(@PathParam("uid") Integer userId) {
        return ubl.getUserById(userId);
    }

    @GET
    @Path("/username/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Users> getUserByUsername(@PathParam("username") String username) {
        return ubl.getUserByUsername(username);
    }

    @POST
    @Path("/adduser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(Users user) {
        try {
            ubl.addUser(user);
            return Response.status(Response.Status.CREATED).entity("User created successfully").build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Error adding user: " + e.getMessage()).build();
        }
    }

    @PUT
    @Path("/updateuser/{uid}/{uname}/{fname}/{pwd}/{profilepic}")
    public void updateUser(@PathParam("uid") Integer userId, @PathParam("uname") String username, @PathParam("fname") String fullName,
            @PathParam("pwd") String password, @PathParam("profilepic") String profilePic) {
        ubl.updateUser(userId, username, fullName, password, profilePic);
    }

    @DELETE
    @Path("/delete/{uid}")
    public void removeUser(@PathParam("uid") Integer userId) {
        ubl.removeUser(userId);
    }

    @POST
    @Path("/follow/{followedid}/{followerid}")
    public void followUnfollowUser(@PathParam("followedid") Integer followedUserId, @PathParam("followerid") Integer followerUserId) {
        ubl.followUnfollowUser(followedUserId, followerUserId);
    }

    @GET
    @Path("/followersofuser/{uid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Users> getAllFollowersOfUser(@PathParam("uid") Integer userId) {
        return ubl.getAllFollowersOfUser(userId);
    }

    @GET
    @Path("/followingsofuser/{uid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Users> getAllFollowingsOfUser(@PathParam("uid") Integer userId) {
        return ubl.getAllFollowingsOfUser(userId);
    }
}
