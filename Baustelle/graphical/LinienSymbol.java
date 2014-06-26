package Baustelle.graphical;
import Baustelle.physical.*;
import java.awt.*;
import java.awt.geom.*;

public class LinienSymbol extends GraphicalObject
{
    private double startX, startY, endX, endY;
    private Line2D line;

    public LinienSymbol(double startX, double startY, double endX, double endY)
    {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        line = new Line2D.Double(startX,startY,endX,endY);
        
    }
    
    public LinienSymbol(Line2D line) {
        this.line = line;
    }    
    

    public void aktualisiere(PhysicalObject po) {
        
    }    
    
    public void paint(Graphics2D g) {
        g.draw(line);
    }    
  
}
