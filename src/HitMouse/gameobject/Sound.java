package HitMouse.gameobject;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
public class Sound {
    private static Clip hitSound;
    private static Clip kissSound;
    public static void HitSound(){
        try {
            File soundFile = new File("data/music/hit.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            hitSound = AudioSystem.getClip();
            hitSound.open(audioIn);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        hitSound.start();
    }
    public static void buttonSound() {
        try {
            File soundFile = new File("music/buttonClicked.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();

        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    public static void KissSound(){
        try {
            File soundFile = new File("data/music/kiss.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            kissSound = AudioSystem.getClip();
            kissSound.open(audioIn);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        kissSound.start();
    }
}
