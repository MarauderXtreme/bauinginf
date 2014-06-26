package Baustelle.graphical;
import java.awt.Graphics2D;
import Baustelle.physical.PhysicalObject;
import Baustelle.Bauplatz;

//import PhysicalObject;
/**
 * Die Klasse GraphicalObject, ist eine abstrake Klasse,sie ist die Grundklasse für alle graphischen Darstellungen
 * sie besteht aus zwei abstakten Methoden, diese müssen in alle unterklassen implementiert werden.
 * <ul>Die Methoden sind :
 * <li> aktualisiere
 * <li>paint
 * </ul>
 * @author Ulf Wagner
 * @author Ali Ismail
 *
 */
public abstract class GraphicalObject  {
    public PhysicalObject po;
    protected  static double massstab=1.0;
    
    //protected static double massstab = 3.0;
    /**
     * die Daten von den graphischen Objekten wird aktualisiert,die Daten werden synchronisiert mit dem aktuellen Daten der verbundenen Physicalobject po
     * @param po    verbinded PhysicalObject mit dem GraphicalObject
     */
    public abstract void aktualisiere(PhysicalObject po);   
    /**
    * abstakte Methode, hier wird die graphische Darstellung implementiert in jeder Klasse.
    * @param g
    */
    public abstract void paint(Graphics2D g);
    /**
     * berichtet, wie ist der allgemeine Maßstab der Darstellung. 
     * <p> der Maßtabwert kommt von der Variable massstab in der Klasse Baustelle.
     * @return
     */
    public static double getMassstab() {
        return massstab;        
    }
    
    public static void setMassstab(double neuerMassstab) {
        if (neuerMassstab <= 0.1) {
            massstab=1.0;
        } else {
            massstab = neuerMassstab;
        }
        ((Bauplatz) Bauplatz.gibBauplatz()).erneutZeichnen();
    }
}
