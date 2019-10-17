package oppgave2;

import oppgave1.GPSPoint;

public class GPSDataConverter {

	// konverter tidsinformasjon i gps data punkt til antall sekunder fra midnatt

	private static int TIME_STARTINDEX = 11; // startindex for tidspunkt i timestr

	public static int toSeconds(String timestr) {
		
		int secs;
		int hr, min, sec;

		hr = Integer.parseInt(timestr.substring(TIME_STARTINDEX,13));
		min = Integer.parseInt(timestr.substring(14,16));
		sec = Integer.parseInt(timestr.substring(17,19));

		secs = hr*60*60+min*60+sec;

		return secs;
	}

	public static GPSPoint convert(String timeStr, String latitudeStr, String longitudeStr, String elevationStr) {
		//oppretter gpspoint objekt som går ut i fra dataen gitt i metodekallet
		GPSPoint gpspoint;

		int time = toSeconds(timeStr);
		double latitude = Double.parseDouble(latitudeStr);
		double longitude = Double.parseDouble(longitudeStr);
		double elevation = Double.parseDouble(elevationStr);

		gpspoint = new GPSPoint(time, latitude, longitude, elevation);

		return gpspoint;
	    
	}
	
}
