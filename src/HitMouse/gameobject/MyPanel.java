package HitMouse.gameobject;

import javax.swing.*;
import java.awt.*;

public class MyPanel  extends JPanel {
    public void paint(Graphics g1) {
        Graphics2D g=(Graphics2D)g1;
        super.paintComponents(g);
    }
}
