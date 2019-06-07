package Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt .*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class drama extends JPanel{

    private Main mainFrame;

    public drama(Main mainFrame) {
        this.mainFrame = mainFrame;
        this.setSize(1200, 675);
        this.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setLayout(null);


        Icon startDrama = new ImageIcon("data/main/tmp.gif");
        JButton draBtn = new JButton(startDrama);
        draBtn.setBounds(0, -20, 1200, 675);
        draBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //點選後進入登入畫面
                mainFrame.changeToEnterScreen();
            }
        });
        add(draBtn);
    }

}