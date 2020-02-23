
//package tema1;

import java.util.*;
import java.io.*;



public class Main {

   
    public static void main(String[] args){
       
        try
        {
            File file_in = new File(args[0]);
            File file_out = new File(args[1]);
            PrintWriter output = new PrintWriter(file_out);
            
            
            Scanner input = new Scanner(file_in);
            
            String cacheType = input.nextLine();
            int sizeMaxCache = input.nextInt();
            int N = input.nextInt();
            
            int timestamp = 0;
            
            
            ArrayList<Subscriptie> mp = new ArrayList<>(); 
            ArrayList<Subscriptie> cache = new ArrayList<>(sizeMaxCache);
            int nr_of_adds = 0;
            input.nextLine();// citim enter ul
            for ( int i = 0 ; i < N ; i ++)
            {   
                int nrOfUses = 0;
                timestamp++;
                String nameOp = "NULL";
                String objName = "NULL";
                
                StringTokenizer string = new StringTokenizer( input.nextLine() );
                int basicNr = 0;
                int premiumNr = 0;
                
                
                if( string.hasMoreTokens() )
                    nameOp = string.nextToken();
                
                                                       //citirea parametrilor(daca este cazul)
                if( string.hasMoreTokens() )
                    objName = string.nextToken();
                
                if( string.hasMoreTokens() )
                     basicNr = Integer.parseInt( string.nextToken() );
                
                if( string.hasMoreTokens() )
                     premiumNr = Integer.parseInt( string.nextToken() );
                
                Free subF;
                Basic subB;
                Premium subP;
                
                if ( nameOp.equals("ADD") == true )
                {   nr_of_adds ++;
                    int ok = 1;  //ok va fi 0 daca obiectul se afla in mem. princ. deci va fi suprascris
                    
                    int idx = -1;// indexul la inceput este -1 prin conventie
                    for( Subscriptie s : mp )
                    {
                        if( s.getNume().equals( objName ) == true )
                        {
                            ok = 0;
                            idx = mp.indexOf(s);
                            break;
                            
                        }
                    }
                   if ( ok == 0 )  // daca deja exista in m.p.
                    {   
                        
                        int index = -1;
                         if( premiumNr == 0 )
                        {
                            Basic nou = new Basic( objName, 0 , basicNr , 0 );  
                            mp.set( idx ,nou );
                        }
                         else                                                       //adaugam in functie de valoarea premiumNr citita
                        {
                            Premium nou  = new Premium( objName , 0 , basicNr , premiumNr , 0 );
                            mp.set(idx , nou );
                        }
                         // Eliminara din cache
                           for( Subscriptie m : cache )
                               if(m.getNume().equals(objName) == true )
                               {
                                   
                                   m.setNrOfUses(0);
                                   index = cache.indexOf(m);
                                   cache.remove(index);   // eliminam elementul direct de la index
                                   break;
                               }
                    }   
                    else
                    {
                        if( premiumNr == 0 )
                        {
                            subB = new Basic( objName ,0, basicNr , 0);
                            mp.add(subB);
                        }
                        else   //se adauga pe acelasi principiu ca mai sus
                        {
                            subP = new Premium( objName, 0 , basicNr , premiumNr , 0);
                            mp.add(subP);
                        }
                        
                    }
                }
                else
                if( nameOp.equals("GET") == true )
                {   
                    
                    
                    boolean isInCache = false;
                    boolean isInMemory = false;
                    int k = -1,w = -1;   //vom pastra indecsii unde se gasesc elementele in memorie si cache
                    
                    for( Subscriptie s: mp )
                        if( s.getNume().equals( objName ) == true)
                        {
                            isInMemory = true;
                            k = mp.indexOf(s); 
                            break;
                        }
                    
                    for( Subscriptie s : cache)
                        if( s.getNume().equals( objName ) == true)
                        {
                            isInCache = true;
                            s.setNrOfUses(s.getNrOfUses()+1);   // pentru LFU crestem numarul de utilizari cu 1
                            s.setTimestamp( timestamp ); //setam timestamp ul daca se afla in cache
                            w = cache.indexOf(s);
                            break;
                        }
                    //Setam timestamp-ul pentru elementele existente in cache

                   
                    Subscriptie a,b;
                    if( isInMemory == true && isInCache == true )
                    {
                        output.print("0 ");
                        a = cache.get(w);
                        a.returnSub(output);
                    }
                    if( isInMemory == true && isInCache == false )   // afisarea
                    {
                        output.print("1 ");
                        b = mp.get(k);
                        b.returnSub(output);
                    }
                    if( isInMemory == false && isInCache == false )
                    {
                        output.println("2");
                    }
                    
                    if( cacheType.equals("FIFO") == true )
                    {
                        FIFOCache c = new FIFOCache();
                        if( cache.size() == sizeMaxCache )   //verificam daca cache ul este plin
                        {
                            int j;
                            Subscriptie s;
                           
                                if( isInMemory == true && isInCache == false )
                                {     
                                    s = mp.get(k);
                                   
                                    c.remove(cache ); //eliminarea
                                    c.add(cache, s , -1);
                                }
                            
                        }
                        else
                        {
                            int j;
                            Subscriptie s;
                            
                                if( isInMemory == true && isInCache == false )   //caz -cache nu este plin
                                {     
                                    s = mp.get(k);
                                    
                                    c.add(cache, s , -1); 
                                }
                            
                        }
                        
                    }
                    else
                    if( cacheType.equals("LRU") == true )
                    {
                        LRUCache c = new LRUCache();
                        if( cache.size() <  sizeMaxCache )
                        {
                            int j;
                            Subscriptie s;
                            
                                if(isInMemory == true && isInCache == false ) //caz cache nu este plin
                                {     
                                    s = mp.get(k);
                                    s.setTimestamp(timestamp);
                                    cache.add(s);
                                }
                        }
                        else                 //daca cache ul este plin
                        {
                             int j;
                            Subscriptie s;
                            
                                if( isInMemory == true && isInCache == false )   //caz cache este plin
                                {     
                                    s = mp.get(k);
                                    s.setTimestamp(timestamp);
                                    int idx = c.remove(cache);  //eliminarea
                                    c.add(cache, s , idx);
                                }
                        }
                    }
                    else
                    if( cacheType.equals("LFU") == true )
                    {
                        LFUCache c = new LFUCache();
                        
                        if( cache.size() <  sizeMaxCache )
                        {
                            int j;
                            Subscriptie s;
                            
                                if(isInMemory == true && isInCache == false ) // cache nu este plin
                                {     
                                    s = mp.get(k);
                                    s.setTimestamp(timestamp);
                                    s.setNrOfUses(s.getNrOfUses());  //se seteaza nr de utilizari
                                    cache.add(s);
                                }
                        }
                        else                 
                        {
                             int j;
                            Subscriptie s;
                            
                                if( isInMemory == true && isInCache == false )
                                {                                               //cache este plin
                                    s = mp.get(k);
                                    s.setTimestamp(timestamp);
                                    s.setNrOfUses(s.getNrOfUses());  // setam numarul de utilizari
                                    int idx = c.remove(cache);   //eliminamm dupa criteriul din LFUCache
                                    c.add(cache, s , idx);
                                }
                        }
                    }   
                }
            }
            input.close();
            output.close();
        }
        catch(IOException err)
        {
            System.out.println("Error.");//prinderea exceptiei
        }
    }
    
}
