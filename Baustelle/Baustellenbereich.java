package Baustelle;
import java.awt.*;
import Baustelle.physical.*;
import Baustelle.graphical.*;
import java.awt.geom.*;
import java.util.Random;
public class Baustellenbereich extends PhysicalObject
{
   // Rechteckiger Bereich der Baustellenbereich begrenzt
   private Rectangle bounds;
   private Fahrzeug[] assignedVehicles;
   private int index = 0;
   private int number = 0;
   
   
   public Baustellenbereich(int number) {
       this.number = number;
       bounds = new Rectangle();
       Random r = new Random();
       int startPointX = r.nextInt(500)+100;
       int startPointY = r.nextInt(500)+100;
       int width = r.nextInt(101)+100;
       int height = r.nextInt(351)+200;
       bounds = new Rectangle(startPointX, startPointY, height, width);
       int size = r.nextInt(3)+1;
       assignedVehicles = new Fahrzeug[size];
       for(int i=0; i<size; i++) {
           createVehicleInArea();
        }
       
   }
   
   public void createVehicleInArea() {
        Fahrzeug f = new Kipper();
        // Kipper innerhalb der rechteckigen Baustellenbegrenzung positionieren 
        Random r = new Random();
        double x,y;
        do {
               x = bounds.x+r.nextInt(bounds.width); 
               y = bounds.y+r.nextInt(bounds.height);
               f.setX(x);
               f.setY(y);
        }
        while(!bounds.contains(x,y));
        // Fahrzeug zuordnen
        if(bounds.contains(x,y)) assignedVehicles[index++] = f; 
        f.setVisible(true);
   }
   
   
   
   
   
   // Alle dem Bereich zugeordneten Fahrzeuge zurückliefern
   public Fahrzeug[] getAssignedVehicles() { return assignedVehicles; }

   
   public boolean isLineInBounds(Line2D line) {
       // TODO BEGIN Anhand der API zu Rectangle prüfen ob Linie Rechteck schneidet
        return bounds.intersectsLine(line);     
        // TODO END
        
   }    
   
   public int getNumber() {
        return number;    
   }    
   
   public Rectangle getRectangleBounds() { return bounds; }
   
   
   public void setVisible(boolean visible) {
      if (visible) {
            if (!isVisible()){              
                go = new BereichsSymbol();
                meldeAn(go);
                benachrichtige();           
                super.setVisible(visible);              
            }
        }
        else {
            if (isVisible()){
                meldeAb(this.go);
                benachrichtige();
                super.setVisible(visible);
            }
        }    
       
   }    
   
   
}
