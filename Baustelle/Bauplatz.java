package Baustelle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.*;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.*;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JSlider;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import Baustelle.physical.Skala;


import Baustelle.graphical.GraphicalObject;
/**
 * Stellt die Objekte als graphische Objekte dar
 * 
 * 
 * @author: Clemens Gebhardt, Ulf Wagner
 * 
 * @version: 2006.03.30
 */
public class Bauplatz {

    private static Bauplatz leinwandSingleton;
    public static Skala skala;
  
    private static int breite = 880;
    private static int hoehe = 800;

    /**
     * Fabrikmethode, die eine Referenz auf das einzige Exemplar dieser Klasse
     * zurückliefert. Wenn es von einer Klasse nur genau ein Exemplar gibt, wird
     * dieses als 'Singleton' bezeichnet.
     */
    public static Bauplatz einrichten() {
        if (leinwandSingleton == null) {
            leinwandSingleton = new Bauplatz("Baustelle", breite, hoehe,
                    Color.white);
        }
        leinwandSingleton.setzeSichtbarkeit(true);
        return leinwandSingleton;
    }
    
    public static Bauplatz gibBauplatz() {
        return einrichten();
       }

    // ----- Exemplarvariablen -----

    private JFrame fenster;

    private Zeichenflaeche zeichenflaeche;

    public Graphics2D graphic;

    private Color hintergrundfarbe;

    private Image leinwandImage;

    private List<GraphicalObject> figuren;
    
    private JSlider slidermassstab;
   

    
    /**
     * Erzeuge eine Leinwand.
     * 
     * @param titel
     *            Titel, der im Rahmen der Leinwand angezeigt wird
     * @param breite
     *            die gewünschte Breite der Leinwand
     * @param hoehe
     *            die gewünschte Höhe der Leinwand
     * @param grundfarbe
     *            die Hintergrundfarbe der Leinwand
     */
    private Bauplatz(String titel, int breite, int hoehe, Color grundfarbe) {
        fenster = new JFrame();
        fenster.addComponentListener(new java.awt.event.ComponentAdapter() {
                public void componentResized(java.awt.event.ComponentEvent e) {
                    NeuAnpassen();
                }
            });
        zeichenflaeche = new Zeichenflaeche();
        slidermassstab=new JSlider();
        slidermassstab.setMaximum(50);
        slidermassstab.setMinimum(1);
        slidermassstab.setMajorTickSpacing(10);
        slidermassstab.setMinorTickSpacing(1);
        slidermassstab.setValue(10);
        slidermassstab.addChangeListener(new javax.swing.event.ChangeListener() {
                public void stateChanged(javax.swing.event.ChangeEvent e) {
                    double neum;
                    neum=(double)slidermassstab.getValue()/10;
                    GraphicalObject.setMassstab(neum);
                    fenster.setTitle("V: "+slidermassstab.getValue()+" Neuer Massstab: "+neum+" M: "+GraphicalObject.getMassstab());
                }
            });
        //fenster.setContentPane(zeichenflaeche);
        fenster.add(BorderLayout.CENTER,zeichenflaeche);
        fenster.add(BorderLayout.SOUTH,slidermassstab);
        fenster.setTitle(titel);
        zeichenflaeche.setPreferredSize(new Dimension(breite, hoehe));
        hintergrundfarbe = grundfarbe;
        fenster.pack();
        figuren = new ArrayList<GraphicalObject>();
    }

    /**
     * Setze, ob diese Leinwand sichtbar sein soll oder nicht. Wenn die Leinwand
     * sichtbar gemacht wird, wird ihr Fenster in den Vordergrund geholt. Diese
     * Operation kann auch benutzt werden, um ein bereits sichtbares
     * Leinwandfenster in den Vordergrund (vor andere Fenster) zu holen.
     * 
     * @param sichtbar
     *            boolean für die gewünschte Sichtbarkeit: true für sichtbar,
     *            false für nicht sichtbar.
     */
    public void setzeSichtbarkeit(boolean sichtbar) {
        if (graphic == null) {
            // erstmaliger Aufruf: erzeuge das Bildschirm-Image und fülle
            // es mit der Hintergrundfarbe
            Dimension size = zeichenflaeche.getSize();
            leinwandImage = zeichenflaeche.createImage(size.width, size.height);
            graphic = (Graphics2D) leinwandImage.getGraphics();
            graphic.setColor(hintergrundfarbe);
            graphic.fillRect(0, 0, size.width, size.height);
            graphic.setColor(Color.black);
        }
        fenster.setVisible(sichtbar);
    }
    
    
    public static void SkalaEinfuegen() {
        skala = new Skala();
        skala.setVisible(true);
    }
    
