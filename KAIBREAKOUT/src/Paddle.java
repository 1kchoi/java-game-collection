import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Paddle {
	
	private Rectangle r;
	private int xpos;
	private int ypos;
	private int width;
	private int height;
	private int xdir = 0;
	private int ydir = 0;
	private double speed = 1;
	private boolean left = false, right = false;
	
	public Paddle(int x, int y, int w, int h)
	{
		xpos = x;
		ypos = y;
		width = w;
		height = h;
		
		r = new Rectangle(xpos, ypos, width, height);
	}
	
	public void paint(Graphics2D g2)
	{
		g2.fill(r);
	}
	
	public int getX()
	{ 
		return xpos;
	}
	
	public void changeX(int x)
	{
		r.setFrame(xpos, ypos, width, height);
		xpos = x;
	}
	
	public int getY()
	{
		return ypos;  
	}
	
	public void changeY(int y)
	{
		r.setFrame(xpos, ypos, width, height);
		ypos = y;
	}
	
	public int getW()
	{
		return width;
	}
	
	public void changeW(int w)
	{
		r.setFrame(xpos, ypos, width, height);
		width = w;
	}
	
	public int getH()
	{
		return height;
	}
	
	public void changeH(int h)
	{
		r.setFrame(xpos, ypos, width, height);
		height = h;
	}
	
	public int getXDir()
	{
		if (left && !right) {
			return -2;
		}
		if (!left && right) {
			return 2;
		}
		return 0;
	}
	
	public void changeXDir() {
		if (left && right) {
			xdir = 0;
		}
		if (left && !right) {
			xdir = -2;
		}
		if (!left && right) {
			xdir = 2;
		}
		xdir = 0;
	}
	
	public int getYDir()
	{
		return ydir;
	}
	
	public void changeYDir(int y) {
		ydir = y;
	}
	
	public void resetPos() {
		r.setFrame(xpos, ypos, width, height);
		xpos = 450;
		ypos = 700;
	}
	public void leftPressed() {
		left = true;
	}
	public void rightPressed() {
		right = true;
	}
	public void leftReleased() {
		left = false;
	}
	public void rightReleased() {
		right = false;
	}

	public double getSpeed() {return speed;}
	public void setSpeed(double speed) {this.speed = speed;}
	
}
