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
import javax.swing.border.EmptyBorder;

import Frogger.util.*;
import House.house.House;
import Main.*;


public class FroggerPanel extends JPanel
{

    private Timer timer;
    private int time = 6000;
    private Image frogImage;
    private ArrayList<Car> cars;
    private Frog frog;
    private Car[] CarsRoadOne = new Car[5];
    private Car[] CarsRoadTwo = new Car[3];
    private Car[] CarsRoadThree = new Car[4];
    private Car[] CarsRoadFour = new Car[6];
    private Car police;
    private int policeShow;
    private UnderWay[] underWays = new UnderWay[3];
    private int end;
    private Main mainFrame ;
    private House house;
    private JLabel timeLabel;

    // set up GUI and register key event handler
    public FroggerPanel(Main mainFrame, House house)
    {
        this.house = house;
        this.mainFrame = mainFrame;
        this.setFocusable(true);
//        this.setLayout(new BorderLayout());
//        timePanel = new JPanel();
        this.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setLayout(null);
//        timePanel.add(timeLabel);
//
//        add(timePanel, BorderLayout.NORTH);
        timeLabel = new JLabel(String.valueOf(time/100));
        timeLabel.setBounds(900, 20 , 200, 20);
        add(timeLabel);

        Init();
        repaint();
        Keylisten listener = new Keylisten();
        this.addKeyListener(listener);
        // handle frame mouse motion event
        //requestFocusInWindow();
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
                    timer.cancel();
                    mainFrame.changeToMainScreen();
                    end = 0;
                    break;
                default:
                    System.out.println(key2);
            }
        }
    }


    private void Init()
    {
        time = 6000;
        this.end = 1;
        cars = new ArrayList<>();
        frog = new Frog(Frog.startX, Frog.startY, 70, 70, "characters50.png");

        police = new Car( 1200, 280, 120 -10, 80-  10, 0, "police170r.png");
        policeShow = 0;
        for(int i =0;i<5;i++){
            CarsRoadOne[i] = new Car(10 + i * 280, 440, 120-10, 80-10, 1, "orangecarr.png"); // 5
            cars.add(CarsRoadOne[i]);
        }
        for(int i =0;i<3;i++){
            CarsRoadTwo[i] = new Car( 20 + i * 466, 360, 200-10, 80-10, 4, "truck200r.png"); // 3
            cars.add(CarsRoadTwo[i]);
        }
        for(int i =0;i<4;i++){
            CarsRoadThree[i] = new Car( 40 + i * 350, 200, 170-10, 80-10, -3, "truck170l.png"); // 4
            cars.add(CarsRoadThree[i]);
        }
        for(int i =0;i<6;i++){
            CarsRoadFour[i] = new Car( 15 + i * 240 , 120, 120-10 , 80-10, -2, "bluecarl.png"); // 6
            cars.add(CarsRoadFour[i]);
        }
        for(int i = 0; i< 3;i++){
            underWays[i] = new UnderWay(325, 525 - i * 240, 80, 80, "underWay.png");
        }
    }

    private void gameStart()
    {
        timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                if(time == 6000){
                    int temp = JOptionPane.showConfirmDialog(null, "小心肝過馬路\n馬路如虎口，小心罰三百\n(Yes : 開始遊戲 No : 回選單)", "", JOptionPane.YES_NO_OPTION);
                    if(temp == 1){
                        timer.cancel();
                        mainFrame.changeToMainScreen();
                    }
                }
                time--;
                //System.out.println("test");
                timeLabel.setText(String.valueOf(time/100));
                if(time == 99){
                    int temp = JOptionPane.showConfirmDialog(null, "遊戲結束\n是否要重新(Yes : 重新遊戲 No : 回選單)", "", JOptionPane.YES_NO_OPTION);
                    if(temp == 0){ // 0 yew 1 no
                        Init();
                    }else if(temp == 1){
                        timer.cancel();
                        mainFrame.changeToMainScreen();
                    }
                }
                if(time == 5000){
                    police.setSpeed(-1);
                    policeShow = 1;
                }
                if(time == 4600){
                    police.setSpeed(0);
                }
                if(time == 4000){
                    police.setSpeed(1);
                }
                if(time == 3600){
                    policeShow =0;
                    police.setSpeed(0);
                }
                if(end == 0){
                    timer.cancel();
                }
                for( Car c : cars){
                    if(c.intersect(frog)){
                        frog.setX(Frog.startX);
                        frog.setY(Frog.startY);
                    }
                    c.update();
                }
                if(police.getSpeed() != 0){
                    police.update();
                }
                if(frog.win()){
                    int temp = JOptionPane.showConfirmDialog(null, "遊戲勝利\n你增加了"+time+"經驗值\n是否要重新(Yes : 重新遊戲 No : 回選單)", "", JOptionPane.YES_NO_OPTION);
                    if(temp == 0){ // 0 yew 1 no
                        Init();
                        time = 5999;
                    }else if(temp == 1) {
                        timer.cancel();
                        mainFrame.changeToMainScreen();
                    }
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
        //g.fillRect( 610, 550, frog.getW(), frog.getH());
        //g.fillRect(frog.getX()+20, frog.getY()+20,frog.getW(), frog.getH());

        for(int i =0;i<3;i++){
            g.fillRect(underWays[i].getX(), underWays[i].getY(), underWays[i].getW(), underWays[i].getH());

        }
        g.fillRect(Frog.endX, Frog.endY, frog.getW(),frog.getH());
        try {
            frogImage = ImageIO.read(new File("data/Frogger/image/" + frog.getImageName()));
            g.drawImage(frogImage, frog.getX(), frog.getY(), null);
            for (Car c : cars) {
                Image tempImage = ImageIO.read(new File("data/Frogger/image/" + c.getImageName()));
                g.drawImage(tempImage, c.getX() - 5, c.getY() - 5, null);
            }

            if(policeShow == 1){
                Image tempImage = ImageIO.read(new File("data/Frogger/image/" + police.getImageName()));
                g.drawImage(tempImage, police.getX()-5, police.getY()-5, null);
            }
        }
        catch (Exception ex) {
                System.out.println("No example.jpg!!");
        }
    }
} // end class PaintPanel
