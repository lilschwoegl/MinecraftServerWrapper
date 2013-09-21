/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wrapper;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mike
 */
public class Server extends Thread{

    ServerSocket serverSocket;
    boolean running = true;
    
    public Server() throws IOException
    {
        this(new ServerSocket(4444));
    }
    
    public Server(ServerSocket serverSocket)
    {
        this.serverSocket = serverSocket;
    }
    
    @Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        while (running)
        {
            try {
                new ServerThread(serverSocket.accept()).start();
            } catch (IOException ex) {
                System.err.println("Could not listen on port: " + serverSocket.getLocalPort());
            }
        }
        try {
            serverSocket.close();
        } catch (IOException ex) {
                System.err.println("Could not close on port: " + serverSocket.getLocalPort());
        }
    }
    
}
