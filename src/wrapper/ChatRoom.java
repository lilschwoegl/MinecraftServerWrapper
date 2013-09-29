/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wrapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    private String host;

    public ChatRoom(int port, String roomName, String host) {
        this.port = port;
        this.roomName = roomName;
        members = new ArrayList<AudioServer>();
        this.host = host;
    }

    public boolean memberInRoom(AudioServer member) {
        for (AudioServer m : members) {
            if (m == member) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<AudioServer> getOtherClients(AudioServer member) {
        ArrayList<AudioServer> m = new ArrayList<AudioServer>();

        for (AudioServer temp : members) {
            if (temp != null && temp != member) {
                m.add(temp);
            }
        }

        return m;
    }

    public int getNumberMembers() {
        return members.size();
    }

    public boolean isOpen() {
        return open;
    }
    
    public int getPort()
    {
        return port;
    }

    public String getHost()
    {
        return host;
    }
    
    public String getRoomName() {
        return roomName;
    }

    public boolean addMember(AudioServer member) {
        if (!members.contains(member)) {
            members.add(member);
            sortArray();
            return true;
        }

        return false;
    }

    public boolean addMember(String username) {
        return true;
    }

    public boolean removeMember(AudioServer member) {
        if (members.contains(member)) {
            members.remove(member);
            return true;
        }

        return false;
    }

    public boolean removeMember(String username) {
        return true;
    }

    public ArrayList<AudioServer> getMembers() {
        return members;
    }

    private void sortArray() {
        Collections.sort(members, new Comparator<AudioServer>() {
            @Override
            public int compare(AudioServer t, AudioServer t1) {
                return t.getUserName().compareTo(t1.getUserName());
            }
        });
    }
}
