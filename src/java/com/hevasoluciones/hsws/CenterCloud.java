/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hevasoluciones.hsws;

import com.hevasoluciones.server.CloudMannager;
import com.hevasoluciones.server.CloudMannagerImpl;
import com.hevasoluciones.shared.Beacon;
import com.hevasoluciones.shared.Campains;
import com.hevasoluciones.shared.VisitsRegionSpec;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author userrsh
 */
@WebService(serviceName = "CenterCloud")
public class CenterCloud {

 CloudMannager cm;

    /**
     * Web service operation
     * @param appId
     * @param appToken
     * @return 
     */
   @WebMethod(operationName = "getBeacons")
    public ArrayList<Beacon> getBeacons(@WebParam(name = "appId") String appId, @WebParam(name = "appToken") String appToken) {
        //TODO write your implementation code here:
        cm= new CloudMannagerImpl();
        return cm.getBeacon(appId, appToken);
      
    }

    /**
     * Web service operation
     * @param appId
     * @param appToken
     * @param uuid
     * @return 
     */
  @WebMethod(operationName = "uniqueVisitorsforRegion")
    public VisitsRegionSpec uniqueVisitorsforRegion(@WebParam(name = "appId") String appId, @WebParam(name = "appToken") String appToken, @WebParam(name = "uuid") String uuid) {
        //TODO write your implementation code here:
          cm= new CloudMannagerImpl();
        return cm.uniqueVisitorsforRegion(appId, appToken, uuid);
    }

    /**
     * Web service operation
     * @param appId
     * @param appToken
     * @param uuid
     * @param major
     * @return 
     */
  @WebMethod(operationName = "visitsforRegion")
   public VisitsRegionSpec visitsforRegion(@WebParam(name = "appId") String appId, @WebParam(name = "appToken") String appToken, @WebParam(name = "uuid") String uuid, @WebParam(name = "major") String major) {
        
         cm= new CloudMannagerImpl();
        return cm.VisitorsforRegion(appId,appToken,uuid,major);
    }

    /**
     * Web service operation
     * @param Beacons
     * @return 
     */
    @WebMethod(operationName = "getCampains")
    public ArrayList<Campains> getCampains(@WebParam(name = "Beacons") String Beacons) {
        
        cm= new CloudMannagerImpl();
        return cm.getCampains();
    }

    /**
     * Web service operation
     * @param idCampain
     * @return 
     */
    @WebMethod(operationName = "deleteCampain")
    public String deleteCampain(@WebParam(name = "idCampain") int idCampain) {
         cm= new CloudMannagerImpl();
         return cm.removeCampain(idCampain);
    }

    /**
     * Web service operation
     * @param idCampain
     * @param title
     * @param content
     * @param featuredImage
     * @param beacons
     * @param tags
     * @return 
     */
    @WebMethod(operationName = "editCampain")
    public String editCampain(@WebParam(name = "idCampain") int  idCampain,@WebParam(name = "title") String title,@WebParam(name = "content") String content,@WebParam(name = "featuredImage") String featuredImage,@WebParam(name = "beacons") ArrayList<Beacon> beacons,@WebParam(name = "tags") ArrayList<String> tags) {
         cm= new CloudMannagerImpl();
         return cm.editCampain(idCampain,title , content,featuredImage,beacons,tags);
    }

    /**
     * Web service operation
     * @param title
     * @param content
     * @param featuredImage
     * @param beaconList
     * @param tagList
     * @return 
     */
    @WebMethod(operationName = "createNewCampain")
    public String createNewCampain(@WebParam(name = "title") String title, @WebParam(name = "content") String content, @WebParam(name = "featuredImage") String featuredImage, @WebParam(name = "beacons") ArrayList<Beacon> beaconList, @WebParam(name = "tags") ArrayList<String> tagList) {
       cm= new CloudMannagerImpl();
       return cm.createNewCampain(title, content, featuredImage, beaconList, tagList);
    }

    /**
     * Web service operation
     * @param beacon
     * @return 
     */
    @WebMethod(operationName = "insertBeaconFistTime")
    public String insertBeaconFistTime() {
       cm= new CloudMannagerImpl();
     ArrayList<Beacon> mybeacon = cm.getBeacon("redigitalbeacons", "68fed2c1fecc03e461572b3e72652440");
     
     for(Beacon b: mybeacon)
     {
     cm.insertBeacon(b);
     
     }
        return "ok";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "updateBeaconsInDb")
    public String updateBeaconsInDb() {
        //TODO write your implementation code here:
     cm= new CloudMannagerImpl();
     boolean itshere=false;  
     ArrayList<Beacon> mybeacon = cm.getBeacon("redigitalbeacons", "68fed2c1fecc03e461572b3e72652440");
     ArrayList<Beacon> mybeaconFromDb = cm.getBeaconsfromDB();
    
     for(Beacon b: mybeacon)
     {
     itshere=false;      
     for(Beacon bdb: mybeaconFromDb) {   
     
     if(b.getMac().equals(bdb.getMac()))    
     
         itshere= true;
     
     }
     
     if(itshere==false){
      cm.insertBeacon(b);
     }
     }
        return "ok";
    }
    
    
    
}
