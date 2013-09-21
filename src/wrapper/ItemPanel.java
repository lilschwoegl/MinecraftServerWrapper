/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wrapper;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureRecognizer;
import java.awt.dnd.DragSource;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import javax.swing.JPanel;

/**
 *
 * @author Mike
 */
public class ItemPanel extends JPanel implements MouseListener, MouseMotionListener{
    
    private Item item;
    MouseListener ml;

    protected Point anchorPoint;
    
    public ItemPanel()
    {
        this(new Item((byte)0, (byte)0, "Air"));
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    public ItemPanel(Item item)
    {
        this.item = item;
        
        this.setSize(64, 64);
        this.setVisible(true);
    }
    
    public ItemPanel(ItemPanel panel)
    {
        this(panel.getItem());
    }
    
    public Item getItem()
    {
        return item;
    }
    
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        g.drawImage(item.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
        g.setColor(Color.green);
        g.fillRect(4, this.getHeight() - 8, this.getWidth() - 8, 4);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        print("Mouse Clicked");
    }

    @Override
    public void mousePressed(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void print(String text)
    {
        System.out.println(text);
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                ItemPanelDragController.panel = this;
        
                int anchorX = anchorPoint.x;
                int anchorY = anchorPoint.y;

                Point parentOnScreen = getParent().getLocationOnScreen();
                Point mouseOnScreen = me.getLocationOnScreen();
                Point position = new Point(mouseOnScreen.x - parentOnScreen.x - 
		anchorX, mouseOnScreen.y - parentOnScreen.y - anchorY);
                setLocation(position);
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        anchorPoint = me.getPoint();
        
    }
    
    
}
