package Baustelle.graphical;
/**
 * 
 */
 
import java.awt.*; 
import java.awt.geom.*; 
import java.util.Iterator;
import Baustelle.physical.PhysicalObject;
import Baustelle.physical.Kran;
import Baustelle.physical.Material;


//import cib.lehre.baustelle.gui.ZeichenPanel2;
//import PhysicalObject;
//import Kran;
//import Material;

/**
 *  Die Darstellung Klasse für  Kran Klasse
 * @author Ali Ismail
 *
 */
public class KranSymbol extends GraphicalObject {	
	private double x0,y0,x1,y1,x ,y ;
	private String kranCode;
	private double baseBreite,ausLegerlaenge;
	private double baselinewinkel;
	private double ausLegerWinkel;
	private double scaleX =getMassstab();
	private double katzeLocation=0.75;
	Color kranColor= new Color( 255 , 204 , 51 , 100 );
	Color ausLegerColor= new Color( 255 , 204 , 51 , 230 );
	Color katzeColor= new Color(0, 0 ,0 , 230);	
	public KranSymbol() {
		
	}	
	/**
	 * 	 die Darstellungsdaten (Kranschienendaten,Auslegerdaten,Katzedaten, usw) werden aktualisiert und synchronisiert mit dem aktuellen Daten der verbundenen Physicalobject po
	 * <p> die Lage der Materialien, die aufgelanden auf dem Kranhaken sind, werden auch aktualisiert.
	 */

