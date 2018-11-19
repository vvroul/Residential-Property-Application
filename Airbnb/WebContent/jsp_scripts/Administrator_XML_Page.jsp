<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "ex" uri = "/WEB-INF/custom.tld"%>
<%@ page import="entities.User" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title> Admin Home | Rentvng </title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href ="../css_scripts/TemplateStyle.css">
    <link rel="stylesheet" type="text/css" href ="../css_scripts/Administrator_XML_Page.css">
    <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Lato"> 
    <script type="text/javascript" src="../js_scripts/testFrame.js"></script> 
  </head>
  <body>
  
  	<% session.setAttribute("current_page","/jsp_scripts/Administrator_XML_Page.jsp"); %>
  
  	<% User user = (User)session.getAttribute("user");
  	@SuppressWarnings("unchecked")
    ArrayList<User> users_list = (ArrayList<User>)session.getAttribute("users_list"); %>
    
    <ex:Template user ="<%= user %>"  current_page ="/jsp_scripts/Administrator_XML_Page.jsp"></ex:Template>

    <h1> Choose export type :  </h1>

    <br>
    
    <span style="padding-left: 5em"> <a href="../Xml_listings" class="button sendButton"> Listings </a> </span>
    
	<br> <br>
    
    <span style="padding-left: 5em"> <a href="../Xml_bookings" class="button sendButton"> Bookings </a> </span>
    
    <br> <br>
    
    <span style="padding-left: 5em"> <a href="../Xml_reviews" class="button sendButton"> Reviews </a> </span>
    
    <br> <br>
    
    <form method="post" action="../Xml_reviews_for_a_host">
    	<span style="padding-left: 5em"> 
    		<input type="submit" class="button sendButton" value="Reviews for a host"/> 
    		<input type="text" class="button sendButton" placeholder="Host Id" name="Host_id"/> 
    	</span>
	</form>
	
  </body>
</html>