/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package rest;

import ejb.UserBeanLocal;
import entity.Comments;
import entity.Posts;
import entity.Users;
import helper.Response;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author DELL
 */
@Path("posts")
@RequestScoped
public class PostResource {

    @EJB
    UserBeanLocal ubl;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PostResource
     */
    public PostResource() {
    }

    /**
     * Retrieves representation of an instance of
     * com.mycompany.social.media.resources.PostResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Default
    @Produces(MediaType.TEXT_HTML)
    public String helloFromPost() {
        return "<h1>Hello from Post</h1>";
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response<Collection<Posts>> getAllPosts() {
        Response<Collection<Posts>> response = new Response();
        try {
            // Fetch all posts from the business layer
            Collection<Posts> allPosts = ubl.getAllPosts();

            // Set the data, status, and message
            response.setResult(allPosts);
            response.setMessage("Posts fetched1  successfully!");
            response.setStatus(true);
        } catch (Exception e) {
            response.setMessage("Failed fetching posts!");
            response.setStatus(false);
            response.setResult(null);
        }
        return response;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Posts getPost(@PathParam("id") Integer postId) {
        return ubl.getPost(postId);
    }

    @GET
    @Path("/allpostofuser/{uid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response<Collection<Posts>> getAllPostsOfUser(@PathParam("uid") Integer userId) {
        Response<Collection<Posts>> response = new Response();
        try {
            // Fetch all posts from the business layer
            Collection<Posts> allPosts = ubl.getAllPostsOfUser(userId);

            // Set the data, status, and message
            response.setResult(allPosts);
            response.setMessage("Posts fetched1  successfully!");
            response.setStatus(true);
        } catch (Exception e) {
            response.setMessage("Failed fetching posts!");
            response.setStatus(false);
            response.setResult(null);
        }
        return response;
    }

    @POST
    @Path("/create/{media}/{caption}/{userId}")
    public void addPost(@PathParam("media") String mediaUrl, @PathParam("caption") String caption, @PathParam("userId") Integer userId) {
        ubl.addPost(mediaUrl, caption, userId);
    }

    @DELETE
    @Path("/deletepost/{pid}/{uid}")
    public void deletePost(@PathParam("pid") Integer postId, @PathParam("uid") Integer userId) {
        ubl.deletePost(postId, userId);
    }

    @POST
    @Path("/like/{uid}/{pid}")
    public void likeUnlikePost(@PathParam("uid") Integer userId, @PathParam("pid") Integer postId) {
        ubl.likeUnlikePost(userId, postId);
    }

    @GET
    @Path("/likesofpost/{pid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Users> getAllLikesOfPost(@PathParam("pid") Integer postId) {
        return ubl.getAllLikesOfPost(postId);
    }

    @GET
    @Path("/likedbyuser/{uid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response<Collection<Posts>> getPostsLikedByUser(@PathParam("uid") Integer userId) {
        Response response = new Response();
        try {
            response.setResult(ubl.getPostsLikedByUser(userId));
            response.setMessage("Posts fetched successfully!");
            response.setStatus(true);
        } catch (Exception e) {
            response.setMessage("Failed fetching posts!");
            response.setStatus(false);
            response.setResult(e);
        }
        return response;
    }

    @POST
    @Path("/comment/{uid}/{pid}/{content}")
    public void addComment(@PathParam("uid") Integer userId, @PathParam("pid") Integer postId, @PathParam("content") String content) {
        ubl.addComment(userId, postId, content);
    }

    @DELETE
    @Path("/deletecomment/{cid}/{uid}/{pid}")
    public void deleteComment(@PathParam("cid") Integer commentId, @PathParam("uid") Integer userId,
            @PathParam("pid") Integer postId) {
        ubl.deleteComment(commentId, userId, postId);
    }

    @GET
    @Path("/commentsofpost/{pid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Comments> getAllCommentsOfPost(@PathParam("pid") Integer postId) {
        return ubl.getAllCommentsOfPost(postId);
    }

}
