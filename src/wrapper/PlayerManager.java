/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wrapper;

import java.util.ArrayList;

/**
 *
 * @author Mike
 */
public class PlayerManager {
    ArrayList<Player> players;
    Main parent;
    
    public PlayerManager(int initialCapacity, Main parent)
    {
        players = new ArrayList<Player>(initialCapacity);
        this.parent = parent;
    }
    
    public void addPlayer(Player player)
    {
        players.add(player);
    }
    
    public void addPlayer(String name)
    {
        players.add(new Player(name));
        updatePlayerList();
    }
    
    public void removePlayer(String name)
    {
        players.remove(findPlayer(name));
        updatePlayerList();
    }
    
    public Player findPlayer(String name)
    {
        for (int i = 0; i < players.size(); i++)
        {
            //System.out.println("Comparing " + name + " and " + players.get(i).getName());
            if (players.get(i).getName().equals(name))
            {
                //System.out.println("Found match");
                return players.get(i);
            }
        }
        return null;
    }
    
    public int count()
    {
        return players.size();
    }
    
    public ArrayList<Player> getList()
    {
        return players;
    }
    
    public void updatePlayerList()
    {
        parent.refreshPlayerList();
    }
}
