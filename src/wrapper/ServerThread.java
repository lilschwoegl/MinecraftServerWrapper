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
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import voicechat.ServerPortController;

/**
 *
 * @author Mike
 */
public class ServerThread  extends Thread implements Stoppable{

    private Socket socket;
    //private Server server;
    private PrintWriter out;
    private BufferedReader in;
    String username;
    
    
    public ServerThread(Socket socket)
    {
        this.socket = socket;
        //this.server = server;
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
        System.out.println("Added username " + username);
        
        //ArrayList<ServerThread> clients = server.getClients();
        
        while((inputLine = in.readLine()) != null)
        {
            
            System.out.println("ServerThread: " + inputLine.substring(inputLine.indexOf("<"), inputLine.indexOf(">") + 1) + " said " + "\"" + inputLine.substring(inputLine.indexOf(">") + 1, inputLine.length()) + "\"");
            processInput(inputLine);
            
            //System.out.println(clients.size());
            
            //for (ServerThread thread : clients)
            //{
            //    if (thread != this)
            //    {
                    out.println("Echo From Server: " + inputLine);
            //    }
            //}
            
            
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
    
    private void processInput(String input)
    {
        StringTokenizer st = new StringTokenizer(input);
        
        String user = st.nextToken();
        user = user.substring(1, user.length() - 1);
        
        String cmd = st.nextToken();
        
        System.out.println("ServerThread: user=" + user + " cmd=" + cmd);
        
        if (cmd.equals("startnewchatroom"))
            {
                
                int port = Integer.parseInt(st.nextToken());
                String roomName = st.nextToken();
                
                System.out.println("ServerThread: starting new chatroom");
                
                ServerPortController.addRoom(new ChatRoom(port, roomName, user));

            }
    }
    
}
