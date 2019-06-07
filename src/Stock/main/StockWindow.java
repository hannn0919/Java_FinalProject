package Stock.main;


import House.house.House;
import Main.Main;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import javax.swing.border.EmptyBorder;

public class StockWindow extends JPanel{
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
    private final int X = 600;
    private final int Y = 500;
    private JButton Stock1, Stock2, Stock3 , Stock4;
    private Font f = new Font("微軟正黑體",Font.BOLD,20);
    private JLabel StockPic;
    private JTextField moneyField;
    private JButton BackButton;
    private int select = 0;

    public static final String STOCK[] = {
            "微積分", "普通物理", "線性代數", "程式設計"
    };

    public StockWindow(Main mainFrame, House house)
    {
        this.house  = house;
        
        this.mainFrame = mainFrame;
        this.setSize(1200, 675);
        this.setLayout(null);
        for(int i = 0;i<4;i++){
            makePNG(i);
        }

        Handler handler = new Handler();
        moneyField = new JTextField("擁有" + String.valueOf(house.getHoldMoney()) + "元");
        moneyField.setBounds(350, 200, 150, 30);
        moneyField.setFont(f);
        TextFieldFix(moneyField);
        add(moneyField);

        BackButton = new JButton("返回");
        BackButton.setBounds(350, 250, 100, 30);
        BackButton.setFont(f);
        BackButton.addActionListener(handler);
        add(BackButton);



        Stock1 = new JButton(STOCK[0]);
        Stock1.setBounds(350, 20 , 150, 30);
        Stock1.setFont(f);
        Stock2 = new JButton(STOCK[1]);
        Stock2.setBounds(350, 50 , 150, 30);
        Stock2.setFont(f);

        Stock3 = new JButton(STOCK[2]);
        Stock3.setBounds(350, 80 , 150, 30);
        Stock3.setFont(f);
        Stock4 = new JButton(STOCK[3]);
        Stock4.setBounds(350, 110 , 150, 30);
        Stock4.setFont(f);
        Stock1.addActionListener(handler);
        Stock2.addActionListener(handler);
        Stock3.addActionListener(handler);
        Stock4.addActionListener(handler);
        add(Stock1);
        add(Stock2);
        add(Stock3);
        add(Stock4);

        StockPic = new JLabel(new ImageIcon("data//Stock//Stock0.png"));
        StockPic.setBounds(550 , 20, 600, 400);
        add(StockPic);

        JLabel ticketNum  = new JLabel("持有張數：");
        ticketNum.setBounds(350, Y-50, 120, 30);
        ticketNum.setFont(f);
        ticketNumField = new JTextField(String.valueOf(house.getStockTicket()[0]));
        TextFieldFix(ticketNumField);
        ticketNumField.setBounds(470, Y-50, 80, 30);
        ticketNumField.setFont(f);

        JLabel priceLabel  = new JLabel("及時股價：");
        priceLabel.setBounds(350, Y-20, 120, 30);
        priceLabel.setFont(f);
        Price = new JTextField(String.format("%.2f",house.getStock()[0]));
        TextFieldFix(Price);
        Price.setBounds(470, Y-20, 80, 30);
        Price.setFont(f);

        JLabel perLabel  = new JLabel("漲跌幅：");
        perLabel.setBounds(350, Y+10, 120, 30);
        perLabel.setFont(f);

        percent = new JTextField(String.format("%.2f",house.getStockPrs()[0]));
        TextFieldFix(percent);
        percent.setBounds(470, Y+10, 80, 30);
        percent.setFont(f);

        add(ticketNum);
        add(ticketNumField);
        add(priceLabel);
        add(Price);
        add(perLabel);
        add(percent);



        JLabel buyLabel = new JLabel("買入");
        buyLabel.setFont(f);
        buyLabel.setBounds(X, Y, 100, 30);
        buyticketField = new JTextField();
        buyticketField.setFont(f);
        buyticketField.setBounds(X + 100, Y, 200, 30);
        buyMoneyField = new JTextField("0元");
        TextFieldFix(buyMoneyField);
        buyMoneyField.setFont(f);
        buyMoneyField.setBounds(X + 300, Y, 100, 30);
        buyMoneyField.setEditable(false);

        BuyButton = new JButton("買入");
        BuyButton.setBounds(X+ 400, Y, 100, 30);
        BuyButton.setFont(f);
        // BuyButton.setEnabled(false);
        JLabel saleLabel = new JLabel("賣出");
        saleLabel.setFont(f);
        saleLabel.setBounds(X, Y +30, 100, 30);
        saleticketField = new JTextField();
        saleticketField.setFont(f);
        saleticketField.setBounds(X+100, Y + 30, 200, 30);
        saleMoneyField = new JTextField("0元");
        TextFieldFix(saleMoneyField);
        saleMoneyField.setFont(f);
        saleMoneyField.setBounds(X + 300, Y +30, 100, 30);
        saleMoneyField.setEditable(false);
        SaleButton = new JButton("賣出");
        SaleButton.setFont(f);
        SaleButton.setBounds(X + 400 , Y +30, 100, 30);
        //SaleButton.setEnabled(false);

        pronoucingLabel = new JLabel("我好帥");
        pronoucingLabel.setBounds(X, Y+60, 100, 30);
        pronoucingLabel.setFont(f);

        BuyButton.addActionListener(handler);
        SaleButton.addActionListener(handler);
        saleticketField.addActionListener(handler);
        buyticketField.addActionListener(handler);



        add(buyLabel);
        add(buyMoneyField);
        add(buyticketField);
        add(BuyButton);
        add(saleLabel);
        add(saleMoneyField);
        add(saleticketField);
        add(SaleButton);
        add(pronoucingLabel);

    }

