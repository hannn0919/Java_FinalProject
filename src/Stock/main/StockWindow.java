package Stock.main;


import House.house.House;
import Main.Main;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.border.EmptyBorder;

public class StockWindow extends JPanel {
    private Main mainFrame;
    private House house;
    private JTextField buyticketField;
    private JTextField saleticketField;
    private JTextField buyMoneyField;
    private JTextField saleMoneyField;
    private JTextField ticketNumField;
    private JTextField Price;
    private JTextField percent;
    private JButton SaleButton;
    private JButton BuyButton;
    private JLabel pronoucingLabel;
    private JButton Stock1, Stock2, Stock3, Stock4;
    private Font f = new Font("微軟正黑體", Font.BOLD, 20);
    private JLabel StockPic;
    private int select = 0;
    private ImageIcon backToMainImage, expandMoneyImage, ruleImage, liverIcon;
    private JButton backToMainButton;
    private JLabel expandMoneyLabel, ruleLabel, liverLabel;
    private JLabel expFromMain, moneyFromMain;



    public static final String STOCK[] = {
            "微積分", "普通物理", "線性代數", "程式設計"
    };

    public StockWindow(Main mainFrame, House house) {
        this.house = house;
        this.mainFrame = mainFrame;
        this.setSize(1200, 675);
        this.setLayout(null);
        for (int i = 0; i < 4; i++) {
            makePNG(i);
        }

        Handler handler = new Handler();
        MouseHandler Mhandler = new MouseHandler();
        ImageIcon Stock1Image = new ImageIcon("data/Stock/image/" + STOCK[0] + ".png");
        Stock1 = new JButton(Stock1Image);
        Stock1.setBounds(340, 20, 180, 63);
        cleanButtom(Stock1);
        Stock1.addActionListener(handler);
        Stock1.addMouseListener(Mhandler);
        add(Stock1);

        ImageIcon Stock2Image = new ImageIcon("data/Stock/image/" + STOCK[1] + ".png");
        Stock2 = new JButton(Stock2Image);
        Stock2.setBounds(340, 84, 180, 63);
        cleanButtom(Stock2);
        Stock2.addActionListener(handler);
        Stock2.addMouseListener(Mhandler);
        add(Stock2);

        ImageIcon Stock3Image = new ImageIcon("data/Stock/image/" + STOCK[2] + ".png");
        Stock3 = new JButton(Stock3Image);
        Stock3.setBounds(340, 148, 180, 63);
        cleanButtom(Stock3);
        Stock3.addActionListener(handler);
        Stock3.addMouseListener(Mhandler);
        add(Stock3);

        ImageIcon Stock4Image = new ImageIcon("data/Stock/image/" + STOCK[3] + ".png");
        Stock4 = new JButton(Stock3Image);
        Stock4.setBounds(340, 212, 180, 63);
        cleanButtom(Stock4);
        Stock4.addActionListener(handler);
        Stock4.addMouseListener(Mhandler);
        add(Stock4);

        try {
            Image Pic = ImageIO.read(new File("data/Stock/Stock0.png"));
            ImageIcon temp = new ImageIcon(Pic);
            StockPic = new JLabel(temp);
        }
        catch (Exception ex) {
            System.out.println("No example.jpg!!");
        }


        StockPic.setBounds(560, 20, 625, 400);
        add(StockPic);

        ticketNumField = new JTextField(String.valueOf(house.getStockTicket()[0]));
        TextFieldFix(ticketNumField);
        ticketNumField.setBounds(530, 470, 100, 40);
        ticketNumField.setFont(f);

        Price = new JTextField(String.format("%.2f", house.getStock()[0]));
        TextFieldFix(Price);
        Price.setBounds(530, 520, 100, 40);
        Price.setFont(f);

        percent = new JTextField(String.format("%.2f", house.getStockPrs()[0]));
        TextFieldFix(percent);
        percent.setBounds(530, 570, 100, 40);
        percent.setFont(f);
        add(ticketNumField);
        add(Price);
        add(percent);


        ImageIcon BuyBtnImage = new ImageIcon("data/Stock/image/買入.png");
        BuyButton = new JButton(BuyBtnImage);
        BuyButton.setBounds(1060, 470, 90, 61);
        cleanButtom(BuyButton);
        BuyButton.addActionListener(handler);
        BuyButton.addMouseListener(Mhandler);
        add(BuyButton);

        ImageIcon SaleBtnImage = new ImageIcon("data/Stock/image/賣出.png");
        SaleButton = new JButton(SaleBtnImage);
        SaleButton.setBounds(1060, 530, 90, 61);
        cleanButtom(SaleButton);
        SaleButton.addActionListener(handler);
        SaleButton.addMouseListener(Mhandler);
        add(SaleButton);

        buyticketField = new JTextField();
        buyticketField.setFont(f);
        buyticketField.setBounds(760, 470, 80, 40);
        buyMoneyField = new JTextField("0元");
        TextFieldFix(buyMoneyField);
        buyMoneyField.setFont(f);
        buyMoneyField.setBounds(850, 470, 200, 40);
        buyMoneyField.setEditable(false);
        saleticketField = new JTextField();
        saleticketField.setFont(f);
        saleticketField.setBounds(760, 530, 80, 40);
        saleMoneyField = new JTextField("0元");
        TextFieldFix(saleMoneyField);

        saleMoneyField.setFont(f);
        saleMoneyField.setBounds(850, 530, 200, 40);
        saleMoneyField.setEditable(false);

        pronoucingLabel = new JLabel("");
        pronoucingLabel.setBounds(680, 600, 500, 30);
        pronoucingLabel.setFont(f);


        saleticketField.addActionListener(handler);
        buyticketField.addActionListener(handler);

        add(buyMoneyField);
        add(buyticketField);
        add(saleMoneyField);
        add(saleticketField);
        add(pronoucingLabel);

        ImageIcon img = new ImageIcon("data/Stock/image/股市background.png");
        Image i = img.getImage();
        i = i.getScaledInstance(900, 675, Image.SCALE_SMOOTH);
        JLabel background = new JLabel();
        background.setIcon(new ImageIcon(i));
        background.setSize(900, 675);
        background.setBounds(300, 0, 900, 675);
        this.add(background);

        backToMainImage = new ImageIcon("data/gamebar/backhome.png");
        backToMainButton = new JButton(backToMainImage);
        backToMainButton.setBounds(0, 0, backToMainImage.getIconWidth(), backToMainImage.getIconHeight());
        cleanButtom(backToMainButton);
        backToMainButton.setEnabled(true);
        backToMainButton.addActionListener(handler);
        backToMainButton.addMouseListener(Mhandler);
        add(backToMainButton);

        ruleImage = new ImageIcon("data/Stock/image/rule.png");
        ruleLabel = new JLabel(ruleImage);
        ruleLabel.setBounds(0, backToMainImage.getIconHeight(), ruleImage.getIconWidth(), ruleImage.getIconHeight());
        add(ruleLabel);

        String character = "data/dinosaur/character/"+house.getLevel()+"/肝";
        if(house.getEquipment("透視眼鏡")==1) character += "+眼鏡";

        if(house.getEquipment("竹蜻蜓")==1) character += "+竹蜻蜓";

        if(house.getEquipment("翅膀")==1) character += "+翅膀";

        if(house.getEquipment("彈簧鞋")==1) character += "+彈簧鞋";

        character += ".png";
        // 左側欄位下方主角顯示Label設置
        ImageIcon temp = new ImageIcon(character);
        ImageIcon liverImg = resize(temp.getIconWidth()+40, temp.getIconHeight()+32, temp);
        liverLabel = new JLabel(liverImg);
        liverLabel.setBounds(50,backToMainImage.getIconHeight()+ruleImage.getIconHeight()+80,liverImg.getIconWidth(),liverImg.getIconHeight());
        add(liverLabel);

        int heightTotal = 0;
        heightTotal += backToMainImage.getIconHeight();
        heightTotal += ruleImage.getIconHeight();

        expFromMain = new JLabel("");
        expFromMain.setFont(new Font("微軟正黑體", Font.BOLD, 17));
        expFromMain.setBounds(140, heightTotal + 13, 150, 25);
        expFromMain.setText(Integer.toString(house.getExp()));
        add(expFromMain, JLayeredPane.MODAL_LAYER);

        moneyFromMain = new JLabel("");
        moneyFromMain.setFont(new Font("微軟正黑體", Font.BOLD, 17));
        moneyFromMain.setBounds(140, heightTotal + 50, 150, 25);
        moneyFromMain.setText(Integer.toString(house.getHoldMoney()));
        add(moneyFromMain, JLayeredPane.MODAL_LAYER);

        expandMoneyImage = new ImageIcon("data/gamebar/expandmoney.png");
        expandMoneyLabel = new JLabel(expandMoneyImage);
        expandMoneyLabel.setBounds(0, backToMainImage.getIconHeight() + ruleImage.getIconHeight(), expandMoneyImage.getIconWidth(), expandMoneyImage.getIconHeight());
        add(expandMoneyLabel);
    }

