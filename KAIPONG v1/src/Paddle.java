import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Paddle {
	
	private Rectangle r;
	private int xpos;
	private int ypos;
	private int width;
	private int height;
	private int speed = 2;
	private boolean up = false;
	private boolean down = false;
	
	public Paddle(int x, int y, int w, int h)
	{
		xpos = x;
		ypos = y;
		width = w;
		height = h;
		
		r = new Rectangle(xpos, ypos, width, height);
	}
	
	public int getX()
	{ 
		return xpos;
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
	
	public int getH()
	{
		return height;
	}
	
	public void paint(Graphics2D g2)
	{
		g2.fill(r);
	}
	
	public int getSpeed()
	{
		if (up && down) {
			return 0;
		}
		if (up && !down) {
			return -speed;
		}
		if (!up && down) {
			return speed;
		}
		return 0;
	}
	
	public void changeSpeed(int x) {
		speed = x;
	}
	
	public void upPressed() {
		up = true;
	}
	public void downPressed() {
		down = true;
	}
	public void upReleased() {
		up = false;
	}
	public void downReleased() {
		down = false;
	}
}
