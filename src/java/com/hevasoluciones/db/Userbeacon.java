/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hevasoluciones.db;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author userrsh
 */
@Entity
@Table(name = "userbeacon")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Userbeacon.findAll", query = "SELECT u FROM Userbeacon u"),
    @NamedQuery(name = "Userbeacon.findById", query = "SELECT u FROM Userbeacon u WHERE u.id = :id")})
public class Userbeacon implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "iduser", referencedColumnName = "iduser")
    @ManyToOne
    private User iduser;
    @JoinColumn(name = "idBeacon", referencedColumnName = "idBeacon")
    @ManyToOne
    private Beacon idBeacon;

    public Userbeacon() {
    }

    public Userbeacon(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getIduser() {
        return iduser;
    }

    public void setIduser(User iduser) {
        this.iduser = iduser;
    }

    public Beacon getIdBeacon() {
        return idBeacon;
    }

    public void setIdBeacon(Beacon idBeacon) {
        this.idBeacon = idBeacon;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Userbeacon)) {
            return false;
        }
        Userbeacon other = (Userbeacon) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hevasoluciones.db.Userbeacon[ id=" + id + " ]";
    }
    
}