	public void aktualisiere(PhysicalObject po){
		this.po=po;
		Kran kranObject= (Kran) po;	
		x0 = kranObject.getX0();
		y0 = kranObject.getY0();
		x1 = kranObject.getX1();
		y1 = kranObject.getY1();		
		x = kranObject.getX();
		y = kranObject.getY();
		kranCode=kranObject.getKranCode();
		baseBreite=kranObject.getbaseBreite();
		ausLegerlaenge=kranObject.getAusLegerlaenge();
		ausLegerWinkel=kranObject.getAuslegerwinkel();
		katzeLocation=kranObject.getKatzeLocation();
		baselinewinkel=kranObject.getBaseLineWinkel();		
		Iterator<Material> it = kranObject.getMaterialListe().listIterator();
	       while (it.hasNext()){
	    	   Material mat = (Material) it.next();
	    	   mat.setX(x+ausLegerlaenge*Math.cos(ausLegerWinkel*Math.PI/180)*katzeLocation);
	    	   mat.setY(y+ausLegerlaenge*Math.sin(ausLegerWinkel*Math.PI/180)*katzeLocation);
	    	   mat.benachrichtige();
	       }
	}
	/**
	 * zeichnet das Kransymbol auf aktuelle (x,y) Lage
	 */
	public void paint (Graphics2D graphics2D) {
		scaleX=getMassstab();
		double cos=Math.cos(Math.toRadians(baselinewinkel));
		double sin=Math.sin(Math.toRadians(baselinewinkel));
		graphics2D.scale(scaleX, scaleX);
		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		graphics2D.setStroke(  new BasicStroke( 1.0f , BasicStroke.CAP_BUTT , BasicStroke.JOIN_MITER ));		
	    Stroke thindashed = new BasicStroke( 0.2f, BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL, 1.0f,new float[] { 1.5f,0.5f },0.0f); 
	    graphics2D.setStroke(thindashed);
		graphics2D.setPaint( new Color( 200 ,0 ,0 , 240 ) );
		
		graphics2D.setStroke(  new BasicStroke( 0.5f , BasicStroke.CAP_BUTT , BasicStroke.JOIN_MITER ));
		graphics2D.draw(new Line2D.Double(x0+0.3*baseBreite*sin, y0+0.3*baseBreite*cos, x1+0.3*baseBreite*sin, y1+0.3*baseBreite*cos));		
		graphics2D.draw(new Line2D.Double(x0-0.3*baseBreite*sin, y0-0.3*baseBreite*cos, x1-0.3*baseBreite*sin, y1-0.3*baseBreite*cos));	
		
		graphics2D.setPaint( kranColor ); 
		graphics2D.setStroke(  new BasicStroke( 0.2f , BasicStroke.CAP_BUTT , BasicStroke.JOIN_MITER ));		
		Rectangle2D.Double kranRect = new Rectangle2D.Double (x-0.5*baseBreite, y-0.5*baseBreite ,baseBreite, baseBreite ) ;
		graphics2D.fill(kranRect);

		graphics2D.setPaint( new Color( 0 ,0 ,0 , 255 ) ); 
    	graphics2D.draw (kranRect);
    	/*if (kranCode.isEmpty()==false  ){
    	graphics2D.drawString(kranCode,(int) x- (int) (baseBreite),(int) y- (int) (baseBreite));}    	*/
    	graphics2D.setStroke(  new BasicStroke( 0.3f , BasicStroke.CAP_BUTT , BasicStroke.JOIN_MITER ));	
		graphics2D.setPaint( ausLegerColor );
		graphics2D.rotate(ausLegerWinkel*Math.PI/180, x, y) ;		
	
		Rectangle2D.Double ausLegerRect = new Rectangle2D.Double (x-0.15*ausLegerlaenge, y-0.2*baseBreite  ,1.15*ausLegerlaenge, 0.4*baseBreite ) ;
		
		graphics2D.fill(ausLegerRect);	
		
		graphics2D.setPaint( new Color( 0 ,0 ,0 , 255) ); 
		graphics2D.draw( new Rectangle2D.Double (x-0.35*baseBreite, y-0.35*baseBreite  ,0.7*baseBreite, 0.7*baseBreite ));
		
		
		graphics2D.setPaint( new Color( 0 ,0 ,0 , 255) ); 
		graphics2D.draw(ausLegerRect);
		
    	Arc2D.Double kranCenter= new Arc2D.Double(x-0.15*baseBreite, y-0.15*baseBreite, 0.3*baseBreite, 0.3*baseBreite, 360, 360,Arc2D.PIE);
		graphics2D.fill(kranCenter);
		
		GeneralPath gp1;
		gp1 = new GeneralPath();
		float signal=-1;
		int n=5;
		gp1.moveTo(x+ausLegerlaenge/n, y-0.2*baseBreite);
		for (int count=0; count <= n; count++) {		
				signal *=-1;
			gp1.lineTo(x+ausLegerlaenge*count/n,  y-0.2*signal*baseBreite);
 		}
		graphics2D.draw((GeneralPath) gp1);
		
		GeneralPath gp2;		
		gp2= new GeneralPath();
		 signal=1;
		gp2.moveTo(x+ausLegerlaenge/n, y-0.2*baseBreite);
		for (int count=0; count <= n; count++) {		
				signal *=-1;
			gp2.lineTo(x+ausLegerlaenge*count/n,  y-0.2*signal*baseBreite);
 		}
		graphics2D.draw((GeneralPath) gp2);		
		graphics2D.rotate(-ausLegerWinkel*Math.PI/180, x, y ) ;
		graphics2D.setPaint( katzeColor ); 
		graphics2D.rotate(ausLegerWinkel*Math.PI/180, x+ausLegerlaenge*Math.cos(ausLegerWinkel*Math.PI/180)*katzeLocation, y+ausLegerlaenge*Math.sin(ausLegerWinkel*Math.PI/180)*katzeLocation) ;
		Rectangle2D.Double katzeRect = new Rectangle2D.Double (x+ausLegerlaenge*Math.cos(ausLegerWinkel*Math.PI/180)*katzeLocation -baseBreite*0.2 , y+ausLegerlaenge*Math.sin(ausLegerWinkel*Math.PI/180)*katzeLocation-baseBreite*0.2  ,baseBreite*0.4, baseBreite*0.4 ) ;
		graphics2D.fill(katzeRect);
		graphics2D.rotate(-ausLegerWinkel*Math.PI/180, x+ausLegerlaenge*Math.cos(ausLegerWinkel*Math.PI/180)*katzeLocation, y+ausLegerlaenge*Math.sin(ausLegerWinkel*Math.PI/180)*katzeLocation ) ;
		graphics2D.scale(1/scaleX, 1/scaleX);
	}	
}
