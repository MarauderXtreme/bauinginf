package Baustelle.physical;
import java.util.ArrayList;
import Baustelle.graphical.BaustrasseSymbol;
/**
 * Die Klasse Baustrasse repr‰sentiert die Staﬂen, auf welcher alle Fahrzeuge fahren kˆnnen.
 * die Straﬂe kann als Linie dargestellt sein oder aus mehreren Straﬂenabschnitten bestehen.
 * es ist auch mˆglich das der Staﬂenabschnitt als Kurve dargestellt wird.  
 * @author Ulf Wagner
 * @author Ali Ismail
 *
 */
@SuppressWarnings("static-access")
public class Baustrasse extends Einrichtungselement{
	//double  x1 ,y1,x2,y2;
	public Bezugspunkt punkt1;
	public Bezugspunkt punkt2;
	public static Bezugspunkt lastEnd;
	private double laenge=0;
	public double winkel=0;
	private static double breite =0;	
	public double alpha1,alpha2,r,xc,yc;
	public boolean isArc=false;
	public double maxGeschwindigkeit;
	public int fahrbahnen=2;
	public ArrayList<Baustrasse> abschnitte = new ArrayList<Baustrasse>();
	/**
	 * Konstrukteur
	 */
	public Baustrasse(){
		abschnitte.add(this);
	}

	
	
	/**
	 * Konstruktor
	 * @param x1	x-Koordinate des Anfangspunkt der Straﬂe
	 * @param y1	y-Koordinate des Anfangspunkt der Straﬂe
	 * @param x2	x-Koordinate des Endpunkt der Straﬂe
	 * @param y2	y-Koordinate des Endpunkt der Straﬂe
	 * @param breite	die Breite der Straﬂe
	 */
	
	public Baustrasse( double x1, double y1 ,double x2, double y2, double breite ) {
		punkt1 = new Bezugspunkt(x1,y1);
		punkt2 = new Bezugspunkt(x2,y2);
		lastEnd=punkt2;
		winkel=getDegree((x2-x1),(y2-y1));
		this.breite=breite;
		this.laenge=Math.sqrt(Math.pow((x2-x1),2) + Math.pow((y2-y1),2));
		abschnitte.add(this);
	}
	
	/**
	 * Konstruktor  ( Startpunkt, Winkel (degree) , L‰nge , Breite )
	 * @param bezugspunkt x- und y-Koordinaten des Anfangspunkts der Straﬂe
	 * @param winkel  die Winkel der Straﬂe
	 * @param laenge	die L‰nge der Straﬂe	
	 * @param breite	die Breite der Straﬂe
	 */
	public Baustrasse(Bezugspunkt bezugspunkt, double winkel, double laenge, double breite) {
		this.winkel=winkel;
		punkt1=bezugspunkt;
		punkt2= new Bezugspunkt (punkt1.getX() + laenge * Math.cos(winkel*Math.PI/180),punkt1.getY() + laenge * Math.sin(winkel*Math.PI/180));
		lastEnd=punkt2;
		this.laenge=laenge;
		this.breite= breite;
		abschnitte.add(this);
	}
	/**
	 * Konstruktor f¸r die Kurven der Baustrasse
	 * @param xc	x-Koordinate des Mittelpunktes des Kreises
	 * @param yc	y-Koordinate des Mittelpunktes des Kreises
	 * @param r		Radius
	 * @param alpha1	der Winkel zwischen dem Anfangspunkt und dem Mittelpunkt des Kreises 
	 * @param alpha2	der Winkel zwischen dem Endpunkt und dem Mittelpunkt des Kreises 	
	 * @param breite	die Breite der Straﬂe	
	 */
	public Baustrasse( double xc, double yc ,double r, double alpha1,double alpha2, double breite ) {
		this.isArc=true;
		this.xc=xc;
		this.yc=yc;
		this.r=r;
		this.alpha1=alpha1;
		this.alpha2=alpha2;
		this.breite=breite;
		double angle = Math.toRadians(-alpha1);		
		double width=2*r;
		double height=2*r;
		double x = xc+ (Math.cos(angle) * 0.5) * width;
		double y = yc + (Math.sin(angle) * 0.5) * height;
		this.punkt2=new Bezugspunkt(x,y);		
		double AngleExtent= alpha2;
		angle = Math.toRadians(-alpha1 - AngleExtent);
		x = xc + (Math.cos(angle) * 0.5) * width;
		y = yc + (Math.sin(angle) * 0.5) * height;		
		this.punkt1=new Bezugspunkt(x,y);		
		this.laenge=(AngleExtent*Math.PI/180)*r;
		System.out.println("("+punkt1.x + ","+ punkt1.y + ") , (" + punkt2.x + "," + punkt2.y + ")");
		lastEnd=punkt2;
		abschnitte.add(this);
	}
	
