package ntou.cs.java2019.last;

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


public class FroggerPanel extends JPanel
{

   private Timer timer;
   private int time;
   private ArrayList<Rectengle> rectengle; 
   private Frog frog;
   private Car[] CarsRoadOne = new Car[5];
   private Car[] CarsRoadTwo = new Car[5];
   private Car[] CarsRoadThree = new Car[5];
   private Car[] CarsRoadFour = new Car[5];
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
					frog.move(0, -50);
					break;
				 
				case KeyEvent.VK_LEFT:
					frog.move(-50, 0);
					break;
				 
				case KeyEvent.VK_DOWN:
					frog.move(0, 50);
					break;
				 
				case KeyEvent.VK_RIGHT:
					frog.move(50, 0);
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
		rectengle = new ArrayList<>();
		frog = new Frog(600, 570, 50, 50);
		for(int i =0;i<5;i++){
			CarsRoadOne[i] = new Car(10 + i * 300, 470, 100, 50, 5);
			CarsRoadTwo[i] = new Car( 20 + i * 250, 370, 70, 50, -8);
			CarsRoadThree[i] = new Car( 40 + i * 350, 270, 80, 50, 12);
			CarsRoadFour[i] = new Car( 15 + i * 500 , 70, 40, 50, -5);
			rectengle.add(CarsRoadOne[i]);
			rectengle.add(CarsRoadTwo[i]);
			rectengle.add(CarsRoadThree[i]);
			rectengle.add(CarsRoadFour[i]);
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
				for( Rectengle r : rectengle){
					if(r.intersect(frog)){
						frog.setX(600);
						frog.setY(570);
					}
					r.update();
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
	  g.fillRect(frog.getX(), frog.getY(), frog.getW(), frog.getH());
	  for( Rectengle r : rectengle){
			g.fillRect(r.getX()+1, r.getY()+1, r.getW()-2, r.getH()-2);
	  }
   } 
} // end class PaintPanel
