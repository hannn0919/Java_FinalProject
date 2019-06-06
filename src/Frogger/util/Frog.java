package Frogger.util;
public class Frog extends Rectengle
{
    public static final int upBound = 0;
    public static final int downBound = 600;
    public static final int leftBound = 300;
    public static final int rightBound = 1100;
    public static final int startX = 605;
    public static final int startY = 525;
    public static final int endX = 605;
    public static final int endY = 45;

    public Frog(int x, int y, int w, int h, String ImageName){
        super(x, y, w, h, ImageName);
    }

    public void move(int xdir, int ydir){

        int newX = getX() + xdir;
        int newY = getY() + ydir;

        if(newY > upBound && newY < downBound && newX > leftBound && newX < rightBound){
            setX( getX() + xdir);
            setY( getY() + ydir);
        }
    }

    public boolean win(){
        if(getX() == endX && getY() == endY){
            return true;
        }else return false;
    }
}