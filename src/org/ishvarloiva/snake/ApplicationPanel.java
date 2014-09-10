package org.ishvarloiva.snake;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Iterator;

import javax.swing.JPanel;

/*
 * We don't intend save status of JPanel - so we can avoid serial id
 */
@SuppressWarnings("serial")
public class ApplicationPanel extends JPanel {
  private static final int SNAKE_ITEM_RADIUS = 20;

  private final Snake snake;
  private final GameField field;

  public ApplicationPanel(GameField field, Snake snake) {
    this.field = field;
    this.snake = snake;
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    Iterator<SPoint> bodyIterator = snake.getBodyIterator();

    // we can don't check "hasNext" because snake always has head
    SPoint head = bodyIterator.next();
    g2.drawOval(head.x, head.y, SNAKE_ITEM_RADIUS, SNAKE_ITEM_RADIUS);
    switch (snake.getDirection()) {
      case UP:
        g2.drawOval(head.x + 7, head.y + 4, 1, 1);
        g2.drawOval(head.x + 13, head.y + 4, 1, 1);
        break;
      case DOWN:
        g2.drawOval(head.x + 7, head.y + 16, 1, 1);
        g2.drawOval(head.x + 13, head.y + 16, 1, 1);
        break;
      case LEFT:
        g2.drawOval(head.x + 7, head.y + 7, 1, 1);
        g2.drawOval(head.x + 7, head.y + 13, 1, 1);
        break;
      case RIGHT:
        g2.drawOval(head.x + 13, head.y + 7, 1, 1);
        g2.drawOval(head.x + 13, head.y + 13, 1, 1);
        break;
    }

    while (bodyIterator.hasNext()) {
      SPoint next = bodyIterator.next();
      g2.drawOval(next.x, next.y, SNAKE_ITEM_RADIUS, SNAKE_ITEM_RADIUS);
    }

    for (Iterator<SPoint> fruitsIterator = field.getFruitsIterator(); fruitsIterator.hasNext();) {
      SPoint next = fruitsIterator.next();
      g2.drawOval(next.x + 5, next.y + 5, 10, 10);
    }
  }
}
