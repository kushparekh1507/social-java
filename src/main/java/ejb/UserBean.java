/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb;

import entity.Comments;
import entity.Conversations;
import entity.Groups;
import entity.Messages;
import entity.Posts;
import entity.Users;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author DELL
 */
@Stateless
public class UserBean implements UserBeanLocal {

    @PersistenceContext(unitName = "projectPU")
    EntityManager em;

    boolean likedOrNot(Integer userId, Integer postId) {
        Posts p = (Posts) em.find(Posts.class, postId);
        Users u = (Users) em.find(Users.class, userId);

        if (p.getUsersCollection().contains(u)) {
            return true;
        } else {
            return false;
        }

    }

    // Add business logic below. (Right-click in editor and cho 
    // "Insert Code > Add Business Method")
//    --------------------            USERS               ----------------------------
    @Override
    public void addUser(Users user) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

//        try {
        System.out.println("name:" + user.getUsername());
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        em.persist(user);
//        } catch (ConstraintViolationException e) {
//            e.getConstraintViolations().forEach(violation -> {
//                System.out.println("Validation error: " + violation.getMessage());
//            });
//        }
    }

    @Override
    public void updateUser(Integer userId, String username, String fullName, String password, String profilePic) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Users u = (Users) em.find(Users.class, userId);
        u.setUsername(username);
        u.setFullName(fullName);
        u.setPassword(password);
        u.setProfilePic(profilePic);

        em.merge(u);
    }

    @Override
    public void removeUser(Integer userId) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Users u = (Users) em.find(Users.class, userId);
        em.remove(u);
    }

    @Override
    public Users getUserById(Integer userId) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.find(Users.class, userId);
    }

    @Override
    public Collection<Users> getAllUsers() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createNamedQuery("Users.findAll").getResultList();
    }

    @Override
    public Collection<Users> getUserByUsername(String username) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createNamedQuery("Users.findByUsername").setParameter("username", username).getResultList();
    }

    @Override
    public Users getUserByEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

//    -----------------------------                     POSTS                     -----------------------------
    @Override
    public void addPost(String mediaUrl, String caption, Integer userId) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Users u = (Users) em.find(Users.class, userId);
        Collection<Posts> posts = u.getPostsCollection();

        Posts p = new Posts();
        p.setMediaUrl(mediaUrl);
        p.setCaption(caption);
        p.setUserId(u);
        p.setCreatedAt(new Date());
        p.setUpdatedAt(new Date());

        try {
            em.persist(p);
            posts.add(p);
            u.setPostsCollection(posts);
            em.merge(u);
        } catch (ConstraintViolationException e) {
            e.getConstraintViolations().forEach(violation -> {
                System.out.println("Validation error: " + violation.getMessage());
            });
        }
    }

    @Override
    public Posts getPost(Integer postId) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.find(Posts.class, postId);
    }

    @Override
    public Collection<Posts> getAllPosts() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createNamedQuery("Posts.findAll").getResultList();
    }

    @Override
    public Collection<Posts> getAllPostsOfUser(Integer userId) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Users u = (Users) em.find(Users.class, userId);
        return u.getPostsCollection();
    }

    @Override
    public void deletePost(Integer postId, Integer userId) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Posts p = (Posts) em.find(Posts.class, postId);

        Users u = (Users) em.find(Users.class, userId);
        Collection<Posts> postOfUser = u.getPostsCollection();

        postOfUser.remove(p);
        u.setPostsCollection(postOfUser);

        em.remove(p);
    }

    @Override
    public void likeUnlikePost(Integer userId, Integer postId) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        boolean alreadyLiked = likedOrNot(userId, postId);

        Users u = (Users) em.find(Users.class, userId);
        Posts p = em.find(Posts.class, postId);

        Collection<Users> likesOfPosts = p.getUsersCollection();
        Collection<Posts> likedPostsOfUser = u.getLikedPostsCollection();

        if (alreadyLiked) {
            likedPostsOfUser.remove(p);
            likesOfPosts.remove(u);

            u.setLikedPostsCollection(likedPostsOfUser);
            p.setUsersCollection(likesOfPosts);

            em.merge(p);
        } else {
            likedPostsOfUser.add(p);
            likesOfPosts.add(u);

            u.setLikedPostsCollection(likedPostsOfUser);
            p.setUsersCollection(likesOfPosts);

            em.merge(p);
        }
    }

    @Override
    public Collection<Users> getAllLikesOfPost(Integer postId) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Posts p = em.find(Posts.class, postId);
        return p.getUsersCollection();
    }

    @Override
    public Collection<Posts> getPostsLikedByUser(Integer userId) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Users u = (Users) em.find(Users.class, userId);
        return u.getLikedPostsCollection();
    }

