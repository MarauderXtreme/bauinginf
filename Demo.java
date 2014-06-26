import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import Baustelle.Bauplatz;
import Baustelle.physical.Kipper;
import Baustelle.physical.Kran;
import Baustelle.physical.Material;
import Baustelle.physical.Kaninchen;
/**
 * Spielt eine kleine Demo mit den verfügbaren Bauobjekten ab
 * 
 * @author Clemens Gebhardt, Ulf Wagner
 * @version 2.2
 */
public class Demo
{

    /**
     * Konstruktor der Klasse Demo
     */
    public Demo()
    {   
    }

    /**
     * Die main-Methode; sie wird automatisch aufgerufen
     */
    public static void main(String [] args)  {
        Demo d = new Demo();
        try {
            d.Ablauf(); 
        } catch (Exception e) {};
    }
    
    
    /**
     * Hier findet der eigentliche Ablauf der Demo statt
     * 
     * @return     Nothing
     */
    public void Ablauf() throws Exception
    {
        //Neues Objekt Bauplatz erstellen
        Bauplatz bauplatz;
        //Zugriff auf den statisch verfügbaren Bauplatz einholen
        bauplatz = Bauplatz.einrichten();
        
        //Erstelle die Bauobjekte mit den zugehörigen Parametern für den Konstruktor
        
        //z.B.: Erstelle einen Kipper an Position (100,100) gedreht um den Winkel 0° mit der Breite 20 und der Länge 80
        Kipper kipper = new Kipper(100,100,0,20,80);
        Kran kran = new Kran(500,100,100,400);
        Material material = new Material("Holz",20,20);
        Kaninchen kaninchen = new Kaninchen(50,250);
        
        //Lege Eigenschaften des Materials fest
        material.setX(460);material.setY(350);
        material.setLaenge(30);material.setBreite(30);
        
        //Mache alle Objekte sichtbar
        kaninchen.setVisible(true);
        material.setVisible(true);
        kipper.setVisible(true);
        kran.setVisible(true);
        
        //Erstelle eine neue Skala auf dem Bauplatz
        bauplatz.SkalaEinfuegen();
        
        //Starte eine Schleife bei 0, laufend bis 100 und inkrementiere in jedem Vorgang um 1
        for (int i =0;i<=100;i++) {
            //Lasse den Kipper 2 Pixel vorwärts fahren
            kipper.vorwaerts(2);
            
            //Drehe den Ausleger um ein zusätzliches Grad
            kran.setAuslegerwinkel(kran.getAuslegerwinkel()+1);
            
            //Solange die y-Koordinate der Katze nicht höher als 350 Pixel liegt, wird die Katze um 0.01 Einheiten (relativ zur Auslegerlänge) zum Ende hin bewegt
            if (kran.getKatzeYLocation() < 350) kran.setKatzeLocation(kran.getKatzeLocation()+0.01);
            
            //Mache eine Unterbrechung von 10ms um die Animation sichtbar zu machen
            Thread.sleep(10);
        }
        
        //Erstelle eine ArrayList, die das Material auf dem Bauplatz enthalten soll
        ArrayList<Material> materialliste = new ArrayList<Material>();
        
        //In dieser Liste wird das Material abgelegt
        materialliste.add(material);
        
        //Füge die Materialliste im Kran ein
        kran.setMaterialListe(materialliste);
        
        //Mache das Material unsichtbar
        material.setVisible(false);
    }
}
