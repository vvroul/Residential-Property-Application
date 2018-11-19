<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "ex" uri = "/WEB-INF/custom.tld"%>
<%@ page import="entities.User" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title> View Rents | Rentvng </title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href ="../css_scripts/TemplateStyle.css">
    <link rel="stylesheet" type="text/css" href ="../css_scripts/Profile_Page.css">
    <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Lato"> 
    <script type="text/javascript" src="../js_scripts/testFrame.js"></script> 
  </head>
  <body>
  
  	<% session.setAttribute("current_page","/jsp_scripts/Profile_Page.jsp"); %>

    <% User user = (User)session.getAttribute("user"); %>
    <ex:Template user ="<%= user %>"></ex:Template>

    <div id="myModal" class="modal">
      <span class="close">&times;</span>
      <iframe class="modal-content" id="myFrame"> </iframe>
      <div id="caption"></div>
    </div>

	<div style="padding-left: 5%;">
    <form style="padding-left: 7.5%;" method="post" action="../Update_user_profile">
      <img class="profileImage" alt="profile_img" src="<% if (is_url(user.getPhoto())) { out.println(user.getPhoto()); } else { out.println("../Image_servlet/\\images\\users\\" + user.getUser_id() + "\\user_image\\" + user.getPhoto()); } %>">
      <div style="position:absolute; top:150px; left:400px; ">
      	<label style="padding-left: 1%;"> Username :</label>  <label style="padding-left: 14.2%;"> E-mail : </label> <label style="padding-left: 14.2%;"> Name : </label> <br>
      	<input  type="text" name="Username" value= <% out.println(user.getUsername()); %> />
      	<input  type="text" name="Email" value= <% out.println(user.getEmail()); %> />
     	<input  type="text" name="Name" value= <% out.println(user.getName()); %> /> <br> <br>
      <label style="padding-left: 1%;"> Surname :</label> <label style="padding-left: 14.2%;"> Telephone :</label>  <label style="padding-left: 14.2%;"> Photo (url only) : </label> <br> 
      <input type="text" name="Surname" value= <% out.println(user.getSurname()); %> />
      <input type="text" name="Contact_number" value= <% out.println(user.getContact_number()); %> />
      <input type="text" name="Photo" value= <% out.println(user.getPhoto()); %> />

      <% if (user.getAdmin_level() == 2 ||  user.getAdmin_level() == 3)
        {
      %>
          <br> <br>
          <label style="padding-left: 1%;"> Host Since :</label>  <label style="padding-left: 14.2%;"> Location : </label> <label style="padding-left: 14.2%;"> About : </label> <br>
          <input id="readOnly" type="text" name="Host_since" value= <% out.println(user.getHost_since()); %> readonly/>
          <input type="text" name="Host_location" value= <% out.println(user.getHost_location()); %> />
          <input type="text" name="Host_about" value= <% out.println(user.getHost_about()); %> /> <br> <br>
          <label style="padding-left: 1%;"> Response Time :</label> <label style="padding-left: 14.2%;">  Responce Rate :</label>  <label style="padding-left: 14.2%;"> Acceptance Rate : 
          </label> 
          <br> 
          <input type="text" name="Host_responce_time" value= <% out.println(user.getHost_responce_time()); %> />
          <input id="readOnly" type="text" name="Host_responce_rate" value= <% out.println(user.getHost_responce_rate()); %> readonly/>
          <input id="readOnly" type="text" name="Host_acceptance_rate" value= <% out.println(user.getHost_acceptance_rate()); %> readonly/> <br> <br>
          <label style="padding-left: 1%;"> Superhost :</label>  <label style="padding-left: 14.2%;"> Neighbourhood : </label> <label style="padding-left: 14.2%;"> Listings : </label> <br>
          <input type="text" name="Host_is_superhost" value= <% out.println(user.getHost_is_superhost()); %> />
          <input type="text" name="Host_neighbourhood" value= <% out.println(user.getHost_neighbourhood()); %> />
          <input id="readOnly" type="text" name="Host_listings_count" value= <% out.println(user.getHost_listings_count()); %> readonly/> <br> <br>
          <label style="padding-left: 1%;"> Total Listings  :</label> <label style="padding-left: 14.2%;"> Verifications :</label>  <label style="padding-left: 14.2%;"> Profile Picture : 
          </label> 
          <br> 
          <input id="readOnly" type="text" name="Host_total_listings_count" value= <% out.println(user.getHost_total_listings_count()); %> readonly/>
          <input type="text" name="Host_verifications" value= <% out.println(user.getHost_verifications()); %> />
          <input id="readOnly" type="text" name="Host_has_profile_pic" value= <% out.println(user.getHost_has_profile_pic()); %> readonly/> <br> <br>
          <label style="padding-left: 1%;"> Identity Verified :</label>  <br>
          <input id="readOnly" type="text" name="Host_identity_verified" value= <% out.println(user.getHost_identity_verified()); %> readonly/> <br> <br> <br>

      <%
        }      
      %>
        <input class="regiSubmit" type="submit" value="Update" name="submitRegi" required/><br />
        </div>
    </form>
    </div>
    
    <div style="position:absolute; top:450px; left:90px; ">
    
    <form method="post" enctype="multipart/form-data" action="../Update_user_photo">
		<label style="width: 200px; margin-left: 1%; font-size: 20px; color:black;"> <strong> Select image to upload: </strong> </label> <br> <br>
		<input type="file" name="file" size="60" /><br />
        <br /> <input type="submit" value="Upload" />
	</form>
	
	</div>
	
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