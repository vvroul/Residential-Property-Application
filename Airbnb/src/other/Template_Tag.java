package other;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.io.*;
import java.util.Arrays;

import entities.User;

public class Template_Tag extends SimpleTagSupport {
	
	private User user = null;
	
	private String login_err_message = null;
	
	private String current_page = null;
	
	private static String[] whiteList = {
							"/jsp_scripts/Administrator_Home_Page.jsp",
							"/jsp_scripts/Administrator_User_Page.jsp",
							"/jsp_scripts/Host_Listing_Details.jsp",
							"/jsp_scripts/Host_Page.jsp",
							"/jsp_scripts/Listing_Details.jsp",
							"/jsp_scripts/View_Rents.jsp",
							"/jsp_scripts/Register_Authentication.jsp",
							"/jsp_scripts/Become_A_Host.jsp",
							"/jsp_scripts/Administrator_XML_Page.jsp",
							"/jsp_scripts/Select_Booking.jsp"};
	
	public void setUser(User user) {
	      this.user = user;
	}
	
	public void setLogin_err_message(String login_err_message) {
	      this.login_err_message = login_err_message;
	}
	
	public void setCurrent_page(String current_page) {
	      this.current_page = current_page;
	}
	
    public void doTag() throws JspException, IOException {
    	
    	JspWriter out = getJspContext().getOut();
    	String html_code = null;
    	
    	
    	if ( ( login_err_message != "") && (login_err_message != null) ) {
    		
    		html_code = "<div id='myModal' class='modal'>" +
	        "<span class='close'>&times;</span>" +
	        "<iframe class='modal-content' id='myFrame'> </iframe>" +
	        "<div id='caption'></div></div>" +
	    	"<script>showLogin();</script>";
    		
    		login_err_message = null;
    	}
    	else {
    		
    		html_code = "<ul>" +
	        "<div id='media'> " +
    		"<a href='Welcome_Page.jsp'>" +
    		"<img  alt='logo' src='../images/logo.png' width='50' height='55'>" +
	        "</a></div>";
    		
    		if (Arrays.asList(whiteList).contains(current_page))
    		{
    			html_code += "<form id='searchForm' style='height: 8%;' action='../Search'> "
		    	      + "<span id='theSpan'> Where: </span> <span id='theSpan' style='padding-left: 5.4em'> When: </span> <br>" 
		    	      + "<input class='templateInput' style='width: 15%;' type='text' name='Location' placeholder='Anywhere' onfocus='this.placeholder='Country, city, district'' onblur='this.placeholder='Anywhere''>" 
		    	      + "<input class='templateInput' style='margin-left: 3em; width: 17%;' placeholder='Anytime' class='textbox-n' type='text' onfocus=\"(this.type='date')\" onblur='(this.type='text')' id='date' name='Date1'>"
		    	      + "<input class='templateInput' style='margin-left: 3em; width: 17%;' placeholder='Anytime' class='textbox-n' type='text' onfocus=\"(this.type='date')\" onblur='(this.type='text')' id='date' name='Date2'>"
		    	      + "<span id='theSpan' style='padding-left: 5em;'> Guests: <input style='margin-left: 1em;' type='number' name='Accommodates' value='1' min='1' max='10'>  </span>"
		    	      + "<input type='hidden' name='filters' value='false'>"
		    	      + "<input id='submitSearch' type='submit' value='Submit'>"
		    	      + "</form>";
    		}
	    	
	    	
	    	if (user == null) {
	    		html_code += "<div id ='topRight'>"  +
	            "<li><a href='Register_Page.jsp'> Register </a></li>" +
	            "<li><a href='' onclick='showLogin(); return false;'> Login </a></li>" +
	            "<div id='myModal' class='modal'>" +
	            "<span class='close'>&times;</span>" +
	            "<iframe class='modal-content' id='myFrame'> </iframe>" +
	            "<div id='caption'></div></div></div>"; 
	    	}
	    	else if (user.getAdmin_level() == 1) {
	    		html_code += "<div id ='topRight'> <li><a href='Become_A_Host.jsp'> Become a host </a></li>" +
	    		"<li><a href='Profile_Page.jsp'> Profile </a></li>" +
	    		"<li><a href='../Logout'> Logout </a></li> </div>";
	    	}
	    	else if ( (user.getAdmin_level() == 2) || (user.getAdmin_level() == 3) ) {
	    		html_code += "<div id ='topRight'><li><a href='Profile_Page.jsp'> Profile </a></li>" +
	    		"<li><a href='../Host_page'> Host Page </a></li>" +
	    		"<li><a href='../Logout'> Logout </a></li></div>";
	    	}
	    	else {
	    		html_code += "<div id ='topRight'><li><a href='Administrator_Home_Page.jsp'> Admin Page </a></li>" +
	    		"<li><a href='../Logout'> Logout </a></li> </div>";
	    	}
	    	
	    	html_code += "</ul>";
	    	
    	}
    	
    	
    	out.println(html_code);
    }
   
}
