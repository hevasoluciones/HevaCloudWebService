/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hevasoluciones.db;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author userrsh
 */
@Entity
@Table(name = "campain")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Campain.findAll", query = "SELECT c FROM Campain c"),
    @NamedQuery(name = "Campain.findByIdCampain", query = "SELECT c FROM Campain c WHERE c.idCampain = :idCampain"),
    @NamedQuery(name = "Campain.findByTitle", query = "SELECT c FROM Campain c WHERE c.title = :title"),
    @NamedQuery(name = "Campain.findByContent", query = "SELECT c FROM Campain c WHERE c.content = :content"),
    @NamedQuery(name = "Campain.findByFeaturedImage", query = "SELECT c FROM Campain c WHERE c.featuredImage = :featuredImage")})
public class Campain implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idCampain")
    private Integer idCampain;
    @Size(max = 255)
    @Column(name = "title")
    private String title;
    @Size(max = 255)
    @Column(name = "content")
    private String content;
    @Size(max = 255)
    @Column(name = "featuredImage")
    private String featuredImage;
    @OneToMany(mappedBy = "idCampain")
    private Collection<Beacon> beaconCollection;
    @OneToMany(mappedBy = "idCampain")
    private Collection<Tag> tagCollection;

    public Campain() {
    }

    public Campain(Integer idCampain) {
        this.idCampain = idCampain;
    }

    public Integer getIdCampain() {
        return idCampain;
    }

    public void setIdCampain(Integer idCampain) {
        this.idCampain = idCampain;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFeaturedImage() {
        return featuredImage;
    }

    public void setFeaturedImage(String featuredImage) {
        this.featuredImage = featuredImage;
    }

    @XmlTransient
    public Collection<Beacon> getBeaconCollection() {
        return beaconCollection;
    }

    public void setBeaconCollection(Collection<Beacon> beaconCollection) {
        this.beaconCollection = beaconCollection;
    }

    @XmlTransient
    public Collection<Tag> getTagCollection() {
        return tagCollection;
    }

    public void setTagCollection(Collection<Tag> tagCollection) {
        this.tagCollection = tagCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCampain != null ? idCampain.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Campain)) {
            return false;
        }
        Campain other = (Campain) object;
        if ((this.idCampain == null && other.idCampain != null) || (this.idCampain != null && !this.idCampain.equals(other.idCampain))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hevasoluciones.db.Campain[ idCampain=" + idCampain + " ]";
    }
    
}
