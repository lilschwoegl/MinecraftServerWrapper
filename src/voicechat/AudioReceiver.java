/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package voicechat;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.SourceDataLine;

import javax.sound.sampled.TargetDataLine;

/**
 *
 * @author Mike
 */
public class AudioReceiver extends Thread {

    boolean stopCapture = false;
    ByteArrayOutputStream byteArrayOutputStream;
    AudioFormat audioFormat;
    TargetDataLine targetDataLine;
    AudioInputStream audioInputStream;
    BufferedOutputStream out;
    BufferedInputStream in;
    Socket socket;
    SourceDataLine sourceDataLine;
    
    public AudioReceiver()
    {
        this("localhost", 4444);
    }
    
    public AudioReceiver(int port)
    {
        this("localhost", port);
    }
    
    public AudioReceiver(String host, int port)
    {
        try {
            socket = new Socket(host, port);
            out = new BufferedOutputStream(socket.getOutputStream());
            in = new BufferedInputStream(socket.getInputStream());
            
            Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo();
            audioFormat = getAudioFormat();
            
            DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
            Mixer mixer = AudioSystem.getMixer(mixerInfo[3]);
            targetDataLine = (TargetDataLine) mixer.getLine(dataLineInfo);
            targetDataLine.open(audioFormat);
            targetDataLine.start();
            
            DataLine.Info dataLineInfo2 = new DataLine.Info(SourceDataLine.class, audioFormat);
            sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
            sourceDataLine.open(audioFormat);
            sourceDataLine.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    
    private AudioFormat getAudioFormat() {
        float sampleRate = 8000.0F;
        int sampleSizeBits = 8;
        int channels = 1;
        boolean signed = true;
        boolean bigEndian = false;

        return new AudioFormat(sampleRate, sampleSizeBits, channels, signed, bigEndian);
    }

    @Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        byte[] tempBuffer = new byte[1000];
        byteArrayOutputStream = new ByteArrayOutputStream();
        stopCapture = false;

        try {
            while (!stopCapture) {
                int cnt = targetDataLine.read(tempBuffer, 0, tempBuffer.length);
                out.write(tempBuffer);

                if (cnt > 0) {
                    byteArrayOutputStream.write(tempBuffer, 0, cnt);
                }

            }
            byteArrayOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
