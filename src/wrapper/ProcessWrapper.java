/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wrapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 *
 * @author Mike
 */
public class ProcessWrapper implements Wrapper{
    
    Process serverProc;
    BufferedReader reader;
    OutputStream out;
    String line;
    Main parent;
    ProcessBuilder pb;
    boolean running = false;
    
    public ProcessWrapper(int memory, String jarLoc, Main parent) throws IOException
    {
        String path = jarLoc.substring(0, jarLoc.lastIndexOf("\\"));
        System.out.println(path);
         pb = new ProcessBuilder("java", "-Xmx1024M", "-Xms1024M", "-jar", jarLoc, "nogui");
        pb.redirectErrorStream(true);
        pb.directory(new File(path));
        serverProc = pb.start();
        reader = new BufferedReader(new InputStreamReader(serverProc.getInputStream()));
        out = serverProc.getOutputStream();
       this.parent = parent;
        
    }
    
    public BufferedReader getReader()
    {
        return reader;
    }
    
    public OutputStream getOutputStream()
    {
        return out;
    }
    
    public boolean isRunning()
    {
        return running;
    }

    @Override
    public void start() {
        InputStreamThread ist = new InputStreamThread(this, parent);
        (new Thread(ist)).start();
    }

    @Override
    public void stop() {
        serverProc.destroy();
    }
    
}
