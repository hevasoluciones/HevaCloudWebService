/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hevasoluciones.server;

import com.hevasoluciones.db.ConnectionDB;
import com.hevasoluciones.shared.Beacon;
import com.hevasoluciones.shared.Campains;
import com.hevasoluciones.shared.VRFields;
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
    public Beacon getBeacon(String appId, String appToken) {
        
        Beacon myBeacons = new Beacon();
        String beaconsjson = "";
        try {

            Runtime runtime = Runtime.getRuntime();
            Process proc = runtime.exec(" curl -u " + appId + ":" + appToken + "\\\n"
                    + "         -H 'Accept: application/json' \\\n"
                    + "         https://cloud.estimote.com/v1/beacons");

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
                myBeacons.setId((int) obj2.get("id"));
                myBeacons.setUuid((String) obj2.get("uuid"));
                myBeacons.setMajor((String) obj2.get("major"));
                myBeacons.setMinor((String) obj2.get("minor"));
                myBeacons.setMac((String) obj2.get("mac"));
                myBeacons.setSettings(obj2.get("settings"));
                myBeacons.setColor((String) obj2.get("color"));
                myBeacons.setName((String) obj2.get("name"));
                myBeacons.setIcon((String) obj2.get("icon"));
                myBeacons.setBattery_life_expectancy_in_days((int) obj2.get("battery_life_expectancy_in_days"));
                myBeacons.setTags((ArrayList) obj2.get("tags"));

            }

        } catch (ParseException pe) {
            System.out.println("position: " + pe.getPosition());
            System.out.println(pe);
        }

        return myBeacons;

    }

    @Override
    public VRFields uniqueVisitorsforRegion(String appId,String appToken,String uuid,String Major) {
       
        
        VRFields uniqueVisitors = new VRFields();
        String beaconsjson = "";
        try {

            Runtime runtime = Runtime.getRuntime();
            Process proc = runtime.exec("curl -u" + appId + ":" + appToken+ " https://cloud.estimote.com/v1/analytics/"+uuid +":"+ Major +"/visits \\\n" +
" -H \"Accept: application/json\"");

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
                   
                    vi.setTotal_visits((int)obj3.get("total_visits"));
                    vi.setApp_in_background_visits((int)obj3.get("app_in_foreground_visits"));
                    vi.setApp_in_foreground_visits((int)obj3.get("app_in_background_visits"));
                    vi.setTime((String)obj3.get("time"));
                    uniqueVisitors.visits.add(vi);
                    
                }

            }

        } catch (ParseException pe) {
            System.out.println("position: " + pe.getPosition());
            System.out.println(pe);
        }

        return uniqueVisitors;

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
                campain.setTitle(result.getString ("content"));
                campain.setTitle(result.getString ("featuredImage"));
                ArrayList<Beacon> beacons = cdb.getBeaconsByCampin(result.getInt ("idCampain"));
                for(Beacon b: beacons){
                campain.beacons.add(b);
                
                }
                ArrayList<String> tags = cdb.getTagsByCampin(result.getInt ("idCampain"));
                for(String t: tags){
                campain.tags.add(t);
                
                }
                
                campains.add(campain);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CloudMannagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    
     cdb.closeConnection();
     return campains;
        
        
    }

    @Override
    public String createNewCampain(Campains campain) {
     
    cdb= new ConnectionDB();
    cdb.createConnection();
    String idTag,idBeacon;
    
   String idCampain = cdb.insertCampain(campain.getTitle(),campain.getContent(),campain.getFeaturedImage());
   
   if(idCampain!=null){
        for (Object tag : campain.getTags()) {
            
            
        idTag=cdb.insertTag(tag.toString());
        cdb.insertCamapinHasTag(idTag, idCampain);
        
        }
        
        for (Object beacon : campain.getBeacons()) {
            
            
        idBeacon = cdb.insertTag(beacon.toString());
        cdb.insertCamapinHasBeacons(idBeacon, idCampain);
        
        }  
        
   }
    
    
    
    
        
    return "Number" + idCampain + "Campain successfully created"; 
        
        
    }

    @Override
    public String editCampain(int idCampin) {
       
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    
    
    }
    
     @Override
    public String removeCampain(int idCampin) {
       
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    
    
    }
    
    
   
    
}


