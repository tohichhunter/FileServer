<%-- 
    Document   : ChekoutPaje
    Created on : 6 ???. 2016, 18:29:21
    Author     : toxa
--%>

<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Checkout page</title>
<link link rel="stylesheet" href="css/bootstrap.css" media="screen">
        <link rel="shortcut icon" href="img/arts0150.png">
</head>
<body style="padding-left:60px;padding-right:60px;">
<%
//allow access only if session exists
if(session.getAttribute("user") == null){
	response.sendRedirect("index.jsp");
}
String userName = null;
String sessionID = null;
Cookie[] cookies = request.getCookies();
if(cookies !=null){
for(Cookie cookie : cookies){
	if(cookie.getName().equals("user")) userName = cookie.getValue();
}
}
%>
<h3>Hi <%=userName %>, do the checkout.</h3>
<br>
<form action="LogoutServlet" method="post">
<input class="btn btn-danger" type="submit" value="Logout" >
</form>
</body>
</html>