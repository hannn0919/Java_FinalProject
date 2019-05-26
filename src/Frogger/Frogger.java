
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Frogger
{
    public static void main(String[] args)
    {
        // create JFrame
        JFrame application = new JFrame("Froger");

        FroggerPanel froggerPanel = new FroggerPanel();
        application.add(froggerPanel, BorderLayout.CENTER);

        //froggerPanel.setFocusable(true);
        //froggerPanel.requestFocusInWindow();

        // create a label and place it in SOUTH of BorderLayout
        application.add(new JLabel("Frogger.java"),
                BorderLayout.SOUTH);

        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.setSize(1200, 675);
        application.setVisible(true);
    }
} // end class Painter
