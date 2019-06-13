package Card;///////game=1

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Random;
import java.util.LinkedList;
import java.io.File;
import javax.swing.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.sound.sampled.*;

import Main.*;
import House.house.House;


/**
 * @author jqs 主要實現記憶翻牌功能
 */
public class RememberCard extends JLayeredPane {  ///翻一對  exp100  money250

    /**
     * 初始化遊戲的行列數，行列數成績必須為偶數
     */
    private static final int ROWS = 5;
    private static final int COLUMNS = 6;
    private static final long serialVersionUID = -8908268719780973221L;

    private JLabel txt_Time;//顯示時間
    private JLabel expFromMain;//跟main拿的exp
    private JLabel moneyFromMain;//跟main拿的money

    private boolean isRunning = false;
    private long time=60;///////全部時間
    /**
     * 存放圖片的目錄，簡單起見，存放圖片的目錄中圖片個數為初始化的行列數乘積的一半
     */
    private String picDir = "data/cards/c";
    private String[] picture;//照片索引


    private PicPanel preOne = null;
    private PicPanel panel_1;//塞進panel_Pic
    private PicPanel panel_2;//塞進panel_ans

    private File file = new File(picDir);
    private File[] pics = file.listFiles();


    private JPanel panel_Pic;//右邊遊戲面板
    private JPanel panel_ans;//openall時顯示

    private JButton addsec;//增時卡
    private JButton openall;//翻開全部
    private JButton btnOk;//結算時ok按鈕
    private JButton btnStart;//開始按鈕
    private JButton backToMainButton;//返回主畫面

    private JCheckBox check_doubleexp;//兩倍經驗卡
    private JCheckBox check_doublemoney;//兩倍金錢卡
   
    private ImageIcon backToMainImage;
    private ImageIcon disCountImage;
    private ImageIcon expandMoneyImage;
    private ImageIcon ruleImage;
    private ImageIcon endImage;
    private ImageIcon testCharacterImage;
    private ImageIcon introduceImg;
    private ImageIcon btnStartImage;
    private ImageIcon swing_card;
    private ImageIcon btnOkImage;
    private ImageIcon addsecImage;
    private ImageIcon openallImage;
    private ImageIcon liverImg;


    private JLabel introduce;
    private JLabel end_money;
    private JLabel end_exp;
    private JLabel disCountLabel;
    private JLabel expandMoneyLabel;
    private JLabel ruleLabel;
    private JLabel endLabel;
    private JLabel testCharacterLabel;
    private JLabel liverLabel;


    private int count;
    private int exp=100;
    private int money=250;

    private Main mainFrame;
    private House house;

    ////////////////////button特效/////////////////
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

