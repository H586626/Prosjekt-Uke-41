package oppgave2;

import oppgave1.GPSPoint;

import static oppgave2.GPSDataConverter.convert;

public class GPSData {
	// Starter med konstruktør for klassen som oppretter tabell med gps punkter
	private GPSPoint[] gpspoints;
	protected int antall = 0;

	public GPSData(int n) {

		gpspoints = new GPSPoint[n];
	}

	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	//Skal sette inn gpspunkt inn i tabellen hvis det er plass og innkrementere antall hvis den blir satt inn
	protected boolean insertGPS(GPSPoint gpspoint) {

		boolean inserted = false;

		if (antall < gpspoints.length){
			gpspoints[antall] = gpspoint;
			inserted = true;
			antall++;
		}

		return inserted;
	}

	public boolean insert(String time, String latitude, String longitude, String elevation) {
		//tar data fra ett metodekall og setter inn tilsvarende gpspunkt inn i tabellen med bruk av metoder allerede laget
		GPSPoint gpspoint;

		gpspoint = convert(time, latitude, longitude, elevation);

		boolean inserted = insertGPS(gpspoint);

		return inserted;

	}

	public void print() {

		System.out.println("====== Konvertert GPS Data - START ======");

		for (int i = 0; i < gpspoints.length; i++){
			System.out.print(gpspoints[i].toString());
		}
		
		// System.out.println("====== Konvertert GPS Data - SLUTT ======");

	}
}
