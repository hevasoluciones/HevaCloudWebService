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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "beacon")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Beacon.findAll", query = "SELECT b FROM Beacon b"),
    @NamedQuery(name = "Beacon.findByIdBeacon", query = "SELECT b FROM Beacon b WHERE b.idBeacon = :idBeacon"),
    @NamedQuery(name = "Beacon.findByUuid", query = "SELECT b FROM Beacon b WHERE b.uuid = :uuid"),
    @NamedQuery(name = "Beacon.findByMajor", query = "SELECT b FROM Beacon b WHERE b.major = :major"),
    @NamedQuery(name = "Beacon.findByMinor", query = "SELECT b FROM Beacon b WHERE b.minor = :minor"),
    @NamedQuery(name = "Beacon.findByMac", query = "SELECT b FROM Beacon b WHERE b.mac = :mac"),
    @NamedQuery(name = "Beacon.findByColor", query = "SELECT b FROM Beacon b WHERE b.color = :color"),
    @NamedQuery(name = "Beacon.findByName", query = "SELECT b FROM Beacon b WHERE b.name = :name"),
    @NamedQuery(name = "Beacon.findByIcon", query = "SELECT b FROM Beacon b WHERE b.icon = :icon")})
public class Beacon implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idBeacon")
    private Integer idBeacon;
    @Size(max = 255)
    @Column(name = "uuid")
    private String uuid;
    @Size(max = 255)
    @Column(name = "major")
    private String major;
    @Size(max = 255)
    @Column(name = "minor")
    private String minor;
    @Size(max = 255)
    @Column(name = "mac")
    private String mac;
    @Size(max = 255)
    @Column(name = "color")
    private String color;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @Size(max = 255)
    @Column(name = "icon")
    private String icon;
    @JoinColumn(name = "idCampain", referencedColumnName = "idCampain")
    @ManyToOne
    private Campain idCampain;
    @OneToMany(mappedBy = "idBeacon")
    private Collection<Userbeacon> userbeaconCollection;

    public Beacon() {
    }

    public Beacon(Integer idBeacon) {
        this.idBeacon = idBeacon;
    }

    public Integer getIdBeacon() {
        return idBeacon;
    }

    public void setIdBeacon(Integer idBeacon) {
        this.idBeacon = idBeacon;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMinor() {
        return minor;
    }

    public void setMinor(String minor) {
        this.minor = minor;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Campain getIdCampain() {
        return idCampain;
    }

    public void setIdCampain(Campain idCampain) {
        this.idCampain = idCampain;
    }

    @XmlTransient
    public Collection<Userbeacon> getUserbeaconCollection() {
        return userbeaconCollection;
    }

    public void setUserbeaconCollection(Collection<Userbeacon> userbeaconCollection) {
        this.userbeaconCollection = userbeaconCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBeacon != null ? idBeacon.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Beacon)) {
            return false;
        }
        Beacon other = (Beacon) object;
        if ((this.idBeacon == null && other.idBeacon != null) || (this.idBeacon != null && !this.idBeacon.equals(other.idBeacon))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hevasoluciones.db.Beacon[ idBeacon=" + idBeacon + " ]";
    }
    
}
