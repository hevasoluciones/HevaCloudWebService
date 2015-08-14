/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hevasoluciones.db;

import com.hevasoluciones.shared.Beacon;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author rafael.rosabal@gmail.com
 */
public class ConnectionDB {
    

 private Connection connection;
 
 public Connection getConnection()
{
   return this.connection;
}
 
 
public boolean createConnection()
{
   try {
      Class.forName("com.mysql.jdbc.Driver");
      connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hevaCloud","root","");
   } catch (SQLException | ClassNotFoundException ex) {
      return false;
   }
 
   return true;
} 
 
  
 public boolean runSQL(String sql)
{
   try {
      Statement statement = connection.createStatement();
      statement.executeUpdate(sql);
   } catch (SQLException ex) {
      return false;
   }
 
   return true;
}  
  
 
public ResultSet runSQLSelect(String sql)
{
   ResultSet result;
   try {
      Statement sentencia = connection.createStatement();
      result = sentencia.executeQuery(sql);
   } catch (SQLException ex) {
      return null;
   }
 
   return result;
} 
  

public synchronized int insertCampain(String title,String Content,String featuredImage) {
    int id = 0;
       try {
            String sql =
                    "INSERT INTO hevacloud.campain ("
                    + " title,"
                    + " content,"
                    + " featuredImage) "
                    + "VALUES (?,?,?)";

            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
           
            ps.setString(1, Content);
            ps.setString(2, title);
            ps.setString(3, featuredImage);
            

            ps.executeUpdate();
            
             ResultSet  generatedKeys = ps.getGeneratedKeys();
           if (generatedKeys.next()) {
                 id =generatedKeys.getInt(1);
             }
            ps.close();
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }

       
      
      return id;
    
      


} 

public synchronized String insertBeacon(String idBeacon,String uuid,long major,long minor,String mac,String color ,String name,String icon) {
    String id = null;
       try {
            String sql =
                    "INSERT INTO hevacloud.beacon ("
                     + " mac,"
                     + " idBeacon,"
                     + " uuid,"
                     + " major,"
                     + " minor,"
                     + " color,"
                     + " name,"
                     + " icon) "
                    + "VALUES (?,?,?,?,?,?,?,?)";

            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            ps.setString(1, mac);
            ps.setString(2, idBeacon);
            ps.setString(3, uuid);
            ps.setLong(4, major);
            ps.setLong(5, minor);
            ps.setString(6, color);
            ps.setString(7, name);
            ps.setString(8, icon);
           
            

            ps.executeUpdate();
            
             ResultSet  generatedKeys = ps.getGeneratedKeys();
           if (generatedKeys.next()) {
                 id = String.valueOf(generatedKeys.getInt(1));
             }
            ps.close();
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }

       
      
      return id;
    
      


} 

public synchronized String insertTag(String tagName) {
    String id = null;
       try {
            String sql =
                    "INSERT INTO hevacloud.tags ( Name ) "
                    + "VALUE (?)";

            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
           
            ps.setString(1, tagName);
           
          
            

            ps.executeUpdate();
            
             ResultSet  generatedKeys = ps.getGeneratedKeys();
           if (generatedKeys.next()) {
                 id = String.valueOf(generatedKeys.getInt(1));
             }
            ps.close();
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }

       
      
      return id;
    
      


}

public synchronized String insertCamapinHasBeacons(String mac,int idCampain) {
    String id = null;
       try {
            String sql =
                    "INSERT INTO hevacloud.campain_has_beacon ("
                    + " Campain_idCampain,"
                    + " Beacon_mac) "
                    + "VALUES (?,?)";

            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
           
            ps.setInt(1, idCampain);
            ps.setString(2, mac);
          
            

            ps.executeUpdate();
            
             ResultSet  generatedKeys = ps.getGeneratedKeys();
           if (generatedKeys.next()) {
                 id = String.valueOf(generatedKeys.getInt(1));
             }
            ps.close();
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }

       
      
      return id;
    
      


}

public synchronized String removeCamapinHasBeacons(String mac,int idCampain) {
    String id = null;
       try {
            String sql =
                    "  DELETE FROM  hevacloud.campain_has_beacon WHERE (Campain_idCampain = ?  AND  Beacon_mac = ?)";

            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
           
            ps.setInt(1, idCampain);
            ps.setString(2, mac);
          
            
      int result = -1;
            result = ps.executeUpdate();
            ps.close();
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }

        return "beacon associated with campaign was successfully deleted";

       
      
 
    
      


}
public synchronized String insertCamapinHasTag(int idTag,int idCampain) {
    String id = null;
       try {
            String sql =
                    "INSERT INTO hevacloud.campain_has_tags ("
                    + " Campain_idCampain,"
                    + " Tags_idTags) "
                    + "VALUES (?,?)";

            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
           
            ps.setInt(1, idCampain);
            ps.setInt(2, idTag);
          
            

            ps.executeUpdate();
            
             ResultSet  generatedKeys = ps.getGeneratedKeys();
           if (generatedKeys.next()) {
                 id = String.valueOf(generatedKeys.getInt(1));
             }
            ps.close();
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }

       
      
      return id;
    
      


}

