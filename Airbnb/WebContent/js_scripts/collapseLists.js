function showHide(category) 
{ 
	var x = document.getElementsByClassName("collapseButton");

	if (category == 1)
	{
		var theList = document.getElementById("mylist1");
		var theField = document.getElementById("legendary1");
		if (x[0].innerHTML == "View the questions and answers for this category")
		{
			x[0].innerHTML = "Collapse";
			theList.style.display = 'block';
			theField.style.display = 'block';
		}
		else
		{
			x[0].innerHTML = "View the questions and answers for this category";
			theList.style.display = 'none';
			theField.style.display = 'none';
		}
	}
	if (category == 2)
	{
		var theList = document.getElementById("mylist2");
		var theField = document.getElementById("legendary2");
		if (x[1].innerHTML == "View the questions and answers for this category")
		{
			x[1].innerHTML = "Collapse";
			theList.style.display = 'block';
			theField.style.display = 'block';
		}
		else
		{
			x[1].innerHTML = "View the questions and answers for this category";
			theList.style.display = 'none';
			theField.style.display = 'none';
		}
	}
	if (category == 3)
	{
		var theList = document.getElementById("mylist3");
		var theField = document.getElementById("legendary3");
		if (x[2].innerHTML == "View the questions and answers for this category")
		{
			x[2].innerHTML = "Collapse";
			theList.style.display = 'block';
			theField.style.display = 'block';
		}
		else
		{
			x[2].innerHTML = "View the questions and answers for this category";
			theList.style.display = 'none';
			theField.style.display = 'none';
		}
	}
}


function showThem(speaksCat)
{
	if (speaksCat == 1)
	{
		var x = document.getElementById('hid');
	}
	if (speaksCat == 2)
	{
		var x = document.getElementById('otherhid');
	}
	if (speaksCat == 3)
	{
		var x = document.getElementById('workhid');
	}
	
    if (x.style.display === 'none') 
    {
        x.style.display = 'block';
    } 
    else 
    {
        x.style.display = 'none';
    }
}