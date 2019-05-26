package Frogger.util;
public class Frog extends Rectengle
{
    public Frog(int x, int y, int w, int h){
        super(x, y, w, h);
    }

    public void move(int xdir, int ydir){
        setX( getX() + xdir);
        setY( getY() + ydir);
    }
}