package Frogger.util;

public class Rectengle{

    private int x;
    private int y;
    private int w;
    private int h;

    public Rectengle(int x, int y, int w, int h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public boolean intersect(Rectengle other){

        int left = this.x + 1;
        int right = this.x + this.w - 1;
        int top  = this.y + 1;
        int bottom = this.y + this.h - 1;

        int oleft = other.x + 1;
        int oright = other.x + other.w - 1;
        int otop = other.y + 1;
        int obottom = other.y + other.h - 1;

        return !(left > oright || right < oleft || top > obottom || bottom < otop);
    }

    public void setX(int newX){
        this.x = newX;
    }

    public void setY(int newY){
        this.y = newY;
    }

    public void setW(int newW){
        this.w = newW;
    }

    public void setH(int newH){
        this.h = newH;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getW(){
        return w;
    }

    public int getH(){
        return h;
    }

    public void update(){
    }

}