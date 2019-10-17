package oppgave3;

import oppgave1.GPSPoint;

import static java.lang.Math.*;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max; 
		
		max = da[0];
		
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		
		return max;
	}

	// finner minste tall i en tabellen
	public static double findMin(double[] da) {

		double min;

		min = da[0];

		for (double d: da){
			if (d < min) {
				min = d;
			}
		}

		return min;

	}
	// tar en tabell med gps punkter og retuneren en tabell med bredde

	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		double[] ny = new double[gpspoints.length];

		for (int i = 0; i < ny.length; i++){
			ny[i] = gpspoints[i].getLatitude();
		}

		return ny;
	}

	// tar en tabell med gps punkter og retuneren en tabell med lengdegrad

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		double[] ny = new double[gpspoints.length];

		for (int i = 0; i < ny.length; i++){
			ny[i] = gpspoints[i].getLongitude();
		}

		return ny;

	}

	private static int R = 6371000; // jordens radius

	//beregner avstand i meter mellom to gps punkter med bruk av haversine-formelen

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double d;
		double latitude1, longitude1, latitude2, longitude2;

		latitude1 = toRadians(gpspoint1.getLatitude());
		latitude2 = toRadians(gpspoint2.getLatitude());
		longitude1 = toRadians(gpspoint1.getLongitude());
		longitude2 = toRadians(gpspoint2.getLongitude());

		double lat = latitude2 - latitude1;
		double lon = longitude2 - longitude1;

		double a = Math.pow(sin(lat/2),2) + cos(latitude1) * cos(latitude2) * Math.pow(sin(lon/2),2);
		double c = 2 * atan2(sqrt(a),sqrt((1-a)));
		d = R*c;

		return d;

	}

	// gjennomsnittshastigeten fra et punkt til et annet i km/t

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int secs;
		double speed;

		// TODO - START

		secs = (gpspoint2.getTime() - gpspoint1.getTime());
		speed = ((distance(gpspoint1, gpspoint2) / secs) * 3.6);

		return speed;
		// TODO - SLUTT

	}

	// formarterer sekunder til hh:mm:ss

	public static String formatTime(int secs) {

		String timestr;
		String TIMESEP = ":";

		// TODO - START

		int timer = secs / 3600;
		int rest = secs%3600;
		int minutter = rest/60;
		int rest2 = rest%60;

		timestr = String.format("%1$10s",(String.format("%02d",timer)+TIMESEP+String.format("%02d",minutter)+TIMESEP+String.format("%02d",rest2)));

		return timestr;
		
		// TODO - SLUTT

	}
	private static int TEXTWIDTH = 10;

	//runder av til to desimaltall i en string og fyller inn mellomrom for at lengden alltid skal være ti.

	public static String formatDouble(double d) {

		String str;

		// TODO - START
		double svar = (double) round(d * 100) / 100;

		str = String.format("%1$"+TEXTWIDTH+"s", svar);

		return str;

		// TODO - SLUTT
		
	}
}
