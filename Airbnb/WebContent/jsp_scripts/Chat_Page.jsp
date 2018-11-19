<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "ex" uri = "/WEB-INF/custom.tld"%>
<%@ page import="entities.User" %>
<%@ page import="entities.Chat" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title> Chat Page | Rentvng </title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href ="../css_scripts/TemplateStyle.css">
    <link rel="stylesheet" type="text/css" href ="../css_scripts/Chat_Page.css">
    <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Lato"> 
    <script type="text/javascript" src="../js_scripts/testFrame.js"></script>
    <script type="text/javascript" src="angular.min.js"></script>
    <script type="text/javascript" src="jquery-3.2.1.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
    <script src="../js_scripts/myApp.js"></script>
    <script src="../js_scripts/jquery.mixitup.min.js"></script>
    <script src="../js_scripts/main.js"></script> <!-- Resource jQuery -->
  </head>
  <body>


	<% session.setAttribute("current_page","/jsp_scripts/Chat_Page.jsp"); %>
    
    <% User user = (User)session.getAttribute("user");
       User user2 = (User)session.getAttribute("user2");
       String chat_previous_page = (String)session.getAttribute("chat_previous_page");
       @SuppressWarnings("unchecked")
       ArrayList<Chat> chat_list = (ArrayList<Chat>)(session.getAttribute("chat_list")); 
       Chat chat_last_entry = (Chat)session.getAttribute("chat_last_entry"); %>
       
    <ex:Template user ="<%= user %>" current_page ="/jsp_scripts/Chat_Page.jsp"></ex:Template>
    
    
    <!-- <div style="background-color:white; width:1000px; height:500px; margin-left:11%;">
    
    </div> -->
    
    
    <div id="wrapper">
	    <div id="menu">
	        <p class="welcome">Welcome, <% out.print(user.getUsername()); %> <b></b></p>
	        <p class="logout"><a id="exit" href="<% out.print(chat_previous_page); %>">Return</a></p>
	        <div style="clear:both"></div>
	    </div>
	     
	    <div id="chatbox">
	   		<% for (Chat chat : chat_list) { %>
	   			<% String writer = null;
	   			   String writerColor = null;
	   				if (chat.getWho_wrote_it() == user.getUser_id()) { 
  	  					writer = user.getUsername();
  	  					writerColor = "#FE2E9A";
	   				}
  	  				else {
  	  					writer = user2.getUsername();
  	  					writerColor = "#3ADF00";
  	  				}
	  	  		%>
	  	  		<div> <strong style="color:<% out.print(writerColor); %>;"><% out.print(writer); %></strong>: <% out.print(chat.getMessage()); %>
	  	  		<% if (user.getUser_id() == chat.getHost_id()) { %>
	  	  			<a href="../Remove_chat_message<%out.println("?Chat_id="+chat.getChat_id()+"&Message_counter="+chat.getMessage_counter());%>">Delete</a>
	  	  		<% } %>
	  	  		</div>
			<% } %>
	    </div>
    	

	
		<form method="post" action="../Chat_new_message">
	    	<input id="usermsg" type="text" name="new_message" size="63" placeholder="Type a message..." required />
	   	 	<input id="submitmsg" type="submit" name="submit" value="Send" />
		</form>
	     
	</div>
    
    
    
    

  </body>
</html>