package Frogger.main;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.*;
import Frogger.util.*;




public class FroggerPanel extends JPanel
{

    private Timer timer;
    private int time;
    private Image frogImage;
    private ArrayList<Car> cars;
    private Frog frog;
    private Car[] CarsRoadOne = new Car[5];
    private Car[] CarsRoadTwo = new Car[3];
    private Car[] CarsRoadThree = new Car[4];
    private Car[] CarsRoadFour = new Car[6];
    private Car police;
    private int end;
    private JPanel panel;
    private JPanel data;
    private JButton shop;
    private JButton gift;
    private JButton honor;
    private JButton bag;
    private JButton stock;
    private Label l1;
    private Label l2;
    private Label l3;
    private Label l4;

    // set up GUI and register key event handler
    public FroggerPanel()
    {
        setLayout(new BorderLayout());
        panel = new JPanel();
        data = new JPanel();
        shop = new JButton("Shop");
        gift = new JButton("Gift");
        honor = new JButton("Honor");
        bag = new JButton("Bag");
        stock = new JButton("Stock");
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalStrut(150));
        panel.add(shop);
        panel.add(Box.createVerticalStrut(30));
        panel.add(gift);
        panel.add(Box.createVerticalStrut(30));
        panel.add(honor);
        panel.add(Box.createVerticalStrut(30));
        panel.add(bag);
        panel.add(Box.createVerticalStrut(30));
        panel.add(stock);
        panel.add(Box.createVerticalStrut(100));

        l1 = new Label("  6666  ");
        l2 = new Label("  LV87  ");
        l3 = new Label("  exp 9487  ");
        l4 = new Label("  money 1234  ");
        data.add(l1);
        data.add(l2);
        data.add(l3);
        data.add(l4);



        add(panel, BorderLayout.WEST);
        add(data, BorderLayout.NORTH);
        this.setFocusable(true);
        Init();
        repaint();
        Keylisten listener = new Keylisten();
        this.addKeyListener(listener);
        // handle frame mouse motion event
        gameStart();
    }

    private class Keylisten extends KeyAdapter // anonymous inner class
    {

        @Override
        public void keyPressed(KeyEvent e)
        {
            int key1 = e.getKeyCode();
            char key2 = e.getKeyChar();

            switch (key1) {
                case KeyEvent.VK_UP:
                    frog.move(0, -80);
                    break;

                case KeyEvent.VK_LEFT:
                    frog.move(-40, 0);
                    break;

                case KeyEvent.VK_DOWN:
                    frog.move(0, 80);
                    break;

                case KeyEvent.VK_RIGHT:
                    frog.move(40, 0);
                    break;
                case KeyEvent.VK_P:
                    end = 0;
                    break;
                default:
                    System.out.println(key2);
            }
        }
    }


    private void Init()
    {
        this.end = 1;
        cars = new ArrayList<>();
        frog = new Frog(605, 500, 70, 70);
        for(int i =0;i<5;i++){
            CarsRoadOne[i] = new Car(10 + i * 280, 415, 120-10, 80-10, 1, "orangecarr.png"); // 5
            cars.add(CarsRoadOne[i]);
        }
        for(int i =0;i<3;i++){
            CarsRoadTwo[i] = new Car( 20 + i * 466, 335, 200-10, 80-10, 4, "truck200r.png"); // 3
            cars.add(CarsRoadTwo[i]);
        }
        for(int i =0;i<4;i++){
            CarsRoadThree[i] = new Car( 40 + i * 350, 175, 170-10, 80-10, -3, "truck170l.png"); // 4
            cars.add(CarsRoadThree[i]);
        }
        for(int i =0;i<6;i++){
            CarsRoadFour[i] = new Car( 15 + i * 240 , 95, 120-10 , 80-10, -2, "bluecarl.png"); // 6
            cars.add(CarsRoadFour[i]);
        }

    }

    private void gameStart() {
        timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                if(end == 0){
                    timer.cancel();
                }
                for( Car c : cars){
                    if(c.intersect(frog)){
                        frog.setX(605);
                        frog.setY(500);
                    }
                    c.update();
                }

                repaint();
            }
        }, 0, 10);
    }




    // draw ovals in a 4-by-4 bounding box at specified locations on window
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g); // clears drawing area
        try {
            frogImage = ImageIO.read(new File("data\\image\\characters50.png"));
        }
        catch (Exception ex) {
            System.out.println("No example.jpg!!");
        }

        g.drawImage(frogImage, frog.getX(), frog.getY(), null);
        //g.fillRect( 610, 550, frog.getW(), frog.getH());
        //g.fillRect(frog.getX()+20, frog.getY()+20,frog.getW(), frog.getH());
        for( Car c : cars){
            try{
                Image tempImage = ImageIO.read(new File("data\\image\\" + c.getImageName()));
                g.drawImage(tempImage, c.getX()-5, c.getY()-5 , null);
            }
            catch (Exception ex) {
                System.out.println("No example.jpg!!");
            }

        }
    }
} // end class PaintPanel