    private class Handler implements ActionListener {
        // handle button event
        @Override
        public void actionPerformed(ActionEvent event) {

            if (event.getSource() == SaleButton) {
                int dollar = (int) (Integer.parseInt(saleticketField.getText()) * 200 * house.getStock()[select]);
                saleMoneyField.setText(dollar + " 元");
                try {
                    if (Integer.parseInt(saleticketField.getText()) == 0) {
                        pronoucingLabel.setText("請輸入要賣出幾張股票，每張兩百股");
                    } else if (house.getStockTicket()[select] < Integer.parseInt(saleticketField.getText())) {
                        pronoucingLabel.setText("股票張數不足無法賣出");
                    } else if (house.getStockTicket()[select] >= Integer.parseInt(saleticketField.getText())) {
                        house.setStockTicket(select, house.getStockTicket()[select]
                                - Integer.parseInt(saleticketField.getText()));
                        house.setHoldMoney(house.getHoldMoney() + dollar);
                        moneyFromMain.setText(Integer.toString(house.getHoldMoney()));
                        pronoucingLabel.setText("謝謝惠顧");
                    }
                } catch (NumberFormatException e) {
                    pronoucingLabel.setText("請輸入整數");
                }

            } else if (event.getSource() == BuyButton) {
                try {
                    int dollar = (int) (Integer.parseInt(buyticketField.getText()) * 200 * house.getStock()[select]);
                    buyMoneyField.setText(dollar + " 元");
                    if (Integer.parseInt(buyticketField.getText()) == 0) {
                        pronoucingLabel.setText("請輸入要買幾張股票，每張兩百股");
                    } else if (house.getHoldMoney() < dollar) {
                        pronoucingLabel.setText("現金不足無法購買");
                    } else if (house.getHoldMoney() >= dollar) {
                        house.setStockTicket(select, house.getStockTicket()[select]
                                + Integer.parseInt(buyticketField.getText()));
                        house.setHoldMoney(house.getHoldMoney() - dollar);
                        moneyFromMain.setText(Integer.toString(house.getHoldMoney()));
                        pronoucingLabel.setText("謝謝惠顧");
                    }
                } catch (NumberFormatException e) {
                    System.out.println(e);
                }

            } else if (event.getSource() == buyticketField) {
                int dollar = (int) (Integer.parseInt(buyticketField.getText()) * 200 * house.getStock()[select]);
                buyMoneyField.setText(dollar + " 元");
                if (Integer.parseInt(buyticketField.getText()) == 0) {
                    pronoucingLabel.setText("請輸入要買幾張股票，每張兩百股");
                } else if (house.getHoldMoney() < dollar) {
                    pronoucingLabel.setText("現金不足無法購買");
                } else if (house.getHoldMoney() >= dollar) {
                    pronoucingLabel.setText("點選買入按鈕購買股票");
                }

            } else if (event.getSource() == saleticketField) {
                int dollar = (int) (Integer.parseInt(saleticketField.getText()) * 200 * house.getStock()[select]);
                saleMoneyField.setText(dollar + " 元");
                if (Integer.parseInt(buyticketField.getText()) == 0) {
                    pronoucingLabel.setText("請輸入要賣出幾張股票，每張兩百股");
                } else if (house.getStockTicket()[select] < Integer.parseInt(saleticketField.getText())) {
                    pronoucingLabel.setText("股票張數不足無法賣出");
                } else if (house.getStockTicket()[select] >= Integer.parseInt(saleticketField.getText())) {
                    pronoucingLabel.setText("點選賣出按鈕賣出股票");
                }
            } else if (event.getSource() == Stock1) {
                select = 0;

            } else if (event.getSource() == Stock2) {
                select = 1;

            } else if (event.getSource() == Stock3) {
                select = 2;

            } else if (event.getSource() == Stock4) {
                select = 3;

            } else if (event.getSource() == backToMainButton) {
                mainFrame.changeToMainScreen();
                nextStock(house);
                house.updataData();
                for (int i = 0; i < 4; i++) {
                    makePNG(i);
                }
            }
            reSetAll();
        }
    }

