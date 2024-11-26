/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import client.UserClient;
import ejb.UserBeanLocal;
import entity.Groupmaster;
import entity.Messages;
import entity.Posts;
import entity.Users;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DELL
 */
@WebServlet(name = "TestServlet", urlPatterns = {"/TestServlet"})
public class TestServlet extends HttpServlet {

    @EJB
    UserBeanLocal ubl;

    UserClient ul;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TestServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>");

//            ul = new UserClient();
//            Groupmaster gm=new Groupmaster();
//            gm.setGroupmasterId(2);
//            Users user = new Users();
//            user.setUsername("kush123");
//            user.setFullName("kush123");
//            user.setPassword("kush123");
//            user.setProfilePic("kush123.jpg");
//            user.setGroupmasterId(gm);
////
//            ubl.addUser(user);
//            ubl.addComment(3, 2, "comment");
//            Collection<Users> usersOfRole = ubl.getUsersByRole(2);
//            for (Users u : usersOfRole) {
//                out.println("<br/>");
//                out.println("user name:" + u.getUsername() + " --user role:" + u.getGroupmasterId().getGroupName());
//            }

//            ubl.createGroup("Kush", 1);
//            ubl.addMemberToGroup(1, 1);
//new conversation
//            List<Integer> participantsIds = new ArrayList<>();
//            participantsIds.add(1);
//            participantsIds.add(3);
//            ubl.sendMessage(participantsIds, null, 3, "How about you?.");
//            ubl.followUnfollowUser(1, 3);
//            Collection<Messages> messages = ubl.getMessagesByConversation(2);
//            for (Messages m : messages) {
//                out.println("<br/>");
//                out.println("Id:" + m.getMessageId() + " ---Conversation id:" + m.getConversationId().getConversationId() + "  -----sender name:"
//                        + m.getSenderId().getUsername() + " -------message:" + m.getContent());
//            }
//            out.println("<hr/>");
//
//            Collection<Users> followersOf1 = ubl.getAllFollowersOfUser(1);
//            for (Users u : followersOf1) {
//                out.println("<br/>");
//                out.println("Id:" + u.getUserId() + " --- Username:" + u.getUsername() + " --- Fullname:" + u.getFullName());
//            }
//
//            out.println("<br/>");
//
//            Collection<Users> followingsOf3 = ubl.getAllFollowingsOfUser(3);
//            for (Users u : followingsOf3) {
//                out.println("<br/>");
//                out.println("Id:" + u.getUserId() + " --- Username:" + u.getUsername() + " --- Fullname:" + u.getFullName());
//            }
//
//            out.println("<br/>");
////            ubl.addUser("shobhit12", "SHobhit Damwala", "shobhit12", "shobhit-profile.jpg");
////            ubl.removeUser(2);
//            Collection<Users> users = ubl.getAllUsers();
//            out.println("All Users");
//            for (Users u : users) {
//                out.println("<br/>");
//                out.println("Id:" + u.getUserId() + " --- Username:" + u.getUsername() + " --- Fullname:" + u.getFullName());
//            }
//
//            Users u = ubl.getUserById(1);
//
//            out.println("<br/>");
//            out.println("<br/>");
//            out.println("Get user by User Id");
//
//            out.println("<br/>");
//            out.println("Id:" + u.getUserId() + " --- Username:" + u.getUsername() + " --- Fullname:" + u.getFullName());
//
////            ubl.addPost("img4.jpg", "First Post from shobhit12", 3);
//            out.println("<br/>");
//            out.println("<br/>");
//            out.println("All Posts");
            Collection<Posts> posts = ubl.getAllPosts();
            for (Posts p : posts) {
                out.println("<br/>");
                out.println("Id:" + p.getPostId() + " --- MediaUrl:" + p.getMediaUrl() + " --- Username:" + p.getUserId().getUsername());
            }
//
//            out.println("<br/>");
//            out.println("<br/>");
//            out.println("All Posts by User Id 1");
//            Collection<Posts> postsOf1 = ubl.getAllPostsOfUser(1);
//            for (Posts p : postsOf1) {
//                out.println("<br/>");
//                out.println("Id:" + p.getPostId() + " --- MediaUrl:" + p.getMediaUrl() + " --- Username:" + p.getUserId().getUsername());
//            }
//
//            out.println("<br/>");
//            out.println("<br/>");
//            out.println("All Posts by User Id 3");
//            Collection<Posts> postsOf3 = ubl.getAllPostsOfUser(3);
//            for (Posts p : postsOf3) {
//                out.println("<br/>");
//                out.println("Id:" + p.getPostId() + " --- MediaUrl:" + p.getMediaUrl() + " --- Username:" + p.getUserId().getUsername());
//            }
            out.println("<br/>");
            out.println("Hello world");

            out.println("</h2>");

            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
