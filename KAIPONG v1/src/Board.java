import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.geom.*;
import java.util.HashSet;
import java.awt.*;
import java.awt.Event;

public class Board extends JPanel implements ActionListener, KeyListener
{

	//Background
	Rectangle background = new Rectangle(0, 0, 1000, 800);
	
	public String mainFont = "Georgia";
	
	//Walls
	public Wall topWall = new Wall (50, 50, 900, 10);
	public Wall leftWall = new Wall (50, 50, 10, 700);
	public Wall rightWall = new Wall (940, 50, 10, 700);
	public Wall bottomWall = new Wall (50, 740, 900, 10);
	
	//Ball
	Ball ball = new Ball (490, 390, 20, 20);
	
	//Paddles
	Paddle p1 = new Paddle(60, 200, 25, 200);
	Paddle p2 = new Paddle(915, 200, 25, 200);
	
	//Colors
	Color kaiYellow = new Color(16777164);
	Color kaiBlue = new Color(12576498);
	Color kaiRed = new Color(16751001);
	Color kaiCyan = new Color (892927);
	Color kaiGreen = new Color(5018700);
	Color kaiMagenta = new Color(16244991);
	
	//Facts
	float kaiPong = 10/10;
	float qwayPong = 1/10;
	boolean itsTrue = kaiPong > qwayPong;
	
	int score1 = 0;
	int score2 = 0;
	int streak = 0;
	boolean streakLock = false;
	boolean first = itsTrue;
	
	private Timer t = new Timer(5, this);
	
	public Board()
	{
		addKeyListener(this);
		setFocusable(itsTrue);
		setFocusTraversalKeysEnabled(false);
		t.start();
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		if (first) {
			g2.setColor(Color.BLACK);
			g2.fill(background);
			topWall.paint(g2);
			leftWall.paint(g2);
			rightWall.paint(g2);
			bottomWall.paint(g2);
			
			g2.setColor(Color.WHITE);
			p1.paint(g2);
			p2.paint(g2);
			ball.paint(g2);
			g.setFont(new Font(mainFont, Font.PLAIN, 72)); 
			g.drawString(String.valueOf(score1), 300, 150);
			g.drawString(String.valueOf(score2), 628, 150);
			g.setFont(new Font(mainFont, Font.PLAIN, 144));
			g.drawString("KAIPONG", 175, 436);
			g.setFont(new Font(mainFont, Font.PLAIN, 18));
			g.drawString("\"THIS IS THE TIME. THIS IS THE RECORD OF THE TIME.\"", 250, 636);
		}
		else {
			if ((score1 + score2) % 4 == 0 && streak < 50) {
				g2.setColor(kaiYellow);
				g2.fill(background);
				
				g2.setColor(kaiBlue);
				g.setFont(new Font(mainFont, Font.PLAIN, 24));
				g.drawString("\"BANANA\"", 450, 636);
			}
			
			if ((score1 + score2) % 4 == 1 && streak < 50) {
				g2.setColor(kaiRed);
				g2.fill(background);
				
				g2.setColor(kaiCyan);
				g.setFont(new Font(mainFont, Font.PLAIN, 24));
				g.drawString("\"SEA PEACH\"", 430, 636);
			}
			
			if ((score1 + score2) % 4 == 2 && streak < 50) {
				g2.setColor(kaiMagenta);
				g2.fill(background);
				
				g2.setColor(kaiGreen);
				g.setFont(new Font(mainFont, Font.PLAIN, 24));
				g.drawString("\"CHERRY\"", 460, 636);
			}
			
			if ((score1 + score2) % 4 == 3 && streak < 50) {
				g2.setColor(Color.WHITE);
				g2.fill(background);
				
				g2.setColor(Color.BLACK);
				g.setFont(new Font(mainFont, Font.PLAIN, 24));
				g.drawString("\"VANILLA\"", 450, 636);
			}
			
			if ((score1 + score2) % 4 == 0 && streak >= 50) {
				g2.setColor(kaiBlue);
				g2.fill(background);
				
				g2.setColor(kaiYellow);
				g.setFont(new Font(mainFont, Font.PLAIN, 24));
				g.drawString("\"BANANA PLUS\"", 425, 636);
			}
			
			if ((score1 + score2) % 4 == 1 && streak >= 50) {
				g2.setColor(kaiCyan);
				g2.fill(background);
				
				g2.setColor(kaiRed);
				g.setFont(new Font(mainFont, Font.PLAIN, 24));
				g.drawString("\"SEA PEACH PLUS\"", 415, 636);
			}
			
			if ((score1 + score2) % 4 == 2 && streak >= 50) {
				g2.setColor(kaiGreen);
				g2.fill(background);
				
				g2.setColor(kaiMagenta);
				g.setFont(new Font(mainFont, Font.PLAIN, 24));
				g.drawString("\"CHERRY PLUS\"", 445, 636);
			}
			
			if ((score1 + score2) % 4 == 3 && streak >= 50) {
				g2.setColor(Color.WHITE);
				g2.fill(background);
				
				g2.setColor(Color.LIGHT_GRAY);
				ball.paint(g2);
				
				g2.setColor(Color.DARK_GRAY);
				g.setFont(new Font(mainFont, Font.PLAIN, 24));
				g.drawString("\"HEAVEN\"", 455, 636);
				p1.paint(g2);
				p2.paint(g2);
				g.setFont(new Font(mainFont, Font.PLAIN, 24));
				g.drawString(String.valueOf(streak), 488, 150);
				g.setFont(new Font(mainFont, Font.PLAIN, 72)); 
				g.drawString(String.valueOf(score1), 300, 150);
				g.drawString(String.valueOf(score2), 628, 150);
				g.setFont(new Font(mainFont, Font.PLAIN, 144));
				g.drawString("KAIPONG", 175, 436);
			}
			
			if (!((score1 + score2) % 4 == 3 && streak >= 50))
			{
				topWall.paint(g2);
				leftWall.paint(g2);
				rightWall.paint(g2);
				bottomWall.paint(g2);
				p1.paint(g2);
				p2.paint(g2);
				ball.paint(g2);
				
				g.setFont(new Font(mainFont, Font.PLAIN, 24));
				g.drawString(String.valueOf(streak), 488, 150);
				g.setFont(new Font(mainFont, Font.PLAIN, 72)); 
				g.drawString(String.valueOf(score1), 300, 150);
				g.drawString(String.valueOf(score2), 628, 150);
				g.setFont(new Font(mainFont, Font.PLAIN, 144));
				g.drawString("KAIPONG", 175, 436);
			}
		}
	}
	
