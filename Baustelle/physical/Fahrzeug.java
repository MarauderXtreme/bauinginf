package Baustelle.physical;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import Baustelle.graphical.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;


/**
 * Diese Klasse stellt bewegliche Fahrzeuge dar, z.B. LKW, Bagger, Kipper, usw.
 * <p>Funktionelle Darstellung:
 * <ul>
 * <li> vor- und zurückfahren
 * <li> stoppen
 * <li> drehen
 * <li> fahren auf einer "Baustrasse" 
 * <li> Material be- und entladen
 * </ul>

 * @author Ali Ismail
 * @author Ulf Wagner
 * @version 1.0
 * 
 */
public abstract class Fahrzeug extends Baugeraet {
    private double x ,y ;
    Bezugspunkt bezugspunkt;
    private double breite=20;
    private double laenge=60;
    private   double winkel;
    private double geschwindigkeit=0;
    private double maxZuladung;
    protected ArrayList<Material> materialListe = new ArrayList<Material>();    
    private Runnable runnable;
    private boolean fahrzeugFahrt=false;

    /**
     *  Konstruktor für Fahrzeug
     */
    public Fahrzeug (){
        this.x=this.laenge/2;
        this.y=this.breite/2;       
        this.winkel=0;      
    }
    
    /**
     *  Konstruktor für Fahrzeug
     * @param x          x-Position (Meter)
     * @param y          y-Position (Meter)
     * @param winkel     Winkel (degree)
     */
    public Fahrzeug (double x, double y , double winkel) {
        this.x=x;
        this.y=y;
        this.winkel=winkel;     
    }
    
        /**
     * Das Fahrzeug fahrt auf die Baustrasse (baustr1) vom Beginn bis Ende
     * @param Baustrasse baustr1
     * @throws InterruptedException
     */  
     private synchronized void fahreAufStrasse(Baustrasse baustr1) throws InterruptedException   {
          fahreAufStrasse(0, baustr1,0);         
     }
     /**
         *  Der fahrzeug fahrt s seconds auf der Strasse baustr, das Fahrt fangt von die Starssestuck nummer j
         * @param s : Fahrenzeit
         * @param baustr1 : Baustrasse Objekt
         * @param j : Der Strasseabschnitt, den das Fahrzeug fahrt von.
         * @throws InterruptedException 
         */
         public synchronized void fahreAufStrasse(double s,Baustrasse baustr1,final int j) throws InterruptedException {
            final double s1 = s*1000;           
            final double zeitFaktor=this.getZeitFaktor();
            final Baustrasse baustr=baustr1;            
            runnable = new Runnable() {
                public  void run() {                    
                    while (fahrzeugFahrt== true) {};    
                    fahrzeugFahrt=true;             
                    double fahreZeit=0;
                    double fahreLange=0;
                    double startZeit = System.currentTimeMillis();
                    double lastZeit=startZeit;
                    double deltaS;
                    int i=j;
                    double strassLange;
                    strassLange=baustr.abschnitte.get(i).getLaenge();
                    winkel=baustr.abschnitte.get(i).getWinkel();    
                    x=baustr.abschnitte.get(i).punkt1.x- breite*Math.sin(Math.toRadians(winkel));
                    y=baustr.abschnitte.get(i).punkt1.y+ breite*Math.cos(Math.toRadians(winkel));               
                    double aktuellezeitFaktor=zeitFaktor;                   
                    while (  (s1 > fahreZeit || (s1==0 && i <= baustr.abschnitte.size())  )  && fahrzeugFahrt==true)  {
                        if (aktuellezeitFaktor != getZeitFaktor()) {
                            aktuellezeitFaktor=getZeitFaktor();
                        }
                        
                            if (fahreLange >= strassLange){
                            i=i+1;  
                            if (i < baustr.abschnitte.size()){
                                winkel=baustr.abschnitte.get(i).getWinkel();
                                x=baustr.abschnitte.get(i).punkt1.x-breite*Math.sin(Math.toRadians(winkel));
                                y=baustr.abschnitte.get(i).punkt1.y+breite*Math.cos(Math.toRadians(winkel));                                
                                strassLange=baustr.abschnitte.get(i).getLaenge();
                                benachrichtige();
                                fahreLange=0;   
                            }
                            else {
                                winkel=baustr.abschnitte.get(i-1).getWinkel();
                                x=baustr.abschnitte.get(i-1).punkt2.x-breite*Math.sin(Math.toRadians(winkel));
                                y=baustr.abschnitte.get(i-1).punkt2.y+breite*Math.cos(Math.toRadians(winkel));                              
                                benachrichtige();
                                fahrzeugFahrt=false;
                                break;
                            }                                                       
                        }                                           
                         deltaS = aktuellezeitFaktor*(System.currentTimeMillis() - lastZeit);
                         lastZeit=System.currentTimeMillis();
                         fahreZeit=fahreZeit+deltaS;
                         fahreLange=fahreLange +(deltaS*geschwindigkeit/3600);
                        x=x + (deltaS*geschwindigkeit/3600)* Math.cos(winkel*Math.PI/180);
                        y=y + (deltaS*geschwindigkeit/3600)* Math.sin(winkel*Math.PI/180);                      
                        benachrichtige();               
                    }                   
                    fahrzeugFahrt=false;
                }
            };
            runnable.run();
        }       

    
    /**
     *  Konstrukteur für Fahrzeug
     * @param bezugspunkt   Bezugspunkt x-Koordinate und  y-Koordinate (Meter)
     * @param winkel        Winkel (degree)
     *  Ausgangswert für Fahrzeugbreite = 2 meter , Fahrzeuglänge=6 meter 
     */
    public Fahrzeug (Bezugspunkt bezugspunkt, double winkel) {
         this.x=bezugspunkt.getX();
         this.y=bezugspunkt.getY();
         this.winkel=winkel;
    }
    
