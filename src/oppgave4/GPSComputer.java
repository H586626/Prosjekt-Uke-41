package oppgave4;

import oppgave1.GPSPoint;
import oppgave2.GPSData;
import oppgave2.GPSDataFileReader;
import oppgave3.GPSUtils;

import java.text.DecimalFormat;

import static oppgave3.GPSUtils.*;

public class GPSComputer {

	private static DecimalFormat df2 = new DecimalFormat("#.##");

	private GPSPoint[] gpspoints;

	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}

	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}

	// beregn total distances (i meter)
	public double totalDistance() {

		double distance = 0;

		// TODO - START
		for (int i = 0; i < gpspoints.length-1; i++){
			distance += distance(gpspoints[i], gpspoints[i+1]);
		}

		return distance;

		// TODO - SLUTT

	}

	// beregn totale høydemeter (i meter)
	public double totalElevation() {

		double elevation = 0;

		for (int i = 0; i < gpspoints.length-1; i++) {
			if (gpspoints[i].getElevation() < gpspoints[i+1].getElevation()) {
				elevation += gpspoints[i+1].getElevation() - gpspoints[i].getElevation();
			}
		}

		return elevation;
	}

	// beregn total tiden for hele turen (i sekunder)
	public int totalTime() {

		int time = 0;

		for (int i = 0; i < gpspoints.length-1; i++){
			time += gpspoints[i+1].getTime()-gpspoints[i].getTime();
		}
		return time;
<
	}

	// beregn gjennomsnitshastighets mellom hver av gps punktene

	public double[] speeds() {

		// TODO - START		// OPPGAVE - START
		double[] tab = new double[gpspoints.length-1];

		for (int i = 0; i < gpspoints.length-1; i++){
			tab[i] = GPSUtils.speed(gpspoints[i], gpspoints[i+1]);
		}
		return tab;


		// TODO - SLUTT

	}

	public double maxSpeed() {

		double maxspeed = 0;

		maxspeed = findMax(speeds());

		return maxspeed;

	}

	public double averageSpeed() {

		double average = 0;

		average = (totalDistance()/totalTime()*3.6);


		return average;
	}
	public static double MS = 2.236936;
	public double kcal(double weight, int secs, double speed) {

		double kcal;

		double met = 0;
		double speedmph = speed * MS;

		if (speedmph < 10){
			met = 4;
		}
		else if (speedmph > 9 && speedmph < 13){
			met = 6;
		}
		else if (speedmph > 12 && speedmph < 15){
			met = 8;
		}
		else if (speedmph > 14 && speedmph < 17){
			met = 10;
		}
		else if (speedmph > 15 && speedmph < 21){
			met = 12;
		}
		else {
			met = 16;
		}
		kcal = (met * weight * secs)/3600;
        return kcal;

	}

	public double totalKcal(double weight) {

		double totalkcal = 0;

		for (int i = 0; i < gpspoints.length-1; i++){
			int time = gpspoints[i+1].getTime()-gpspoints[i].getTime();
			totalkcal += kcal(weight, time, speeds()[i]);
		}

		return totalkcal;

	}
		private static double WEIGHT = 80.0;

		public String displayStatistics() {
			String str = "";
			str += "==============================================" + "\n";

			str += "Total Time     :   " + GPSUtils.formatTime(totalTime()) + "\n";
			str += "Total distance :      " + df2.format((totalDistance()/1000)) + " km" + "\n";
			str += "Total elivation:     " + df2.format(totalElevation()) + " m" + "\n";
			str += "Max speed      :      " + df2.format(maxSpeed()) + " km/t" + "\n";
			str += "Average speed  :      " + df2.format(averageSpeed()) + " km/t" + "\n";
			str += "Energy         :     " + df2.format(totalKcal(WEIGHT)) + " kcal" + "\n";

			str += "==============================================" + "\n";

			System.out.println(str);

			return str;



		}
}
