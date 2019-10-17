package oppgave5;

import easygraphics.EasyGraphics;
import oppgave1.GPSPoint;
import oppgave4.GPSComputer;

import javax.swing.*;

public class ShowProfile extends EasyGraphics {

	private static int MARGIN = 50;		// margin on the sides 
	
	//FIXME: use highest point and scale accordingly
	private static int MAXBARHEIGHT = 500; // assume no height above 500 meters
	
	private GPSPoint[] gpspoints;

	public ShowProfile() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		GPSComputer gpscomputer =  new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();
		
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		int N = gpspoints.length; // number of data points

		makeWindow("Height profile", 2 * MARGIN + 3 * N, 2 * MARGIN + MAXBARHEIGHT);

		// top margin + height of drawing area
		showHeightProfile(MARGIN + MAXBARHEIGHT); 
	}

	public void showHeightProfile(int ybase) {

		// ybase indicates the position on the y-axis where the columns should start


		int j = 0;
		for (int i = 0; i < gpspoints.length; i++){
			j+=2;
			setColor(0, 0, 255);
			drawLine(MARGIN+j,  ybase , MARGIN+j,ybase - (int) gpspoints[i].getElevation());
		}


	}

}
