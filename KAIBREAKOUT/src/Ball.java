import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

class Ball {
	
	private Ellipse2D.Double r;
	private double xpos, ypos, width, height;
	private double speed, xdir, ydir;
	
	public Ball(double x, double y, double w, double h)
	{
		xpos = x;
		ypos = y;
		width = w;
		height = h;
		speed = 1.5;
		xdir = 1;
		ydir = 1;
		
		r = new Ellipse2D.Double(xpos, ypos, width, height);
	}
	
	public void paint(Graphics2D g2)
	{
		g2.fill(r);
	}
	
	public double getX()
	{
		r.setFrame(xpos, ypos, width, height);
		return xpos;
	}
	
	public double getY()
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
	
	protected void changeXdir(double x)
	{
		xdir = x;
	}
	
	public void changeYdir(double y)
	{
		ydir = y;
	}
	
	public double getSpeed() {return speed;}
	public void setSpeed (double a)
	{
		speed = a;
	}
	
	public void resetPos()
	{
		r.setFrame(xpos, ypos, width, height);
		xpos = 490;
		ypos = 675;
	}
	
	public boolean intersects(double x, double y, double w, double h)
	{
		return r.intersects(x, y, w, h);
	}
	
}
 