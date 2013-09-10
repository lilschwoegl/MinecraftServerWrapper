/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wrapper;

import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author Mike
 */
public class InputWrapper implements Wrapper{
    
    private OutputStream out;
    
    public InputWrapper(OutputStream out)
    {
        this.out = out;
    }
    
    public void injectCommand(String command, String arguements)
    {
        String injection = command + " " + arguements + "\n";
        try {
            out.write(injection.getBytes());
            out.flush();
        } catch (IOException e)
        {
            
        }
    }
    

    @Override
    public void start() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void stop() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
