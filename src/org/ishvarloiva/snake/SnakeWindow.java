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
    int width = 597;
    int height = 480;

    JFrame frame = new JFrame("Snake");
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setBounds(500, 200, width, height);


    GameField field = new GameField(width, height);
    Snake snake = new Snake(260, 220);
    ApplicationPanel panel = new ApplicationPanel(field, snake);
    
    SnakeController controller = new SnakeController(panel, field, snake);
    
    String[] commands = SnakeController.commands;
    
    for (int i = 0; i < commands.length; i++) {
      panel.registerKeyboardAction(controller, commands[i], KeyStroke.getKeyStroke(commands[i]),
          JComponent.WHEN_IN_FOCUSED_WINDOW);
    }
    frame.setContentPane(panel);

    // call frame.setVisible at the last of method. frame should be fully configured before.
    frame.setVisible(true);
    controller.start();

  }
}
