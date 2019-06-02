package HitMouse.gameobject;

import javax.swing.*;

public class Windows extends JButton{
    private int width;
    private int height;

    public Windows(int x,int y,String name){
        this.width=153;
        this.height=134;
        setIcon(new ImageIcon(name));
        setBounds(x,y,width,height);
    }
}
