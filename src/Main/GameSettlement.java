package Main;

import House.house.House;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameSettlement extends JPanel{
    private Main mainFrame;
    private House house;
    private ImageIcon image = new ImageIcon("data/main/byebyebyebye.png");
    private ImageIcon image2= new ImageIcon("data/main/backhome.png");
    public GameSettlement(Main mainFrame,House house){
        this.mainFrame=mainFrame;
        this.house=house;
        setLayout(null);
        JLabel label = new JLabel();
        label.setBounds(-10, -25, image.getIconWidth(), image.getIconHeight());
        label.setIcon(image);
        JLabel labelC2 = new JLabel(String.format(""+house.getTotalMoney(1)));
        JLabel labelC3 = new JLabel(String.format(""+house.getTotalPlay(1)));
        JLabel labelC4 = new JLabel(String.format(""+house.getTotalMistake(1)));
        JLabel labelC5 = new JLabel(String.format(""+house.getTotalExp(1)));

        JLabel labelH2 = new JLabel(String.format(""+house.getTotalMoney(2)));
        JLabel labelH3 = new JLabel(String.format(""+house.getTotalPlay(2)));
        JLabel labelH4 = new JLabel(String.format(""+house.getTotalMistake(2)));
        JLabel labelH5 = new JLabel(String.format(""+house.getTotalExp(2)));

        JLabel labelF2 = new JLabel(String.format(""+house.getTotalMoney(3)));
        JLabel labelF3 = new JLabel(String.format(""+house.getTotalPlay(3)));
        JLabel labelF4 = new JLabel(String.format(""+house.getTotalMistake(3)));
        JLabel labelF5 = new JLabel(String.format(""+house.getTotalExp(3)));

        JLabel labelD2 = new JLabel(String.format(""+house.getTotalMoney(4)));
        JLabel labelD3 = new JLabel(String.format(""+house.getTotalPlay(4)));
        JLabel labelD4 = new JLabel(String.format(""+house.getTotalMistake(4)));
        JLabel labelD5 = new JLabel(String.format(""+house.getTotalExp(4)));

        setLabelW(labelC2);
        setLabelW(labelC3);
        setLabelW(labelC4);
        setLabelW(labelC5);
        setLabelW(labelH2);
        setLabelW(labelH3);
        setLabelW(labelH4);
        setLabelW(labelH5);
        setLabelB(labelF2);
        setLabelB(labelF3);
        setLabelB(labelF4);
        setLabelB(labelF5);
        setLabelB(labelD2);
        setLabelB(labelD3);
        setLabelB(labelD4);
        setLabelB(labelD5);

        labelD2.setBounds(110,167,150,59);
        labelD3.setBounds(110,264,150,51);
        labelD4.setBounds(110,357,150,48);
        labelD5.setBounds(110,451,150,53);

        labelC2.setBounds(410,167,150,59);
        labelC3.setBounds(410,264,150,51);
        labelC4.setBounds(410,357,150,48);
        labelC5.setBounds(410,451,150,53);

        labelF2.setBounds(710,167,150,59);
        labelF3.setBounds(710,264,150,51);
        labelF4.setBounds(710,357,150,48);
        labelF5.setBounds(710,451,150,53);

        labelH2.setBounds(1020,167,150,59);
        labelH3.setBounds(1020,264,150,51);
        labelH4.setBounds(1020,357,150,48);
        labelH5.setBounds(1020,451,150,53);

        label.add(labelC2);
        label.add(labelC3);
        label.add(labelC4);
        label.add(labelC5);
        label.add(labelH2);
        label.add(labelH3);
        label.add(labelH4);
        label.add(labelH5);
        label.add(labelF2);
        label.add(labelF3);
        label.add(labelF4);
        label.add(labelF5);
        label.add(labelD2);
        label.add(labelD3);
        label.add(labelD4);
        label.add(labelD5);

        JButton backToMainButton = new JButton(image2);
        backToMainButton.setBounds(558,600 , image2.getIconWidth(), image2.getIconHeight());
        label.add(backToMainButton);
        backToMainButton.setBorderPainted(false);
        backToMainButton.setBorder(null);
        backToMainButton.setFocusPainted(false);
        backToMainButton.setContentAreaFilled(false);
        backToMainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.changeToMainScreen();
            }
        });
        add(label);
    }

    //設計道具按鈕
    private void setLabelB(JLabel j){
        j.setOpaque(false);
        j.setHorizontalAlignment(SwingConstants.RIGHT);
        j.setFont(new Font("微軟正黑體",Font.BOLD,30));
        j.setForeground( Color.BLACK);
    }

    private void setLabelW(JLabel j){
        j.setOpaque(false);
        j.setHorizontalAlignment(SwingConstants.RIGHT);
        j.setFont(new Font("微軟正黑體",Font.BOLD,30));
        j.setForeground( Color.WHITE);
    }
}