//    ----------------------------                  COMMENTS                ----------------------------------
    @Override
    public void addComment(Integer userId, Integer postId, String content) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Posts p = (Posts) em.find(Posts.class, postId);
        Collection<Comments> commOfPost = p.getCommentsCollection();

        Users u = (Users) em.find(Users.class, userId);
        Collection<Comments> commOfUser = p.getCommentsCollection();

        Comments c = new Comments();
        c.setPostId(p);
        c.setUserId(u);
        c.setContent(content);
        c.setCreatedAt(new Date());
        c.setUpdatedAt(new Date());

        commOfPost.add(c);
        p.setCommentsCollection(commOfPost);

        commOfUser.add(c);
        p.setCommentsCollection(commOfUser);

        em.persist(c);
        em.merge(p);
        em.merge(u);
    }

    @Override
    public void deleteComment(Integer commentId, Integer userId, Integer postId) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Comments c = (Comments) em.find(Comments.class, commentId);

        Users u = (Users) em.find(Users.class, userId);
        Collection<Comments> commOfUser = u.getCommentsCollection();

        Posts p = (Posts) em.find(Posts.class, postId);
        Collection<Comments> commOfPost = p.getCommentsCollection();

        commOfUser.remove(c);
        u.setCommentsCollection(commOfUser);

        commOfPost.remove(c);
        p.setCommentsCollection(commOfPost);

        em.merge(p);
        em.merge(c);
        em.remove(c);
    }

    @Override
    public Collection<Comments> getAllCommentsOfPost(Integer postId) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Posts p = (Posts) em.find(Posts.class, postId);
        return p.getCommentsCollection();
    }

//    ----------------------------------             FOLLOWERS               --------------------------------
    @Override
    public void followUnfollowUser(Integer followedUserId, Integer followerUserId) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Users followedUser = (Users) em.find(Users.class, followedUserId);
        Users followerUser = (Users) em.find(Users.class, followerUserId);

        Collection<Users> followers = followedUser.getFollowersCollection();
        Collection<Users> followings = followerUser.getFollowingsCollection();

        if (followers.contains(followerUser)) {
            followers.remove(followerUser);
            followings.remove(followedUser);
        } else {
            followers.add(followerUser);
            followings.add(followedUser);
        }

        followedUser.setFollowersCollection(followers);
        followerUser.setFollowingsCollection(followings);

        em.merge(followedUser);
    }

    @Override
    public Collection<Users> getAllFollowersOfUser(Integer userId) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Users u = (Users) em.find(Users.class, userId);
        return u.getFollowersCollection();
    }

    @Override
    public Collection<Users> getAllFollowingsOfUser(Integer userId) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Users u = (Users) em.find(Users.class, userId);
        return u.getFollowingsCollection();
    }

