/**
 * 
 */
package Baustelle.graphical;

import java.awt.*;
import java.util.Iterator;
import java.awt.image.*;
import java.awt.geom.*; 
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.Frame;
import java.awt.Image;
import java.awt.MediaTracker;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import Baustelle.Bauplatz;
import Baustelle.physical.PhysicalObject;
import Baustelle.physical.Skala;

/**
 *  Die Darstellungsklasse für die Skala
 * @author Clemens Gebhardt
 * @version 1.0
 *
 */
public class SkalaSymbol extends GraphicalObject {
    Skala obj;
    private double scaleX;

    public void aktualisiere(PhysicalObject po) {
        this.po=po;
        obj = (Skala) po;
    }

    /**
     * zeichnet die Skala
     */
    public void paint(Graphics2D g) {
        scaleX=getMassstab();
        Double neuBreite,neuHoehe;
        neuBreite=(Bauplatz.getBreite()/scaleX);
        neuHoehe=Bauplatz.getHoehe()/scaleX;
        g.scale(scaleX,scaleX);
        g.setColor(new Color(190,190,190));
        g.fill(new Rectangle2D.Double (0,0,neuBreite,12));
        g.fill(new Rectangle2D.Double (0,12,12,neuHoehe));
        
        int i=0;
        for (i=3;i<= (Math.round(neuBreite)/5);i++) {
            g.setColor(new Color(90,90,90));
            if ((i % 4) == 0) {
                g.draw(new Line2D.Double (i*5,0,i*5,12));
            }else {
                g.draw(new Line2D.Double (i*5,0,i*5,8));
            }
            if ((i % 20) == 0) {
                g.setColor(new Color(255,0,0));
                g.drawString(""+i*5,i*5-10,12+13);
            }
        }
        for (i=3;i<=(Math.round(neuHoehe)/5);i++) {
            g.setColor(new Color(90,90,90));
            if ((i % 4) == 0) {
                g.draw(new Line2D.Double (0,i*5,12,i*5));
            }else {
                g.draw(new Line2D.Double (0,i*5,8,i*5));
            }
            if ((i % 20) == 0) {
                g.setColor(new Color(255,0,0));
                g.drawString(""+i*5,14,i*5+5);
            }
        }
        g.drawString("0",2,12);
        g.scale(1/scaleX,1/scaleX);
    }

}
