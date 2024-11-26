/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/JerseyClient.java to edit this template
 */
package client;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:PostResource [posts]<br>
 * USAGE:
 * <pre>
 *        PostResource client = new PostResource();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author DELL
 */
public class PostResource {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8084/social-media/resources";

    public PostResource() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("posts");
    }

    public String helloFromPost() throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.TEXT_HTML).get(String.class);
    }

    public void deletePost(String pid, String uid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("deletepost/{0}/{1}", new Object[]{pid, uid})).request().delete();
    }

    public <T> T getPost(Class<T> responseType, String id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{id}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getPostsLikedByUser(Class<T> responseType, String uid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("likedbyuser/{0}", new Object[]{uid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void deleteComment(String cid, String uid, String pid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("deletecomment/{0}/{1}/{2}", new Object[]{cid, uid, pid})).request().delete();
    }

    public <T> T getAllCommentsOfPost(Class<T> responseType, String pid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("commentsofpost/{0}", new Object[]{pid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllLikesOfPost(Class<T> responseType, String pid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("likesofpost/{0}", new Object[]{pid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addPost(String media, String caption, String userId) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("create/{0}/{1}/{2}", new Object[]{media, caption, userId})).request().post(null);
    }

    public <T> T getAllPostsOfUser(Class<T> responseType, String uid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("allpostofuser/{0}", new Object[]{uid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void likeUnlikePost(String uid, String pid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("like/{0}/{1}", new Object[]{uid, pid})).request().post(null);
    }

    public <T> T getAllPosts(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("all");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addComment(String uid, String pid, String content) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("comment/{0}/{1}/{2}", new Object[]{uid, pid, content})).request().post(null);
    }

    public void close() {
        client.close();
    }
    
}
