<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "ex" uri = "/WEB-INF/custom.tld"%>
<%@ page import="entities.User" %>
<%@ page import="entities.Listing" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title> Host Page | Rentvng </title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href ="../css_scripts/TemplateStyle.css">
    <link rel="stylesheet" type="text/css" href ="../css_scripts/Host_Page.css">
    <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Lato"> 
    <script type="text/javascript" src="../js_scripts/testFrame.js"></script>
    <script type="text/javascript" src="../js_scripts/angular.min.js"></script>
    <script type="text/javascript" src="../js_scripts/jquery-3.2.1.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
    <script src="../js_scripts/myApp.js"></script>
    <script src="../js_scripts/jquery.mixitup.min.js"></script>
    <script src="../js_scripts/main.js"></script> <!-- Resource jQuery -->
  </head>
  <body>


	<% session.setAttribute("current_page","/jsp_scripts/Host_Page.jsp"); %>
    
    <% User user = (User)session.getAttribute("user");
       @SuppressWarnings("unchecked")
       ArrayList<Listing> listings = (ArrayList<Listing>)(session.getAttribute("listings"));
       
  	%>
       
    <ex:Template user ="<%= user %>" current_page ="/jsp_scripts/Host_Page.jsp"></ex:Template>
    
    
    <% if (!user.getVerified()) {
    	String site = new String("/Airbnb/jsp_scripts/Register_Authentication.jsp");
        response.setStatus(response.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", site); 
    }
    %>
   
	<br> <br>
	
	<span id="new_listing"><a href="Host_New_Listing.jsp" style="color: white;">New Listing</a></span>  <br> <br>
	
    	<div id="map"></div>
       	<script type="text/javascript" src = "../js_scripts/mapShow.js"></script>
       	<script> FillLocations(<% out.println(getArrayString(listings)); %>) </script>
       	<script> FillNames(<% out.println(getArrayNames(listings)); %>) </script>
       	<script async defer
       		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyClU3fGihtFNeHlr1Vj96OR0OcGXIkdlnE&callback=initMap">
       	</script>
       <script 
       		src="https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/markerclusterer.js">
       </script>
       <br> <br>
    	<label id="listingLabel">Listings :</label> <br> <br>
    	<% for (Listing listing : listings) { %>
	  	  <form method='post' action='../Host_listing_details'>
    	  	  <input class="myListings" name='Listing_id' type='hidden' value=<% out.println(listing.getListing_id()); %> />
    	      <input class="myListings" style="margin-left: 5%;" type='submit' value='<% out.println(listing.getName()); %>' />
    	  </form><br>	
		<% } %> 
		
		
		
		
		
		
		
<%-- 		<div> <% out.println(getArrayString(listings)); %> </div> --%>
		
		
		
		<%!
			public static String getArrayString(ArrayList<Listing> listings)
			{
		    	String result = "[";
		    	int i = 0;
		    	int length = listings.size();
		    	for(Listing listing : listings) {
		        	result += "{lat: " +  listing.getLatitude() + ", " +
		    				  "lng: " + listing.getLongitude() + "}";
		        	if(i < length - 1) {
		            	result += ", ";
		        	}
		        	i++;
		    	}
		    	result += "]";
		
		    return result;
			}
		%> 
		
		<%!
			public static String getArrayNames(ArrayList<Listing> listings)
			{
		    	String result = "[";
		    	int i = 0;
		    	int length = listings.size();
		    	for(Listing listing : listings) {
		        	result += "\"" + listing.getName() + "\"";
		        	if(i < length - 1) {
		            	result += ", ";
		        	}
		        	i++;
		    	}
		    	result += "]";
		    	
		    return result;
			}
		%>

  </body>
</html>