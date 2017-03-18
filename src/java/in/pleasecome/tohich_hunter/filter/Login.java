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
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Login extends HttpServlet {
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        Validate validate = new Validate();
        if(validate.Log(email, pass))
        {
          HttpSession session = request.getSession();
			session.setAttribute("user", email);
			//setting session to expiry in 30 mins
			session.setMaxInactiveInterval(30*60);
			Cookie userName = new Cookie("user", validate.first+" "+validate.last);
                        Cookie userMail = new Cookie("email", email);
                        Cookie userBirth = new Cookie("birth", validate.birthdate);
			userName.setMaxAge(30*60);
                        userBirth.setMaxAge(30*60);
                        userMail.setMaxAge(30*60);
			response.addCookie(userName);
                        response.addCookie(userMail);
                        response.addCookie(userBirth);
                        
                        request.setAttribute("first", validate.first);
                        request.setAttribute("last", validate.last);
                        request.setAttribute("birthdate", validate.birthdate);
                        request.setAttribute("email", email);
                        request.getRequestDispatcher("Logged.jsp").forward(request, response);
			//response.sendRedirect("Logged.jsp");
		}else{
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
			 out= response.getWriter();
			out.println("<font color=red>Either user name or password is wrong.</font>");
			rd.include(request, response);
		}
       
    }
}   