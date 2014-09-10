package screen;

import screen_paint.ApplicationWindow;
import javax.swing.*;
import engine.Snake_Engine;
import variables.Variables;

/**
 * It is helper class to show main frame. Make it abstract to disable instantiate.
 * 
 * @author user
 * 
 */
public abstract class Snake_window {
  /*
   * add private constructor to disable inheritance
   */
  private Snake_window() {}

  public static void show() {
    Variables  vars = new Variables();
    JFrame frame = new JFrame("Snake");
    // parent unused - remove it
    ApplicationWindow window = new ApplicationWindow(vars);
    Snake_Engine e = new Snake_Engine(vars, window);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setBounds(500, 200, 597, 480);
    
    frame.addKeyListener(e);
    frame.setContentPane(window);

    // call frame.setVisible at the last of method. frame should be fully configured before. 
    frame.setVisible(true);

  }
}
