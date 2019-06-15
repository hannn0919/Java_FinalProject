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

    private JCheckBox expCard, moneyCard, prop;

    private Timer timer;
    private int time = 6000;

    // constructor
    public DinoJLayer(Main frame, House house) {
        this.frame = frame;
        this.house = house;

        Handler handle = new Handler();
        UseItem useItem = new UseItem();

        // 三種道具的checkBox
        expCard = new JCheckBox("經驗加倍券");
        ItemSet(expCard);
        if(house.getItem("經驗加倍券")<=0) expCard.setEnabled(false);

        moneyCard = new JCheckBox("金錢加倍券");
        ItemSet(moneyCard);
        if(house.getItem("金錢加倍券")<=0) moneyCard.setEnabled(false);

        prop = new JCheckBox("電蚊拍");
        ItemSet(prop);
        if(house.getItem("電蚊拍")<=0) prop.setEnabled(false);

        expCard.setBounds(500, 445, 150, 30);
        add(expCard, JLayeredPane.DEFAULT_LAYER);
        expCard.addItemListener(useItem);

        moneyCard.setBounds(680, 445, 150, 30);
        add(moneyCard, JLayeredPane.DEFAULT_LAYER);
        moneyCard.addItemListener(useItem);

        prop.setBounds(860, 445, 150, 30);
        add(prop, JLayeredPane.DEFAULT_LAYER);
        prop.addItemListener(useItem);

        // 開始遊戲的按鈕設置
        startImg = new ImageIcon("data/dinosaur/開始遊戲按鈕.png");
        startBtn = new JButton(startImg);
        startBtn.setBounds(660, 510, 150, 60);
        ButtonSet(startBtn);
        startBtn.addActionListener(handle);
        add(startBtn, JLayeredPane.DEFAULT_LAYER);

        // 遊戲說明Label
        introduceImg = new ImageIcon("data/dinosaur/introduce.png");
        introduce = new JLabel(introduceImg);
        introduce.setBounds(400, 99, 676, 476);
        add(introduce, JLayeredPane.DEFAULT_LAYER);

        // 結尾統計確認按鈕設置
        checkImg = new ImageIcon("data/dinosaur/OK鍵.png");
        checkBtn = new JButton(checkImg);
        checkBtn.setBounds(630, 490, 62, 60);
        ButtonSet(checkBtn);
        checkBtn.addActionListener(handle);
        add(checkBtn, JLayeredPane.DEFAULT_LAYER);
        checkBtn.setVisible(false);

        // 結算時顯示的錢、經驗值設置
        static_pxp = new JLabel();
        static_pxp.setFont(new Font("微軟正黑體",Font.BOLD,25));
        static_pxp.setBounds(625, 238, 150,40);
        add(static_pxp, JLayeredPane.DEFAULT_LAYER);
        static_pxp.setVisible(false);

        static_money = new JLabel();
        static_money.setFont(new Font("微軟正黑體",Font.BOLD,25));
        static_money.setBounds(625, 389, 150,40);
        add(static_money, JLayeredPane.DEFAULT_LAYER);
        static_money.setVisible(false);

        // 遊戲結算畫面
        staticsImg = new ImageIcon("data/dinosaur/遊戲結算.png");
        statics = new JLabel(staticsImg);
        statics.setBounds(400, 99, 504,476);
        add(statics, JLayeredPane.DEFAULT_LAYER);
        statics.setVisible(false);

        MouseHandler mouseHandler = new MouseHandler();
        checkBtn.addMouseListener(mouseHandler);
        startBtn.addMouseListener(mouseHandler);

        // 主要遊戲畫面
        game = new GameScreen(frame, house);
        game.setBounds(300, 0, 1200, 675);
        add(game, JLayeredPane.DEFAULT_LAYER);

        // 右邊欄位倒數計時顯示
        timeLabel = new JLabel(String.valueOf(time / 100));
        timeLabel.setFont(new Font("微軟正黑體", Font.BOLD, 32));
        //計時60秒
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                time = game.getTime();
                timeLabel.setText(String.valueOf(time / 100));
                timeLabel.setBounds(173, 76, 300, 100);
                add(timeLabel, JLayeredPane.PALETTE_LAYER);
                repaint();
                if(time == 0){
                    timer.cancel();
                    Random ran = new Random();
                    int m = ran.nextInt(800);
                    int e = ran.nextInt(400);
                    int meg = 10;       // 分數換算初始倍率
                    if(house.getEquipment("竹蜻蜓")==1) meg = 7;    // 若角色裝備竹蜻蜓，則改變倍率
                    int gainExp = (int)game.getMax() * meg + e;               // 角色獲得經驗值換算
                    int gainMoney = (int)game.getMax() * meg + m;             // 角色獲得金錢換算
                    if(game.getExpCard() == true) gainExp *= 2;               // 若角色使用道具，則獲得經驗值加倍
                    if(game.getMoneyCard() == true) gainMoney *= 2;           // 若角色使用道具，則獲得金錢加倍
                    house.gameSettlementSomething(4, gainMoney, gainExp);   // 將角色獲得的錢跟經驗值傳入house統計
                    house.gameSettlementmistake(4, game.getDieTime());      // 將角色死亡次數傳入house統計
                    static_pxp.setText(String.valueOf(gainExp));                // 改變統計畫面的文字顯示
                    static_money.setText(String.valueOf(gainMoney));            // 改變統計畫面的文字顯示
                    house.setExp(house.getExp()+gainExp);                      // 改變house中角色的經驗值
                    house.setHoldMoney(house.getHoldMoney()+gainMoney);        // 改變house中角色的金錢

                    // 顯示統計畫面，將返回主畫面按鈕開啟
                    checkBtn.setVisible(true);
                    statics.setVisible(true);
                    static_pxp.setVisible(true);
                    static_money.setVisible(true);
                    backToMainButton.setEnabled(true);
                }
            }
        }, 0, 10);

        int heightTotal=0;  // 用來計算左側欄位高度

        // 反回主畫面按鈕設置
        backToMainImage = new ImageIcon("data/gamebar/backhome.png");
        backToMainButton = new JButton(backToMainImage);
        backToMainButton.setBounds(0, heightTotal, backToMainImage.getIconWidth(), backToMainImage.getIconHeight());
        add(backToMainButton, JLayeredPane.DEFAULT_LAYER);
        ButtonSet(backToMainButton);
        backToMainButton.addActionListener(handle);
        backToMainButton.addMouseListener(mouseHandler);
        heightTotal += backToMainImage.getIconHeight();

        // 倒數計時Label設置
        disCountImage = new ImageIcon("data/gamebar/discount.png");
        disCountLabel = new JLabel(disCountImage);
        disCountLabel.setBounds(0, heightTotal, disCountImage.getIconWidth(), disCountImage.getIconHeight());
        add(disCountLabel, JLayeredPane.DEFAULT_LAYER);
        heightTotal += disCountImage.getIconHeight();

        // 簡易遊戲說明Label設置
        ruleImage = new ImageIcon("data/dinosaur/rule.png");
        ruleLabel = new JLabel(ruleImage);
        ruleLabel.setBounds(0, heightTotal, ruleImage.getIconWidth(), ruleImage.getIconHeight());
        add(ruleLabel, JLayeredPane.DEFAULT_LAYER);
        heightTotal += ruleImage.getIconHeight();

        // 確認角色目前狀態，是否有裝裝備對應到不同的路徑
        String character = "data/dinosaur/character/"+house.getLevel()+"/肝";
        if(house.getEquipment("透視眼鏡")==1)
            character += "+眼鏡";

        if(house.getEquipment("竹蜻蜓")==1)
            character += "+竹蜻蜓";

        if(house.getEquipment("翅膀")==1)
            character += "+翅膀";

        if(house.getEquipment("彈簧鞋")==1)
            character += "+彈簧鞋";

        character += ".png";
        // 左側欄位下方主角顯示Label設置
        Icon temp = new ImageIcon(character);
        liverImg = resize(temp.getIconWidth()+40, temp.getIconHeight()+32, (ImageIcon) temp);
        liverLabel = new JLabel(liverImg);
        liverLabel.setBounds(50,heightTotal+80,liverImg.getIconWidth(),liverImg.getIconHeight());
        add(liverLabel,JLayeredPane.DRAG_LAYER);

        // 顯示錢與經驗值Label設置
        expandMoneyImage = new ImageIcon("data/gamebar/expandmoney.png");
        expandMoneyLabel = new JLabel(expandMoneyImage);
        expandMoneyLabel.setBounds(0, backToMainImage.getIconHeight() + disCountImage.getIconHeight() + ruleImage.getIconHeight(), expandMoneyImage.getIconWidth(), expandMoneyImage.getIconHeight());
        add(expandMoneyLabel, JLayeredPane.DEFAULT_LAYER);

        // 經驗值顯示Label設置
        expFromMain = new JLabel("");
        expFromMain.setFont(new Font("微軟正黑體",Font.BOLD,17));
        expFromMain.setBounds(140,heightTotal+13,150,25);
        expFromMain.setText(Integer.toString(house.getExp()));
        add(expFromMain,JLayeredPane.MODAL_LAYER);

        // 金錢顯示Label設置
        moneyFromMain = new JLabel("");
        moneyFromMain.setFont(new Font("微軟正黑體",Font.BOLD,17));
        moneyFromMain.setBounds(140,heightTotal+50,150,25);
        moneyFromMain.setText(Integer.toString(house.getHoldMoney()));
        add(moneyFromMain,JLayeredPane.MODAL_LAYER);
    }

    // 各按鈕輸入偵測
    private class Handler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == startBtn) {
                remove(expCard);
                remove(moneyCard);
                remove(prop);
                remove(startBtn);
                remove(introduce);
                backToMainButton.setEnabled(false);
                game.startGame();
                game.requestFocus();
            } else if (e.getSource() == backToMainButton) {
                frame.changeToMainScreen();
            } else if (e.getSource() == checkBtn) {
                frame.changeToMainScreen();
            }
        }
    }

    // 道具checkBox偵測
    private class UseItem implements ItemListener{
        @Override
        public void itemStateChanged(ItemEvent event) {
            if (event.getStateChange() == ItemEvent.SELECTED) {     // 若道具被選擇，則改變角色狀態，並改變house中的數量
                if (event.getItem() == expCard) {
                    game.setExpCard(true);
                    house.setItem("經驗加倍券", house.getItem("經驗加倍券")-1);
                }
                if (event.getItem() == moneyCard) {
                    game.setMoneyCard(true);
                    house.setItem("金錢加倍券", house.getItem("金錢加倍券")-1);
                }
                if (event.getItem() == prop) {
                    game.getMainCharacter().setinvIncible(true);
                    house.setItem("電蚊拍", house.getItem("電蚊拍")-1);
                }
            }
            if (event.getStateChange() == ItemEvent.DESELECTED) {       // 若道具取消選擇，則改變角色狀態，並改變house中的數量
                if (event.getItem() == expCard) {
                    game.setExpCard(false);
                    house.setItem("經驗加倍券", house.getItem("經驗加倍券")+1);
                }
                if (event.getItem() == moneyCard) {
                    game.setMoneyCard(false);
                    house.setItem("金錢加倍券", house.getItem("金錢加倍券")+1);
                }
                if (event.getItem() == prop) {
                    game.getMainCharacter().setinvIncible(false);
                    house.setItem("電蚊拍", house.getItem("電蚊拍")+1);
                }
            }
        }
    }

    // 將道具的checkBox處理，去背、設置大小等
    public void ItemSet(JCheckBox box) {
        box.setFont(new Font("微軟正黑體", Font.PLAIN, 23));
        box.setOpaque(false);
        box.setContentAreaFilled(false);
        box.setFocusPainted(false);
        box.setBorder(null);
    }

    // 將btiion處理，去背、取消選取時的虛線等
    public void ButtonSet(JButton button) {
        button.setOpaque(false);
        button.setBorderPainted(false);
        button.setBorder(null);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
    }

    // 偵測滑鼠，播放音效改變大小
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
            } else if(e.getSource()==startBtn){
                buttonSound();
                startBtn.setIcon(resize(startBtn.getIcon().getIconWidth()+10,startBtn.getIcon().getIconHeight()+10,(ImageIcon)startBtn.getIcon()));
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            } else if(e.getSource()==backToMainButton){
                if(backToMainButton.isEnabled() == true) {
                    buttonSound();
                    backToMainButton.setIcon(resize(backToMainButton.getIcon().getIconWidth() + 10, backToMainButton.getIcon().getIconHeight() + 10, (ImageIcon) backToMainButton.getIcon()));
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
            }
        }
        @Override
        public void mouseExited(MouseEvent arg0) {
            if(arg0.getSource()==checkBtn){
                checkBtn.setIcon(new ImageIcon("data/dinosaur/OK鍵.png"));
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            } else if(arg0.getSource()==startBtn) {
                startBtn.setIcon(new ImageIcon("data/dinosaur/開始遊戲按鈕.png"));
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            } else if(arg0.getSource()==backToMainButton) {
                backToMainButton.setIcon(new ImageIcon("data/gamebar/backhome.png"));
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        }
    }

    // 改變圖片大小
    public ImageIcon resize(int width, int height, ImageIcon img) {
        Image i = img.getImage();
        Image new_img = i.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return  new ImageIcon(new_img);
    }

    // 按鈕音效
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
}