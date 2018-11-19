<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "ex" uri = "/WEB-INF/custom.tld"%>
<%@ page import="entities.User" %>
<%@ page import="entities.Listing" %>
<%@ page import="entities.AmenitiesRules" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Host Listing Details</title>
	<link rel="stylesheet" type="text/css" href ="../css_scripts/TemplateStyle.css">
	<link rel="stylesheet" type="text/css" href ="../css_scripts/Host_New_Listing.css">
    <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Lato"> 
</head>
<body>


	<% session.setAttribute("current_page","/jsp_scripts/Host_Listing_Details.jsp"); %>
    
    <% User user = (User)session.getAttribute("user");%>
       
    <ex:Template user ="<%= user %>"></ex:Template>
    
    <h1> New Listing </h1> <br> <br>

	
  	  <form method='post' action='../Host_new_listing'>
  	  	<div style="margin-left: 0.7%;"> 
   	  	  <label style="margin-left: 1.3%;">Name</label> <label style="margin-left: 7.8%;" >Location</label> <label style="margin-left: 8.2%;">Latitude</label>
   	  	  <label style="margin-left: 8%;">Longitude</label> <label style="margin-left: 7.8%;">Accommodates</label> <label style="margin-left: 8%;">Price</label> <br> 
   	  	  <input name='Name' type='text' required/>
   	  	  <input name='Location' type='text' required/>
   	  	  <input name='Latitude' type='text' required/>
   	  	  <input name='Longitude' type='text' required/>
   	  	  <input name='Accommodates' type='text' required/>
   	  	  <input name='Price' type='text' required /> <br> <br>
   	  	  <label style="margin-left: 1.3%;">Cleaning_fee</label> <label style="margin-left: 7.8%;">Property_type</label> <label style="margin-left: 8.1%;">Room_type</label> 
   	  	  <label style="margin-left: 8%;">Beds</label> <label style="margin-left: 8%;">Bathrooms</label><label style="margin-left: 8%;">Bedrooms</label> <br>
   	  	  <input name='Cleaning_fee' type='text' required />
   	  	  <input name='Property_type' type='text' required />
   	  	  <input name='Room_type' type='text' required />
   	  	  <input name='Beds' type='text' required /> 
   	  	  <input name='Bathrooms' type='text' required />
   	  	  <input name='Bedrooms' type='text' required /> <br> <br> 
   	  	  <label style="margin-left: 1.3%;">Bed_type</label><label style="margin-left: 8.2%;">Square_feet</label><label style="margin-left: 8.3%;">Description</label>
   	  	  <label style="margin-left: 8%;">Minimum_nights</label><label style="margin-left: 8.3%;">Maximum_nights</label><label style="margin-left: 8%;">Transit</label> <br>
   	  	  <input name='Bed_type' type='text' required />
   	  	  <input name='Square_feet' type='text' />
   	  	  <input name='Description' type='text' />
   	  	  <input name='Minimum_nights' type='text' />
   	  	  <input name='Maximum_nights' type='text' />
   	  	  <input name='Transit' type='text' /> <br> <br>
   	  	  <label style="margin-left: 1.3%;">Guest_included</label> <label style="margin-left: 8%;">Extra_people</label> <br>
   	  	  <input name='Guest_included' type='text' required />
   	  	  <input name='Extra_people' type='text' required /> <br> <br> <br>
   	  	</div>
   	  	  
   	  	 <div><label class="selectLabel">Wifi</label><select required id="AmenitiesSelect" name="Wifi" style="margin-left: 5%; width: 175px;" class="reasons" >
	   		<option value="Yes"> Yes </option>
	   		<option value="No"> No </option>
	 	  </select></div>
	 	  <div><label class="selectLabel">Cooling</label><select required id="AmenitiesSelect" name="Cooling" style="margin-left: 5%; width: 175px;" class="reasons" >
	   		<option value="Yes"> Yes </option>
	   		<option value="No"> No </option>
	 	  </select></div>
	 	  <div><label class="selectLabel">Heating</label><select required id="AmenitiesSelect" name="Heating" style="margin-left: 5%; width: 175px;" class="reasons" >
	   		<option value="Yes"> Yes </option>
	   		<option value="No"> No </option>
	 	  </select></div>
	 	  <div><label class="selectLabel">Kitchen</label><select required id="AmenitiesSelect" name="Kitchen" style="margin-left: 5%; width: 175px;" class="reasons" >
	   		<option value="Yes"> Yes </option>
	   		<option value="No"> No </option>
	 	  </select></div>
	 	  <div><label class="selectLabel">Tv</label><select required id="AmenitiesSelect" name="Tv" style="margin-left: 5%; width: 175px;" class="reasons" >
	   		<option value="Yes"> Yes </option>
	   		<option value="No"> No </option>
	 	  </select></div>
	 	  <div><label class="selectLabel">Parking_lot</label><select required id="AmenitiesSelect" name="Parking_lot" style="margin-left: 5%; width: 175px;" class="reasons" >
	   		<option value="Yes"> Yes </option>
	   		<option value="No"> No </option>
	 	  </select></div>
	 	  <div><label class="selectLabel">Elevator</label><select required id="AmenitiesSelect" name="Elevator" style="margin-left: 5%; width: 175px;" class="reasons" >
	   		<option value="Yes"> Yes </option>
	   		<option value="No"> No </option>
	 	  </select></div>
	 	  <div><label class="selectLabel">Smoking</label><select required id="AmenitiesSelect" name="Smoking" style="margin-left: 5%; width: 175px;" class="reasons" >
	   		<option value="Yes"> Yes </option>
	   		<option value="No"> No </option>
	 	  </select></div>
	 	  <div><label class="selectLabel">Pets</label><select required id="AmenitiesSelect" name="Pets" style="margin-left: 5%; width: 175px;" class="reasons" >
	   		<option value="Yes"> Yes </option>
	   		<option value="No"> No </option>
	 	  </select></div>
	 	  <div><label class="selectLabel">Events</label><select required id="AmenitiesSelect" name="Events" style="margin-left: 5%; width: 175px;" class="reasons" >
	   		<option value="Yes"> Yes </option>
	   		<option value="No"> No </option>
	 	  </select></div>
	 	  
	 	  <br> <br> <br>
       	  
       	  <div style="margin-left: 0.7%;">
	       	  <label style="margin-left: 1.3%;">Date From :</label> <label style="margin-left: 5.2%;">Date To :</label> <br> 
	       	  <input name='date1' type='date' required />
	       	  <input name='date2' type='date' required /> <br>
    	  </div>
   	      <input class="regiSubmit" type='submit' value='Submit' />
   	  </form><br>
	

</body>
</html>