package game;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.*;
import java.util.Random;
import game.pokaimons.*;

public class Board extends JPanel implements ActionListener, KeyListener
{
	/* STATES:
	 * -3 - Options
	 * -2 - Start Screen
	 * -1 - Select Character
	 * 0 - Moves
	 * 1 - Battle
	 * 2 - Terminate Battle
	 * 
	 * GAMEMODES:
	 * 0 - Campaign
	 * 1 - Singleplayer
	 * 2 - Multiplayer
	 * 3 - Options
	 * 
	 * STATE 1 PROMPTS:
	 * 1 - p1's move
	 * 2 - p2's move
	 */
	
	// Background
	Rectangle background = new Rectangle(0, 0, Main.frameWidth, Main.frameHeight);
	
	Random random = new Random();
	
	int mouseX;
	int mouseY;

	
	int selectorX = 45;
	int selectorY = 295;
	int selectorW = 110;
	int selectorH = 210;
	int selectorPlayer = 1;
	int selectorEvolution = 1;
	
	Pokaimon pikaichu = new Pokaimon("Pikaichu");
	Pokaimon qwaymander = new Pokaimon("Qwaymander");
	Pokaimon sguortle = new Pokaimon("Sguortle");
	Pokaimon jonahsaur = new Pokaimon("Jonahsaur");
	Pokaimon connerpie = new Pokaimon("Connerpie");
	
	long startTime = -1;
	
	int gamemode = 1;
	
	// Players
	Pokaimon p1 = new Pokaimon("Pikaichu");
	Pokaimon p2 = new Pokaimon("Pikaichu");
	String p1select = "";
	
	Move empty = new Move("", "", 0);
	Move p1move, p2move = empty;
	
	// Other Variables
	int state = -2;
	int state1prompt = 1;
	boolean win = false;
	int turn = 1;
	int round = 1;
	int doOnce = 1;
	String loser = "";
	int statChangeColor = 0;
	
	int damageDealt = 0;
	int damageLimit = 0;
	boolean doneTurn = false;
	
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
		
		// Background
		g2.setColor(Color.WHITE);
		g2.fill(background);
		
