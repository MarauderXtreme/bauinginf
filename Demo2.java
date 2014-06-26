import Baustelle.physical.*;
import Baustelle.*;

/**
 * Write a description of class Demo2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Demo2
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class Demo2
     */
    public Demo2()
    {
       
    }

    
    
    /**
     * Erzeugt eine Baustrasse
     */
    public static Baustrasse createConstructionRoad() {
        Baustrasse str=new Baustrasse(10,10,50,10,16);      
        str.setVisible(true);       
        str.AddSegment(50, 90);
        str.AddSegment(150, 0);
        str.AddSegment(110, 90);
        str.AddSegment(190, 180);
        str.AddSegment(160, -90);
        str.AddSegment(16, 0);
        return str;
    }    
    
    /**
     * Erzeuge Baugrube 
     *
     */
    public static Baugrube createExcavationPit() {
        Baugrube grube=new Baugrube();
        grube.setX(50);
        grube.setY(70);
        grube.setBreite(80);
        grube.setLaenge(140);
        grube.setTiefe(3);
        grube.setAktuelleBreite(80);
        grube.setAktuelleLaenge(140);
        grube.setAktuelleTiefe(3);
        grube.setVisible(true);
        return grube;
        
    }    
    
    /**
     * Erzeugt eine Menge von Baucontainern
     */
    public static void createBuildingContainer() {
        Baucontainer baucont1=new Baucontainer(25,20,12,3);
        baucont1.setVisible(true);
        
        Baucontainer baucont2=new Baucontainer(25,40,12,3);
        baucont2.setVisible(true);
        
        Baucontainer baucont3=new Baucontainer(25,60,12,3);
        baucont3.setVisible(true);
        
        Baucontainer baucont4=new Baucontainer(25,80,12,3);
        baucont4.setVisible(true);
        
        Baucontainer baucont5=new Baucontainer(25,100,12,3);
        baucont5.setVisible(true);
        
    }    
    
    public static void main(String[] args) {
        //Neues Objekt Bauplatz erstellen
        Bauplatz bauplatz;
        //Zugriff auf den statisch verfügbaren Bauplatz einholen
        bauplatz = Bauplatz.einrichten();
        Baustrasse str = createConstructionRoad();
       
        
        Kran kran1 = new Kran("Kran1");
        kran1.setX(45);
        kran1.setY(75);
        kran1.setAusLegerlaenge(75);
        kran1.setDrehenGeschwindigkeit(0.1);
        kran1.setKatzeGeschwindigkeit(3);
        kran1.setbaseBreite(8);
        kran1.setVisible(true);
        
        Kran kran2 = new Kran();
        kran2.setKranCode("K2");        
        kran2.setX0(60);
        kran2.setX1(190);
        kran2.setY0(160);
        kran2.setY1(160);
        kran2.setBaseLocation(1);
        kran2.setAusLegerlaenge(75);
        kran2.setbaseBreite(8);
        kran2.setAuslegerwinkel(0);
        kran2.setDrehenGeschwindigkeit(0.3);
        kran2.setKatzeGeschwindigkeit(0.8);
        kran2.setBaseGeschwindigkeit(5);
        kran2.setVisible(true);
        
        createExcavationPit();
        
        
        Kipper kip1=new Kipper(8,0,0,6,12);
        kip1.setMaxVolumen(50);
        kip1.setGeschwindigkeit(40);
        kip1.setVisible(true);
        
        
       
        
        Bagger bgr=new Bagger(10,10);
        bgr.setKetten(true);
        bgr.setX(40);
        bgr.setY(140);
        bgr.setGrubenLeistung(1000);
        bgr.setVisible(true);
        bgr.setFullenLeistung(kip1.getMaxVolumen());
        
        createBuildingContainer();
        
        Silo silo1 = new Silo(30,50,3);
        silo1.setHoehe(7.5);
        silo1.setVolumen(25);
        silo1.setLeergewicht(2500);
        silo1.setVisible(true);
        silo1.setNotiz("<font color='#0000FF'>Silo für Zement");  
        
        
        Material mat1=new Material("Holz");
        mat1.setGewicht(200);
        mat1.setVolumen(0.5);
        mat1.setX(100);
        mat1.setY(100);
        mat1.setBreite(8);
        mat1.setLaenge(8);   
        mat1.setVisible(true);
        
        LKW lkw1=new LKW(15,12,0,4.8,12);
        lkw1.setGeschwindigkeit(50);
        lkw1.setVisible(true);
        
       
       
        
           System.out.println("Anzahl Gesamtvolumen vor Befüllung durch Bagger:"+kip1.getLadungGesamtVolumen());    
           
           try {
           bgr.kipperfullen(kip1);   
           lkw1.fahreAufStrasse(10,str,0);
           
           
           
           
        // Mögliche Aufgabe 2, Übung zu Schleifen - Kipper entladen mittels diverser Schleifen   
        // Aufgabe 2 Kipper mittels verschiedemer Schleifen beladen
        // Als Materialvorgabe Erdboden nutzen, da Bagger mit Erdboden befüllt
        // Teilaufgabe 1: Herausfinden was für Material (Erdboden) von Bagger mittels befullen beladen wird und
        // Teilaufgabe 2: Kipper mittels for Schleife entladen
        // Teilaufgabe 3: Kipper mittels While Schleife entladen
        // Im zweiten Semester kann auf diese Aufgabe zurückgegriffen werden im Zusammenhang mit Liste, da Liste was für 
        // Material geladen ist ebenfalls abgefragt werden kann
       // for(int i=0; i<kip1.getAktuellVolumen();i++) {
       //         kip1.entladen(new Material ("Erdboden"));
       //         System.out.println("Entlade"+kip1.getAktuellVolumen());       
       //  }
            
           // Nur gefüllt mit Erdboden, d.h. MaterialAnzahl 1
         //  System.out.println("Anzahl Material"+kip1.getMaterialAnzahl());
         //  System.out.println("Anzahl Gesamtvolumen:"+kip1.getLadungGesamtVolumen());    
         //  kip1.entladen(new Material("Erdboden"));
         //  System.out.println("Anzahl Gesamtvolumen nach Entladung:"+kip1.getLadungGesamtVolumen());    
                
        // Kranausleger mittels diverser Schleifen drehen    
          
        
        Material erdboden = new Material("Erdboden",10,1);
        // Gesamtvolumen Kipper 50, also Schleife 50 mal laufen lassen
        //for(int i=0; i<50;i++) {
         //   kip1.laden(erdboden);
         //   System.out.println("Belade. Akt. Volumen: "+kip1.getAktuellVolumen());
        // }    
        
            
            
           
           
            kran1.setKatzeXYLocation(100, 100);
            kran1.beladen(mat1);        
            kran1.setKatzeXYLocation(60, 120);    
            kran1.entladen(mat1);
            // Kran in Ausgangsposition drehen
            kran1.drehen(-kran1.getAuslegerwinkel());
            
            kran2.kranUmzug(-100);           
            kran2.setKatzeXYLocation(60, 120);
            kran2.beladen(mat1);
           }catch(Exception e) { System.out.println("Excep. beim Kipperfüllen"); }
        
       

           
        }    
        
       
            
}