    /**
     * Passt das Zeichenfenster an die neue Fenstergröße an und zeichnet neu
     */
    public void NeuAnpassen() {
            Dimension size = zeichenflaeche.getSize();
            leinwandImage = zeichenflaeche.createImage(size.width, size.height);
            graphic = (Graphics2D) leinwandImage.getGraphics();
            graphic.setColor(hintergrundfarbe);
            graphic.fillRect(0, 0, size.width, size.height);
            graphic.setColor(Color.black);
            breite=size.width;
            hoehe=size.height;
            breite=fenster.getWidth();
            hoehe=fenster.getHeight();
            erneutZeichnen();
            //System.out.println("componentResized()"); // TODO Auto-generated Event stub componentResized()
    }
    
    /**
     * Zeichne ein neues Symbol auf der Leinwand
     * 
     * @param figur
     *            das Figur-Objekt, für das ein Shape gezeichnet werden soll
     */
    public void zeichne(GraphicalObject figur) {
        figuren.remove(figur); // entfernen, falls schon eingetragen
        figuren.add(figur); // am Ende hinzufügen
        
        erneutZeichnen();
    }
    
     

    /**
     * Entfernt ein Element vom Bauplatz
     * 
     * @param figur
     *            Element, dass vom Bauplatz entfernt wird
     */
    public void entferne(Object figur) {
        figuren.remove(figur); 
        erneutZeichnen();
    }

    /**
     * Setze die Zeichenfarbe der Leinwand.
     * 
     * @param farbname
     *            der Name der neuen Zeichenfarbe.
     */
    public void setzeZeichenfarbe(String farbname) {
        if (farbname.equals("rot")) {
            graphic.setColor(Color.red);
        } else if (farbname.equals("schwarz")) {
            graphic.setColor(Color.black);
        } else if (farbname.equals("blau")) {
            graphic.setColor(Color.blue);
        } else if (farbname.equals("gelb")) {
            graphic.setColor(Color.yellow);
        } else if (farbname.equals("gruen")) {
            graphic.setColor(Color.green);
        } else if (farbname.equals("lila")) {
            graphic.setColor(Color.magenta);
        } else if (farbname.equals("weiss")) {
            graphic.setColor(Color.white);
        } else {
            graphic.setColor(Color.black);
        }
    }

    /**
     * Warte für die angegebenen Millisekunden. Mit dieser Operation wird eine
     * Verzögerung definiert, die für animierte Zeichnungen benutzt werden kann.
     * 
     * @param millisekunden
     *            die zu wartenden Millisekunden
     */
    public void warte(int millisekunden) {
        try {
            Thread.sleep(millisekunden);
        } catch (Exception e) {
            // Exception ignorieren
        }
    }

    /**
     * gibt die Breite des Bauplatzes
     *
     * @return Die Breite
     */
    public static int getBreite() 
    {
        return breite;
    };
    
    /**
     * Gibt die Hoehe des Bauplatzes
     *
     * @return Die Höhe
     */
    public static int getHoehe() 
    {
        return hoehe;
    }
    
    /**
     * Zeichne erneut alle Objekte auf der Leinwand.
     */
    public void erneutZeichnen() {
        loeschen();
        try {
        for (GraphicalObject figur : figuren) {
            figur.paint(graphic);
        }
        } catch (Exception e) {}
        zeichenflaeche.repaint();
    }

   
    
    /**
     * Lösche die gesamte Leinwand.
     */
    private void loeschen() {
        Color original = graphic.getColor();
        graphic.setColor(hintergrundfarbe);
        Dimension size = zeichenflaeche.getSize();
        graphic.fill(new Rectangle(0, 0, size.width, size.height));
        graphic.setColor(original);
    }

    /***************************************************************************
     * Interne Klasse Zeichenflaeche - die Klasse für die GUI-Komponente, die
     * tatsächlich im Leinwand-Fenster angezeigt wird. Diese Klasse definiert
     * ein JPanel mit der zusätzlichen Möglichkeit, das auf ihm gezeichnet Image
     * aufzufrischen (erneut zu zeichnen).
     */
    private class Zeichenflaeche extends JPanel {
        private static final long serialVersionUID = 20060330L;

        public void paint(Graphics g) {
            g.drawImage(leinwandImage, 0, 0, null);
        }
    }
}