    private class Handler implements ActionListener {
        // handle button event
        @Override
        public void actionPerformed(ActionEvent event) {

            if( event.getSource() == SaleButton){
                int  dollar = (int) (Integer.parseInt(saleticketField.getText()) * 200 * house.getStock()[ select] );
                saleMoneyField.setText( dollar  + " 元");
                try{
                    if(Integer.parseInt(buyticketField.getText()) == 0){
                        pronoucingLabel.setText("請輸入要賣出幾張股票，每張兩百股");
                    }else if(house.getStockTicket()[select ] < Integer.parseInt(saleticketField.getText())){
                        pronoucingLabel.setText("股票張數不足無法賣出");
                    }else if(house.getStockTicket()[ select] >= Integer.parseInt(saleticketField.getText())){
                        house.setStockTicket( select,  house.getStockTicket()[ select ]
                                - Integer.parseInt(saleticketField.getText()));
                        house.setHoldMoney(house.getHoldMoney() + dollar);
                        moneyField.setText(String.valueOf(house.getHoldMoney()));
                        pronoucingLabel.setText("謝謝惠顧");
                    }
                }
                catch (NumberFormatException e ){
                    pronoucingLabel.setText("請輸入要賣出幾張股票，每張兩百股");
                }

            }else if(event.getSource() == BuyButton){
                int  dollar = (int) (Integer.parseInt(buyticketField.getText()) * 200 * house.getStock()[ select] );
                buyMoneyField.setText( dollar  + " 元");
                if(Integer.parseInt(buyticketField.getText()) == 0){
                    pronoucingLabel.setText("請輸入要買幾張股票，每張兩百股");
                }else if(house.getHoldMoney() < dollar){
                    pronoucingLabel.setText("現金不足無法購買");
                }else if(house.getHoldMoney() >= dollar){
                    house.setStockTicket( select,  house.getStockTicket()[ select ]
                            + Integer.parseInt(buyticketField.getText()));
                    house.setHoldMoney(house.getHoldMoney() - dollar);
                    moneyField.setText(String.valueOf(house.getHoldMoney()));
                    pronoucingLabel.setText("謝謝惠顧");
                }
            }else if(event.getSource() == buyticketField){
                int  dollar = (int) (Integer.parseInt(buyticketField.getText()) * 200 * house.getStock()[select] );
                buyMoneyField.setText( dollar  + " 元");
                if(Integer.parseInt(buyticketField.getText()) == 0){
                    pronoucingLabel.setText("請輸入要買幾張股票，每張兩百股");
                }else if(house.getHoldMoney() < dollar){
                    pronoucingLabel.setText("現金不足無法購買");
                }else if(house.getHoldMoney() >= dollar){
                    pronoucingLabel.setText("點選買入按鈕購買股票");
                }

            }else if(event.getSource() == saleticketField){
                int  dollar = (int) (Integer.parseInt(saleticketField.getText()) * 200 * house.getStock()[select] );
                saleMoneyField.setText( dollar  + " 元");
                if(Integer.parseInt(buyticketField.getText()) == 0){
                    pronoucingLabel.setText("請輸入要賣出幾張股票，每張兩百股");
                }else if(house.getStockTicket()[select ] < Integer.parseInt(saleticketField.getText())){
                    pronoucingLabel.setText("股票張數不足無法賣出");
                }else if(house.getStockTicket()[ select ] >= Integer.parseInt(saleticketField.getText())){
                    pronoucingLabel.setText("點選賣出按鈕賣出股票");
                }
            }else if(event.getSource() == Stock1){
                select = 0;

            }else if(event.getSource() == Stock2){
                select = 1;

            }else if(event.getSource() == Stock3){
                select = 2;

            }else if(event.getSource() == Stock4){
                select = 3;

            }else if(event.getSource() == BackButton){
                mainFrame.changeToMainScreen();
            }
            resetall();
        }
    }

