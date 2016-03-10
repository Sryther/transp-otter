<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
		<!--importation de l'API google MAP Version 3-->
		<script type="text/javascript" src="http://maps.google.com/maps/api/js? sensor=false"></script>
		<script type="text/javascript" language = "javascript">
			var directionsService = new google.maps.DirectionsService(); 
			// service GoogleMaps 
			var map,geocoder, marker, marker2;
			// La carte, le service de géocodage et les marqueurs 
			var depart,arrivee,ptCheck; // point de départ, arrivé et de vérification 
	
			/*initialise google MAP V3*/ 
			function init() { 
				/*gestion des routes*/ 
				directionsDisplay = new google.maps.DirectionsRenderer(); /*emplacement par défaut de la carte (Toulouse)*/ 
				var maison = new google.maps.LatLng(43.6042600, 1.4436700); /*option par défaut de la carte*/
				var myOptions = {
					zoom:8,  
					mapTypeId: google.maps.MapTypeId.ROADMAP,
					center: maison
				} /*creation de la map*/ 
				map = new google.maps.Map(document.getElementById("divMap"), myOptions); 
				/*connexion de la map + le panneau de l'itinéraire*/ 
				directionsDisplay.setMap(map); 
				directionsDisplay.setPanel(document.getElementById("divRoute")); 
				/*intialise le geocoder pour localiser les adresses */ 
				geocoder = new google.maps.Geocoder(); 
			} 
	
			function trouveRoute() { 
				/*test si les variables sont bien initialisés*/ 
				if (depart && arrivee){  
					var request = {    
							origin:depart,    
							destination:arrivee,    
							travelMode: google.maps.DirectionsTravelMode["DRIVING"]  
					};  
					/*appel à l'API pour tracer l'itinéraire*/  
					directionsService.route(request, function(response, status) {
						if (status == google.maps.DirectionsStatus.OK) {      
							directionsDisplay.setDirections(response);   
							var monTrajet=response.routes[0] ; 
							var point0=monTrajet.overview_path[0];
							// Position (B:, K:) 
							var latPoint0=point0.K; 
							// Latitude de départ du premier segment 
							var longPoint0=point0.B; 
							// Longitude de départ du premier segment
						}  
					});
				}
			}
	
			function rechercher(src,src2){ 
				// ptCheck = code; 
				/*adresse de départ ou arrivée ? */ 
				if (geocoder) {  
					geocoder.geocode( { 'address': document.getElementById(src).value}, function(results, status) {
						if (status == google.maps.GeocoderStatus.OK) {      
							/*ajoute un marqueur à l'adresse choisie*/      
							map.setCenter(results[0].geometry.location);      
							if (marker) { 
								marker.setMap(null);
							}        
							marker = new google.maps.Marker({        
								map: map,        
								position: results[0].geometry.location      
							});      
							/*on remplace l'adresse par celle fournie du geocoder*/      
							document.getElementById(src).value = results[0].formatted_address;      
							depart = results[0].formatted_address;      
							/*trace la route*/
					    }  
					});  
					geocoder.geocode( { 'address': document.getElementById(src2).value}, function(results, status) {
						if (status == google.maps.GeocoderStatus.OK) {
							/*ajoute un marqueur à l'adresse choisie*/      
							if (marker2) { 
								marker2.setMap(null);
							}      
							marker2 = new google.maps.Marker({        
								map: map,        
								position: results[0].geometry.location      
							});      
							/*on remplace l'adresse par celle fournie du geocoder*/        
							document.getElementById(src2).value = results[0].formatted_address;     
							arrivee = results[0].formatted_address;      
							/*trace la route*/    
						}    
						trouveRoute();  
					}); 
				} 
			}
		</script> 
	</head>
	<body onload="init();">
		<div>
			<table>
				<tr>
					<td>
						<b>Départ: </b>
					</td>
					<td>
						<input type="text" id="adrDep" value="" style="width:300px;">
					</td>
					<td>
						<b>Transport: </b> 
						<select id="mode" onchange="calcRoute();"> 
							<option value="DRIVING">voiture</option> 
							<option value="WALKING">marche</option> 
							<option value="BICYCLING">vélo</option> 
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<b>Arrivée: </b>
					</td>
					<td>
						<input type="text" id="adrArr" value="" style="width:300px;">
					</td>
					<td>
						<input type="button" value="Recherche" onclick="rechercher('adrDep','adrArr');">
					</td>
				</tr>
			</table>
		</div>
		<div id="divMap" >
		
		</div>
		<div id="divRoute" style="float:right;width:30%;height: 80%">
		
		</div>
		<br/>
		<center>
		
		</center>
	</body>
</html>