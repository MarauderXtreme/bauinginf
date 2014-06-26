package Baustelle.physical;
/**
 * 
 */

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import Baustelle.graphical.KranSymbol;
//import KranSymbol;
//import Material;

/**
 * Die Kranklasse stellt die Kräne auf der Baustelle dar.
 * <p>Die Funktionen der Kräne sind
 * <ul>
 * <li> Auslegerdrehen in beide Richtungen
 * <li> Krane bewegen vor und zurück auf einer Schiene
 * <li> Katze bewegen vor und zurück am Ausleger
 * <li> Material entladen
 * <li> Material abladen
 * <li> Kranhaken heben und senken 
 * </ul>
 * <p>Der Kran kann feststehend oder beweglich sein, der Benutzer muss alle benötigten Daten dem Kran geben, z.B:
 * Auslegerlänge,Kranhöhe und die Koordinaten der Kranschiene.
 * @author Ulf Wagner
 * @author Ali Ismail
 *
 */



public class Kran extends Baugeraet {
    private double x0,y0,x1,y1; 
    private String KranCode;
    private double x ,y ;
    private double auslegerwinkel;  
    private double katzeLocation=1;
    private double katzeXLocation;  
    private double katzeYLocation;
    private double drehenGeschwindigkeit;
    private double katzeGeschwindigkeit;
    private double baseGeschwindigkeit;
    private double hakenGeschwindigkeit;
    private double maxTragfaehigkeit;   
    private double minTragfaehigkeit;
    private double aktuellTragfaehigkeit;
    private double aktuellLast;
    private double baseBreite;
    private double baseLocation;
    private double baseBewegenLaenge;
    private double ausLegerlaenge;
    private double kranHohe;
    private double hakenHohe;
    private Runnable runnable;  
    private boolean krandrehen=false;
    private boolean katzebewegt=false;
    private boolean basebewegt=false;
    private boolean hakenbewegt=false;
    protected ArrayList<Material> materialListe = new ArrayList<Material>();    
    
    /**
     * Konstruktor für den Kran
     * @param kranCode  Krancode ?
     */
    public Kran(String kranCode) {
        this.x=0;
         this.y=0;
         this.x0=0;
         this.x1=0;
         this.y0=0;
         this.y1=0;
         this.baseBewegenLaenge=0;
         this.baseLocation=0;
         this.katzeXLocation=0;
         this.katzeYLocation=0;
         this.katzeLocation=0;
         this.baseBreite=3;
         this.ausLegerlaenge=30;
         this.KranCode=kranCode;        
    }
    /**
     *  Konstruktor für den Kran 
     */
    public Kran(){
         this.x=0;
         this.y=0;
         this.x0=0;
         this.x1=0;
         this.y0=0;
         this.y1=0;
         this.baseBewegenLaenge=0;
         this.baseLocation=0;
         this.katzeXLocation=0;
         this.katzeYLocation=0;
         this.katzeLocation=0;
         this.baseBreite=3;
         this.ausLegerlaenge=30;
         this.KranCode="";
    }
    /**
     *   Konstruktor für den Kran 
     * @param x     x-Koordinate für den neuen Kran
     * @param y     y-Koordinate für den neuen Kran
     * @param fussbreite    die Fussbreite
     * @param auslegerLaenge die Auslegerlänge
     *  <p>Ausgangswerte : 
     *  <ul>
     *  <li>(x0,y0)=(x1,y1)=(x,y)
     *  <li>katzeXLocation=x
     *  <li>katzeYLocation=y
     *  <li>katzeLocation=0
     *  <li>drehenGeschwindigkeit=0
     *  <li>katzeGeschwindigkeit=0
     *  <li>baseGeschwindigkeit=0
     *  <li>minTragfaehigkeit=0
     *  <li>maxTragfaehigkeit=0
     *  </ul>
     */
    public Kran(double x,double y,double fussbreite, double auslegerLaenge){
         this.x=x;
         this.y=y;
         this.x0=x;
         this.x1=x;
         this.y0=y;
         this.y1=y;
         this.baseBewegenLaenge=0;
         this.baseLocation=0;
         this.katzeXLocation=x;
         this.katzeYLocation=y;
         this.katzeLocation=0;
         this.baseBreite=fussbreite;
         this.ausLegerlaenge=auslegerLaenge;
         this.KranCode="";
    }
    
