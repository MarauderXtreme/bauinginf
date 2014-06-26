package Baustelle.graphical;
/**
 * 
 */
 

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import Baustelle.physical.Material;
import Baustelle.physical.PhysicalObject;

//import cib.lehre.baustelle.po.PhysicalObject.PhysicalObject;
//import cib.lehre.baustelle.po.material.Material;



/**
 *  Die Darstellung Klasse für  Material Klasse
 * @author Ali Ismail
 *
 */

public class MaterialSymbol extends GraphicalObject {
	Material obj;
	private double x,y,laenge , bereite , winkel;
	private double scaleX=1;
	private Color matColor =new Color (255,0,0,200);	
	public MaterialSymbol() {
					
	
	}
	/**
	 * 	 die Darstellungsdaten (x,y,winkel,laenge,breite,matcolor) werden aktualisiert und synchronisiert mit dem aktuellen Daten der verbundenen Material Physicalobject po
	 */
	public void aktualisiere(PhysicalObject po) {
		Material mat= (Material) po;
		this.po=po;
		obj = (Material) po;
		x=mat.getX();
		y=mat.getY();
		laenge=mat.getLaenge();
		bereite=mat.getBreite();
		winkel=mat.getWinkel();
		matColor=mat.getMatcolor();	

	}
	/**
	 * zeichnet das Materialsymbol als vierecke auf die aktuelle Materiallage (x,y)
	 */
	public void paint(Graphics2D graphics2D) {
		scaleX=getMassstab();
		graphics2D.scale(scaleX, scaleX);	
		graphics2D.rotate(winkel*Math.PI/180, x, y ) ;		
		graphics2D.setStroke(  new BasicStroke( 0.5f , BasicStroke.CAP_BUTT , BasicStroke.JOIN_MITER ));		
		Rectangle2D.Double matRect = new Rectangle2D.Double (x-0.5*laenge, y-0.5*bereite, laenge,bereite ) ;
		graphics2D.setPaint( matColor );
		graphics2D.fill(matRect);
		graphics2D.rotate(-winkel*Math.PI/180, x, y ) ;
		graphics2D.scale(1/scaleX, 1/scaleX);
	}
}
