package com.ynov.transpotter.helpers;

import com.ynov.transpotter.exceptions.NegativeRadiusException;
import com.ynov.transpotter.models.Coordinates;

public class GeoHelper {

    // La terre a un rayon différent à l'équateur et aux pôles (négligeable pour
    // distances courtes)
    static double radiusEarthPolar = 6356.752;
    static double radiusEarthEquator = 6378.137;

    // On calcule le périmètre avec les deux rayons
    static double perimeterEarthEquator = 2 * Math.PI * radiusEarthEquator;
    static double perimeterEarthPolar = 2 * Math.PI * radiusEarthPolar;

    /**
     * Johann PONSET - 2015 Cette fonction renvoie true si les coordonnées pos1
     * sont comprises dans un rayon de radiusKm (par défaut 1) kilomètre à
     * partir du centre défini par les coordonnées pos2 Sinon elle renvoie false
     * 
     * @param position1
     * @param radiusKm
     * @param position2
     * @return boolean
     * @throws Exception
     */
    public static boolean isInRadius(Coordinates position1, int radiusKm, Coordinates position2) throws Exception {
	if (radiusKm < 0) {
	    throw new NegativeRadiusException();
	}

	// - Ne peut pas dépasser la moitié du périmètre de la Terre car
	// inutile.
	if (radiusKm > (perimeterEarthEquator / 2)) {
	    radiusKm = (int) Math.abs(perimeterEarthEquator / 2);
	}

	radiusKm = Math.abs(radiusKm);

	// On calcule le modulo par 360 des valeurs entrées, 360° étant un tour
	// de la terre
	double position1Lat = position1.getLat() % 360;
	double position1Lon = position1.getLon() % 360;

	double position2Lat = position2.getLat() % 360;
	double position2Lon = position2.getLon() % 360;

	// On calcule à combien de kilomètres équivaut un degré GPS.
	double kmByDegreeLat = perimeterEarthPolar / 360;
	double kmByDegreeLon = perimeterEarthEquator / 360;

	// On calcule la différence de coordonnées entre le centre et le point
	// donné.
	double differenceLat = Math.abs(position2Lat - position1Lat);
	double differenceLon = Math.abs(position2Lon - position1Lon);

	// On traduit cette différence en KMs.
	double differenceKmLat = differenceLat * kmByDegreeLat;
	double differenceKmLon = differenceLon * kmByDegreeLon;

	// On peut enfin calculer la distance du centre à notre point.
	double radiusKmFromCenter = Math.sqrt(Math.pow(differenceKmLat, 2) + Math.pow(differenceKmLon, 2));

	// Si la distance calculée est inférieure ou égale au rayon désiré, le
	// point donné est dans le cercle.

	return radiusKm > radiusKmFromCenter;
    }

    public static double getDistance(Coordinates position1, Coordinates position2) {

	// On calcule le modulo par 360 des valeurs entrées, 360° étant un tour
	// de la terre
	double position1Lat = position1.getLat() % 360;
	double position1Lon = position1.getLon() % 360;

	double position2Lat = position2.getLat() % 360;
	double position2Lon = position2.getLon() % 360;

	// On calcule à combien de kilomètres équivaut un degré GPS.
	double kmByDegreeLat = perimeterEarthPolar / 360;
	double kmByDegreeLon = perimeterEarthEquator / 360;

	// On calcule la différence de coordonnées entre le centre et le point
	// donné.
	double differenceLat = Math.abs(position2Lat - position1Lat);
	double differenceLon = Math.abs(position2Lon - position1Lon);

	// On traduit cette différence en KMs.
	double differenceKmLat = differenceLat * kmByDegreeLat;
	double differenceKmLon = differenceLon * kmByDegreeLon;

	// On peut enfin calculer la distance du centre à notre point.
	double distanceKM = Math.sqrt(Math.pow(differenceKmLat, 2) + Math.pow(differenceKmLon, 2));

	return distanceKM;
    }

    public static Coordinates getNearestOnSegment(double latitudeOrigine, double longitudeOrigne, double latitudeDest,
	    double longitudeDest, double latitudeDest2, double longitudeDest2) {
	Coordinates returnCoord = null;
	double angle1 = getAngle2(latitudeDest, longitudeDest, latitudeOrigine, longitudeOrigne, latitudeDest2,
		longitudeDest2);
	double angle2 = getAngle2(latitudeDest2, longitudeDest2, latitudeOrigine, longitudeOrigne, latitudeDest,
		longitudeDest);

	if (angle1 >= 90 || angle2 >= 90) {
	    if (angle1 >= 90)
		returnCoord = new Coordinates(longitudeDest, latitudeDest);
	    else
		returnCoord = new Coordinates(longitudeDest2, latitudeDest2);
	} else {
	    // Si dans le segment
	    double aire = ((getDistance(new Coordinates(longitudeDest, latitudeDest),
		    new Coordinates(longitudeOrigne, latitudeOrigine))
		    * getDistance(new Coordinates(longitudeDest, latitudeDest),
			    new Coordinates(longitudeDest2, latitudeDest2)))
		    / 2) * Math.sin(Math.toRadians(angle1));
	    double hauteur = (2 * aire) / (getDistance(new Coordinates(longitudeDest, latitudeDest),
		    new Coordinates(longitudeDest2, latitudeDest2)));

	    double distanceFromDestToPoint = Math
		    .sqrt(Math.pow(getDistance(new Coordinates(longitudeDest, latitudeDest),
			    new Coordinates(longitudeOrigne, latitudeOrigine)), 2) - Math.pow(hauteur, 2));

	    double xPoint = latitudeDest + ((distanceFromDestToPoint * (latitudeDest2 - latitudeDest) / (Math.sqrt(
		    Math.pow((latitudeDest - latitudeDest2), 2) + Math.pow((longitudeDest - longitudeDest2), 2)))));

	    double yPoint = longitudeDest + ((distanceFromDestToPoint * (longitudeDest2 - longitudeDest) / (Math.sqrt(
		    Math.pow((latitudeDest - latitudeDest2), 2) + Math.pow((longitudeDest - longitudeDest2), 2)))));
	    returnCoord = new Coordinates(xPoint, yPoint);
	}

	return returnCoord;

    }

    public static double getAngle(double lat1, double lon1, double lat2, double lon2) {

	double longDelta = lon2 - lon1;
	double angleX = Math.cos(Math.toRadians(lat2)) * Math.sin(Math.toRadians(longDelta));
	double angleY = Math.cos(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2))
		- Math.sin(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(longDelta));

	double angle = Math.toDegrees(Math.atan2(Math.toRadians(angleX), Math.toRadians(angleY)));

	return angle;
    }

    public static float getAngle2(double latitudeOrigine, double longitudeOrigne, double latitudeDest,
	    double longitudeDest, double latitudeDest2, double longitudeDest2) {
	double angle = getAngle(latitudeOrigine, longitudeOrigne, latitudeDest, longitudeDest);
	double angle2 = getAngle(latitudeOrigine, longitudeOrigne, latitudeDest2, longitudeDest2);

	float result = 0.0f;
	if (angle > angle2)
	    result = (float) (angle - angle2);
	else
	    result = (float) (angle2 - angle);

	return (float) result % 360;
    }
}
