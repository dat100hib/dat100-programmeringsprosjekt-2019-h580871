
package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowRoute extends EasyGraphics {

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

		playRoute(MARGIN + MAPYSIZE);
		
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
		
		// TODO - START
		
		double maxlan = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		double minlan = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

		ystep = MAPXSIZE / (Math.abs(maxlan - minlan)); 

		return ystep;
		// TODO - SLUTT
		
	}

	public void showRouteMap(int ybase) {

		// TODO - START
		
		//find x,y pos med long lang. tegn cirkle, tegne strekk fra gammel xy til ny xy
		setColor(0,255,0);
		
		int x=0;
		int y=0;
		
		double lats[] = GPSUtils.getLatitudes(gpspoints);
		double lons[] = GPSUtils.getLongitudes(gpspoints);
		
		int oldX = (int) Math.round(((lons[0]-GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints)))*xstep()));
		int oldY = (int) Math.round(((lats[0]-GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints)))*ystep()));
		
		
		for(int i = 0 ;  i < gpspoints.length;i ++) {
			
		x =  (int) Math.round(((lons[i]-GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints)))*xstep()));
		y = (int)Math.round(((lats[i]-GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints)))*ystep()));

		
		fillCircle(x+ MARGIN,  ybase-y, 4);
		drawLine(oldX+MARGIN,ybase-oldY, x+MARGIN, ybase-y);
	
		oldX = x;
		oldY = y;
		
		
		}
		setColor(0,0,255);
		fillCircle(x+ MARGIN,  ybase-y, 7);
		
	
		
		// TODO - SLUTT
	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;

		setColor(0,0,0);
		setFont("Courier",11);
		
		// TODO - START
		
	drawString(String.format("%-15s:%11s", "Total Time      ", GPSUtils.formatTime(gpscomputer.totalTime())), 15, 15);
	drawString(String.format("%-15s:%11.2f km", "Total distance  ", gpscomputer.totalDistance()/1000),15, 25);
	drawString(String.format("%-15s:%11.2f m", "Total elevation ", gpscomputer.totalElevation()), 15, 35);
	drawString(String.format("%-15s:%11.2f km/h", "Max speed       ", gpscomputer.maxSpeed()), 15, 45);
	drawString(String.format("%-15s:%11.2f km/h", "Average speed   ", gpscomputer.averageSpeed()), 15, 55);
	drawString(String.format("%-15s:%11.2f kcal", "Energy          ", gpscomputer.totalKcal(gpscomputer.getWEIGHT())), 15, 65);
		
		// TODO - SLUTT;
	}

	
	
	
	
	public void playRoute(int ybase) {

		// TODO - START
		
	//	throw new UnsupportedOperationException(TODO.method());
		
		// TODO - SLUTT
	}

}