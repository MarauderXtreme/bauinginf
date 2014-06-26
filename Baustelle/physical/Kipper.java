package Baustelle.physical;
/**
 * 
 */
 
import Baustelle.graphical.KipperSymbol;


/**
 * Klasse für Kipper, vererbt von der LKW-Klasse
 * @author Ulf Wagner
 * @author Ali Ismail
 *
 */
public class Kipper extends LKW {
	private double maxGewicht;
	private double maxVoluem;
	private double aktuellGewicht;
	private double aktuellVoluem;
	private boolean isLeer;
	
	public Kipper() {
	
	}
	/**
	 * Konstruktor
	 * @param x  x-Koordinate
	 * @param y  y-Koordinate
	 * @param winkel Winkel
	 */
	public Kipper(double x, double y, double winkel) {
		super(x, y, winkel);
	}

	/**
	 *  Konstruktor
	 * @param bezugspunkt : Bezugspunkt objekt
	 * @param winkel
	 */
	public Kipper(Bezugspunkt bezugspunkt, double winkel) {
		super(bezugspunkt, winkel);
	}

	/**
	 * Konstruktor
	 * @param x
	 * @param y
	 * @param winkel
	 * @param breite
	 * @param laenge
	 */
	public Kipper(double x, double y, double winkel, double breite,
			double laenge) {
		super(x, y, winkel, breite, laenge);
	}
	
	/**
	 * Lädt ein Material auf den Kipper und prüft die Maximallast
	 * @param mat
	 * @throws Exception
	 */
	public void laden(Material mat) throws Exception {
		if (mat.getVolumen() > this.getMaxVolumen() || mat.getGewicht() > this.getMaxGewicht() ) {
			 throw new Exception (" Materialvoluem > MaxVoluem oder Materialgewicht > MaxGewicht  ");	
			}
			else {
			super.beladen(mat);	
			this.isLeer=false;
			}
		this.benachrichtige();
	}
	
	/**
	 * Ausladen des Kippers in bestimmter Ratio (zwischen 0 und 1)
	 * @param ratio
	 * @throws Exception
	 */
	public void ausladen(double ratio) throws Exception {
		if (this.getMaterialListe().size() == 0) {
			throw new Exception (" Das Kipper ist leer");					
		}

		if (ratio > 1 || ratio < 0) 
			throw new Exception (" ratio muss >0 und < 1");
		if (ratio == 1) {
			super.entladen(this.getMaterialListe().get(0));
			this.isLeer=true;
		}
		else {
			this.getMaterialListe().get(0).setGewicht(ratio*this.getMaterialListe().get(0).getGewicht());
			this.getMaterialListe().get(0).setVolumen(ratio*this.getMaterialListe().get(0).getVolumen());
		}	
		this.benachrichtige();
	}
	
	
	
	/**
	 *  Setzt den Zustand der Sichtbarkeit des Kippers
	 * 
	 */
	public void setVisible(boolean visible) {
		
		if (visible) {
			if (!isVisible()){				
				go = new KipperSymbol();
				meldeAn(go);
				benachrichtige();			
				super.setVisible(visible);				
			}
		}
		else {
			if (isVisible()){
			   	meldeAb(this.go);
			   	go = null;
				benachrichtige();
				super.setVisible(visible);
			}
		}
		this.zeichnen();
	}
	/**
	 * Gibt die Maximallast des Kippers zurück.
	 * @return the maxGewicht
	 */
	public double getMaxGewicht() {
		return maxGewicht;
	}

	/**
	 * Setzt die neue Maximallast des Kippers
	 * @param maxGewicht neue Maximallast
	 */
	public void setMaxGewicht(double maxGewicht) {
		this.maxGewicht = maxGewicht;
	}

	/**
	 * Gibt das maximale Volumen des Kippers zurück
	 * @return maximales Volumen
	 */
	public double getMaxVolumen() {
		return maxVoluem;
	}

	/**
	 * Setzt des neue maximale Volumen des Kippers
	 * @param maxVoluem neues maximales Volumen
	 */
	public void setMaxVolumen(double maxVoluem) {
		this.maxVoluem = maxVoluem;
	}

	/**
	 * Gibt das aktuelle Gewicht der Ladung zurück.
	 * @return aktuelles Gewicht
	 */
	public double getAktuellGewicht() {
		return aktuellGewicht;
	}

	/**
	 * Setzt des neue Gewicht der Ladung
	 * @param aktuellGewicht neues Gewicht
	 */
	public void setAktuellGewicht(double aktuellGewicht) {
		this.aktuellGewicht = aktuellGewicht;
	}

	/**
	 * Gibt das aktuelle Volumen der Ladung zurück
	 * @return the aktuellVoluem
	 */
	public double getAktuellVolumen() {
		return aktuellVoluem;
	}
	/**
	 * Setzt das aktuelle Volumen der Ladung
	 * @param aktuellVoluem neues Volumen
	 */
	public void setAktuellVolumen(double aktuellVoluem) {
		this.aktuellVoluem = aktuellVoluem;
	}
	/**
	 * Gibt den Ladungszustand des Kippers zurück
	 * @return Ladungszustand
	 */
	public boolean isLeer() {
		return isLeer;
	}

	/**
	 * Setzt den Ladungszustand des Kippers
	 * @param isLeer Ladungszustand
	 */
	public void setLeer(boolean isLeer) {
		this.isLeer = isLeer;
		this.benachrichtige();
	}
	
	protected void finalize()
    {
        this.setVisible(false);
    }

}
