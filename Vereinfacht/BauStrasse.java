package Vereinfacht;
import Baustelle.physical.Baustrasse;

public class BauStrasse {
    private Baustrasse baustrasse;
    public BauStrasse(double x1, double y1, double x2, double y2, double breite) {
        baustrasse = new Baustrasse(x1,y1,x2,y2,breite);
        baustrasse.setVisible(true);
    }
    
    public BauStrasse(double y) {
        baustrasse = new Baustrasse(0,y,800,y,40);
        baustrasse.setVisible(true);
    }
    public BauStrasse(int x) {
        baustrasse = new Baustrasse(x,0,x,500,40);
        baustrasse.setVisible(true);
    }
    public BauStrasse() {
        baustrasse = new Baustrasse(0,100,800,100,40);
        baustrasse.setVisible(true);
    }
    
    public void setVisible(boolean v) {
        this.baustrasse.setVisible(v);
    }
        
    protected void finalize()
    {
        this.baustrasse.setVisible(false);
    }
}