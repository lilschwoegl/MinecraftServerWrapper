/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package voicechat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import wrapper.ChatRoom;
import wrapper.Main;
import wrapper.ServerThread;

/**
 *
 * @author Mike
 */
public class AudioMultiServer extends Thread{
    
    boolean running = true;
    ServerSocket serverSocket;
    Main parent;
    ChatRoom room;
    
    public AudioMultiServer(Main parent) throws IOException
    {
        this(new ServerSocket(4444), parent);
    }
    
    public AudioMultiServer(int port, Main parent) throws IOException
    {
        this(new ServerSocket(port), parent);
    }
    
    public AudioMultiServer(ServerSocket serverSocket, Main parent)
    {
        this.serverSocket = serverSocket;
        this.parent = parent;
        room = new ChatRoom(serverSocket.getLocalPort(), "Test Room", "testName");
        ServerPortController.addRoom(room);
        parent.refreshChatRoomList();
    }
    
    public ChatRoom getRoom()
    {
        return room;
    }
    
    public void run()
    {
        System.out.println("Server started on port " + serverSocket.getLocalPort());
        while(running)
        {
            try {
                Socket socket = serverSocket.accept();
                AudioServer as = new AudioServer(socket, room);
                room.addMember(as);
                as.start();
                System.out.println("Client connected " + serverSocket.getLocalPort());
            } catch (IOException ex) {
                Logger.getLogger(AudioMultiServer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (LineUnavailableException ex) {
                Logger.getLogger(AudioMultiServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
