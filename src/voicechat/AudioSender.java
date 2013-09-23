/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package voicechat;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

/**
 *
 * @author Mike
 */
public class AudioSender extends Thread {

    ServerSocket serverSocket;
    BufferedInputStream in;
    TargetDataLine targetDataLine;
    BufferedOutputStream out;
    ByteArrayOutputStream byteArrayOut;
    AudioFormat audioFormat;
    SourceDataLine sourceDataLine;
    byte tempBuffer[] = new byte[1000];
    Socket clientSocket;

    public AudioSender() throws LineUnavailableException {
        this(4444);
    }

    public AudioSender(int port) throws LineUnavailableException {
        System.out.println("created");
        try {
            audioFormat = getAudioFormat();
            DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);
            sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
            sourceDataLine.open(audioFormat);
            sourceDataLine.start();
            
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            
            captureAudio();
            
            in = new BufferedInputStream(clientSocket.getInputStream());
            out = new BufferedOutputStream(clientSocket.getOutputStream());
            // set up interaction w/clients

            SenderListener sl = new SenderListener(in, sourceDataLine);
            sl.start();
            
            //while (in.read(tempBuffer) != -1) {
            //    sourceDataLine.write(tempBuffer, 0, 1000);
            //}
        } catch (IOException e) {
            e.printStackTrace();
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

    private void captureAudio() throws LineUnavailableException {
        Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo();

        for (int i = 0; i < mixerInfo.length; i++) {
            // Print info somewhere?..
        }

        audioFormat = getAudioFormat();

        DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
        Mixer mixer = AudioSystem.getMixer(mixerInfo[3]);
        targetDataLine = (TargetDataLine) mixer.getLine(dataLineInfo);
        targetDataLine.open();
        targetDataLine.start();

    }

    @Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        byte tempBuffer[] = new byte[1000];

        try {
            while (true) {
                int cnt = targetDataLine.read(tempBuffer, 0,
                        tempBuffer.length);

                out.write(tempBuffer);
            }

        } catch (Exception e) {
        }

    }
}
