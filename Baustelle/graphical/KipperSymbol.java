package Baustelle.graphical;

/**
 * 
 */
 
import Baustelle.physical.Bezugspunkt;
import Baustelle.physical.PhysicalObject;
import Baustelle.physical.Kipper;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
//import cib.lehre.baustelle.po.PhysicalObject.PhysicalObject;
//import cib.lehre.baustelle.po.baugeraet.Kipper;

/**
 *  Die Darstellung Klasse für  Kipper Klasse
 * @author Ali Ismail
 *
 */

public class KipperSymbol extends GraphicalObject {
	private double x ,y, winkel;
	Bezugspunkt LkwLocation;
	private double breite;
	private double laenge;
	private double scaleX ;
	private boolean lkwfahrt;
	private double matRatio;
	Color kipperColor= new Color(150 ,255 ,150 , 197);
	
	public KipperSymbol (){
		}

	/**
	 * 	 die Darstellungsdaten (x,y,winkel) werden aktualisiert und synchronisiert mit dem aktuellen Daten der verbundenen Kipper-Physicalobject po
	 * <p> die Lage der Materialien, die aufgelanden auf dem Kipper sind, werden auch aktualisiert.
	 */
	public void aktualisiere(PhysicalObject po){
		this.po=po;
		Kipper kipperObj= (Kipper) po;		
		x = kipperObj.getX();
		y = kipperObj.getY();
		breite=kipperObj.getBreite();
		laenge=kipperObj.getLaenge();
		winkel=kipperObj.getWinkel();
		lkwfahrt=kipperObj.isFahrt();		
		if (kipperObj.getMaterialListe().size() == 1) {
			matRatio=kipperObj.getMaterialListe().get(0).getVolumen()/kipperObj.getMaxVolumen();	
			if(matRatio > 1)
				matRatio=1;
		}		
	}
	/**
	 * zeichnet das Kippersymbol
	 * <p> die Füllmenge wird auch mit dargestellt
	 */
	public void paint (Graphics2D graphics2D) {	
		scaleX=getMassstab();
		graphics2D.scale(scaleX, scaleX);
		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		graphics2D.setPaint( kipperColor ); 
		graphics2D.setStroke( new BasicStroke( 0.1f , BasicStroke.CAP_BUTT , BasicStroke.JOIN_BEVEL ) ); 
		graphics2D.rotate(winkel*Math.PI/180, x, y) ;	
		
		Rectangle2D.Double LkwRect = new Rectangle2D.Double (x-0.5*laenge, y-0.4*breite  ,0.85*laenge, 0.8*breite ) ;		
		Rectangle2D.Double lkwRectInside = new Rectangle2D.Double (x-0.45*laenge, y-0.35*breite  ,0.9*laenge, 0.7*breite ) ;
		graphics2D.fill(LkwRect);		
		
		if (matRatio > 0 && matRatio <= 1 ) {
			graphics2D.setPaint(new Color(0,0,0,180));
			Rectangle2D.Double LkwMat = new Rectangle2D.Double (x-0.5*laenge, y-0.4*breite  ,0.85*laenge*matRatio, 0.8*breite ) ;
			graphics2D.fill(LkwMat);
		}		
		
		graphics2D.setPaint(new Color(255,0,0,255));
		Rectangle2D.Double LkwRect1 = new Rectangle2D.Double (x+0.5*laenge, y-0.35*breite  ,laenge*0.25, breite*0.7 ) ;		
		Rectangle2D.Double lkwrad1 = new Rectangle2D.Double (x-0.35*laenge, y-0.5*breite  ,laenge*0.2, breite*0.1 ) ;
		Rectangle2D.Double lkwrad2 = new Rectangle2D.Double (x-0.35*laenge, y+0.4*breite  ,laenge*0.2, breite*0.1 ) ;	
		Rectangle2D.Double lkwrad11 = new Rectangle2D.Double (x+0.2*laenge, y-0.5*breite  ,laenge*0.2, breite*0.1 ) ;
		Rectangle2D.Double lkwrad22 = new Rectangle2D.Double (x+0.2*laenge, y+0.4*breite  ,laenge*0.2, breite*0.1 ) ;	
		Rectangle2D.Double lkwrad3 = new Rectangle2D.Double (x+0.52*laenge, y-0.47*breite  ,laenge*0.1, breite*0.1 ) ;
		Rectangle2D.Double lkwrad4= new Rectangle2D.Double (x+0.52*laenge, y+0.37*breite  ,laenge*0.1, breite*0.1 ) ;
		
		graphics2D.fill(LkwRect1);
		graphics2D.setPaint(new Color(0,0,0,255));	
		
		graphics2D.draw(LkwRect);
		graphics2D.draw(lkwRectInside);	
		
		graphics2D.fill(lkwrad1);
		graphics2D.fill(lkwrad2);
		graphics2D.fill(lkwrad11);
		graphics2D.fill(lkwrad22);
		graphics2D.fill(lkwrad3);
		graphics2D.fill(lkwrad4);
		
		graphics2D.draw(LkwRect1);		
		if (lkwfahrt==true) {			
			
		}		
		graphics2D.rotate(-winkel*Math.PI/180, x, y);
		graphics2D.scale(1/scaleX, 1/scaleX);		
	}	
}
