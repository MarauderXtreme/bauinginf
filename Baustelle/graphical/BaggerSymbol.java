/**
 * 
 */
package Baustelle.graphical;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;

import Baustelle.physical.Bagger;
import Baustelle.physical.Material;
import Baustelle.physical.PhysicalObject;

/**
 * @author Ali Ismail
 *
 */
@SuppressWarnings("serial")
public class BaggerSymbol extends BaugeraeteSymbol {
	private double scaleX;
	private double x,y,breite,laenge,winkel;
	private double auslaegerWinkel;
	private boolean ketten;
	private boolean radSagen;
	private boolean gruben;
	private boolean kipperfuleen;
	private double schaufelLaenge;
	private double schaufelBreite;
	private double auslagerLaenge;
	Color baggerColor= new Color(255 ,255 ,0 , 197);	
	
	public BaggerSymbol(){
		
	}
	
	public void aktualisiere(PhysicalObject po){
		this.po=po;		
		Bagger baggerObject= (Bagger) po;		
		x = baggerObject.getX();
		y = baggerObject.getY();
		breite=baggerObject.getBreite();
		laenge=baggerObject.getLaenge();
		winkel=baggerObject.getWinkel();
		ketten=baggerObject.isKetten();
		radSagen=baggerObject.isRadSagen();	
		if (gruben== true && kipperfuleen == false) {
			if (baggerObject.getSchaufelLageRatio() > 1)
				baggerObject.setSchaufelLageRatio(0.2);
			else
				baggerObject.setSchaufelLageRatio(baggerObject.getSchaufelLageRatio()+ 0.1*po.getZeitFaktor());	
		}
		
		if (kipperfuleen == true)
			baggerObject.setSchaufelLageRatio(0.75);
			
		
		auslagerLaenge=baggerObject.getSchaufelLageRatio()*baggerObject.getAuslagerLaenge();
		schaufelBreite=baggerObject.getSchaufelBreite();
		schaufelLaenge=baggerObject.getSchaufelLaenge();
		auslaegerWinkel=baggerObject.getAuslaegerWinkel();
		gruben=baggerObject.isGruben();
		kipperfuleen=baggerObject.isKipperfullen();
		
		
		
		Iterator<Material> it = baggerObject.getMaterialListe().listIterator();
	       while (it.hasNext()){
	    	   Material mat = (Material) it.next();
	    	   mat.setX(x+Math.cos(Math.toRadians(winkel+auslaegerWinkel))*(auslagerLaenge+schaufelBreite));
	    	   mat.setY(y+Math.sin(Math.toRadians(winkel+auslaegerWinkel))*(auslagerLaenge+schaufelBreite));
	    	   mat.setWinkel(winkel+auslaegerWinkel);
	    	   mat.benachrichtige();
	       }
	}
	
