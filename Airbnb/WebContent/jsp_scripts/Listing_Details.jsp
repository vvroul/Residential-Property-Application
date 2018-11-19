<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "ex" uri = "/WEB-INF/custom.tld"%>
<%@ page import="entities.User" %>
<%@ page import="entities.Listing" %>
<%@ page import="entities.Review" %>
<%@ page import="entities.Photo" %>
<%@ page import="entities.AmenitiesRules" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href ="../css_scripts/Listing_Details.css">
	<link rel="stylesheet" type="text/css" href ="../css_scripts/TemplateStyle.css">
    <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Lato">
	<script type="text/javascript" src="../js_scripts/testFrame.js"></script>
</head>
<body>

	<% session.setAttribute("current_page","/jsp_scripts/Listing_Details.jsp"); %>

	<% User user = (User)session.getAttribute("user"); 
	   User host = (User)session.getAttribute("host");
	   Listing listing = (Listing)session.getAttribute("listing");
	   @SuppressWarnings("unchecked")
	   ArrayList<Review> reviews = (ArrayList<Review>)(session.getAttribute("reviews"));
	   @SuppressWarnings("unchecked")
	   ArrayList<Photo> photos = (ArrayList<Photo>)(session.getAttribute("photos"));
	   AmenitiesRules amenities_rules = (AmenitiesRules)session.getAttribute("amenities_rules");
	   String for_booking = (String)session.getAttribute("for_booking"); %>
	   
	<button onclick="topFunction()" id="myBtn" title="Go to top">Top</button>

	<ex:Template user ="<%= user %>" current_page ="/jsp_scripts/Listing_Details.jsp"></ex:Template>

	<div id='myModal2' class='modal2'>
		<span class='close2'>&times;</span>
		<iframe class='modal-content2' id='myFrame2'> </iframe>
		<div id='caption2'></div>
	</div>
	
	<% if ( (listing != null) && (listing.getPhoto_large() != null) ) { %>
	
	<img alt="" src="<% if (is_url(listing.getPhoto_large())) { out.print(listing.getPhoto_large()); } else { out.print("../Image_servlet/\\images\\users\\" + user.getUser_id() + "\\listing_images\\" + listing.getListing_id() + "\\listing_large_image\\" + listing.getPhoto_large()); } %>" 
    style="z-index:-1000; width:100%; height:500px">
    
    <% } %>
	
	<br> <br>
	
	<% if (user != null) { %>

	<a class=" filterButton" style="margin-left: 4%;" href='' onclick='showReview(); return false;'> Review </a> 
	
	<% if (for_booking.equals("View_Rents"))  { %>
		<a class=" filterButton" style="margin-left: 25%;" href='../New_booking'> Confirm Booking </a>
	<% } 
	else { 
	%>
		<a class=" filterButton" style="margin-left: 25%;" href='Select_Booking.jsp'> Booking </a>
	<% } %>
	<a class=" filterButton" style="margin-left: 25%;" href='View_Rents.jsp'> Return </a>
	
	<% 
	}
	else { %>
		<a class=" filterButton" style="margin-left: 4%;" href='View_Rents.jsp'> Return </a>
	<% } %>
	
	
	<br> <br>
	
	<br> <br>
	
	<% if (listing != null) { %>
		<div>
			<p> <span> <strong> The space </strong> </span> </p>
			<div style="margin-left: 4%;">
				<span class="infoSpan"> Beds : <% out.print(listing.getBeds()); %>  </span>
				<span class="infoSpan" style="margin-left: 58px;"> Bathrooms : <% out.print(listing.getBathrooms()); %> </span>
				<span class="infoSpan" style="margin-left: 48px;"> Property Type : <% out.print(listing.getProperty_type()); %> </span> <br> <br>
				<span class="infoSpan"> Bedrooms : <% out.print(listing.getBedrooms()); %> </span>
				<span class="infoSpan" style="margin-left: 20px;"> Room Type : <% out.print(listing.getRoom_type()); %> </span>
				<span class="infoSpan" style="margin-left: 20px;"> Square Feet : <% out.print(listing.getSquare_feet()); %> </span>
			</div>
		</div>
		
		<br> <br>

		<hr style="width: 92%;">
	
		<br>
	
		<div> 
			<p> <span> <strong> Description </strong> </span> </p>
			<div style="margin-left: 4%;">
				<span class="infoSpan" style="display:block; width:65%; word-wrap:break-word;"> <% if (listing.getDescription() != null) { out.print(listing.getDescription()); } %> </span>
			</div>
		</div>
		
		<br> <br>

		<hr style="width: 92%;">
	
		<br>
		
		<div>
			<p> <span> <strong> House rules </strong> </span> </p>
			<div style="margin-left: 4%;">
				<span class="infoSpan"> Smoking : <% out.print(amenities_rules.getSmoking()); %></span>
				<span class="infoSpan" style="margin-left: 57px;"> Pets : <% out.print(amenities_rules.getPets()); %></span> <br> <br>
				<span class="infoSpan"> Events : <% out.print(amenities_rules.getEvents()); %></span>
				<span class="infoSpan" style="margin-left: 45px;"> Minimum nights : <% out.print(listing.getMinimum_nights()); %> </span>
			</div>
		</div>
		
		<br> <br>

		<hr style="width: 92%;">
	
		<br>
	
		<div>
			<p> <span>  <strong> Location </strong> </span> </p>
			<div style="margin-left: 18%;">
				<span>
					<div id="map"> </div>
			       	<script type="text/javascript" src = "../js_scripts/newMap.js"></script>
			       	<script> FillLocations(<% out.println(getArrayString(listing)); %>) </script>
			       	<script> FillNames(<% out.println(getArrayNames(listing)); %>) </script>
			       	<script async defer
			       		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyClU3fGihtFNeHlr1Vj96OR0OcGXIkdlnE&callback=initMap">
			       	</script>
			       <script 
			       	src="https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/markerclusterer.js">
			       </script>
				</span>
			</div>
		</div>
	
		<br> <br>
	
		<hr style="width: 92%;">
	
		<br>
		
		<div>
			<p> <span> <strong> Photos </strong> </span> </p>
			<div style="margin-left: 4%;">
				<span>
					<% if ( (photos != null) && (photos.size() != 0) ) { %>
		
						<div class="slideshow-container">
						
						<% int length = photos.size(); %>
						<% int i = 1; %>
						
						<% for (Photo photo : photos) { %>
						  	  
						  	  <div class="mySlides fade">
						  		<div class="numbertext"><% out.print(i++ + " / " + length); %></div>
						  		<img src="<% if (is_url(photo.getPhoto())) { out.print(photo.getPhoto()); } else { out.print("../Image_servlet/\\images\\users\\" + host.getUser_id() + "\\listing_images\\" + listing.getListing_id() + "\\" + photo.getPhoto()); } %>" style="width:600px; height:400px">
							  </div>
						<% } %>
						
						<a class="prev" onclick="plusSlides(-1)">&#10094;</a>
						<a class="next" onclick="plusSlides(1)">&#10095;</a>
						
						</div>
						<br>
						
						<div style="text-align:center">
						<% for ( i=1; i<=length; i++) { %>
						  <span class="dot" onclick="currentSlide(<%out.print(i);%>)"></span> 
						<% } %> 
						</div>
						
						<script>
							var slideIndex = 1;
							showSlides(slideIndex);
						</script>
				
					<% } %> 
				</span>
			</div>
		</div>
		
		
		<br> <br>
	
		<hr style="width: 92%;">
	
		<br>
		
		<div>
			<p> <span style="font-weight: bold;"> Host info </span> </p>
			<div style="margin-left: 4%;">
				<span style="color:darkorange; font-size: 20px;"> Photo
					<div>
						<% if (host.getPhoto() != null) { %>
						<img src="<% if (is_url(host.getPhoto())) { out.print(host.getPhoto()); } else { out.print("../Image_servlet/\\images\\users\\" + host.getUser_id() + "\\user_image\\" + host.getPhoto()); } %>" style="width:300px; height:250px">
						<% } %>
					</div>
				</span> 
				<br> <br>
				<span style="color:darkorange; font-size: 20px;">
					Reviews
				</span>
				<span class="infoSpan" style="display:block; width:65%; word-wrap:break-word;"> 
					<% for (Review review : reviews) { %>
				  	  <br>
				  	  <div class="reviewClass"> 
				  	  	<% out.print(review.getScore() / 20); %> -- <% out.print(review.getMessage()); %>
				  	  	<br> <br>
				  	  </div>
					<% } %>
				</span>
			</div>
		</div>


	<br> <br>	
	
	
	
		
	
	<% } %>

	<% if ( (user != null) && (listing != null) && (user.getUser_id() != listing.getHost_id()) ) { %>

	<form method='post' action='../Find_chat'>
     	  <input name='User2_id' type='hidden' value=<% out.print(listing.getHost_id()); %> />
     	  <input name='Listing_id' type='hidden' value=<% out.print(listing.getListing_id()); %> />
     	  <input name='Chat_previous_page' type='hidden' value=<% out.print("Listing_Details.jsp"); %> />
          <input class=" filterButton" style="margin-left: 5%;" type='submit' value='Contact host' />
    </form><br>
    
    <% } %>
    
    
    <%!
		public static String getArrayString(Listing listing)
		{
        	return "[{lat: " +  listing.getLatitude() + ", " + "lng: " + listing.getLongitude() + "}]";
		}
	 %> 
	  
	 <%!
		public static String getArrayNames(Listing listing)
		{
	    	return "[\"" + listing.getName() + "\"]";
		}
	  %>
	  
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