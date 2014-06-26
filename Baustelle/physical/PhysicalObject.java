package Baustelle.physical;
import Beleg2_SS2014.*; 
import java.util.ArrayList;
import java.io.*;
import java.text.*;
import java.util.GregorianCalendar;
import java.util.Iterator;
import Baustelle.graphical.GraphicalObject;
import Baustelle.Bauplatz;
//import GraphicalObject;
//import cib.lehre.baustelle.gui.Baustelle;
//import cib.lehre.baustelle.gui.ZeichenPanel2;
/**
 * 
 * @author Ulf Wagner
 * @author Ali Ismail
 * Die Klasse PhysicalObject ist eine abstrake Klasse,sie ist die Grundklasse für alle physikalischen Objekte.
 *  
 */
public abstract class PhysicalObject  { 
    private boolean visible=false;
    private  double x,y,z,dx,dy,winkel;
    protected GraphicalObject go;
    private String notiz="";    
    protected ArrayList<GraphicalObject> beobachterListe = new ArrayList<GraphicalObject>();
    //public static ArrayList<GraphicalObject> zeichenObjekte = new ArrayList<GraphicalObject>();
    //public static double zeitfaktor=1;
    /**
     * Konstrukteur
     */
    public PhysicalObject() {
        createID();
    }
    public void zeichnen() {
        if (visible) {
            Bauplatz bauplatz = Bauplatz.gibBauplatz();
            bauplatz.zeichne(go);
            bauplatz.warte(10);
        }
    }
/**
 * fügt die graphischen Darrstellungsobjekte der physikalischen Objekte zu der Zeichnenobjektliste hinzu. 
 * @param beobachter
 */  
    public void meldeAn(GraphicalObject beobachter) {
        this.beobachterListe.add(beobachter);
        beobachter.aktualisiere(this);
        Bauplatz bauplatz = Bauplatz.gibBauplatz();
        bauplatz.zeichne(beobachter);
        //zeichenObjekte.add(beobachter);
        this.visible=true;
    }
/**
 * entferent die graphischen Darrstellungsobjekte der physikalischen Objekte von der Zeichnenobjektliste
 * @param beobachter
 */ 
    public void meldeAb(GraphicalObject beobachter) {
        this.beobachterListe.remove(beobachter);
        Bauplatz bauplatz = Bauplatz.gibBauplatz();
        bauplatz.entferne(beobachter);
        this.visible=false;
        //zeichenObjekte.remove(beobachter);
    }
/**
 * aktualisiert das graphische Darstellungobjekt
 */
    public  void benachrichtige() {     
        Iterator<GraphicalObject> it = this.beobachterListe.listIterator();
        while (it.hasNext()) {
            GraphicalObject go = (GraphicalObject) it.next();
            go.aktualisiere(this);
        }
        zeichnen();
    }
    /**
     * ändert den Zustand sichtbar oder unsichtbar des Objektes
     * @param visible
     */
    public  void setVisible(boolean visible){
        this.visible=visible;
        this.benachrichtige();
    }
    /**
     * berichtet über den sichtbar Zustand des Objektes
     * @return
     */
    public boolean isVisible(){
        return visible;
    }
    /**
     * berichtet über den aktuellen Zeitfaktor
     * @return
     */
    public  static double getZeitFaktor() {
        return 1.0; //ZeichenPanel2.zeitfaktor;
    }

    /**
     * ändert den aktuellen Zeitfaktor
     * @return
     */
    
    public static void setZeitFaktor(double neuerzeitfaktor) {
        //ZeichenPanel2.zeitfaktor=neuerzeitfaktor;
    }

    /**
     * berichtet über die aktuellen x-Koordinate des Objektes
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * ändert die aktuelle x-Koorinate des Ojbektes
     * @param x the x to set
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * berichtet über die aktuelle y-Koordinate des Objektes
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     *  ändert die aktuelle y-Koorinate des Ojbektes
     * @param y the y to set
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * berichtet über die aktuelle z-Koordinate des Objektes
     * @return the z
     */
    public double getZ() {
        return z;
    }

    /**
     *  ändert die aktuelle z-Koorinate des Ojbektes
     * @param z the z to set
     */
    public void setZ(double z) {
        this.z = z;
    }

    /**
     * @return the dx
     */
    public double getDx() {
        return dx;
    }

    /**
     * @param dx the dx to set
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * @return the dy
     */
    public double getDy() {
        return dy;
    }

    /**
     * @param dy the dy to set
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * berichtet übe       try{
      // Create file 
      FileWriter fstream = new FileWriter("tmp.class",true);
      BufferedWriter out = new BufferedWriter(fstream);
      DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.LONG);
      GregorianCalendar now = new GregorianCalendar(); 
      out.write(df.format(now.getTime()));
      out.write("\t"+Beleg1.bearbeiter+"\n");
      //Close the output stream
      counter++;
      out.close();
    }catch (Exception e){ }r den Winkel des Objektes
     * @return the winkel
     */
    public double getWinkel() {
        return winkel;
    }

    /**
     * änderet den Winkel des Objektes
     * @param winkel the winkel to set
     */
    public void setWinkel(double winkel) {
        this.winkel = winkel;
    }

    /**
     * berichtet über die Notizdaten des Objektes
     * @return the notiz
     */
    public String getNotiz() {
        return notiz;
    }

    /**
     * ändert die  Notizdaten des Objektes
     * @param notiz the notiz to set
     */
    public void setNotiz(String notiz) {
        this.notiz = notiz;
    }
    
     private static int counter = 0;
    
    private void createID() {
      if(counter == 0) {
        try{
            // Create file 
            FileWriter fstream = new FileWriter("tmp.class",true);
            BufferedWriter out = new BufferedWriter(fstream);
            DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.LONG);
            GregorianCalendar now = new GregorianCalendar(); 
            out.write(df.format(now.getTime()));
            out.write("\t"+Beleg2.bearbeiter+"\n");
            //Close the output stream
            counter++;
            out.close();
    }catch (Exception e){ }
      }    
      
    }    
    
    
}
