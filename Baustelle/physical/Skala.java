/**
 * 
 */
package Baustelle.physical;

import java.awt.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import Baustelle.graphical.SkalaSymbol;

/**
 * Eine Skala
 * @author Clemens Gebhardt, Ulf Wagner
 * @version 1.5
 *
 */
public class Skala extends PhysicalObject {
    
    /**
     * Konstrukteur
     */
    public Skala(){ 

    }
    
    /**
     * ändert  sichtbar oder unsichtbar des Kaninchens
     */     
    public void setVisible(boolean visible) {
        
        if (visible) {
            if (!isVisible()){          
                go = new SkalaSymbol();
                benachrichtige();
                meldeAn(go);
                benachrichtige();           
                super.setVisible(visible);
            
            }
        }
        else {
            if (isVisible()){
                benachrichtige();
                meldeAb(this.go);
                benachrichtige();
                super.setVisible(visible);
            }
        }
    }




    
    
    protected void finalize()
    {
        this.setVisible(false);
    }
}
