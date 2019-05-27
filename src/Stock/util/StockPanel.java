

package Stock.util;
// JLabels with text and icons.
import java.awt.*; // specifies how components are arranged
import java.awt.event.*;
import java.util.Random;
import javax.swing.JLabel; // displays text and images
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import javax.swing.*;

import Stock.main.TempHouse;
import Stock.util.*;

public class StockPanel extends JPanel
{

    private JPanel upPanel;
    private JPanel downPanel;
    private JLabel StockName;
    private JLabel StockLast;
    private JLabel StockNew;
    private JLabel StockPer;
    private JLabel[] StockLabel;
    private JTextField[] LastField;
    private JTextField[] newField;
    private JTextField[] PesField;

    private final JLabel buyLabel;
    private final JLabel saleLabel;
    private JTextField buyticketField;
    private JTextField saleticketField;
    private JTextField buyMoneyField;
    private JTextField saleMoneyField;
    private JButton SaleButton;
    private JButton BuyButton;
    private final JComboBox<String> JComboBoxBuy;
    private final JComboBox<String> JComboBoxSale;

    private TempHouse temph;

    private JLabel pronoucingLabel;

    public static final String STOCK[] = {
            "微積分", "普通物理", "線性代數", "程式設計"
    };

    public StockPanel(TempHouse h)
    {
        this.temph = h;
        nextStock(temph);
        StockName = new JLabel("股票");
        StockLast = new JLabel("前次股價");
        StockNew = new JLabel("現在股價");
        StockPer = new JLabel("漲跌幅");

        StockLabel = new JLabel[4];
        LastField= new JTextField[4];
        newField= new JTextField[4];
        PesField= new JTextField[4];



        upPanel = new JPanel();
        upPanel.setLayout(new GridLayout(5,4));
        upPanel.add(StockName);
        upPanel.add(StockLast);
        upPanel.add(StockNew);
        upPanel.add(StockPer);
        for(int i = 0 ;i< 4;i++) {

            StockLabel[i] = new JLabel(STOCK[i]);
            upPanel.add(StockLabel[i]);
            LastField[i] = new JTextField(String.valueOf( h.getStockLast()[i]));
            LastField[i].setEditable(false);
            newField[i] = new JTextField(String.valueOf( h.getStock()[i] ));
            newField[i].setEditable(false);
            PesField[i] = new JTextField(String.valueOf( h.getStockPrs()[i]));
            PesField[i].setEditable(false);
            upPanel.add(LastField[i]);
            upPanel.add(newField[i]);
            upPanel.add(PesField[i]);
        }

        downPanel = new JPanel();
        downPanel.setLayout(new GridLayout(2,5));

        buyLabel = new JLabel("買入");
        downPanel.add(buyLabel);
        JComboBoxBuy  = new JComboBox<String>(STOCK);
        JComboBoxBuy.setSelectedIndex(0);
        downPanel.add(JComboBoxBuy);
        buyticketField = new JTextField("輸入股票張數");
        downPanel.add(buyticketField);
        buyMoneyField = new JTextField("0");
        buyMoneyField.setEditable(false);
        downPanel.add(buyMoneyField);
        BuyButton = new JButton("買入");
       // BuyButton.setEnabled(false);
        downPanel.add(BuyButton);
        saleLabel = new JLabel("賣出");
        downPanel.add(saleLabel);
        JComboBoxSale  = new JComboBox<String>(STOCK);
        JComboBoxSale.setSelectedIndex(0);
        downPanel.add(JComboBoxSale);
        saleticketField = new JTextField("輸入股票張數");
        downPanel.add(saleticketField);
        saleMoneyField = new JTextField("0");
        saleMoneyField.setEditable(false);
        downPanel.add(saleMoneyField);
        SaleButton = new JButton("賣出");
        //SaleButton.setEnabled(false);
        downPanel.add(SaleButton);

        pronoucingLabel = new JLabel();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createVerticalStrut(100));
        add(upPanel);
        add(Box.createVerticalStrut(100));
        add(downPanel);
        add(Box.createVerticalStrut(100));
        add(pronoucingLabel);



