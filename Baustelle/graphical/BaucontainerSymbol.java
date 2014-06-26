/**
 * 
 */
package Baustelle.graphical;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import Baustelle.physical.PhysicalObject;
import Baustelle.physical.Baucontainer;

/**
 *  Die Darstellung Klasse für  Baucontainer Klasse
 * @author Ali Ismail
 *
 */
public class BaucontainerSymbol extends GraphicalObject {
	Baucontainer obj;
	private double x;
	private double y;
	private double laenge;
	private double breite;
	private Color farbe;
	private double scaleX;


	public void aktualisiere(PhysicalObject po) {
	this.po=po;
	obj = (Baucontainer) po;
	x=obj.getX();
	y=obj.getY();
	breite=obj.getBreite();
	laenge=obj.getLaenge();
	farbe=obj.getFarbe();
	}

	/**
	 * zeichnet das Baucontainersymbol
	 */
	public void paint(Graphics2D g) {
		scaleX=getMassstab();
		g.scale(scaleX, scaleX);
		g.setColor(new Color (0,0,0,255));
		g.draw(new Rectangle2D.Double (x-0.5*laenge, y-0.5*breite, laenge, breite));
		g.setColor(farbe);
		g.fill(new Rectangle2D.Double (x-0.5*laenge, y-0.5*breite, laenge, breite));
		g.scale(1/scaleX, 1/scaleX);
	}

}
