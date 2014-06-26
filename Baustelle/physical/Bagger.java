/**
 * 
 */
package Baustelle.physical;

import Baustelle.graphical.BaggerSymbol;





/**
 * @author Ali Ismail
 *  <br> Dieser Klasse repräsentiet ein Bagger.
 *  <br> Die Funktionen der Bagger sind:
 *   <br>Fahren (die Klasse ist geerbt von Fahrzueg Klasse)
 *   <br>Gruben ---> gruben (Baugruben grubObj) methode
 *   <br>Kipper fullen  ---> kipperfullen ( Kipper kipObj)
 *   <br>Drehen
 *   
 */
public class Bagger extends Fahrzeug {

    private boolean ketten;
    private boolean radSagen;
    private double schaufelVolume;
    private double schaufelLaenge;
    private double schaufelBreite;
    private double auslagerLaenge;
    private double schaufelLageRatio=1;
    private double drehenGeschwindigkeit;
    private double auslaegerWinkel;
    private double maxHebenLeistung;
    private boolean gruben;
    private boolean schaufeldrehen;
    private boolean kipperfullen;   
    private Runnable runnable;
    private double grubenLeistung ; // m3 per hour
    private double fullenLeistung;  
    private Baugrube grube;
    
    /**
     *  Konstructor
     */
    public Bagger(){        
        super( 0,  0 ,  0, 3.5,   7);
        this.radSagen=true;
        this.auslagerLaenge=this.getLaenge();
        this.schaufelBreite=0.4*this.getBreite();   
        this.schaufelLaenge=0.8*this.getBreite();       
        this.schaufelLageRatio=0.5;
    }

    
    public Bagger (double breite, double  laenge){
        super( 0,  0 ,  0, breite,   laenge);       
        this.radSagen=true;
        this.auslagerLaenge=laenge;
        this.schaufelBreite=0.4*breite; 
        this.schaufelLaenge=0.8*laenge;
        this.schaufelLageRatio=0.5;
    }
    
    public Bagger (double x, double y,double breite, double  laenge) {
        super( x,  y ,  0, breite,   laenge);       
        this.radSagen=true;
        this.auslagerLaenge=laenge;
        this.schaufelBreite=0.4*breite; 
        this.schaufelLaenge=0.8*laenge;
        this.schaufelLageRatio=0.5; 
    }
    
    
    
