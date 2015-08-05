/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hevasoluciones.server;

import com.hevasoluciones.shared.Beacon;
import com.hevasoluciones.shared.VRFields;


/**
 *
 * @author rafael.rosabal@gmail.com
 */
public interface CloudMannager {
    
public Beacon getBeacon(String appId , String appToken);   
public VRFields  uniqueVisitorsforRegion(String appId,String appToken,String uuid,String Major);
    
}
