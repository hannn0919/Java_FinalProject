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
    private FroggerPanel froggerPanel;

    private ImageIcon backToMainImage;
    private ImageIcon disCountImage;
    private ImageIcon expandMoneyImage;
    private ImageIcon ruleImage;
    private ImageIcon testCharacterImage;

    private ImageIcon startImg;
    private ImageIcon staticsImg;
    private ImageIcon checkImg;
    private ImageIcon introduceImg;
    private JButton checkBtn;
    private JButton startBtn;
    private JLabel introduce;

    private JLabel backGroundLabel;
    private JButton backToMainButton;
    private JLabel disCountLabel;
    private JLabel expandMoneyLabel;
    private JLabel ruleLabel;
    private JLabel testCharacterLabel;
    private JLabel expFromMain;
    private JLabel moneyFromMain;

    private JLabel statics;
    private JLabel static_pxp, static_money;

    private JCheckBox expCard, moneyCard, policeCard;

    private Timer timer;
    private int time;
    private JLabel timeLabel;

    public FrogJLayer(Main frame, House house){
        this.frame = frame;
        this.house = house;

        Handler handle = new Handler();

        expCard = new JCheckBox("經驗加倍卡");
        expCard.setFont(new Font("微軟正黑體", Font.PLAIN, 23));

        moneyCard = new JCheckBox("金錢加倍卡");
        moneyCard.setFont(new Font("微軟正黑體", Font.PLAIN, 23));

        policeCard = new JCheckBox("警察卡");
        policeCard.setFont(new Font("微軟正黑體", Font.PLAIN, 23));

        startImg = new ImageIcon("data/dinosaur/開始遊戲按鈕.png");
        startBtn = new JButton(startImg);
        startBtn.setBounds(660, 510, 140, 50);
        startBtn.addActionListener(handle);
        add(startBtn, JLayeredPane.DEFAULT_LAYER);

        expCard.setBounds(500, 460, 150, 30);
        add(expCard, JLayeredPane.DEFAULT_LAYER);

        moneyCard.setBounds(680, 460, 150, 30);
        add(moneyCard, JLayeredPane.DEFAULT_LAYER);

        policeCard.setBounds(860, 460, 150, 30);
        add(policeCard, JLayeredPane.DEFAULT_LAYER);

        introduceImg = new ImageIcon("data/dinosaur/introduce.png");
        introduce = new JLabel(introduceImg);
        introduce.setBounds(400, 99, 676, 476);
        add(introduce, JLayeredPane.DEFAULT_LAYER);

        checkImg = new ImageIcon("data/dinosaur/OK鍵.png");
        checkBtn = new JButton(checkImg);
        checkBtn.setBounds(630, 490, 52, 50);
        checkBtn.addActionListener(handle);
        add(checkBtn, JLayeredPane.DEFAULT_LAYER);
        checkBtn.setVisible(false);

        static_pxp = new JLabel(String.valueOf(house.getExp()));
        static_pxp.setFont(new Font("微軟正黑體",Font.BOLD,20));
        static_pxp.setBounds(617, 238, 150,40);
        add(static_pxp, JLayeredPane.DEFAULT_LAYER);
        static_pxp.setVisible(false);

        static_money = new JLabel("1243");
        static_money.setFont(new Font("微軟正黑體",Font.BOLD,20));
        static_money.setBounds(617, 389, 150,40);
        add(static_money, JLayeredPane.DEFAULT_LAYER);
        static_money.setVisible(false);

        staticsImg = new ImageIcon("data/dinosaur/遊戲結算.png");
        statics = new JLabel(staticsImg);
        statics.setBounds(400, 99, 504,476);
        add(statics, JLayeredPane.DEFAULT_LAYER);
        statics.setVisible(false);


        froggerPanel = new FroggerPanel(frame,house);
        froggerPanel.setBounds(300,0,1200,675);
        add(froggerPanel,JLayeredPane.DEFAULT_LAYER);

        timeLabel = new JLabel(String.valueOf(time/100));
        timeLabel.setFont(new Font("微軟正黑體", Font.BOLD, 32));
        //計時60秒


        backToMainImage = new ImageIcon("data/gamebar/backhome.png");
        backToMainButton = new JButton(backToMainImage);
        backToMainButton.setBounds(0,0,backToMainImage.getIconWidth(),backToMainImage.getIconHeight());
        add(backToMainButton,JLayeredPane.DEFAULT_LAYER);
        backToMainButton.setBorderPainted(false);
        backToMainButton.setBorder(null);
        backToMainButton.setFocusPainted(false);
        backToMainButton.setContentAreaFilled(false);
        backToMainButton.addActionListener(handle);

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
                    checkBtn.setVisible(true);
                    statics.setVisible(true);
                    static_pxp.setVisible(true);
                    static_money.setVisible(true);
                    backToMainButton.setEnabled(true);
                }else {
                    repaint();
                }
            }
        }, 0, 10);

    }

    private class Handler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == startBtn) {
                remove(expCard);
                remove(moneyCard);
                remove(policeCard);
                remove(startBtn);
                remove(introduce);
                backToMainButton.setEnabled(false);
                froggerPanel.gameStart();
                froggerPanel.requestFocus();;

            } else if (e.getSource() == backToMainButton) {
                frame.changeToMainScreen();
            } else if (e.getSource() == checkBtn) {
                frame.changeToMainScreen();
            }
        }
    }
}
