import Baustelle.physical.*;
public class FullDemo
{
   
    /**
     * Constructor for objects of class FullDemo
     */
    public FullDemo()
    {
      
    }

     public void demo() throws Exception{
    	Baustrasse str=new Baustrasse(10,10,50,10,8);    	
    	str.setVisible(true);    	
    	str.AddSegment(50, 90);
    	str.AddSegment(150, 0);
    	str.AddSegment(110, 90);
    	str.AddSegment(190, 180);
    	str.AddSegment(155, -90);
    	
    	
    	Kran kran1 = new Kran("Kran1");
    	kran1.setX(45);
    	kran1.setY(75);
    	kran1.setAusLegerlaenge(75);
    	kran1.setDrehenGeschwindigkeit(0.1);
    	kran1.setKatzeGeschwindigkeit(3);
    	kran1.setbaseBreite(4);
    	kran1.setVisible(true);
    	
    	Kran kran2 = new Kran();
    	kran2.setKranCode("K2");    	
    	kran2.setX0(60);
    	kran2.setX1(190);
    	kran2.setY0(160);
    	kran2.setY1(160);
    	kran2.setBaseLocation(0.5);
    	kran2.setAusLegerlaenge(75);
    	kran2.setbaseBreite(4);
    	kran2.setAuslegerwinkel(0);
    	kran2.setDrehenGeschwindigkeit(0.1);
    	kran2.setKatzeGeschwindigkeit(0.5);
    	kran2.setBaseGeschwindigkeit(2);
    	kran2.setVisible(true);
    	
    	
    	Baugrube grube=new Baugrube();
    	grube.setX(50);
    	grube.setY(70);
    	grube.setBreite(80);
    	grube.setLaenge(140);
    	grube.setTiefe(3);
    	grube.setAkteulleBreite(80);
    	grube.setAkteulleLaenge(140);
    	grube.setAkteulleTiefe(3);
    	grube.setVisible(true);
    	
    	Bagger bgr=new Bagger(5,5);
    	bgr.setKetten(true);
    	bgr.setX(20);
    	bgr.setY(60);
    	bgr.setGrubenLeistung(1000);
    	bgr.setVisible(true);
    	
    	
    	Bagger bgr1=new Bagger(3,8);    	
    	bgr1.setX(40);
    	bgr1.setY(140);
    	bgr1.setGrubenLeistung(1000);
    	bgr1.setGeschwindigkeit(10);
    	bgr1.setWinkel(-90);
    	bgr1.setFullenLeistung(3);
    	bgr1.setVisible(true);
    	
    	Kipper kip1=new Kipper(8,0,0,3,6);
    	kip1.setMaxVolumen(12);
    	kip1.setGeschwindigkeit(40);
    	kip1.setVisible(true);
    	
    	
    	bgr1.kipperfullen(kip1);    	
    	
    	LKW lkw1=new LKW(15,12,0,2.4,6);
    	lkw1.setGeschwindigkeit(40);
    	lkw1.setVisible(true);
    	
    	Kipper kipper= new Kipper(25,12,0,2.5,7);
    	kipper.setVisible(true);
    	kipper.setGeschwindigkeit(20);
    	
    	
    	
    	Material mat1=new Material("Holz");
    	mat1.setGewicht(200);
    	mat1.setVolumen(0.5);
    	mat1.setX(80);
    	mat1.setY(67);
    	mat1.setBreite(2);
    	mat1.setLaenge(8);

    	
    	lkw1.beladen(mat1);
    /*
    	kran1.setKatzeXYLocation(80, 67);
    	kran1.beladen(mat1);    	
    	kran1.setKatzeXYLocation(60, 120);    
    	kran1.entladen(mat1);
    	kran1.setKatzeXYLocation(90, 60);
    	*/
    	//kran2.drehen(-90);
    	
    	
    	
    	
    //	kran2.katzeUmzug(60);    	
    	kran2.setKatzeXYLocation(40, 100);    	
     	//lkw1.fahreAufStrasse(str);
    	// kipper.fahreAufStrasse(str);
    	bgr.gruben(grube);

    	
    	Baucontainer baucont1=new Baucontainer(25,20,12,3);
    	baucont1.setVisible(true);
    	baucont1.setCode("BC1");
    	baucont1.setNotiz("<font color='#FF0000'>benutzt als Aufenthaltsraum");
    	
    	Silo silo1 = new Silo(30,50,3);
    	silo1.setHoehe(7.5);
    	silo1.setVolumen(25);
    	silo1.setLeergewicht(2500);
    	silo1.setVisible(true);
    	silo1.setNotiz("<font color='#0000FF'>Silo für Zement");  	
    
    }
}
