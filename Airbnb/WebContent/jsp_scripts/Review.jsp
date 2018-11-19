<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <title> Review </title>
  <link rel="stylesheet" type="text/css" href ="../css_scripts/Login.css">
  <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Lato"> 
</head>
<body>

  <h1 style="color:white; padding-left: 30%; padding-top: 10%;"> Write your review </h1>
  <form id="loginForm" method="post" action="../New_review" target="_top">
    <label>Score (0-100) :</label><br>
    <input type="text" name="score" placeholder="score" /><br><br>
    <label>Review :</label><br>
    <input type="text" name="new_message" placeholder="review" />  <br><br>
    <input type="submit" name="submit" value="Submit" /> 
  </form>
</body>
</html>