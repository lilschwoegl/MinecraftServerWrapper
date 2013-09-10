/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mike
 */
public class InputStreamThread implements Runnable{

    ProcessWrapper procWrapper;
    BufferedReader reader;
    Main parent;
    InputProcessor inpProc;
    
    public InputStreamThread(ProcessWrapper procWrapper, Main parent)
    {
        this.procWrapper = procWrapper;
        this.parent = parent;
        inpProc = new InputProcessor(parent);
    }
    
    @Override
    public void run() {
        reader = procWrapper.getReader();
        String line;
        
        try {
            while (( line = reader.readLine()) != null)
            {
                parent.writeToCommandHistory(line + "\n");
                inpProc.processInput(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(InputStreamThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
