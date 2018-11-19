<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "ex" uri = "/WEB-INF/custom.tld"%>
<%@ page import="entities.User" %>
<%@ page import="entities.Listing" %>
<!DOCTYPE html>
<html>
<head>
  <title> Select Booking </title>
  <link rel="stylesheet" type="text/css" href ="../css_scripts/TemplateStyle.css">
  <link rel="stylesheet" type="text/css" href ="../css_scripts/Select_Booking.css">
  <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Lato"> 
</head>
<body>

	<% session.setAttribute("current_page","/jsp_scripts/Select_Booking.jsp"); %>
    
    <% User user = (User)session.getAttribute("user");
       Listing listing = (Listing)(session.getAttribute("listing"));
       String error_booking = "false";
       if (session.getAttribute("error_booking") != null) {
       		error_booking = (String)session.getAttribute("error_booking"); 
       }%>
  
  <ex:Template user ="<%= user %>" current_page ="/jsp_scripts/Host_Listing_Details.jsp"></ex:Template>

  <h1> Booking </h1> <br>
  <form id="bookingForm" method="post" action="../New_booking_recommend" target="_top">
  		<label style="margin-left: 2%;"> Price : <span style="color: white;"> <% out.print(listing.getPrice()); %> </span> </label> <br> <br> <br>
		<label style="margin-left: 2%;"> Date From : </label> <br>
  	  	<input  class="hostChangeInput" name='date1' type='date'/> <br> <br>
  	  	<label style="margin-left: 2%;"> Date To :</label> <br> 
  	  	<input  class="hostChangeInput" name='date2' type='date'/> <br> <br>
  	  	<label style="margin-left: 2%;"> Guests :</label> <br> 
  	  	<input  class="hostChangeInput" name='accommodates' type='text'/> <br> <br>
    	<input  class="hostChangeInput" type="submit" name="submit" value="Submit" /> 
  </form>
  
  <% if (error_booking.equals("true")) {  %>
  	<span style="position: absolute; top:200px; left:500px; color:red; font-size: 25px;"> The listing you've selected is not available for these dates. </span>
  <% } %>
</body>
</html>