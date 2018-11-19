<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "ex" uri = "/WEB-INF/custom.tld"%>
<%@ page import="entities.User" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title> Admin Home | Rentvng </title>
     <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href ="../css_scripts/Administrator_Home_Page.css">
    <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Lato"> 
    <script type="text/javascript" src="../js_scripts/testFrame.js"></script> 
  </head>
  <body>
  
	<form method="post" action="../Listing_details">
		<label> Listing </label>
		<label> Listing_id :</label><br>
	    <input type="text" name="Listing_id" placeholder="listing_id" /><br><br>
	    <input type="submit" name="submit" value="GO" />
	</form>
	
	
	<form method="post" enctype="multipart/form-data" action="../Upload_image">
		Select file to upload: <input type="file" name="file" size="60" /><br />
		Message : <input type="text" name="message">
		<input type="hidden" name="hidden_var" value="24">
        <br /> <input type="submit" value="Upload" />
	</form>
	
	<form method="post" action="../lala">
		Message : <input type="text" name="message">
		<input type="hidden" name="hidden_var" value="24">
        <br /> <input type="submit" value="lala" />
	</form>
	
	
	<div>
	
	<img alt="akatsuki" src="/Airbnb/user_images/akatsuki.png">
	
	<img alt="lala" src="file:///C:/Users/Geo/workspace/Airbnb/WebContent/user_images/1253726_1374831957304_full.jpg">
	
	<img  alt='logo2' src='/Airbnb/user_images/logo.png' width='50' height='55'>
	
	<img alt="profile_img3" src="https://i.pinimg.com/736x/47/54/30/475430f563568f99f70f48c94b1d944e--bleach-art-bleach-anime.jpg">
	
	<img src='../Image_servlet/akatsuki.png'>
	
	<img src="../Image_servlet/Akeno_-_Do_I_Look_Good_In_It.jpg" />
	
	<img src="${pageContext.request.contextPath}/Image_servlet/1253726_1374831957304_full.jpg" width='50' height='55' />
	
	<img src="${pageContext.request.contextPath}/Image_servlet/\images\logo.png" />
	
	</div>
    
  </body>
</html>