    /**
     * Konstruktor für Fahrzeug
     * @param x          x-Position (Meter)
     * @param y          y-Position (Meter)
     * @param winkel     Winkel (degree)
     * @param breite    Die fahrzeugbreite (Meter)
     * @param laenge    Die fahrzeuglänge (Meter)
     */
    public Fahrzeug (double x, double y , double winkel,double breite, double  laenge) {
        this.x=x;
        this.y=y;
        this.winkel=winkel;
        this.breite=breite;
        this.laenge=laenge;
        
    }
    /**
     * gibt die aktuelle x-Koordinate zurück
     * @return  aktuelle x-Koordinate
     */
    public double getX() {
        return x;
    }

    /**
     * setze neue x-Koordinate
     * @param x neue x-Koordinate
     */
    public void setX(double x) {
        this.x = x;
        benachrichtige();
    }

    /**
     * gibt die aktuelle y-Koordinate zurück
     * @return aktuelle y-Koordinate
     */
    public double getY() {
        return y;
    }

    /**
     * setze neue y-Koordinate
     * @param y neue y-Koordinate
     */
    public void setY(double y) {
        this.y = y;
        benachrichtige();
    }
    
    /**
     * Setzt die Sichtbarkeit des Objektes
     * 
     */
    public void setVisible(boolean visible) {
        
        if (visible) {
            if (!isVisible()){              
                go = new LKWSymbol();
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

    /**
     * gibt den Winkel zurück
     * @return den Winkel
     */
    public double getWinkel() {
        return winkel;
    }

    /**
     * setze neuen Winkel
     * @param winkel neuer Winkel des Fahrzeugs
     */
     public synchronized void setWinkel(double winkel) {
        this.winkel = winkel;
        benachrichtige();
    }

    
    /**
     * @return die Geschwindigkeit
     */
    public double getGeschwindigkeit() {
        return geschwindigkeit;
    }

    /**
     * ändert die Geschwindigkeit
     * @param geschwindigkeit die Geschwindigkeit
     */
    public void setGeschwindigkeit(double geschwindigkeit) {
        this.geschwindigkeit = geschwindigkeit;
    }
    /**
     * gibt die aktuelle Fahrzeugbreite zurück
     * @return die Breite
     */
    public double getBreite() {
        return breite;
    }

    /**
     * setze neue Fahrzeugbreite
     * @param breite neue Fahrzeugbreite
     */
    public void setBreite(double breite) {
        this.breite = breite;
        this.benachrichtige();
    }

    /**
     * gibt die aktuelle Fahrzeuglänge zurück
     * @return the laenge
     */
    public double getLaenge() {
        return laenge;
    }

    /**
     * setze neue Fahrzeuglänge
     * @param laenge neue Fahrzeuglänge
     */
    public void setLaenge(double laenge) {
        this.laenge = laenge;
        this.benachrichtige();
    }
    
    /**
     * gibt zurück, ob das Fahrzeug gerade fährt
     * @return ob Fahrzeug fährt
     */
    public boolean isFahrt() {
        return fahrzeugFahrt;
    }

    /**
     * bewege das Vorzeug um die angegebene Distanz
     * @param d Distanz
     */
    public void vorwaerts(int d) {      
        x=x + d* Math.cos(winkel*Math.PI/180);
        y=y + d* Math.sin(winkel*Math.PI/180);  
        benachrichtige();
    }

    /**
     * Fahrzeug fährt die Streckenlänge in angegebener Geschwindigkeit in 5ms-Schritten
     * @param distanz
     * @param geschwindigkeit
     */
    public void fahre(int distanz, int geschwindigkeit) throws Exception {
        int schritte = Math.abs(distanz/geschwindigkeit);
        for (int i=0;i<= (schritte);i++) {
            this.vorwaerts(geschwindigkeit);
            try {Thread.sleep(5);} catch (Exception e) {}
        }
    }
    
    /**
     * Fahrzeug fährt die Streckenlänge in eingestellter Geschwindigkeit in 5ms-Schritten
     * @param distanz
     */
    public void fahre(int distanz) {
        int schritte = Math.abs(distanz/((int)this.getGeschwindigkeit()));
        for (int i=0;i<= (schritte);i++) {
            this.vorwaerts((int)this.getGeschwindigkeit());
            try {Thread.sleep(5);} catch (Exception e) {}
        }
    }
    
    /**
     * berichtet über die Maximale Nutzlast
     * @return the maxZuladung
     */
    public double getMaxZuladung() {
        return maxZuladung;
    }

    /**
     * ändert die Maximale Nutzlast
     * @param maxZuladung the maxZuladung to set
     */
    public void setMaxZuladung(double maxZuladung) {
        this.maxZuladung = maxZuladung;
    }
/**
 * aktualisiert die grafische Darstellung der Fahrzeuge 
 */
public void benachrichtige(){
    super.setX(this.x);
    super.setY(this.y);
    super.setDx(this.laenge);
    super.setDy(this.breite);
    super.setWinkel(this.winkel);
    super.benachrichtige();
}

    /**
     * Das Fahrzeug fährt auf der Baustrasse (baustr1) vom Beginn bis Ende
     * @param  baustr1 Baustrasse
     * @throws InterruptedException
     */  
     /*public synchronized void fahreAufStrasse(Baustrasse baustr1) throws InterruptedException   {
          fahreAufStrasse(0, baustr1,0);         
     }*/
     /**
      *  Das Fahrzeug fährt in s Sekunde auf der Baustrasse, die Fahrt beginnt vom eingegebinen Streckenabschnitt
      * @param s : Fahrenzeit
      * @param baustr1 : Baustrasse Objekt
      * @param j : Der Strasseabschnitt von dem das Fahrzeug fährt.
      * @throws InterruptedException 
     */
        /* public synchronized void fahreAufStrasse(double s,Baustrasse baustr1,final int j) throws InterruptedException {
            final double s1 = s*1000;           
            final double zeitFaktor=getZeitFaktor();
            final Baustrasse baustr=baustr1;            
            runnable = new Runnable() {
                public  void run() {                    
                    while (fahrzeugFahrt== true) {};    
                    fahrzeugFahrt=true;             
                    double fahreZeit=0;
                    double fahreLange=0;
                    double startZeit = System.currentTimeMillis();
                    double lastZeit=startZeit;
                    double deltaS;
                    int i=j;
                    double strassLange;
                    strassLange=baustr.abschnitte.get(i).getLaenge();
                    winkel=baustr.abschnitte.get(i).getWinkel();    
                    x=baustr.abschnitte.get(i).punkt1.x- breite*Math.sin(Math.toRadians(winkel));
                    y=baustr.abschnitte.get(i).punkt1.y+ breite*Math.cos(Math.toRadians(winkel));               
                    double aktuellezeitFaktor=zeitFaktor;                   
                    while (  (s1 > fahreZeit || (s1==0 && i <= baustr.abschnitte.size())  )  && fahrzeugFahrt==true)  {
                        if (aktuellezeitFaktor != getZeitFaktor()) {
                            aktuellezeitFaktor=getZeitFaktor();
                        }
                        
                            if (fahreLange >= strassLange){
                            i=i+1;  
                            if (i < baustr.abschnitte.size()){
                                winkel=baustr.abschnitte.get(i).getWinkel();
                                x=baustr.abschnitte.get(i).punkt1.x-breite*Math.sin(Math.toRadians(winkel));
                                y=baustr.abschnitte.get(i).punkt1.y+breite*Math.cos(Math.toRadians(winkel));                                
                                strassLange=baustr.abschnitte.get(i).getLaenge();
                                benachrichtige();
                                fahreLange=0;   
                            }
                            else {
                                winkel=baustr.abschnitte.get(i-1).getWinkel();
                                x=baustr.abschnitte.get(i-1).punkt2.x-breite*Math.sin(Math.toRadians(winkel));
                                y=baustr.abschnitte.get(i-1).punkt2.y+breite*Math.cos(Math.toRadians(winkel));                              
                                benachrichtige();
                                fahrzeugFahrt=false;
                                break;
                            }                                                       
                        }                                           
                         deltaS = aktuellezeitFaktor*(System.currentTimeMillis() - lastZeit);
                         lastZeit=System.currentTimeMillis();
                         fahreZeit=fahreZeit+deltaS;
                         fahreLange=fahreLange +(deltaS*geschwindigkeit/3600);
                        x=x + (deltaS*geschwindigkeit/3600)* Math.cos(winkel*Math.PI/180);
                        y=y + (deltaS*geschwindigkeit/3600)* Math.sin(winkel*Math.PI/180);                      
                        benachrichtige();               
                    }                   
                    fahrzeugFahrt=false;
                }
            };
            execute(runnable);  
        }       */
         
         /**
          * Das Fahrzeug fährt auf der Baustrasse , aber in die andere Fahrtrichtung
          * @param s : Fahrzeit
          * @param baustr1 : Baustrasse Objekt
          * @param j : Der Strassenabschnitt auf dem das Fahrzeug fährt.
          * @throws InterruptedException
          */
         public synchronized void fahreAufStrasseEnd(double s,Baustrasse baustr1,final int j) throws InterruptedException {
                final double s1 = s*1000;           
                final double zeitFaktor=getZeitFaktor();
                final Baustrasse baustr=baustr1;            
                /*runnable = new Runnable() {
                    public  void run() {                    
                        while (fahrzeugFahrt== true) {};    */
                        fahrzeugFahrt=true;             
                        double fahreZeit=0;
                        double fahreLange=0;
                        double startZeit = System.currentTimeMillis();
                        double lastZeit=startZeit;
                        double deltaS;
                        
                        int i=baustr.abschnitte.size()-1;
                        if(j != baustr.abschnitte.size() && j > 0 && j <  baustr.abschnitte.size() ) {
                            i=j-1;                      
                        }
                            
                        
                        double strassLange;
                        strassLange=baustr.abschnitte.get(i).getLaenge();
                        winkel=baustr.abschnitte.get(i).getWinkel() + 180;  
                        x=baustr.abschnitte.get(i).punkt2.x- breite*Math.sin(Math.toRadians(winkel));
                        y=baustr.abschnitte.get(i).punkt2.y+ breite*Math.cos(Math.toRadians(winkel));
                        
                        double aktuellezeitFaktor=zeitFaktor;                   
                        while  (  (s1 > fahreZeit || (s1==0 && i >= 0)  )  && fahrzeugFahrt==true) {
                            if (aktuellezeitFaktor != getZeitFaktor()) {
                                aktuellezeitFaktor=getZeitFaktor();
                            }                   
                            if (fahreLange >= strassLange){
                                i=i-1;  
                                if (i  >=0 ){
                                    winkel=baustr.abschnitte.get(i).getWinkel()+180;
                                    x=baustr.abschnitte.get(i).punkt2.x-breite*Math.sin(Math.toRadians(winkel));
                                    y=baustr.abschnitte.get(i).punkt2.y+breite*Math.cos(Math.toRadians(winkel));                                
                                    strassLange=baustr.abschnitte.get(i).getLaenge();
                                    benachrichtige();
                                    fahreLange=0;   
                                }
                                else {
                                    
                                //  winkel=baustr.abschnitte.get(0).getWinkel()+180;
                                //  x=baustr.abschnitte.get(0).punkt2.x-breite*Math.sin(Math.toRadians(winkel));
                                //  y=baustr.abschnitte.get(0).punkt2.y+breite*Math.cos(Math.toRadians(winkel));                                
                                //  benachrichtige();   
                                    
                                }                                                       
                            }                                           
                             deltaS = aktuellezeitFaktor*(System.currentTimeMillis() - lastZeit);
                             lastZeit=System.currentTimeMillis();
                             fahreZeit=fahreZeit+deltaS;
                             fahreLange=fahreLange +(deltaS*geschwindigkeit/3600);
                            x=x+(deltaS*geschwindigkeit/3600)* Math.cos(winkel*Math.PI/180);
                            y=y +(deltaS*geschwindigkeit/3600)* Math.sin(winkel*Math.PI/180);                                               
                            benachrichtige();               
                        }                               
                        
                        fahrzeugFahrt=false;
                    }
                /*};
                execute(runnable);*/    
            //}     
         
         /*public synchronized void fahreAufStrasseEnd(Baustrasse baustr1) throws InterruptedException   {
             fahreAufStrasseEnd(0, baustr1,baustr1.abschnitte.size());       
         }      */ 
    /**
     * drehet das Fahrzeug in einem bestimmten Winkel 
     * @param alpha ( Grad 0 bis 360)
     */
    public void drehen (double alpha){      
        winkel=alpha;
        benachrichtige();
    }
    
    /**
     * Stoppt das Fahrzeug
     */
    public void stoppen(){
        this.fahrzeugFahrt=false;
    }
    /**
     *  beladet das Fahrzeug 
     * @param matrial :Das Material Objekt
     * @throws Exception 
     *
     */
    public void beladen(Material matrial) throws Exception{
        materialListe.add(matrial); 
        this.benachrichtige();  
    }
    
    /**
     * entladet das Fahrzeug 
     * @param matrial
     * 
     */
    public void entladen(Material matrial){
        if (this.fahrzeugFahrt==false) {
        materialListe.remove(matrial);
        this.benachrichtige();
        }
    }
    
    /**
     * Entlädt gesamtes Fahrzeug
     */
    public void entladen() {
         if (this.fahrzeugFahrt==false) materialListe.clear();
    }    
   
    

    /**
     * wiedergabe der Materialliste,was das Fahrzeug schon beladen hat.
     * @return the materialListe
     */
    public ArrayList<Material> getMaterialListe() {
        return materialListe;
    }

    /**
     * beladet das Fahrzeuge mit einer Materialliste.
     * @param materialListe the materialListe to set
     */
    public void setMaterialListe(ArrayList<Material> materialListe) {
        this.materialListe = materialListe;
    }

    
    /**
     * Gibt Anzahl der geladenen Materialien zurück
     */
     public int getMaterialAnzahl() {
        return this.materialListe.size();
     }    
    
    /**
     * Gibt die Lage eines Objektes auf der Baustellenfläche
     * @return the bezugspunkt
     */
    public Bezugspunkt getBezugspunkt() {
         bezugspunkt.x=this.getX();
         bezugspunkt.y=bezugspunkt.getY();
        return bezugspunkt;
    }

    /**
     *  set die Lage eines Objektes auf der Baustellenfläche
     * @param bezugspunkt the bezugspunkt to set
     */
    public void setBezugspunkt(Bezugspunkt bezugspunkt) {
        this.x=bezugspunkt.x;
        this.y=bezugspunkt.y;
        this.bezugspunkt = bezugspunkt;
        this.benachrichtige();
    }

    /**
     * @param fahrzeugFahrt the fahrzeugFahrt to set
     */
    public void setFahrt(boolean fahrzeugFahrt) {
        this.fahrzeugFahrt = fahrzeugFahrt;
    }

    /**
     * berichtet die Eigenschaften der Fahrzeuge wie die Lage und Geschwiendigkeit,Materialliste.
     * @return String
     */ 
    public String getEigenschaften(){
        NumberFormat formatter = new DecimalFormat("0.00"); 
        String  s = "<html>Class = " + this.getClass().getSimpleName() +
       "<br>x = " +formatter.format(this.getX())+
       ",  y = "    + formatter.format(this.getY()) +
       "<br>w = "   + formatter.format(this.getDx()) +
       "<br>h = "   + formatter.format(this.getDy()) +
       "<br>, <b>Speed</b> = "     + formatter.format(this.getGeschwindigkeit()) +
       "<br>, <b>Material</b> = "     + this.getMaterialListe();
        s =s + "<br>" + this.getNotiz();
        return s;
    }
    
    /*
     * wiedergabe des Winkelwertes
     * @param dx
     * @param dy
     * @return
     */
    private  double getDegree(double dx ,double dy){
        if(dx == 0)
        return 90+(dy<0 ? 180 : 0);
        if(dy == 0)
        return dx<0 ? 180 : 0;
        double tmpDeg = Math.toDegrees(Math.atan(dy/dx));
        if(dx > 0 && dy > 0)
        return tmpDeg;
        if(dx < 0 &&  dy > 0)
             return tmpDeg+180.0;
        if(dx > 0  && dy < 0)
             return tmpDeg;
        if(dx < 0  && dy < 0)
             return tmpDeg+180;
        return tmpDeg; 
    }       
    
    /**
     * Liefert das Gesamtvolumen aller geladenen Materialien, oder 0
     */
    public double getLadungGesamtVolumen() {
        double gesamtVolumen = 0;
        for(Material m : materialListe) {
            gesamtVolumen = gesamtVolumen + m.getVolumen();
        }    
        return gesamtVolumen;
    }    
    

}