	/**
	 * static Methode f¸r die Verbindung zwischen zwei Staﬂen
	 * @param bauS1
	 * @param bauS2
	 * @return Baustraﬂe Objekt
	 */
	public static Baustrasse connectTwoStreets(Baustrasse bauS1,Baustrasse bauS2){
		Baustrasse newBauStrasse= new Baustrasse();
		newBauStrasse.setPunkt1(bauS1.getPunkt2());
		newBauStrasse.setPunkt2(bauS2.getPunkt1());
		newBauStrasse.setBreite((bauS1.getBreite()+bauS2.getBreite())/2);		
		return newBauStrasse;
	}
	
	/**
	 * Verl‰ngert die Straﬂe bis zum Punkt (x,y)
	 * @param x
	 * @param y
	 */	
	public  void inToXY(double x, double y){
		Baustrasse newBauStrasse= new Baustrasse();
		newBauStrasse.setPunkt1(Baustrasse.lastEnd);
		newBauStrasse.setPunkt2(new Bezugspunkt(x,y));	
		newBauStrasse.winkel=getDegree((x-newBauStrasse.punkt1.x),(y-newBauStrasse.punkt1.y));
		newBauStrasse.setLaenge(Math.sqrt(Math.pow((x-newBauStrasse.punkt1.x),2) + Math.pow((y-newBauStrasse.punkt1.y),2)));
		newBauStrasse.setBreite(Baustrasse.breite);
		lastEnd=newBauStrasse.punkt2;
		abschnitte.add(newBauStrasse);
	}
	
	/**
	 * Verl‰ngert die Straﬂe in einem bestimmten Winkel und L‰nge
	 * @param laenge
	 * @param winkel
	 */
	public   void AddSegment(double laenge, double winkel){
		Baustrasse newBauStrasse= new Baustrasse();
		newBauStrasse.setPunkt1(Baustrasse.lastEnd);
		newBauStrasse.setPunkt2(new Bezugspunkt (Baustrasse.lastEnd.getX() + laenge * Math.cos(winkel*Math.PI/180),Baustrasse.lastEnd.getY() + laenge * Math.sin(winkel*Math.PI/180)));
		newBauStrasse.winkel=getDegree((newBauStrasse.punkt2.x-newBauStrasse.punkt1.x),(newBauStrasse.punkt2.y-newBauStrasse.punkt1.y));
		newBauStrasse.setBreite(Baustrasse.breite);
		newBauStrasse.setLaenge(laenge);
		lastEnd=newBauStrasse.punkt2;
		abschnitte.add(newBauStrasse);
	}
	
	/**
	 * Verl‰ngert die Straﬂe durch eine Kurve
	 * @param xc
	 * @param yc
	 * @param r
	 * @param alpha1
	 * @param alpha2
	 */
	public   void AddSegment(double xc, double yc ,double r, double alpha1,double alpha2){
		Baustrasse newBauStrasse= new Baustrasse();
		newBauStrasse.isArc=true;
		newBauStrasse.xc=xc;
		newBauStrasse.yc=yc;
		newBauStrasse.r=r;
		newBauStrasse.alpha1=alpha1;
		newBauStrasse.alpha2=alpha2;
		newBauStrasse.setBreite(Baustrasse.breite);
		double angle = Math.toRadians(-alpha1);		
		double width=2*r;
		double height=2*r;
		double x = xc+ (Math.cos(angle) * 0.5) * width;
		double y = yc + (Math.sin(angle) * 0.5) * height;
		newBauStrasse.punkt2=new Bezugspunkt(x,y);		
		double AngleExtent= alpha2;
		angle = Math.toRadians(-alpha1 - AngleExtent);
		x = xc + (Math.cos(angle) * 0.5) * width;
		y = yc + (Math.sin(angle) * 0.5) * height;		
		newBauStrasse.punkt1=new Bezugspunkt(x,y);		
		newBauStrasse.laenge=(AngleExtent*Math.PI/180)*r;
		lastEnd=punkt1;
		abschnitte.add(newBauStrasse);
	}	
	
	/**
	 * Gibt den Anfangspunkt der Straﬂe zur¸ck
	 * @return Anfangspunkt
	 */
	public Bezugspunkt getPunkt1() {
		return punkt1;
	}


	/**
	 * Setzt den neuen Anfangspunkt der Straﬂe
	 * @param punkt1 neuer Anfangspunkt
	 */
	public void setPunkt1(Bezugspunkt punkt1) {
		this.punkt1 = punkt1;
		benachrichtige();
	}


	/**Gibt den Endpunkt der Straﬂe zur¸ck
	 * @return Endpunkt
	 */
	public Bezugspunkt getPunkt2() {
		return punkt2;
	}


	/**
	 * Setzt den neuen Endpunkt der Straﬂe
	 * @param punkt2 neuer Endpunkt
	 */
	public void setPunkt2(Bezugspunkt punkt2) {
		this.punkt2 = punkt2;
		benachrichtige();
	}


	/**
	 * Gitb die Breite der Straﬂe zur¸ck
	 * @return Straﬂenbreite
	 */
	public double getBreite() {
		return breite;
	}


	/**
	 * Setzt die neue Breite der Straﬂe
	 * @param breite neue Straﬂenbreite
	 */
	public void setBreite(double breite) {
		this.breite = breite;
		benachrichtige();
	}


