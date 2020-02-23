
//package tema1;
import java.io.*;

public abstract class Subscriptie {
    
    private String nume;
    private int timestamp;
    private int nrOfUses;
    protected Subscriptie(String nume,int timestamp , int nrOfUses) {
        this.nume = nume;
        this.timestamp = timestamp;
        this.nrOfUses = nrOfUses;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public int getNrOfUses() {
        return nrOfUses;
    }

    public void setNrOfUses(int nrOfUses) {
        this.nrOfUses = nrOfUses;
    }
    
    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }
    public abstract void returnSub(PrintWriter output);  // pentru afisarea FREE/BASIC/PREMIUM
    
}







