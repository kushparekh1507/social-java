/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb;

import entity.Comments;
import entity.Conversations;
import entity.Groups;
import entity.Messages;
import entity.Posts;
import entity.Users;
import java.util.Collection;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author DELL
 */
@Local
public interface UserBeanLocal {

//    users
    void addUser(Users user);

    void updateUser(Integer userId, String username, String fullName, String password, String profilePic);

    void removeUser(Integer userId);

    Users getUserById(Integer userId);

    Users getUserByUsername(String username);

    Users getUserByEmail(String email);

    Collection<Users> getAllUsers();

    Collection<Users> getUsersByRole(Integer groupmasterId);

//    posts
    void addPost(String mediaUrl, String caption, Integer userId);

    void deletePost(Integer postId, Integer userId);

    Posts getPost(Integer postId);

    Collection<Posts> getAllPosts();

    Collection<Posts> getAllPostsOfUser(Integer userId);

//    likes
    void likeUnlikePost(Integer userId, Integer postId);

    Collection<Users> getAllLikesOfPost(Integer postId);

    Collection<Posts> getPostsLikedByUser(Integer userId);

//    comments
    void addComment(Integer userId, Integer postId, String content);

    void deleteComment(Integer commentId, Integer userId, Integer postId);

    Collection<Comments> getAllCommentsOfPost(Integer postId);

//    followers
    void followUnfollowUser(Integer followedUserId, Integer followerUserId);

    Collection<Users> getAllFollowersOfUser(Integer userId);

    Collection<Users> getAllFollowingsOfUser(Integer userId);

//    groups and group members
    void createGroup(String groupName, Integer userId);  // created by

    void addMemberToGroup(Integer userId, Integer groupId);

    void deleteMemberFromGroup(Integer groupId, Integer userId);

    Collection<Groups> getAllGroups();

    Collection<Users> getAllMembersOfGroup(Integer groupId);

    Collection<Groups> getAllGroupsOfUser(Integer userId);

    Collection<Groups> getAllGroupsCreatedByUser(Integer userId);

//    conversations
    Conversations findOrCreateConversation(List<Integer> participantsIds, Integer groupId);

    Conversations getConversationById(Integer conversationId);

    Collection<Conversations> getAllConversationOfUser(Integer userId);

    Messages sendMessage(List<Integer> participantsIds, Integer groupId, Integer senderId, String text);

    Collection<Messages> getMessagesByConversation(Integer conversationId);

}