		if (state == -2)
		{
			// Title (Top Center)
			g2.setColor(Color.BLACK);
			g.setFont(new Font("Courier", Font.PLAIN, 120)); 
			g2.drawString("PoKAImon", 200, 130);
			g.setFont(new Font("Courier", Font.PLAIN, 12)); 
			g2.drawString("a very original game concept by Kai Choi. alpha 1.4.0", 300, 150);
			
			//Pokaiball
			g2.setColor(Color.RED);
			g.fillRect(450, 200, 100, 100);
			g2.setColor(Color.BLACK);
			g.fillRect(450, 250, 100, 50);
			g2.setColor(Color.WHITE);
			g.fillRect(450, 240, 100, 20);
			g.fillRect(475, 225, 50, 50);
			g2.setColor(Color.BLACK);
			g.fillRect(485, 235, 30, 30);
			
			g2.setColor(Color.BLACK);
			g.setFont(new Font("Courier", Font.PLAIN, 20));
			if (gamemode == 1)
			{
				g.setFont(new Font("Courier", Font.PLAIN, 30));
				g2.drawString("Singleplayer", 425, 500);
				g.setFont(new Font("Courier", Font.PLAIN, 20));
				g2.drawString("Multiplayer", 425, 550);
			}
			if (gamemode == 2)
			{
				g2.drawString("Singleplayer", 425, 500);
				g.setFont(new Font("Courier", Font.PLAIN, 30));
				g2.drawString("Multiplayer", 425, 550);
			}
		}
		if (state > -2)
		{
			// Title (Top Left)
			g2.setColor(Color.BLACK);
			g.setFont(new Font("Courier", Font.PLAIN, 120)); 
			g2.drawString("PoKAImon", 10, 130);
			g.setFont(new Font("Courier", Font.PLAIN, 12)); 
			g2.drawString("a very original game concept by Kai Choi. alpha 1.4.0", 220, 150);
		}
		if (state == -1)
		{
			g2.fill(new Rectangle(selectorX, selectorY, selectorW, selectorH));
			
			g2.setColor(Color.YELLOW);
			g2.fillRect(50, 300, 100, 200);
			g2.setColor(Color.ORANGE);
			g2.fillRect(250, 300, 100, 200);
			g2.setColor(Color.BLUE);
			g2.fillRect(450, 300, 100, 200);
			g2.setColor(Color.GREEN);
			g2.fillRect(650, 300, 100, 200);
			g2.setColor(Color.MAGENTA);
			g2.fillRect(850, 300, 100, 200);
			
			g2.setColor(Color.BLACK);
			g.setFont(new Font("Courier", Font.PLAIN, 20)); 
			
			if (p1select == "MEWTWOTHREE")
			{
				g2.setColor(Color.RED);
			}
			
			g.drawString("Player 1: " + p1select, 10, 790);
			g2.setColor(Color.BLACK);
			if (selectorX == 45)
			{
				g.setFont(new Font("Courier", Font.PLAIN, 48)); 
				g.drawString(pikaichu.getName(), 10, 550);
				g.setFont(new Font("Courier", Font.PLAIN, 16)); 
				g.drawString(pikaichu.getSubtitle(), 10, 575);
				g.setFont(new Font("Courier", Font.PLAIN, 24)); 
				g.drawString(pikaichu.getHP() + " HP, " + pikaichu.getMaxAttack() + " Attack, " + pikaichu.getMaxDefence() + " Defence", 10, 600);
				g.drawString(pikaichu.getMoveset()[0].getName() + " - " + pikaichu.getMoveset()[0].getPower() + " " + pikaichu.getMoveset()[0].getType(), 10, 625);
				g.drawString(pikaichu.getMoveset()[1].getName() + " - " + pikaichu.getMoveset()[1].getPower() + " " + pikaichu.getMoveset()[1].getType(), 10, 650);
				g.drawString(pikaichu.getMoveset()[2].getName() + " - " + pikaichu.getMoveset()[2].getPower() + " " + pikaichu.getMoveset()[2].getType(), 10, 675);
			}
			if (selectorX == 245)
			{
				g.setFont(new Font("Courier", Font.PLAIN, 48)); 
				g.drawString(qwaymander.getName(), 10, 550);
				g.setFont(new Font("Courier", Font.PLAIN, 16)); 
				g.drawString(qwaymander.getSubtitle(), 10, 575);
				g.setFont(new Font("Courier", Font.PLAIN, 24)); 
				g.drawString(qwaymander.getHP() + " HP, " + qwaymander.getMaxAttack() + " Attack, " + qwaymander.getMaxDefence() + " Defence", 10, 600);
				g.drawString(qwaymander.getMoveset()[0].getName() + " - " + qwaymander.getMoveset()[0].getPower() + " " + qwaymander.getMoveset()[0].getType(), 10, 625);
				g.drawString(qwaymander.getMoveset()[1].getName() + " - " + qwaymander.getMoveset()[1].getPower() + " " + qwaymander.getMoveset()[1].getType(), 10, 650);
				g.drawString(qwaymander.getMoveset()[2].getName() + " - " + qwaymander.getMoveset()[2].getPower() + " " + qwaymander.getMoveset()[2].getType(), 10, 675);
			}
			if (selectorX == 445)
			{
				g.setFont(new Font("Courier", Font.PLAIN, 48)); 
				g.drawString(sguortle.getName(), 10, 550);
				g.setFont(new Font("Courier", Font.PLAIN, 16)); 
				g.drawString(sguortle.getSubtitle(), 10, 575);
				g.setFont(new Font("Courier", Font.PLAIN, 24)); 
				g.drawString(sguortle.getHP() + " HP, " + sguortle.getMaxAttack() + " Attack, " + sguortle.getMaxDefence() + " Defence", 10, 600);
				g.drawString(sguortle.getMoveset()[0].getName() + " - " + sguortle.getMoveset()[0].getPower() + " " + sguortle.getMoveset()[0].getType(), 10, 625);
				g.drawString(sguortle.getMoveset()[1].getName() + " - " + sguortle.getMoveset()[1].getPower() + " " + sguortle.getMoveset()[1].getType(), 10, 650);
				g.drawString(sguortle.getMoveset()[2].getName() + " - " + sguortle.getMoveset()[2].getPower() + " " + sguortle.getMoveset()[2].getType(), 10, 675);
			}
			if (selectorX == 645)
			{
				g.setFont(new Font("Courier", Font.PLAIN, 48)); 
				g.drawString(jonahsaur.getName(), 10, 550);
				g.setFont(new Font("Courier", Font.PLAIN, 16)); 
				g.drawString(jonahsaur.getSubtitle(), 10, 575);
				g.setFont(new Font("Courier", Font.PLAIN, 24)); 
				g.drawString(jonahsaur.getHP() + " HP, " + jonahsaur.getMaxAttack() + " Attack, " + jonahsaur.getMaxDefence() + " Defence", 10, 600);
				g.drawString(jonahsaur.getMoveset()[0].getName() + " - " + jonahsaur.getMoveset()[0].getPower() + " " + jonahsaur.getMoveset()[0].getType(), 10, 625);
				g.drawString(jonahsaur.getMoveset()[1].getName() + " - " + jonahsaur.getMoveset()[1].getPower() + " " + jonahsaur.getMoveset()[1].getType(), 10, 650);
				g.drawString(jonahsaur.getMoveset()[2].getName() + " - " + jonahsaur.getMoveset()[2].getPower() + " " + jonahsaur.getMoveset()[2].getType(), 10, 675);
			}
			if (selectorX == 845)
			{
				g.setFont(new Font("Courier", Font.PLAIN, 48)); 
				g.drawString(connerpie.getName(), 10, 550);
				g.setFont(new Font("Courier", Font.PLAIN, 16)); 
				g.drawString(connerpie.getSubtitle(), 10, 575);
				g.setFont(new Font("Courier", Font.PLAIN, 24)); 
				g.drawString(connerpie.getHP() + " HP, " + connerpie.getMaxAttack() + " Attack, " + connerpie.getMaxDefence() + " Defence", 10, 600);
				g.drawString(connerpie.getMoveset()[0].getName() + " - " + connerpie.getMoveset()[0].getPower() + " " + connerpie.getMoveset()[0].getType(), 10, 625);
				g.drawString(connerpie.getMoveset()[1].getName() + " - " + connerpie.getMoveset()[1].getPower() + " " + connerpie.getMoveset()[1].getType(), 10, 650);
				g.drawString(connerpie.getMoveset()[2].getName() + " - " + connerpie.getMoveset()[2].getPower() + " " + connerpie.getMoveset()[2].getType(), 10, 675);
			}
		}
		
