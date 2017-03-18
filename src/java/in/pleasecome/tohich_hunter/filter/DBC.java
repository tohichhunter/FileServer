/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.pleasecome.tohich_hunter.filter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;
import java.text.SimpleDateFormat;

/**
 *
 * @author toxa
 */
public class DBC {

    /**
     * @param args the command line arguments
     */
   String name="",last="",email="",password="",birthdate=""; 
   static final String JDBC_DRIVER = "org.postgresql.Driver";  
   static final String DB_URL = "jdbc:postgresql://localhost:5432/filter";

   //  Database credentials
   static final String USER = "postgres";
   static final String PASS = "ilovedicks";
   
     public  boolean Reg(String name, String last, String email, String password, String birthdate) {
     boolean out=false;
     this.birthdate=birthdate;
     this.email=email;
     this.last=last;
     this.name=name;
     this.password=password;
   Connection conn = null;
   Statement stmt = null;
   try{
      //STEP 2: Register JDBC driver
      Class.forName(JDBC_DRIVER);

      //STEP 3: Open a connection
     
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      //STEP 4: Execute a query
     
      stmt = conn.createStatement();
      
      String sql = "";//CREATE DATABASE STUDENTS ";
     
        SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd" );
        java.util.Date jDate =  format.parse(birthdate);
java.sql.Date sqlDate = new java.sql.Date(jDate.getTime());
         System.out.println("Creating info...");
          sql = "INSERT INTO Persons (first, last, birthdate, password,email) VALUES('"+name+"','"+last+"','"+
                  sqlDate+"','"+password+"','"+email+"');";
      stmt.executeUpdate(sql);
     out=true;
     
   }catch(SQLException se){
      //Handle errors for JDBC
      
      se.printStackTrace();
     
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
     
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            stmt.close();
         
      }catch(SQLException se2){
      }// nothing we can do
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
         
      }//end finally try
      return out;
   }//end try
  }//end Reg
   
   
   public  boolean Img(String path, String emailID) throws  SQLException{
     boolean out=false;
   Connection conn = null;
   Statement stmt = null;
   try{
      //STEP 2: Register JDBC driver
      Class.forName(JDBC_DRIVER);

      //STEP 3: Open a connection
     
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      //STEP 4: Execute a query
     
      stmt = conn.createStatement();
      
      String  sql = "INSERT INTO images (image_path, user_id) VALUES('"+path+"','"+emailID+"');";
      stmt.executeUpdate(sql);
     out=true;
     
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
     
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            stmt.close();
         
      }catch(SQLException se2){
      }// nothing we can do
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
         
      }//end finally try
      return out;
   }//end try
  }//end Reg
}//end JDBCExample