	public void actionPerformed(ActionEvent e)
	{	
			
		ball.move();
		//Top/Bottom Walls
		if (ball.intersects(topWall.getX(),topWall.getY(),topWall.getW(),topWall.getH()))
		{
			ball.changeYdir(1);
		}
		if (ball.intersects(bottomWall.getX(),bottomWall.getY(),bottomWall.getW(),bottomWall.getH()))
		{
			ball.changeYdir(-1);
		}
		
		//Left/Right Walls
		if (ball.intersects(leftWall.getX(),leftWall.getY(),leftWall.getW(),leftWall.getH()))
		{
			ball.resetPos();
			ball.changeXdir(1);
			ball.changeYdir(0);
			ball.changeSpeed(1);
			score2 += 1;
			streak = 0;
			first = true;
		}
		if (ball.intersects(rightWall.getX(),rightWall.getY(),rightWall.getW(),rightWall.getH()))
		{
			ball.resetPos();
			ball.changeXdir(-1);
			ball.changeYdir(0);
			ball.changeSpeed(1);
			score1 += 1;	
			streak = 0;
			first = true;
		}
		
		//If Ball Hits Paddle
		if (ball.intersects(p1.getX(),p1.getY(),p1.getW(),p1.getH()))
		{
			ball.changeXdir(1);
			ball.changeSpeed(3);
			if (first)
			{
				ball.changeYdir(1);
				first = false;
			}
			if (!streakLock)
			{
				streak += 1;
				streakLock = true;
			}
		}
		
		if (ball.intersects(p2.getX(),p2.getY(),p2.getW(),p2.getH()))
		{
			ball.changeXdir(-1);
			ball.changeSpeed(3);
			if (first)
			{
				ball.changeYdir(-1);
				first = false;
			}
			if (!streakLock)
			{
				streak += 1;
				streakLock = true;
			}
		}
		
		if (ball.intersects(500, 0, 1, 800))
		{
			streakLock = false;
		}
		
		//Move Paddles
		
		if ( !(p1.getY() <= topWall.getY() + topWall.getH() && p1.getSpeed() == -2) && !(p1.getY() >= bottomWall.getY() - p1.getH() && p1.getSpeed() == 2) )
		{
			p1.changeY(p1.getY() + p1.getSpeed());
		}
		
		if ( !(p2.getY() <= topWall.getY() + topWall.getH() && p2.getSpeed() == -2) && !(p2.getY() >= bottomWall.getY() - p2.getH() && p2.getSpeed() == 2) )
		{
			
			p2.changeY(p2.getY() + p2.getSpeed());
		}
				
		repaint();
	}
	
	public void keyTyped(KeyEvent e) {}
	
	public void keyPressed(KeyEvent e)
	{
		int button = e.getKeyCode();
		
		if (button == KeyEvent.VK_W) {
			System.out.println("w");
			p1.upPressed();
		}
		if (button == KeyEvent.VK_S) {
			System.out.println("s");
			p1.downPressed();
		}
		if (button == KeyEvent.VK_UP) {
			System.out.println("up");
			p2.upPressed();
		}
		if (button == KeyEvent.VK_DOWN) {
			System.out.println("down");
			p2.downPressed();
		}
	}
	
	public void keyReleased(KeyEvent e)
	{	
		int button = e.getKeyCode();
		
		if (button == KeyEvent.VK_W) {
			p1.upReleased();
		}
		if (button == KeyEvent.VK_S) {
			p1.downReleased();
		}
		if (button == KeyEvent.VK_UP) {
			p2.upReleased();
		}
		if (button == KeyEvent.VK_DOWN) {
			p2.downReleased();
		}
	}
}
