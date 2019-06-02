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
    private String picDir = "data/cards";
    private String[] picture;//照片索引
    protected boolean isStart;
    private PicPanel preOne = null;
    /**
     * 用於標示已找到的對數
     */
    private int count;
    private JPanel panel_Pic;

    public RememberCard() {

        panel_Pic = new JPanel();//右邊的面板
        
        setTitle("相同卡牌");
        JPanel panel_another = new JPanel();//左邊的面板
        JPanel panel_another1 = new JPanel();//時間的面板
        JPanel panel_another2 = new JPanel();//rule的面板
        JPanel panel_another3 = new JPanel();//item的面板
        JPanel panel_another4 = new JPanel();//exp and money的面板
        
        getContentPane().add(panel_another, BorderLayout.WEST);
        getContentPane().add(panel_Pic, FlowLayout.CENTER);
        panel_another.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        panel_Pic.setSize(900, 675);//右邊的面板
        panel_Pic.setBackground(Color.blue);

        panel_another.setPreferredSize(new Dimension(300, 675));//左邊的面板
        panel_another.setBackground(Color.yellow);

        panel_another1.setPreferredSize(new Dimension(300, 168));
        panel_another1.setBackground(Color.green);

        panel_another2.setPreferredSize(new Dimension(300, 168));
        panel_another2.setBackground(Color.red);

        panel_another3.setPreferredSize(new Dimension(300, 168));
        panel_another3.setBackground(Color.gray);

        panel_another4.setPreferredSize(new Dimension(300, 168));

        //////////////////////////////////////
        ////////time//////////////////////////
        JLabel lbl_Time = new JLabel("已經過");
        panel_another1.add(lbl_Time);//放在上面 panel time

        txt_Time = new JTextField();
        txt_Time.setEditable(false);    
        txt_Time.setColumns(10);////放秒的框框
        panel_another1.add(txt_Time);

        JLabel lbl_Unit = new JLabel("秒");
        panel_another1.add(lbl_Unit);
        panel_another.add(panel_another1);
        //////////////////////////////////////
        ////////rule//////////////////////////
        JLabel lbl_rule = new JLabel("規則:");
        panel_another2.add(lbl_rule);//放在上面 panel time

        JButton btn_rule = new JButton("點擊看規則!");
        panel_another2.add(btn_rule);
        panel_another.add(panel_another2);
        ///////////////////////////////////////////////////////////////

        ////////item//////////////////////////
        JLabel lbl_item = new JLabel("道具/裝備:");
        panel_another3.add(lbl_item);
        
        panel_another.add(panel_another3);
        ///////////////////////////////////////////////////////////////
        ////////exp and money//////////////////////////
        panel_another4.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel lbl_exp = new JLabel("exp:");
        JLabel lbl_money = new JLabel("money:");
        lbl_exp.setPreferredSize(new Dimension(300, 50));
        panel_another4.add(lbl_exp);
        panel_another4.add(lbl_money);

        panel_another.add(panel_another4);
        ///////////////////////////////////////////////////////////////

        //JPanel panel_Time = new JPanel();//新的面板
        JButton btn_Start = new JButton("開始遊戲");
        panel_Pic.add(btn_Start);

        getContentPane().add(panel_Pic, BorderLayout.CENTER);///照片的位置

        /*btn_Item.addActionListener(new ActionListener() {//道具的按鈕click
            public void actionPerformed(ActionEvent e) {//按下道具後  框框的時間還在跑 得改
                openCard();//翻開全部的卡三秒
            }

        });*/

        btn_Start.addActionListener(new ActionListener() {//開始遊戲的按鈕click
            @Override
            public void actionPerformed(ActionEvent e) {
                panel_Pic.remove(btn_Start);
                if (isRunning) {
                    return;
                }
                setRunning(true);
                startGame();

            }

        });
        ///////////////////////////////////////////////////////
        btn_rule.addActionListener(new ActionListener() {//rule
            public void actionPerformed(ActionEvent e) {
                JFrame frame_rule = new JFrame("規則說明");
                //frame_rule.setTitle("規則說明");

            }

        });
        //initPicPanels();//初始化圖片面板
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
                    txt_Time.setText(((System.currentTimeMillis() - startTime) / 1000)  + "");//秒數跑的速度
                    if(count > ROWS * COLUMNS / 2) {
                        break;
                    }
                }
                //////////
                JOptionPane.showMessageDialog(null, "共找出" + count + "對");//共找出幾對  要記得改
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

    public void setallPic() {

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

//public class Rule extends JFrame{
    


//}
