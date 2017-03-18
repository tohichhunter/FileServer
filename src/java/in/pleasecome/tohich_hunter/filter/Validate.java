/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.pleasecome.tohich_hunter.filter;

/**
 *
 * @author toxa
 */
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.SimpleDateFormat;

public class Validate
 {
String first="",last="",email="",password="",birthdate=""; 
   static final String JDBC_DRIVER = "org.postgresql.Driver";  
   static final String DB_URL = "jdbc:postgresql://localhost:5432/filter";

   //  Database credentials
   static final String USER = "postgres";
   static final String PASS = "ilovedicks";
   String hashof(String plainText)
    {
         MessageDigest m=null;
        try
        {
            m= MessageDigest.getInstance("MD5");
        }
        catch(NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
m.reset();
m.update(plainText.getBytes());
String hash = new BigInteger(1,m.digest()).toString(16);
while(hash.length() < 32 ){
  hash = "0"+hash;
}
        return hash;
    }
    
   
   
   public boolean Log(String mail, String pass){
   Connection conn = null;
   Statement stmt = null;
   
   boolean out=false;
   try{
      //STEP 2: Register JDBC driver
      Class.forName(JDBC_DRIVER);

      //STEP 3: Open a connection     
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
           
      //STEP 4: Execute a query
      stmt = conn.createStatement();
      
      String sql = "SELECT first, last, birthdate, password FROM persons WHERE email='"+mail+"';";
      ResultSet rs = stmt.executeQuery(sql);
      //STEP 5: Extract data from result set
     if(rs.next())
          if(hashof(pass).equals(rs.getString("password"))) 
          {
              out=true;
              first=rs.getString("first");
              last=rs.getString("last");
              birthdate=rs.getString("birthdate");
          };
         
       
     
      rs.close();
      
      
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
            conn.close();
      }catch(SQLException se){
      }// do nothing
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }
   return out;
   }//end try
   
   }}
