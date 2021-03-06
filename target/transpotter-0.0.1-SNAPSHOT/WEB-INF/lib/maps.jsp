	var directionsService = new google.maps.DirectionsService();
	// service GoogleMaps 
	var map, geocoder, marker, marker2;
	// La carte, le service de géocodage et les marqueurs 
	var depart, arrivee, ptCheck; // point de départ, arrivé et de vérification 

	/*initialise google MAP V3*/
	function init() {
		/*gestion des routes*/
		directionsDisplay = new google.maps.DirectionsRenderer(); /*emplacement par défaut de la carte (Toulouse)*/
		var maison = new google.maps.LatLng(43.6042600, 1.4436700); /*option par défaut de la carte*/
		var myOptions = {
			zoom : 8,
			mapTypeId : google.maps.MapTypeId.ROADMAP,
			center : maison
		} /*creation de la map*/

		map = new google.maps.Map(document.getElementById("map_canvas"),
				myOptions);
		directionsDisplay.setMap(map);

		directionsDisplay.setPanel(document.getElementById("divRoute"));
		/*intialise le geocoder pour localiser les adresses */
		geocoder = new google.maps.Geocoder();
	}

	function trouveRoute(mode) {
		/*test si les variables sont bien initialisés*/
		if (depart && arrivee) {
			var request = {
				origin : depart,
				destination : arrivee,
				travelMode : google.maps.DirectionsTravelMode[mode]
			};
			/*appel à l'API pour tracer l'itinéraire*/
			directionsService.route(request, function(response, status) {
				console.log(response.routes[0]);
				if (status == google.maps.DirectionsStatus.OK) {
					directionsDisplay.setDirections(response);
					var monTrajet = response.routes[0];
					// var point0 = monTrajet.overview_path[0];
					// Position (B:, K:) 
					// var latPoint0 = point0.K;
					// Latitude de départ du premier segment 
					// var longPoint0 = point0.B;
					// Longitude de départ du premier segment
				}
			});
		}
	}

	function rechercher(src, src2, mode, radius) {
		// ptCheck = code; 
		/*adresse de départ ou arrivée ? */
		console.log(document.getElementById(radius).value)
		if (geocoder) {
			geocoder
					.geocode(
							{
								'address' : document.getElementById(src).value
							},
							function(results, status) {
								if (status == google.maps.GeocoderStatus.OK) {
									/*ajoute un marqueur à l'adresse choisie*/
									map.setCenter(results[0].geometry.location);
									if (marker) {
										marker.setMap(null);
									}
									/*marker = new google.maps.Marker({
										map : map,
										position : results[0].geometry.location
									});*/
									/*on remplace l'adresse par celle fournie du geocoder*/
									document.getElementById(src).value = results[0].formatted_address;
									depart = results[0].formatted_address;
									
									var cityCircle = new google.maps.Circle({
									      strokeColor: '#FF0000',
									      strokeOpacity: 0.8,
									      strokeWeight: 2,
									      fillColor: '#FF0000',
									      fillOpacity: 0.35,
									      map: map,
									      center: results[0].geometry.location,
									      radius: parseInt(document.getElementById(radius).value)
									    });
								}
							});
			geocoder
					.geocode(
							{
								'address' : document.getElementById(src2).value
							},
							function(results, status) {
								
								if (status == google.maps.GeocoderStatus.OK) {
									// ajoute un marqueur à l'adresse choisie
									if (marker2) {
										marker2.setMap(null);
									}
									/*marker2 = new google.maps.Marker({
										map : map,
										position : results[0].geometry.location
									});*/
									// on remplace l'adresse par celle fournie du geocoder
									// document.getElementById(src2).value = results[0].formatted_address;
									arrivee = results[0].formatted_address;
									// trace la route
								}
							    trouveRoute(document.getElementById(mode).value);
							});
		}
	}