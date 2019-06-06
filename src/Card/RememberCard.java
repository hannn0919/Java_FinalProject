package Card;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;



/**
 * @author jqs 主要實現記憶翻牌功能
 */
public class RememberCard extends JFrame {

    /**
     * 初始化遊戲的行列數，行列數成績必須為偶數
     */
    private static final int ROWS = 6;
    private static final int COLUMNS = 5;
    private static final long serialVersionUID = -8908268719780973221L;
    private JTextField txt_Time;
    private boolean isRunning = false;
    /**
     * 存放圖片的目錄，簡單起見，存放圖片的目錄中圖片個數為初始化的行列數乘積的一半
     */
    private String picDir = "cards";
    private String[] picture;//照片索引
    protected boolean isStart;
    private PicPanel preOne = null;
    /**
     * 用於標示已找到的對數
     */
    private int count;
    private JPanel panel_Pic;
    private JButton back;//記得寫
    public RememberCard() {

        panel_Pic = new JPanel();//右邊的面板
        
        //setTitle("相同卡牌");
        JPanel panel_another = new JPanel();//左邊的面板
        JPanel panel_another1 = new JPanel();//返回的面板
        JPanel panel_another2 = new JPanel();//time的面板
        JPanel panel_another3 = new JPanel();//exp and money的面板
        JPanel panel_another4 = new JPanel();//rule的面板
        JPanel panel_another5 = new JPanel();//道具

        //Rule r = new Rule();  //開啟視窗
        getContentPane().add(panel_another, BorderLayout.WEST);
        getContentPane().add(panel_Pic, FlowLayout.CENTER);
        panel_another.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        panel_Pic.setSize(900, 675);//右邊的面板
        panel_Pic.setBorder(BorderFactory.createEmptyBorder(-5, 0, -5, 0));
        //panel_Pic.setBackground(Color.blue);

        panel_another.setPreferredSize(new Dimension(300, 675));//左邊的面板
        panel_another.setBorder(BorderFactory.createEmptyBorder(-5, 0, -5, 0));
        //panel_another.setBackground(Color.yellow);

		////////////////返回//////////////////////
        panel_another1.setPreferredSize(new Dimension(300, 56));//backhome
        //panel_another1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        ImageIcon backhome = new ImageIcon("icon\\backhome.png");
        
        back = new JButton();//記得寫
		back.setBorderPainted(false);
		back.setBorder(null);
		back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        back.setIcon(backhome);
        //back.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5, -5));
        panel_another1.add(back);
        //panel_another1.setBounds(0, 0, 300, 56);
        panel_another.add(panel_another1);
        //panel_another1.setBackground(Color.green);
        /////////////////返回//////////////////////////////////////////
        //////////////////////倒數///////////////////////////////////
        panel_another2.setPreferredSize(new Dimension(300, 56));//discount
        panel_another2.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5, -5));
        panel_another2.setBorder(null);

        ImageIcon icon_discount = new ImageIcon("icon\\discount.png");
        JLabel another_2 = new JLabel();

        //panel_another2.setBorderPainted(false);
		//icon_discount.setBorder(null);
		//panel_another2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        another_2.setIcon(icon_discount);
        //another_2.setBounds()

        txt_Time = new JTextField();
        txt_Time.setEditable(false);    
        txt_Time.setColumns(5);////放秒的框框
        //txt_Time.setBounds(50, 80, 5, 100);

        panel_another2.add(txt_Time);
        //another_2.setBounds(0, 112, 300, 56);
        panel_another2.add(another_2);
        //panel_another2.setBounds(0, 112, 300, 56);
		panel_another.add(panel_another2);
        //////////////////////倒數///////////////////////////////
        ///////////////////////經驗和金錢//////////////////
        panel_another3.setPreferredSize(new Dimension(300, 112));
        panel_another3.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5, -5));
        ImageIcon icon_expandmoney = new ImageIcon("icon\\expandmoney.png");
        JLabel another_3 = new JLabel();
        another_3.setIcon(icon_expandmoney);
        panel_another3.add(another_3);
        //panel_another3.setBackground(Color.gray);
        panel_another3.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel lbl_exp = new JLabel("exp:");
        JLabel lbl_money = new JLabel("money:");
        lbl_exp.setPreferredSize(new Dimension(300, 50));
        panel_another3.add(lbl_exp);
        panel_another3.add(lbl_money);

        panel_another.add(panel_another3);
        ///////////////////////經驗和金錢//////////////////
        //////////////規則//////////////////////////////
        panel_another4.setPreferredSize(new Dimension(300, 168));//rule
        panel_another4.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5,-5));
        ImageIcon icon_rule = new ImageIcon("icon\\rule.png");
        JLabel another_4 = new JLabel();
        another_4.setIcon(icon_rule);
        panel_another4.add(another_4);
		panel_another4.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel lbl_rule = new JLabel("規則:");
        panel_another4.add(lbl_rule);//放在上面 panel time
        JLabel ruletext = new JLabel("<html>30秒內盡量翻開一對的卡牌,可使用道具翻開全部卡<br>面三秒,採計分制</html>");
        panel_another4.add(ruletext);
        
        panel_another.add(panel_another4);
        ////////////////////////規則/////////////////

        /////////////////肝//////////////////////////////
        panel_another5.setPreferredSize(new Dimension(300, 283));//test_charater
        panel_another5.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5, -5));
        ImageIcon icon_charater = new ImageIcon("icon\\test_charater.png");
        JLabel another_5 = new JLabel();
        another_5.setIcon(icon_charater);
        panel_another5.add(another_5);
        JLabel lbl_item = new JLabel("主人翁");
        panel_another5.add(lbl_item);
        
        panel_another.add(panel_another5);
        //////////////////肝///////////////////////////////////////

        JButton btn_Start = new JButton("開始遊戲");
        panel_Pic.add(btn_Start);

        getContentPane().add(panel_Pic, BorderLayout.CENTER);///照片的位置


        //JOptionPane.showMessageDialog(null, "30秒內盡量翻開一對的卡牌,可使用道具翻開全部卡面三秒,採計分制");//共找出幾對  要記得改
        btn_Start.addActionListener(new ActionListener() {//開始遊戲的按鈕click
            @Override
            public void actionPerformed(ActionEvent e) {
                panel_Pic.remove(btn_Start);
                back.setEnabled(false);
                if (isRunning) {
                    return;
                }
                setRunning(true);
                startGame();
                //back.setEnabled(true);
            }

        });

        ///////////////////////////////////////////////////////
        /*btn_rule.addActionListener(new ActionListener() {//rule
            public void actionPerformed(ActionEvent e) {
                
                //frame_rule.setTitle("規則說明");

            }

        });*/
        //initPicPanels();//初始化圖片面板
        //Rule r = new Rule();  //開啟視窗
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
     * 開始遊戲
     */
    //static int hi=0;
    protected void startGame() {
        new Thread() {//執行緒  可能要改掉  不然監聽上會變慢
            ////@Override
            //Thread.sleep(500);

            public void run() {
                initPicPanels();
                //改時間的地方
                long startTime = System.currentTimeMillis();//返回當前時間(毫秒)。返回值的時間單位是1毫秒
                while ((System.currentTimeMillis() - startTime) / 1000 <= 30) {//30s結束或全部找完
                    long a=(Math.abs(System.currentTimeMillis() - startTime) / 1000)-30;
                    txt_Time.setText(  Math.abs(a) + "");//秒數跑的速度
                    if(count > ROWS * COLUMNS / 2) {
                        break;
                    }
                }
                //////////
                JOptionPane.showMessageDialog(null, "共找出" + count + "對");//共找出幾對  要記得改
                back.setEnabled(true);
                // 結束後重新初始化一下面板以便於下一次的執行

                count = 0;
                panel_Pic.removeAll();

                txt_Time.setText(null);
                panel_Pic.validate();
                isRunning = false;
                /////////////////初始化完成
            }
        }.start();
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
