/**
 * 
 */
package Baustelle.physical;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import Baustelle.graphical.BaucontainerSymbol;
import Baustelle.physical.Material;

/**
 * Diese Klasse repr�sentiert Baucontainer, welche mit Materialien beladen werden k�nnen
 * @author Ali Ismail
 *
 */
public class Baucontainer extends Einrichtungselement {
	private String code;
	private double hoehe;
	private double breite;
	private double laenge;
	private double gewicht;
	private Color farbe=new Color (0,0,200,150);
	protected ArrayList<Material> materialListe = new ArrayList<Material>();
	
	/**
	 * Konstruktor
	 */
	public Baucontainer(double x, double y , double dx , double dy){	
		this.setX(x);
		this.setY(y);
		this.laenge=dx;
		this.breite=dy;		
	}
	
	/**
	 * Setzt die Sichtbarkeit des Baucontainers
	 */		
	public void setVisible(boolean visible) {
		
		if (visible) {
			if (!isVisible()){			
				go = new BaucontainerSymbol();
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


	/**
	 * Gibt den Code des Baucontainers zur�ck.
	 * @return Code des Baucontainers
	 */
	public String getCode() {
		return code;
	}


	/**
	 * Setzt einen Code des Baucontainers
	 * @param code neuer Code des Baucontainers
	 */
	public void setCode(String code) {
		this.code = code;
	}


	/**
	 * Gibt die H�he des Baucontainers zur�ck.
	 * @return H�he
	 */
	public double getHoehe() {
		return hoehe;
	}


	/**
	 * Setzt die neue H�he des Baucontainers.
	 * @param hoehe neue H�he
	 */
	public void setHoehe(double hoehe) {
		this.hoehe = hoehe;
		this.benachrichtige();
	}


	/**
	 * Gibt die Breite des Baucontainers zur�ck.
	 * @return Breite
	 */
	public double getBreite() {
		return breite;
	}


	/**
	 * Setzt die neue Breite des Baucontainers
	 * @param breite neue Breite
	 */
	public void setBreite(double breite) {
		this.breite = breite;
		this.benachrichtige();
	}


	/**
	 * Gibt die L�nge des Baucontainers zur�ck.
	 * @return the laenge
	 */
	public double getLaenge() {
		return laenge;
	}


	/**
	 * Setzt die neue L�nge des Baucontainers
	 * @param laenge neue L�nge
	 */
	public void setLaenge(double laenge) {
		this.laenge = laenge;
		this.benachrichtige();
	}


	/**
	 * Gibt das Gewicht des Baucontainers zur�ck
	 * @return Gewicht
	 */
	public double getGewicht() {
		return gewicht;
	}


	/**
	 * Setzt das neue Gewicht des Baucontainers.
	 * @param neues Gewicht
	 */
	public void setGewicht(double gewicht) {
		this.gewicht = gewicht;
		this.benachrichtige();
	}


	/**
	 * Gibt die Farbe des Baucontainers zur�ck.
	 * @return Farbe
	 */
	public Color getFarbe() {
		return farbe;
	}


	/**
	 * Setzt die neue Farbe des Baucontainers.
	 * @param farbe neue Farbe
	 */
	public void setFarbe(Color farbe) {
		this.farbe = farbe;
		this.benachrichtige();
	}


	/**
	 * Gibt die derzeitige Materialliste zur�ck.
	 * @return Materialliste
	 */
	public ArrayList<Material> getMaterialListe() {
		return materialListe;
	}


	/**
	 * Setzt die neue Materialliste
	 * @param materialListe neue Materialliste.
	 */
	public void setMaterialListe(ArrayList<Material> materialListe) {
		this.materialListe = materialListe;
	}	
	
	/**
	 * Gibt alle Eigenschaften des Baucontainers als formatierten Text zur�ck.
	 * @return String
	 */	
	public String getEigenschaften(){
		NumberFormat formatter = new DecimalFormat("0.00"); 
		String	s= "<html>Class = " + this.getClass().getSimpleName() +
		"<br>Baucontainer code = " +this.getCode()+	
		   "<br>x = " +formatter.format(this.getX())+
        ",  y = "    + formatter.format(this.getY()) +
        "<br>Laenge = "   + formatter.format(this.getLaenge()) +
        "<br>Breite = "   + formatter.format(this.getBreite()) +
        "<br>Material = "   + this.getMaterialListe() ;
		s =s + "<br>" + this.getNotiz();		
		return s;
	}
	
	protected void finalize()
    {
        this.setVisible(false);
    }
}
