package Card;

import java.awt.BorderLayout;
import java.awt.Component;
//import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
//import java.awt.Image;
//import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.LinkedList;
import java.util.Random;

//import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



/**
 * @author jqs 主要實現記憶翻牌功能
 */
public class RememberCard extends JFrame {

    /**
     * 初始化遊戲的行列數，行列數成績必須為偶數
     */
    private static final int ROWS = 4;
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
        setTitle("相同卡牌");

        JPanel panel_Time = new JPanel();//新的面板
        getContentPane().add(panel_Time, BorderLayout.NORTH);//放在上面 panel time

        JLabel lbl_Time = new JLabel("已經過");
        panel_Time.add(lbl_Time);//放在上面 panel time

        txt_Time = new JTextField();
        txt_Time.setEditable(false);
        panel_Time.add(txt_Time);
        txt_Time.setColumns(10);////放秒的框框

        JLabel lbl_Unit = new JLabel("秒");
        panel_Time.add(lbl_Unit);

        JButton btn_Start = new JButton("開始遊戲");
        panel_Time.add(btn_Start);

        JButton btn_Item = new JButton("道具");
        panel_Time.add(btn_Item);
        /////////////////////////////////////

        panel_Pic = new JPanel();
        getContentPane().add(panel_Pic, BorderLayout.CENTER);///照片的位置

        btn_Item.addActionListener(new ActionListener() {//道具的按鈕click
            public void actionPerformed(ActionEvent e) {//按下道具後  框框的時間還在跑 得改
                openCard();//翻開全部的卡三秒
            }

        });

        btn_Start.addActionListener(new ActionListener() {//開始遊戲的按鈕click
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isRunning) {
                    return;
                }
                setRunning(true);
                startGame();

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
