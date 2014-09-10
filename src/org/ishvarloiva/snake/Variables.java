package org.ishvarloiva.snake;

import java.util.ArrayList;
import java.awt.Point;

public class Variables {
  public ArrayList<Point> snake = new ArrayList<Point>();
  public Point head = new Point(280, 220);
  public Point fruit = new Point();
  public Point last = new Point();
  public String direction = "right";
  public String last_direction = "right";

  public Variables() {
    snake.add(new Point(260, 220));
    snake.add(new Point(240, 220));
  }
}
