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
@Table(name = "groupmaster")
@NamedQueries({
    @NamedQuery(name = "Groupmaster.findAll", query = "SELECT g FROM Groupmaster g"),
    @NamedQuery(name = "Groupmaster.findByGroupmasterId", query = "SELECT g FROM Groupmaster g WHERE g.groupmasterId = :groupmasterId"),
    @NamedQuery(name = "Groupmaster.findByGroupName", query = "SELECT g FROM Groupmaster g WHERE g.groupName = :groupName"),
    @NamedQuery(name = "Groupmaster.findByCreatedAt", query = "SELECT g FROM Groupmaster g WHERE g.createdAt = :createdAt"),
    @NamedQuery(name = "Groupmaster.findByUpdatedAt", query = "SELECT g FROM Groupmaster g WHERE g.updatedAt = :updatedAt")})
public class Groupmaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "groupmasterId")
    private Integer groupmasterId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "groupName")
    private String groupName;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "groupmasterId")
    private Collection<Users> usersCollection;

    public Groupmaster() {
    }

    public Groupmaster(Integer groupmasterId) {
        this.groupmasterId = groupmasterId;
    }

    public Groupmaster(Integer groupmasterId, String groupName, Date createdAt, Date updatedAt) {
        this.groupmasterId = groupmasterId;
        this.groupName = groupName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getGroupmasterId() {
        return groupmasterId;
    }

    public void setGroupmasterId(Integer groupmasterId) {
        this.groupmasterId = groupmasterId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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
    public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupmasterId != null ? groupmasterId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Groupmaster)) {
            return false;
        }
        Groupmaster other = (Groupmaster) object;
        if ((this.groupmasterId == null && other.groupmasterId != null) || (this.groupmasterId != null && !this.groupmasterId.equals(other.groupmasterId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Groupmaster[ groupmasterId=" + groupmasterId + " ]";
    }
    
}
