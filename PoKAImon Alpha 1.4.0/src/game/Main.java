package game;
import javax.swing.JFrame;

public class Main extends JFrame {
	
	public static final int frameWidth = 1000;
	public static final int frameHeight = 800;
	
	public Main()
	{
		add(new Board());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(frameWidth, frameHeight + 22);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
	}
	
	public static void main(String[] args) {
		new Main();
	}
	
}