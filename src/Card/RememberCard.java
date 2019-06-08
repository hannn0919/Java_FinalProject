package Card;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Random;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.TimerTask;
import javax.swing.*;
//import javax.swing.border.Border;
//import javax.swing.border.TitledBorder;
import Main.*;
import House.house.House;



/**
 * @author jqs 主要實現記憶翻牌功能
 */
public class RememberCard extends JLayeredPane {
    private JFrame frame;
    /**
     * 初始化遊戲的行列數，行列數成績必須為偶數
     */
    private static final int ROWS = 6;
    private static final int COLUMNS = 5;
    private static final long serialVersionUID = -8908268719780973221L;
    private JLabel txt_Time;
    private boolean isRunning = false;
    private long a;
    private long time=30;
    /**
     * 存放圖片的目錄，簡單起見，存放圖片的目錄中圖片個數為初始化的行列數乘積的一半
     */
    private String picDir = "data/cards";
    private String[] picture;//照片索引
    protected boolean isStart;
    private PicPanel preOne = null;
    /**
     * 用於標示已找到的對數
     */
    private int count;
    private JPanel panel_Pic;//////////////////////////////////右邊
    private JButton swing; 
    private JButton addsec;
    private JButton openall;

    private ImageIcon imageBackGround;//背景圖
   
   
    private ImageIcon backToMainImage;
    private ImageIcon disCountImage;
    private ImageIcon expandMoneyImage;
    private ImageIcon ruleImage;
    private ImageIcon testCharacterImage;

    private JButton btnStart;//開始按鈕
    private JButton backToMainButton;
    
    private final int SHIFT=300;
   
    private JLabel backGroundLabel;
    private JLabel disCountLabel;
    private JLabel expandMoneyLabel;
    private JLabel ruleLabel;
    private JLabel testCharacterLabel;
    private Main mainFrame;
    private House house;

