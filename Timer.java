package Beleg2_SS2014;
import java.util.*;
public class Timer 
{
    private int elapsedTime = 0;
    private java.util.Timer timer = new java.util.Timer(); 
    
    TimerTask t = new TimerTask() {
        @Override 
        public void run() {
            elapsedTime++;
        }

    };    
    
    public void start(int seconds) {
        timer.schedule(t,0,seconds*1000);
    }
    
    public void stop() {
        timer.cancel();
    }    
    
    public int getElapsedTime() {
        return elapsedTime;    
    }    

}
