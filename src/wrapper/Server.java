/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wrapper;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import voicechat.AudioMultiServer;

/**
 *
 * @author Mike
 */
public class Server extends Thread implements Stoppable{

    ServerSocket serverSocket;
    boolean running = true;
    ArrayList<ServerThread> clients;
    Main parent;
    
    public Server(Main parent) throws IOException
    {
        this(new ServerSocket(4444), parent);
    }
    
    public Server(int port, Main parent) throws IOException
    {
        this(new ServerSocket(port), parent);
    }
    
    public Server(ServerSocket serverSocket, Main parent)
    {
        this.serverSocket = serverSocket;

        this.parent = parent;
        clients = new ArrayList<ServerThread>();
    }
    
    public void printClients()
    {
        for (ServerThread t : clients)
        {
            System.out.println(t.username + ": " + t.getIP() + ":" + t.getPort());
        }
    }
    
    public ArrayList<ServerThread> getClients()
    {
        return clients;
    }
    
    @Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        System.out.println("Server started on port " + serverSocket.getLocalPort());
        
        while (running)
        {
            try {
           
                Socket socket = serverSocket.accept();
                System.out.println("Accepted new socket");
                ServerThread t = new ServerThread(socket);
                clients.add(t);
                t.start();
                parent.refreshClientList();
                printClients();
                
            } catch (IOException ex) {
                System.err.println("Could not listen on port: " + serverSocket.getLocalPort());
            }
        }
        try {
            System.out.println("Closing");
            serverSocket.close();
        } catch (IOException ex) {
                System.err.println("Could not close on port: " + serverSocket.getLocalPort());
        }
        
        System.out.println("Closed");
    }

    @Override
    public void closePorts() {
        try {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

            serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