        Handler handler = new Handler();
        SaleButton.addActionListener(handler);
        BuyButton.addActionListener(handler);
        saleticketField.addActionListener(handler);
        buyticketField.addActionListener(handler);


    }


    private class Handler implements ActionListener {
        // handle button event
        @Override
        public void actionPerformed(ActionEvent event) {

            if( event.getSource() == SaleButton){
                int  dollar = (int) (Integer.parseInt(saleticketField.getText()) * 200 * temph.getStock()[ JComboBoxSale.getSelectedIndex()] );
                saleMoneyField.setText( dollar  + " 元");
                if(Integer.parseInt(buyticketField.getText()) == 0){
                    pronoucingLabel.setText("請輸入要賣出幾張股票，每張兩百股");
                }else if(temph.getStockTicket()[ JComboBoxSale.getSelectedIndex() ] < Integer.parseInt(saleticketField.getText())){
                    pronoucingLabel.setText("股票張數不足無法賣出");
                }else if(temph.getStockTicket()[ JComboBoxSale.getSelectedIndex() ] >= Integer.parseInt(saleticketField.getText())){
                    temph.setStockTicket( JComboBoxSale.getSelectedIndex(),  temph.getStockTicket()[ JComboBoxSale.getSelectedIndex() ]
                            - Integer.parseInt(saleticketField.getText()));
                    temph.setHoldMoney(temph.getHoldMoney() + dollar);
                    pronoucingLabel.setText("謝謝惠顧");
                }
            }else if(event.getSource() == BuyButton ){
                int  dollar = (int) (Integer.parseInt(buyticketField.getText()) * 200 * temph.getStock()[ JComboBoxBuy.getSelectedIndex()] );
                buyMoneyField.setText( dollar  + " 元");
                if(Integer.parseInt(buyticketField.getText()) == 0){
                    pronoucingLabel.setText("請輸入要買幾張股票，每張兩百股");
                }else if(temph.getHoldMoney() < dollar){
                    pronoucingLabel.setText("現金不足無法購買");
                }else if(temph.getHoldMoney() >= dollar){
                    temph.setStockTicket( JComboBoxBuy.getSelectedIndex(),  temph.getStockTicket()[ JComboBoxBuy.getSelectedIndex() ]
                            + Integer.parseInt(buyticketField.getText()));
                    temph.setHoldMoney(temph.getHoldMoney() - dollar);
                    pronoucingLabel.setText("謝謝惠顧");
                }
            }else if(event.getSource() == buyticketField){
                int  dollar = (int) (Integer.parseInt(buyticketField.getText()) * 200 * temph.getStock()[ JComboBoxBuy.getSelectedIndex()] );
                buyMoneyField.setText( dollar  + " 元");
                if(Integer.parseInt(buyticketField.getText()) == 0){
                    pronoucingLabel.setText("請輸入要買幾張股票，每張兩百股");
                }else if(temph.getHoldMoney() < dollar){
                    pronoucingLabel.setText("現金不足無法購買");
                }else if(temph.getHoldMoney() >= dollar){
                    pronoucingLabel.setText("點選買入按鈕購買股票");
                }

            }else if(event.getSource() == saleticketField){
                int  dollar = (int) (Integer.parseInt(saleticketField.getText()) * 200 * temph.getStock()[ JComboBoxSale.getSelectedIndex()] );
                saleMoneyField.setText( dollar  + " 元");
                if(Integer.parseInt(buyticketField.getText()) == 0){
                    pronoucingLabel.setText("請輸入要賣出幾張股票，每張兩百股");
                }else if(temph.getStockTicket()[ JComboBoxSale.getSelectedIndex() ] < Integer.parseInt(saleticketField.getText())){
                    pronoucingLabel.setText("股票張數不足無法賣出");
                }else if(temph.getStockTicket()[ JComboBoxSale.getSelectedIndex() ] >= Integer.parseInt(saleticketField.getText())){
                    pronoucingLabel.setText("點選賣出按鈕賣出股票");
                }
            }


        }
    }


    public static void nextStock(TempHouse h){
        for(int i = 0;i<4;i++) {
            h.setStockLast(i, h.getStock()[i]);
            h.setStockPrs(i, getNextPercent());
            h.setStock(i, h.getStockLast()[i] * (1F + h.getStockPrs()[i]));
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

}
