
import java.awt.Point;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PaintPanel extends JPanel 
{
   // list Point references
   private final ArrayList<Point> points = new ArrayList<>(); 

   // set up GUI and register mouse event handler
   public PaintPanel()
   {
      // handle frame mouse motion event
      addKeyListener(
         new KeyListener() // anonymous inner class
         {  
            // store drag coordinates and repaint
			
            @Override
            public void mouseDragged(MouseEvent event)
            {
               points.add(event.getPoint());
               repaint(); // repaint JFrame
            } 
         } 
      ); 
   }

   // draw ovals in a 4-by-4 bounding box at specified locations on window
   @Override
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g); // clears drawing area

      // draw all 
      for (Point point : points)
         g.fillRect(point.x, point.y, 50, 50);
   } 
} // end class PaintPanel
