/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "users")
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByUserId", query = "SELECT u FROM Users u WHERE u.userId = :userId"),
    @NamedQuery(name = "Users.findByUsername", query = "SELECT u FROM Users u WHERE u.username = :username"),
    @NamedQuery(name = "Users.findByFullName", query = "SELECT u FROM Users u WHERE u.fullName = :fullName"),
    @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password"),
    @NamedQuery(name = "Users.findByProfilePic", query = "SELECT u FROM Users u WHERE u.profilePic = :profilePic"),
    @NamedQuery(name = "Users.findByCreatedAt", query = "SELECT u FROM Users u WHERE u.createdAt = :createdAt"),
    @NamedQuery(name = "Users.findByUpdatedAt", query = "SELECT u FROM Users u WHERE u.updatedAt = :updatedAt")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "userId")
    private Integer userId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "fullName")
    private String fullName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "profilePic")
    private String profilePic;
    @Basic(optional = false)
    @NotNull
    @Column(name = "createdAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "updatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @JoinTable(name = "followers", joinColumns = {
        @JoinColumn(name = "followerUserId", referencedColumnName = "userId")}, inverseJoinColumns = {
        @JoinColumn(name = "followedUserId", referencedColumnName = "userId")})
    @ManyToMany
    private Collection<Users> followersCollection;
    @ManyToMany(mappedBy = "followersCollection")
    private Collection<Users> followingsCollection;
    @ManyToMany(mappedBy = "usersCollection")
    private Collection<Groups> groupsCollection;
    @ManyToMany(mappedBy = "participants")
    private Collection<Conversations> conversationsCollection;
    @JoinTable(name = "likes", joinColumns = {
        @JoinColumn(name = "userId", referencedColumnName = "userId")}, inverseJoinColumns = {
        @JoinColumn(name = "postId", referencedColumnName = "postId")})
    @ManyToMany
    private Collection<Posts> likedPostsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<Comments> commentsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<Groupmaster> groupmasterCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdBy")
    private Collection<Groups> createdGroups;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "senderId")
    private Collection<Messages> messagesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<Posts> postsCollection;

    public Users() {
    }

    public Users(Integer userId) {
        this.userId = userId;
    }

    public Users(Integer userId, String username, String fullName, String password, String profilePic, Date createdAt, Date updatedAt) {
        this.userId = userId;
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        this.profilePic = profilePic;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @JsonbTransient
    public Collection<Groups> getGroupsCollection() {
        return groupsCollection;
    }

    public void setGroupsCollection(Collection<Groups> groupsCollection) {
        this.groupsCollection = groupsCollection;
    }

    @JsonbTransient
    public Collection<Conversations> getConversationsCollection() {
        return conversationsCollection;
    }

    public void setConversationsCollection(Collection<Conversations> conversationsCollection) {
        this.conversationsCollection = conversationsCollection;
    }

    @JsonbTransient
    public Collection<Posts> getPostsCollection() {
        return postsCollection;
    }

    public void setPostsCollection(Collection<Posts> postsCollection) {
        this.postsCollection = postsCollection;
    }

    @JsonbTransient
    public Collection<Comments> getCommentsCollection() {
        return commentsCollection;
    }

    public void setCommentsCollection(Collection<Comments> commentsCollection) {
        this.commentsCollection = commentsCollection;
    }

    @JsonbTransient
    public Collection<Groupmaster> getGroupmasterCollection() {
        return groupmasterCollection;
    }

    public void setGroupmasterCollection(Collection<Groupmaster> groupmasterCollection) {
        this.groupmasterCollection = groupmasterCollection;
    }

    @JsonbTransient
    public Collection<Messages> getMessagesCollection() {
        return messagesCollection;
    }

    public void setMessagesCollection(Collection<Messages> messagesCollection) {
        this.messagesCollection = messagesCollection;
    }

    @JsonbTransient
    public Collection<Users> getFollowersCollection() {
        return followersCollection;
    }

    public void setFollowersCollection(Collection<Users> followersCollection) {
        this.followersCollection = followersCollection;
    }

    @JsonbTransient
    public Collection<Users> getFollowingsCollection() {
        return followingsCollection;
    }

    public void setFollowingsCollection(Collection<Users> followingsCollection) {
        this.followingsCollection = followingsCollection;
    }

    @JsonbTransient
    public Collection<Posts> getLikedPostsCollection() {
        return likedPostsCollection;
    }

    public void setLikedPostsCollection(Collection<Posts> likedPostsCollection) {
        this.likedPostsCollection = likedPostsCollection;
    }

    @JsonbTransient
    public Collection<Groups> getCreatedGroups() {
        return createdGroups;
    }

    public void setCreatedGroups(Collection<Groups> createdGroups) {
        this.createdGroups = createdGroups;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Users[ userId=" + userId + " ]";
    }

}
