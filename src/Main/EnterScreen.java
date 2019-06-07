package Main;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.sound.sampled.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.EmptyBorder;


public class EnterScreen extends JPanel{

    private Main mainFrame;

    public EnterScreen(Main mainFrame){
        this.mainFrame = mainFrame;
        this.setSize(1200, 675);
        this.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setLayout(null);

        JButton btnNew = new JButton();
        cleanButtom(btnNew);
        btnNew.setBounds(600, 300, 290, 100);
        btnNew.setIcon(new ImageIcon("data/main/開始遊戲.png"));
        btnNew.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainFrame.changeToMainScreen();
            }
            @Override
            public void mouseEntered(MouseEvent e){
                buttonSound();
                btnNew.setToolTipText("这是按钮");
                ToolTipManager.sharedInstance().setDismissDelay(5000);// 设置为5秒
                btnNew.setIcon(resize(btnNew.getIcon().getIconWidth()+10,btnNew.getIcon().getIconHeight()+10,(ImageIcon)btnNew.getIcon()));
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent arg0) {
                btnNew.setIcon(new ImageIcon("data/main/開始遊戲.png"));
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

        });
        this.add(btnNew);


        JButton btnLoad = new JButton();
        cleanButtom(btnLoad);
        btnLoad.setBounds(600, 450, 255, 100);
        btnLoad.setIcon(new ImageIcon("data/main/載入遊戲小.png"));
        btnLoad.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainFrame.changeToMainScreen();
            }
        });
        this.add(btnLoad);

        ImageIcon img = new ImageIcon("data/main/主畫面.png");
        Image i = img.getImage();
        i = i.getScaledInstance(1200, 675, Image.SCALE_SMOOTH);
        JLabel background = new JLabel();
        background.setIcon(new ImageIcon(i));
        background.setSize(1200, 675);
        this.add(background);
    }

    public ImageIcon resize(int width, int height, ImageIcon img) {
        Image i = img.getImage();
        Image new_img = i.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return  new ImageIcon(new_img);
    }

    public void buttonSound() {
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

    public void cleanButtom(JButton button) {
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorder(null);
    }

}
