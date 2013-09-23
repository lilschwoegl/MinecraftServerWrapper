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
public class PortManager {
    private ArrayList<Integer> portsInUse;
    private int serverPort;
    
    public PortManager()
    {
        this(4444);
    }
    
    public PortManager(int serverPort)
    {
        portsInUse = new ArrayList<Integer>();
        this.serverPort = serverPort;
    }
    
}