    public void setVisible(boolean visible) {       
        if (visible) {
            if (!isVisible()){              
                go = new BaggerSymbol();
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
     * @return the ketten
     */
    public boolean isKetten() {
        return ketten;
    }

    /**
     * @param ketten the ketten to set
     */
    public void setKetten(boolean ketten) {
        if (ketten==true)
              this.radSagen=false;
            else
               this.radSagen=true;
        this.ketten = ketten;
    }

    /**
     * @return the radSagen
     */
    public boolean isRadSagen() {
        return radSagen;
    }

    /**
     * @return the gruben
     */
    public boolean isGruben() {
        return gruben;
    }

    /**
     * @param gruben the gruben to set
     */
    public void setGruben(boolean gruben) {
        this.gruben = gruben;
    }

    /**
     * @param radSagen the radSagen to set
     */
    public void setRadSagen(boolean radSagen) {
        if (radSagen==true)
          this.ketten=false;
        else
           this.ketten=true;
        this.radSagen = radSagen;
    }

    /**
     * @return the schaufelVolume
     */
    public double getSchaufelVolume() {
        return schaufelVolume;
    }

    /**
     * @param schaufelVolume the schaufelVolume to set
     */
    public void setSchaufelVolume(double schaufelVolume) {
        this.schaufelVolume = schaufelVolume;
    }

    /**
     * @return the schaufelLaenge
     */
    public double getSchaufelLaenge() {
        return schaufelLaenge;
    }

    /**
     * @param schaufelLaenge the schaufelLaenge to set
     */
    public void setSchaufelLaenge(double schaufelLaenge) {
        this.schaufelLaenge = schaufelLaenge;
    }

    /**
     * @return the schaufelBreite
     */
    public double getSchaufelBreite() {
        return schaufelBreite;
    }

    /**
     * @param schaufelBreite the schaufelBreite to set
     */
    public void setSchaufelBreite(double schaufelBreite) {
        this.schaufelBreite = schaufelBreite;
    }

    /**
     * @return the auslagerLaenge
     */
    public double getAuslagerLaenge() {
        return auslagerLaenge;
    }

    /**
     * @param auslagerLaenge the auslagerLaenge to set
     */
    public void setAuslagerLaenge(double auslagerLaenge) {
        this.auslagerLaenge = auslagerLaenge;
    }

    /**
     * @return the auslaegerWinkel
     */
    public double getAuslaegerWinkel() {
        return auslaegerWinkel;
    }

    /**
     * @param auslaegerWinkel the auslaegerWinkel to set
     */
    public void setAuslaegerWinkel(double auslaegerWinkel) {
        this.auslaegerWinkel = auslaegerWinkel;
    }

    /**
     * @return the schaufelLageRatio
     */
    public double getSchaufelLageRatio() {
        return schaufelLageRatio;
    }

    /**
     * @param schaufelLageRatio the schaufelLageRatio to set
     */
    public void setSchaufelLageRatio(double schaufelLageRatio) {
        this.schaufelLageRatio = schaufelLageRatio;
    }
    
    /**
     * graben eine Ausgrabung (Baugrube objekt)
     * @param grube1 : Baugrube objekt
     * @throws InterruptedException
     */
    public synchronized void gruben(Baugrube grube1) throws InterruptedException{
        final double zeitFaktor=this.getZeitFaktor();       
        this.setX(grube1.getX()+ 0.5*getLaenge());
        this.setY(grube1.getY() + 0.5*getBreite());
        this.setWinkel(grube1.getWinkel());
        final Baugrube grub2=grube1;
        this.grube=grube1;
        final double sin=Math.sin(Math.toRadians(grube.getWinkel()));
        final double cos=Math.cos(Math.toRadians(grube.getWinkel()));       
        final double erforderlichGrubenzeit=((grube.getVolume()-grube.getAkteulleVolume())/ this.grubenLeistung)*3600;
        runnable = new Runnable() {         
            public  void run() {
                while (gruben== true) {};   
                gruben=true;    
                double grubenZeit=0;
                double startZeit = System.currentTimeMillis();
                double lastZeit=startZeit;
                double deltaS;
                int yFaktor=0;          
                double aktuellezeitFaktor=zeitFaktor;                           
                while ( erforderlichGrubenzeit > grubenZeit && gruben==true && grube.getAkteulleVolume() <= grube.getVolume() ) {
                    while (kipperfullen== true) {};
                    if (aktuellezeitFaktor != getZeitFaktor()) {
                        aktuellezeitFaktor=getZeitFaktor();
                    }
                     deltaS = aktuellezeitFaktor*(System.currentTimeMillis() - lastZeit);
                     lastZeit=System.currentTimeMillis();
                     grubenZeit=grubenZeit+deltaS;      
                     setX(getX()+0.3*aktuellezeitFaktor*cos);    
                     setY(getY()+ 0.3*aktuellezeitFaktor*sin);
                     
                     if (getX()+ getLaenge()*cos + getAuslagerLaenge()*cos > grub2.getX()+ grub2.getLaenge()*cos)
                     {
                         setX((grub2.getX()+ 0.5*aktuellezeitFaktor*getLaenge()*cos));
                         yFaktor=yFaktor+1;
                         setY((grub2.getY()+ getBreite()*yFaktor));
                         
                     }
                     if (getY()+ getBreite() > grub2.getY()+ grub2.getBreite())
                     {
                         setX(grub2.getX()+ 0.5*aktuellezeitFaktor*getLaenge()*cos);
                         yFaktor=1;
                         setY(grub2.getY()+ getBreite()*yFaktor);
                         
                     }      
                     grube.setAktuellesVolumen(grube.getAkteulleVolume()+(grubenLeistung*(deltaS/3600)));
                     grube.setAktuelleBreite(grube.getBreite()*(grube.getAkteulleVolume()/grube.getVolume()));
                     grube.setAktuelleLaenge(grube.getLaenge());                     
                     grube.benachrichtige();
                     benachrichtige();  
                     try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } 
                deltaS=erforderlichGrubenzeit- grubenZeit;                              
                benachrichtige();
                gruben=false;
            }
            };
        runnable.run(); 
        //execute(runnable);        
    }
    /**
     *  drehen die schaufel
     * @param winkel : degree
     * @param drehenGeschwindigkeit
     * @throws Exception
     */
    public synchronized void schaufeldrehen(double winkel,double drehenGeschwindigkeit) throws Exception {
        
        if (this.drehenGeschwindigkeit <= 0)
            throw new Exception ("drehen Fehler : drehenGeschwindigkeit <= 0");         
       
        double  s=(Math.abs(winkel)*Math.PI/180)/this.drehenGeschwindigkeit;
            final double s1 = s*1000;
        final double winkel1=winkel;
        final double zeitFaktor= this.getZeitFaktor();
        
        runnable = new Runnable() {
            public  void run() {
                while (schaufeldrehen== true ) {};  
                schaufeldrehen=true;
                double fahreZeit=0;
                double startZeit = System.currentTimeMillis();
                double lastZeit=startZeit;
                double deltaS;
                double auslegerwinkel0=auslaegerWinkel;
                double aktuellezeitFaktor=zeitFaktor;
                while (  s1 > fahreZeit ) {
                    if (aktuellezeitFaktor != getZeitFaktor()) {
                        aktuellezeitFaktor=getZeitFaktor();
                    }                   
                     deltaS = aktuellezeitFaktor*(System.currentTimeMillis() - lastZeit);
                     lastZeit=System.currentTimeMillis();
                     fahreZeit=fahreZeit+deltaS;
                     auslaegerWinkel=auslaegerWinkel+winkel1*(deltaS/(double)s1)    ;                        
                     benachrichtige();              
                }                               
                schaufeldrehen=false;
                auslaegerWinkel=auslegerwinkel0+winkel1;                
                benachrichtige();               
            }
        };
        runnable.run();
        //ex.execute(runnable); 
    }

    /**
     *  fullen einen Kipper
     * @param kipper1: Kipper Objekt
     * @throws InterruptedException
     */
    public synchronized void kipperfullen(final Kipper kipper1) throws InterruptedException{
        final double zeitFaktor=this.getZeitFaktor();       
        final double erforderlichGrubenzeit=((kipper1.getMaxVolumen())/ this.fullenLeistung)*3600;
        
        if (kipper1.getMaterialListe().size()==0)
            kipper1.getMaterialListe().add(new Material ("Erdboden"));
        
        runnable = new Runnable() {
            public  void run() {
                while (kipperfullen== true) {}; 
                kipperfullen=true;  
                double fullenZeit=0;
                double startZeit = System.currentTimeMillis();
                double lastZeit=startZeit;
                double deltaS;
                auslaegerWinkel=0;
                kipper1.setFahrt(false);
                kipper1.setX(getX());
                kipper1.setY(getY()-auslagerLaenge-schaufelBreite);
                kipper1.setWinkel(getWinkel()+90);
                
                
                double aktuellezeitFaktor=zeitFaktor;
                while (  erforderlichGrubenzeit > fullenZeit ) {
                    if (aktuellezeitFaktor != getZeitFaktor()) {
                        aktuellezeitFaktor=getZeitFaktor();
                    }
                     deltaS = aktuellezeitFaktor*(System.currentTimeMillis() - lastZeit);
                     lastZeit=System.currentTimeMillis();
                     fullenZeit=fullenZeit+deltaS;                   
                     auslaegerWinkel=auslaegerWinkel -10*aktuellezeitFaktor;
                     if (auslaegerWinkel < -90)
                         auslaegerWinkel=0;
                      
                     kipper1.setAktuellVolumen(kipper1.getAktuellVolumen()+fullenLeistung*fullenZeit);                   
                     kipper1.getMaterialListe().get(0).setVolumen(fullenLeistung*fullenZeit/3600);                   
                     //kipper1.getMaterialListe().get(0).setVolumen(6);
                     kipper1.benachrichtige();
                     benachrichtige();  
                     try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }               
                deltaS=erforderlichGrubenzeit- fullenZeit;
                kipper1.setAktuellVolumen(kipper1.getMaxVolumen());              
                kipper1.getMaterialListe().get(0).setVolumen(kipper1.getMaxVolumen());
                kipperfullen=false;
                auslaegerWinkel=0;
                schaufelLageRatio=0;                
                benachrichtige();               
            }
            };
            runnable.run();
            //ex.execute(runnable);     
    }
    /**
     * @return the grubenLeistung
     */
    public double getGrubenLeistung() {
        return grubenLeistung;
    }
    
    /**
     * @param grubenLeistung the grubenLeistung to set
     */
    public void setGrubenLeistung(double grubenLeistung) {
        this.grubenLeistung = grubenLeistung;
    }

    /**
     * @return the fullenLeistung
     */
    public double getFullenLeistung() {
        return fullenLeistung;
    }

    /**
     * @param fullenLeistung the fullenLeistung to set
     */
    public void setFullenLeistung(double fullenLeistung) {
        this.fullenLeistung = fullenLeistung;
    }

    /**
     * @return the maxHebenLeistung
     */
    public double getMaxHebenLeistung() {
        return maxHebenLeistung;
    }


    /**
     * @param maxHebenLeistung the maxHebenLeistung to set
     */
    public void setMaxHebenLeistung(double maxHebenLeistung) {
        this.maxHebenLeistung = maxHebenLeistung;
    }


    /**
     * @return the kipperfullen
     */
    public boolean isKipperfullen() {
        return kipperfullen;
    }

    /**
     * @param kipperfullen the kipperfullen to set
     */
    public void setKipperfullen(boolean kipperfullen) {
        this.kipperfullen = kipperfullen;
    }

    /**
     * @return the grube
     */
    public Baugrube getGrube() {
        return grube;
    }

    /**
     * @param grube the grube to set
     */
    public void setGrube(Baugrube grube) {
        this.grube = grube;
    }

    /**
     * @return the drehenGeschwindigkeit
     */
    public double getDrehenGeschwindigkeit() {
        return drehenGeschwindigkeit;
    }

    /**
     * @param drehenGeschwindigkeit the drehenGeschwindigkeit to set
     */
    public void setDrehenGeschwindigkeit(double drehenGeschwindigkeit) {
        this.drehenGeschwindigkeit = drehenGeschwindigkeit;
    }

    /**
     * @return the schaufeldrehen
     */
    public boolean isSchaufeldrehen() {
        return schaufeldrehen;
    }

    /**
     * @param schaufeldrehen the schaufeldrehen to set
     */
    public void setSchaufeldrehen(boolean schaufeldrehen) {
        this.schaufeldrehen = schaufeldrehen;
    }   
}
