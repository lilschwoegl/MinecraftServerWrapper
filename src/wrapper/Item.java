/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wrapper;

import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 *
 * @author Mike
 */
public class Item {
    
    private Tag t;
    
    private short ID;
    private short damage;
    private byte count;
    private byte slot;
    private String imagePath;
    private String name;
    private ImageIcon image;
    
    
    public Item(short ID, short damage, String name)
    {
        this(ID, (byte)0, (byte)0, damage, name);
    }
    
    public Item(short ID, byte count, byte slot, short damage, String name)
    {
        this.ID = ID;
        this.count = count;
        this.slot = slot;
        this.damage = damage;
        this.name = name;
        this.image = new ImageIcon(getClass().getResource("/icons/" + "bed" + "_icon32.png").getPath(), "");
        
        t = new Tag(Tag.Type.TAG_Compound, "Item", new Tag[] {new Tag(Tag.Type.TAG_Short, "ID", ID), 
            new Tag(Tag.Type.TAG_Byte, "Count", count), 
            new Tag(Tag.Type.TAG_Byte, "Slot", slot), 
            new Tag(Tag.Type.TAG_Short, "Damage", damage),
        new Tag(Tag.Type.TAG_String, "Name", name)});

    }
   
    
    public int getID()
    {
        return ID;
    }

    
    public short getDamage()
    {
        return damage;
    }
    
    public byte getCount()
    {
        return count;
    }
    
    public String getName()
    {
        return name;
    }
    
    public Image getImage()
    {
        return image.getImage();
    }
    
    @Override
    public String toString()
    {
        return ID + ": " + name;
    }
    
}
