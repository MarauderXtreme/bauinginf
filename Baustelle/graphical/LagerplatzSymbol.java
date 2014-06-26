/**
 * 
 */
package Baustelle.graphical;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import Baustelle.physical.PhysicalObject;
import Baustelle.physical.Lagerplatz;

/**
 * @author Ali
 *
 */
public class LagerplatzSymbol extends EinrichtungselementSymbol {
	Lagerplatz obj;
	private double scaleX;

	/* (non-Javadoc)
	 * @see cib.lehre.baustelle.go.GraphicalObject#aktualisiere(cib.lehre.baustelle.po.PhysicalObject.PhysicalObject)
	 */
	@Override
	public void aktualisiere(PhysicalObject po) {
		this.po=po;
		obj = (Lagerplatz) po;
	}

	/* (non-Javadoc)
	 * @see cib.lehre.baustelle.go.GraphicalObject#paint(java.awt.Graphics2D)
	 */
	@Override
	public void paint(Graphics2D g) {
		scaleX=getMassstab();
		g.scale(scaleX, scaleX);
		g.setColor(new Color(100,100,250,240));
		g.fill(new Rectangle.Double (obj.getX()-0.5*obj.getDx(), obj.getY()-0.5*obj.getDy(),obj.getDx(),obj.getDy()));
		g.setColor(new Color(100,100,150,200));
		/*
		for ( int i=0; i <= obj.getMaterialListe().size();i++){
			double x,y,dx,dy;
			x=obj.getMaterialListe().get(i).getBreite();
			y=obj.getMaterialListe().get(i).getLaenge();
			g.fill(new Rectangle.Double (obj.getX()-0.5*obj.getDx(), obj.getY()+0.5*obj.getDy(),x,y));
		}
			*/
		g.scale(1/scaleX, 1/scaleX);

	}

}
