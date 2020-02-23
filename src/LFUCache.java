
//package tema1;
import java.util.*;

public class LFUCache implements Cache{
    
    public LFUCache(){
        
    }
    @Override
    public void add( ArrayList<Subscriptie> cache , Subscriptie s , int i)
    {
       
         cache.add(i,s );
    }
    // la fel ca la LRU
    @Override
    public int remove( ArrayList<Subscriptie> cache )
    {
        int j = -1;
        int minNrOfUses = 999999999;
        int minTime = 99999999;
        Subscriptie toBeRemoved;
        
        
        
        for(Subscriptie s : cache )
            if( s.getNrOfUses()  < minNrOfUses )
                minNrOfUses = s.getNrOfUses() ; 
        
        for ( Subscriptie s : cache )
            
            if ( s.getNrOfUses() == minNrOfUses )
            
                if( s.getTimestamp() < minTime )     //gasim elementul cu cel mai mic timestamp dintre cele cu
                                                   // numarul de utilizari  egale
                    minTime = s.getTimestamp();
        
        for ( Subscriptie s : cache )
            if ( s.getTimestamp() == minTime )
            {   
                s.setNrOfUses(0);
                j = cache.indexOf(s);
                cache.remove(j);   //eliminarea
                break;
            }
        
   
        return j;
    }
    
}
