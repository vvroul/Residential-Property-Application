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
    <link rel="stylesheet" type="text/css" href ="../css_scripts/Administrator_Home_Page.css">
    <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Lato"> 
    <script type="text/javascript" src="../js_scripts/testFrame.js"></script> 
  </head>
  <body>
  
  	<% session.setAttribute("current_page","/jsp_scripts/Administrator_Home_Page.jsp"); %>
  
  	<% 	User user = (User)session.getAttribute("user");
  		@SuppressWarnings("unchecked")
	    ArrayList<User> users_list = (ArrayList<User>)session.getAttribute("users_list"); 
	    int page_counter = 0;
	    if (request.getParameter("page_counter") != null) {
	 	   page_counter = Integer.parseInt(request.getParameter("page_counter"));
	    }
	    int page_size = 24;
    %>
    
    <ex:Template user ="<%= user %>"  current_page ="/jsp_scripts/Administrator_Home_Page.jsp"></ex:Template>
    
    <button onclick="topFunction()" id="myBtn" title="Go to top">Top</button>

    <h1> User Management <span style="padding-left: 5em"> <a href="Administrator_XML_Page.jsp" class="button sendButton"> Export data in XML </a> </span>  </h1>

    <br>
    
    <label class="colorChat" style="color:darkorange"> Orange : User awaiting verification </label> <br>
   	<label class="colorChat"> Green : User verified </label>

    <h2> Users : </h2>
    
    <div id = "users"> 
   
   	<br>
	  
	  <% if ( (users_list != null) && (users_list.size() != 0) ) { %>
	  
		    <% int maxpage_counter = users_list.size()/page_size;
		    if (users_list.size() % page_size != 0) {
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
    	<a href="Administrator_Home_Page.jsp?page_counter=<% out.print(page_counter-1); %>" class="button ticketbutton"> Previous </a>
   		<% out.print((page_counter+1) + " / " + maxpage_counter); %>
   		<a href="Administrator_Home_Page.jsp?page_counter=<% out.print(page_counter+1); %>" class="button ticketbutton"> Next </a>
	 </div>
 
 	<br> <br>
	
	
		  <div class="container">
		  <% for (int i=(page_counter*page_size),count=0; i<users_list.size() && count < page_size; i++ , count++) { %>
		    <% String new_message_color = null;
		       int User2_id = 0;
		  	   if ( (!users_list.get(i).getVerified())) {
		  	     new_message_color = "background-color: darkorange;";
		  	   }
		       else {
		    	 new_message_color = ""; 
		       }
		    %>
		    	<form method='post' action='Administrator_User_Page.jsp'>
		    		<input class="myUserList" name='User_id' type='hidden' value=<% out.println(users_list.get(i).getUser_id()); %> />
		    	    <input class="myUserList" name='Username' type='hidden' value=<% out.println(users_list.get(i).getUsername()); %> />
		    	    <input class="myUserList" name='Name' type='hidden' value=<% out.println(users_list.get(i).getName()); %> />
		    	    <input class="myUserList" name='Surname' type='hidden' value=<% out.println(users_list.get(i).getSurname()); %> />
		    	    <input class="myUserList" name='Email' type='hidden' value=<% out.println(users_list.get(i).getEmail()); %> />
		    	    <input class="myUserList" name='Contact_number' type='hidden' value=<% out.println(users_list.get(i).getContact_number()); %> />
		    	    <input class="myUserList" name='Admin_level' type='hidden' value=<% out.println(users_list.get(i).getAdmin_level()); %> />
		    	    <input class="myUserList" name='Verified' type='hidden' value=<% out.println(users_list.get(i).getVerified()); %> />
		    	    <input class="myUserList" style="<% out.print(new_message_color); %>" type='submit' value=<% out.println(users_list.get(i).getUsername()); %> />
		    	</form>
		  <% } %> 
		  </div>
	  
		  <br> <br>
		  
		  <div id="bottom" style="text-align: center;">
	    	<a href="Administrator_Home_Page.jsp?page_counter=<% out.print(page_counter-1); %>" class="button ticketbutton"> Previous </a>
	   		<% out.print((page_counter+1) + " / " + maxpage_counter); %>
	   		<a href="Administrator_Home_Page.jsp?page_counter=<% out.print(page_counter+1); %>" class="button ticketbutton"> Next </a>
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

    </div>
  </body>
</html>