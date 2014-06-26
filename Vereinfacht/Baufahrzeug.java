package Vereinfacht;
import Baustelle.physical.Kipper;
import java.util.Random;

public class Baufahrzeug {
    Kipper baufahrzeug;
    Baufahrzeug(double x, double y, double winkel) {
        baufahrzeug = new Kipper(x,y,winkel,20,50);
        baufahrzeug.setVisible(true);
    }
    
    Baufahrzeug() {
        Random random = new Random();
        baufahrzeug = new Kipper(random.nextInt(200),random.nextInt(200),random.nextInt(360),20,50);
        baufahrzeug.setVisible(true);
    }
    
    public void feierabend() {
        baufahrzeug.setVisible(false);
    }
    
    public void drehen(double winkel) {
        baufahrzeug.setWinkel(baufahrzeug.getWinkel()+winkel);
    }
    
    public void fahre(int distanz, int geschwindigkeit) {
        int schritte = Math.abs(distanz/geschwindigkeit);
        for (int i=0;i<= (schritte);i++) {
            baufahrzeug.vorwaerts(geschwindigkeit);
            try {Thread.sleep(5);} catch (Exception e) {}
        }
    }
    
    public void fahre() throws Exception {
        this.fahre(400,7);
    }
    
    public void fahrerechtskurve(double distanz, int geschwindigkeit, double winkel) {
        double w = baufahrzeug.getWinkel();
        for (int i=0;i<= distanz;i++) {
            baufahrzeug.vorwaerts(geschwindigkeit);
            baufahrzeug.setWinkel((winkel/distanz)*i + w);
            try {Thread.sleep(5);} catch (Exception e) {}
        }
    }
    
    public void fahrelinkskurve(double distanz, int geschwindigkeit, double winkel) {
        double w = baufahrzeug.getWinkel();
        for (int i=0;i<= distanz;i++) {
            baufahrzeug.vorwaerts(geschwindigkeit);
            baufahrzeug.setWinkel(-(winkel/distanz)*i + w);
            try {Thread.sleep(5);} catch (Exception e) {}
        }
    }
    
    public void linksabbiegen() {
        this.fahrelinkskurve(60,5,90);
    }
            
    public void rechtsabbiegen() {
        this.fahrerechtskurve(60,5,90);
    }
    
    protected void finalize()
    {
        this.baufahrzeug.setVisible(false);
    }
    
    public void setVisible(boolean v) {
        this.baufahrzeug.setVisible(v);
    }

}