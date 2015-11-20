/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hevasoluciones.shared;

import java.util.ArrayList;

/**
 *
 * @author rafael.rosabal@gmail.com
 */
public class Beacon {
    
  private String id;
  private String uuid;
  private long major;
  private long minor;
  private String mac;
  private String color;
  private String name;
  long battery_life_expectancy_in_days;
  private ArrayList<String> tags;
  //private Object settings;
  /*private int interval;
  private float range;
  private String hardware;
  private String firmware;
  private boolean  basic_power_mode;
  private boolean  smart_power_mode;
  private String timezone;
  private boolean security;*/
  private String icon;

    public Beacon() {
  
    this.tags= new ArrayList<>();
    }
  
  

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public long getMajor() {
        return major;
    }

    public void setMajor(long major) {
        this.major = major;
    }

    public long getMinor() {
        return minor;
    }

    public void setMinor(long minor) {
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

    public long getBattery_life_expectancy_in_days() {
        return battery_life_expectancy_in_days;
    }

    public void setBattery_life_expectancy_in_days(long battery_life_expectancy_in_days) {
        this.battery_life_expectancy_in_days = battery_life_expectancy_in_days;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

  
    public String getBeaconStatus(){
    
    if(this.battery_life_expectancy_in_days==0){
    return "off";
    
    }else if(this.battery_life_expectancy_in_days<=30)   
    {
     return "alert";
    }else{
        
    return "on";     
    }
    
        
    }
    
  
     
  
    
}
