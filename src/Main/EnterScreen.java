package Main;

import javax.swing.*;
import java.awt.*;
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


    public void cleanButtom(JButton button) {
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorder(null);
    }


}
