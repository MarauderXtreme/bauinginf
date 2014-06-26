package Baustelle.graphical;
/**
 * 
 */
 

 
import java.awt.*; 
import java.awt.geom.*; 
import java.util.Iterator;
import Baustelle.physical.Bezugspunkt;
import Baustelle.physical.PhysicalObject;
import Baustelle.physical.LKW;
import Baustelle.physical.Material;

//import cib.lehre.baustelle.gui.ZeichenPanel2;
//import cib.lehre.baustelle.po.PhysicalObject.PhysicalObject;
//import cib.lehre.baustelle.po.baugeraet.LKW;
//import cib.lehre.baustelle.po.material.Material;

/**
 *  Die Darstellung Klasse für  LKW Klasse
 * @author Ali Ismail
 *
 */

public class LKWSymbol extends GraphicalObject {
	public double x ,y, winkel;
	Bezugspunkt LkwLocation;
	public double breite;
	public double laenge;
	private double scaleX =getMassstab();
	private boolean lkwfahrt;
	Color LkwColor= new Color(255 ,255 ,0 , 197);
	/**
	 * Konstruktor
	 */
	public LKWSymbol() {
		
	
	}
	/**
	 * 	 die Darstellungsdaten (x,y,winkel) werden aktualisiert und synchronisiert mit dem aktuellen Daten der verbundenen LKW Physicalobject po
	 * <p> die Lage der Materialien, die aufgelanden auf dem LKW sind, werden auch aktualisiert.
	 */
	public void aktualisiere(PhysicalObject po){
		this.po=po;
		LKW LkwObject= (LKW) po;		
		x = LkwObject.getX();
		y = LkwObject.getY();
		breite=LkwObject.getBreite();
		laenge=LkwObject.getLaenge();
		winkel=LkwObject.getWinkel();
		lkwfahrt=LkwObject.isFahrt();		
		Iterator<Material> it = LkwObject.getMaterialListe().listIterator();
	       while (it.hasNext()){
	    	   Material mat = (Material) it.next();
	    	   mat.setX(x);
	    	   mat.setY(y);
	    	   mat.setWinkel(winkel);
	    	   mat.benachrichtige();
	       }
		
	}
	/**
	 * zeichnet das LKWsymbol auf die aktuellelage (x,y)
	 */
	public void paint (Graphics2D graphics2D) {
		scaleX=getMassstab();
		graphics2D.scale(scaleX, scaleX);
		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		graphics2D.setPaint( LkwColor ); 
		graphics2D.setStroke( new BasicStroke( 0.1f , BasicStroke.CAP_BUTT , BasicStroke.JOIN_BEVEL ) ); 
		graphics2D.rotate(winkel*Math.PI/180, x, y) ;
		
		
		Rectangle2D.Double LkwRect = new Rectangle2D.Double (x-0.5*laenge, y-0.5*breite  ,laenge, breite ) ;
		Rectangle2D.Double lkwRectInside = new Rectangle2D.Double (x-0.45*laenge, y-0.42*breite  ,0.9*laenge, 0.9*breite ) ;
		graphics2D.fill(LkwRect);
		graphics2D.setPaint(new Color(255,0,0,255));
		Rectangle2D.Double LkwRect1 = new Rectangle2D.Double (x+0.5*laenge, y-0.35*breite  ,laenge*0.15, breite*0.7 ) ;
		
		Rectangle2D.Double lkwrad1 = new Rectangle2D.Double (x-0.35*laenge, y-0.55*breite  ,laenge*0.2, breite*0.1 ) ;
		Rectangle2D.Double lkwrad2 = new Rectangle2D.Double (x-0.35*laenge, y+0.5*breite  ,laenge*0.2, breite*0.1 ) ;
		
		
		
		Rectangle2D.Double lkwrad3 = new Rectangle2D.Double (x+0.52*laenge, y-0.47*breite  ,laenge*0.1, breite*0.1 ) ;
		Rectangle2D.Double lkwrad4= new Rectangle2D.Double (x+0.52*laenge, y+0.37*breite  ,laenge*0.1, breite*0.1 ) ;
		
		graphics2D.fill(LkwRect1);
		graphics2D.setPaint(new Color(0,0,0,255));
		
		
		graphics2D.draw(LkwRect);
		graphics2D.draw(lkwRectInside);		
		graphics2D.fill(lkwrad1);
		graphics2D.fill(lkwrad2);
		graphics2D.fill(lkwrad3);
		graphics2D.fill(lkwrad4);
		
		graphics2D.draw(LkwRect1);		
		if (lkwfahrt==true) {			
			
		}		
		graphics2D.rotate(-winkel*Math.PI/180, x, y);
		graphics2D.scale(1/scaleX, 1/scaleX);		
	}
}
