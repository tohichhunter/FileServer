<%-- 
    Document   : Logged
    Created on : 4 вер. 2016, 20:49:45
    Author     : toxa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gallery page</title>
        <link rel="stylesheet" href="css/bootstrap.css" media="screen">
        <link rel="shortcut icon" href="img/arts0150.png">
    </head>
    <body style="padding-left:60px;padding-right:60px;">
        <%
     //allow access only if session exists
            String user = null;
            if (session.getAttribute("user") == null)
            {
                response.sendRedirect("index.jsp");
            } else
            {
                user = (String) session.getAttribute("user");
            }
            String userName = null;
            String sessionID = null;
            String userMail = null;
            String userBirth = null;
            Cookie[] cookies = request.getCookies();
            if (cookies != null)
            {
                for (Cookie cookie : cookies)
                {
                    if (cookie.getName().equals("user"))
                    {
                        userName = cookie.getValue();
                    }
                    if (cookie.getName().equals("JSESSIONID"))
                    {
                        sessionID = cookie.getValue();
                    }
                    if (cookie.getName().equals("birth"))
                    {
                        userBirth = cookie.getValue();
                    }
                    if (cookie.getName().equals("email"))
                    {
                        userMail = cookie.getValue();
                    }
                }
            }
        %>
        <div class="panel panel-default">
            <div class="panel-heading">
                <h1>Welcome </h1> 
                <div class="row">
                    <div class="col-sm-2">
                        <p><%=userName%></p>
                        <p id="email" > <%=userMail%></p>
                        <p><%=userBirth%></p>
                    </div>
                    <div class="col-sm-2">
                        <form id="form"  action="LogoutServlet" method="GET">
                            <input type="submit" class="btn btn-danger" name="logout" value="log out"/>
                        </form>
                    </div>
                </div>

            </div>

            <div class="panel-body">
                <div class="row">
                    <div class="col-sm-4">

                        <form action="StoreServlet"  method="post" enctype="multipart/form-data" name="form1" id="form1">
                            <table border="1">
                                <tr>
                                    <td align="center"><b>Files Upload form</td>
                                </tr>
                                <tr>
                                    <td >
                                        <div class="input-group">
                                            <label class="input-group-btn">
                                                <span class="btn btn-default">
                                                    Browse&hellip; <input style="display: none" name="file"  type="file" id="file" multiple>
                                                </span>
                                            </label>
                                            <input type="text" class="form-control" readonly>
                                        </div>

                                    </td>
                                </tr>
                                <tr>
                                    <td align="center">
                                        <input id="in" class="btn btn-primary" type="submit" name="Submit" value="Submit files"/>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>
                    <div class="col-sm-6">
                        <img id="big" class="img-rounded" src="img/1c.gif"  height="300" onClick="right();"><BR><BR>
                        <div class="row">
                            <div class="col-sm-5" ><button class="btn btn-success" onclick="left();" id="left">Left</button></div>
                            <div class="col-sm-5" ><button class="btn btn-success" onclick="right();" id="right">Right</button></div><BR><BR>
                        </div>     
                        <div id="d0" class="row">
                            <!-- here appends imgs -->

                        </div>
                    </div>
                </div>
                <a href="CheckoutPage.jsp">Checkout Page</a>
                <div id="q"></div>
            </div>
        </div>
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
        <script>
               var current = 0, quant = 0, size = 1, msg = "msg", arr;
               var images = document.getElementsByTagName("img");

               $(document).ready(grant());

               function slider(el) {
                   for (i = 0; i < images.length; i++)
                       if (el == images[i])
                           current = i - 1;
                   var source = document.getElementById(el.id).getAttribute('src');
                   document.getElementById('big').setAttribute('src', source);
               }
               ;

               function grant()
               {
                   $.post("RestoreServlet", msg, function (data)
                   {

                       arr = JSON.parse(data);
                       size = arr.length;

                       for (var i = 0; i < arr.length; i++) {

                           var mydiv = document.getElementById("d0");
                           var imag = document.createElement('img');
                           var clasdiv = document.createElement('div');
                           imag.setAttribute("src", arr[i]);
                           imag.setAttribute("id", "i" + i);
                           imag.setAttribute("width", "120");
                           imag.setAttribute("class", "img-rounded");
                           //imag.setAttribute("height","120");
                           imag.setAttribute("onClick", "slider(this);");
                           clasdiv.className = "col-sm-3";
                           clasdiv.appendChild(imag);
                           mydiv.appendChild(clasdiv);
                       }
                       ;
                       if (arr.length > 0)
                           document.getElementById("big").src = arr[0];
                       for (i = 0; i < images.length; i++) {
                           images[i].style.cursor = "pointer";
                       }

                   }, "text");


               }
               function left()
               {
                   if (current > 0)
                       big.src = arr[--current];

               }
               var big = document.getElementById("big");
               function right()
               {
                   if (current < size - 1)
                       big.src = arr[++current];

               }
               $(function () {

                   // We can attach the `fileselect` event to all file inputs on the page
                   $(document).on('change', ':file', function () {
                       var input = $(this),
                               numFiles = input.get(0).files ? input.get(0).files.length : 1,
                               label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
                       input.trigger('fileselect', [numFiles, label]);
                   });

                   // We can watch for our custom `fileselect` event like this
                   $(document).ready(function () {
                       $(':file').on('fileselect', function (event, numFiles, label) {

                           var input = $(this).parents('.input-group').find(':text'),
                                   log = numFiles > 1 ? numFiles + ' files selected' : label;

                           if (input.length) {
                               input.val(log);
                           } else {
                               if (log)
                                   alert(log);
                           }

                       });
                   });

               });
        </script>
    </body>
</html>
