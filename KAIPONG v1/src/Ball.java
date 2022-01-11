import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

class Ball {
	
	private Ellipse2D.Double r;
	private int xpos, ypos, width, height;
	int speed, xdir, ydir;
	
	public Ball(int x, int y, int w, int h)
	{
		xpos = x;
		ypos = y;
		width = w;
		height = h;
		speed = 1;
		xdir = 1;
		ydir = 0;
		
		r = new Ellipse2D.Double(xpos, ypos, width, height);
	}
	
	public void paint(Graphics2D g2)
	{
		g2.fill(r);
	}
	
	public int getX()
	{
		r.setFrame(xpos, ypos, width, height);
		return xpos;
	}
	
	public int getY()
	{
		r.setFrame(xpos, ypos, width, height);
		return ypos;
	}
	
	public void move()
	{
		r.setFrame(xpos, ypos, width, height);
		xpos = xpos + speed * xdir;
		ypos = ypos + speed * ydir;
	}
	
	protected void changeXdir(int x)
	{
		xdir = x;
	}
	
	public void changeYdir(int y)
	{
		ydir = y;
	}
	
	public void changeSpeed (int a)
	{
		speed = a;
	}
	
	public void resetPos()
	{
		r.setFrame(xpos, ypos, width, height);
		xpos = 490;
		ypos = 390;
	}
	
	public boolean intersects(int x, int y, int w, int h)
	{
		return r.intersects(x, y, w, h);
	}
	
}
 