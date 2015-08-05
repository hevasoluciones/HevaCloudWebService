/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hevasoluciones.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



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
  

public ResultSet runSQLInsert(String sql){

ResultSet result;
   try {
      Statement sentencia = connection.createStatement();
      result = sentencia.executeQuery(sql);
   } catch (SQLException ex) {
      return null;
   }
 
   return result;
}

public void closeConnection(){

     try {
            connection.close();
            
        } catch (SQLException ex) {
            System.out.println(" ... FAIL");
           
        }
    

}
}
