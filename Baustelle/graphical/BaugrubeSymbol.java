/**
 * 
 */
package Baustelle.graphical;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;


import Baustelle.physical.Baugrube;
import Baustelle.physical.PhysicalObject;

/**
 * @author Ali
 *
 */
@SuppressWarnings("serial")
public class BaugrubeSymbol extends GraphicalObject {
	private double x,y,laenge, breite,tiefe,winkel,scaleX ;
	private double akteulleLaenge ,akteulleBreite ,akteulleTiefe;
	private Color grubeColor= new Color(204  ,153,  51,30);	
	
	public BaugrubeSymbol(){
	}
	/* (non-Javadoc)
	 * @see cib.lehre.baustelle.go.GraphicalObject#aktualisiere(cib.lehre.baustelle.po.PhysicalObject.PhysicalObject)
	 */
	@Override
	public void aktualisiere(PhysicalObject po) {
		Baugrube baugrubeObj= (Baugrube) po;		
		x = baugrubeObj.getX();
		y = baugrubeObj.getY();
		breite=baugrubeObj.getBreite();
		laenge=baugrubeObj.getLaenge();
		tiefe=baugrubeObj.getTiefe();
		winkel=baugrubeObj.getWinkel();
		akteulleLaenge=baugrubeObj.getAkteulleLaenge();
		akteulleBreite=baugrubeObj.getAkteulleBreite();
		akteulleTiefe=baugrubeObj.getAktuelleTiefe();
	}

	/* (non-Javadoc)
	 * @see cib.lehre.baustelle.go.GraphicalObject#paint(java.awt.Graphics2D)
	 */
	@Override
	public void paint(Graphics2D g) {
		scaleX=getMassstab();
		g.scale(scaleX, scaleX);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g.setPaint( grubeColor ); 
		g.setStroke( new BasicStroke( 0.1f , BasicStroke.CAP_BUTT , BasicStroke.JOIN_BEVEL ) ); 
		g.rotate(winkel*Math.PI/180, x, y) ;		
		g.fill(new Rectangle2D.Double (x, y  ,akteulleLaenge, akteulleBreite));
		
		Stroke thindashed = new BasicStroke(0.4f, BasicStroke.CAP_BUTT,
	      	      /* join style, miter limit */BasicStroke.JOIN_BEVEL, 1.0f,
	      	      /* the dash pattern */new float[] { 3.0f,1.0f },
	      	      /* the dash phase */0.0f); /* on 8, off 3, on 2, off 3 */
		g.setStroke(thindashed);
		g.setPaint(new Color(0,0,0,200));
		g.draw(new Rectangle2D.Double (x, y  ,laenge, breite));
		
		g.setPaint(new Color(  240 , 153,  51,(int) ( (akteulleTiefe/tiefe) *60)));
		g.fill(new Rectangle2D.Double (x+0.05*akteulleLaenge, y+0.05*akteulleBreite  ,0.9*akteulleLaenge, 0.9*akteulleBreite));
		
		g.rotate(-winkel*Math.PI/180, x, y);
		g.scale(1/scaleX, 1/scaleX);		
	}

}
