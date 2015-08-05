/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hevasoluciones.shared;

/**
 *
 * @author rafael.rosabal@gmail.com
 */
public class Visits {
    
   String time;
   int app_in_foreground_visits;
   int app_in_background_visits;
   int total_visits;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getApp_in_foreground_visits() {
        return app_in_foreground_visits;
    }

    public void setApp_in_foreground_visits(int app_in_foreground_visits) {
        this.app_in_foreground_visits = app_in_foreground_visits;
    }

    public int getApp_in_background_visits() {
        return app_in_background_visits;
    }

    public void setApp_in_background_visits(int app_in_background_visits) {
        this.app_in_background_visits = app_in_background_visits;
    }

    public int getTotal_visits() {
        return total_visits;
    }

    public void setTotal_visits(int total_visits) {
        this.total_visits = total_visits;
    }
   
    
}
