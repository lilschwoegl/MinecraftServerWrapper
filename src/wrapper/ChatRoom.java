/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wrapper;

import java.util.ArrayList;
import voicechat.AudioServer;

/**
 *
 * @author Mike
 */
public class ChatRoom {
   private ArrayList<AudioServer> members;
   private boolean open = false;
   private int port;
   private String roomName;
   
   public ChatRoom(int port, String roomName)
   {
       this.port = port;
       this.roomName = roomName;
       members = new ArrayList<AudioServer>();
   }
   
   public int getNumberMembers()
   {
       return members.size();
   }
   
   public boolean isOpen()
   {
       return open;
   }
   
   public String getRoomName()
   {
       return roomName;
   }
   
   public boolean addMember(AudioServer member)
   {
       if (!members.contains(member))
       {
           members.add(member);
           return true;
       }
       
       return false;
   }
   
   public boolean addMember(String username)
   {
       return true;
   }
   
   public boolean removeMember(AudioServer member)
   {
       if (members.contains(member))
       {
           members.remove(member);
           return true;
       }
       
       return false;
   }
   
   public boolean removeMember(String username)
   {
       return true;
   }
   
   public ArrayList<AudioServer> getMembers()
   {
       return members;
   }
   
}
