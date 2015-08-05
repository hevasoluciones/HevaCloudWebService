/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hevasoluciones.shared;

import java.util.ArrayList;

/**
 *
 * @author userrsh
 */
public class Campains {

private int id;    
private String title;
private String content;
private String featuredImage;
public ArrayList beacons;
public ArrayList tags;


     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public ArrayList getBeacons() {
        return beacons;
    }

    public void setBeacons(ArrayList beacons) {
        this.beacons = beacons;
    }

    public ArrayList getTags() {
        return tags;
    }

    public void setTags(ArrayList tags) {
        this.tags = tags;
    }
    


}
