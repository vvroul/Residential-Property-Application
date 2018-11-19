function changeTheCheckbox(argument) 
{
	if (argument == 1)
	{
		var checkBox = document.getElementsByClassName("fieldTrips");
		var elements = document.getElementsByClassName("checkOne");
		var elementsLength = elements.length;

		if (!checkBox.checked)
		{
			checkBox.checked = !checkBox.checked;
			for (var i = 0; i <= elementsLength ; i++) {
				elements[i].style.visibility = 'hidden';
			}
		}
		else
		{
			checkBox.checked = !checkBox.checked;
			for (var i = 0; i <= elementsLength ; i++) {
				elements[i].style.visibility = 'visible';
			}
		}
	}

	if (argument == 2)
	{
		var checkBox = document.getElementsByClassName("speakers");
		var elements = document.getElementsByClassName("checkTwo");
		var elementsLength = elements.length;
		
		if (!checkBox.checked)
		{
			checkBox.checked = !checkBox.checked;
			for (var i = 0; i <= elementsLength ; i++) {
				elements[i].style.visibility = 'hidden';
			}
		}
		else
		{
			checkBox.checked = !checkBox.checked;
			for (var i = 0; i <= elementsLength ; i++) {
				elements[i].style.visibility = 'visible';
			}
		}
	}

	if (argument == 3)
	{
		var checkBox = document.getElementsByClassName("workshops");
		var elements = document.getElementsByClassName("checkThree");
		var elementsLength = elements.length;
		
		if (!checkBox.checked)
		{
			checkBox.checked = !checkBox.checked;
			for (var i = 0; i <= elementsLength ; i++) {
				elements[i].style.visibility = 'hidden';
			}
		}
		else
		{
			checkBox.checked = !checkBox.checked;
			for (var i = 0; i <= elementsLength ; i++) {
				elements[i].style.visibility = 'visible';
			}
		}
	}

	if (argument == 4)
	{
		var checkBox = document.getElementsByClassName("others");
		var elements = document.getElementsByClassName("checkFour");
		var elementsLength = elements.length;

		
		if (!checkBox.checked)
		{
			checkBox.checked = !checkBox.checked;
			for (var i = 0; i <= elementsLength ; i++) {
				elements[i].style.visibility = 'hidden';
			}
		}
		else
		{
			checkBox.checked = !checkBox.checked;
			for (var i = 0; i <= elementsLength ; i++) {
				elements[i].style.visibility = 'visible';
			}
		}
	}

	if (argument == 5)
	{
		var checkBox = document.getElementsByClassName("social");
		var elements = document.getElementsByClassName("checkFive");
		var elementsLength = elements.length;
		
		if (!checkBox.checked)
		{
			checkBox.checked = !checkBox.checked;
			for (var i = 0; i <= elementsLength ; i++) {
				elements[i].style.visibility = 'hidden';
			}
		}
		else
		{
			checkBox.checked = !checkBox.checked;
			for (var i = 0; i <= elementsLength ; i++) {
				elements[i].style.visibility = 'visible';
			}
		}
	}
}