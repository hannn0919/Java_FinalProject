package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.io.*;
import javax.sound.sampled.*;
import java.awt.event.MouseEvent;
import javax.swing.border.EmptyBorder;


public class EnterScreen extends JPanel{

    private Main mainFrame;
    private JButton btnNew,btnLoad;

    public EnterScreen(Main mainFrame){
        this.mainFrame = mainFrame;
        this.setSize(1200, 675);
        this.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setLayout(null);

        btnNew = new JButton();
        cleanButtom(btnNew);
        btnNew.setBounds(600, 300, 290, 100);
        btnNew.setIcon(new ImageIcon("data/main/開始遊戲.png"));
        this.add(btnNew);

        btnLoad = new JButton();
        cleanButtom(btnLoad);
        btnLoad.setBounds(600, 450, 255, 100);
        btnLoad.setIcon(new ImageIcon("data/main/載入遊戲小.png"));
        this.add(btnLoad);

        MouseHandler mouseHandler = new MouseHandler();
        btnNew.addMouseListener(mouseHandler);
        btnLoad.addMouseListener(mouseHandler);

        ImageIcon img = new ImageIcon("data/main/主畫面.png");
        Image i = img.getImage();
        i = i.getScaledInstance(1200, 675, Image.SCALE_SMOOTH);
        JLabel background = new JLabel();
        background.setIcon(new ImageIcon(i));
        background.setSize(1200, 675);
        this.add(background);
    }
    private class MouseHandler implements  MouseListener{
        @Override public void mousePressed(MouseEvent e){}
        @Override public void mouseReleased(MouseEvent e){}
        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getSource()==btnNew)
                mainFrame.stratDrama();
            else if(e.getSource()==btnLoad)
                mainFrame.changeToMainScreen();
        }
        @Override
        public void mouseEntered(MouseEvent e){
            if(e.getSource()==btnNew) {
                buttonSound();
                btnNew.setIcon(resize(btnNew.getIcon().getIconWidth()+10,btnNew.getIcon().getIconHeight()+10,(ImageIcon)btnNew.getIcon()));
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }else if(e.getSource()==btnLoad){
                buttonSound();
                btnLoad.setIcon(resize(btnLoad.getIcon().getIconWidth()+10,btnLoad.getIcon().getIconHeight()+10,(ImageIcon)btnLoad.getIcon()));
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        }
        @Override
        public void mouseExited(MouseEvent arg0) {
            if(arg0.getSource()==btnNew){
                btnNew.setIcon(new ImageIcon("data/main/開始遊戲.png"));
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            else if(arg0.getSource()==btnLoad) {
                btnLoad.setIcon(new ImageIcon("data/main/載入遊戲小.png"));
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        }
    }

    public ImageIcon resize(int width, int height, ImageIcon img) {
        Image i = img.getImage();
        Image new_img = i.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return  new ImageIcon(new_img);
    }

    public void buttonSound() {
        try {
            File soundFile = new File("music/enterbutton.wav");
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

    public void cleanButtom(JButton button) {
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorder(null);
    }

}
