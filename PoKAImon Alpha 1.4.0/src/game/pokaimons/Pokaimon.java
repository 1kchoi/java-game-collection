package game.pokaimons;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Pokaimon {
	
	private Rectangle r;
	private int xpos = 100;
	private int ypos = 350;
	private int width = 200;
	private int height = 400;
	private int xdir = 0;
	private int ydir = 0;
	
	private String name;
	private String subtitle;

	private Move[] moveset = new Move[3];
	private int maxHP;
	private int hp;
	private int maxAttack;
	private int Attack;
	private int maxDefence;
	private int Defence;
	private Color color;
	
	public Pokaimon(String name)
	{
		r = new Rectangle(xpos, ypos, width, height);
		this.name = name;
		
		if (name == "Pikaichu")
		{
			setSubtitle("the standard");
			maxHP = 200;
			hp = 200;
			maxAttack = 30;
			Attack = 30;
			maxDefence = 30;
			Defence = 30;
			color = Color.YELLOW;
			moveset[0] = new Move("Shove", "Attack", 20);
			moveset[1] = new Move("Shock", "Special", 30);
			moveset[2] = new Move("Defend", "Defence Stat", 5);
		}
		if (name == "Qwaymander")
		{
			setSubtitle("the standard");
			maxHP = 200;
			hp = 200;
			maxAttack = 30;
			Attack = 30;
			maxDefence = 30;
			Defence = 30;
			color = Color.ORANGE;
			moveset[0] = new Move("Bite", "Attack", 20);
			moveset[1] = new Move("Burn", "Special", 30);
			moveset[2] = new Move("Strengthen", "Attack Stat", 5);
		}
		if (name == "Sguortle")
		{
			setSubtitle("the heavyweight champ");
			maxHP = 200;
			hp = 200;
			maxAttack = 30;
			Attack = 30;
			maxDefence = 15;
			Defence = 15;
			color = Color.BLUE;
			moveset[0] = new Move("Slam", "Attack", 15);
			moveset[1] = new Move("Water Gun", "Special", 25);
			moveset[2] = new Move("Belly Drum", "Attack Stat", 15);
		}
		if (name == "Jonahsaur")
		{
			setSubtitle("the jack of all trades");
			maxHP = 250;
			hp = 250;
			maxAttack = 30;
			Attack = 30;
			maxDefence = 30;
			Defence = 30;
			color = Color.GREEN;
			moveset[0] = new Move("Push", "Attack", 20);
			moveset[1] = new Move("Poison", "Special", 15);
			moveset[2] = new Move("Protect", "Defence Stat", 15);
		}
		if (name == "Connerpie")
		{
			setSubtitle("the bomb");
			maxHP = 200;
			hp = 200;
			maxAttack = 30;
			Attack = 30;
			maxDefence = 30;
			Defence = 30;
			color = Color.MAGENTA;
			moveset[0] = new Move("Bite", "Attack", 35);
			moveset[1] = new Move("Grow", "Attack Stat", 15);
			moveset[2] = new Move("Defend", "Defence Stat", 30);
		}
		if (name == "Mewtwothree")
		{
			maxHP = 500;
			hp = 500;
			maxAttack = 60;
			Attack = 60;
			maxDefence = 100;
			Defence = 100;
			color = Color.PINK;
			moveset[0] = new Move("Smite", "Attack", 75);
			moveset[1] = new Move("Splatter", "Special", 100);
			moveset[2] = new Move("Sharpen", "Attack Stat", 100);
			
			width += 200;
		}
	}
	
	public void paint(Graphics2D g2) {
		g2.setColor(color);
		g2.fill(r);
	}
	public int getX() {return xpos;}
	public void setX(int x) {
		r.setFrame(xpos, ypos, width, height);
		xpos = x;
	}
	public int getY() {return ypos; }
	public void setY(int y) {
		r.setFrame(xpos, ypos, width, height);
		ypos = y;
	}
	public int getW() {return width;}
	public void setW(int w) {
		r.setFrame(xpos, ypos, width, height);
		width = w;
	}
	public int getH() {return height;}
	public void setH(int h) {
		r.setFrame(xpos, ypos, width, height);
		height = h;
	}
	public void setPos(int x, int y, int w, int h)
	{
		r.setFrame(xpos, ypos, width, height);
		xpos = x;
		ypos = y;
		width = w;
		height = h;
	}
	public int getXDir() {return xdir;}
	public void setXDir(int x) {xdir = x;}
	public int getYDir() {return ydir;}
	public void setYDir(int y) {ydir = y;}
	public String getName() {return name;}
	public void setName(String x) {name = x;}
	public int getHP() {return hp;}
	public void setHP(int x) {hp = x;}
	public int getMaxHP() {return maxHP;}
	public Move[] getMoveset() {return moveset;}
	public void setMoveset(Move[] x) {moveset = x;}
	public int getAttack() {return Attack;}
	public void setAttack(int x) {Attack = x;}
	public int getDefence() {return Defence;}
	public void setDefence(int x) {Defence = x;}
	public int getMaxAttack() {return maxAttack;}
	public int getMaxDefence() {return maxDefence;}
	public Color getColor() {return color;}
	public void setColor(Color color) {this.color = color;}
	public String getSubtitle() {return subtitle;}
	public void setSubtitle(String subtitle) {this.subtitle = subtitle;}
	
}
