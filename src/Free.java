
//package tema1;
import java.io.*;

public class Free extends Subscriptie{ 

    public Free( String nume ,int timestamp , int nrOfUses) {
        super( nume , timestamp ,nrOfUses);
    }
    @Override
    public void returnSub(PrintWriter output){
        output.println("Free");
    }
   
}
