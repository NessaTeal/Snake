package screen;

import screen_paint.ApplicationWindow;
import javax.swing.*;
import engine.Snake_Engine;
import variables.Variables;

public class Snake_window
{
    public Snake_window(Variables vars)
    {	
    	JFrame frame = new JFrame("Snake");
    	ApplicationWindow window = new ApplicationWindow(vars, this);
    	Snake_Engine e = new Snake_Engine(vars, window);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setBounds(500, 200, 597, 480);
		frame.setVisible(true);
    	frame.addKeyListener(e);
		frame.setContentPane(window);
	}
}