    //private JLayeredPane layeredPane;
    public RememberCard(Main mainFrame,House house) {

        this.house=house;
        this.mainFrame=mainFrame;
       // timer1.start();
        //timer1.stop();

        backToMainImage = new ImageIcon("data/cards/icon/backhome.png");
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
                 mainFrame.changeToMainScreen();
             }
         });

        disCountImage = new ImageIcon("data/cards/icon/discount.png");
        disCountLabel = new JLabel(disCountImage);
        txt_Time = new JLabel();
        txt_Time.setBounds(200, 65, 82, 60);
        disCountLabel.setBounds(0,backToMainImage.getIconHeight(),disCountImage.getIconWidth(),disCountImage.getIconHeight());
        add(disCountLabel,JLayeredPane.DEFAULT_LAYER);
        add(txt_Time,JLayeredPane.MODAL_LAYER);

        ruleImage = new ImageIcon("data/cards/icon/rule.png");
        ruleLabel = new JLabel(ruleImage);
        ruleLabel.setBounds(0,backToMainImage.getIconHeight()+disCountImage.getIconHeight(),ruleImage.getIconWidth(),ruleImage.getIconHeight());
        add(ruleLabel,JLayeredPane.DEFAULT_LAYER);

        expandMoneyImage = new ImageIcon("data/cards/icon/expandMoney.png");
        expandMoneyLabel = new JLabel(expandMoneyImage);
        expandMoneyLabel.setBounds(0,backToMainImage.getIconHeight()+disCountImage.getIconHeight()+ruleImage.getIconHeight(),expandMoneyImage.getIconWidth(),expandMoneyImage.getIconHeight());
        add(expandMoneyLabel,JLayeredPane.DEFAULT_LAYER);


        System.out.println(ruleImage.getIconHeight());
        testCharacterImage = new ImageIcon("data/cards/icon/test_charater.png");
        testCharacterLabel = new JLabel(testCharacterImage);
        testCharacterLabel.setBounds(0,397,testCharacterImage.getIconWidth(),testCharacterImage.getIconHeight());
        add(testCharacterLabel,JLayeredPane.DEFAULT_LAYER);
        
        btnStart = new JButton("開始遊戲");
        add(btnStart,JLayeredPane.DEFAULT_LAYER);
        btnStart.setBounds(750, 0, 100, 40);
        //

        swing = new JButton("翻一對");
        addsec = new JButton("加時");
        openall = new JButton("看全部");

        swing.setBounds(200,550,100,30);
        addsec.setBounds(200,580,100,30);
        openall.setBounds(200,610,100,30);

        add(swing, JLayeredPane.MODAL_LAYER);
        add(addsec, JLayeredPane.MODAL_LAYER);
        add(openall, JLayeredPane.MODAL_LAYER);
        Rule r = new Rule();
        
        btnStart.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                remove(btnStart);//移掉開始按鈕
                panel_Pic = new JPanel();
                panel_Pic.setBounds(300, 0, 900, 655);
                add(panel_Pic, JLayeredPane.DEFAULT_LAYER);
                backToMainButton.setEnabled(false);
                if (isRunning) {
                    return;
                }
                setRunning(true);
                startGame();
            }
         });

        swing.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer3.start();
            }
        });
        addsec.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer2.start();
            }
        });

        openall.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer4.start();
            }
        });

    }



    Timer timer1 = new Timer(1000, new ActionListener() {//倒數30sec
        public void actionPerformed(ActionEvent e) {
            txt_Time.setText(time + "");
            time--;
            if(time==0){
                JOptionPane.showMessageDialog(null, "共找出" + count + "對");//共找出幾對  要記得改
                backToMainButton.setEnabled(true);
                // 結束後重新初始化一下面板以便於下一次的執行
                count = 0;
                panel_Pic.removeAll();
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

    Timer timer3 = new Timer(1, new ActionListener() {//swing
        public void actionPerformed(ActionEvent e) {

        }
    });

    Timer timer4 = new Timer(1, new ActionListener() {//openall 10sec
        public void actionPerformed(ActionEvent e) {
            //openallcard();

            timer4.stop();
        }
    });

    /**
     * 開始遊戲
     */
    protected void startGame() {
        new Thread() {//執行緒  可能要改掉  不然監聽上會變慢
            ////@Override
            //Thread.sleep(500);
            public void run() {
                initPicPanels();
                timer1.start();//開始倒數
            }
        }.start();
    }

    /**
     * 初始化圖片面板
     */
    private void initPicPanels() {
        panel_Pic.setLayout(new GridLayout(ROWS, COLUMNS, 10, 10));//10是間隙
        initPictureIndex();//初始化圖片的索引並賦值每個圖片的路徑

        for (int i = 0; i < ROWS * COLUMNS; i++) {
            PicPanel panel_1 = new PicPanel(this, picture[i]);
            panel_Pic.add(panel_1);
            //ImageIO.read(new File();
        }
        try {
            Thread.sleep(3000);//延遲三秒蓋牌
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < panel_Pic.getComponentCount(); i++) {
            Component comp = panel_Pic.getComponent(i);
            if(comp instanceof PicPanel){
                PicPanel panel_1 = (PicPanel)comp;
                panel_1.setLabelPicNull();
            }
        }
    }


    /**
     * 初始化圖片的索引並賦值每個圖片的路徑
     */
    private void initPictureIndex() {
        picture = new String[ROWS * COLUMNS];////裡面放圖片的路徑

        // 這裡沒有檢測圖片目錄中檔案的有效性，需要保證都是圖片型別。
        File file = new File(picDir);
        File[] pics = file.listFiles();

        // 初始化一個ROWS*COLUMNS的int陣列，裡面存放每個圖片的索引
        int[] indexs = getIndexs(picture.length, pics.length);
        for (int i = 0; i < indexs.length; i++) {
            picture[i] = pics[indexs[i]].getAbsolutePath();
            //Image image = ImageIO.read(new File(picPath));
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

    private void openCard() {//一開始跟item
        long startTime = System.currentTimeMillis();//返回當前時間(毫秒)。返回值的時間單位是1毫秒
        while ((System.currentTimeMillis() - startTime) / 1000 <= 3) {//3s
            //setTitle("測試");
            for (int i = 0; i < ROWS * COLUMNS; i++) {
                //picture[i] = pics[indexs[i]].getAbsolutePath();
            }

        }
    }

    public PicPanel getPreOne() {
        return preOne;
    }

    public void setPreOne(PicPanel preOne) {
        this.preOne = preOne;
    }

    public void addCount() {
        count++;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

}

class Rule extends JFrame{
    public Rule(){
        super("rule"); 
        //JPanel ruleloca = new JPanel();
        JLabel ruletext = new JLabel("       30秒內盡量翻開一對的卡牌,可使用道具翻開全部卡面三秒,採計分制");
        this.add(ruletext);
        setSize(450,250); 
        setLocationRelativeTo(null);
        //setText("")
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); 
        setVisible(true); 
    }
}
