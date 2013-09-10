/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wrapper;

/**
 *
 * @author Mike
 */
public class InputProcessor {
    Main parent;
    
    public InputProcessor(Main parent)
    {
        this.parent = parent;
    }
    
    public void processInput(String input)
    {
        if (input.contains("joined the game"))
        {
            String playerName = input.substring(input.indexOf("]") + 2, input.indexOf("joined the game"));
            //System.out.println(playerName);
            parent.playerManager.addPlayer(playerName.trim());
        }
        else if (input.contains("left the game"))
        {
            String playerName = input.substring(input.indexOf("]") + 2, input.indexOf("left the game") - 1);
            //System.out.println(playerName);
            parent.playerManager.removePlayer(playerName.trim());
        }
    }
}
