package Beleg2_SS2014;
import Baustelle.physical.*;
// TODO import anpassen
import Baustelle.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import Baustelle.graphical.*;
public class Beleg2 {

  // TODO A0 Bearbeiter anpassen; Matrikelnummer der Bearbeiter eintragen!
  public static String bearbeiter = "xxxxxxx und xxxxxxx";
  // TODO A0 ENDE

  public static void main(String[] args) {
    System.out.println("Generierte ID: " + createUUID() + "\n");
    Timer c = new Timer();
    c.start(1);
    MySilo[] silos = createSilos(10);

    for(int i=0; i<silos.length;i++) {
      // TODO A2
      // Jedes Silo bei jedem anderen (ausser sich selbst) als Observer registrieren
        for(int j=0; j<silos.length;j++) {
          // TODO
          if(i != j) {
            silos[i].registerObserver(silos[j]);
          }
        }
      // TODO A2 END
      // Alle Silos bzgl. eigener Position informieren    
      silos[i].sendPosition();    
    }

    // TODO END
    System.out.println("Verstrichene Zeit bis zur Kollision: "+c.getElapsedTime());

  }    

  private static void baustelleEinrichten() {
    Bauplatz.SkalaEinfuegen();
    Baustrasse str=new Baustrasse(0,400,900,400,45);   
    str.setVisible(true);       
    Baustrasse str2=new Baustrasse(0,400,900,400,45);   
    str2.setVisible(true);       

  }    




  private static MySilo[] createSilos(int anzahl) {
    MySilo[] silos = new MySilo[anzahl];
    // Array silos mit den erzeugten Silos befüllen und zurückgeben
    // TODO A1 BEGIN
    for(int i = 0; i <= anzahl; i++) {
      silos.add(new MySilo);
    }

    // TODO A1 END
    return silos;

  }    

  private static String createUUID() {
    String id = bearbeiter;   
    try {
      UUID uid = UUID.fromString(id);
      id = bearbeiter;
    }
    catch(Exception e) {
      UUID uuid = UUID.nameUUIDFromBytes(id.getBytes());
      id = uuid.toString();
      return id;
    }
    return id;
  }  


}
