/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wrapper;


/**
 *
 * @author Mike
 */
public class ItemInfo {
    
    static enum Axes
    {
        DIAMOND(279, "diamondaxe"), GOLD(286, "goldaxe"), IRON(258, "ironaxe"), STONE(275, "stoneaxe"), WOOD(271, "woodenaxe");
        private int ID;
        private String icon;
        
        private Axes(int ID, String icon)
        {
            this.ID = ID;
            this.icon =  getClass().getResource("/icons/" + icon + "_icon32.png").getPath();
        }
        
        public int getID()
        {
            return ID;
        }
        
        public String getIconPath()
        {
            return icon;
        }
    }
    
    static enum Picaxes
    {
        DIAMOND(279, "diamondpicaxe"), GOLD(286, "goldpicaxe"), IRON(258, "ironpicaxe"), STONE(275, "stonepicaxe"), WOOD(271, "woodenpicaxe");
        private int ID;
        private String icon;
        
        private Picaxes(int ID, String icon)
        {
            this.ID = ID;
            this.icon =  getClass().getResource("/icons/" + icon + "_icon32.png").getPath();
        }
        
        public int getID()
        {
            return ID;
        }
        
        public String getIconPath()
        {
            return icon;
        }
    }
    
    static enum Shovels
    {
        DIAMOND(279, "diamondshovel"), GOLD(286, "goldshovel"), IRON(258, "ironshovel"), STONE(275, "stoneshovel"), WOOD(271, "woodenshovel");
        private int ID;
        private String icon;
        
        private Shovels(int ID, String icon)
        {
            this.ID = ID;
            this.icon =  getClass().getResource("/icons/" + icon + "_icon32.png").getPath();
        }
        
        public int getID()
        {
            return ID;
        }
        
        public String getIconPath()
        {
            return icon;
        }
    }
    
 
        
        
        
        
        
        
    
}
