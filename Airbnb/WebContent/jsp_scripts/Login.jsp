<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <title> User Login </title>
  <link rel="stylesheet" type="text/css" href ="../css_scripts/Login.css">
  <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Lato"> 
</head>
<body>


	<%  String login_err_message = (String)session.getAttribute("login_err_message");
		session.setAttribute("login_err_message",null);
		if (login_err_message != null) { %>
		
			<div id='errorMessage'> <% out.println(login_err_message); %></div>
		
	 <% } %>


  <h1 style="color:white; padding-left: 30%; padding-top: 10%;"> Welcome to Rentvng </h1>
  <form id="loginForm" method="post" action="../Login" target="_top">
    <label>Username :</label><br>
    <input type="text" name="Username" placeholder="username" /><br><br>
    <label>Password :</label><br>
    <input type="password" name="Password" placeholder="password" />  <br><br>
    <input type="submit" name="submit" value="Login" /> 
  </form>
</body>
</html>