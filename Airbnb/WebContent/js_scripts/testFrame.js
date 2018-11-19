function showFrame() 
{ 

	var x = document.getElementById("myFrame");
	// document.body.style.opacity = 0.52;
	// x.style.display = 'block';
	// Get the modal
	var modal = document.getElementById('myModal');
	var modalImg = document.getElementById("myFrame");
	var captionText = document.getElementById("caption");

    modal.style.display = "block";
    modalImg.src = "../html_scripts/info.html";
    modalImg.width = "500px";
    modalImg.height = "500px";

	// Get the <span> element that closes the modal
	var span = document.getElementsByClassName("close")[0];

	// When the user clicks on <span> (x), close the modal
	span.onclick = function() { 
	    modal.style.display = "none";
	}

}


function showDiv(elem)
{
	if ((elem.value == 2) || (elem.value == 3))
	{
		document.getElementById('hidden_div').style.display = "block";
	}
	else
	{
		document.getElementById('hidden_div').style.display = "none";
	}

}


var first_click = true;
function showFilters()
{
	if (first_click) 
	{
		document.getElementById('hidden_filter_div').style.display = "block";
		first_click = false;
	}
	else
	{
		document.getElementById('hidden_filter_div').style.display = "none";
		first_click = true;
	}
}


function disableFilters()
{
	if(document.getElementById("wifi").checked) {
	    document.getElementById("nowifi").disabled = true;
	}
	if(document.getElementById("heating").checked) {
	    document.getElementById("noheating").disabled = true;
	}
	if(document.getElementById("cooling").checked) {
	    document.getElementById("nocooling").disabled = true;
	}
	if(document.getElementById("kitchen").checked) {
	    document.getElementById("nokitchen").disabled = true;
	}
	if(document.getElementById("tv").checked) {
	    document.getElementById("notv").disabled = true;
	}
	if(document.getElementById("parking_lot").checked) {
	    document.getElementById("noparking_lot").disabled = true;
	}
	if(document.getElementById("elevator").checked) {
	    document.getElementById("noelevator").disabled = true;
	}
}


function showLogin()
{
	// Get the modal
	var modal = document.getElementById('myModal');
	var modalImg = document.getElementById("myFrame");
	var captionText = document.getElementById("caption");

    modal.style.display = "block";
    modalImg.src = "Login.jsp";
    modalImg.width = "500px";
    modalImg.height = "500px";

	// Get the <span> element that closes the modal
	var span = document.getElementsByClassName("close")[0];

	// When the user clicks on <span> (x), close the modal
	span.onclick = function() { 
	    modal.style.display = "none";
	}
}


function showReview()
{
	// Get the modal
	var modal = document.getElementById('myModal2');
	var modalImg = document.getElementById("myFrame2");
	var captionText = document.getElementById("caption2");

    modal.style.display = "block";
    modalImg.src = "Review.jsp";
    modalImg.width = "500px";
    modalImg.height = "500px";

	// Get the <span> element that closes the modal
	var span = document.getElementsByClassName("close2")[0];

	// When the user clicks on <span> (x), close the modal
	span.onclick = function() { 
	    modal.style.display = "none";
	}
}



function showLogout()
{
	var r = confirm("Do you really want to log out?");
    if (r) {
       window.location.href = 'logout.php'
    }
}


function updateInfo()
{
	var r = confirm("Are you sure you want to update your info?");
    if (r) {
       window.location.reload();
    }
    
}


function insertData()
{
	var r = confirm("Are you sure you want to proceed?");
    if (r) 
    {
       window.location.href = 'insert.php'
    }
}


function goToAnchor(anchor) 
{
  var loc = document.location.toString().split('#')[0];
  document.location = loc + '#' + anchor;
  return false;
}


function showPrice()
{
	var x = document.getElementById("priceSelector").value;
	document.getElementById("price").innerHTML = "Total cost : " + x;
}


function GoToProgram()
{
	window.top.location.href = "../php_scripts/fullProgram.php";
}


function addSelect() 
{
    var x = document.getElementById("resiSelect");
    var option = document.createElement("option");
    option.text = "Kiwi";
    x.add(option);
}



function plusSlides(n) {
  showSlides(slideIndex += n);
}

function currentSlide(n) {
  showSlides(slideIndex = n);
}

function showSlides(n) {
  var i;
  var slides = document.getElementsByClassName("mySlides");
  var dots = document.getElementsByClassName("dot");
  if (n > slides.length) {slideIndex = 1}
  if (n < 1) {slideIndex = slides.length}
  for (i = 0; i < slides.length; i++) {
      slides[i].style.display = "none";  
  }
  for (i = 0; i < dots.length; i++) {
      dots[i].className = dots[i].className.replace(" active", "");
  }
  slides[slideIndex-1].style.display = "block";  
  dots[slideIndex-1].className += " active";
}


function bottom() {
    document.getElementById( 'bottom' ).scrollIntoView();
};

function top() {
    document.getElementById( 'top' ).scrollIntoView();    
};
