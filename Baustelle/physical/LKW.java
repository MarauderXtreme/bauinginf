package Baustelle.physical;
//import cib.lehre.baustelle.go.Bezugspunkt;
//import cib.lehre.baustelle.go.LKWSymbol;

import Baustelle.physical.LKW;
import Baustelle.graphical.LKWSymbol;
/**
 * Klasse für LKW Objekte auf der Baustelle.
 * @author Ulf Wagner
 * @author Ali Ismail
 * @version 1.0
 */
public class LKW extends Fahrzeug {
	
	/**
	 *  Konstruktor für LKW
	 */
	public LKW () {
		super();
	}
	
	/**
	 *  Konstruktor für LKW
	 * @param x 		 x-Position (Meter)
	 * @param y 		 y-Position (Meter)
	 * @param winkel	 Winkel (Grad)
	 */
	public LKW (double x, double y , double winkel) {		
		super(x,y,winkel);
	}
	
	/**
	 *  Konstruktor für LKW
	 * @param bezugspunkt	Bezugspunkt als Lage des LKWs
	 * @param winkel	 	Winkel (Grad)
	 *  Ausgangswert für LKW Breite = 2 Meter , LKW Länge=6 Meter 
	 */
	public LKW (Bezugspunkt bezugspunkt, double winkel) {
		super(bezugspunkt.getX(),bezugspunkt.getY(),winkel);
	}
	
	/**
	 * Konstruktor für LKW
	 * @param x 		 x-Koordinate (Meter)
	 * @param y 		 y-Koordinate (Meter)
	 * @param winkel	 Winkel (degree)
	 * @param breite	 LKW-breite (Meter)
	 * @param laenge	 LKW-länge (Meter)
	 */
	public LKW (double x, double y , double winkel,double breite, double  laenge) {		
		super(x,y,winkel,breite,laenge);
	}
	/**
	 * Setzt den Zustand der Sichtbarkeit des LKWs
	 * 
	 */
	public void setVisible(boolean visible) {		
		if (visible) {
			if (!isVisible()){				
				go = new LKWSymbol();
				meldeAn(go);
				benachrichtige();			
				super.setVisible(visible);				
			}
		}
		else {
			if (isVisible()){
			   	meldeAb(this.go);
				benachrichtige();
				super.setVisible(visible);
			}
		}
	}
}
