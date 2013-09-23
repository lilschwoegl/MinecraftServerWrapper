/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package voicechat;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.SourceDataLine;

/**
 *
 * @author Mike
 */
public class SenderListener extends Thread{

    byte[] tempBuffer = new byte[1000];
    BufferedInputStream in;
    SourceDataLine src;
    
    public SenderListener(BufferedInputStream in, SourceDataLine src)
    {
        this.in = in;
        this.src = src;
    }
        
    @Override
    public void run() {
        try {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            while (in.read(tempBuffer) != -1)
            {
                src.write(tempBuffer, 0, 1000);
            }
        } catch (IOException ex) {
            Logger.getLogger(SenderListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