 public synchronized boolean deleteCampain(int idCampain) {
     
     try {
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(
                    "DELETE FROM hevacloud.campain "
                    + " WHERE(`campain`.`idCampain` = ?)");
            ps.setInt(1, idCampain);
          
            int result = -1;
            result = ps.executeUpdate();//devuelve filas borradas
            ps.close();
        } catch (SQLException sqle) {
            
            throw new RuntimeException(sqle);
            
        }
 
       return true;
 }
 
  public synchronized void deleteBeacon(String idBeacon) {
     
     try {
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(
                    "DELETE FROM hevacloud.beacon "
                    + " WHERE((`beacon`.`idBeacon` = ?)");
            ps.setString(1, idBeacon);
          
            int result = -1;
            result = ps.executeUpdate();//devuelve filas borradas
            ps.close();
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
 
 
 }
  
    public synchronized void deleteTag(int idTag) {
     
     try {
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(
                    "DELETE FROM hevacloud.tags  WHERE(`tags`.`idTag` = ?)");
            ps.setInt(1, idTag);
          
            int result = -1;
            result = ps.executeUpdate();//devuelve filas borradas
            ps.close();
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
 
 
 }
    
   public synchronized String editCampain(String title,String Content,String featuredImage,int id) throws SQLException {
     
      String sql = "UPDATE hevacloud.campain "
                    + "  SET title = '" + title
                    + "', content = '"  + Content
                    + "', featuredImage = '" + featuredImage
                  
                    + "'  WHERE (`campain`.`idCampain` = '" + id + "')";
     
      try {
       Statement ps = connection.createStatement();
            int rows = ps.executeUpdate(sql);
             ps.close();

           
   
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }

        return "successfully edition";
    
 
 
 }   
   
 public synchronized ArrayList<Beacon> getBeaconsByCampin(int idCampain) throws SQLException  {
     
 ArrayList<Beacon> bl= new ArrayList<Beacon>();
 Beacon b= new Beacon();
     
 String sql="Select Beacon_mac" + " FROM hevacloud.campain_has_beacon" + " WHERE (" + idCampain + " = Campain_idCampain)";   
            
            Statement ps = connection.createStatement();
     
            ResultSet bidRs = ps.executeQuery(sql);
            
            
            while (bidRs.next()) {
                String mac = bidRs.getString("Beacon_mac");
               
               String query="Select *  FROM hevacloud.beacon WHERE(`beacon`.mac = '" + mac +  "' ) "; 
               
              
               Statement ps2 = connection.createStatement();
     
               ResultSet blRs = ps2.executeQuery(query);
                while (blRs.next()) {
                    b.setMac(blRs.getString("mac"));
                    b.setId(blRs.getString("idBeacon"));
                    b.setUuid(blRs.getString("uuid"));
                    b.setMajor(blRs.getLong("major"));
                    b.setMinor(blRs.getLong("minor"));
                    b.setColor(blRs.getString("color"));
                    b.setName(blRs.getString("name"));
                    b.setIcon(blRs.getString("icon"));
                    
                    bl.add(b);
                    b= new Beacon();
                
                
                }
              ps2.close();  
            }
        
        ps.close();
    


   return bl;
    
 
 }
 
 public synchronized ArrayList<String> getTagsByCampin(int idCampain) throws SQLException  {
     
 ArrayList<String> taglist= new ArrayList<String>();
 String b;
     
 String sql="Select Tags_idTags " + " FROM hevacloud.campain_has_tags" + " WHERE ('" + idCampain + "' = campain_has_tags.Campain_idCampain)";   
            
            Statement ps = connection.createStatement();
     
            ResultSet tagidRs = ps.executeQuery(sql);
            
            
            while (tagidRs.next()) {
                int tid = tagidRs.getInt("Tags_idTags");
               
               String query="Select Name " + " FROM hevacloud.tags" + " WHERE ('" + tid + "' = tags.idTags)";   
               
               Statement ps2 = connection.createStatement();
               ResultSet blRs = ps2.executeQuery(query);
                while (blRs.next()) {
                
                   
                    b= blRs.getString("Name");
                   
                    
                    taglist.add(b);
                
                
                }
                   ps2.close();
            }
  
            
            ps.close();
        
    


   return taglist;
    
 
 }

public void closeConnection(){

     try {
            connection.close();
            
        } catch (SQLException ex) {
            System.out.println(" ... FAIL");
           
        }
    

}

    public String removeCamapinHastag(int oldtag, int idCampain) {
     String id = null;
       try {
            String sql =
                    "  DELETE FROM  hevacloud.campain_has_tags WHERE (Campain_idCampain = ?  AND  Tags_idTags = ?)";

            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
           
            ps.setInt(1, idCampain);
            ps.setInt(2, oldtag);
          
            
      int result = -1;
            result = ps.executeUpdate();
            ps.close();
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }

        return "tag associated with campaign was successfully deleted";   
    
    }
    
    public synchronized ArrayList<Beacon> getBeacons() {
    String id = null;
    ArrayList<Beacon> bl= new ArrayList<Beacon>();
       try {
            String sql =
                    "SELECT "
                     + " mac,"
                     + " idBeacon,"
                     + " uuid,"
                     + " major,"
                     + " minor,"
                     + " color,"
                     + " name,"
                     + " icon "
                    + "FROM beacon ";

           Statement ps2 = connection.createStatement();
            ResultSet blRs = ps2.executeQuery(sql);
            while (blRs.next()) {
                Beacon b = new Beacon();
                    b.setMac(blRs.getString("mac"));
                    b.setId(blRs.getString("idBeacon"));
                    b.setUuid(blRs.getString("uuid"));
                    b.setMajor(blRs.getLong("major"));
                    b.setMinor(blRs.getLong("minor"));
                    b.setColor(blRs.getString("color"));
                    b.setName(blRs.getString("name"));
                    b.setIcon(blRs.getString("icon"));

               bl .add(b);
            }
            ps2.close();


        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return bl;
      


} 

    public ArrayList<String> getTags() throws SQLException {

        ArrayList<String> taglist = new ArrayList<>();
        String query = "Select Name " + " FROM hevacloud.tags";

        Statement ps2 = connection.createStatement();
        ResultSet tRs = ps2.executeQuery(query);
        while (tRs.next()) {

            String tag = tRs.getString("Name");
            taglist.add(tag);
        }

        return taglist;
    }
    
 
  public int giveMeTheTagIfThere(String nameTag) throws SQLException{
    
      int tag = 0 ;
      
       String query="Select idTags " + " FROM hevacloud.tags" + " WHERE ('" + nameTag + "' = tags.Name)";   
              Statement ps = connection.createStatement();
       ResultSet blRs = ps.executeQuery(query);
                while (blRs.next()) {
                
                   
          tag = blRs.getInt("idTags");
                   
                    
                   
                
                
                
            }
  
            
            ps.close();
        
    


    return tag;
  
  
  
  }  
  
  
}