    /**
     *  setzt die Sichtbarkeit des Kranes
     */
    public void setVisible(boolean visible) {
        
        if (visible) {
            if (!isVisible()){              
                go = new KranSymbol();
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
     *  gibt die aktuelle x-Koordinate zurück
     * @return x
     */
    public double getX() {
        return x;
    }

    /**
     * Setzt die x-Koordinate
     * @param x neue x-Koordinate
     */
    public void setX(double x) {
        this.x = x;
        benachrichtige();
    }

    /**
     *   gibt die aktuelle y-Koordinate zurück
     * @return y
     */
    public double getY() {
        return y;
    }

    /**
     *Setzt die y-Koordinate
     * @param y neue y-Koordinate
     */
    public void setY(double y) {
        this.y = y;
        benachrichtige();
    }
    
    /**
     * gibt die aktuelle x-Koordinate des Anfangs der Schiene zurück
     * @return x0
     */
    public double getX0() {
        return x0;
    }

    /**
     * setzt die x-Koordinate des Anfangs der Schiene
     * @param x0 neue x-Koordinate
     */
    public void setX0(double x0) {
        this.x0 = x0;
        benachrichtige();
    }

    /**
     * gibt die aktuelle y-Koordinate des Anfangs der Schiene zurück
     * @return y0
     */
    public double getY0() {
        return y0;
    }

    /**
     * setzt die y-Koordinate des Anfangs der Schiene
     * @param y0 neue y-Koordinate
     */
    public void setY0(double y0) {
        this.y0 = y0;
        benachrichtige();
    }

    /**
     * gibt die aktuelle x-Koordinate des Endes der Schiene zurück
     * @return x1
     */
    public double getX1() {
        return x1;
    }

    /**
     * setzt die x-Koordinate des Endes der Schiene
     * @param x1 neue x-Koordinate
     */
    public void setX1(double x1) {
        this.x1 = x1;
        benachrichtige();
    }

    /**
     * gibt die aktuelle y-Koordinate des Endes der Schiene zurück
     * @return the y1
     */
    public double getY1() {
        return y1;
    }

    /**
     * setzt die y-Koordinate des Endes der Schiene
     * @param y1 neue y-Koordinate
     */
    public void setY1(double y1) {
        this.y1 = y1;
        benachrichtige();
    }
    /**
     * Gibt den Winkel zwischen Kranschiene und x-Achse zurück
     * @return double
     */
    public double getBaseLineWinkel(){
         return this.getDegree(x1-x0,y1-y0);
    }
    /**
     * Gibt den Winkel des Auslegers zurück
     * @return Auslegerwinkel
     */
    public double getAuslegerwinkel() {
        return auslegerwinkel;
    }

    /**
     * Setzt den neuen Auslegerwinkel
     * @param auslegerwinkel neuer Auslegerwinkel
     */
    public void setAuslegerwinkel(double auslegerwinkel) {
        this.auslegerwinkel = auslegerwinkel;
        benachrichtige();
    }

    /**
     * Gibt die Breite des Kranfußes zurück
     * @return Fußbreite
     */
    public double getbaseBreite() {
        return baseBreite;
    }

    /**
     * Setzt die neue Fußbreite
     * @param breite neue Fußbreite
     */
    public void setbaseBreite(double breite) {
        this.baseBreite = breite;
        benachrichtige();
    }

    
     /**
      * Gibt die aktuelle Hakenhöhe zurück
     * @return Hakenhöhe
     */
    public double getHakenHohe() {
        return hakenHohe;
    }
    /**
     * Setzt die neue Hakenhöhe
     * @param hakenHohe neue Hakenhöhe
     */
    public void setHakenHohe(double hakenHohe) {
        this.hakenHohe = hakenHohe;
    }
    /**
     * Gibt die aktuelle Länge des Auslegers zurück
     * @return Auslegerlänge
     */
    public double getAusLegerlaenge() {
        return ausLegerlaenge;
    }


    /**
     * Setzt die neue Auslegerlänge
     * @param ausLegerlaenge neue Auslegerlänge
     */
    public void setAusLegerlaenge(double ausLegerlaenge) {
        this.ausLegerlaenge = ausLegerlaenge;
        benachrichtige();
    }

    /**
     *  Dreht den Kranausleger mit der akteullen Drehgeschwindigkeit
     *  <p>Postive Winkel dreht den Ausleger entgegengesetzt des Uhrzeigers
     * @param winkel  Drehwinkel (Grad)
     * @throws Exception
     */
    public synchronized void drehen(double winkel) throws Exception {
        
            if (this.drehenGeschwindigkeit <= 0)
                throw new Exception ("Fehler beim Drehen des Auslegers : drehenGeschwindigkeit <= 0");         
           
            double  s=(Math.abs(winkel)*Math.PI/180)/this.drehenGeschwindigkeit;
            final double s1 = s*1000;
            final double winkel1=winkel;
            final double zeitFaktor= this.getZeitFaktor();
 
                    krandrehen=true;
                    double fahreZeit=0;
                    double startZeit = System.currentTimeMillis();
                    double lastZeit=startZeit;
                    double deltaS;
                    double auslegerwinkel0=auslegerwinkel;
                    double aktuellezeitFaktor=zeitFaktor;                   
                    while (  s1 > fahreZeit ) { 
                        deltaS = aktuellezeitFaktor*(System.currentTimeMillis() - lastZeit);
                        if (aktuellezeitFaktor != getZeitFaktor()) {
                            aktuellezeitFaktor=getZeitFaktor();
                        }                                            
                         lastZeit=System.currentTimeMillis();
                         fahreZeit=fahreZeit+deltaS;
                         auslegerwinkel=auslegerwinkel+winkel1*(deltaS/(double)s1)  ;                        
                         benachrichtige();              
                    }                               
                    krandrehen=false;
                    auslegerwinkel=auslegerwinkel0+winkel1;
                    katzeXLocation=x+ausLegerlaenge*Math.cos(auslegerwinkel*Math.PI/180)*katzeLocation;
                    katzeYLocation=y+ausLegerlaenge*Math.sin(auslegerwinkel*Math.PI/180)*katzeLocation;     
                    benachrichtige();                   
        }
    
        /**
         *  Diese Methode bewegt den Kran, dreht den Kranausleger und bewegt die Katze gleichzeitig von der akteullen Lage zur (katzeX,katzeY) Koordinate.
         *  <p> Der Kran bewegt sich nur, wenn die neue Lage (katzeX,katzeY) nicht erreichbar vom Ausgangspunkt ist.
         *  <p> Wenn der Kran ohne Last ist, bewegt sich der Kran auf der Schiene bis zu einem Punkt, wo die neue Lage erreichbar ist
         *  <p> Wenn der Kran beladen ist, bewegt sich der Kran nur bis zu einer erreichbaren Stelle
         * @param katzeX    x-Koordinate der neuen Lage
         * @param katzeY    y-Hoordinate der neuen Lage
         * @throws Exception
         */
        public void setKatzeXYLocation(double katzeX ,double katzeY) throws Exception {         
            double neuKatzeLocation;
            double xBase,yBase;
            double newBaseLocation;
            double drehenAngle;
            double abstand;
            double d,xx,yy;         
            double A = katzeX - x0;
            double B = katzeY - y0;
            double C = x1 - x0;
            double D = y1 - y0;
            double dot = A * C + B * D;
            double len_sq = C * C + D * D;
            double param = dot / len_sq;            
            xBase=x;
            yBase=y;
            double kranBaseAngel=getDegree((x1-x0),(y1-y0));    
            if(param < 0)
            {
                xx = x0;
                yy = y0;
            }
            else if(param > 1)
            {
                xx = x1;
                yy = y1;
            }
            else
            {
                xx = x0 + param * C;
                yy = y0 + param * D;
            }
            d=Math.sqrt(Math.pow(xx-katzeX, 2)+Math.pow(yy-katzeY, 2));     

            if (d > this.ausLegerlaenge )
                throw new Exception("ungültiger neuer Ort der Katze");
            if (this.aktuellLast > this.maxTragfaehigkeit)
                throw new Exception("Die aktuellLast > maxTragfaehigkeit");         
            
            neuKatzeLocation=Math.sqrt( Math.pow((katzeY-y), 2)+Math.pow((katzeX-x), 2))/this.ausLegerlaenge;   
            
            double baseOffset=0;
            if ( (neuKatzeLocation > 1  || neuKatzeLocation <= 0 ) && this.aktuellLast > 0) {
                double xr=0;
                if(this.aktuellLast > this.minTragfaehigkeit )
                  {xr=this.ausLegerlaenge*((this.aktuellLast-this.minTragfaehigkeit)/(this.maxTragfaehigkeit-this.minTragfaehigkeit));}
                
                neuKatzeLocation=(this.ausLegerlaenge-xr)/this.ausLegerlaenge;
                
                double Base_katze_dis=Math.sqrt(Math.pow(katzeX-xx,2)+Math.pow(katzeY-yy,2));
                baseOffset=Math.sqrt(Math.pow(this.ausLegerlaenge-xr,2)-Math.pow(Base_katze_dis,2));                
                
                if (x > xx) {
                    xBase=xx+baseOffset*Math.cos(Math.toRadians(kranBaseAngel));
                    yBase=yy+baseOffset*Math.sin(Math.toRadians(kranBaseAngel));    
                }
                else {
                    xBase=xx-baseOffset*Math.cos(Math.toRadians(kranBaseAngel));
                    yBase=yy-baseOffset*Math.sin(Math.toRadians(kranBaseAngel));
                }
                
                newBaseLocation=Math.sqrt(Math.pow(xBase-x0, 2)+Math.pow(yBase-y0, 2))/Math.sqrt(Math.pow(x1-x0, 2)+Math.pow(y1-y0, 2));
                abstand=(newBaseLocation-baseLocation)*Math.sqrt(Math.pow(x1-x0, 2)+Math.pow(y1-y0, 2));
                System.out.println("Kran Umzug :" + abstand);
                this.kranUmzug(abstand);
            }   
            
                    
            
            if ( (neuKatzeLocation > 1  || neuKatzeLocation <= 0 ) ){
                neuKatzeLocation=Math.sqrt( Math.pow((katzeY-yy), 2)+Math.pow((katzeX-xx), 2))/this.ausLegerlaenge;
                if (AktuellTragfaehigkeit(neuKatzeLocation) < this.aktuellLast)
                    throw new Exception ("Die Tragfähigkeit für den neuen Ort der Katze ist geringer als die aktuelle Last ");
                
                newBaseLocation=Math.sqrt(Math.pow(xx-x0, 2)+Math.pow(yy-y0, 2))/Math.sqrt(Math.pow(x1-x0, 2)+Math.pow(y1-y0, 2));
                abstand=(newBaseLocation-baseLocation)*Math.sqrt(Math.pow(x1-x0, 2)+Math.pow(y1-y0, 2));
                this.kranUmzug(abstand);
                xBase=xx;
                yBase=yy;
            }       
            
            if (AktuellTragfaehigkeit(neuKatzeLocation) < this.aktuellLast)
                throw new Exception ("Die Tragfähigkeit für den neuen Ort der Katze ist geringer als die aktuelle Last ");                        

            abstand=(neuKatzeLocation-this.katzeLocation)*this.ausLegerlaenge;
            katzeXLocation=x+ausLegerlaenge*Math.cos(auslegerwinkel*Math.PI/180)*katzeLocation;
            katzeYLocation=y+ausLegerlaenge*Math.sin(auslegerwinkel*Math.PI/180)*katzeLocation;         
            double alpha=getDegree((katzeX-xBase),(katzeY-yBase));          
            drehenAngle=alpha-auslegerwinkel;
            if (alpha-auslegerwinkel > 180)
                drehenAngle=-(360 -(alpha-auslegerwinkel)); 
            
            try {
                this.drehen(drehenAngle);           
                this.katzeUmzug(abstand);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            benachrichtige();
        }

        /**
         *  Bewegt den Kran vor- und zurück auf der Kranschiene
         * @param Abstand
         * @throws Exception
         */
         public void kranUmzug(double abstand) throws Exception{            
             
                baseBewegenLaenge=Math.sqrt( Math.pow((y1-y0), 2)+Math.pow((x1-x0), 2));
                 if (this.baseGeschwindigkeit <= 0 && baseBewegenLaenge > 0 )
                        throw new Exception ("kranUmzug Fehler : baseGeschwindigkeit <= 0");
                final   double  finalKranocation=((baseLocation*baseBewegenLaenge)+abstand)/baseBewegenLaenge ;         
                
                if (baseBewegenLaenge== 0)
                    throw new Exception ("Dieser Kran kann nicht bewegt werden, setzen Sie bitte (x0,y0) (x1,y1) und baseLocation.");
                if (finalKranocation > 1 || finalKranocation <0 )
                    throw new Exception ("ungültiger Kranumzug");
                if (this.baseGeschwindigkeit <= 0)
                    throw new Exception ("Kranumzug Fehler : kranGeschwindigkeit <= 0 ");
                double  s=Math.abs(abstand)/this.baseGeschwindigkeit;
                final double s1 = s*1000;           
                final double abstand1=abstand;
                final double zeitFaktor=this.getZeitFaktor();
   
                        basebewegt=true;
                        double umzugZeit=0;
                        double startZeit = System.currentTimeMillis();
                        double lastZeit=startZeit;
                        double deltaS;
                        double aktuellezeitFaktor=zeitFaktor;
                        while (  s1 > umzugZeit ) {                      
                                deltaS = aktuellezeitFaktor*(System.currentTimeMillis() - lastZeit);
                                lastZeit=System.currentTimeMillis();
                                if (aktuellezeitFaktor != getZeitFaktor()) {
                                    deltaS=getZeitFaktor()/aktuellezeitFaktor;
                                    umzugZeit=umzugZeit*(getZeitFaktor()/aktuellezeitFaktor);
                                    aktuellezeitFaktor=getZeitFaktor();
                                }                           
                             umzugZeit=umzugZeit+deltaS;                                 
                             baseLocation=baseLocation +(abstand1/baseBewegenLaenge)*(deltaS/(double)s1);   
                             if(baseLocation <1 && baseLocation > 0)
                                 setBaseLocation(baseLocation);                         
                        } 
                        baseLocation=finalKranocation ;
                        x=x0+baseLocation*(x1-x0);
                        y=y0+baseLocation*(y1-y0);  
                        benachrichtige();   
                        basebewegt=false;  
         }
                
    /**
     *  Bewegt die Katze vor- und zuruck auf dem Kranausleger, wenn der Abstand positiv ist bewgt sich die Katze in Richtung Auslegerende
     *  und wenn der Abstand negativ ist wird di Katze Richtung Fuß bewegt.
     * @param Abstand  
     * @throws Exception
     */
     public void katzeUmzug(double abstand) throws Exception{
         
            final   double  finalKatzeLocation=((katzeLocation*ausLegerlaenge)+abstand)/ausLegerlaenge ;            
            if (finalKatzeLocation > 1 || finalKatzeLocation <0 )
                throw new Exception ("ungültiger Katzenumzug");
            if (this.AktuellTragfaehigkeit(finalKatzeLocation) < this.aktuellLast)
                throw new Exception ("ungültiger Katzenumzug : die Tragfaehigkeit ist geringer als die aktuelle Last ");
            if (this.katzeGeschwindigkeit <= 0)
                throw new Exception ("Katzenumzug Fehler : katzeGeschwindigkeit <= 0 ");
            double  s=Math.abs(abstand)/this.katzeGeschwindigkeit;
            final double s1 = s*1000;           
            final double abstand1=abstand;
            final double zeitFaktor=this.getZeitFaktor();  
                    katzebewegt=true;
                    double umzugZeit=0;
                    double startZeit = System.currentTimeMillis();
                    double lastZeit=startZeit;
                    double deltaS;      
                    double aktuellezeitFaktor=zeitFaktor;
                    while (  s1 > umzugZeit ) {                          
                         deltaS = aktuellezeitFaktor*(System.currentTimeMillis() - lastZeit);
                            if (aktuellezeitFaktor != getZeitFaktor()) {
                                deltaS=getZeitFaktor()/aktuellezeitFaktor;
                                umzugZeit=umzugZeit*(getZeitFaktor()/aktuellezeitFaktor);
                                aktuellezeitFaktor=getZeitFaktor();
                            }   
                            
                         lastZeit=System.currentTimeMillis();
                         umzugZeit=umzugZeit+deltaS;
                         setKatzeLocation(katzeLocation+(abstand1/ausLegerlaenge)*(deltaS/(double)s1))  ;                        
                        benachrichtige();               
                    } 
                    setKatzeLocation(finalKatzeLocation) ;
                    benachrichtige();       
                    katzebewegt=false;                     
     }
/**
 *  Hebt oder senkt den Kranhaken, Heben bei positivem und Senken bei negativem Abstand
 * @param Abstand  
 */
     public void hakenUmzug(double abstand) throws Exception{
         
         if (kranHohe ==0 )
                throw new Exception ("Kranhohe = null");
         final  double  finalHakeneLocation=(this.hakenHohe+abstand)/this.kranHohe ;            
            if (finalHakeneLocation > 1 || finalHakeneLocation <0 )
                throw new Exception ("ungültige hakenUmzug");
            if (this.hakenGeschwindigkeit <= 0)
                throw new Exception ("hakenUmzug Fehler : hakengeschwindigkeit <= 0 ");
            double  s=Math.abs(abstand)/this.katzeGeschwindigkeit;
            final double s1 = s*1000;           
            final double abstand1=abstand;
            final double zeitFaktor=this.getZeitFaktor();
                    hakenbewegt=true;
                    double umzugZeit=0;
                    double startZeit = System.currentTimeMillis();
                    double lastZeit=startZeit;
                    double deltaS;                  
                    while (  s1 > umzugZeit ) {
                         deltaS =zeitFaktor* (System.currentTimeMillis() - lastZeit);
                         lastZeit=System.currentTimeMillis();
                         umzugZeit=umzugZeit+deltaS;
                         setKatzeLocation(hakenHohe+(abstand1/kranHohe)*(deltaS/(double)s1))    ;                        
                        benachrichtige();               
                    } 
                    setKatzeLocation(finalHakeneLocation) ;
                    benachrichtige();       
                    hakenbewegt=false;                    
     }
     /**
      * Errechnet die erforderliche Zeit um die Katze zu bewegen von der akteullen Lage zur (newX , newY)-Lage
      * <p>Wenn die Bewegung nicht möglich ist, wird -1 zurückgegeben
      * @param newX
      * @param newY
      * @return Erforderliche Zeit oder -1
      */
     public double fordertZeit(double newX ,double newY) {
        double fordert_drehen , fordert_umzug;
        double neuKatzeLocation;
        double drehenAngle=0;
        neuKatzeLocation=Math.sqrt( Math.pow((newY-y), 2)+Math.pow((newX-x), 2))/this.ausLegerlaenge;
        if (neuKatzeLocation > 1  || neuKatzeLocation <= 0 )
            return -1;      
        if (AktuellTragfaehigkeit(neuKatzeLocation) < this.aktuellLast)
            return -1;      
        if (drehenGeschwindigkeit <= 0 || katzeGeschwindigkeit <= 0 )
            return -1;
        
        double alpha= 180*Math.atan((katzeYLocation-newY)/(katzeXLocation-newY))/Math.PI;
        if (newX< x && newY > y)    alpha=alpha+180;
        if (newX< x && newY < y)    alpha=alpha+180;
        if (newX > x && newY < y)   alpha=alpha-90; 
        drehenAngle=alpha-auslegerwinkel;
        if (alpha-auslegerwinkel > 180)
            drehenAngle=-(360 -(alpha-auslegerwinkel));         
        fordert_drehen=(Math.abs(drehenAngle)*Math.PI/180)/this.drehenGeschwindigkeit;  
        fordert_umzug=Math.abs(neuKatzeLocation-katzeLocation)*this.ausLegerlaenge/this.katzeGeschwindigkeit;
        if (fordert_drehen > fordert_umzug)
            return fordert_drehen;
        else
            return fordert_umzug;        
     }
     
     
    /**
     *  Gibt die Lage des Kranfußes auf den Schienen relativ zu deren Länge zurück,
     *  <br>wenn der Kran steht auf (x0,y0) --> 0 
     *  <br>wenn der Kran steht auf (x1,y1) --> 1 
     * @return the baseLocation
     */
    public double getBaseLocation() {
        return Math.sqrt(Math.pow(x-x0, 2)+Math.pow(y-y0, 2))/Math.sqrt(Math.pow(x1-x0, 2)+Math.pow(y1-y0, 2));
    }

    /**
     * Setzt die neue Kranfußlage auf den Kranschienen relativ zu der Schienenlänge.
     * @param baseLocation  die neue Kranfußlage als Ratio
     */
    public void setBaseLocation(double baseLocation) {
        if (baseLocation < 0 || baseLocation > 1)
            try {
                throw  new Exception ("ungültige baseLocation");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        this.x=x0+baseLocation*(x1-x0);
        this.y=y0+baseLocation*(y1-y0);
        benachrichtige();   
        this.baseLocation = baseLocation;
    }

    /**
     *  Gibt die aktuelle Lage der Katze auf dem Ausleger relativ zur Auslegerlänge zurück.
     *  @return Lage der Katze
     */
    public double getKatzeLocation() {
        return katzeLocation;
    }

    /**
     * Setzt die neue Lage der Katze auf dem Ausleger relativ zur Auslegerlänge
     * @param katzeLocation neue Lage der Katze
     */
    public void setKatzeLocation(double katzeLocation) {
        this.katzeXLocation=x+ausLegerlaenge*Math.cos(auslegerwinkel*Math.PI/180)*katzeLocation;
        this.katzeYLocation=y+ausLegerlaenge*Math.sin(auslegerwinkel*Math.PI/180)*katzeLocation;    
        this.katzeLocation = katzeLocation;
        benachrichtige();   
    }
    
    /**
     *  Setzt die neue Hakenhöhe relativ zur Kranhöhe
     * @param  Hakenhöhe 
     */
    public void setHakenLocation(double hakenhoehe) {
        this.hakenHohe=hakenhoehe*this.kranHohe;
        benachrichtige();       
    }   

    /**
     *  Gibt die aktuelle Hakenhöhe relativ zur Kranhöhe zurück
     *  @return Hakenhöhe
     */
    public double getHakenLocation() {
        return hakenHohe/kranHohe;
    }   
    
    /**
     * Gibt die aktuelle Kranhöhe zurück
     * @return Kranhöhe
     */
    public double getKranHohe() {
        return kranHohe;
    }
    /**
     * Setzt die neue Kranhöhe
     * @param neue Kranhöhe
     */
    public void setKranHohe(double kranHohe) {
        this.kranHohe = kranHohe;
    }
    /**
     * Gibt die x-Koordinate der Katze zurück
     * @return x-Koordinate der Katze
     */
    public double getKatzeXLocation() {     
    return x+ausLegerlaenge*Math.cos(auslegerwinkel*Math.PI/180)*katzeLocation;
    }


    /**
     * Setzt die neue x-Koordinate der Katze auf dem Ausleger
     * @param katzeXLocation neue x-Koordinate
     */
    public void setKatzeXLocation(double katzeXLocation) {
        this.katzeXLocation = katzeXLocation;
        this.benachrichtige();
    }

    /**
     * Gibt die aktuelle y-Koordinate der Katze auf dem Ausleger zurück
     * @return y-Koordinate
     */
    public double getKatzeYLocation() {
        return y+ausLegerlaenge*Math.sin(auslegerwinkel*Math.PI/180)*katzeLocation;
    }


    /**
     * Setzt die neue y-Koordinate der Katze auf dem Ausleger
     * @param katzeYLocation neue y-Koordinate
     */
    public void setKatzeYLocation(double katzeYLocation) {
        this.katzeYLocation = katzeYLocation;
        this.benachrichtige();
        
    }

    /**
     * Gibt die aktuelle Geschwindigkeit des Kranes auf der Schiene zurück
     * @return baseGeschwindigkeit
     */
    public double getBaseGeschwindigkeit() {
        return baseGeschwindigkeit;
    }

    /**
     * Setzt die neue Geschwindigkeit des Kranes auf der Schiene
     * @param baseGeschwindigkeit Geschwindigkeit
     */
    public void setBaseGeschwindigkeit(double baseGeschwindigkeit) {
        this.baseGeschwindigkeit = baseGeschwindigkeit;
    }

    /**
     * Gibt die Drehgeschwindigkeit des Auslegers zurück
     * @return Drehgeschwindigkeit 
     */
    public double getDrehenGeschwindigkeit() {
        return drehenGeschwindigkeit;
    }


    /**
     * Setzt die neue Drehgeschwindigkeit
     * @param Drehgeschwindigkeit (in Radiant/Sekunde)
     */
    public void setDrehenGeschwindigkeit(double drehenGeschwindigkeit) {
        this.drehenGeschwindigkeit = drehenGeschwindigkeit;
    }


    /**
     * Gibt die aktuelle Geschwindigkeit der Katze zurück
     * @return Geschwindigkeit der Katze
     */
    public double getKatzeGeschwindigkeit() {
        return katzeGeschwindigkeit;
    }


    /**
     * Setzt die neue Geschwindigkeit der Katze
     * @param katzeGeschwindigkeit Geschwindigkeit der Katze
     */
    public void setKatzeGeschwindigkeit(double katzeGeschwindigkeit) {
        this.katzeGeschwindigkeit = katzeGeschwindigkeit;
    }


    /**
     * Gitb die aktuelle Geschwindigkeit des Hakens zurück
     * @return Hakengeschwindigkeit
     */
    public double gethakenGeschwindigkeit() {
        return hakenGeschwindigkeit;
    }
    /**
     * Setzt die neue Geschwindigkeit des Hakens
     * @param hakenGeschwindigkeit Geschwindigkeit des Hakens
     */
    public void sethakenGeschwindigkeit(double hakenGeschwindigkeit) {
        this.hakenGeschwindigkeit = hakenGeschwindigkeit;
    }
    /**
     * Gibt die maximale Tragfähigkeit der Katze am Kranfuß zurück.
     * @return the maxTragfaehigkeit
     */
    public double getMaxTragfaehigkeit() {
        return maxTragfaehigkeit;
    }


    /**
     * Setzt die neue Tragfähigkeit der Katze am Kranfuß
     * @param maxTragfaehigkeit the maxTragfaehigkeit to set
     */
    public void setMaxTragfaehigkeit(double maxTragfaehigkeit) {
        this.maxTragfaehigkeit = maxTragfaehigkeit;
    }

    /**
     * Gibt die minimale Tragfähigkeit der Katze am Ende des Auslegers zurück
     * @return the minTragfaehigkeit
     */
    public double getMinTragfaehigkeit() {
        return minTragfaehigkeit;
    }

    /**
     * Setzt die neue minimale Tragfähgkeit der Katze am Ende des Auslegers
     * @param minTragfaehigkeit
     */
    public void setMinTragfaehigkeit(double minTragfaehigkeit) {
        this.minTragfaehigkeit = minTragfaehigkeit;
    }


    /**
     * Gibt die aktuelle Tragfähgkeit abhängig von der Lage der Katze zurück
     * @return the aktuellTragfaehigkeit
     */
    public double getAktuellTragfaehigkeit() {
        AktuellTragfaehigkeit();
        return this.aktuellTragfaehigkeit;
    }

    /**
     * Berechnet die aktuelle Maximallast, die der Kran heben kann für die aktuelle Lage der Katze auf dem Ausleger
     */
    public void AktuellTragfaehigkeit(){
        this.aktuellTragfaehigkeit= this.maxTragfaehigkeit -(this.katzeLocation/this.ausLegerlaenge)*(this.maxTragfaehigkeit-this.minTragfaehigkeit);
    }
        
    /**
     * Gibt die maximale Tragfähigkeit für eine bestimmte Lage der Katze zurück
     * @param thisKatzeLocation : Katzelage auf dem Ausleger als Ratio
     * @return double
     */
    public double AktuellTragfaehigkeit(double thisKatzeLocation){
        return this.aktuellTragfaehigkeit= this.maxTragfaehigkeit -(thisKatzeLocation/this.ausLegerlaenge)*(this.maxTragfaehigkeit-this.minTragfaehigkeit);
    }
    
    /**
     * Gibt das aktuelle Gewicht der Last zurück
     * @return aktuelle Last
     */
    public double getAktuellLast() {
        return aktuellLast;
    }


    /**
     * Setzt das neue Gewicht der Last
     * @param aktuellLast aktuelle Last
     */
    public void setAktuellLast(double aktuellLast) {
        this.aktuellLast = aktuellLast;
    }
    /**
     * Belädt den Kran mit einem Material
     * @param Material 
     *
     */
    public void beladen(Material material){
        //while (   krandrehen==true || katzebewegt==true || basebewegt==true){};
        materialListe.add(material);     
        this.benachrichtige();
    }
    
    /**
     * Entlädt ein Material vom Kran
     * @param Material
     * @param menge
     */
    public void entladen(Material material){
        //while (   krandrehen==true || katzebewegt==true || basebewegt==true){};
        materialListe.remove(material);
        this.benachrichtige();
    
    }

    /**
     * Gibt die aktuelle Liste der Materialien zurück
     * @return Liste mit Materialien
     */
    public ArrayList<Material> getMaterialListe() {
        return materialListe;
    }

    /**
     * Belädt den Kran mit einer Liste von Materialien
     * @param neue Liste mit Materialien
     */
    public void setMaterialListe(ArrayList<Material> materialListe) {
        this.materialListe = materialListe;
    }

    
    /**
     * Gibt zurück, ob der Kran sich dreht
     * @return Drehzustand
     */
    public boolean isKrandrehen() {
        return krandrehen;
    }

    /**
     * Setzt den Zustand des Krandrehens
     * @param krandrehen Zustand des Krandrehens
     */
    public void setKrandrehen(boolean krandrehen) {
        this.krandrehen = krandrehen;
    }

    /**
     * Gibt zurück, ob sich die Katze bewegt
     * @return Zustand der Bewegung der Katze
     */
    public boolean isKatzebewegt() {
        return katzebewegt;
    }

    /**
     * Setzt einen neuen Zustand der Bewegung der Katze
     * @param katzebewegt neuer Zustand
     */
    public void setKatzebewegt(boolean katzebewegt) {
        this.katzebewegt = katzebewegt;
    }

    /**
     * Gibt zurück, ob sie der Kranfuß bewegt
     * @return Zustand der Bewegung
     */
    public boolean isBasebewegt() {
        return basebewegt;
    }

    /**
     * Setzt den neuen Zustand der Fußbewegung
     * @param neuer Zustand der Fußbewegung
     */
    public void setBasebewegt(boolean basebewegt) {
        this.basebewegt = basebewegt;
    }

    /**
     * Gibt den aktuellen Krancode zurück
     * @return  kranCode String
     */
    public String getKranCode() {
        return KranCode;
    }

    /**
     * Setzt den neuen Krancode
     * @param kranCode String
     */
    public void setKranCode(String kranCode) {
        KranCode = kranCode;
    }
    
    /**
     * Gibt alle Eigenschaften des Kranes als formatierte Liste zurück
     * @return String
     */
    public String getEigenschaften(){
        NumberFormat formatter = new DecimalFormat("0.00"); 
        String s = "<html>Class = " + this.getClass().getSimpleName() +
        "<br>Kran code = " +this.getKranCode()+ 
        "<br>x = " +formatter.format(this.getX())+
        ",  y = "    + formatter.format(this.getY()) +
        "<br>Auslegerlänge = "   + formatter.format(this.getAusLegerlaenge()) +
        "<br>Auslegerwinkel = "   + formatter.format(this.getAuslegerwinkel()) +
        "<br>Katzelocation (x,y) =( "   + formatter.format(this.getKatzeXLocation()) + ","+ formatter.format(this.getKatzeYLocation()) + ")" +
        "<br>Material = "   + this.getMaterialListe() ; 
        s =s + "<br>" + this.getNotiz();
        s=s +"<hr><table><tr><td>Drehengeschwindigkeit</td><td>" + this.getDrehenGeschwindigkeit() +"</td></tr>";
        s=s+ "<tr><td>Katzegeschwindigkeit</td><td>" + this.getKatzeGeschwindigkeit() +"</td></tr>";
        s=s+ "<tr><td>Basegeschwindigkeit</td><td>" +  this.getBaseGeschwindigkeit() +"</td></tr>";
        s=s+"</table>";
    return s;   
    }
    
    
    
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
    
    protected void finalize()
    {
        this.setVisible(false);
    }
}
