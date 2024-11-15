/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.ManyToOne;
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
@Table(name = "conversations")
@NamedQueries({
    @NamedQuery(name = "Conversations.findAll", query = "SELECT c FROM Conversations c"),
    @NamedQuery(name = "Conversations.findByConversationId", query = "SELECT c FROM Conversations c WHERE c.conversationId = :conversationId"),
    @NamedQuery(name = "Conversations.findByType", query = "SELECT c FROM Conversations c WHERE c.type = :type"),
    @NamedQuery(name = "Conversations.findByCreatedAt", query = "SELECT c FROM Conversations c WHERE c.createdAt = :createdAt"),
    @NamedQuery(name = "Conversations.findByUpdatedAt", query = "SELECT c FROM Conversations c WHERE c.updatedAt = :updatedAt")})
public class Conversations implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "conversationId")
    private Integer conversationId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @JoinTable(name = "conversation_participants", joinColumns = {
        @JoinColumn(name = "conversationId", referencedColumnName = "conversationId")}, inverseJoinColumns = {
        @JoinColumn(name = "userId", referencedColumnName = "userId")})
    @ManyToMany
    private Collection<Users> participants;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conversationId")
    private Collection<Messages> messagesCollection;
    @JoinColumn(name = "groupId", referencedColumnName = "groupId")
    @ManyToOne
    private Groups groupId;

    public Conversations() {
    }

    public Conversations(Integer conversationId) {
        this.conversationId = conversationId;
    }

    public Conversations(Integer conversationId, String type, Date createdAt, Date updatedAt) {
        this.conversationId = conversationId;
        this.type = type;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getConversationId() {
        return conversationId;
    }

    public void setConversationId(Integer conversationId) {
        this.conversationId = conversationId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Collection<Users> getParticipants() {
        return participants;
    }

    public void setParticipants(Collection<Users> participants) {
        this.participants = participants;
    }

    public Collection<Messages> getMessagesCollection() {
        return messagesCollection;
    }

    public void setMessagesCollection(Collection<Messages> messagesCollection) {
        this.messagesCollection = messagesCollection;
    }

    public Groups getGroupId() {
        return groupId;
    }

    public void setGroupId(Groups groupId) {
        this.groupId = groupId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (conversationId != null ? conversationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Conversations)) {
            return false;
        }
        Conversations other = (Conversations) object;
        if ((this.conversationId == null && other.conversationId != null) || (this.conversationId != null && !this.conversationId.equals(other.conversationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Conversations[ conversationId=" + conversationId + " ]";
    }

}
