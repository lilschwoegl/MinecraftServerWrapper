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
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mike
 */
public class Client extends Thread implements Stoppable {

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private String username;

    public Client() throws UnknownHostException, IOException {
        this(new Socket("localhost", 4444), "test_client");
    }
    
    public Client(String username) throws UnknownHostException, IOException
    {
       this(new Socket("localhost", 4444), username);
    }

    public Client(String server, int port, String username) throws UnknownHostException, IOException {
        this(new Socket(server, port), username);
    }
    
    public Client(int port, String username) throws UnknownHostException, IOException
    {
        this(new Socket("localhost", port), username);
    }

    public Client(Socket clientSocket, String username) {
        try {
            this.clientSocket = clientSocket;
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            this.username = username;
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getIP()
    {
        return clientSocket.getInetAddress().getHostAddress();
    }
    
    // Formatting:
    // startnewchatroom <port> <name>
    public void sayToServer(String say)
    {
        out.println("<" + username + "> " + say);
        
    }

    public int getPort() {
        return clientSocket.getPort();
    }

    public int getLocalPort() {
        return clientSocket.getLocalPort();
    }
    
    public String getUsername()
    {
        return username;
    }

    @Override
    public void run() {
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String fromServer;
        String fromUser;

        System.out.println("Client (" + clientSocket.toString() + ") connected to port" + getLocalPort());
        out.println(username);


        try {
            while ((fromServer = in.readLine()) != null) {
                System.out.println(fromServer);
                 //System.out.println("Client: " + stdIn.readLine());
               // out.println(stdIn.readLine());
            }
            
            System.out.println("Doneski Client");

            out.close();
            in.close();
            stdIn.close();
            clientSocket.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void closePorts() {
        try {
            out.close();
            in.close();
            clientSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