    public void resetall(){
        ticketNumField.setText(String.valueOf(house.getStockTicket()[select]));
        Price.setText(String.format("%.2f",house.getStock()[select]));
        percent.setText(String.format("%.2f",house.getStockPrs()[select]));
        StockPic.setIcon(new ImageIcon("data//Stock//Stock"+select+".png"));
    }

    public void makePNG(int i){
        CategoryDataset dataset = createDataset(house.getData()[i]);
        // 步骤2：根据Dataset 生成JFreeChart对象，以及做相应的设置
        JFreeChart freeChart = createChart(dataset, "");
        // 步骤3：将JFreeChart对象输出到文件，Servlet输出流等
        saveAsFile(freeChart, "data//Stock//Stock"+ i +".png", 600, 400);
    }




    public static void nextStock(House h){
        for(int i = 0;i<4;i++) {
            //h.setStockLast(i, h.getStock()[i]);
            h.setStockPrs(i, getNextPercent());
            h.setStock(i, h.getStock()[i] * (1F + h.getStockPrs()[i]));
        }

    }

    public static float getNextPercent() {
        Random random = new Random();
        float tempf;
        if(random.nextFloat() > 0.3) {
            tempf = random.nextFloat();
            tempf = tempf - 0.5F;
        }else {
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
            ChartUtilities.writeChartAsPNG(out, chart, 600, 400);
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
        CategoryPlot plot = (CategoryPlot)jfreechart.getPlot();
        // 背景色 透明度
        plot.setBackgroundAlpha(0.5f);
        // 前景色 透明度
        plot.setForegroundAlpha(0.5f);
        // 其他设置 参考 CategoryPlot类
        LineAndShapeRenderer renderer = (LineAndShapeRenderer)plot.getRenderer();
        //renderer.setBaseShapesVisible(true); // series 点（即数据点）可见
        renderer.setBaseLinesVisible(true); // series 点（即数据点）间有连线可见
        renderer.setSeriesStroke(0, new BasicStroke(5F));
//        renderer.setUseSeriesOffset(); // 设置偏移量
        //renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        //renderer.setBaseItemLabelsVisible(true);
        return jfreechart;
    }

    public static CategoryDataset createDataset( double[][] data ) {
//        String[] rowKeys = {"A平台"};
//        String[] colKeys = {"0:00", "1:00", "2:00", "7:00", "8:00", "9:00",
//                "10:00", "11:00", "12:00", "13:00", "16:00", "20:00", "21:00",
//                "23:00"};
//        double[][] data = {{4, 3, 1, 1, 1, 1, 2, 2, 2, 1, 8, 2, 1, 1},};
        // 或者使用类似以下代码
        // DefaultCategoryDataset categoryDataset = new
        // DefaultCategoryDataset();
        // categoryDataset.addValue(10, "rowKey", "colKey");
        return DatasetUtilities.createCategoryDataset("","", data);
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
}

