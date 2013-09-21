/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wrapper;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Mike
 */
public class ServerThread  extends Thread{

    private Socket socket;
    
    public ServerThread(Socket socket)
    {
        this.socket = socket;
    }
    
    
    @Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        
        
    
    }
    
}
