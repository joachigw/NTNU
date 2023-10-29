package edu.ntnu.idatt2001.mappevurdering.ej;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class PlaySFX {
    // her skrives det tekst
    static void playSound(File sound){
        try{
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(sound));
            clip.start();

            Thread.sleep(clip.getMicrosecondLength()/1000);

        }catch(Exception e){

        }
    }
}
