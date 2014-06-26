/**
 * 
 */
package Baustelle.graphical;

import Baustelle.physical.PhysicalObject;
import Baustelle.physical.Baustrasse;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Arc2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
/**
 *  Die Darstellung Klasse für  Baustrasse Klasse
 * @author Ali Ismail
 *
 */

public class BaustrasseSymbol extends GraphicalObject {
    private double x1,y1,x2,y2;
    private double breite=30;
    private int Transparent=120;
    private double scaleX =getMassstab();
    private Color BaustrasseColor =new Color( 0 , 0 , 0 , Transparent );
    private Color BaustrassGestrichelteColor =new Color( 255 , 255 , 255 , Transparent );
    private double alpha1,alpha2,r,xc,yc;
    private boolean isArc=false;
    private Baustrasse baustr;
    
    
    public BaustrasseSymbol() {
        
    }
    /**
     *   die Darstellungsdaten (x1,yl,x2,y2,breite) werden aktualisiert und synchronisiert mit dem aktuellen Daten der verbundenen Baustrasse Physicalobject po
     */
    public void aktualisiere(PhysicalObject po){
        baustr= (Baustrasse) po;        
        this.isArc=baustr.isArc;
        if (isArc== false){
        x1 = baustr.getPunkt1().getX();
        y1 = baustr.getPunkt1().getY();
        x2 = baustr.getPunkt2().getX();
        y2 = baustr.getPunkt2().getY();
        }
        else {
            xc= baustr.xc;
            yc= baustr.yc;
            r= baustr.r;
            alpha1= baustr.alpha1;
            alpha2= baustr.alpha2;          
        }
        breite = baustr.getBreite();        
    }
    
    /**
     *  zeichnet die Straße an die aktuelle Stelle
     *  @param graphics2D 
     */ 
    public void paint(Graphics2D graphics2D){
        scaleX=getMassstab();
        graphics2D.scale(scaleX, scaleX);   
        Stroke baustrStroke= new BasicStroke( (float) breite , BasicStroke.CAP_BUTT , BasicStroke.CAP_ROUND );
        Stroke thindashed = new BasicStroke( 0.05f* (float)breite, BasicStroke.CAP_BUTT,
              /* join style, miter limit */BasicStroke.JOIN_BEVEL, 1.0f,
              /* the dash pattern */new float[] { 0.5f* (float)breite,0.5f* (float)breite },
              /* the dash phase */0.0f); /* on 8, off 3, on 2, off 3 */
        graphics2D.setStroke( baustrStroke );
        graphics2D.setPaint(BaustrasseColor );      
        try {
        if (baustr.abschnitte.size() > 1) {
            GeneralPath gp1,gp2;
            gp1 = new GeneralPath();
            gp2= new GeneralPath();
            int i=0;
            gp1.moveTo(baustr.abschnitte.get(0).punkt1.x, baustr.abschnitte.get(0).punkt1.y);
            gp2.moveTo(baustr.abschnitte.get(0).punkt1.x, baustr.abschnitte.get(0).punkt1.y);       
            for ( i=1; i < baustr.abschnitte.size(); i++) {             
                gp1.lineTo(baustr.abschnitte.get(i).punkt1.x,  baustr.abschnitte.get(i).punkt1.y);
                gp2.lineTo(baustr.abschnitte.get(i).punkt1.x,  baustr.abschnitte.get(i).punkt1.y);                              
            }           
            gp1.lineTo(baustr.abschnitte.get(i-1).punkt2.x,  baustr.abschnitte.get(i-1).punkt2.y);          
            gp2.lineTo(baustr.abschnitte.get(i-1).punkt2.x,  baustr.abschnitte.get(i-1).punkt2.y);          
            graphics2D.setStroke( baustrStroke );
            graphics2D.setPaint(BaustrasseColor );  
            graphics2D.draw((GeneralPath) gp1);
            graphics2D.setStroke(thindashed);
            graphics2D.setPaint( BaustrassGestrichelteColor);
            if (baustr.getFahrbahnen() == 2)
            graphics2D.draw((GeneralPath) gp2); 
            else
            drawFahrbahn(graphics2D);
        }
        else {      
        if (isArc==false) {     
        Line2D lin = new Line2D.Double(x1,y1, x2, y2);
        graphics2D.draw(lin);       
        graphics2D.setStroke(thindashed);
        graphics2D.setPaint( BaustrassGestrichelteColor);
        lin = new Line2D.Double(x1,y1, x2, y2);
        graphics2D.draw(lin); 
        }
        else {          
            graphics2D.draw(new Arc2D.Double(xc-r, yc-r, 2*r, 2*r, alpha1, alpha2,Arc2D.OPEN));
            graphics2D.setStroke(thindashed);
            graphics2D.setPaint( BaustrassGestrichelteColor);
            graphics2D.draw(new Arc2D.Double(xc-r, yc-r, r*2, r*2, alpha1, alpha2,Arc2D.OPEN));             
        }
        }
        } catch (java.lang.NullPointerException e) {}
        graphics2D.scale(1/scaleX, 1/scaleX);           
    }
    
    
    
    

    /**
     * die Baustrassenfarbe kann hier geändert werden
     * @param baustrasseColor the baustrasseColor to set
     */
    public void setBaustrasseColor(Color baustrasseColor) {
        BaustrasseColor = baustrasseColor;
    }
    /**
     * zeichnet die Baustrassenspuren
     * @param graphics2D
     */
    public void drawFahrbahn(Graphics2D graphics2D){
            Stroke thindashed = new BasicStroke( 0.05f* (float)breite, BasicStroke.CAP_BUTT,
              /* join style, miter limit */BasicStroke.JOIN_BEVEL, 1.0f,
              /* the dash pattern */new float[] { 0.5f* (float)breite,0.5f* (float)breite },
              /* the dash phase */0.0f); /* on 8, off 3, on 2, off 3 */
        if (baustr.abschnitte.size() > 1) {
            int i=0;
            graphics2D.setStroke(thindashed);
            graphics2D.setPaint( BaustrassGestrichelteColor);
            for ( i=0; i < baustr.abschnitte.size(); i++) { 
                double delta=breite / (baustr.getFahrbahnen());
                double dd=delta;
                for (int k=0; k < baustr.getFahrbahnen()-1; k++) {
                        double cos=Math.cos(Math.toRadians(baustr.abschnitte.get(i).winkel));
                        double sin= Math.sin(Math.toRadians(baustr.abschnitte.get(i).winkel));
                        graphics2D.draw( new Line2D.Double(
                        baustr.abschnitte.get(i).punkt1.x+( breite/2 - delta )* sin,
                        baustr.abschnitte.get(i).punkt1.y+( breite/2 - delta )*cos ,
                        baustr.abschnitte.get(i).punkt2.x+(breite/2  - delta )* sin-0.5*dd*cos,
                        baustr.abschnitte.get(i).punkt2.y+(breite/2  - delta )* cos-0.5*dd*sin));
                        delta=delta+dd;
                }           
            }
        }   
    }
}
