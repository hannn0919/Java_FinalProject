import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class frogger
{
   public static void main(String[] args)
   { 
      // create JFrame
      JFrame application = new JFrame("Froger");

      PaintPanel paintPanel = new PaintPanel(); 
      application.add(paintPanel, BorderLayout.CENTER); 
      
      // create a label and place it in SOUTH of BorderLayout
      application.add(new JLabel("Frogger"), 
         BorderLayout.SOUTH);

      application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      application.setSize(500, 500); 
      application.setVisible(true); 
   } 
} // end class Painter