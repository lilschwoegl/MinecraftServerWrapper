/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mike
 */
public class ServerThread  extends Thread implements Stoppable{

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    String username;
    
    
    public ServerThread(Socket socket)
    {
        this.socket = socket;
    }
    
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    public String getUserName()
    {
        return username;
    }
    
    public String getIP()
    {
        return socket.getInetAddress().getHostAddress();
    }
    
    public int getPort()
    {
        return socket.getLocalPort();
    }
    
    @Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        try
        {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String inputLine, outputLine;
        
        username = in.readLine();
        
        while((inputLine = in.readLine()) != null)
        {
            System.out.println("ServerThread: " + inputLine);
        }
        
        System.out.println("Doneski ServerThread");
        out.close();
        in.close();
        socket.close();
            
        
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        
    
    }

    @Override
    public void closePorts() {
        try {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            
            out.close();
            in.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
