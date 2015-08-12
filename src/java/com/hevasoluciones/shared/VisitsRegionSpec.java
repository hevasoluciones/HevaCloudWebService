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
public class VisitsRegionSpec {
    
  private  String region;
  public   ArrayList<Visits>  visits;     

    public VisitsRegionSpec() {
        
        this.visits= new ArrayList<Visits>();
    }
  
  

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

  

 
    
}
