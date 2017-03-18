/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.pleasecome.tohich_hunter.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author toxa
 */
public class Registration extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
           String password = request.getParameter("password");
           String name=  request.getParameter("name");
           String year=  request.getParameter("year");
           String last=  request.getParameter("last");
           String email= request.getParameter("email");
           DBC main =new DBC();
           
          if(main.Reg(name,last,email,hashof(password),year)){        
            request.setAttribute("name", name);         
            request.getRequestDispatcher("index.jsp").forward(request, response);
          }
          else {
              RequestDispatcher rd = getServletContext().getRequestDispatcher("/Registration.html");
			out= response.getWriter();
			out.println("<font color=red>User with the same e-mail already exists.</font>");
			rd.include(request, response);
          }
    }

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
}
