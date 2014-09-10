package org.ishvarloiva.snake;

import javax.swing.*;



/**
 * It is helper class to show main frame. Make it abstract to disable instantiate.
 * 
 * @author user
 * 
 */
public abstract class SnakeWindow {
  /*
   * add private constructor to disable inheritance
   */
  private SnakeWindow() {}

  public static void show() {
    Variables  vars = new Variables();
    JFrame frame = new JFrame("Snake");
    // parent unused - remove it
    ApplicationPanel window = new ApplicationPanel(vars);
    SnakeEngine e = new SnakeEngine(vars, window);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setBounds(500, 200, 597, 480);
    
    frame.addKeyListener(e);
    frame.setContentPane(window);

    // call frame.setVisible at the last of method. frame should be fully configured before. 
    frame.setVisible(true);

  }
}
