<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "ex" uri = "/WEB-INF/custom.tld"%>
<%@ page import="entities.User" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title> View User | Rentvng </title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href ="../css_scripts/TemplateStyle.css">
    <link rel="stylesheet" type="text/css" href ="../css_scripts/Administrator_User_Page.css">
    <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Lato"> 
    <script type="text/javascript" src="../js_scripts/testFrame.js"></script> 
  </head>
  <body>

	<% session.setAttribute("current_page","/jsp_scripts/Administrator_User_Page.jsp"); %>  

    <% 
       User user = (User)session.getAttribute("user");  
       String User_id = request.getParameter("User_id");
       String Username = request.getParameter("Username");
       String Name = request.getParameter("Name");
       String Surname = request.getParameter("Surname");
       String Email = request.getParameter("Email");
       String Contact_number = request.getParameter("Contact_number");
       String Admin_level = request.getParameter("Admin_level");
       boolean Verified = Boolean.parseBoolean(request.getParameter("Verified")); 
    %>
    
    <ex:Template user ="<%= user %>" current_page ="/jsp_scripts/Administrator_User_Page.jsp"></ex:Template>
    




	<div id='info'>
	    <label> User ID :  </label> <% out.println(User_id); %><br><br><hr align='left' id='myHR'><br>
	    <label> Username :  </label> <% out.println(Username); %><br><br><hr align='left' id='myHR'><br>
	    <label> Name :  </label> <% out.println(Name); %><br><br><hr align='left' id='myHR'><br>
	    <label> Surname :  </label> <% out.println(Surname); %><br><br><hr align='left' id='myHR'><br>
	    <label> Email :  </label> <% out.println(Email); %><br><br><hr align='left' id='myHR'><br>
	    <label> Contact number :  </label> <% out.println(Contact_number); %><br><br><hr align='left' id='myHR'><br>
	    <label> Admin level :  </label> <% out.println(Admin_level); %><br><br><hr align='left' id='myHR'><br>
	    <label> Verified :  </label> <% out.println(Verified); %><br><br><hr align='left' id='myHR'><br>
    </div>
    
    <div id='theActions'>

    <% if (!Verified) { %>
        <a style='color:darkorange;' href='../Verify_user?User_id=<%out.println(User_id);%>'>Verify User</a>
    <% } %>

    <a style='color:#F7FE2E; padding-left:50px;' href='Administrator_Home_Page.jsp'>Return</a>

    </div>



  </body>
</html>