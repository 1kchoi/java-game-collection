import java.awt.Graphics2D;
import java.awt.Rectangle;

class Brick {
	
	private Rectangle r;
	private int xpos, ypos, width, height;
	
	public Brick(int x, int y, int w, int h)
	{
		xpos = x;
		ypos = y;
		width = w;
		height = h;
		
		r = new Rectangle(xpos, ypos, width, height);
		
	}
	public void paint(Graphics2D g2) {g2.fill(r);}

	public int getX() {return xpos;}
	public int getY() {return ypos;}
	public int getW() {return width;}
	public int getH() {return height;}
	
	public void setX(int x)
	{
		r.setFrame(xpos, ypos, width, height);
		xpos = x;
	}
	
	public void setY(int y)
	{
		r.setFrame(xpos, ypos, width, height);
		ypos = y;
	}
	
	public void setPos(int x, int y, int w, int h)
	{
		r.setFrame(xpos, ypos, width, height);
		xpos = x;
		ypos = y;
		width = w;
		height = h;
	}
}
