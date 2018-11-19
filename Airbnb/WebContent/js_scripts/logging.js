function showLogin()
{
	// Get the modal
	var modal = document.getElementById('myModal');
	var modalImg = document.getElementById("myFrame");
	var captionText = document.getElementById("caption");

    modal.style.display = "block";
    modalImg.src = "../php_scripts/loginTest.php";
    modalImg.width = "500px";
    modalImg.height = "500px";

	// Get the <span> element that closes the modal
	var span = document.getElementsByClassName("close")[0];

	// When the user clicks on <span> (x), close the modal
	span.onclick = function() { 
	    modal.style.display = "none";
	}
}