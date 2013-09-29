/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package voicechat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import wrapper.ChatRoom;
import wrapper.Main;

/**
 *
 * @author Mike
 */
public class ServerPortController {
    
    private static ArrayList <ChatRoom> rooms = new ArrayList<ChatRoom>();
    private static Main parent;
    
    public static void setParent(Main p)
    {
        parent = p;
    }
    
    public static ArrayList<ChatRoom> getRooms()
    {
        return rooms;
    }
    
    public static void addRoom(ChatRoom room)
    {
        rooms.add(room);
        sortRooms();
        parent.refreshChatRoomList();
    }
    
    public static boolean removeRoom(ChatRoom room)
    {
        return rooms.remove(room);
    }
    
    public static boolean roomExists(ChatRoom room)
    {
        return rooms.contains(room);
    }
    
    public static boolean portIsOpen(int port)
    {
        for (ChatRoom r : rooms)
        {
            if (r.getPort() == port)
            {
                return false;
            }
        }
        
        return true;
    }
    
    private static void sortRooms() {
        Collections.sort(rooms, new Comparator<ChatRoom>() {

            @Override
            public int compare(ChatRoom t, ChatRoom t1) {
                return t.getRoomName().compareTo(t1.getRoomName());
            }
        });
    }
    
    
}