    private class MouseHandler implements  MouseListener{
        @Override public void mousePressed(MouseEvent e){
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
        @Override public void mouseReleased(MouseEvent e){}
        @Override
        public void mouseClicked(MouseEvent e) {}
        @Override
        public void mouseEntered(MouseEvent e){
            if(e.getSource()==check_doubleexp) {
                buttonSound();
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            else if(e.getSource()==check_doublemoney){
                buttonSound();
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            else if(e.getSource()==btnStart){
                buttonSound();
                btnStart.setIcon(resize(btnStart.getIcon().getIconWidth()+10,btnStart.getIcon().getIconHeight()+10,(ImageIcon)btnStart.getIcon()));
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            else if(e.getSource()==btnOk){
                buttonSound();
                btnOk.setIcon(resize(btnOk.getIcon().getIconWidth()+10,btnOk.getIcon().getIconHeight()+10,(ImageIcon)btnOk.getIcon()));
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            else if(e.getSource()==backToMainButton){
                buttonSound();
                backToMainButton.setIcon(resize(backToMainButton.getIcon().getIconWidth()+10,backToMainButton.getIcon().getIconHeight()+10,(ImageIcon)backToMainButton.getIcon()));
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            else if(e.getSource()==addsec){
                buttonSound();
                addsec.setIcon(resize(addsec.getIcon().getIconWidth()+10,addsec.getIcon().getIconHeight()+10,(ImageIcon)addsec.getIcon()));
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            else if(e.getSource()==openall){
                buttonSound();
                openall.setIcon(resize(openall.getIcon().getIconWidth()+10,openall.getIcon().getIconHeight()+10,(ImageIcon)openall.getIcon()));
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        }
        @Override
        public void mouseExited(MouseEvent arg0) {
            if(arg0.getSource()==btnOk){
                btnOk.setIcon(new ImageIcon("data/cards/OK鍵.png"));
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            else if(arg0.getSource()==btnStart) {
                btnStart.setIcon(new ImageIcon("data/cards/開始遊戲按鈕.png"));
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            else if(arg0.getSource()==backToMainButton) {
                backToMainButton.setIcon(new ImageIcon("data/gamebar/backhome.png"));
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            else if(arg0.getSource()==addsec) {
                addsec.setIcon(new ImageIcon("data/cards/增時卡.png"));
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            else if(arg0.getSource()==openall) {
                openall.setIcon(new ImageIcon("data/cards/再看一次.png"));
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        }
    }
    ////////////////////button特效/////////////////


    public RememberCard(Main mainFrame,House house) {

        this.house=house;
        this.mainFrame=mainFrame;
        MouseHandler mouseHandler = new MouseHandler();

        //////介紹關卡////////
        introduceImg = new ImageIcon("data/cards/introduce_test.png");
        introduce = new JLabel(introduceImg);
        introduce.setBounds(400, 99, 676, 476);
        add(introduce, JLayeredPane.POPUP_LAYER);
        //////介紹關卡////////

        /////////////////經驗和金錢加倍卡///////////////
        check_doubleexp = new JCheckBox("經驗加倍卡");
        check_doubleexp .setFont(new Font("微軟正黑體", Font.PLAIN, 23));
        check_doubleexp .setBounds(580, 460, 150, 30);
        check_doubleexp .setBorderPainted(false);
        check_doubleexp .setFocusPainted(false);
        check_doubleexp .setContentAreaFilled(false);
        check_doubleexp.addMouseListener(mouseHandler);
        if(house.getItem("經驗加倍券")==0){
            check_doubleexp.setEnabled(false);
        }
        add(check_doubleexp, JLayeredPane.DRAG_LAYER);

        check_doublemoney = new JCheckBox("金錢加倍卡");
        check_doublemoney.setFont(new Font("微軟正黑體", Font.PLAIN, 23));
        check_doublemoney.setBorderPainted(false);
        check_doublemoney.setFocusPainted(false);
        check_doublemoney.setContentAreaFilled(false);
        check_doublemoney.addMouseListener(mouseHandler);
        if(house.getItem("金錢加倍券")==0){
            check_doublemoney.setEnabled(false);
        }
        check_doublemoney.setBounds(810, 460, 150, 30);
        add(check_doublemoney, JLayeredPane.DRAG_LAYER);
        /////////////////經驗和金錢加倍卡///////////////

        //////左下經驗和金錢//////////
        expFromMain = new JLabel("");
        expFromMain.setFont(new Font("Hollywood Hills",Font.BOLD,17));
        expFromMain.setBounds(140,405,150,25);
        expFromMain.setText(Integer.toString(house.getExp()));
        add(expFromMain,JLayeredPane.MODAL_LAYER);

        moneyFromMain = new JLabel("");
        moneyFromMain.setFont(new Font("Hollywood Hills",Font.BOLD,17));
        moneyFromMain.setBounds(140,440,150,25);
        moneyFromMain.setText(Integer.toString(house.getHoldMoney()));
        add(moneyFromMain,JLayeredPane.MODAL_LAYER);
        //////左下經驗和金錢//////////

        //////返回主畫面/////////////
        backToMainImage = new ImageIcon("data/cards/icon/backhome.png");
        backToMainButton = new JButton(backToMainImage);
        backToMainButton.setBounds(0,0,backToMainImage.getIconWidth(),backToMainImage.getIconHeight());
        backToMainButton.setBorderPainted(false);
        backToMainButton.setBorder(null);
        backToMainButton.setFocusPainted(false);
        backToMainButton.setContentAreaFilled(false);
        backToMainButton.addMouseListener(mouseHandler);
        add(backToMainButton,JLayeredPane.DEFAULT_LAYER);
        backToMainButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 mainFrame.changeToMainScreen();
             }
         });
        //////返回主畫面/////////////

        /////顯示剩下時間/////////
        txt_Time = new JLabel();
        txt_Time.setBounds(180, 95, 82, 60);
        add(txt_Time,JLayeredPane.MODAL_LAYER);
        /////顯示剩下時間/////////

        /////倒數時間的背景/////////
        disCountImage = new ImageIcon("data/cards/icon/discount.png");
        disCountLabel = new JLabel(disCountImage);
        disCountLabel.setBounds(0,backToMainImage.getIconHeight(),disCountImage.getIconWidth(),disCountImage.getIconHeight());
        add(disCountLabel,JLayeredPane.DEFAULT_LAYER);
        /////倒數時間的背景/////////

        /////左邊規則圖片/////////
        ruleImage = new ImageIcon("data/cards/icon/rule.png");
        ruleLabel = new JLabel(ruleImage);
        ruleLabel.setBounds(0,backToMainImage.getIconHeight()+disCountImage.getIconHeight(),ruleImage.getIconWidth(),ruleImage.getIconHeight());
        add(ruleLabel,JLayeredPane.DEFAULT_LAYER);
        /////左邊規則圖片/////////

        /////左邊經驗和金錢圖片/////////
        expandMoneyImage = new ImageIcon("data/gamebar/expandMoney.png");
        expandMoneyLabel = new JLabel(expandMoneyImage);
        expandMoneyLabel.setBounds(0,backToMainImage.getIconHeight()+disCountImage.getIconHeight()+ruleImage.getIconHeight(),expandMoneyImage.getIconWidth(),expandMoneyImage.getIconHeight());
        add(expandMoneyLabel,JLayeredPane.DEFAULT_LAYER);
        /////左邊經驗和金錢圖片/////////

        /////左邊的肝/////////
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
        liverLabel.setBounds(30,500,liverImg.getIconWidth(),liverImg.getIconHeight());
        add(liverLabel,JLayeredPane.DRAG_LAYER);
        
        /////左邊的肝/////////


        /////左下的道具按鈕///////
        //////加十秒//////////////
        addsecImage = new ImageIcon("data/cards/增時卡.png");
        addsec = new JButton(addsecImage);
        addsec.setBorderPainted(false);
        addsec.setBorder(null);
        addsec.setFocusPainted(false);
        addsec.setContentAreaFilled(false);
        addsec.addMouseListener(mouseHandler);
        addsec.setBounds(180,520,addsecImage.getIconWidth(),addsecImage.getIconHeight());
        add(addsec, JLayeredPane.MODAL_LAYER);
        addsec.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(house.getItem("增時卡")>0){//道具>1
                    house.setItem("增時卡",house.getItem("增時卡")-1 );//使用道具  道具-1
                    timer2.start();
                }
            }
        });
        //////加十秒/////////////
        //////翻開全部十秒///////
        openallImage = new ImageIcon("data/cards/再看一次.png");
        openall = new JButton(openallImage);
        openall.setBorderPainted(false);
        openall.setBorder(null);
        openall.setFocusPainted(false);
        openall.setContentAreaFilled(false);
        openall.addMouseListener(mouseHandler);
        openall.setBounds(180,570,openallImage.getIconWidth(),openallImage.getIconHeight());
        add(openall, JLayeredPane.MODAL_LAYER);
        openall.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(house.getItem("再看一次")>0){//道具>1
                    house.setItem("再看一次",house.getItem("再看一次")-1 );//使用道具  道具-1
                    openallcard();
                    timer4.start();
                }
            }
        });
        //////翻開全部十秒///////
        /////左下的道具按鈕//////

        ///////結算時的ok按鈕////////////////////
        btnOkImage = new ImageIcon("data/cards/OK鍵.png");
        btnOk = new JButton(btnOkImage);
        btnOk.setBorderPainted(false);
        btnOk.setBorder(null);
        btnOk.setFocusPainted(false);
        btnOk.setContentAreaFilled(false);
        btnOk.addMouseListener(mouseHandler);
        add(btnOk,JLayeredPane.DRAG_LAYER);
        ///////結算時的ok按鈕////////////////////

        //////////////開始遊戲按鈕/////////////////
        btnStartImage = new ImageIcon("data/cards/開始遊戲按鈕.png");
        btnStart = new JButton(btnStartImage);
        btnStart.setBounds(660,520,btnStartImage.getIconWidth(),btnStartImage.getIconHeight());
        btnStart.setBorderPainted(false);
        btnStart.setBorder(null);
        btnStart.setFocusPainted(false);
        btnStart.setContentAreaFilled(false);
        btnStart.addMouseListener(mouseHandler);
        add(btnStart,JLayeredPane.DRAG_LAYER);
        btnStart.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 remove(btnStart);//移掉開始按鈕
                 remove(introduce);//移掉規則
                 remove(check_doubleexp);//移掉經驗加倍
                 remove(check_doublemoney);//移掉金錢加倍
                 repaint();

                 panel_Pic = new JPanel();//增加右邊遊戲面板
                 panel_Pic.setBounds(300, 0, 900, 655);
                 add(panel_Pic, JLayeredPane.MODAL_LAYER);//100

                 panel_ans = new JPanel();//增加答案面板
                 panel_ans.setBounds(300, 0, 900, 655);
                 panel_ans.setVisible(true);
                 add(panel_ans, JLayeredPane.DEFAULT_LAYER);//0


                 backToMainButton.setEnabled(false);////返回主畫面失效
                 if (isRunning) {
                     return;
                 }
                 setRunning(true);
                 if(house.getItem("增時卡")==0){ //如果沒有增時卡 失效
                     addsec.setEnabled(false);
                 }
                 if(house.getItem("再看一次")==0){  //如果沒有翻開全部  失效
                     openall.setEnabled(false);
                 }
                 startGame();//開始遊戲
            }
         });
        //////////////開始遊戲按鈕/////////////////
    }

