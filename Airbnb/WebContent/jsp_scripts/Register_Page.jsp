<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "ex" uri = "/WEB-INF/custom.tld"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title> Register to Rentvng </title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href ="../css_scripts/TemplateStyle.css">
    <link rel="stylesheet" type="text/css" href ="../css_scripts/Register_Page.css">
    <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Lato"> 
    <script type="text/javascript" src="../js_scripts/testFrame.js"></script> 
  </head>
  <body>
  
  	<% session.setAttribute("current_page","/jsp_scripts/Register_Page.jsp"); %>
  	<% String register_err_message = (String)session.getAttribute("register_err_message"); %>
  
    <ex:Template></ex:Template>


    <h1> Register </h1> 

    <br> 

    <br> 
    	
    <div id='errorMessage'> <% if (register_err_message != null) { out.println(register_err_message); session.setAttribute("register_err_message", null); } %></div>
    
    <br> <br>
    

    <div style="padding-left: 5%;">


    <form style="padding-left: 7.5%;" method="post" action="../Register">
      <label>Username :</label> <br>
      <input type="text" name="Username" required /> <br> <br>
      <label>Password :</label> <label style="padding-left: 13.7%;">Confirm Password :</label> <label style="padding-left: 8.8%;"> E-mail : </label> 
      <label style="padding-left: 15.4%;"> Role selection : </label> <br> 
      <input type="password" name="Password" required/>
      <input style="margin-left: 5%;" type="password" name="Confirm_password" required/>
      <input style="margin-left: 5%;" type="text" name="Email" required/>
      <select required id="priceSelector" onchange="showDiv(this);" name="Admin_level" style="margin-left: 5%; width: 175px;" class="reasons" >
          <option value="1"> Tenant </option>
          <option value="2"> Host </option>
          <option value="3"> Both </option>
        </select> <br> <br>
      <label> Name : </label> <label style="padding-left: 15.7%;"> Surname :</label> <label style="padding-left: 14.2%;"> Telephone :</label><br> 
      <input type="text" name="Name" required/>
      <input style="margin-left: 5%;" type="text" name="Surname" required/>
      <input style="margin-left: 5%;" type="text" name="Contact_number" required/>
      <br><br>
      <div id="hidden_div" style="display: none;">
        <label> Location :</label>  <label style="padding-left: 14.2%;"> About : </label> <label style="padding-left: 15.5%;"> Response Time : </label> <br>
        <input type="text" name="Host_location" />
        <input style="margin-left: 5%;" type="text" name="Host_about" />
        <input style="margin-left: 5%;" type="text" name="Host_responce_time" /> <br> <br>
        <label> Superhost (1 or 0) :</label> <label style="padding-left: 9.2%;">  Neighbourhood :</label>  <label style="padding-left: 10.2%;"> Verifications : 
        </label> 
        <br> 
        <input type="text" name="Host_is_superhost" />
        <input style="margin-left: 5%;" type="text" name="Host_neighbourhood" />
        <input style="margin-left: 5%;" type="text" name="Host_verifications" /> 
      </div>
        <br> <br>
        <input class="regiSubmit" type="submit" value="Proceed" name="submitRegi" /><br />
    </form>
    </div>    
 
  </body>
</html>