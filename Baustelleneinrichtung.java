import Baustelle.*;
import Baustelle.physical.*;
import java.awt.Color;

public class Baustelleneinrichtung
{
     /**
     * Erzeugt eine Baustrasse (fixe Position)
     */
    private Baustrasse createConstructionRoad() {
        Baustrasse str=new Baustrasse(0,400,900,400,45);    
        
        str.setVisible(true);       

        return str;
    }    
    /**
     * Erzeugt eine Baugrube (fixe Position)
     */
    private Baugrube createExcavationPit() {
        Baugrube grube=new Baugrube();
        grube.setX(240);
        grube.setY(500);
        grube.setBreite(200);
        grube.setLaenge(200);
        grube.setTiefe(3);
        grube.setAktuelleBreite(200);
        grube.setAktuelleLaenge(200);
        grube.setAktuelleTiefe(3);
        grube.setVisible(true);
        
        
        
        return grube;
    }    
    
    /**
     * Erzeugt einen Kran (fixe Position)
     */
    private Kran createCrane() {
        Kran kran1 = new Kran("Kran1");
        kran1.setX(150);
        kran1.setY(185);
        kran1.setAusLegerlaenge(150);
        kran1.setDrehenGeschwindigkeit(0.1);
        kran1.setKatzeGeschwindigkeit(3);
        kran1.setbaseBreite(35);
        kran1.setVisible(true);
        return kran1;
    }    
    
    
    /**
     * Erzeugt einen Kran auf der Schiene (fixe Position)
     */
    
    private Kran createRailCrane() {
        Kran kran2 = new Kran();
        kran2.setKranCode("K2");        
       
        // X0,X1 - Y0, Y1 Anfangs- / Endpunkt der Schiene
        kran2.setX0(330);
        kran2.setX1(330);
        kran2.setY0(350);
        kran2.setY1(85);
        kran2.setBaseLocation(1);
        kran2.setAusLegerlaenge(110);
        kran2.setbaseBreite(35);
        kran2.setAuslegerwinkel(-180);
        kran2.setDrehenGeschwindigkeit(0.3);
        kran2.setKatzeGeschwindigkeit(0.8);
        kran2.setBaseGeschwindigkeit(5);
        kran2.setVisible(true);    
        return kran2;
    }    
    
    /**
     * Erzeugt einen Bagger (fixe Position)
     */
    
    private Bagger createExcavator() {
        Bagger bgr=new Bagger(35,35);
        bgr.setKetten(true);
        bgr.setX(210);
        bgr.setY(650);
        bgr.setGrubenLeistung(1000);
        bgr.setVisible(true);
        return bgr;
    }    
    
    /**
     * Erzeugt einen Kipper (fixe Position)
     */
    
    private Kipper createDumper() {
        Kipper kipper = new Kipper(200,600,0,22,40);
        kipper.setMaxVolumen(50);
        kipper.setGeschwindigkeit(40);
        kipper.setVisible(true);
        return kipper;
    }    
    
    /**
     * Erzeugt eine Menge von Baucontainern (fixe Position)
     */
    
    private void createBuildingContainer() {
        Baucontainer baucont1=new Baucontainer(260,60,48,24);
        baucont1.setVisible(true);
        
        Baucontainer baucont2=new Baucontainer(260,110,48,24);
        baucont2.setVisible(true);
        
        Baucontainer baucont3=new Baucontainer(260,160,48,24);
        baucont3.setVisible(true);
        
        Baucontainer baucont4=new Baucontainer(260,210,48,24);
        baucont4.setVisible(true);
        
        Baucontainer baucont5=new Baucontainer(260,260,48,24);
        baucont5.setVisible(true);
        
    }
    
    private void createSilos() {
    
    Silo Silo = new Silo(100,100,40);    
    int i = 0, j = 0;
       
       while(i<4) {
           while(j<2) {
               Silo silo1=new Silo(250+35*j,60+65*i,30);
               silo1.setVisible(true);
               j++;
           }
       i++;
       // Ohne zurücksetzen auf 0 werden nur zwei Silos angezeigt
       j=0;
       }
  

       
        
    }
    
    
  
    /**
     * Erzeugt einen Lagerplatz (fixe Position)
     */
    
    private Lagerplatz createLagerplatz() {
        Lagerplatz lager1=new Lagerplatz();
        lager1.setX(270);
        lager1.setY(150);
        lager1.setVisible(true);  
        
        Material mat1=new Material();
        mat1.setX(270);
        mat1.setY(150);
        mat1.setMatName("Erde");
        mat1.setGewicht(1200);
        mat1.setBreite(290);
        mat1.setLaenge(100);
        mat1.setMatcolor(new Color (204  ,153,  51,30));
        mat1.setVisible(true);
        lager1.addMaterial(mat1);
        return lager1;    
    }    
    
    /**
     * Simple Demo einer ersten Baustelleneinrichtung
     * @author A.Wülfing
     */
    
    public void demo() {
        Bauplatz bauplatz = Bauplatz.einrichten(); 
        bauplatz.SkalaEinfuegen();
        createConstructionRoad();
        createExcavationPit();
        createLagerplatz();
        // Kran k = createCrane();
        // try { k.drehen(90); } catch(Exception e) {}
        createExcavator();
        createSilos();
        createRailCrane();
        createDumper();
       
    }    

}
