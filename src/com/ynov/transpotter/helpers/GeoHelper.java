package com.ynov.transpotter.helpers;

import com.ynov.transpotter.exceptions.NegativeRadiusException;
import com.ynov.transpotter.models.Coordinates;

public class GeoHelper {
	
	// La terre a un rayon différent à l'équateur et aux pôles (négligeable pour distances courtes)
	static double radiusEarthPolar = 6356.752;
	static double radiusEarthEquator = 6378.137;
	
	// On calcule le périmètre avec les deux rayons
	static double perimeterEarthEquator = 2 * Math.PI * radiusEarthEquator;
	static double perimeterEarthPolar = 2 * Math.PI * radiusEarthPolar;

	
	/**
	 * Johann PONSET - 2015
	 * Cette fonction renvoie true si les coordonnées pos1 sont comprises
	 * dans un rayon de radiusKm (par défaut 1) kilomètre à partir du centre défini par les coordonnées pos2
	 * Sinon elle renvoie false
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
		
        // - Ne peut pas dépasser la moitié du périmètre de la Terre car inutile.
        if(radiusKm > (perimeterEarthEquator /2 )) {
            radiusKm = (int) Math.abs(perimeterEarthEquator / 2);
        }
        
        radiusKm = Math.abs(radiusKm);
        
		// On calcule le modulo par 360 des valeurs entrées, 360° étant un tour de la terre
		double position1Lat = position1.getLat() % 360;
		double position1Lon = position1.getLon() % 360;
		
		double position2Lat = position2.getLat() % 360;
		double position2Lon = position2.getLon() % 360;
		
		// On calcule à combien de kilomètres équivaut un degré GPS.
        double kmByDegreeLat =  perimeterEarthPolar / 360;
        double kmByDegreeLon = perimeterEarthEquator / 360;
        
        // On calcule la différence de coordonnées entre le centre et le point donné.
        double differenceLat = Math.abs(position2Lat - position1Lat);
        double differenceLon = Math.abs(position2Lon - position1Lon);
        
        // On traduit cette différence en KMs.
        double differenceKmLat = differenceLat * kmByDegreeLat;
        double differenceKmLon = differenceLon * kmByDegreeLon;
      
        // On peut enfin calculer la distance du centre à notre point.
        double radiusKmFromCenter = Math.sqrt(Math.pow(differenceKmLat, 2) + Math.pow(differenceKmLon, 2));
        
        // Si la distance calculée est inférieure ou égale au rayon désiré, le point donné est dans le cercle.
        
        return radiusKm > radiusKmFromCenter;
	}

}
