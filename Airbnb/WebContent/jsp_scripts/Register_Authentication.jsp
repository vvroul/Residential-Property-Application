<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "ex" uri = "/WEB-INF/custom.tld"%>
<%@ page import="entities.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
    <title> Awaiting Authentication | Rentvng </title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href ="../css_scripts/TemplateStyle.css">
    <link rel="stylesheet" type="text/css" href ="../css_scripts/Register_Authentication.css">
    <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Lato"> 
    <script type="text/javascript" src="../js_scripts/testFrame.js"></script> 
  </head>
  <body>
  
  	<% session.setAttribute("current_page","/jsp_scripts/Register_Authentication.jsp"); %>

    <% User user = (User)session.getAttribute("user"); %>
    <ex:Template user ="<%= user %>" current_page ="/jsp_scripts/Register_Authentication.jsp"></ex:Template>

    <h1> Valid credentials </h1>  
    <h2> Awaiting authentication from an administrator </h2>

    <br> <br> <br>

    <br> <br> 

    <p id="countText" style="padding-right: 10%;"> Better visit the home page! <p> 

    <br> 
    
    

  </body>
</html>