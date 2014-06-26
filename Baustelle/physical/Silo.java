package Baustelle.physical;


import java.awt.Color;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;


import Baustelle.graphical.SiloSymbol;

/**
 * @author Ali Ismail
 *
 */
public class Silo extends Einrichtungselement {
	private String code;
	private String siloType;
	private double hoehe;
	private double durchmesser;
	private double gewicht;
	private double volumen;
	private double leergewicht;
	private Color farbe=new Color (200,0,200,150);
	protected ArrayList<Material> materialListe = new ArrayList<Material>();
	
	public Silo() {
	    
	}    
	
	public Silo(double x, double y, double durchmesser){
		this.setX(x);
		this.setY(y);
		this.durchmesser=durchmesser;		
	}
	
	
public void setVisible(boolean visible) {
		
		if (visible) {
			if (!isVisible()){			
				go = new SiloSymbol();
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
 * @return the code
 */
public String getCode() {
	return code;
}


/**
 * @param code the code to set
 */
public void setCode(String code) {
	this.code = code;
}


/**
 * @return the hoehe
 */
public double getHoehe() {
	return hoehe;
}


/**
 * @param hoehe the hoehe to set
 */
public void setHoehe(double hoehe) {
	this.hoehe = hoehe;
}


/**
 * @return the durchmesser
 */
public double getDurchmesser() {
	return durchmesser;
}


/**
 * @param durchmesser the durchmesser to set
 */
public void setDurchmesser(double durchmesser) {
	this.durchmesser = durchmesser;
}


/**
 * @return the gewicht
 */
public double getGewicht() {
	return gewicht;
}


/**
 * @param gewicht the gewicht to set
 */
public void setGewicht(double gewicht) {
	this.gewicht = gewicht;
}


/**
 * @return the farbe
 */
public Color getFarbe() {
	return farbe;
}


/**
 * @param farbe the farbe to set
 */
public void setFarbe(Color farbe) {
	this.farbe = farbe;
}


/**
 * @return the materialListe
 */
public ArrayList<Material> getMaterialListe() {
	return materialListe;
}


/**
 * @param materialListe the materialListe to set
 */
public void setMaterialListe(ArrayList<Material> materialListe) {
	this.materialListe = materialListe;
}


/**
 * @return the volumen
 */
public double getVolumen() {
	return volumen;
}


/**
 * @param volumen the volumen to set
 */
public void setVolumen(double volumen) {
	this.volumen = volumen;
}


/**
 * @return the leergewicht
 */
public double getLeergewicht() {
	return leergewicht;
}


/**
 * @param leergewicht the leergewicht to set
 */
public void setLeergewicht(double leergewicht) {
	this.leergewicht = leergewicht;
}


/**
 * @return the siloType
 */
public String getSiloType() {
	return siloType;
}


/**
 * @param siloType the siloType to set
 */
public void setSiloType(String siloType) {
	this.siloType = siloType;
}

public String getEigenschaften(){
	NumberFormat formatter = new DecimalFormat("0.00"); 
	String s="<html>Class = " + this.getClass().getSimpleName() +
	"<br>Silo code = " +this.getCode()+	
	"<br>x = " +formatter.format(this.getX())+
	",  y = "    + formatter.format(this.getY()) +
	"<br>Durchmesser = "   + formatter.format(this.getDurchmesser()) +
	"<br>Höhe = "   + formatter.format(this.getHoehe()) +
	"<br>Material = "   + this.getMaterialListe();
	s =s + "<br>" + this.getNotiz();
	return s;
}

}
