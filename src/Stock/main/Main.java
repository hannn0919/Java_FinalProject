package Stock.main;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Stock.util.*;

public class Main {

    public static void main(String[] args)
    {
        // create JFrame
        JFrame application = new JFrame("Stock");

        TempHouse h = new TempHouse();
        StockPanel stockPanel = new StockPanel(h);
        application.add(stockPanel, BorderLayout.CENTER);


        // create a label and place it in SOUTH of BorderLayout
        application.add(new JLabel("Frogger.java"),
                BorderLayout.SOUTH);

        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.setSize(1200, 675);
        application.setVisible(true);
    }
} // end class Painter