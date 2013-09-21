/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wrapper;

/**
 *
 * @author Mike
 */
public class ItemPanelDragController {
    
    public static ItemPanel panel;
    public static Main parent;
    
    public ItemPanelDragController(Main parent)
    {
        this.parent = parent;
    }
    
    public ItemPanelDragController(ItemPanel panel)
    {
        this.panel = panel;
    }
    
    public ItemPanel getPanel()
    {
        return panel;
    }
    
    public void setPanel(ItemPanel panel)
    {
        this.panel = panel;
    }
}
