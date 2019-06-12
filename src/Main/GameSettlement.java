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

    public static void main(String[] Args){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 675);

    }
    //public GameSettlement(Main mainFrame,House house){
    public GameSettlement(Main mainFrame,House house){
        this.mainFrame=mainFrame;
        this.house=house;
        setLayout(new GridLayout(10,1));
        JLabel label = new JLabel("遊戲結算");
        JLabel label2 = new JLabel("消滅一對是一對");
        JLabel label3 = new JLabel("得到總經驗值:");
        JLabel label4 = new JLabel("得到總金錢:");
        JLabel label5 = new JLabel("總遊玩次數:");
        JLabel label6 = new JLabel("總找到對數:");
        setLabel(label);
        setLabel(label2);
        setLabel(label3);
        setLabel(label4);
        setLabel(label5);
        setLabel(label6);
        add(label);
        add(label2);
        add(label3);
        add(label4);
        add(label5);
        add(label6);
    }

    //設計道具按鈕
    private void setLabel(JLabel j){
        j.setOpaque(true);
        j.setFont(new Font("微軟正黑體",Font.BOLD,30));
        j.setForeground(new Color(0,0,128));
    }
}
