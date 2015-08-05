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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author userrsh
 */
@Entity
@Table(name = "tag")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tag.findAll", query = "SELECT t FROM Tag t"),
    @NamedQuery(name = "Tag.findByIdTag", query = "SELECT t FROM Tag t WHERE t.idTag = :idTag"),
    @NamedQuery(name = "Tag.findByName", query = "SELECT t FROM Tag t WHERE t.name = :name")})
public class Tag implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idTag")
    private Integer idTag;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "idCampain", referencedColumnName = "idCampain")
    @ManyToOne
    private Campain idCampain;

    public Tag() {
    }

    public Tag(Integer idTag) {
        this.idTag = idTag;
    }

    public Integer getIdTag() {
        return idTag;
    }

    public void setIdTag(Integer idTag) {
        this.idTag = idTag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Campain getIdCampain() {
        return idCampain;
    }

    public void setIdCampain(Campain idCampain) {
        this.idCampain = idCampain;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTag != null ? idTag.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tag)) {
            return false;
        }
        Tag other = (Tag) object;
        if ((this.idTag == null && other.idTag != null) || (this.idTag != null && !this.idTag.equals(other.idTag))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hevasoluciones.db.Tag[ idTag=" + idTag + " ]";
    }
    
}
