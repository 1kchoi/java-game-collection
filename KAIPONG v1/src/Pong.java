import java.awt.GraphicsEnvironment;
import java.util.Arrays;

import javax.swing.JFrame;

public class Pong extends JFrame {
	
	public Pong()
	{
		add(new Board());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
	}
	
	public static void main(String[] args) {	
		System.out.println(Arrays.deepToString(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()));
		new Pong();
		
	}
	
}