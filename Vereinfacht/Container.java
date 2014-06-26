package Vereinfacht;
import Baustelle.physical.Baucontainer;
import java.util.Random;

public class Container {
    Baucontainer container;

    public Container(int x, int y) {
        container = new Baucontainer(x,y,40,40);
        container.setVisible(true);
    }
    
    public Container() {
        Random r = new Random();
        container = new Baucontainer(r.nextInt(800),r.nextInt(500),40,40);
        container.setVisible(true);
    }
    
    protected void finalize()
    {
        this.container.setVisible(false);
    }
    
    public void setVisible(boolean v) {
        this.container.setVisible(v);
    }
}