    Timer timer1 = new Timer(1000, new ActionListener() {//倒數60sec   // 1 sec 跑一次
        public void actionPerformed(ActionEvent e) {
            txt_Time.setFont(new Font("微軟正黑體", Font.BOLD, 32));;
            txt_Time.setText(time + "");
            time--;

            if(time==-2||count==15){//不是0 是因為要加上翻牌的三秒  翻開全部
                house.gameSettlementmistake(1, count);///回傳玩了一次和翻了幾對

                endImage = new ImageIcon("data/cards/遊戲結算.png");
                endLabel = new JLabel(endImage);
                endLabel.setBounds(510, 150, endImage.getIconWidth(),endImage.getIconHeight());
                add(endLabel,JLayeredPane.POPUP_LAYER);

                btnOk.setBounds(670, 465, btnOkImage.getIconWidth()+10,btnOkImage.getIconHeight()+10);
                btnOk.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mainFrame.changeToMainScreen();
                    }
                });

               //////////////////結算///////////////////////////

                if(check_doubleexp.isSelected()){
                    exp=200;//100*2
                }
                if(check_doublemoney.isSelected()){
                    money=500;//250*2
                }
                /////////////////////////////////////////////////
                house.setExp(house.getExp()+count*exp);//返回
                house.setHoldMoney(house.getHoldMoney()+ count*money );//返回
                //////////////////////////////////////////////////////////

                //////結算的經驗和金錢/////////////
                end_exp = new JLabel();
                end_exp.setText(Integer.toString(count*exp));//exp 每翻一對+100
                end_exp.setFont(new Font("Hollywood Hills",Font.BOLD,23));
                end_exp.setBounds(720, 260, 150, 25);
                add(end_exp,JLayeredPane.DRAG_LAYER);

                end_money = new JLabel();
                end_money.setText(Integer.toString(count*money));//money 每翻一對+250
                end_money.setFont(new Font("Hollywood Hills",Font.BOLD,23));
                end_money.setBounds(720,390,150,25);
                add(end_money,JLayeredPane.DRAG_LAYER);
                //////結算的經驗和金錢/////////////

                // 結束後重新初始化一下面板以便於下一次的執行
                backToMainButton.setEnabled(true);
                count = 0;
                txt_Time.setText(null);
                panel_Pic.validate();
                isRunning = false;
                timer1.stop();
            }
        }
    });

    Timer timer2 = new Timer(1, new ActionListener() {//addsec
        public void actionPerformed(ActionEvent e) {
            time=time+10;
            timer2.stop();
        }
    });

    Timer timer4 = new Timer(10000, new ActionListener() {//openall 10sec
        public void actionPerformed(ActionEvent e) {
            mainFrame.remove(panel_ans);///移掉答案面板
            repaint();
            timer4.stop();
        }
    });

    /**
     * 開始遊戲
     */
    protected void startGame() {
        new Thread() {//執行緒  可能要改掉  不然監聽上會變慢
            public void run() {
                initPicPanels();
                timer1.start();//開始倒數
            }
        }.start();
    }

    private void openallcard(){//翻開全部十秒
        add(panel_ans ,JLayeredPane.DRAG_LAYER);//400
        panel_ans.setVisible(true);
    }

    /**
     * 初始化圖片面板
     */
    private void initPicPanels() {
        panel_Pic.setLayout(new GridLayout(ROWS, COLUMNS, 20, 20));//10是間隙
        panel_ans.setLayout(new GridLayout(ROWS, COLUMNS, 20, 20));//10是間隙
        panel_Pic.setBackground( Color.ORANGE);
        panel_ans.setBackground( Color.ORANGE);
        initPictureIndex();//初始化圖片的索引並賦值每個圖片的路徑

        for (int i = 0; i < ROWS * COLUMNS; i++) {
            panel_1 = new PicPanel(this, picture[i]);///小格的卡片
            panel_1.setBorder(null);
            panel_Pic.add(panel_1);///加入右邊遊戲面板
            repaint();
        }
        for (int i = 0; i < ROWS * COLUMNS; i++) {
            panel_2 = new PicPanel(this, picture[i]);///小格的卡片
            panel_2.setBorder(null);
            panel_ans.add(panel_2);///加入答案面板
            repaint();
        }
        try {
            Thread.sleep(3000);//延遲三秒蓋牌
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        PicPanel panel_swing = new PicPanel(this, picture[5]);//挑其中一張當裝備必翻的卡牌
        for (int i = 0; i < panel_Pic.getComponentCount(); i++) {//一個一個放進去
            Component comp = panel_Pic.getComponent(i);
            if(comp instanceof PicPanel){
                panel_1 = (PicPanel)comp;
                panel_2=(PicPanel)comp;
                panel_1.setCardback();//放背面卡牌
            }
            if(house.getEquipment("翅膀")>0){
                count++;//因為會有一對
                if(panel_swing.picPath.compareTo(panel_1.picPath)==0){//相同
                    swing_card = new ImageIcon(picture[5]);
                    panel_swing.lbl_Pic.setIcon(swing_card);//翻開
                    panel_1.lbl_Pic.setIcon(swing_card);//翻開
                }
            }
        }
    }
    /**
     *
     * 初始化圖片的索引並賦值每個圖片的路徑
     */
    private void initPictureIndex() {
        picture = new String[ROWS * COLUMNS];////裡面放圖片的路徑

        // 這裡沒有檢測圖片目錄中檔案的有效性，需要保證都是圖片型別。
        file = new File(picDir);
        pics = file.listFiles();

        // 初始化一個ROWS*COLUMNS的int陣列，裡面存放每個圖片的索引
        int[] indexs = getIndexs(picture.length, pics.length);
        for (int i = 0; i < indexs.length; i++) {
            picture[i] = pics[indexs[i]].getAbsolutePath();
        }
    }

    /**
     * 根據提供的圖片總數目（假設圖片都是互不相同的）得到一個長度為sum的陣列用來表示每個圖片的索引
     *
     * @param sum
     *            遊戲的行列數乘積
     * @param picNums
     *            給定目錄下圖片的總數目
     * @return
     */
    private int[] getIndexs(int sum, int picNums) {

        int half = sum / 2;
        int[] tmpResult = new int[sum];
        Random random = new Random(System.currentTimeMillis());
        int temp = 0;
        LinkedList<Integer> list = new LinkedList<Integer>();

        while (list.size() != half) {
            temp = random.nextInt(picNums);
            if (!list.contains(temp)) {
                list.add(temp);
            }
            else if (picNums < half) {
                list.add(temp);
            }
        }

        for (int i = 0; i < tmpResult.length; i++) {
            tmpResult[i] = list.get(i >= half ? i % half : i);
        }
        // 將順序打亂，否則會出現前半部分和後半部分是完全分開的情況
        LinkedList<Integer> _result = new LinkedList<Integer>();

        while (_result.size() != sum) {
            temp = random.nextInt(sum);
            if (!_result.contains(temp)) {
                _result.add(temp);
            }
        }
        int[] result = new int[sum];
        for (int i = 0; i < result.length; i++) {
            result[i] = tmpResult[_result.get(i)];
        }
        return result;
    }


    public PicPanel getPreOne() {
        return preOne;
    }

    public void setPreOne(PicPanel preOne) {
        this.preOne = preOne;
    }

    public void addCount() {//對數
        count++;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

}




