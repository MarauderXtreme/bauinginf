/**
 * 
 */
package Baustelle.physical;

import Baustelle.graphical.BaugrubeSymbol;


/**
 * @author Ali
 *
 */
public class Baugrube extends BauObjekt {
	
	private Material grubeMaterial;
	private double x,y,laenge , breite ,winkel, tiefe;
	private double akteulleLaenge ,akteulleBreite ,akteulleTiefe;
	private double akteulleVolume;

	
	public Baugrube (){
		
	}
	
	public Baugrube (double x , double y , double laenge , double breite,double tiefe){		
		this.x=x;
		this.y=y;
		this.breite=breite;
		this.laenge=laenge;		
		this.tiefe=tiefe;
	}

	
	/**
	 * 
	 * 
	 */
	public void setVisible(boolean visible) {		
		if (visible) {
			if (!isVisible()){				
				go = new BaugrubeSymbol();
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
	 * @return the x
	 */
	public double getX() {
		return x;
	}


	/**
	 * @param x the x to set
	 */
	public void setX(double x) {
		this.x = x;
	}


	/**
	 * @return the y
	 */
	public double getY() {
		return y;
	}


	/**
	 * @param y the y to set
	 */
	public void setY(double y) {
		this.y = y;
	}


	/**
	 * @return the laenge
	 */
	public double getLaenge() {
		return laenge;
	}

	/**
	 * @param laenge the laenge to set
	 */
	public void setLaenge(double laenge) {
		this.laenge = laenge;
	}

	/**
	 * @return the breite
	 */
	public double getBreite() {
		return breite;
	}

	/**
	 * @param breite the breite to set
	 */
	public void setBreite(double breite) {
		this.breite = breite;
	}

	/**
	 * @return the tiefe
	 */
	public double getTiefe() {
		return tiefe;
	}

	/**
	 * @param tiefe the tiefe to set
	 */
	public void setTiefe(double tiefe) {
		this.tiefe = tiefe;
	}

	/**
	 * @return the winkel
	 */
	public double getWinkel() {
		return winkel;
	}


	/**
	 * @param winkel the winkel to set
	 */
	public void setWinkel(double winkel) {
		this.winkel = winkel;
	}


	/**
	 * @return the grubeMaterial
	 */
	public Material getGrubeMaterial() {
		return grubeMaterial;
	}


	/**
	 * @param grubeMaterial the grubeMaterial to set
	 */
	public void setGrubeMaterial(Material grubeMaterial) {
		this.grubeMaterial = grubeMaterial;
	}


	/**
	 * @return the volume
	 */
	public double getVolume() {
		return laenge*breite*tiefe;
	}

	/**
	 * @return the akteulleLaenge
	 */
	public double getAkteulleLaenge() {
		return akteulleLaenge;
	}

	/**
	 * @param akteulleLaenge the akteulleLaenge to set
	 */
	public void setAktuelleLaenge(double akteulleLaenge) {
		this.akteulleLaenge = akteulleLaenge;
	}

	/**
	 * @return the akteulleBreite
	 */
	public double getAkteulleBreite() {
		return akteulleBreite;
	}

	/**
	 * @param akteulleBreite the akteulleBreite to set
	 */
	public void setAktuelleBreite(double akteulleBreite) {
		this.akteulleBreite = akteulleBreite;
	}

	/**
	 * @return the akteulleTiefe
	 */
	public double getAktuelleTiefe() {
		return akteulleTiefe;
	}

	/**
	 * @param akteulleTiefe the akteulleTiefe to set
	 */
	public void setAktuelleTiefe(double akteulleTiefe) {
		this.akteulleTiefe = akteulleTiefe;
	}


	/**
	 * @return the akteulleVolume
	 */
	public double getAkteulleVolume() {
		return akteulleVolume;
	}


	/**
	 * @param akteulleVolume the akteulleVolume to set
	 */
	public void setAktuellesVolumen(double akteulleVolume) {
		this.akteulleVolume = akteulleVolume;
	}
	
}
