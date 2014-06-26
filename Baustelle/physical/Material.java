package Baustelle.physical;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import Baustelle.graphical.MaterialSymbol;


/**
 * Klasse Material als Darstellung verschiedener Baustoffe
 *  @author Ulf Wagner
 * @author Ali Ismail
 */
public class Material extends PhysicalObject{

	private double gewicht;
	private double volumen;
	private String matName;
	@SuppressWarnings("unused")
	private boolean fluessigkeit=false;	
	private double x,y, laenge , breite , winkel;
	private Color matFarbe;	
	/**
	 * Konstruktor
	 */
	public Material() {
	}
	/**
	 * Konstruktor
	 * @param matName
	 */
	public Material(String matName) {
	this.matName=matName;
	}
	/**
	 * Konstruktor
	 * @param matName
	 * @param gewicht
	 * @param volumen
	 */
	public Material (String matName,double gewicht, double volumen) {
		this.matName=matName;
		this.gewicht=gewicht;
		this.volumen=volumen;
	}
	
	/**
	 * @return Name des Materials
	 */
	public String getMatName() {
		return matName;
	}
	/**
	 * @param matName neuer Name des Materials
	 */
	public void setMatName(String matName) {
		this.matName = matName;
	}
	/**
	 * @return Gewicht des Materials
	 */
	public double getGewicht() {
		return gewicht;
	}
	/**
	 * @param gewicht neues Gewicht des Materials
	 */
	public void setGewicht(double gewicht) {
		this.gewicht = gewicht;
	}
	/**
	 * @return Volumen des Materials
	 */
	public double getVolumen() {
		return volumen;
	}
	/**
	 * @param volumen neues Volumen des Materials
	 */
	public void setVolumen(double volumen) {
		this.volumen = volumen;
	}

	/**
	 * @return Länge des Materials
	 */
	public double getLaenge() {
		return laenge;
	}

	/**
	 * @param laenge neue Länge des Materials
	 */
	public void setLaenge(double laenge) {
		this.laenge = laenge;
		this.benachrichtige();
	}

	/**
	 * @return Breite des Materials
	 */
	public double getBreite() {
		return breite;
	}

	/**
	 * @param breite neue Breite des Materials
	 */
	public void setBreite(double breite) {
		this.breite = breite;
		this.benachrichtige();
	}

	/**
	 * @return Winkel des Materials
	 */
	public double getWinkel() {
		return winkel;
	}

	/**
	 * @param winkel neuer Winkel des Materials
	 */
	public void setWinkel(double winkel) {
		this.winkel = winkel;
		this.benachrichtige();
	}

	/**
	 * @return Farbe des Materials
	 */
	public Color getMatcolor() {
		return matFarbe;
	}

	/**
	 * @param matcolor neue Farbe des Materials
	 */
	public void setMatcolor(Color matfarbe) {
		matFarbe = matfarbe;
		this.benachrichtige();
	}
	
	
	/**
	 * gibt die x-Koordinate zurück
	 * @return x
	 */
	public double getX() {
		return x;
	}

	/**
	 * Setzt eine neue x-Koordinate
	 * @param x neue x-Koordinate
	 */
	public void setX(double x) {
		this.x = x;
		this.benachrichtige();
	}

	/**
	 * gibt die y-Koordinate zurück
	 * @return y
	 */
	public double getY() {
		return y;
	}

	/**
	 * Setzt eine neue y-Koordinate
	 * @param y neue y-Koordinate
	 */
	public void setY(double y) {
		this.y = y;
		this.benachrichtige();
	}

	/**
	 * setzt die Sichtbarkeit des Materials
	 */	
	public void setVisible(boolean visible) {
			
			if (visible) {
				if (!isVisible()){				
					go = new MaterialSymbol();
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
	 * gibt die Eigenschaften des Materials als formatierten Text zurück
	 * @return String
	 */	
	public String getEigenschaften(){
		NumberFormat formatter = new DecimalFormat("0.00"); 
		String	s= "<html>Class = " + this.getClass().getSimpleName() +
		"<br>Material name = " +this.getMatName()+	
		   "<br>x = " +formatter.format(this.getX())+
	    ",  y = "    + formatter.format(this.getY()) +
	    "<br>Laenge = "   + formatter.format(this.getDx()) +
	    "<br>Breite = "   + formatter.format(this.getDy()) +
	    "<br>Gewicht = "   + formatter.format(this.getGewicht()) +
	    "<br>Volumen = "   + formatter.format(this.getVolumen()) ; 
		s =s + "<br>" + this.getNotiz();		
		return s;
	}
	
	protected void finalize()
    {
        this.setVisible(false);
    }
}
