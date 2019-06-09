package Frogger.main;

import House.house.House;
import Main.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;


public class FrogJLayer extends JLayeredPane {
    private Main frame;
    private House house;

    private ImageIcon backToMainImage;
    private ImageIcon disCountImage;
    private ImageIcon expandMoneyImage;
    private ImageIcon ruleImage;
    private ImageIcon testCharacterImage;

    private JLabel backGroundLabel;
    private JButton backToMainButton;
    private JLabel disCountLabel;
    private JLabel expandMoneyLabel;
    private JLabel ruleLabel;
    private JLabel testCharacterLabel;
    private JLabel expFromMain;
    private JLabel moneyFromMain;

    private java.util.Timer timer;
    private int time = 6000;
    private JLabel timeLabel;

    public FrogJLayer(Main frame, House house){
        this.frame = frame;
        this.house = house;
        FroggerPanel froggerPanel = new FroggerPanel(frame,house);
        froggerPanel.setBounds(300,0,1200,675);
        add(froggerPanel,JLayeredPane.DEFAULT_LAYER);
        addKeyListener(froggerPanel);

        timeLabel = new JLabel(String.valueOf(time/100));
        timeLabel.setFont(new Font("微軟正黑體", Font.BOLD, 32));
        //計時60秒
        timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                time = froggerPanel.time;
                timeLabel.setText(String.valueOf(time/100));
                timeLabel.setBounds(173,76,300,100);
                add(timeLabel,JLayeredPane.PALETTE_LAYER);
                if(time == 0){
                    timer.cancel();
                }else {
                    repaint();
                }
            }
        }, 0, 10);

        backToMainImage = new ImageIcon("data/gamebar/backhome.png");
        backToMainButton = new JButton(backToMainImage);
        backToMainButton.setBounds(0,0,backToMainImage.getIconWidth(),backToMainImage.getIconHeight());
        add(backToMainButton,JLayeredPane.DEFAULT_LAYER);
        backToMainButton.setBorderPainted(false);
        backToMainButton.setBorder(null);
        backToMainButton.setFocusPainted(false);
        backToMainButton.setContentAreaFilled(false);
        backToMainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                froggerPanel.time = 0;
                frame.changeToMainScreen();
            }
        });

        disCountImage = new ImageIcon("data/gamebar/discount.png");
        disCountLabel = new JLabel(disCountImage);
        disCountLabel.setBounds(0,backToMainImage.getIconHeight(),disCountImage.getIconWidth(),disCountImage.getIconHeight());
        add(disCountLabel,JLayeredPane.DEFAULT_LAYER);

        ruleImage = new ImageIcon("data/gamebar/rule.png");
        ruleLabel = new JLabel(ruleImage);
        ruleLabel.setBounds(0,backToMainImage.getIconHeight()+disCountImage.getIconHeight(),ruleImage.getIconWidth(),ruleImage.getIconHeight());
        add(ruleLabel,JLayeredPane.DEFAULT_LAYER);

        expandMoneyImage = new ImageIcon("data/gamebar/expandmoney.png");
        expandMoneyLabel = new JLabel(expandMoneyImage);
        expandMoneyLabel.setBounds(0,backToMainImage.getIconHeight()+disCountImage.getIconHeight()+ruleImage.getIconHeight(),expandMoneyImage.getIconWidth(),expandMoneyImage.getIconHeight());
        add(expandMoneyLabel,JLayeredPane.DEFAULT_LAYER);

        int heightTotal=backToMainImage.getIconHeight()+disCountImage.getIconHeight()+ruleImage.getIconHeight();
        expFromMain = new JLabel("");
        expFromMain.setFont(new Font("微軟正黑體",Font.BOLD,17));
        expFromMain.setBounds(140,heightTotal+13,150,25);
        expFromMain.setText(Integer.toString(house.getExp()));
        add(expFromMain,JLayeredPane.MODAL_LAYER);

        moneyFromMain = new JLabel("");
        moneyFromMain.setFont(new Font("微軟正黑體",Font.BOLD,17));
        moneyFromMain.setBounds(140,heightTotal+50,150,25);
        moneyFromMain.setText(Integer.toString(house.getHoldMoney()));
        add(moneyFromMain,JLayeredPane.MODAL_LAYER);
    }
}
