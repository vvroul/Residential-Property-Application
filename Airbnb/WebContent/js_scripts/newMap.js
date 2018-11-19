var locations;
var name;

function FillLocations(myArg) {
	locations = myArg;
}

function FillNames(myArg) {
	name = myArg;
}

function initMap() {

  var map = new google.maps.Map(document.getElementById('map'), {
    zoom: 13,
    center: locations[0]
  });

  var markers = locations.map(function(location, i) {
	    return new google.maps.Marker({
	      position: location,
	      label: name
	    });
});

  // Add a marker clusterer to manage the markers.
  var markerCluster = new MarkerClusterer(map, markers,
      {imagePath: 'https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/m'});
}
