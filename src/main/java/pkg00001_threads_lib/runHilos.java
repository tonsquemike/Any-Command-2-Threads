/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg00001_threads_lib;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author miguel
 */
public class runHilos 
{
    String []s;
    static int CORES = Runtime.getRuntime().availableProcessors();
    int forceNcores = 0;
    String initComand = "";
    private String output;
    private ArrayList<String> salidas = new ArrayList<>();
    private String BASH;
    private boolean manyArguments;
    public runHilos(String []files, String comando, int forceNcores)
    {
        if(files.length<forceNcores)
        {
            forceNcores = files.length;
        }
        s = files;
        //System.out.println(s[1]);
        initComand = comando;
        this.forceNcores = forceNcores;
    }
    
    public runHilos(String []files, String comando, int forceNcores, String bash)
    {
        if(files.length<forceNcores)
        {
            forceNcores = files.length;
        }
        s = files;
        //System.out.println(s[1]);
        initComand = comando;
        this.forceNcores = forceNcores;
        BASH = bash;
    }
    
    public String ejecuta()
    {
        String out = "";
        //System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        if(this.forceNcores>0)
            CORES = forceNcores;
        
        //long inicio        = System.currentTimeMillis();  

        double sizeThreads = (double)s.length/(double)CORES;
        
        int div = round(sizeThreads);
         
        int total = div*CORES;
        int sizeLastVector = 0;

        if (total > s.length)
        {
            sizeLastVector = (total-s.length);
            total = total-1;
        }
        int aux = 0;
        recorrido [] r = new recorrido[CORES];

        aux = div;
        //for (int i = 0; i < s.length; i++) 
        //    System.out.println(s[i]);
        //}
        //System.out.println("//////////////////////////");*/
        String [][] cadenas = new String[CORES][aux];
        int contadorGeneral = 0;
        int indiceLugar = 0;
 
        for (int i = 0,nCore = 0; i < s.length; i++) 
        {
                cadenas[nCore++][indiceLugar] = initComand+" "+s[contadorGeneral++];
                if(nCore==CORES)
                {
                    nCore = 0;
                    indiceLugar++;
                }  
                //System.out.println(cadenas[nCore][indiceLugar]);/////////////++++++++++++++++++++++++++++++++++++++++
        }
           
        /*for (int i = 0; i < CORES; i++) 
            {
                for (int j = 0; j < div; j++) 
                {
                    System.out.println(cadenas[i][j]);
                }
                System.out.println("---------------------------");
            }*/
        //System.out.println("**************************************");
        for (int i = 0; i < CORES; i++) 
        {
                r[i] = new recorrido(cadenas[i],i);
                //System.out.println(r[i]);
        }
            //System.out.println("******************"); 

           // execute the tasks with an ExecutorService
            ExecutorService executor = Executors.newFixedThreadPool(CORES);//Sincronyse the Thread execution
            //final Future<String>  task;
            for (int i = 0; i < CORES; i++) {
                executor.execute(r[i]);              
               // System.out.println(r[i]);
            }
          
            //Wait to finish the Threads execution*****************************IMPORTANT--sincronise th Thread execution
            //executor = Executors.newFixedThreadPool(1);    
            executor.shutdown();
            try 
            {
                executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            } catch (InterruptedException ex) {
                Logger.getLogger(runHilos.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally 
            {
                if (!executor.isTerminated()) {
                    System.err.println("cancel non-finished tasks");
                }
                executor.shutdownNow();
            //    System.out.println("shutdown finished");
            }
            salidas = new ArrayList();
            for (int i = 0; i < r.length; i++) {
                //System.out.println("output "+r[i].getOutput());
                    salidas.add(r[i].getOutput());
                    out = out.concat(r[i].getOutput());
            }
            
            try
            {
                out = out.substring(out.indexOf("["), out.length());
            }catch(Exception e){}
            
        return out;
        
    }
    public String ejecutaForLongFiles()
    {
        String out = "";
        //System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        if(this.forceNcores>0)
            CORES = forceNcores;
        
        //long inicio        = System.currentTimeMillis();  

        double sizeThreads = (double)s.length/(double)CORES;
        
        int div = round(sizeThreads);
         
        int total = div*CORES;
        int sizeLastVector = 0;

        if (total > s.length)
        {
            sizeLastVector = (total-s.length);
            total = total-1;
        }
        int aux = 0;
        recorrido [] r = new recorrido[CORES];

            aux = div;
        //for (int i = 0; i < s.length; i++) 
        //    System.out.println(s[i]);
        //}
        //System.out.println("//////////////////////////");*/
        String [][] cadenas = new String[CORES][aux];
        int contadorGeneral = 0;
        int indiceLugar = 0;
 
        for (int i = 0,nCore = 0; i < s.length; i++) 
        {
                cadenas[nCore++][indiceLugar] = initComand+" "+s[contadorGeneral++];
                if(nCore==CORES)
                {
                    nCore = 0;
                    indiceLugar++;
                }  
                //System.out.println(cadenas[nCore][indiceLugar]);/////////////++++++++++++++++++++++++++++++++++++++++
        }
           
        /*for (int i = 0; i < CORES; i++) 
            {
                for (int j = 0; j < div; j++) 
                {
                    System.out.println(cadenas[i][j]);
                }
                System.out.println("---------------------------");
            }*/
        //System.out.println("**************************************");
        for (int i = 0; i < CORES; i++) 
        {
                r[i] = new recorrido(cadenas[i],i);
                //System.out.println(r[i]);
        }
            //System.out.println("******************"); 

           // execute the tasks with an ExecutorService
           CountDownLatch latch = new CountDownLatch(r.length);
            ExecutorService executor = Executors.newFixedThreadPool(CORES);//Sincronyse the Thread execution
            //final Future<String>  task;
            for (int i = 0; i < CORES; i++) {
                executor.execute(r[i]);              
               // System.out.println(r[i]);
            }
          
            try 
            {
                latch.await();
            } catch (InterruptedException E) 
            {
                // handle
            }
            salidas = new ArrayList();
            for (int i = 0; i < r.length; i++) {
                //System.out.println("output "+r[i].getOutput());
                    salidas.add(r[i].getOutput());
                    out = out.concat(r[i].getOutput());
            }
            try{
             out = out.substring(out.indexOf("["), out.length());
            }catch(Exception e){}
            
        return out;
        
    }
        public void ejecutaOnlyArgs()
        {
            //System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            if(this.forceNcores>0)
                CORES = forceNcores;

            //long inicio        = System.currentTimeMillis();  

            double sizeThreads = (double)s.length/(double)CORES;

            int div = round(sizeThreads);

            int total = div*CORES;
            int sizeLastVector = 0;

            if (total > s.length)
            {
                sizeLastVector = (total-s.length);
                total = total-1;
            }

            int aux = 0;
            recorrido [] r = new recorrido[CORES];

                aux = div;
            String [][] cadenas = new String[CORES][aux];
            int contadorGeneral = 0;
            int indiceLugar = 0;

            for (int i = 0,nCore = 0; i < s.length; i++) 
            {
                    cadenas[nCore++][indiceLugar] = initComand+" "+s[contadorGeneral++];
                    if(nCore==CORES)
                    {
                        nCore = 0;
                        indiceLugar++;
                    }  
                    //System.out.println(cadenas[nCore][indiceLugar]);/////////////++++++++++++++++++++++++++++++++++++++++
            }

            for (int i = 0; i < CORES; i++) 
            {
                    r[i] = new recorrido(cadenas[i],i);
                    //System.out.println(r[i]);
            }
                //System.out.println("******************"); 

              // execute the tasks with an ExecutorService
               ExecutorService executor = Executors.newFixedThreadPool(CORES);//Sincronyse the Thread execution
                //final Future<String>  task;
              for (int i = 0; i < CORES; i++) {
                    executor.execute(r[i]);
                    //System.out.println(r[i]);
                }

              executor.shutdown();
            try {
                executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            } catch (InterruptedException ex) {
                Logger.getLogger(runHilos.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    private int round(double d){
        //System.out.println("ROUND: "+d);
    double dAbs = Math.abs(d);
    int i = (int) dAbs;
    double result = dAbs - (double) i;
    if(result<0.1){
        return d<0 ? -i : i;            
    }else{
        return d<0 ? -(i+1) : i+1;          
    }
}

    /**
     * @return the output
     */
    public String getOutput() {
        return output;
    }

    /**
     * @param output the output to set
     */
    public void setOutput(String output) {
        this.output = output;
    }

    /**
     * @return the salidas
     */
    public ArrayList<String> getSalidas() {
        return salidas;
    }

    /**
     * @param salidas the salidas to set
     */
    public void setSalidas(ArrayList<String> salidas) {
        this.salidas = salidas;
    }
   
}
