/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package rest;

import ejb.UserBeanLocal;
import entity.Groups;
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
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author DELL
 */
@Path("groups")
@RequestScoped
public class GroupResource {

    @EJB
    UserBeanLocal ubl;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GroupResource
     */
    public GroupResource() {
    }

    /**
     * Retrieves representation of an instance of rest.GroupResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Default
    @Produces(MediaType.TEXT_HTML)
    public String helloFromGroup() {
        return "<h1>Hello from groups</h1>";
    }
    
    @GET
    @Path("/allgroups")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Groups> getAllGroups() {
        return ubl.getAllGroups();
    }
    
    @GET
    @Path("/getmembers/{gid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Users> getAllMembersOfGroup(@PathParam("gid") Integer groupId) {
        return ubl.getAllMembersOfGroup(groupId);
    }
    
    @GET
    @Path("/groupsofuser/{uid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Groups> getAllGroupsOfUser(@PathParam("uid") Integer userId) {
        return ubl.getAllGroupsOfUser(userId);
    }
    
    @GET
    @Path("/createdby/{uid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Groups> getAllGroupsCreatedByUser(@PathParam("uid") Integer userId) {
        return ubl.getAllGroupsCreatedByUser(userId);
    }
    
    @POST
    @Path("/create/{name}/{uid}")
    public void createGroup(@PathParam("name") String groupName, @PathParam("uid") Integer userId) {
        ubl.createGroup(groupName, userId);
    }

    @POST
    @Path("/addmember/{uid}/{gid}")
    public void addMemberToGroup(@PathParam("uid") Integer userId, @PathParam("gid") Integer groupId) {
        ubl.addMemberToGroup(userId, groupId);
    }

    @DELETE
    @Path("/deletemember/{gid}/{uid}")
    public void deleteMemberFromGroup(@PathParam("gid") Integer groupId,@PathParam("uid") Integer userId) {
        ubl.deleteMemberFromGroup(groupId, userId);
    }
}
