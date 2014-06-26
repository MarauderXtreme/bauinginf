package Baustelle.graphical;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import Baustelle.physical.PhysicalObject;
import Baustelle.physical.Silo;

/**
 * @author Ali
 *
 */
public class SiloSymbol extends EinrichtungselementSymbol {
	Silo obj;
	private double scaleX;

	/* (non-Javadoc)
	 * @see cib.lehre.baustelle.go.GraphicalObject#aktualisiere(cib.lehre.baustelle.po.PhysicalObject.PhysicalObject)
	 */
	@Override
	public void aktualisiere(PhysicalObject po) {
		this.po=po;
		obj = (Silo) po;
	}

	/* (non-Javadoc)
	 * @see cib.lehre.baustelle.go.GraphicalObject#paint(java.awt.Graphics2D)
	 */
	@Override
	public void paint(Graphics2D g) {
		scaleX=getMassstab();
		g.scale(scaleX, scaleX);
		g.setColor(new Color (0,0,0,255));
		g.draw(new Arc2D.Double (obj.getX()-0.5*obj.getDurchmesser(), obj.getY()-0.5*obj.getDurchmesser(),obj.getDurchmesser(),obj.getDurchmesser(),0,360,Arc2D.CHORD));
		g.setColor(obj.getFarbe());
		g.fill(new Arc2D.Double (obj.getX()-0.5*obj.getDurchmesser(), obj.getY()-0.5*obj.getDurchmesser(),obj.getDurchmesser(),obj.getDurchmesser(),0,360,Arc2D.CHORD));
		g.scale(1/scaleX, 1/scaleX);

	}

}
