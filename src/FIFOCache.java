
//package tema1;
import java.util.*;

public class FIFOCache implements  Cache{

    public FIFOCache() {
    }
    
    @Override
    public void add( ArrayList<Subscriptie> cache , Subscriptie s , int i)
    {
        cache.add(s); //adaugam la sfarsit
    }
    @Override
    public int remove( ArrayList<Subscriptie> cache )
    {
        cache.remove(0);  // eliminam primul element
        return -1; // prin conventie pentru LRU,LFU , nu avem nevoie de acest -1 la FIFO
        
    }
}
