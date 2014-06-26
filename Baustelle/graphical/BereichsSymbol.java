package Baustelle.graphical;
import Baustelle.physical.*;
import java.awt.*;
import Baustelle.*;

import java.awt.geom.*;

public class BereichsSymbol extends GraphicalObject
{
   private Rectangle bounds; 
   private Baustellenbereich bereich;
   

   public void aktualisiere(PhysicalObject po){
        this.po=po;
        bereich= (Baustellenbereich) po;  
        bounds = bereich.getRectangleBounds();
    }
    
    public void paint (Graphics2D graphics2D) {
        graphics2D.draw(bounds);
        graphics2D.drawString(new Integer(bereich.getNumber()).toString(), bounds.x, bounds.y);
       
       
    }   
    
    
    
}
