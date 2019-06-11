package Frogger.main;

import House.house.House;
import Main.Main;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import static com.sun.java.accessibility.util.AWTEventMonitor.addItemListener;


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


    private JButton backToMainButton;
    private JLabel disCountLabel;
    private JLabel expandMoneyLabel;
    private JLabel ruleLabel;
    private JLabel testCharacterLabel;
    private JLabel expFromMain;
    private JLabel moneyFromMain;

    private JLabel statics;
    private JLabel static_pxp, static_money;

    private JCheckBox expCard, moneyCard, policeCard, underKey;

    private Timer timer;
    private int time;
    private JLabel timeLabel;

    public FrogJLayer(Main frame, House house){
        this.frame = frame;
        this.house = house;

        Handler handle = new Handler();
        UseItem useItem = new UseItem();


        expCard = new JCheckBox("經驗加倍卡");
        Itemset(expCard);
        if(house.getItem("經驗加倍券")<=0) expCard.setEnabled(false);

        moneyCard = new JCheckBox("金錢加倍卡");
        Itemset(moneyCard);
        if(house.getItem("金錢加倍卡")<=0) moneyCard.setEnabled(false);

        policeCard = new JCheckBox("警察卡");
        Itemset(policeCard);
        if(house.getItem("警察卡")<=0) policeCard.setEnabled(false);

        underKey = new JCheckBox("地下道鑰匙");
        Itemset(underKey);
        if(house.getItem("地下道鑰匙")<=0) underKey.setEnabled(false);



        startImg = new ImageIcon("data/dinosaur/開始遊戲按鈕.png");
        startBtn = new JButton(startImg);
        startBtn.setBounds(660, 510, 140, 50);
        startBtn.addActionListener(handle);
        add(startBtn, JLayeredPane.DEFAULT_LAYER);

        expCard.setBounds(600, 430, 150, 30);
        add(expCard, JLayeredPane.DEFAULT_LAYER);
        expCard.addItemListener(useItem);

        moneyCard.setBounds(800, 430, 150, 30);
        add(moneyCard, JLayeredPane.DEFAULT_LAYER);
        moneyCard.addItemListener(useItem);

        policeCard.setBounds(600, 460, 150, 30);
        add(policeCard, JLayeredPane.DEFAULT_LAYER);
        policeCard.addItemListener(useItem);

        underKey.setBounds(800, 460, 150, 30);
        add(underKey, JLayeredPane.DEFAULT_LAYER);
        underKey.addItemListener(useItem);

        introduceImg = new ImageIcon("data/Frogger/image/遊戲規則.png");
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

        static_money = new JLabel("");
        static_money.setFont(new Font("微軟正黑體",Font.BOLD,20));
        static_money.setBounds(617, 389, 150,40);
        add(static_money, JLayeredPane.DEFAULT_LAYER);
        static_money.setVisible(false);

        staticsImg = new ImageIcon("data/dinosaur/遊戲結算.png");
        statics = new JLabel(staticsImg);
        statics.setBounds(400, 99, 504,476);
        add(statics, JLayeredPane.DEFAULT_LAYER);
        statics.setVisible(false);


        MouseHandler mouseHandler = new MouseHandler();
        cleanButtom(checkBtn);
        cleanButtom(startBtn);
        checkBtn.addMouseListener(mouseHandler);
        startBtn.addMouseListener(mouseHandler);


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
        backToMainButton.addMouseListener(mouseHandler);

        disCountImage = new ImageIcon("data/gamebar/discount.png");
        disCountLabel = new JLabel(disCountImage);
        disCountLabel.setBounds(0,backToMainImage.getIconHeight(),disCountImage.getIconWidth(),disCountImage.getIconHeight());
        add(disCountLabel,JLayeredPane.DEFAULT_LAYER);

        ruleImage = new ImageIcon("data/Frogger/image/rule.png");
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
                    int gainExp , gainMoney;
                    gainExp = house.getExp() + time / 6 / house.getLevel();
                    if(froggerPanel.endTime > 3000){
                        gainMoney = 3000;
                    }else if(froggerPanel.endTime > 1000){
                        gainMoney = 2500;
                    }else if(froggerPanel.endTime > 99){
                        gainMoney = 2000;
                    }else {
                        gainExp = 200;
                        gainMoney = 1000;
                    }
                    if(froggerPanel.expCard == true) gainExp *= 2;
                    if(froggerPanel.moneyCard == true) gainMoney *= 2;
                    static_pxp.setText(String.valueOf(gainExp));
                    static_money.setText(String.valueOf(gainMoney));
                    house.setExp(house.getExp()+gainExp);
                    house.setHoldMoney(house.getHoldMoney()+gainMoney);

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

    private class MouseHandler implements MouseListener {
        @Override public void mousePressed(MouseEvent e){
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
        @Override public void mouseReleased(MouseEvent e){}
        @Override
        public void mouseClicked(MouseEvent e) {}
        @Override
        public void mouseEntered(MouseEvent e){
            if(e.getSource()==checkBtn) {
                buttonSound();
                checkBtn.setIcon(resize(checkBtn.getIcon().getIconWidth()+10,checkBtn.getIcon().getIconHeight()+10,(ImageIcon)checkBtn.getIcon()));
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }else if(e.getSource()==startBtn){
                buttonSound();
                startBtn.setIcon(resize(startBtn.getIcon().getIconWidth()+10,startBtn.getIcon().getIconHeight()+10,(ImageIcon)startBtn.getIcon()));
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }else if(e.getSource()==backToMainButton){
                buttonSound();
                backToMainButton.setIcon(resize(backToMainButton.getIcon().getIconWidth()+10,backToMainButton.getIcon().getIconHeight()+10,(ImageIcon)backToMainButton.getIcon()));
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        }
        @Override
        public void mouseExited(MouseEvent arg0) {
            if(arg0.getSource()==checkBtn){
                checkBtn.setIcon(new ImageIcon("data/dinosaur/OK鍵.png"));
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            else if(arg0.getSource()==startBtn) {
                startBtn.setIcon(new ImageIcon("data/dinosaur/開始遊戲按鈕.png"));
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            else if(arg0.getSource()==backToMainButton) {
                backToMainButton.setIcon(new ImageIcon("data/gamebar/backhome.png"));
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        }
    }

    private class Handler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == startBtn) {
                remove(expCard);
                remove(moneyCard);
                remove(policeCard);
                remove(underKey);
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

    public void Itemset(JCheckBox box) {
        box.setFont(new Font("微軟正黑體", Font.PLAIN, 23));
        box.setOpaque(false);
        box.setContentAreaFilled(false);
        box.setFocusPainted(false);
        box.setBorder(null);
    }


    public ImageIcon resize(int width, int height, ImageIcon img) {
        Image i = img.getImage();
        Image new_img = i.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return  new ImageIcon(new_img);
    }

    private class UseItem implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent event) {
            int chkbox = event.getStateChange();
            if (chkbox == ItemEvent.SELECTED) {
                if (event.getItem() == expCard) {
                    froggerPanel.expCard = true;
                    house.setItem("經驗加倍券", house.getItem("經驗加倍券")-1);
                }
                if (event.getItem() == moneyCard) {
                    froggerPanel.moneyCard = true;
                    house.setItem("金錢加倍券", house.getItem("金錢加倍券")-1);
                }
                if (event.getItem() == policeCard) {
                    froggerPanel.noPolice = true;
                    house.setItem("警察卡", house.getItem("警察卡")-1);
                }
                if (event.getItem() == underKey) {
                    froggerPanel.underKey = true;
                    house.setItem("地下道鑰匙", house.getItem("地下道鑰匙")-1);
                }

            }
        }
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
