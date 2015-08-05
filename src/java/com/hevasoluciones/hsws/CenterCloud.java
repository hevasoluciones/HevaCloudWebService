/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hevasoluciones.hsws;

import com.hevasoluciones.server.CloudMannager;
import com.hevasoluciones.shared.Beacon;
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
    public Beacon getBeacons(@WebParam(name = "appId") String appId, @WebParam(name = "appToken") String appToken) {
        //TODO write your implementation code here:
        return cm.getBeacon(appId, appToken);
        
    }

    /**
     * Web service operation
     * @param appId
     * @param appToken
     * @return 
     */
    @WebMethod(operationName = "uniqueVisitorsforRegion")
    public String uniqueVisitorsforRegion(@WebParam(name = "appId") String appId, @WebParam(name = "appToken") String appToken) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     * @param appId
     * @param appToken
     * @return 
     */
    @WebMethod(operationName = "visitsforRegion")
    public String visitsforRegion(@WebParam(name = "appId") String appId, @WebParam(name = "appToken") String appToken) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     * @param Beacons
     * @return 
     */
    @WebMethod(operationName = "getCampains")
    public String getCampains(@WebParam(name = "Beacons") String Beacons) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "deleteCampain")
    public String deleteCampain(@WebParam(name = "name") String name) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "editCampain")
    public String editCampain(@WebParam(name = "name") String name) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "createNewCampain")
    public String createNewCampain(@WebParam(name = "title") String title, @WebParam(name = "content") String content, @WebParam(name = "featuredImage") String featuredImage, @WebParam(name = "beacons") String beacons, @WebParam(name = "tags") String tags) {
        //TODO write your implementation code here:
        return null;
    }
}
