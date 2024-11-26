/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/JerseyClient.java to edit this template
 */
package client;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:GroupResource [groups]<br>
 * USAGE:
 * <pre>
 *        GroupClient client = new GroupClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author DELL
 */
public class GroupClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8084/social-media/resources";

    public GroupClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("groups");
    }

    public <T> T getAllGroups(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("allgroups");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void createGroup(String name, String uid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("create/{0}/{1}", new Object[]{name, uid})).request().post(null);
    }

    public <T> T getAllGroupsCreatedByUser(Class<T> responseType, String uid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("createdby/{0}", new Object[]{uid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void deleteMemberFromGroup(String gid, String uid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("deletemember/{0}/{1}", new Object[]{gid, uid})).request().delete();
    }

    public void addMemberToGroup(String uid, String gid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addmember/{0}/{1}", new Object[]{uid, gid})).request().post(null);
    }

    public String helloFromGroup() throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.TEXT_HTML).get(String.class);
    }

    public <T> T getAllGroupsOfUser(Class<T> responseType, String uid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("groupsofuser/{0}", new Object[]{uid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllMembersOfGroup(Class<T> responseType, String gid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getmembers/{0}", new Object[]{gid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}
