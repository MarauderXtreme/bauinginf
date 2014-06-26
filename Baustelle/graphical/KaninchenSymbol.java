/**
 * 
 */
package Baustelle.graphical;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.Frame;
import java.awt.Image;
import java.awt.MediaTracker;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


import Baustelle.physical.PhysicalObject;
import Baustelle.physical.Kaninchen;

/**
 *  Die Darstellungsklasse für das Kaninchen
 * @author Clemens Gebhardt, Ulf Wagner
 * @version 1.5
 *
 */
public class KaninchenSymbol extends GraphicalObject {
    Kaninchen obj;
    private double x;
    private double y;
    private double hoehe=172;
    private double breite=114;
    private double scaleX;
    //private java.awt.image.BufferedImage kaninchenbild;
    private static Frame f = new Frame();
    //public static Image kaninchenbild;
    private static BufferedImage kaninchenbild;
    private static URL url;

    static {
      /*  try {url = new URL("http://kamelopedia.mormo.org/images/7/7b/Kaninchen.png");}
        catch (MalformedURLException e) {}
        MediaTracker mt = new MediaTracker( f );
        kaninchenbild = f.getToolkit().getImage(url);
        mt.addImage( kaninchenbild, 0 );
        try { mt.waitForAll(); } catch( InterruptedException ex ) { }*/
        File bilddatei = new File("Baustelle/graphical/Kaninchen.png");
        //System.out.println( bilddatei.getAbsolutePath() );
        try {
            kaninchenbild = ImageIO.read(bilddatei);
        }
        catch (IOException ioe) 
        {
            System.out.println("Kaninchenbild nicht gefunden");
        }
    }

    public void aktualisiere(PhysicalObject po) {
        this.po=po;
        obj = (Kaninchen) po;
        x=obj.getX();
        y=obj.getY();
        breite=obj.getBreite();
        hoehe=obj.getHoehe();
    }

    /**
     * zeichnet das Kaninchen
     */
    public void paint(Graphics2D g) {
        scaleX=getMassstab();
        g.scale(scaleX, scaleX);
        g.drawImage(kaninchenbild, (int) x, (int) y, (int) hoehe, (int) breite, new Frame());
        g.scale(1/scaleX, 1/scaleX);
        /*scaleX=getMassstab();
        g.scale(scaleX, scaleX);
        g.setColor(new Color (0,0,0,255));
        g.draw(new Rectangle2D.Double (x-0.5*laenge, y-0.5*breite, laenge, breite));
        g.setColor(new Color(255,255,255,255));
        g.fill(new Rectangle2D.Double (x-0.5*laenge, y-0.5*breite, laenge, breite));
        g.scale(1/scaleX, 1/scaleX);*/
    }

}
