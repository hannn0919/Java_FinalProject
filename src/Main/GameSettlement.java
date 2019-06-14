package Main;

import House.house.House;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.io.*;
import java.awt.event.MouseEvent;
public class GameSettlement extends JPanel{
    private Main mainFrame;
    private House house;
    private ImageIcon pair=new ImageIcon("data//main/pair.png");
    private ImageIcon EXP=new ImageIcon("data//main/gameexp.png");
    private ImageIcon gamePlay=new ImageIcon("data//main/gameplay.png");
    private ImageIcon money=new ImageIcon("data//main/一袋錢.png");
    private ImageIcon death=new ImageIcon("data//main/skullicon.png");
    public static void main(String[] Args){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 675);

    }
    public GameSettlement(Main mainFrame,House house){
        this.mainFrame=mainFrame;
        this.house=house;
        setLayout(null);
        JLabel labelC2 = new JLabel(String.format(""+house.getTotalExp(1)));
        JLabel labelC3 = new JLabel(String.format(""+house.getTotalMoney(1)));
        JLabel labelC4 = new JLabel(String.format(""+house.getTotalPlay(1)));
        JLabel labelC5 = new JLabel(String.format(""+house.getTotalMistake(1)));

        JLabel labelH2 = new JLabel(String.format(""+house.getTotalExp(2)));
        JLabel labelH3 = new JLabel(String.format(""+house.getTotalMoney(2)));
        JLabel labelH4 = new JLabel(String.format(""+house.getTotalPlay(2)));
        JLabel labelH5 = new JLabel(String.format(""+house.getTotalMistake(2)));

        JLabel labelF2 = new JLabel(String.format(""+house.getTotalExp(3)));
        JLabel labelF3 = new JLabel(String.format(""+house.getTotalMoney(3)));
        JLabel labelF4 = new JLabel(String.format(""+house.getTotalPlay(3)));
        JLabel labelF5 = new JLabel(String.format(""+house.getTotalMistake(3)));

        JLabel labelD2 = new JLabel(String.format(""+house.getTotalExp(4)));
        JLabel labelD3 = new JLabel(String.format(""+house.getTotalMoney(4)));
        JLabel labelD4 = new JLabel(String.format(""+house.getTotalPlay(4)));
        JLabel labelD5 = new JLabel(String.format(""+house.getTotalMistake(4)));
        setLabel(labelC2);
        setLabel(labelC3);
        setLabel(labelC4);
        setLabel(labelC5);
        setLabel(labelH2);
        setLabel(labelH3);
        setLabel(labelH4);
        setLabel(labelH5);
        setLabel(labelF2);
        setLabel(labelF3);
        setLabel(labelF4);
        setLabel(labelF5);
        setLabel(labelD2);
        setLabel(labelD3);
        setLabel(labelD4);
        setLabel(labelD5);

        labelC2.setBounds(101,167,174,59);

        add(labelC2);
        /*add(labelC3);
        add(labelC4);
        add(labelC5);
        add(labelH2);
        add(labelH3);
        add(labelH4);
        add(labelH5);
        add(labelF2);
        add(labelF3);
        add(labelF4);
        add(labelF5);
        add(labelD2);
        add(labelD3);
        add(labelD4);
        add(labelD5);*/

        JButton back =new JButton("返回按鈕");
        //add(back,BorderLayout.SOUTH);

        ImageIcon img = new ImageIcon("data/main/byebyebyebye.png");
        Image i = img.getImage();
        i = i.getScaledInstance(1200, 675, Image.SCALE_SMOOTH);
        JLabel background = new JLabel();
        background.setIcon(new ImageIcon(i));
        background.setSize(1200, 675);
        this.add(background);
    }

    //設計道具按鈕
    private void setLabel(JLabel j){
        j.setOpaque(true);
        j.setFont(new Font("微軟正黑體",Font.BOLD,30));
        j.setForeground(new Color(0,0,128));
    }
}
