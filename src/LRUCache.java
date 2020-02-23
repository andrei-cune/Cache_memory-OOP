
//package tema1;
import java.util.*;

public class LRUCache implements Cache{

    public LRUCache() {
    }
     @Override
    public void add( ArrayList<Subscriptie> cache , Subscriptie s , int i)
    {
        cache.add(i,s );  //adaugam la index ul de unde se face eliminarea
    }
    @Override
    public int remove(ArrayList<Subscriptie> cache)
    {
        int i = -1;
        int minTimestamp = 9999999;
        for ( Subscriptie s : cache)
        
            if ( s.getTimestamp() < minTimestamp )
                minTimestamp = s.getTimestamp();      //gasim minimul
        
        for ( Subscriptie s : cache)
        
            if ( s.getTimestamp() == minTimestamp )   
            {   
                i = cache.indexOf(s);
                cache.remove(i);     //eliminarea
                break;
            }
        return i;  //returnam index ul de unde eliminam
        
            
        
        
    }
    
}
