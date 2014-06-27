package Beleg2_SS2014;
import Baustelle.physical.*;
import java.util.*;

public class MySilo extends Silo implements Subject, Observer
{
    private Punkt meinePosition;   
    private List<Observer> observer = new ArrayList<Observer>();
    
    public MySilo()
    {
         setRandomPosition();
    }
    
    
    
    public Punkt getPosition() {
        return meinePosition;    
    } 

    /*
     * Helperfunction
     * Calculates the difference to the border of the field
     */
    private int differenceBorder(int value) {
        int diff = 0;

        if(value >= 0 && value <= 20) {
            diff = 20 - value;
            value = value + diff;
        }

        if(value >= 430 && value <= 450) {
            diff = 450 - value;
            value = value - diff;
        }

        return value;
    }
    
    private void setRandomPosition() {
        // TODO A4 mittels Random zufällige x und y Position (Variable randx,randy) mittels Random ermitteln
        // BEGIN

        Random randomgenerator = new Random();

        int randx = randomgenerator.nextInt(450);
        int randy = randomgenerator.nextInt(450);
        randx = differenceBorder(randx);
        randy = differenceBorder(randy);

        // TODO END
        this.meinePosition = new Punkt(randx,randy);
        this.setDurchmesser(40);
        this.setX(randx);
        this.setY(randy);
        this.setVisible(true);    
        
    }
    
    public void registerObserver(Observer o) {
        // TODO A3
        // Observer zur Liste observer hinzufügen. 
        // Auf Duplikate prüfen
        if (!observer.contains(o)) {
            observer.add(o);   
        }
    }    
    
    public void removeObserver(Observer o) {
        // TODO A3 
        // Observer aus Liste observer entfernen 
        observer.remove(o);
    }    
    
    public void sendPosition() {
       
        for(Observer o : observer) {
            // TODO A3
            // Alle Observer in der Liste informieren (Aufruf der update Methode)
            o.update(this);
        }    
    }     
    
    // Prüfen ob Silo Kollission mit einem anderen Silo hat
    public void update(MySilo s) {
        // System.out.println("Kollision prüfen"); 
        if(checkCollision(s)) {
            System.out.println("Kollision dieses Silos mit einem anderen Silo");
            try {
                Thread.sleep(3000);
            }
            catch(Exception e) { }    
            // setVisible(false);
            System.out.println("Neue Position setzen und observer ueber neue Position informieren (erneute Kollisionspruefung)");
            setRandomPosition();
            sendPosition();
            
        }    
        
        
    }
    // Wenn die Distanz zwischen den Mittelpunkten zweier Kreise kleiner 
    // ist als die Summe ihrer Radien, so liegt eine Kollision vor. 
    private boolean checkCollision(MySilo s) {
        double siloDurchmesser = this.getDurchmesser();
        double radius1 = siloDurchmesser/2;
        double radius2 = s.getDurchmesser()/2;
        double distanz = Math.sqrt(Math.pow(this.meinePosition.getX()-s.getPosition().getX(),2)+Math.pow(this.meinePosition.getY()-s.getPosition().getY(),2));
        // TODO A5 BEGIN
        // Wenn die Distanz kleiner ist als die Summe der beiden Radien soll true zurückgegeben 
        // werden
        if (distanz < radius1+radius2) {
            return true;
        }
        // TODO A5 END
        return false;
        
    }     
   
}
