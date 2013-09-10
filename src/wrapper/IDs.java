/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wrapper;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mike
 */
public class IDs {

    public static Map<Integer, List<Item>> items = new HashMap<Integer, List<Item>>();
    FileReader fr;
    BufferedReader reader;
            
    
    
    public IDs()
    {
        try {
            fr = new FileReader("C:\\Users\\Mike\\Desktop\\items.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(IDs.class.getName()).log(Level.SEVERE, null, ex);
        }
        reader = new BufferedReader(fr);
        
        String line = null;
        try {
            while((line = reader.readLine()) != null)
            {
                if (line.contains("#"))
                    continue;
                
                StringTokenizer st = new StringTokenizer(line);
                
                short itemID = Short.parseShort(st.nextToken());
                String name = st.nextToken().replace("_", " ");
                short damage = 0;
                
                
                if (st.hasMoreTokens())
                    damage = Short.parseShort(st.nextToken());
                
                if (!items.containsKey((int)itemID))
                {
                    
                    ArrayList<Item> temp = new ArrayList<Item>();
                    temp.add(new Item(itemID, damage, name));
                    items.put((int)itemID, temp);
                    
                    
                }
                else
                {
                    ArrayList<Item> temp = (ArrayList<Item>)items.get((int)itemID);
                    temp.add(new Item(itemID, damage, name));
                    items.put((int)itemID, temp);
                }
                
                
            }
        } catch (IOException ex) {
            Logger.getLogger(IDs.class.getName()).log(Level.SEVERE, null, ex);
            
            
        }
        
        
        
    }
    
 
}
