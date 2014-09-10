package org.ishvarloiva.snake;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;




public class ApplicationPanel extends JPanel {
  public String state = "new game";
  Variables vars;
  public SnakeWindow parent;
  private static final long serialVersionUID = 1L;

  public ApplicationPanel(Variables vars) {
    this.vars = vars;
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2.drawOval(vars.head.x, vars.head.y, 20, 20);
    switch (vars.last_direction) {
      case "up":
        g2.drawOval(vars.head.x + 7, vars.head.y + 4, 1, 1);
        g2.drawOval(vars.head.x + 13, vars.head.y + 4, 1, 1);
        break;
      case "down":
        g2.drawOval(vars.head.x + 7, vars.head.y + 16, 1, 1);
        g2.drawOval(vars.head.x + 13, vars.head.y + 16, 1, 1);
        break;
      case "left":
        g2.drawOval(vars.head.x + 7, vars.head.y + 7, 1, 1);
        g2.drawOval(vars.head.x + 7, vars.head.y + 13, 1, 1);
        break;
      case "right":
        g2.drawOval(vars.head.x + 13, vars.head.y + 7, 1, 1);
        g2.drawOval(vars.head.x + 13, vars.head.y + 13, 1, 1);
        break;
    }
    g2.drawOval(vars.fruit.x + 5, vars.fruit.y + 5, 10, 10);
    for (int i = 0; i < vars.snake.size(); i++) {
      g2.drawOval(vars.snake.get(i).x, vars.snake.get(i).y, 20, 20);
    }
  }
}
