package ntou.cs.java2019.last;


public class Car extends Rectengle
{
	private int speed;
	private String ImageName;
	
	Car(int x, int y, int w, int h, int speed, String ImageName){
		super(x, y, w, h);
		this.speed = speed;
		this.ImageName = ImageName;
	}
	
	
	
	public void setSpeed(int newSpeed){
		this.speed = newSpeed;
	}
	
	public int getSpeed(){
		return this.speed;
	}
	
	public void setImageName(String s){
		this.ImageName = s;
	}
	
	public String getImageName(){
		return this.ImageName;
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