/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package rest;

import ejb.UserBeanLocal;
import entity.Conversations;
import entity.Messages;
import helper.MessageRequest;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author DELL
 */
@Path("conversation")
@RequestScoped
public class ConversationResource {

    @EJB
    UserBeanLocal ubl;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of MessageResource
     */
    public ConversationResource() {
    }

    /**
     * Retrieves representation of an instance of rest.MessageResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Default
    @Produces(MediaType.TEXT_HTML)
    public String helloFromConversation() {
        return "<h1>Hello from conversation</h1>";
    }

    @POST
    @Path("/sendmessage")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Messages sendMessage(MessageRequest mb) {
        return ubl.sendMessage(mb.getParticipantsIds(), mb.getGroupId(), mb.getSenderId(), mb.getText());
    }

    @GET
    @Path("/messagesofconv/{cid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Messages> getMessagesByConversation(@PathParam("cid") Integer conversationId) {
        return ubl.getMessagesByConversation(conversationId);
    }

    @GET
    @Path("/convofuser/{uid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Conversations> getAllConversationOfUser(@PathParam("uid") Integer userId) {
        return ubl.getAllConversationOfUser(userId);
    }

    @GET
    @Path("/{cid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Conversations getConversationById(@PathParam("cid") Integer conversationId) {
        return ubl.getConversationById(conversationId);
    }

}
