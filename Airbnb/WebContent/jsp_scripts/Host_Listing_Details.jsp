<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "ex" uri = "/WEB-INF/custom.tld"%>
<%@ page import="entities.User" %>
<%@ page import="entities.Listing" %>
<%@ page import="entities.AmenitiesRules" %>
<%@ page import="entities.Photo" %>
<%@ page import="entities.Chat" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Host Listing Details</title>
	<link rel="stylesheet" type="text/css" href ="../css_scripts/TemplateStyle.css">
	<link rel="stylesheet" type="text/css" href ="../css_scripts/Host_Listing_Details.css">
	<script type="text/javascript" src="../js_scripts/testFrame.js"></script>
    <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Lato"> 
</head>
<body>

	<% session.setAttribute("current_page","/jsp_scripts/Host_Listing_Details.jsp"); %>
    
    <% User user = (User)session.getAttribute("user");
       Listing listing = (Listing)(session.getAttribute("listing"));
       AmenitiesRules amenities_rules = (AmenitiesRules)(session.getAttribute("amenities_rules"));
       @SuppressWarnings("unchecked")
       ArrayList<Photo> photos = (ArrayList<Photo>)(session.getAttribute("photos"));
       @SuppressWarnings("unchecked")
       ArrayList<Chat> chats = (ArrayList<Chat>)(session.getAttribute("chats"));
       int page_counter = 0;
       if (request.getParameter("page_counter") != null) {
    	   page_counter = Integer.parseInt(request.getParameter("page_counter"));
       }
       int page_size = 25;
       %>
       
    <ex:Template user ="<%= user %>" current_page ="/jsp_scripts/Host_Listing_Details.jsp"></ex:Template>
    
    <h1> Listing Information </h1>

	<button onclick="topFunction()" id="myBtn" title="Go to top">Top</button>

	<br>

	<% if (listing != null) { %>
	  	  <form method='post' action='../Host_update_listing'>
    	  	  <input class="hostChangeInput" name='Listing_id' type='hidden' value=<% out.print(listing.getListing_id()); %> />
    	  	  <label class="hostChangeLabel">Name :</label> <label class="hostChangeLabel" style="padding-left: 14.2%;"> Location :</label> <label class="hostChangeLabel" style="padding-left: 12.7%;"> Latitude : </label> 
    	  	  <label class="hostChangeLabel" style="padding-left: 13%;"> Longitude : </label> <label class="hostChangeLabel" style="padding-left: 11.8%;"> Accommodates : </label> <br>
    	  	  <input class="hostChangeInput" name='Name' type='text' value="<% out.print(listing.getName()); %>" />
    	  	  <input class="hostChangeInput" name='Location' type='text' value="<% out.print(listing.getLocation()); %>" />
    	  	  <input class="hostChangeInput" name='Latitude' type='text' value=<% out.print(listing.getLatitude()); %> />
    	  	  <input class="hostChangeInput" name='Longitude' type='text' value=<% out.print(listing.getLongitude()); %> />
    	  	  <input class="hostChangeInput" name='Accommodates' type='text' value=<% out.print(listing.getAccommodates()); %> /> <br> <br>
    	  	  <label class="hostChangeLabel"> Price : </label> <label class="hostChangeLabel" style="padding-left: 14.5%;"> Cleaning Fee : </label> <label class="hostChangeLabel" style="padding-left: 10.6%;"> Property Type : </label>
    	  	  <label class="hostChangeLabel" style="padding-left: 10.2%;"> Room Type :</label> <label class="hostChangeLabel" style="padding-left: 11.4%;"> Beds :</label> <br> 
    	  	  <input class="hostChangeInput" name='Price' type='text' value=<% out.print(listing.getPrice()); %> />
    	  	  <input class="hostChangeInput" name='Cleaning_fee' type='text' value=<% out.print(listing.getCleaning_fee()); %> />
    	  	  <input class="hostChangeInput" name='Property_type' type='text' value="<% out.print(listing.getProperty_type()); %>" />
    	  	  <input class="hostChangeInput" name='Room_type' type='text' value="<% out.print(listing.getRoom_type()); %>" />
    	  	  <input class="hostChangeInput" name='Beds' type='text' value=<% out.print(listing.getBeds()); %> /> <br> <br>
    	  	  <label class="hostChangeLabel">Bathrooms : </label> <label class="hostChangeLabel" style="padding-left: 11.8%;"> Bedrooms : </label> <label class="hostChangeLabel" style="padding-left: 12%;"> Bed Type : </label> 
    	  	  <label class="hostChangeLabel" style="padding-left: 12.3%;">Square Feet :</label> <label class="hostChangeLabel" style="padding-left: 11.4%;"> Description :</label> <br>
    	  	  <input class="hostChangeInput" name='Bathrooms' type='text' value=<% out.print(listing.getBathrooms()); %> />
    	  	  <input class="hostChangeInput" name='Bedrooms' type='text' value=<% out.print(listing.getBedrooms()); %> />
    	  	  <input class="hostChangeInput" name='Bed_type' type='text' value="<% out.print(listing.getBed_type()); %>" />
    	  	  <input class="hostChangeInput" name='Square_feet' type='text' value=<% out.print(listing.getSquare_feet()); %> />
    	  	  <input class="hostChangeInput" name='Description' type='text' value="<% out.print(listing.getDescription()); %>" /> <br> <br>
    	  	  <label class="hostChangeLabel">Minimum Nights : </label> <label class="hostChangeLabel" style="padding-left: 8.8%;"> Maximum Nights : </label> <label class="hostChangeLabel" style="padding-left: 8.4%;"> Transit : </label> 
			  <label class="hostChangeLabel" style="padding-left: 13.7%;">Guest Included :</label> <label class="hostChangeLabel" style="padding-left: 9.6%;"> Extra People (price) :</label> <br>
    	  	  <input class="hostChangeInput" name='Minimum_nights' type='text' value=<% out.print(listing.getMinimum_nights()); %> />
    	  	  <input class="hostChangeInput" name='Maximum_nights' type='text' value=<% out.print(listing.getMaximum_nights()); %> />
    	  	  <input class="hostChangeInput" name='Transit' type='text' value="<% out.print(listing.getTransit()); %>" />
    	  	  <input class="hostChangeInput" name='Guest_included' type='text' value=<% out.print(listing.getGuest_included()); %> />
    	  	  <input class="hostChangeInput" name='Extra_people' type='text' value=<% out.print(listing.getExtra_people()); %> /> <br> <br> <br>
    	  	 
    	  	  
    	  	  
    	  	  <div><label class="selectLabel">Wifi</label><select required id="AmenitiesSelect" name="Wifi" style="margin-left: 5%; width: 175px;" class="reasons" >
          		<option value="Yes" <%= (amenities_rules.getWifi().equals("Yes"))?"selected":"" %>> Yes </option>
          		<option value="No" <%= (amenities_rules.getWifi().equals("No"))?"selected":"" %>> No </option>
        	  </select></div>
        	  <div><label class="selectLabel">Cooling</label><select required id="AmenitiesSelect" name="Cooling" style="margin-left: 5%; width: 175px;" class="reasons" >
          		<option value="Yes" <%= (amenities_rules.getCooling().equals("Yes"))?"selected":"" %>> Yes </option>
          		<option value="No" <%= (amenities_rules.getCooling().equals("No"))?"selected":"" %>> No </option>
        	  </select></div>
        	  <div><label class="selectLabel">Heating</label><select required id="AmenitiesSelect" name="Heating" style="margin-left: 5%; width: 175px;" class="reasons" >
          		<option value="Yes" <%= (amenities_rules.getHeating().equals("Yes"))?"selected":"" %>> Yes </option>
          		<option value="No" <%= (amenities_rules.getHeating().equals("No"))?"selected":"" %>> No </option>
        	  </select></div>
        	  <div><label class="selectLabel">Kitchen</label><select required id="AmenitiesSelect" name="Kitchen" style="margin-left: 5%; width: 175px;" class="reasons" >
          		<option value="Yes" <%= (amenities_rules.getKitchen().equals("Yes"))?"selected":"" %>> Yes </option>
          		<option value="No" <%= (amenities_rules.getKitchen().equals("No"))?"selected":"" %>> No </option>
        	  </select></div>
        	  <div><label class="selectLabel">Tv</label><select required id="AmenitiesSelect" name="Tv" style="margin-left: 5%; width: 175px;" class="reasons" >
          		<option value="Yes" <%= (amenities_rules.getTv().equals("Yes"))?"selected":"" %>> Yes </option>
          		<option value="No" <%= (amenities_rules.getTv().equals("No"))?"selected":"" %>> No </option>
        	  </select></div>
        	  <div><label class="selectLabel">Parking_lot</label><select required id="AmenitiesSelect" name="Parking_lot" style="margin-left: 5%; width: 175px;" class="reasons" >
          		<option value="Yes" <%= (amenities_rules.getParking_lot().equals("Yes"))?"selected":"" %>> Yes </option>
          		<option value="No" <%= (amenities_rules.getParking_lot().equals("No"))?"selected":"" %>> No </option>
        	  </select></div>
        	  <div><label class="selectLabel">Elevator</label><select required id="AmenitiesSelect" name="Elevator" style="margin-left: 5%; width: 175px;" class="reasons" >
          		<option value="Yes" <%= (amenities_rules.getElevator().equals("Yes"))?"selected":"" %>> Yes </option>
          		<option value="No" <%= (amenities_rules.getElevator().equals("No"))?"selected":"" %>> No </option>
        	  </select></div>
        	  <div><label class="selectLabel">Smoking</label><select required id="AmenitiesSelect" name="Smoking" style="margin-left: 5%; width: 175px;" class="reasons" >
          		<option value="Yes" <%= (amenities_rules.getSmoking().equals("Yes"))?"selected":"" %>> Yes </option>
          		<option value="No" <%= (amenities_rules.getSmoking().equals("No"))?"selected":"" %>> No </option>
        	  </select></div>
        	  <div><label class="selectLabel">Pets</label><select required id="AmenitiesSelect" name="Pets" style="margin-left: 5%; width: 175px;" class="reasons" >
          		<option value="Yes" <%= (amenities_rules.getPets().equals("Yes"))?"selected":"" %>> Yes </option>
          		<option value="No" <%= (amenities_rules.getPets().equals("No"))?"selected":"" %>> No </option>
        	  </select></div>
        	  <div><label class="selectLabel">Events</label><select required id="AmenitiesSelect" name="Events" style="margin-left: 5%; width: 175px;" class="reasons" >
          		<option value="Yes" <%= (amenities_rules.getEvents().equals("Yes"))?"selected":"" %>> Yes </option>
          		<option value="No" <%= (amenities_rules.getEvents().equals("No"))?"selected":"" %>> No </option>
        	  </select></div>
        	  
        	  <div class="hostDateChange"> 
        	  	<label style="font-size: 22px; color: white; margin-left: 20px;"> Add dates </label> <br> <br>
        	  	<label style="font-size: 16px; color: white; margin-left: 2.2%;">Date From :</label> <label style="font-size: 16px; color: white; margin-left: 11.7%;">Date To :</label> <br> 
	       	  	<input class="hostChangeInput" name='date1' type='date'/>
	       	  	<input class="hostChangeInput" name='date2' type='date'/> <br>
        	  </div>
        	  
        	  <br>
     
    	      <input class="hostChangeInput" type='submit' style="position: relative; left: 1050px;" value='Change' />
    	  </form><br>
    	  
    	  <br> 
    	  
    	  <hr width="85%;">
    	  
    	  <h1> Photos </h1>
    	  
    	  <div style="margin-left: 8%;">
    	  <% for (Photo photo : photos) { %>
    	  		<div class="listing_img">
    	  			<img src="<% if (is_url(photo.getPhoto())) { out.print(photo.getPhoto()); } else { out.print("../Image_servlet/\\images\\users\\" + user.getUser_id() + "\\listing_images\\" + listing.getListing_id() + "\\" + photo.getPhoto()); } %>" style="width:500px; height:400px">
    	  			<div class="desc">
    	  				<a style="color: white; background-color: black;" href="../Remove_listing_photo?Photo=<%out.print(photo.getPhoto());%>">Delete Photo</a>
    	  			</div>
    	  		</div>
    	  <% } %>
    	  </div>
    	  
    	  
    	  <%
    	  boolean odd = false;
    	  if (photos.size() % 2 == 1) {
    		  odd = true;
    	  }
    	  %>
    	  
    	  <div style="<%if (odd) {out.print("position:relative; top: 450px; left: -600px;");}%>;" class= "addPhotoClass">
    	  
    	  <br> <br> <br>
    	  
    	  <div style="position:relative; top:20px; left:110px; ">
    	  
    	  <form method='post' action='../Add_listing_url_photo'>
    	  	  <label style="width: 200px; margin-left: 1%; font-size: 20px; color:black;"> <strong> Insert image as url: </strong> </label> <br> <br>
    	  	  <input style="width: 303px; height: 40px;" class="hostChangeInput" name='Photo' type='text' required /> <br><br>
    	  	  <input class="hostChangeInput" type='submit' value='Add url' />
    	  </form>
    	  
    	  </div>
    	  
   	      <div style="position:relative; top:-123px; left:640px; ">
   
		    <form method="post" enctype="multipart/form-data" action="../Upload_listing_photo">
				<label style="width: 200px; margin-left: 1%; font-size: 20px; color:black;"> <strong> Select image to upload: </strong> </label> <br> <br>
				<input class="hostChangeInput" type="file" name="file" /><br><br>
		        <input class="hostChangeInput" type="submit" value="Upload photo" />
			</form>
		
		  </div>
    	  
    	  <br>
    	  
    	 </div>
    	 
    	 <% 
    	 int base = 40;
    	 int inc = 25;
    	 int counter = (photos.size() / 2);
    	 if (!odd) {
    		 counter += 1;
    	 }
    	 int max = base + (inc*counter);
    	 if (odd) {
    	 	for (int i=0; i<max; i++) { %>
    	 		<br>
    	 <% } 
    	 } %>
    	 
    	 
    	 
    	 <hr width="85%;">
    	  
		  <h1> Photo medium </h1>
		  
		  <% if (listing.getPhoto_medium() != null) { %>
		  
		  <div style="margin-left: 8%;">
			<div style="width: 500px; float: left; margin-left: 2%; ">
				<img src="<% if (is_url(listing.getPhoto_medium())) { out.print(listing.getPhoto_medium()); } else { out.print("../Image_servlet/\\images\\users\\" + user.getUser_id() + "\\listing_images\\" + listing.getListing_id() + "\\listing_medium_image\\" + listing.getPhoto_medium()); } %>" style="width:500px; height:400px">
			</div>
		  </div>
		  
		  <% } %>
		  
		  
		  <div style="<%if (listing.getPhoto_medium() != null) {out.print("position:relative; top: 450px; left: -600px;");}%>;" class= "addPhotoClass">
		  
			  <br> <br> <br>
			  
			  <div style="position:relative; top:20px; left:110px; ">
		  
				  <form method='post' action='../Add_listing_url_medium_photo'>
					  <label style="width: 200px; margin-left: 1%; font-size: 20px; color:black;"> <strong> Insert image as url: </strong> </label> <br> <br>
					  <input style="width: 303px; height: 40px;" class="hostChangeInput" name='Photo' type='text' required /> <br><br>
					  <input class="hostChangeInput" type='submit' value='Add url' />
				  </form>
		  
			   </div>
		  
			  <div style="position:relative; top:-123px; left:640px; ">
		
				<form method="post" enctype="multipart/form-data" action="../Upload_listing_medium_photo">
					<label style="width: 200px; margin-left: 1%; font-size: 20px; color:black;"> <strong> Select image to upload: </strong> </label> <br> <br>
					<input class="hostChangeInput" type="file" name="file" /><br><br>
					<input class="hostChangeInput" type="submit" value="Upload photo" />
				</form>
		
			  </div>
		  
		      <br>
		  
		  </div>
		 
		 
		 
		 <% 
    	 int max2 = 40;
    	 if (listing.getPhoto_medium() != null) {
    	 	for (int i=0; i<max2; i++) { %>
    	 		<br>
    	 <% } 
    	 } %> 
		  
		  
		  
		  <hr width="85%;">
		  
		  <h1> Photo large </h1>
		  
		  <% if (listing.getPhoto_large() != null) { %>
		  
		  <div style="margin-left: 8%;">
			<div style="width: 500px; float: left; margin-left: 2%; ">
				<img src="<% if (is_url(listing.getPhoto_large())) { out.print(listing.getPhoto_large()); } else { out.print("../Image_servlet/\\images\\users\\" + user.getUser_id() + "\\listing_images\\" + listing.getListing_id() + "\\listing_large_image\\" + listing.getPhoto_large()); } %>" style="width:1000px; height:600px">
			</div>
		  </div>
		  
		  <% } %>
		  
		  
		  <div style="<%if (listing.getPhoto_large() != null) {out.print("position:relative; top: 600px; left: -600px;");}%>;" class= "addPhotoClass">
		  
			  <br> <br> <br>
			  
			  <div style="position:relative; top:20px; left:110px; ">
			  
				  <form method='post' action='../Add_listing_url_large_photo'>
					  <label style="width: 200px; margin-left: 1%; font-size: 20px; color:black;"> <strong> Insert image as url: </strong> </label> <br> <br>
					  <input style="width: 303px; height: 40px;" class="hostChangeInput" name='Photo' type='text' required /> <br><br>
					  <input class="hostChangeInput" type='submit' value='Add url' />
				  </form>
			  
			  </div>
		  
			  <div style="position:relative; top:-123px; left:640px; ">
		
				<form method="post" enctype="multipart/form-data" action="../Upload_listing_large_photo">
					<label style="width: 200px; margin-left: 1%; font-size: 20px; color:black;"> <strong> Select image to upload: </strong> </label> <br> <br>
					<input class="hostChangeInput" type="file" name="file" /><br><br>
					<input class="hostChangeInput" type="submit" value="Upload photo" />
				</form>
		
			  </div>
		  
			<br>
		  
		 </div>
    	 
    	 <% 
    	 int max3 = 47;
    	 if (listing.getPhoto_large() != null) {
    	 	for (int i=0; i<max3; i++) { %>
    	 		<br>
    	 <% } 
    	 } %> 
    	 
    	  <br> <br> 
    	  
    	  <hr style="position: relative;" width="85%;">
    	  
    	  <h1> Chat </h1>
    	 
    	  <label class="colorChat" style="color:darkorange"> Orange : New message </label> <br>
    	  <label class="colorChat"> Green : Seen message </label>
    	  
    	  <br>
    	  
		  <label id="listingLabel">Users :</label> <br> <br>
		  
		  <% if ( (chats != null) && (chats.size() != 0) ) { %>
    
		    <% int maxpage_counter = chats.size()/page_size;
		    if (chats.size() % page_size != 0) {
		    	++maxpage_counter;	
		    }
		    if (page_counter == -1) {
				   page_counter = 0;
			}
		    else if (page_counter == maxpage_counter) {
		    	page_counter = maxpage_counter-1;
		    }
		    %>
		
			 <div style="text-align: center;">
		    	<a href="Host_Listing_Details.jsp?page_counter=<% out.print(page_counter-1); %>" class="button ticketbutton"> Previous </a>
		   		<% out.print((page_counter+1) + " / " + maxpage_counter); %>
		   		<a href="Host_Listing_Details.jsp?page_counter=<% out.print(page_counter+1); %>" class="button ticketbutton"> Next </a>
			 </div>
		 
		 	<br> <br>
		
		
			  <div class="container">
			  <% for (int i=(page_counter*page_size),count=0; i<chats.size() && count < page_size; i++ , count++) { %>
			    <% String new_message_color = null;
			       int User2_id = 0;
			  	   if ( (!chats.get(i).getSeen()) && (chats.get(i).getWho_wrote_it() == chats.get(i).getUser_id()) ) {
			  	     new_message_color = "background-color: darkorange;";
			  	   }
			       else {
			    	 new_message_color = ""; 
			       }
			    %>
			 	<form method='post' action='../Find_chat'>
			 		<div>
			  	  	  <input name='User2_id' type='hidden' value=<% out.println(chats.get(i).getUser_id()); %> />
			  	  	  <input name='Listing_id' type='hidden' value=<% out.println(chats.get(i).getListing_id()); %> />
			  	  	  <input name='Chat_previous_page' type='hidden' value=<% out.print("Host_Listing_Details.jsp"); %> />
			  	      <input class="myChatList" style=" <% out.print(new_message_color); %>" type='submit' value='<% out.println(chats.get(i).getMessage()); %>' />
			  	    </div>
			  	</form>	
			  <% } %> 
			  </div>
		  
			  <br> <br>
			  
			  <div id="bottom" style="text-align: center;">
		    	<a href="Host_Listing_Details.jsp?page_counter=<% out.print(page_counter-1); %>" class="button ticketbutton"> Previous </a>
		   		<% out.print((page_counter+1) + " / " + maxpage_counter); %>
		   		<a href="Host_Listing_Details.jsp?page_counter=<% out.print(page_counter+1); %>" class="button ticketbutton"> Next </a>
			  </div>
			  
			  
			  <% if (request.getParameter("page_counter") != null) { %>
			  
			   <script> bottom(); </script>
			   
			   <% } %>
		  
		  
			<% } %>
    	  
	<% } %>
	
	<script>
	// When the user scrolls down 20px from the top of the document, show the button
	window.onscroll = function() {scrollFunction()};
	
	function scrollFunction() {
	    if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
	        document.getElementById("myBtn").style.display = "block";
	    } else {
	        document.getElementById("myBtn").style.display = "none";
	    }
	}
	
	// When the user clicks on the button, scroll to the top of the document
	function topFunction() {
	    document.body.scrollTop = 0;
	    document.documentElement.scrollTop = 0;
	}
	</script>
	
	<%! public boolean is_url(String photo) {
		
		if (photo == null) {
			return false;
		}
		
		String http_url = photo.substring(0, Math.min(photo.length(), 4));
		String https_url = photo.substring(0, Math.min(photo.length(), 5));
		
		if ( (http_url.equals("http")) || (https_url.equals("https")) ) {
			return true;
		}
		
		return false;
	}
	%>

</body>
</html>