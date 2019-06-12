package Card;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

/**
 * @author jqs

 *         圖片面板，主要實現了圖片的顯示與圖片相同判斷
 */
public class PicPanel extends JPanel {
    private static final long serialVersionUID = 2172162568449349737L;
    protected String picPath;//路徑
    protected JLabel lbl_Pic = new JLabel();
    private ImageIcon bgIcon = new ImageIcon("data/cards/icon/test_charater.png");
    private ImageIcon cardback = new ImageIcon("data/cards/icon/test_charater.png");
    private boolean isShow = false;
    private RememberCard parent;
    private boolean finished = false;

    public PicPanel(RememberCard rememberCard, String picPath) {
        this.picPath = picPath;
        this.parent = rememberCard;
        this.setBorder(new CompoundBorder(null, new LineBorder(new Color(0, 0, 0), 1)));
        this.setLayout(new BorderLayout());
        this.add(lbl_Pic, BorderLayout.CENTER);
        this.addMouseListener(mouseAdapter);
        initLabelImage();
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    /**
     * 圖片面板的滑鼠事件監聽，配對過程在此完成
     */
    private MouseAdapter mouseAdapter = new MouseAdapter() {
        @Override

        public void mouseClicked(MouseEvent e) {
            new Thread() {
                public void run() {
                    if (!parent.isRunning() || finished) {
                        return;
                    }
                    isShow = !isShow;//true  點到的物件
                    if (isShow) {
                        if (bgIcon == null) {//點到沒翻開的
                            initLabelImage();
                        }
                        PicPanel curOne = (PicPanel) lbl_Pic.getParent();//第2個
                        PicPanel preOne = parent.getPreOne();//第1個
                        if (preOne == null) {
                            parent.setPreOne(curOne);
                        }
                        else {
                            boolean right = checkRight(curOne, preOne);
                            if (right) {//配對成功
                                parent.setPreOne(null);
                                curOne.setFinished(true);
                                preOne.setFinished(true);
                                parent.addCount();
                            }
                            else {
                                lbl_Pic.setIcon(bgIcon);
                                repaint();
                                try {
                                    Thread.sleep(500);//0.5s後蓋掉卡片
                                }
                                catch (InterruptedException e1) {
                                    e1.printStackTrace();
                                }
                                lbl_Pic.setIcon(cardback);
                                isShow = !isShow;
                                repaint();
                                preOne.getMouseListeners()[0].mouseClicked(null);
                                parent.setPreOne(null);
                                return;
                            }
                        }
                        lbl_Pic.setIcon(bgIcon);
                    }
                    else {
                        lbl_Pic.setIcon(cardback);
                    }
                    repaint();
                };
            }.start();
        }

        /**
         * 檢查兩個面板顯示的圖片是否一致，根據圖片的路徑來判斷，同時要保證兩個面板不是同一個面板
         *
         * @param curOne
         * @param preOne
         * @return
         */
        private boolean checkRight(PicPanel curOne, PicPanel preOne) {
            return curOne.getPicPath().equals(preOne.getPicPath()) && !curOne.equals(preOne);
        }

    };

    /**
     * 初始化Label物件的image
     */
    private void initLabelImage() {//設成圖片
        try {
            Image image = ImageIO.read(new File(picPath));//顯示圖片
            if (image != null) {
                int lblWidth = 130;//圖片的長寬
                int lblHeight = 160;
                bgIcon = new ImageIcon(image.getScaledInstance(lblWidth, lblHeight, Image.SCALE_DEFAULT));
                lbl_Pic.setIcon(bgIcon);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    protected void setCardback(){
        lbl_Pic.setIcon(cardback);
    }


    /**
     * 當找到配對的圖片面板後設置完成狀態為true，此時點選圖片面板已經無效了。
     *
     * @param b
     */
    protected void setFinished(boolean b) {
        finished = b;
    }

    public void setLabelPicNull() {
        lbl_Pic.setIcon(null);
    }
}


