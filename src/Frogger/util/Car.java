package Frogger.util;

public class Car extends Rectengle
{
    private int speed;
    private String ImageName;

    public Car(int x, int y, int w, int h, int speed, String ImageName){
        super(x, y, w, h, ImageName);
        this.speed = speed;

    }



    public void setSpeed(int newSpeed){
        this.speed = newSpeed;
    }

    public int getSpeed(){
        return this.speed;
    }

    @Override
    public void update(){
        setX( getX() + speed);
        if(speed < 0 && getX() < -100){
            setX( 1300);
        }else if(speed > 0 && getX() > 1300){
            setX( -100);
        }
    }
}