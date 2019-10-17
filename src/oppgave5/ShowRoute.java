package oppgave5;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import oppgave1.GPSPoint;
import oppgave3.GPSUtils;
import oppgave4.GPSComputer;

import javax.swing.*;
import java.text.DecimalFormat;


public class ShowRoute extends EasyGraphics {

	private static DecimalFormat df2 = new DecimalFormat("#.##");

	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 800;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;
	
	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		showRouteMap(MARGIN + MAPYSIZE);

		//playRoute(MARGIN + MAPYSIZE);
		
		showStatistics();
	}

	// antall x-pixels per lengdegrad
	public double xstep() {

		double maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));

		double xstep = MAPXSIZE / (Math.abs(maxlon - minlon)); 

		return xstep;
	}

	// antall y-pixels per breddegrad
	public double ystep() {
	
		double ystep;
		
		double maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

		ystep = MAPYSIZE / (Math.abs(maxlat - minlat));

		return ystep;

		// TODO - SLUTT
		
	}

	public void showRouteMap(int ybase) {

		// TODO - START
		double[] lats = GPSUtils.getLatitudes(gpspoints);
		double[] lons = GPSUtils.getLongitudes(gpspoints);

		//finner breddegrad for punktet som tilsvarer ybase
		double min = GPSUtils.findMin(lats);

		//tegner punkt og linjer mellom punktene
		//bruker xstep og ystep for å finne ut hvor mange pixler mellom hvert punkt

		for (int i = 0; i < gpspoints.length; i++) {
			pause(20);
			double lon1 = MARGIN + (lons[i] * xstep() - lons[0] * xstep());
			double lat1 = ybase - (lats[i] * ystep() - min* ystep());

			if(i < gpspoints.length - 1) {
				double lon2 = MARGIN + (lons[i+1] * xstep() - lons[0] * xstep());
				double lat2 = ybase - (lats[i+1] * ystep() - min * ystep());
				setColor(0, 255, 0);
				drawLine((int) lon1, (int) lat1, (int) lon2, (int) lat2);
			}
			setColor(0, 255, 0);
			fillCircle((int) lon1, (int) lat1, 2);
		}
	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;

		setColor(0,0,0);
		setFont("Courier",12);
		
		// TODO - START
		drawString("Total Time     :   " + GPSUtils.formatTime(gpscomputer.totalTime()), MARGIN, TEXTDISTANCE);
		TEXTDISTANCE += 20;
		drawString("Total distance :      " + df2.format((gpscomputer.totalDistance()/1000)) + " km", MARGIN, TEXTDISTANCE);
		TEXTDISTANCE += 20;
		drawString("Total elivation:     " + df2.format(gpscomputer.totalElevation()) + " m", MARGIN, TEXTDISTANCE);
		TEXTDISTANCE += 20;
		drawString("Max speed      :      " + df2.format(gpscomputer.maxSpeed()) + " km/t", MARGIN, TEXTDISTANCE);
		TEXTDISTANCE += 20;
		drawString("Average speed  :      " + df2.format(gpscomputer.averageSpeed()) + " km/t", MARGIN, TEXTDISTANCE);
		TEXTDISTANCE += 20;
		drawString("Energy         :     " + df2.format(gpscomputer.totalKcal(80)) + " kcal", MARGIN, TEXTDISTANCE);
		
		// TODO - SLUTT;
	}

	public void playRoute(int ybase) {

		// TODO - START
		
		throw new UnsupportedOperationException(TODO.method());
		
		// TODO - SLUTT
	}

}
