package Test;

import oppgave1.GPSPoint;
import oppgave2.GPSData;

public class TGPSData extends GPSData {

	public TGPSData(int n) {
		super(n);
	}
	
	public int getAntall() {
		return this.antall;
	}
	
	public boolean insertGPST(GPSPoint g) {
		return (insertGPS(g));
	}
}
