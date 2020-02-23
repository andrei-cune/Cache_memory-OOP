
//package tema1;
import java.io.*;

public class Basic extends Free{
    
    int basicLeft;
    
    public Basic(String nume ,int timestamp, int basicLeft , int nrOfUses) {
        super(nume,timestamp , nrOfUses);
        this.basicLeft = basicLeft;
    }

    public int getBasicLeft() {
        return basicLeft;
    }
   
    public void setBasicLeft(int basicLeft) {
        this.basicLeft = basicLeft;
    }

    @Override
    public void returnSub(PrintWriter output){
        if( this.basicLeft <= 0 )
            super.returnSub( output );
        else                               //daca nu avem subscriptii basic ne intoarcem in clasa Free
        {                                   //si facem decrementarea
          output.println("Basic");
          basicLeft --;  
        }
        
    }
    
}
