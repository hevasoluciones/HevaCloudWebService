/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hevasoluciones.server;

import com.hevasoluciones.db.ConnectionDB;
import com.hevasoluciones.shared.Beacon;
import com.hevasoluciones.shared.Campains;
import com.hevasoluciones.shared.VisitsRegionSpec;
import com.hevasoluciones.shared.Visits;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 *
 * @author rafael.rosabal@gmail.com
 */
public class CloudMannagerImpl implements CloudMannager{
    
    
    private ConnectionDB cdb;

    @Override
    public ArrayList<Beacon> getBeacon(String appId, String appToken) {
        
        Beacon myBeacons = new Beacon();
        ArrayList<Beacon>myBeaconsList= new ArrayList<Beacon>();
        String beaconsjson = "";
        try {

            Runtime runtime = Runtime.getRuntime();
            Process proc = runtime.exec(" curl -u " + appId + ":" + appToken + " https://cloud.estimote.com/v1/beacons -H \"Accept: application/json");

            InputStream is = proc.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            String line;

            while ((line = br.readLine()) != null) {
                //System.out.println(line);
                beaconsjson += line;
            }

        } catch (IOException ex) {
            Logger.getLogger(CloudMannagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(beaconsjson);
            JSONArray beacon = (JSONArray) obj;

            for (Object pbeacon : beacon) {

                JSONObject obj2 = (JSONObject) pbeacon;
               
                myBeacons.setId( (String)obj2.get("id"));
                myBeacons.setUuid((String) obj2.get("uuid"));
                myBeacons.setMajor((long) obj2.get("major"));
                myBeacons.setMinor((long) obj2.get("minor"));
                myBeacons.setMac((String) obj2.get("mac"));
               // myBeacons.setSettings(obj2.get("settings"));
                myBeacons.setColor((String) obj2.get("color"));
                myBeacons.setName((String) obj2.get("name"));
                myBeacons.setIcon((String) obj2.get("icon"));
               // myBeacons.setBattery_life_expectancy_in_days((int) obj2.get("battery_life_expectancy_in_days"));
                myBeacons.setTags((ArrayList<String>) obj2.get("tags"));
                
                myBeaconsList.add(myBeacons);
                myBeacons = new Beacon();
            }

        } catch (ParseException pe) {
            System.out.println("position: " + pe.getPosition());
            System.out.println(pe);
        }

        return myBeaconsList;

    }

    @Override
    public VisitsRegionSpec uniqueVisitorsforRegion(String appId,String appToken,String uuid) {
       
        
        VisitsRegionSpec uniqueVisitors = new VisitsRegionSpec();
      
        String beaconsjson = "";
        try {

            Runtime runtime = Runtime.getRuntime();
            Process proc = runtime.exec("curl -u" + appId + ":" + appToken+ " https://cloud.estimote.com/v1/analytics/"+uuid+"/visits  -H \"Accept: application/json");

            InputStream is = proc.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            String line;

            while ((line = br.readLine()) != null) {
                //System.out.println(line);
                beaconsjson += line;
            }

        } catch (IOException ex) {
            Logger.getLogger(CloudMannagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        JSONParser parser = new JSONParser();
        beaconsjson="[" + beaconsjson + "]";

        try {
            Object obj = parser.parse(beaconsjson);
            JSONArray beacon = (JSONArray) obj;

            for (Object pbeacon : beacon) {

                JSONObject obj2 = (JSONObject) pbeacon;
                uniqueVisitors.setRegion((String) obj2.get("region"));
                String visists=obj2.get("visits").toString();
                Object obj1 = parser.parse(visists);
                JSONArray v = (JSONArray) obj1;
                Visits vi=new Visits();
                
                for (Object pv : v) {
                    JSONObject obj3 = (JSONObject) pv;
                   
                    vi.setTotal_visits((long)obj3.get("total_visits"));
                    vi.setApp_in_background_visits((long)obj3.get("app_in_foreground_visits"));
                    vi.setApp_in_foreground_visits((long)obj3.get("app_in_background_visits"));
                    vi.setTime((String)obj3.get("time"));
                    uniqueVisitors.visits.add(vi);
                    vi=new Visits();
                }
            
            
            }

        } catch (ParseException pe) {
            System.out.println("position: " + pe.getPosition());
            System.out.println(pe);
        }

        return uniqueVisitors;

    }
    
     @Override
    public VisitsRegionSpec VisitorsforRegion(String appId,String appToken,String uuid,String Major) {
       
        
        VisitsRegionSpec Visitors = new VisitsRegionSpec();
      
        String beaconsjson = "";
        try {

            Runtime runtime = Runtime.getRuntime();
            Process proc = runtime.exec("curl -u" + appId + ":" + appToken+ " https://cloud.estimote.com/v1/analytics/"+uuid +":"+ Major +"/visits -H \"Accept: application/json");

            InputStream is = proc.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            String line;

            while ((line = br.readLine()) != null) {
                //System.out.println(line);
                beaconsjson += line;
            }

        } catch (IOException ex) {
            Logger.getLogger(CloudMannagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        JSONParser parser = new JSONParser();
        beaconsjson="[" + beaconsjson + "]";

        try {
            Object obj = parser.parse(beaconsjson);
            JSONArray beacon = (JSONArray) obj;

            for (Object pbeacon : beacon) {

                JSONObject obj2 = (JSONObject) pbeacon;
                Visitors.setRegion((String) obj2.get("region"));
                String visists=obj2.get("visits").toString();
                Object obj1 = parser.parse(visists);
                JSONArray v = (JSONArray) obj1;
                Visits vi=new Visits();
                
                for (Object pv : v) {
                    JSONObject obj3 = (JSONObject) pv;
                   
                    vi.setTotal_visits((long)obj3.get("total_visits"));
                    vi.setApp_in_background_visits((long)obj3.get("app_in_foreground_visits"));
                    vi.setApp_in_foreground_visits((long)obj3.get("app_in_background_visits"));
                    vi.setTime((String)obj3.get("time"));
                    Visitors.visits.add(vi);
                    vi=new Visits();
                }
             
            }

        } catch (ParseException pe) {
            System.out.println("position: " + pe.getPosition());
            System.out.println(pe);
        }

        return Visitors;

    }

    @Override
    public ArrayList<Campains> getCampains() {
        
        
     ArrayList<Campains> campains= new ArrayList<Campains>();
     Campains campain= new Campains();
     
     cdb= new ConnectionDB();
     
     cdb.createConnection();
     String Sql="select * from campain";
     ResultSet result = cdb.runSQLSelect(Sql);
     
        try {
            while (result.next())
                
            {

                campain.setId(result.getInt ("idCampain"));
                campain.setTitle(result.getString ("title"));
                campain.setContent(result.getString ("content"));
                campain.setFeaturedImage(result.getString ("featuredImage"));
                ArrayList<Beacon> beacons = cdb.getBeaconsByCampin(result.getInt ("idCampain"));
                for(Beacon b: beacons){
                campain.beacons.add(b);
                
                }
                ArrayList<String> tags = cdb.getTagsByCampin(result.getInt ("idCampain"));
                for(String t: tags){
                campain.tags.add(t);
                
                }
                
                campains.add(campain);
                campain= new Campains();
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CloudMannagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    
     cdb.closeConnection();
     return campains;
        
        
    }

    @Override
    public String createNewCampain(String title,String content,String  featuredImage, ArrayList<Beacon> beaconList,ArrayList<String> tags) {
        
        
     
    cdb= new ConnectionDB();
    cdb.createConnection();
    
    
   int idCampain = cdb.insertCampain(title,content,featuredImage);
   
   if(idCampain!=0){
        for (String  tag : tags) {
          int id = 0;  
            try {
                id = cdb.giveMeTheTagIfThere(tag);
            } catch (SQLException ex) {
                Logger.getLogger(CloudMannagerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
       if(id==0){
        String idTag = cdb.insertTag(tag);
        cdb.insertCamapinHasTag(Integer.valueOf(idTag), idCampain);
       }else
       {
        cdb.insertCamapinHasTag(id,idCampain);
       }   
        }
        
        for (Beacon beacon : beaconList) {
            
            
        //String idBeacon = cdb.insertBeacon(beacon.getId(),beacon.getUuid(),beacon.getMajor(),beacon.getMinor(),beacon.getMac(),beacon.getColor(),beacon.getName(),beacon.getIcon());
        cdb.insertCamapinHasBeacons(beacon.getMac(), idCampain);
        
        }  
        
   }else{
   
    return "Campain not created"; 
   }
    
    
    
    
        
    return  "Campain created successfully "; 
        
        
    }

    @Override
    public String editCampain(int idCampain,String title,String content,String  featuredImage, ArrayList<Beacon> beacon,ArrayList<String> tag) {
      cdb= new ConnectionDB();
      cdb.createConnection();
      
      ArrayList<Beacon> bc = new ArrayList<Beacon>();
       ArrayList<String> tbc = new ArrayList<String>();
      
       
        try {
            cdb.editCampain(title,content,featuredImage,idCampain);
        } catch (SQLException ex) {
            Logger.getLogger(CloudMannagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
   
        
        try {
          bc = cdb.getBeaconsByCampin(idCampain);
        } catch (SQLException ex) {
            Logger.getLogger(CloudMannagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    // comprobamos en la nueva lista de beacons hay alguno que no este registrado, si lo esta lo inserto    
        for(Beacon b: beacon){
       
        int count=0;
        
        for(Beacon beaconsByCampain : bc){
        
            if(b.getId() == null ? beaconsByCampain.getId() != null : !b.getId().equals(beaconsByCampain.getId())){
            
              count++;
            }
        
        }
          if(count==0){
              
          String idBeacon = cdb.insertBeacon(b.getId(),b.getUuid(),b.getMajor(),b.getMinor(),b.getMac(),b.getColor(),b.getName(),b.getIcon());
           cdb.insertCamapinHasBeacons(idBeacon,idCampain);
        
          }
        
        }
 //comprobamos si en la nueva lista existen todos los beacons que estaban asociados a la campaña. El que no esta lo eliminamos.       
        
   for(Beacon oldbeacon: bc){
       
        int count=0;
        
        for(Beacon newBeacon : beacon){
        
            if(oldbeacon.getId() == null ? newBeacon.getId() == null : oldbeacon.getId().equals(newBeacon.getId())){
            
              count++;
            }
        
        }
          if(count==0){
              
         
           cdb.removeCamapinHasBeacons(String.valueOf(oldbeacon.getId()), idCampain);
        
          }
        
        }     
        
          try {
          tbc = cdb.getTagsByCampin(idCampain);
        } catch (SQLException ex) {
            Logger.getLogger(CloudMannagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         for(String nameTag: tag){
       
        int count=0;
        
        for(String tagByCampain : tbc){
        
            if(nameTag.equals(tagByCampain)){
            
              count++;
            }
        
        }
          if(count==0){
              
           String idtag = cdb.insertTag(nameTag);
           cdb.insertCamapinHasTag(Integer.valueOf(idtag), idCampain);
        
          }
        
        }
         
         
         for(String oldtag: tbc){
       
        int count=0;
        
        
        for(String newtag : tag){
        
            if(oldtag.equals(newtag)){
            
              count++;
            }
        
        }
          if(count==0){
              
           int id=0;
            try {
                id = cdb.giveMeTheTagIfThere(oldtag);
            } catch (SQLException ex) {
                Logger.getLogger(CloudMannagerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(id!=0)
           cdb.removeCamapinHastag(id, idCampain);
        
          }
        
        }    
        
      return "Number" + idCampain + "Campain successfully edited"; 
    }
    
     @Override
    public String removeCampain(int idCampin) {
       
        cdb= new ConnectionDB();
        cdb.createConnection();
        
        boolean rc = cdb.deleteCampain(idCampin);
        if(rc)
        return "Campain removed successfully"    ;
        else
        return "An error has occurred" ;
    
    
    }

    @Override
    public String insertBeacon(Beacon beacon) {
        cdb= new ConnectionDB();
        cdb.createConnection();
        return cdb.insertBeacon(beacon.getId(),beacon.getUuid(),beacon.getMajor(),beacon.getMinor(),beacon.getMac(),beacon.getColor(),beacon.getName(),beacon.getIcon());
   
    }

    @Override
    public ArrayList<Beacon> getBeaconsfromDB() {
         cdb= new ConnectionDB();
        cdb.createConnection();
        return cdb.getBeacons();
    }

    @Override
    public ArrayList<String> getTags() {
        cdb= new ConnectionDB();
        cdb.createConnection();
        try {
            return cdb.getTags();
        } catch (SQLException ex) {
            Logger.getLogger(CloudMannagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
   
    
}