		if (state >= 0)
		{
			p1.setPos(100, 350, p1.getW(), p1.getH()); // Player 1
			p2.setPos(700, 150, p2.getW(), p2.getH()); // Player 2
			
			// Players (p1 Bottom Left, p2 Top Right)
			p1.paint(g2);
			p2.paint(g2);
			
			// Health Bars (Above the Players)
			g2.setColor(Color.RED);
			g2.fill(new Rectangle(100, 300, p1.getMaxHP(), 10)); // Player 1 Red Health Bar
			g2.fill(new Rectangle(700, 125, p2.getMaxHP() / 2, 5)); // Player 2 Red Health Bar
			g2.setColor(Color.GREEN);
			g2.fill(new Rectangle(100, 300, p1.getHP(), 10)); // Player 1 Green Health Bar
			g2.fill(new Rectangle(700, 125, p2.getHP() / 2, 5)); // Player 2 Green Health Bar
			
			// HP Numbers (Above Health Bar)
			if(p1.getHP() <= p1.getMaxHP() / 5)
			{
				g2.setColor(Color.RED);
			}
			else if(p1.getHP() <= p1.getMaxHP() / 2)
			{
				g2.setColor(new Color(12566298)); // Darker Yellow
			}
			else
			{
				g2.setColor(Color.GREEN);
			}
			g.setFont(new Font("Courier", Font.PLAIN, 24)); 
			g.drawString("HP: " + Integer.toString(p1.getHP()) + "/" + Integer.toString(p1.getMaxHP()), 100, 270); // Player 1 HP
			g2.setColor(Color.BLACK);
			g.drawString(p1.getName(), 100, 240); // Player 1 Name
			if (p2.getHP() <= p2.getMaxHP() / 5)
			{
				g2.setColor(Color.RED);
			}
			else if(p2.getHP() <= p2.getMaxHP() / 2)
			{
				g2.setColor(new Color(12566298)); // Darker Yellow
			}
			else
			{
				g2.setColor(Color.GREEN);
			}
			g.setFont(new Font("Courier", Font.PLAIN, 12)); 
			g.drawString("HP: " + Integer.toString(p2.getHP()) + "/" + Integer.toString(p2.getMaxHP()), 700, 110); // Player 2 HP
			g2.setColor(Color.BLACK);
			g.drawString(p2.getName(), 700, 95); // Player 2 Name
			
			// Console (Bottom Right)
			g2.setColor(Color.BLACK);
			g2.fill(new Rectangle(530, 450, 525, 400));
			g2.setColor(Color.WHITE);
			g2.fill(new Rectangle(550, 510, 430, 280));
			g2.fill(new Rectangle(550, 460, 430, 40));
			
	
			// Console Messages
			if (state == 0)
			{
				g2.setColor(Color.BLACK);
				g.setFont(new Font("Courier", Font.PLAIN, 30));
				// Moves + Whose Turn (Inside Console)
				if (turn == 1)
				{
					g.drawString("Player 1 - " + p1.getName(), 550, 490);
					g.drawString(p1.getMoveset()[0].getName(), 550, 650);
					g.drawString(p1.getMoveset()[1].getName(), 680, 550);
					g.drawString(p1.getMoveset()[2].getName(), 750, 650);
				}
				if (turn == 2)
				{
					g.drawString("Player 2 - " + p2.getName(), 550, 490);
					g.drawString(p2.getMoveset()[0].getName(), 550, 650);
					g.drawString(p2.getMoveset()[1].getName(), 680, 550);
					g.drawString(p2.getMoveset()[2].getName(), 750, 650);
				}
			}
			if (state == 1)
			{
				g2.setColor(Color.BLACK);
				g.setFont(new Font("Courier", Font.PLAIN, 30));
				// Player Used Move (Inside Console)
				if (state1prompt == 1)
				{
					g.drawString(p1.getName() + " used", 550, 550);
					g.drawString(p1move.getName(), 550, 600);
					if (p1move.getType() == "Attack")
					{
						g.setColor(Color.BLACK);
						g.setFont(new Font("Courier", Font.PLAIN, 12));
						if (p1.getAttack() + p1move.getPower() - p2.getDefence() <= 0)
						{
							g.drawString("-5 HP!", 640, 175); //700, 150
						}
						else
						{
							g.drawString(-(p1.getAttack() + p1move.getPower() - p2.getDefence()) + " HP!", 640, 175);
						}
					}
					if (p1move.getType() == "Special")
					{
						g.setColor(Color.BLACK);
						g.setFont(new Font("Courier", Font.PLAIN, 12));
						g.drawString(-p1move.getPower() + " HP!" , 640, 175);
					}
					if (p1move.getType() == "Attack Stat")
					{
						g.setColor(new Color(statChangeColor, 0, 0));
						g.setFont(new Font("Courier", Font.PLAIN, 24));
						g.drawString("+" + p1move.getPower() + " Attack", 325, 400); //700, 150
					}
					if (p1move.getType() == "Defence Stat")
					{
						g.setColor(new Color(0, statChangeColor, 0));
						g.setFont(new Font("Courier", Font.PLAIN, 24));
						g.drawString("+" + p1move.getPower() + " defence", 325, 400); //700, 150
					}
				}
				if (state1prompt == 2)
				{
					g.drawString(p2.getName() + " used", 550, 550);
					g.drawString(p2move.getName(), 550, 600);
					if (p2move.getType() == "Attack")
					{
						g.setColor(Color.BLACK);
						g.setFont(new Font("Courier", Font.PLAIN, 24));
						if (p2.getAttack() + p2move.getPower() - p1.getDefence() <= 0)
						{
							g.drawString("-1 HP!", 325, 400); //700, 150
						}
						else
						{
							g.drawString(-(p2.getAttack() + p2move.getPower() - p1.getDefence()) + " HP!", 325, 400);
						}
					}
					if (p2move.getType() == "Special")
					{
						g.setColor(Color.BLACK);
						g.setFont(new Font("Courier", Font.PLAIN, 24));
						g.drawString(-p2move.getPower() + " HP!" , 325, 400);
					}
					if (p2move.getType() == "Attack Stat")
					{
						g.setColor(new Color(statChangeColor, 0, 0));
						g.setFont(new Font("Courier", Font.PLAIN, 12));
						g.drawString("+" + p2move.getPower() + " Attack", 575, 175); //700, 150
					}
					if (p2move.getType() == "Defence Stat")
					{
						g.setColor(new Color(0, statChangeColor, 0));
						g.setFont(new Font("Courier", Font.PLAIN, 12));
						g.drawString("+" + p2move.getPower() + " defence", 575, 175); //700, 150
					}
				}
				if (doneTurn)
				{
					g.setColor(Color.BLACK);
					g.setFont(new Font("Courier", Font.PLAIN, 12));
					g.drawString("(Press ENTER/SPACE to continue.)", 700, 780); //700, 150
				}
			}
			if (state == 2)
			{
				// Player was KO'd! (Inside Console)
				g.setColor(Color.BLACK);
				g.setFont(new Font("Courier", Font.PLAIN, 30));
				g.drawString(loser + " was KO'd!", 550, 550);
				g.setFont(new Font("Courier", Font.PLAIN, 12));
				g.drawString("Press ESC to return to main menu.", 550, 590);
			}
		}
	}
	
	public void actionPerformed(ActionEvent e)
	{
		
		mouseX = MouseInfo.getPointerInfo().getLocation().x;
		mouseY = MouseInfo.getPointerInfo().getLocation().y;
		
		if (state == -1)
		{
			if (selectorPlayer > 2)
			{
				p2.setW(p2.getW() / 2);
				p2.setH(p2.getH() / 2);
				state = 0;
			}
		}
		if (state == 0)
		{
			if (gamemode == 1 && turn == 2) // SINGLEPLAYER
			{
				p2move = p2.getMoveset()[random.nextInt(3)];
				turn = 1;
			}
			if (p1move != empty && p2move != empty)
			{
				state = 1;
			}
		}
		
		if (state == 1)
		{
			if (state1prompt == 1 && !doneTurn)
			{
				if (p1move.getType() == "Attack")
				{
					if (p1.getAttack() + p1move.getPower() - p2.getDefence() > 0)
					{
						if (startTime == -1)
						{
							startTime = System.nanoTime();
							damageDealt = 0;
							damageLimit = p1.getAttack() + p1move.getPower() - p2.getDefence();
						}
						else
						{
							if (damageDealt < damageLimit)
							{
								if (System.nanoTime() - startTime >= 1000000000 / damageLimit * damageDealt)
								{
									p2.setHP(p2.getHP() - 1);
									damageDealt++;
								}
							}
							else if (damageDealt == damageLimit)
							{
								startTime = -1;
								doneTurn = true;
							}
						}
					}
					else
					{
						p2.setHP(p2.getHP() - 1);
						doneTurn = true;
					}
				}
				if (p1move.getType() == "Special")
				{
					if (startTime == -1)
					{
						startTime = System.nanoTime();
						damageDealt = 0;
						damageLimit = p1move.getPower();
					}
					else
					{
						if (damageDealt < damageLimit)
						{
							if (System.nanoTime() - startTime >= 1000000000 / damageLimit * damageDealt)
							{
								p2.setHP(p2.getHP() - 1);
								damageDealt++;
							}
						}
						else if (damageDealt == damageLimit)
						{
							startTime = -1;
							doneTurn = true;
						}
					}
				}
				if (p1move.getType() == "Defence Stat")
				{
					if (startTime == -1)
					{
						startTime = System.nanoTime();
						statChangeColor = 0;
					}
					else
					{
						if (statChangeColor < 255)
						{
							if (System.nanoTime() - startTime >= 5 * 1000000000 / 255 * statChangeColor)
							{
								statChangeColor++;
							}
						}
						else if (statChangeColor == 255)
						{
							startTime = -1;
							p1.setDefence(p1.getDefence() + p1move.getPower());
							doneTurn = true;
						}
					}
					
				}
				if (p1move.getType() == "Attack Stat")
				{
					if (startTime == -1)
					{
						startTime = System.nanoTime();
						statChangeColor = 0;
					}
					else
					{
						if (statChangeColor < 255)
						{
							if (System.nanoTime() - startTime >= 5 * 1000000000 / 255 * statChangeColor)
							{
								statChangeColor++;
							}
						}
						else if (statChangeColor == 255)
						{
							startTime = -1;
							p1.setAttack(p1.getAttack() + p1move.getPower());
							doneTurn = true;
						}
					}
				}
			}
			
			if (state1prompt == 2 && !doneTurn)
			{
				if (p2move.getType() == "Attack")
				{
					if (p2.getAttack() + p2move.getPower() - p1.getDefence() > 0)
					{
						if (startTime == -1)
						{
							startTime = System.nanoTime();
							damageDealt = 0;
							damageLimit = p2.getAttack() + p2move.getPower() - p1.getDefence();
						}
						else
						{
							if (damageDealt < damageLimit)
							{
								if (System.nanoTime() - startTime >= 1000000000 / damageLimit * damageDealt)
								{
									p1.setHP(p1.getHP() - 1);
									damageDealt++;
								}
							}
							else if (damageDealt == damageLimit)
							{
								startTime = -1;
								doneTurn = true;
							}
						}
					}
					else
					{
						p1.setHP(p1.getHP() - 1);
						doneTurn = true;
					}
				}
				if (p2move.getType() == "Special")
				{
					if (startTime == -1)
					{
						startTime = System.nanoTime();
						damageDealt = 0;
						damageLimit = p2move.getPower();
					}
					else
					{
						if (damageDealt < damageLimit)
						{
							if (System.nanoTime() - startTime >= 1000000000 / damageLimit * damageDealt)
							{
								p1.setHP(p1.getHP() - 1);
								damageDealt++;
							}
						}
						else if (damageDealt == damageLimit)
						{
							startTime = -1;
							doneTurn = true;
						}
					}
				}
				if (p2move.getType() == "Defence Stat")
				{
					if (startTime == -1)
					{
						startTime = System.nanoTime();
						statChangeColor = 0;
					}
					else
					{
						if (statChangeColor < 255)
						{
							if (System.nanoTime() - startTime >= 5 * 1000000000 / 255 * statChangeColor)
							{
								statChangeColor++;
							}
						}
						else if (statChangeColor == 255)
						{
							startTime = -1;
							p2.setDefence(p2.getDefence() + p2move.getPower());
							doneTurn = true;
						}
					}
				}
				if (p2move.getType() == "Attack Stat")
				{
					if (startTime == -1)
					{
						startTime = System.nanoTime();
						statChangeColor = 0;
					}
					else
					{
						if (statChangeColor < 255)
						{
							if (System.nanoTime() - startTime >= 5 * 1000000000 / 255 * statChangeColor)
							{
								statChangeColor++;
							}
						}
						else if (statChangeColor == 255)
						{
							startTime = -1;
							p2.setAttack(p2.getAttack() + p2move.getPower());
							doneTurn = true;
						}
					}
				}
			}
		}
		
		if (state1prompt > 2)
		{
			state1prompt = 2;
			state = 0;
			round++;
			p1move = empty;
			p2move = empty;
		}
		
		if (state1prompt < 1)
		{
			state1prompt = 1;
			state = 0;
			round++;
			p1move = empty;
			p2move = empty;
		}
		
		if (p1.getHP() <= 0 || p2.getHP() <= 0)
		{
			state = 2;
			if (p1.getHP() <= 0)
			{
				loser = p1.getName();
			}
			if (p2.getHP() <= 0)
			{
				loser = p2.getName();
			}
		}

		repaint();
	}
	
	public void keyTyped(KeyEvent e) {}
	
	public void keyPressed(KeyEvent e) {}
	
	public void keyReleased(KeyEvent e) {
		int button = e.getKeyCode();
		if (state == -2) // START SCREEN
		{
			if (((button == KeyEvent.VK_DOWN) || (button == KeyEvent.VK_S)) && gamemode < 2)
			{
				gamemode++;
			}
			if (((button == KeyEvent.VK_UP) || (button == KeyEvent.VK_W)) && gamemode > 1)
			{
				gamemode--;
			}
			if (button == KeyEvent.VK_ENTER || button == KeyEvent.VK_SPACE)
			{
				if (gamemode == 1 || gamemode == 2)
				{
					state = -1;
				}
			}
		}
		else if (state == -1) // PLAYER SELECTION
		{
			if (button == KeyEvent.VK_LEFT)
			{
				if (selectorX > 45)
				{
					selectorX -= 200;
				}
				else
				{
					selectorX = 845;
				}
			}
			if (button == KeyEvent.VK_RIGHT)
			{
				if (selectorX < 845)
				{
					selectorX += 200;
				}
				else
				{
					selectorX = 45;
				}
			}
			if (button == KeyEvent.VK_M)
			{
				if (selectorPlayer == 1)
				{
					p1select = "MEWTWOTHREE";
					p1 = new Pokaimon("Mewtwothree");
				}
				else
				{
					p2 = new Pokaimon("Mewtwothree");

				}
				selectorPlayer++;
			}
			if (button == KeyEvent.VK_ENTER || button == KeyEvent.VK_SPACE)
			{
				if (selectorPlayer == 1)
				{
					if (selectorX == 45)
					{
						p1select = "Pikaichu";
						p1 = new Pokaimon("Pikaichu");
					}
					if (selectorX == 245)
					{
						p1select = "Qwaymander";
						p1 = new Pokaimon("Qwaymander");
					}
					if (selectorX == 445)
					{
						p1select = "Sguortle";
						p1 = new Pokaimon("Sguortle");
					}
					if (selectorX == 645)
					{
						p1select = "Jonahsaur";
						p1 = new Pokaimon("Jonahsaur");
					}
					if (selectorX == 845)
					{
						p1select = "Connerpie";
						p1 = new Pokaimon("Connerpie");
					}
				}
				if (selectorPlayer == 2)
				{
					if (selectorX == 45)
					{
						p2 = new Pokaimon("Pikaichu");
					}
					if (selectorX == 245)
					{
						p2 = new Pokaimon("Qwaymander");
					}
					if (selectorX == 445)
					{
						p2 = new Pokaimon("Sguortle");
					}
					if (selectorX == 645)
					{
						p2 = new Pokaimon("Jonahsaur");
					}
					if (selectorX == 845)
					{
						p2 = new Pokaimon("Connerpie");
					}
				}
				selectorPlayer++;
			}
			
		}
		else if (state == 0) // MOVE SELECT
		{
			if (turn == 1) // P1'S TURN
			{
				if (button == KeyEvent.VK_A)
				{
					p1move = p1.getMoveset()[0];
					if (round % 2 == 1 && gamemode == 2 || gamemode == 1)
					{
						turn = 2;
					}
					
				}
				
				else if (button == KeyEvent.VK_W)
				{
					p1move = p1.getMoveset()[1];
					if (round % 2 == 1 && gamemode == 2 || gamemode == 1)
					{
						turn = 2;
					}
				}
				
				else if (button == KeyEvent.VK_D)
				{
					p1move = p1.getMoveset()[2];
					if (round % 2 == 1 && gamemode == 2 || gamemode == 1)
					{
						turn = 2;
					}
				}
			}
			else if (turn == 2) // P2'S TURN
			{
				if (gamemode == 2) // MULTIPLAYER
				{
					if (button == KeyEvent.VK_LEFT)
					{
						p2move = p2.getMoveset()[0];
						if (round % 2 == 0)
							turn = 1;
					}
					
					else if (button == KeyEvent.VK_UP)
					{
						p2move = p2.getMoveset()[1];
						if (round % 2 == 0)
							turn = 1;
					}
					
					else if (button == KeyEvent.VK_RIGHT)
					{
						p2move = p2.getMoveset()[2];
						if (round % 2 == 0)
							turn = 1;
					}
				}
			}
		}
		else if (state == 1) // MOVES REVEALED
		{
			if (button == KeyEvent.VK_SPACE && doneTurn || button == KeyEvent.VK_ENTER && doneTurn)
			{
				if (round % 2 == 0)
				{
					state1prompt--;
				}
				if (round % 2 == 1)
				{
					state1prompt++;
				}
				doneTurn = false;
			}
		}
		if (button == KeyEvent.VK_ESCAPE)
		{
			gamemode = 1;
			doneTurn = false;
			win = false;
			p1 = new Pokaimon("Pikaichu");
			p2 = new Pokaimon("Pikaichu");
			turn = 1;
			round = 1;
			state1prompt = 1 ;
			p1move = empty;
			p2move = empty;
			p1select = "";
			selectorPlayer = 1;
			state = -2;
		}
	}
}