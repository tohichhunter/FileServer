<%-- 
    Document   : index
    Created on : 5 вер. 2016, 18:02:15
    Author     : toxa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
  
    <head>
        <title>Login</title>
        <meta charset="UTF-8">       
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/bootstrap.css" media="screen">
        <link rel="shortcut icon" href="img/arts0150.png">
    </head>
    <body style="padding-left:60px;padding-right:60px;">
        <script src="http://cdn.jsdelivr.net/webshim/1.12.4/extras/modernizr-custom.js"></script>
<script src="http://cdn.jsdelivr.net/webshim/1.12.4/polyfiller.js"></script> 
<script>
  webshims.setOptions('waitReady', false);
  webshims.setOptions('forms-ext', {types: 'date'});
  webshims.polyfill('forms forms-ext');
</script>
        <div class="panel panel-default">
            <div class="panel-heading"><h1>Log in</h1></div>
      <div class="panel-body">
          <form id="form" name="form" action="Login" method="POST" onsubmit="return isValidForm()" >
           
            <div ><p>e-mail</p></div>
            <input id="email" name="email" type="email"  value=""/><BR>
            <div ><p>Password</p></div>
            <input id="password" name="password" type="password" value=""/><BR><BR>
           <input  class="btn btn-primary btn-lg" id="b1" type="submit" name="button" value="Log in"/> 
          </form>
          <BR>
          <a href="Registration.html">Not registered yet?</a>
      </div>
      </div>
        
        <script src="http://code.jquery.com/jquery-latest.min.js" ></script>
        <script>
        function isValidForm()
        {
            if(document.getElementById("email").value=="")
                 {
                     document.getElementById("email").style.backgroundColor="coral";
                      return false;
                 } else  document.getElementById("email").style.backgroundColor="white";
            if(document.getElementById("password").value=="")
                 {
                     document.getElementById("password").style.backgroundColor="coral";
                      return false;
                 } else  document.getElementById("password").style.backgroundColor="white";
                 return true;
        }
            
        </script>
    </body>
</html>
