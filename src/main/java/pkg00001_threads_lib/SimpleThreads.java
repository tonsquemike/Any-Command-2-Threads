/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg00001_threads_lib;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author miguel
 */
public class SimpleThreads {

/**
     * Función que ejecuta un archivo .bat
     * @param cadenaConfig
     * @param Ejecutable
     * @return 
     */
    String configFile = "";
    String exec = "";
    private String outThread;
    
    public String RUN(String cadenaConfig,String Ejecutable, String nombreConfig)
    {
        boolean bnd = true;
        String OS = "";
        String out = "";
            String Args = "1";
       
            OS = System.getProperty("os.name");
            OS = OS.toLowerCase();
            if(OS.contains("mac")|OS.contains("lin"))
            {
                
               out = (SimpleThreads.executeUNIXScript(Ejecutable));
                //System.out.println(Ejecutable);
            }
            else
            {
                out = (SimpleThreads.executeWINDOWSScript(Ejecutable));
            }
                
       
        return out;
    }
    public ArrayList<String> RUN(String bash,String Ejecutable)
    {
        String OS = "";
        ArrayList<String> out = new ArrayList();
        String[] comands = { bash, "-c", Ejecutable};
        
            OS = System.getProperty("os.name");
            OS = OS.toLowerCase();
            //if(OS.contains("mac")|OS.contains("lin"))
            //{
                
            SimpleThreads.executeUNIXScriptDIR(comands);
                //System.out.println(Ejecutable);
            //}
            //else
            //{
                
            //}
                
       
        return out;
    }
    
    public static String executeUNIXScript(String comand) 
    {
        //System.out.println(comand);
        //System.out.println("-----------------NIXSCRIPT");
        Process p = null;
        try {
            //System.out.println("Llamada: "+comand);
            p = Runtime.getRuntime().exec(comand);
        } catch (IOException ex) {
            //Logger.getLogger(SimpleThreads.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error1");
        }
        try {
            p.waitFor();
        } catch (InterruptedException ex) {
           // Logger.getLogger(SimpleThreads.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error2");
        }
 
        BufferedReader reader = 
            new BufferedReader(new InputStreamReader(p.getInputStream()));
 
        String line = "";	
        String allOut = "0";
        try 
        {
            //System.out.println("SALIDA:");
            while ((line = reader.readLine())!= null) 
            {
                //System.out.println(line);
                allOut = allOut.concat(line);
            }   
            
        } catch (IOException ex) 
        {
            allOut = "0";
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            //System.out.println("erro3");        }
        System.out.println("error");
        }
        //System.out.println("---endOut");
        return allOut;
    }
    public static String executeWINDOWSScript(String comand) 
    {
        //System.out.println(comand);
        //System.out.println("-----------------NIXSCRIPT");
        Process p = null;
        try {
            //System.out.println("Llamada: "+comand);
            p = Runtime.getRuntime().exec(comand);
        } catch (IOException ex) {
            //Logger.getLogger(SimpleThreads.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error1");
        }
        try {
            p.waitFor();
        } catch (InterruptedException ex) {
           // Logger.getLogger(SimpleThreads.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error2");
        }
 
        BufferedReader reader = 
            new BufferedReader(new InputStreamReader(p.getInputStream()));
 
        String line = "";	
        String allOut = "0";
        try 
        {
            //System.out.println("SALIDA:");
            while ((line = reader.readLine())!= null) 
            {
                //System.out.println(line);
                allOut = allOut.concat(line);
            }   
            
        } catch (IOException ex) 
        {
            allOut = "0";
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            //System.out.println("erro3");        }
        System.out.println("error");
        }
        //System.out.println("---endOut");
        return allOut;
    }
    public static ArrayList<String> executeUNIXScriptDIR(String []comand)
    {
        //System.out.println("-----------------NIXSCRIPT");
        Process p = null;
        try {
            System.out.println("Llamada");
            
            
            p = Runtime.getRuntime().exec(comand);
            
            //p.destroy();
            //p = Runtime.getRuntime().exec(comand);
        } catch (IOException ex) {
            Logger.getLogger(SimpleThreads.class.getName()).log(Level.SEVERE, null, ex);
            //System.out.println("error1");
        }
        try {
            p.waitFor();
        } catch (InterruptedException ex) {
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            //System.out.println("error2");
        }

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(p.getInputStream()));

        String line = "";
        ArrayList<String> allOut = new ArrayList<>();
        try
        {
            System.out.println("SALIDA-----------:");
            while ((line = reader.readLine())!= null)
            {
                System.out.println(line);
                allOut.add(line);
            }

        } catch (IOException ex)
        {
            //allOut = "0";
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            //System.out.println("erro3");        }
            System.out.println("error");
        }
        return allOut;
    }
    

    /**
     * @return the outThread
     */
    public String getOutThread() {
        return outThread;
    }

    /**
     * @param outThread the outThread to set
     */
    public void setOutThread(String outThread) {
        this.outThread = outThread;
    }
}