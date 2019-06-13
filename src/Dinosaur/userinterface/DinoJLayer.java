package Dinosaur.userinterface;
import House.house.House;
import Main.Main;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class DinoJLayer extends JLayeredPane {
    private Main frame;
    private House house;
    private GameScreen game;

    private ImageIcon backToMainImage, disCountImage, expandMoneyImage, ruleImage;
    private ImageIcon introduceImg, startImg, staticsImg, checkImg;

    private JButton checkBtn, startBtn, backToMainButton;
    private JLabel introduce, disCountLabel, expandMoneyLabel, ruleLabel;
    private JLabel expFromMain, moneyFromMain, statics, timeLabel;
    private JLabel static_pxp, static_money;
    private JLabel liverLabel;
    private ImageIcon liverImg;

    private JCheckBox expcard, moneycard, prop;

    private Timer timer;
    private int time = 6000;

    public DinoJLayer(Main frame, House house) {
        this.frame = frame;
        this.house = house;

        Handler handle = new Handler();
        UseItem useItem = new UseItem();

        expcard = new JCheckBox("經驗加倍券");
        Itemset(expcard);
        if(house.getItem("經驗加倍券")<=0) expcard.setEnabled(false);


        moneycard = new JCheckBox("金錢加倍券");
        Itemset(moneycard);
        if(house.getItem("金錢加倍券")<=0) moneycard.setEnabled(false);

        prop = new JCheckBox("電蚊拍");
        Itemset(prop);
        if(house.getItem("電蚊拍")<=0) prop.setEnabled(false);

        startImg = new ImageIcon("data/dinosaur/開始遊戲按鈕.png");
        startBtn = new JButton(startImg);
        startBtn.setBounds(660, 510, 150, 60);
        startBtn.setBorderPainted(false);
        startBtn.setBorder(null);
        startBtn.setFocusPainted(false);
        startBtn.setContentAreaFilled(false);
        startBtn.addActionListener(handle);
        add(startBtn, JLayeredPane.DEFAULT_LAYER);

        expcard.setBounds(500, 445, 150, 30);
        add(expcard, JLayeredPane.DEFAULT_LAYER);
        expcard.addItemListener(useItem);

        moneycard.setBounds(680, 445, 150, 30);
        add(moneycard, JLayeredPane.DEFAULT_LAYER);
        moneycard.addItemListener(useItem);

        prop.setBounds(860, 445, 150, 30);
        add(prop, JLayeredPane.DEFAULT_LAYER);
        prop.addItemListener(useItem);


        introduceImg = new ImageIcon("data/dinosaur/introduce.png");
        introduce = new JLabel(introduceImg);
        introduce.setBounds(400, 99, 676, 476);
        add(introduce, JLayeredPane.DEFAULT_LAYER);

        checkImg = new ImageIcon("data/dinosaur/OK鍵.png");
        checkBtn = new JButton(checkImg);
        checkBtn.setBounds(630, 490, 62, 60);
        checkBtn.setBorderPainted(false);
        checkBtn.setBorder(null);
        checkBtn.setFocusPainted(false);
        checkBtn.setContentAreaFilled(false);
        checkBtn.addActionListener(handle);
        add(checkBtn, JLayeredPane.DEFAULT_LAYER);
        checkBtn.setVisible(false);

        static_pxp = new JLabel();
        static_pxp.setFont(new Font("Hollywood Hills",Font.BOLD,25));
        static_pxp.setBounds(625, 238, 150,40);
        add(static_pxp, JLayeredPane.DEFAULT_LAYER);
        static_pxp.setVisible(false);

        static_money = new JLabel();
        static_money.setFont(new Font("Hollywood Hills",Font.BOLD,25));
        static_money.setBounds(625, 389, 150,40);
        add(static_money, JLayeredPane.DEFAULT_LAYER);
        static_money.setVisible(false);

        staticsImg = new ImageIcon("data/dinosaur/遊戲結算.png");
        statics = new JLabel(staticsImg);
        statics.setBounds(400, 99, 504,476);
        add(statics, JLayeredPane.DEFAULT_LAYER);
        statics.setVisible(false);

        MouseHandler mouseHandler = new MouseHandler();
        checkBtn.addMouseListener(mouseHandler);
        startBtn.addMouseListener(mouseHandler);

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
                    Random ran = new Random();
                    int m = ran.nextInt(800);
                    int e = ran.nextInt(400);
                    int meg = 10;
                    if(house.getEquipment("竹蜻蜓")==1) meg = 7;
                    int gainexp = (int)game.max * meg + e;
                    int gainmoney = (int)game.max * meg + m;
                    if(game.expcard == true) gainexp *= 2;
                    if(game.moneycard == true) gainmoney *= 2;
                    house.gameSettlementSomething(4, gainmoney, gainexp);
                    house.gameSettlementmistake(4, game.getDieTime());
                    static_pxp.setText(String.valueOf(gainexp));
                    static_money.setText(String.valueOf(gainmoney));
                    house.setExp(house.getExp()+gainexp);
                    house.setHoldMoney(house.getHoldMoney()+gainmoney);
                    checkBtn.setVisible(true);
                    statics.setVisible(true);
                    static_pxp.setVisible(true);
                    static_money.setVisible(true);
                    backToMainButton.setEnabled(true);
                }
            }
        }, 0, 10);

        int heightTotal=0;

        backToMainImage = new ImageIcon("data/gamebar/backhome.png");
        backToMainButton = new JButton(backToMainImage);
        backToMainButton.setBounds(0, heightTotal, backToMainImage.getIconWidth(), backToMainImage.getIconHeight());
        add(backToMainButton, JLayeredPane.DEFAULT_LAYER);
        backToMainButton.setBorderPainted(false);
        backToMainButton.setBorder(null);
        backToMainButton.setFocusPainted(false);
        backToMainButton.setContentAreaFilled(false);
        backToMainButton.addActionListener(handle);
        backToMainButton.addMouseListener(mouseHandler);
        heightTotal += backToMainImage.getIconHeight();

        disCountImage = new ImageIcon("data/gamebar/discount.png");
        disCountLabel = new JLabel(disCountImage);
        disCountLabel.setBounds(0, heightTotal, disCountImage.getIconWidth(), disCountImage.getIconHeight());
        add(disCountLabel, JLayeredPane.DEFAULT_LAYER);
        heightTotal += disCountImage.getIconHeight();

        ruleImage = new ImageIcon("data/dinosaur/rule.png");
        ruleLabel = new JLabel(ruleImage);
        ruleLabel.setBounds(0, heightTotal, ruleImage.getIconWidth(), ruleImage.getIconHeight());
        add(ruleLabel, JLayeredPane.DEFAULT_LAYER);
        heightTotal += ruleImage.getIconHeight();

        String character = "data/dinosaur/character/"+house.getLevel()+"/肝";
        if(house.getEquipment("透視眼鏡")==1){
            character += "+眼鏡";
        }

        if(house.getEquipment("竹蜻蜓")==1){
            character += "+竹蜻蜓";
        }

        if(house.getEquipment("翅膀")==1){
            character += "+翅膀";
        }

        if(house.getEquipment("彈簧鞋")==1){
            character += "+彈簧鞋";

        }
        liverImg = new ImageIcon( character+".png");//偷個恐龍圖片
        liverLabel = new JLabel(liverImg);
        liverLabel.setBounds(30,heightTotal+100,liverImg.getIconWidth(),liverImg.getIconHeight());
        add(liverLabel,JLayeredPane.DRAG_LAYER);

        expandMoneyImage = new ImageIcon("data/gamebar/expandmoney.png");
        expandMoneyLabel = new JLabel(expandMoneyImage);
        expandMoneyLabel.setBounds(0, backToMainImage.getIconHeight() + disCountImage.getIconHeight() + ruleImage.getIconHeight(), expandMoneyImage.getIconWidth(), expandMoneyImage.getIconHeight());
        add(expandMoneyLabel, JLayeredPane.DEFAULT_LAYER);

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

    private class UseItem implements ItemListener{
        @Override
        public void itemStateChanged(ItemEvent event) {
            int chkbox = event.getStateChange();
            if (chkbox == ItemEvent.SELECTED) {
                if (event.getItem() == expcard) {
                    game.expcard = true;
                    house.setItem("經驗加倍券", house.getItem("經驗加倍券")-1);
                }
                if (event.getItem() == moneycard) {
                    game.moneycard = true;
                    house.setItem("金錢加倍券", house.getItem("金錢加倍券")-1);
                }
                if (event.getItem() == prop) {
                    game.mainCharacter.setinvIncible(true);
                    house.setItem("電蚊拍", house.getItem("電蚊拍")-1);
                }
            }
            if (chkbox == ItemEvent.DESELECTED) {
                if (event.getItem() == expcard) {
                    game.expcard = true;
                    house.setItem("經驗加倍券", house.getItem("經驗加倍券")+1);
                }
                if (event.getItem() == moneycard) {
                    game.moneycard = true;
                    house.setItem("金錢加倍券", house.getItem("金錢加倍券")+1);
                }
                if (event.getItem() == prop) {
                    game.mainCharacter.setinvIncible(false);
                    house.setItem("電蚊拍", house.getItem("電蚊拍")+1);
                }
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

    private class MouseHandler implements  MouseListener{
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

    public ImageIcon resize(int width, int height, ImageIcon img) {
        Image i = img.getImage();
        Image new_img = i.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return  new ImageIcon(new_img);
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
    public String toString(){
        return "I am in Dinosaur";
    }

}
