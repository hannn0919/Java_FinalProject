package Dinosaur.userinterface;
import House.house.House;
import Main.Main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

public class DinoJLayer extends JLayeredPane {
    private Main frame;
    private House house;
    private GameScreen game;

    private ImageIcon backToMainImage;
    private ImageIcon disCountImage;
    private ImageIcon expandMoneyImage;
    private ImageIcon ruleImage;
    private ImageIcon introduceImg;

    private ImageIcon startImg;
    private ImageIcon staticsImg;
    private ImageIcon checkImg;
    private JButton checkBtn;
    private JButton startBtn;
    private JLabel introduce;
    private JLabel backGroundLabel;
    private JButton backToMainButton;
    private JLabel disCountLabel;
    private JLabel expandMoneyLabel;
    private JLabel ruleLabel;
    private JLabel endlabel;
    private JLabel expFromMain, moneyFromMain;
    private JLabel statics;
    private JLabel static_pxp, static_money;

    private JCheckBox expcard, moneycard, prop;

    private Timer timer;
    private int time = 6000;
    private JLabel timeLabel;

    public DinoJLayer(Main frame, House house) {
        this.frame = frame;
        this.house = house;

        Handler handle = new Handler();

        expcard = new JCheckBox("經驗加倍卡");
        expcard.setFont(new Font("微軟正黑體", Font.PLAIN, 23));

        moneycard = new JCheckBox("金錢加倍卡");
        moneycard.setFont(new Font("微軟正黑體", Font.PLAIN, 23));

        prop = new JCheckBox("電蚊拍");
        prop.setFont(new Font("微軟正黑體", Font.PLAIN, 23));

        startImg = new ImageIcon("data/dinosaur/開始遊戲按鈕.png");
        startBtn = new JButton(startImg);
        startBtn.setBounds(660, 510, 140, 50);
        startBtn.addActionListener(handle);
        add(startBtn, JLayeredPane.DEFAULT_LAYER);

        expcard.setBounds(500, 460, 150, 30);
        add(expcard, JLayeredPane.DEFAULT_LAYER);

        moneycard.setBounds(680, 460, 150, 30);
        add(moneycard, JLayeredPane.DEFAULT_LAYER);

        prop.setBounds(860, 460, 150, 30);
        add(prop, JLayeredPane.DEFAULT_LAYER);

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

        static_pxp = new JLabel("1242346");
        static_pxp.setFont(new Font("Hollywood Hills",Font.BOLD,20));
        static_pxp.setBounds(617, 238, 150,40);
        add(static_pxp, JLayeredPane.DEFAULT_LAYER);
        static_pxp.setVisible(false);

        static_money = new JLabel("1243");
        static_money.setFont(new Font("Hollywood Hills",Font.BOLD,20));
        static_money.setBounds(617, 389, 150,40);
        add(static_money, JLayeredPane.DEFAULT_LAYER);
        static_money.setVisible(false);

        staticsImg = new ImageIcon("data/dinosaur/遊戲結算.png");
        statics = new JLabel(staticsImg);
        statics.setBounds(400, 99, 504,476);
        add(statics, JLayeredPane.DEFAULT_LAYER);
        statics.setVisible(false);


        game = new GameScreen(frame, house);
        game.setBounds(300, 0, 1200, 675);
        add(game, JLayeredPane.DEFAULT_LAYER);

        timeLabel = new JLabel(String.valueOf(time / 100));
        timeLabel.setFont(new Font("微軟正黑體", Font.BOLD, 32));
        //計時60秒
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                time = game.time;
                timeLabel.setText(String.valueOf(time / 100));
                timeLabel.setBounds(173, 76, 300, 100);
                add(timeLabel, JLayeredPane.PALETTE_LAYER);
                repaint();
                if(time ==0){
                    timer.cancel();
                    checkBtn.setVisible(true);
                    statics.setVisible(true);
                    static_pxp.setVisible(true);
                    static_money.setVisible(true);
                    backToMainButton.setEnabled(true);
                }
            }
        }, 0, 10);

        backToMainImage = new ImageIcon("data/gamebar/backhome.png");
        backToMainButton = new JButton(backToMainImage);
        backToMainButton.setBounds(0, 0, backToMainImage.getIconWidth(), backToMainImage.getIconHeight());
        add(backToMainButton, JLayeredPane.DEFAULT_LAYER);
        backToMainButton.setBorderPainted(false);
        backToMainButton.setBorder(null);
        backToMainButton.setFocusPainted(false);
        backToMainButton.setContentAreaFilled(false);
        backToMainButton.addActionListener(handle);

        disCountImage = new ImageIcon("data/gamebar/discount.png");
        disCountLabel = new JLabel(disCountImage);
        disCountLabel.setBounds(0, backToMainImage.getIconHeight(), disCountImage.getIconWidth(), disCountImage.getIconHeight());
        add(disCountLabel, JLayeredPane.DEFAULT_LAYER);

        ruleImage = new ImageIcon("data/gamebar/rule.png");
        ruleLabel = new JLabel(ruleImage);
        ruleLabel.setBounds(0, backToMainImage.getIconHeight() + disCountImage.getIconHeight(), ruleImage.getIconWidth(), ruleImage.getIconHeight());
        add(ruleLabel, JLayeredPane.DEFAULT_LAYER);

        expandMoneyImage = new ImageIcon("data/gamebar/expandmoney.png");
        expandMoneyLabel = new JLabel(expandMoneyImage);
        expandMoneyLabel.setBounds(0, backToMainImage.getIconHeight() + disCountImage.getIconHeight() + ruleImage.getIconHeight(), expandMoneyImage.getIconWidth(), expandMoneyImage.getIconHeight());
        add(expandMoneyLabel, JLayeredPane.DEFAULT_LAYER);

        int heightTotal=0;
        heightTotal+=backToMainImage.getIconHeight();
        heightTotal+=disCountImage.getIconHeight();
        heightTotal+=ruleImage.getIconHeight();

        expFromMain = new JLabel("");
        expFromMain.setFont(new Font("Hollywood Hills",Font.BOLD,17));
        expFromMain.setBounds(140,heightTotal+13,150,25);
        expFromMain.setText(Integer.toString(house.getExp()));
        add(expFromMain,JLayeredPane.MODAL_LAYER);

        moneyFromMain = new JLabel("");
        moneyFromMain.setFont(new Font("Hollywood Hills",Font.BOLD,17));
        moneyFromMain.setBounds(140,heightTotal+50,150,25);
        moneyFromMain.setText(Integer.toString(house.getHoldMoney()));
        add(moneyFromMain,JLayeredPane.MODAL_LAYER);
    }

    private class Handler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == startBtn) {
                remove(expcard);
                remove(moneycard);
                remove(prop);
                remove(startBtn);
                remove(introduce);
                backToMainButton.setEnabled(false);
                game.startGame();
                game.requestFocus();;

            } else if (e.getSource() == backToMainButton) {
                frame.changeToMainScreen();
            } else if (e.getSource() == checkBtn) {
                frame.changeToMainScreen();
            }
        }
    }


}
