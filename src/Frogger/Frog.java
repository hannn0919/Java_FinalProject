package ntou.cs.java2019.last;

public class Frog extends Rectengle
{
	Frog(int x, int y, int w, int h){
		super(x, y, w, h);
	}
	
	public void move(int xdir, int ydir){
		setX( getX() + xdir);
		setY( getY() + ydir);
	}
}