import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.geom.*;
import java.awt.*;

public class Board extends JPanel implements ActionListener, KeyListener
{
	//Background
	Rectangle background = new Rectangle(0, 0, Main.frameWidth, Main.frameHeight);
	
	Wall topWall = new Wall (50, 50, 900, 10);
	Wall leftWall = new Wall (50, 50, 10, 700);
	Wall rightWall = new Wall (940, 50, 10, 700);
	Wall bottomWall = new Wall (50, 740, 900, 10);
	
	boolean setup = true;
	boolean cornerHit = false;
	static int gridWidth = 11;
	static int gridHeight = 8;
	int brickCount = gridWidth * gridHeight - gridHeight / 2;
	int bricksBroken = 0;
	static Brick[][] grid = new Brick[gridHeight][gridWidth];
	int score = 0;
	int multiplier = 1;
	int state = 0;
	
	public void generateGrid ()
	{
		for (int i = 0; i < gridHeight; i++)
		{
			for (int j = 0; j < gridWidth; j++)
			{
				if (i % 2 == 0)
				{
					grid[i][j] = new Brick(j * 77 + 77, i * 40 + 65, 72, 35);
				}
				if (i % 2 == 1)
				{
					if (j == gridWidth - 1)
					{
						grid[i][j] = new Brick(0, 0, 0, 0);
					}
					else
					{
						grid[i][j] = new Brick(j * 77 + 118, i * 40 + 65, 72, 35);
					}
				}	
			}
		}
	}
	
	Paddle p1 = new Paddle(450, 700, 200, 10);
	Ball ball = new Ball(490, 675, 20, 20);
	
	private Timer t = new Timer(5, this);
	
	public Board()
	{
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		t.start();
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(Color.WHITE);
		g2.fill(background);
		
		if (state == 0)
		{
			g2.setColor(Color.BLACK);
			p1.paint(g2);
			ball.paint(g2);
			topWall.paint(g2);
			leftWall.paint(g2);
			rightWall.paint(g2);
			bottomWall.paint(g2);
			
			g.setFont(new Font("Courier", Font.PLAIN, 20));
			g2.drawString("Score: " + score, 10, 20);
			g2.setColor(Color.RED);
			g2.drawString("x" + multiplier, 150, 20);
			g2.setColor(Color.BLACK);
			
			if (!setup)
			{
				for (int i = 0; i < grid.length; i++)
				{
					for (int j = 0; j < grid[0].length; j++)
					{
						grid[i][j].paint(g2);
					}
				}
			}
		}
		if (state == 1)
		{
			g2.setColor(Color.BLACK);
			g.setFont(new Font("Courier", Font.PLAIN, 72));
			g2.drawString("Score: " + score, 300, 500);
		}
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if (state == 0)
		{
			if (setup)
			{
				generateGrid();
				setup = false;
			}
			
			ball.move();
			
			// Player Movement
			if (!(p1.getX() <= leftWall.getX() + leftWall.getW() && p1.getXDir() == -2) && !(p1.getX() >= rightWall.getX() - p1.getW() && p1.getXDir() == 2))
			{
				System.out.println(p1.getXDir());
				p1.changeX(p1.getX() + p1.getXDir());
			}
			
			// Ball Hits Wall/Paddle
			if (ball.intersects(leftWall.getX(), leftWall.getY(), leftWall.getW(), leftWall.getH()))
			{
				ball.changeXdir(1);
			}
			if (ball.intersects(rightWall.getX(), rightWall.getY(), rightWall.getW(), rightWall.getH()))
			{
				ball.changeXdir(-1);
			}
			if (ball.intersects(topWall.getX(), topWall.getY(), topWall.getW(), topWall.getH()))
			{
				ball.changeYdir(1);
				cornerHit = false;
			}
			if (ball.intersects(bottomWall.getX(), bottomWall.getY(), bottomWall.getW(), bottomWall.getH()))
			{
				state = 1;
			}
			if (ball.intersects(p1.getX(), p1.getY(), p1.getW(), p1.getH()))
			{
				ball.changeYdir(-1);
				if (ball.intersects(p1.getX() - 5, p1.getY() - 5, 10, p1.getH() + 5) || ball.intersects(p1.getX() + p1.getW() - 5, p1.getY() - 5, 10, p1.getH() + 5) && !cornerHit)
				{
					ball.setSpeed(ball.getSpeed() * 1.1);
					p1.setSpeed(p1.getSpeed() + 0.2);
					multiplier++;
					cornerHit = true;
				}
			}
		
			// Ball hits Brick
			for (int i = 0; i < grid.length; i++)
			{
				for (int j = 0; j < grid[0].length; j++)
				{
					if (ball.intersects(grid[i][j].getX(), grid[i][j].getY(), grid[i][j].getW(), grid[i][j].getH()))
					{
						if (ball.intersects(grid[i][j].getX(), grid[i][j].getY() + grid[i][j].getH() - 1, grid[i][j].getW(), 1)) // Bottom surface
						{
							ball.changeYdir(1);
						}
						if (ball.intersects(grid[i][j].getX(), grid[i][j].getY(), grid[i][j].getW(), 1)) // Top surface
						{
							ball.changeYdir(-1);
						}
						if (ball.intersects(grid[i][j].getX(), grid[i][j].getY(), 1, grid[i][j].getH()))
						{
							ball.changeXdir(-1);
						}
						if (ball.intersects(grid[i][j].getX() + grid[i][j].getW() - 1, grid[i][j].getY(), 1, grid[i][j].getH()))
						{
							ball.changeXdir(1);
						}
						grid[i][j].setX(-500);
						grid[i][j].setY(-500);
						cornerHit = false;
						score += multiplier;
						bricksBroken++;
					}
				}
			}
			
			if (bricksBroken >= brickCount)
			{
				state = 1;
			}
		}
		
		repaint();
	}
	
	public void keyTyped(KeyEvent e) {}
	
	public void keyPressed(KeyEvent e)
	{
		int button = e.getKeyCode();
		if (state == 0)
		{
			if (button == KeyEvent.VK_LEFT) {
				System.out.println("leftPressed");
				p1.leftPressed();
			}
			if (button == KeyEvent.VK_RIGHT) {
				System.out.println("rightPressed");
				p1.rightPressed();
			}
		}
	}
	
	public void keyReleased(KeyEvent e)
	{	
		int button = e.getKeyCode();
		if (state == 0)
		{
			if (button == KeyEvent.VK_LEFT) {
				System.out.println("leftReleased");
				p1.leftReleased();
			}
			if (button == KeyEvent.VK_RIGHT) {
				System.out.println("rightReleased");
				p1.rightReleased();
			}
		}
		if (state == 1)
		{
			if (button == KeyEvent.VK_SPACE || button == KeyEvent.VK_ENTER)
			{
				setup = true;
				cornerHit = false;
				bricksBroken = 0;
				grid = new Brick[gridHeight][gridWidth];
				score = 0;
				multiplier = 1;
				ball.resetPos();
				ball.setSpeed(1.5);
				p1.resetPos();
				p1.leftReleased();
				p1.rightReleased();
				state = 0;
			}
		}
	}
}
