package Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt .*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class drama extends JPanel{

    private Main mainFrame;
    int time = 4400;

    public drama(Main mainFrame) {

        Timer timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                time--;
                if(time == 0){
                    timer.cancel();
                    mainFrame.changeToMainScreen();
                }
            }
        }, 0, 10);

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
                timer.cancel();
                mainFrame.changeToMainScreen();
            }
        });
        add(draBtn);


    }

}