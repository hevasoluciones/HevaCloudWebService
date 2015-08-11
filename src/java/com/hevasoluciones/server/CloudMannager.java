/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hevasoluciones.server;

import com.hevasoluciones.shared.Beacon;
import com.hevasoluciones.shared.Campains;
import com.hevasoluciones.shared.VRFields;
import java.util.ArrayList;


/**
 *
 * @author rafael.rosabal@gmail.com
 */
public interface CloudMannager {
    
public ArrayList<Beacon> getBeacon(String appId , String appToken);   
public VRFields  uniqueVisitorsforRegion(String appId,String appToken,String uuid);
public VRFields VisitorsforRegion(String appId,String appToken,String uuid,String Major) ;
public ArrayList<Campains> getCampains() ;
public String  createNewCampain(String title,String content,String  featuredImage, ArrayList<Beacon> beacon,ArrayList<String> tag);
public String  editCampain(int idCampin,String title,String content,String  featuredImage,ArrayList<Beacon> beacon,ArrayList<String> tag);
public String  removeCampain(int idCampin);
public String insertBeacon(Beacon beacon);
public ArrayList<Beacon> getBeaconsfromDB();
public ArrayList<String> getTags();

}
