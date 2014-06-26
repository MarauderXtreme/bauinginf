package Baustelle.physical; 
/**
 * Die Klasse Bezugspunkt speichert einen Punkt durch x und y
 * @author Ulf Wagner
 *
 */
public class Bezugspunkt {

	public double x,y;	
	
	public Bezugspunkt(double x,double y){
		this.x=x;
		this.y=y;
	}
	
	public double getX() {	
		return x;
	}
	
	public double getY() {		
		return y;
	}
	
	public void setX(double x) {
		this.x=x;
	}
	
	public void setY(double y) {
		this.y=y;
	}
}
