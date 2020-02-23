
//package tema1;
import java.io.*;

public class Premium extends Basic{
    
    int premiumLeft; 
    public Premium(String nume ,int timestamp, int basicLeft , int premiumLeft , int nrOfUses) {
        super(nume ,timestamp , basicLeft , nrOfUses );
        this.premiumLeft = premiumLeft;
    }

    public int getPremiumLeft() {
        return premiumLeft;
    }

    public void setPremiumLeft(int premiumLeft , int basicLeft) {
        this.premiumLeft = premiumLeft;
        this.basicLeft = basicLeft;
    }
    
    
    @Override
    public void returnSub(PrintWriter output){
        if( this.premiumLeft <= 0 )
            super.returnSub( output );
        else
        {                           //acelasi principiu ca la Basic
          output.println("Premium");
          premiumLeft --;  
        }
        
    }
}
