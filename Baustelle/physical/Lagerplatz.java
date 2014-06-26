/**
 * 
 */
package Baustelle.physical;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import Baustelle.graphical.LagerplatzSymbol;


/**
 * @author Ali Ismail
 *
 */
public class Lagerplatz extends Einrichtungselement {
	private String Code;
	/**
	 * 
	 */
	public Lagerplatz(){
		
	}
	
	protected ArrayList<Material> materialListe = new ArrayList<Material>();

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
	
	public void addMaterial(Material mat){
		mat.setX(this.getX());
		mat.setY(this.getY());
		mat.benachrichtige();
		this.materialListe.add(mat);
	}
	
	public void removeMaterial(Material mat) {
	 if(this.materialListe.contains(mat)== true ) {
		 this.materialListe.remove(mat);
	 }
	}
public void setVisible(boolean visible) {
		
		if (visible) {
			if (!isVisible()){				
				go = new LagerplatzSymbol();
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
	return Code;
}

/**
 * @param code the code to set
 */
public void setCode(String code) {
	Code = code;
}

public String getEigenschaften(){
	NumberFormat formatter = new DecimalFormat("0.00"); 
	String	s= "<html>Class = " + this.getClass().getSimpleName() +
	"<br>Lagerplatz code = " +this.getCode()+	
	"<br>x = " +formatter.format(this.getX())+
    ",  y = "    + formatter.format(this.getY()) +
    "<br>Laenge = "   + formatter.format(this.getDx()) +
    "<br>Breite = "   + formatter.format(this.getDy()) +
    "<br>Material = "   + this.getMaterialListe() ;
	s =s + "<br>" + this.getNotiz();		
	return s;
}	
}
