/**
 * 
 */
package Baustelle.physical;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import Baustelle.graphical.KaninchenSymbol;

/**
 * Ein Kaninchen
 * @author Clemens Gebhardt, Ulf Wagner
 * @version 1.5
 *
 */
public class Kaninchen extends Lebewesen {

	private double hoehe=55;
	private double breite=35;
	private double gewicht;
	
	/**
	 * Konstrukteur
	 */
	public Kaninchen(double x, double y , double breite){	
		this.setX(x);
		this.setY(y);
		this.hoehe=breite * 55/35;
		this.breite=breite;	
	}
	
	public Kaninchen(double x, double y){	
		this.setX(x);
		this.setY(y);	
	}
	
	/**
	 * �ndert  sichtbar oder unsichtbar des Kaninchens
	 */		
	public void setVisible(boolean visible) {
		
		if (visible) {
			if (!isVisible()){			
				go = new KaninchenSymbol();
				benachrichtige();
				meldeAn(go);
				benachrichtige();			
				super.setVisible(visible);
			
			}
		}
		else {
			if (isVisible()){
			    benachrichtige();
			   	meldeAb(this.go);
				benachrichtige();
				super.setVisible(visible);
			}
		}
	}





	/**
	 * gibt die H�he des Kaninchen
	 * @return die H�he
	 */
	public double getHoehe() {
		return hoehe;
	}


	/**
	 * �ndert die H�he des Kaninchens
	 * @param hoehe die neue H�he
	 */
	public void setHoehe(double hoehe) {
		this.hoehe = hoehe;
		this.breite=hoehe * 35/55;	
		benachrichtige();
	}


	/**
	 * gibt die Breite des Kaninchens
	 * @return die Breite
	 */
	public double getBreite() {
		return breite;
	}


	/**
	 * �ndert die Breite des Kaninchens
	 * @param breite die neue Breite
	 */
	public void setBreite(double breite) {
		this.breite = breite;
		this.hoehe = breite *55/35;
		benachrichtige();
	}

	
	
	protected void finalize()
    {
        this.setVisible(false);
    }
}
