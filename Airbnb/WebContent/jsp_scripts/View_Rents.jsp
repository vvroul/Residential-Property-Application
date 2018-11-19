<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "ex" uri = "/WEB-INF/custom.tld"%>
<%@ page import="entities.User" %>
<%@ page import="entities.Listing" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title> View Rents | Rentvng </title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href ="../css_scripts/TemplateStyle.css">
    <link rel="stylesheet" type="text/css" href ="../css_scripts/View_Rents.css">
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
  
  	<% session.setAttribute("current_page","/jsp_scripts/View_Rents.jsp"); %>


    <% User user = (User)session.getAttribute("user"); 
       @SuppressWarnings("unchecked")
       ArrayList<Listing> listings = (ArrayList<Listing>)(session.getAttribute("listings"));
       String date1 = (String)session.getAttribute("date1");
       String date2 = (String)session.getAttribute("date2");
       int accommodates = (Integer)session.getAttribute("accommodates");
       String location = (String)session.getAttribute("location");
       int page_counter = 0;
       if (request.getParameter("page_counter") != null) {
    	   page_counter = Integer.parseInt(request.getParameter("page_counter"));
       }
       int page_size = 10;
       session.setAttribute("for_booking", "View_Rents");
       %>
       
    <ex:Template user ="<%= user %>" current_page ="/jsp_scripts/View_Rents.jsp"></ex:Template>
    
    <button onclick="topFunction()" id="myBtn" title="Go to top">Top</button>
    
    <button class="button filterButton" onclick="showFilters()"> Filters </button>

	<br> <br>
    
    <div id="hidden_filter_div" style="display: none;">
    	<form method="post" onsubmit="disableFilters()" action="../Search">
    	
    		<label class="filterLabel" style="margin-left: 3.5%; " >Room type :</label> 
    		<label class="filterLabel" style="margin-left: 9.1%; "> Price : </label>
    		<br> <br> 
    		<input class="filterLabelTextInput" style="margin-left: 3.5%;" type="text" name="Room_type" />
    		<input class="filterLabelTextInput" style="margin-left: 3%;" type="text" name="Price" /> 
    		
    		<div class="checkboxes">
	    		<input id="nowifi" type="hidden" name="Wifi" value="">
	    		<input class="filterCheckBox" id="wifi" type="checkbox" name="Wifi" value="Yes">
	    		<label for="wifi"> Wifi </label>
				<input id="nocooling" type="hidden" name="Cooling" value=""> 
	  			<input class="filterCheckBox" id="cooling" type="checkbox" name="Cooling" value="Yes">
	  			<label for="cooling"> Cooling </label>
		  		<input id="noheating" type="hidden" name="Heating" value="">
		  		<input class="filterCheckBox" id="heating" type="checkbox" name="Heating" value="Yes">
		  		<label for="heating"> Heating </label>
		  		<input id="nokitchen" type="hidden" name="Kitchen" value="">
		  		<input class="filterCheckBox" id="kitchen" type="checkbox" name="Kitchen" value="Yes" >
		  		<label for="kitchen"> Kitchen </label>
		  		<input id="notv" type="hidden" name="Tv" value="">
		  		<input class="filterCheckBox" id="tv" type="checkbox" name="Tv" value="Yes">
		  		<label for="tv"> Tv </label>
		  		<input id="noparking_lot" type="hidden" name="Parking_lot" value="">
		  		<input class="filterCheckBox" id="parking_lot" type="checkbox" name="Parking_lot" value="Yes">
		  		<label for="parking_lot"> Parking_lot </label>
		  		<input id="noelevator" type="hidden" name="Elevator" value="">
		  		<input class="filterCheckBox" id="elevator" type="checkbox" name="Elevator" value="Yes" >
		  		<label for="elevator"> Elevator </label>
		  	</div>
	  		
	  		<input type="hidden" name="Location" value="<% out.print(location); %>">
	  		<input type="hidden" name="Date1" value="<% out.print(date1); %>">
	  		<input type="hidden" name="Date2" value="<% out.print(date2); %>">
	  		<input type="hidden" name="Accommodates" value=<% out.print(accommodates); %>>
	  		
	  		<input type="hidden" name="filters" value="true">
	  		
	  		<input class="filterLabelSubmit" type="submit" value="Submit">

   		 </form>
    </div>
    
    <br> <br> <br>
    
    <% if ( (listings != null) && (listings.size() != 0) ) { %>
    
    <% int maxpage_counter = listings.size()/page_size;
    if (listings.size() % page_size != 0)
    {
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
	    	<a href="View_Rents.jsp?page_counter=<% out.print(page_counter-1); %>" class="button ticketbutton"> Previous </a>
	   		<% out.print((page_counter+1) + " / " + maxpage_counter); %>
	   		<a href="View_Rents.jsp?page_counter=<% out.print(page_counter+1); %>" class="button ticketbutton"> Next </a>
		</div>
		
		<br> <br> <br>
		
		<% int height = page_counter*page_size; 
		   if ((height + page_size) <= (listings.size() - height)) {
			   height = page_size / 2;
		   }
		   else {
			   int temp = (listings.size() - height) / 2;
			   if ((listings.size() - height) % 2 != 0) {
				   height = temp + 1;
			   }
			   else {
				   height = temp;
			   }
		   }
		   height = (height * 400) + 50; 
		   String h = height + "px";%>
	
		<div style="margin-left: 10%; text-align:center; height:<%out.print(h);%>;">
		<% for (int i=(page_counter*page_size),count=0; i<listings.size() && count < page_size; i++ , count++) { %>
		<%  String[] col = { "", "", "", "", "" }; 
			for (int k = 0; k < (listings.get(i).getReview_scores_rating() / 20 ); k++) { 
				col[k] = "color: red;";
			}
		%>
	    	<form method="post" action="../Listing_details">
	    		<div class="listing_img">
					<% if (listings.get(i).getPhoto_medium() != null) { %>
	    			<input type="image" src="<% if (is_url(listings.get(i).getPhoto_medium())) { out.print(listings.get(i).getPhoto_medium()); } else { out.print("../Image_servlet/\\images\\users\\" + listings.get(i).getHost_id() + "\\listing_images\\" + listings.get(i).getListing_id() + "\\listing_medium_image\\" + listings.get(i).getPhoto_medium()); } %>" alt="No photo" width="400" height="260">
	    			<% }
	    			else {  %>
	    			<input type="image" src="https://vignette1.wikia.nocookie.net/bokunoheroacademia/images/d/d5/NoPicAvailable.png/revision/latest?cb=20160326222204" alt="No photo" width="400" height="260">
	    			<% } %>
	    			<div class="desc">
	    					<span id="desc_span"> From &euro;<% out.print(listings.get(i).getPrice());%></span>
	    					<span id="little_star">&nbsp;&nbsp; * &nbsp;&nbsp;</span>
	    					<span id="desc_span"> <% out.print(listings.get(i).getName()); %></span>
	    					<br>
		    				<span id="desc_span"> <% out.print(listings.get(i).getProperty_type()); %></span>
		    				<span id="little_star">&nbsp;&nbsp; * &nbsp;&nbsp;</span>
		    				<span id="desc_span"> <% out.print(listings.get(i).getBeds()); %> beds</span>
		    				<br>
							<div class="stars">
							    <label style="<% out.print(col[0]); %>" class="star star-1" for="star-1"></label>
							    <label style="<% out.print(col[1]); %>" class="star star-2" for="star-2"></label>
							    <label style="<% out.print(col[2]); %>" class="star star-3" for="star-3"></label>
							    <label style="<% out.print(col[3]); %>" class="star star-4" for="star-4"></label>
							    <label style="<% out.print(col[4]); %>" class="star star-5" for="star-5"></label>
							    <span id="scoreRating"> <% out.print(listings.get(i).getNumber_of_reviews()); %></span>
							</div>
	    			 </div>
	    		</div>
		    	<input type="hidden" name="Listing_id" value=<% out.print(listings.get(i).getListing_id()); %>>
	    	</form>
	    <% } %>
	    </div>
    
    
    	<div style="min-height:100%; position: relative;">
    
		<div id="br_link">
	   		<a href="View_Rents.jsp?page_counter=<% out.print(page_counter-1); %>" class="button ticketbutton"> Previous </a>
	  			<% out.print((page_counter+1) + " / " + maxpage_counter); %>
	  		<a href="View_Rents.jsp?page_counter=<% out.print(page_counter+1); %>" class="button ticketbutton"> Next </a>
		</div>
		
		</div>
		
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