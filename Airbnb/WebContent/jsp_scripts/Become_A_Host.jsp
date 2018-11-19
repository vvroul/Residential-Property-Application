<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "ex" uri = "/WEB-INF/custom.tld"%>
<%@ page import="entities.User" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title> Become a host | Rentvng </title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href ="../css_scripts/TemplateStyle.css">
    <link rel="stylesheet" type="text/css" href ="../css_scripts/Register_Page.css">
    <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Lato"> 
    <script type="text/javascript" src="../js_scripts/testFrame.js"></script> 
  </head>
  <body>
  
  	<% session.setAttribute("current_page","/jsp_scripts/Register_Page.jsp"); %>
  	<% String register_err_message = (String)session.getAttribute("register_err_message"); 
  	User user = (User)session.getAttribute("user"); %>
  
    <ex:Template user ="<%= user %>"  current_page ="/jsp_scripts/Become_A_Host.jsp"></ex:Template>


    <h1> Become A Host </h1> 

    <br> 

    <br> 
    	
    <div id='errorMessage'> <% if (register_err_message != null) { out.println(register_err_message); session.setAttribute("register_err_message", null); } %></div>
    
    <br> <br>
    

    <div style="padding-left: 5%;">


    <form style="padding-left: 7.5%;" method="post" action="../Register_host">
        <label> Location :</label>  <label style="padding-left: 14.2%;"> About : </label> <label style="padding-left: 15.5%;"> Response Time : </label> <br>
        <input type="text" name="Host_location" />
        <input style="margin-left: 5%;" type="text" name="Host_about" />
        <input style="margin-left: 5%;" type="text" name="Host_responce_time" /> <br> <br>
        <label> Superhost :</label> <label style="padding-left: 13.8%;">  Neighbourhood :</label>  <label style="padding-left: 10.2%;"> Verifications : 
        </label> 
        <br> 
        <input type="text" name="Host_is_superhost" />
        <input style="margin-left: 5%;" type="text" name="Host_neighbourhood" />
        <input style="margin-left: 5%;" type="text" name="Host_verifications" /> 
        <br> <br>
        <input class="regiSubmit" type="submit" value="Proceed" name="submitRegi" /><br />
    </form>
    </div>    
 
  </body>
</html>