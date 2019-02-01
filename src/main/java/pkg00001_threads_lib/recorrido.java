/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg00001_threads_lib;

/**
 *
 * @author miguel
 */
public class recorrido  implements Runnable{
    private final SimpleThreads thread = new SimpleThreads();
    String [] files;
    String exec;
    String configFile;//name of the config file
    String args;//args for send to the script
    int NoThread = 0;//number of the actual thread
    private String output;
    private String BASH;
    
    public recorrido(String []files, String exec, String configFile)
    {
        this.files = files;
        this.exec = exec;
        this.configFile = configFile;
        System.out.println("EXEC "+exec);
    }

    public recorrido(String []cadena, int NoThread) {
        files = cadena;
        this.NoThread = NoThread;
    }
    @Override
    public void run() {
        //System.out.println(files.length);
        
        for (int i = 0; i < files.length; i++) 
        {
            if(files[i]!=null&!"".equals(files[i]))
            {
               // System.out.println(files[i]+" -hilo: "+NoThread);
                this.output = thread.RUN(files[i], files[i], files[i]);
                this.setOutput(thread.getOutThread()+" thread: "+this.NoThread);
            }
        }
        /*for (int i = 0; i < files.length; i++) 
        {
            thread.RUN(configFile, exec, exec);
        }*/
        
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
    
}