    public void reSetAll() {
        ticketNumField.setText(String.valueOf(house.getStockTicket()[select]));
        Price.setText(String.format("%.2f", house.getStock()[select]));
        percent.setText(String.format("%.2f", house.getStockPrs()[select]));
        try {
            Image Pic = ImageIO.read(new File("data/Stock/Stock" + select + ".png"));
            ImageIcon temp = new ImageIcon(Pic);
            StockPic.setIcon(temp);
        }
        catch (Exception ex) {
            System.out.println("No example.jpg!!");
        }

         //repaint();
    }

    public void makePNG(int i) {
        CategoryDataset dataset = createDataset(house.getData()[i]);
        // 步骤2：根据Dataset 生成JFreeChart对象，以及做相应的设置
        JFreeChart freeChart = createChart(dataset, "");
        // 步骤3：将JFreeChart对象输出到文件，Servlet输出流等
        saveAsFile(freeChart, "data//Stock//Stock" + i + ".png", 625, 400);
    }

    public static void nextStock(House h) {
        for (int i = 0; i < 4; i++) {
            h.setStockPrs(i, getNextPercent());
            h.setStock(i, h.getStock()[i] * (1F + h.getStockPrs()[i]));
        }
    }

    public static float getNextPercent() {
        Random random = new Random();
        float tempf;
        if (random.nextFloat() > 0.3) {
            tempf = random.nextFloat();
            tempf = tempf - 0.5F;
        } else {
            tempf = random.nextFloat();
            tempf = tempf * 0.6F;
            tempf = tempf - 0.3F;
        }
        return tempf;
    }