	public void paint (Graphics2D graphics2D) {	
		 scaleX=getMassstab();
		graphics2D.scale(scaleX, scaleX);
		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		
		graphics2D.setStroke( new BasicStroke( 0.25f , BasicStroke.CAP_BUTT , BasicStroke.JOIN_BEVEL ) ); 
		graphics2D.rotate(winkel*Math.PI/180, x, y) ;		
		

		 if (ketten== true && radSagen ==false ) {
		graphics2D.setPaint( new Color (0,0,0,255) ); 
		for (int i=0; i < 20 ;i++) {
			graphics2D.draw( new Line2D.Double (x-0.5*laenge + i * (laenge/20),y-0.5*breite,x-0.5*laenge + i * (laenge/20),y-0.3*breite));
			graphics2D.draw( new Line2D.Double (x-0.5*laenge + i * (laenge/20),y+0.3*breite,x-0.5*laenge + i * (laenge/20),y+0.5*breite));
		}	
		graphics2D.draw(new Rectangle2D.Double (x-0.5*laenge, y-0.5*breite  ,laenge, 0.2*breite  ));
		graphics2D.draw(new Rectangle2D.Double (x-0.5*laenge, y+0.3*breite  ,laenge, 0.2*breite  ));
		 }
		 
		 if (radSagen==true){
			 graphics2D.setPaint( new Color (255,0,0,200) ); 
			 graphics2D.fill( new Rectangle2D.Double (x-0.5*laenge,y-0.5*breite,laenge ,breite));
			 graphics2D.setPaint( new Color (0,0,0,200) );
			 graphics2D.fill( new Rectangle2D.Double (x-0.4*laenge,y-0.75*breite,0.3*laenge ,0.3*breite));
			 graphics2D.fill( new Rectangle2D.Double (x-0.4*laenge,y+0.45*breite,0.3*laenge ,0.3*breite));
			 graphics2D.fill( new Rectangle2D.Double (x+0.4*laenge,y-0.75*breite,0.3*laenge ,0.3*breite));
			 graphics2D.fill( new Rectangle2D.Double (x+0.4*laenge,y+0.45*breite,0.3*laenge ,0.3*breite));
			 
		 }
		 
		 
		graphics2D.rotate(auslaegerWinkel*Math.PI/180, x, y) ;	
		graphics2D.setPaint( new Color (0,0,0,255) ); 
		graphics2D.draw( new Rectangle2D.Double (x-0.3*laenge, y-0.3*breite  ,0.5*laenge, 0.6*breite ));
		graphics2D.setPaint( baggerColor ); 
		graphics2D.fill( new Rectangle2D.Double (x-0.3*laenge, y-0.3*breite  ,0.5*laenge, 0.6*breite ));
		graphics2D.setPaint( new Color (160,160,0,255) ); 
		graphics2D.fill(new Rectangle2D.Double (x-0.125*laenge, y-0.125*breite  ,0.25*laenge, 0.25*breite ));
		
		
		
		if (schaufelLaenge > 0 && schaufelBreite > 0 && auslagerLaenge > 0 ) {
		graphics2D.setPaint( baggerColor ); 
		graphics2D.fill( new Rectangle2D.Double (x+0.25*laenge, y-0.1*breite  ,0.5*auslagerLaenge, 0.2*breite ));
		graphics2D.setPaint( new Color (0,0,0,200) ); 
		graphics2D.draw( new Rectangle2D.Double (x+0.25*laenge, y-0.1*breite  ,0.5*auslagerLaenge, 0.2*breite ));		
		graphics2D.fill( new Rectangle2D.Double (x+0.25*laenge, y-0.1*breite  ,0.5*auslagerLaenge, 0.2*breite ));
		graphics2D.setPaint( new Color (0,0,0,200) ); 
		graphics2D.draw( new Rectangle2D.Double (x+0.25*laenge, y-0.1*breite  ,0.5*auslagerLaenge, 0.2*breite ));
		
		
		graphics2D.setPaint( baggerColor ); 
		graphics2D.fill( new Rectangle2D.Double (x+0.25*laenge+0.55*auslagerLaenge, y-0.1*breite  ,0.5*auslagerLaenge, 0.2*breite ));
		graphics2D.setPaint( new Color (0,0,0,200) ); 
		graphics2D.draw( new Rectangle2D.Double (x+0.25*laenge+0.55*auslagerLaenge, y-0.1*breite  ,0.5*auslagerLaenge, 0.2*breite ));		
		graphics2D.fill( new Rectangle2D.Double (x+0.25*laenge+0.55*auslagerLaenge, y-0.1*breite  ,0.5*auslagerLaenge, 0.2*breite ));
		graphics2D.setPaint( new Color (0,0,0,200) ); 
		graphics2D.draw( new Rectangle2D.Double (x+0.25*laenge+0.55*auslagerLaenge, y-0.1*breite  ,0.5*auslagerLaenge, 0.2*breite ));
		
		
		graphics2D.setPaint( new Color (192,192,192,150) ); 
		graphics2D.fill( new Rectangle2D.Double (x+0.3*laenge+auslagerLaenge, y-0.5*schaufelLaenge  , schaufelBreite ,schaufelLaenge));
		graphics2D.setPaint( new Color (0,0,0,255) ); 
		graphics2D.draw( new Rectangle2D.Double (x+0.3*laenge+auslagerLaenge, y-0.5*schaufelLaenge  , schaufelBreite ,schaufelLaenge));
		}
		graphics2D.rotate(-auslaegerWinkel*Math.PI/180, x, y) ;	
		
		graphics2D.rotate(-winkel*Math.PI/180, x, y);
		graphics2D.scale(1/scaleX, 1/scaleX);		
	}

	
}
