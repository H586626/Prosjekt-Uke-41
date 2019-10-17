package oppgave5;

import easygraphics.EasyGraphics;
import oppgave1.GPSPoint;
import oppgave4.GPSComputer;

import javax.swing.*;

public class ShowSpeed extends EasyGraphics {
			
	private static int MARGIN = 50;
	private static int BARHEIGHT = 200; // assume no speed above 200 km/t

	private GPSComputer gpscomputer;
	private GPSPoint[] gpspoints;
	
	public ShowSpeed() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();
		
	}
	
	// read in the files and draw into using EasyGraphics
	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		int N = gpspoints.length-1; // number of data points
		
		makeWindow("Speed profile", 2*MARGIN + 2 * N, 2 * MARGIN + BARHEIGHT);
		
		showSpeedProfile(MARGIN + BARHEIGHT,N);
	}
	
	public void showSpeedProfile(int ybase, int N) {


		int j = 0;
		for (int i = 0; i < N; i++){
			j+=2;
			setColor(0,0,255);
			drawLine(MARGIN+j, ybase, MARGIN+j, ybase - (int) gpscomputer.speeds()[i]);
		}
			setColor(0, 255, 0);
			drawLine(MARGIN, ybase - (int) gpscomputer.averageSpeed(), MARGIN+j, ybase - (int)gpscomputer.averageSpeed());

	}
}