    public static void saveAsFile(JFreeChart chart, String outputPath,
                                  int weight, int height) {
        FileOutputStream out = null;
        try {
            File outFile = new File(outputPath);
            if (!outFile.getParentFile().exists()) {
                outFile.getParentFile().mkdirs();
            }
            out = new FileOutputStream(outputPath);
            // 保存为PNG
            ChartUtilities.writeChartAsPNG(out, chart, 625, 400);
            // 保存为JPEG
            //ChartUtilities.writeChartAsJPEG(out, chart, 600, 400);
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    // do nothing
                }
            }
        }
    }

    public static JFreeChart createChart(CategoryDataset categoryDataset, String s) {
        // 创建JFreeChart对象：ChartFactory.createLineChart
        JFreeChart jfreechart = ChartFactory.createLineChart(s, // 标题
                "", // categoryAxisLabel （category轴，横轴，X轴标签）
                "", // valueAxisLabel（value轴，纵轴，Y轴的标签）
                categoryDataset, // dataset
                PlotOrientation.VERTICAL, true, // legend
                false, // tooltips
                false); // URLs
        // 使用CategoryPlot设置各种参数。以下设置可以省略。
        CategoryPlot plot = (CategoryPlot) jfreechart.getPlot();
        // 背景色 透明度
        plot.setBackgroundAlpha(0.5f);
        // 前景色 透明度
        plot.setForegroundAlpha(0.5f);
        // 其他设置 参考 CategoryPlot类
        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        renderer.setBaseShapesVisible(true); // series 点（即数据点）可见
        renderer.setBaseLinesVisible(true); // series 点（即数据点）间有连线可见
        renderer.setSeriesStroke(0, new BasicStroke(5F));
        return jfreechart;
    }

    public static CategoryDataset createDataset(double[][] data) {
        return DatasetUtilities.createCategoryDataset("", "", data);
    }

    public void cleanButtom(JButton button) {
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorder(null);
    }

    // 文字框去除背景、去除邊框、靠右對齊、不可編輯
    public void TextFieldFix(JTextField field) {
        field.setOpaque(false);
        field.setEditable(false);
        field.setHorizontalAlignment(JTextField.RIGHT);
        field.setBorder(BorderFactory.createLineBorder(Color.BLACK, 0));
    }

    private class MouseHandler implements MouseListener {
        @Override
        public void mousePressed(MouseEvent e) {
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
        @Override
        public void mouseReleased(MouseEvent e) {}
        @Override
        public void mouseClicked(MouseEvent e) {}
        @Override
        public void mouseEntered(MouseEvent e) {
            if (e.getSource() == BuyButton) {
                buttonSound();
                BuyButton.setIcon(resize(BuyButton.getIcon().getIconWidth() + 10, BuyButton.getIcon().getIconHeight() + 10, (ImageIcon) BuyButton.getIcon()));
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            } else if (e.getSource() == SaleButton) {
                buttonSound();
                SaleButton.setIcon(resize(SaleButton.getIcon().getIconWidth() + 10, SaleButton.getIcon().getIconHeight() + 10, (ImageIcon) SaleButton.getIcon()));
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            } else if (e.getSource() == backToMainButton) {
                buttonSound();
                backToMainButton.setIcon(resize(backToMainButton.getIcon().getIconWidth() + 10, backToMainButton.getIcon().getIconHeight() + 10, (ImageIcon) backToMainButton.getIcon()));
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            } else if (e.getSource() == Stock1) {
                buttonSound();
                Stock1.setIcon(resize(Stock1.getIcon().getIconWidth() + 10, Stock1.getIcon().getIconHeight() + 10, (ImageIcon) Stock1.getIcon()));
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            } else if (e.getSource() == Stock2) {
                buttonSound();
                Stock2.setIcon(resize(Stock2.getIcon().getIconWidth() + 10, Stock2.getIcon().getIconHeight() + 10, (ImageIcon) Stock2.getIcon()));
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            } else if (e.getSource() == Stock3) {
                buttonSound();
                Stock3.setIcon(resize(Stock3.getIcon().getIconWidth() + 10, Stock3.getIcon().getIconHeight() + 10, (ImageIcon) Stock3.getIcon()));
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            } else if (e.getSource() == Stock4) {
                buttonSound();
                Stock4.setIcon(resize(Stock4.getIcon().getIconWidth() + 10, Stock4.getIcon().getIconHeight() + 10, (ImageIcon) Stock4.getIcon()));
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        }

        @Override
        public void mouseExited(MouseEvent arg0) {
            if (arg0.getSource() == BuyButton) {
                BuyButton.setIcon(new ImageIcon("data/Stock/image/買入.png"));
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            } else if (arg0.getSource() == SaleButton) {
                SaleButton.setIcon(new ImageIcon("data/Stock/image/賣出.png"));
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            } else if (arg0.getSource() == backToMainButton) {
                backToMainButton.setIcon(new ImageIcon("data/gamebar/backhome.png"));
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            } else if (arg0.getSource() == Stock1) {
                Stock1.setIcon(new ImageIcon("data/Stock/image/" + STOCK[0] + ".png"));
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            } else if (arg0.getSource() == Stock2) {
                Stock2.setIcon(new ImageIcon("data/Stock/image/" + STOCK[1] + ".png"));
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            } else if (arg0.getSource() == Stock3) {
                Stock3.setIcon(new ImageIcon("data/Stock/image/" + STOCK[2] + ".png"));
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            } else if (arg0.getSource() == Stock4) {
                Stock4.setIcon(new ImageIcon("data/Stock/image/" + STOCK[3] + ".png"));
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        }
    }


    public ImageIcon resize(int width, int height, ImageIcon img) {
        Image i = img.getImage();
        Image new_img = i.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(new_img);
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
}