//    --------------------------             GROUPS                     -----------------------------------
    @Override
    public void createGroup(String groupName, Integer userId) {
        System.out.println("User id:" + userId);
        Users u = em.find(Users.class, userId);
        System.out.print("User :" + u);

        if (u == null) {
            System.out.println("User with ID " + userId + " does not exist.");
            return; // Or throw an exception if needed
        }

        Collection<Groups> createdGroups = u.getCreatedGroups();

        Groups g = new Groups();
        g.setGroupName(groupName);
        g.setCreatedBy(u);
        g.setCreatedAt(new Date());

        try {
            System.out.println("Creating group: " + g);

            createdGroups.add(g); // Update user's created groups
            u.setCreatedGroups(createdGroups); // Ensure user entity reflects this relationship

            em.persist(g);
            em.flush();
            em.merge(u);

            Integer generatedId = g.getGroupId();
            System.out.println("Generated group ID: " + generatedId);

            addMemberToGroup(userId, g.getGroupId());
        } catch (ConstraintViolationException e) {
            e.getConstraintViolations().forEach(violation -> {
                System.out.println("Constraint violation on " + violation.getPropertyPath() + ": " + violation.getMessage());
            });
            throw e; // Re-throw to handle it on the frontend or higher layer if necessary
        }
    }

    @Override
    public void addMemberToGroup(Integer userId, Integer groupId) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        System.out.println("User id:" + userId + " Group Id:" + groupId);
        Users u = (Users) em.find(Users.class, userId);
        Groups g = (Groups) em.find(Groups.class, groupId);

        Collection<Groups> groupsOfUser = u.getGroupsCollection();
        Collection<Users> membersOfGroup = g.getUsersCollection();

        groupsOfUser.add(g);
        membersOfGroup.add(u);

        em.merge(g);
    }

    @Override
    public void deleteMemberFromGroup(Integer groupId, Integer userId) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Users u = (Users) em.find(Users.class, userId);
        Groups g = (Groups) em.find(Groups.class, groupId);

        Collection<Groups> groupsOfUser = u.getGroupsCollection();
        Collection<Users> membersOfGroup = g.getUsersCollection();

        groupsOfUser.remove(g);
        membersOfGroup.remove(u);

        em.merge(g);
    }

    @Override
    public Collection<Groups> getAllGroups() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createNamedQuery("Groups.findAll").getResultList();
    }

    @Override
    public Collection<Users> getAllMembersOfGroup(Integer groupId) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Groups g = (Groups) em.find(Groups.class, groupId);
        return g.getUsersCollection();
    }

    @Override
    public Collection<Groups> getAllGroupsOfUser(Integer userId) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Users u = em.find(Users.class, userId);
        return u.getGroupsCollection();
    }

    @Override
    public Collection<Groups> getAllGroupsCreatedByUser(Integer userId) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Users u = (Users) em.find(Users.class, userId);
        return u.getCreatedGroups();
    }

//    --------------------------------           CONVERSATIONS AND MESSAGES                  --------------
    @Override
    public Conversations findOrCreateConversation(List<Integer> participantsIds, Integer groupId) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        Conversations conv;

        if (groupId == null) {
//        first get user entities based on participant ids
            Collection<Users> participants = em.createQuery("SELECT u FROM Users u where u.userId IN :participantsIds", Users.class)
                    .setParameter("participantsIds", participantsIds)
                    .getResultList();

//          now check if conversation exists or not
            conv = em.createQuery("SELECT c FROM Conversations c JOIN c.participants p where c.groupId IS NULL AND "
                    + "p in :participants GROUP BY c HAVING COUNT(p)=:size", Conversations.class)
                    .setParameter("participants", participants)
                    .setParameter("size", participants.size())
                    .getResultStream()
                    .findFirst()
                    .orElse(null);

            if (conv == null) {
                conv = new Conversations();
                conv.setParticipants(participants);
                conv.setType("user");
                conv.setCreatedAt(new Date());
                conv.setUpdatedAt(new Date());
                em.persist(conv);
            }

        } else {
//            retrive group conversation if it exists
            conv = em.createQuery("SELECT c FROM Conversations c where c.groupId.groupId=:groupId", Conversations.class)
                    .setParameter("groupId", groupId)
                    .getResultStream()
                    .findFirst()
                    .orElse(null);

            if (conv == null) {
                conv = new Conversations();
                Groups g = em.find(Groups.class, groupId);
                conv.setGroupId(g);
                conv.setCreatedAt(new Date());
                conv.setUpdatedAt(new Date());
                conv.setType("group");
                em.persist(conv);
            }
        }
        return conv;
    }

    @Override
    public Conversations getConversationById(Integer conversationId) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.find(Conversations.class, conversationId);
    }

    @Override
    public Collection<Conversations> getAllConversationOfUser(Integer userId) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.find(Users.class, userId).getConversationsCollection();
    }

    @Override
    public Messages sendMessage(List<Integer> participantsIds, Integer groupId, Integer senderId, String text) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Conversations conv = findOrCreateConversation(participantsIds, groupId);

        Messages m = new Messages();
        Users sender = em.find(Users.class, senderId);

        m.setConversationId(conv);
        m.setSenderId(sender);
        m.setContent(text);
        m.setSentAt(new Date());
        m.setIsSeen(Boolean.FALSE);
        em.persist(m);

        conv.setUpdatedAt(new Date());
        em.merge(conv);

        return m;
    }

    @Override
    public Collection<Messages> getMessagesByConversation(Integer conversationId) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Conversations c = em.find(Conversations.class, conversationId);
        return c.getMessagesCollection();
    }

}