	/**
	 * Gibt die L‰nge der Straﬂe zur¸ck
	 * @return L‰nge der Straﬂe
	 */
	public double getLaenge() {
		return laenge;
	}

	/**
	 * Setzt die neue Straﬂenl‰nge
	 * @param neue Straﬂenl‰nge
	 */
	public void setLaenge(double laenge) {
		this.laenge = laenge;
		this.benachrichtige();
	}

	/**
	 * Gibt den aktuellen Winkel der Straﬂe zur¸ck.
	 * @return Winkel der Straﬂe
	 */
	public double getWinkel() {
		return winkel;
	}

	/**
	 * Setzt den neuen Winkel der Straﬂe
	 * @param winkel neuer Winkel
	 */
	public void setWinkel(double winkel) {
		this.winkel = winkel;
		this.benachrichtige();
	}

	/**
	 *  Gibt die Anzahl der Fahrbahnen zur¸ck.
	 * @return Fahrbahnen
	 */
	public int getFahrbahnen() {
		return fahrbahnen;
	}

	/**
	 * Setzt die neue Anzahl der Fahrbahnen
	 * @param fahrbahnen neue Anzahl
	 */
	public void setFahrbahnen(int fahrbahnen) {
		this.fahrbahnen = fahrbahnen;
		this.benachrichtige();
	}

	/**
	 * Setzt den Zustand der Sichtbarkeit der Straﬂe
	 */
	public void setVisible(boolean visible) {
	
		if (visible) {
			if (!isVisible()){			
				go = new BaustrasseSymbol();
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
	 * Pr¸ft ob ein bestimmter Punkt auf der Straﬂe liegt
	 * @param x
	 * @param y
	 * @return false oder true
	 */
	public boolean aufDieStrasse(double x , double y) {	
		double x0,y0,x1,y1;
		for ( int i=0; i < abschnitte.size(); i++) {				
			 x0=abschnitte.get(i).punkt1.x;
			 y0=abschnitte.get(i).punkt1.y;
			 x1=abschnitte.get(i).punkt2.x;
			 y1=abschnitte.get(i).punkt2.y;
			 double l1=Math.sqrt(Math.pow(x-x0, 2) + Math.pow(y-y0, 2));
			 double l2=Math.sqrt(Math.pow(x-x1, 2) + Math.pow(y-y1, 2));
			 double l=Math.sqrt(Math.pow(x1-x0, 2) + Math.pow(y1-y0, 2));			 
			 System.out.println(l1 + "+" + l2 + "=" + l);
			 if (l1+l2 ==  l)
			return true;		
	}
		return false;
	}
	
	/**
	 * Gibt die Fahrtl‰nge zwischen Anfangspunkt der Straﬂe und einem bestimmten Punkt (x,y),der auf der Straﬂe liegt, zur¸ck.
	 * @param x
	 * @param y
	 * @return 
	 */
	public double langebisXY(double x , double y) {	
		double x0,y0,x1,y1,lange=0;
		for ( int i=0; i < abschnitte.size(); i++) {				
			 x0=abschnitte.get(i).punkt1.x;
			 y0=abschnitte.get(i).punkt1.y;
			 x1=abschnitte.get(i).punkt2.x;
			 y1=abschnitte.get(i).punkt2.y;
			 double l1=Math.sqrt(Math.pow(x-x0, 2) + Math.pow(y-y0, 2));
			 double l2=Math.sqrt(Math.pow(x-x1, 2) + Math.pow(y-y1, 2));
			 double l=Math.sqrt(Math.pow(x1-x0, 2) + Math.pow(y1-y0, 2));			 
			 if (l1+l2 ==  l)
			return lange+l1;
			 else
				 lange=lange +l;
			 }
		return lange;
	}
	/**
	 * Pr¸ft ob ein bestimmter Punkt (x,y) auf einer bestimmten Straﬂe liegt
	 * @param x
	 * @param y
	 * @param baustr Straﬂenobjekt
	 * @return true oder false
	 */
	public static boolean aufDieStrasse(double x , double y ,Baustrasse baustr) {
		double x0,y0,x1,y1;
		for ( int i=0; i < baustr.abschnitte.size(); i++) {				
			 x0= baustr.abschnitte.get(i).punkt1.x;
			 y0= baustr.abschnitte.get(i).punkt1.y;
			 x1= baustr.abschnitte.get(i).punkt2.x;
			 y1= baustr.abschnitte.get(i).punkt2.y;
			 double l1=Math.sqrt(Math.pow(x-x0, 2) + Math.pow(y-y0, 2));
			 double l2=Math.sqrt(Math.pow(x-x1, 2) + Math.pow(y-y1, 2));
			 double l=Math.sqrt(Math.pow(x1-x0, 2) + Math.pow(y1-y0, 2));			 
			 if (l1+l2 ==  l)
			return true;		
	}
		return false;
		
	}
	
	protected void finalize()
    {
        this.setVisible(false);
    }
}
