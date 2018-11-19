<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "ex" uri = "/WEB-INF/custom.tld"%>
<%@ page import="entities.User" %>
<%@ page import="entities.Listing" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title> Rentvng | Home </title>
    <meta charset="utf-8">
 	<link rel="stylesheet" type="text/css" href ="../css_scripts/TemplateStyle.css">
    <link rel="stylesheet" type="text/css" href ="../css_scripts/Welcome_Page.css">
    <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Lato"> 
    <script type="text/javascript" src="../js_scripts/testFrame.js"></script> 
  </head>
  <body>
  
  	<% session.setAttribute("current_page","/jsp_scripts/Welcome_Page.jsp"); %>

	<% String login_err_message = (String)session.getAttribute("login_err_message");
	   User user = (User)session.getAttribute("user"); 
	   @SuppressWarnings("unchecked")
	   ArrayList<Listing> listings = (ArrayList<Listing>)(session.getAttribute("recommendation_list"));
	   session.setAttribute("for_booking", "Welcome"); %>
	   
    <ex:Template user ="<%= user %>" login_err_message ="<%= login_err_message %>"></ex:Template>
	
	
	<button onclick="topFunction()" id="myBtn" title="Go to top">Top</button>
	
    <h1> Rentvng <span class="conDate"> <sup>&reg;</sup>Masters of Innovation </span> </h1> 

    <p id="phrase"> Rent unique houses and rooms <br> 
    <span class="con"> At the most economical prices </span> </p>


    <form id="searchForm_block" action="../Search">
      <span id="theSpan"> Where: </span> <span id="theSpan" style="padding-left: 9.9em"> When: </span> <br> 
      <input type="text" name="Location" placeholder="Anywhere" onfocus="this.placeholder='Country, city, district'" onblur="this.placeholder='Anywhere'"> 
      <input style="margin-left: 3em;" placeholder="Anytime" class="textbox-n" type="text" onfocus="(this.type='date')" onblur="(this.type='text')" id="date" name="Date1">
      <input style="margin-left: 3em;" placeholder="Anytime" class="textbox-n" type="text" onfocus="(this.type='date')" onblur="(this.type='text')" id="date" name="Date2">
      <span id="theSpan" style="padding-left: 2em;"> Guests: <input style="margin-left: 1em;" type="number" name="Accommodates" value="1" min="1" max="10">  </span>  
      <span style="padding-left: 3em"> </span>
      <input type="hidden" name="filters" value="false">
      <input id="submitSearch_block" type="submit" value="Submit">
    </form>
    
    
    <% if ( (listings != null) && (listings.size() != 0) ) { %>
    
    <br>
    <br>
    
    <hr style="width: 92%;">
    
    <h1> <strong> Recommended for you </strong></h1>
    
    <br>
    <br>
    
    <div style="margin-left: 10%; text-align:center;">
	<% for (int i=0; i<listings.size(); i++) { %>
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
			return true;
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