package org.ishvarloiva.snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.Math;
import java.awt.Point;

public class SnakeEngine implements KeyListener, Runnable {
  boolean flag = false;
  boolean fruit_flag = false;
  Variables vars;
  ApplicationPanel parent;
  Thread worker = new Thread(this);

  public SnakeEngine(Variables vars, ApplicationPanel parent) {
    this.vars = vars;
    this.parent = parent;
    move_fruit();
  }

  void move_fruit() {
    do {
      vars.fruit = new Point((int) (Math.random() * 28) * 20, (int) (Math.random() * 21) * 20);
      for (int i = 0; i < vars.snake.size() - 1; i++) {
        if (vars.fruit.equals(vars.snake.get(i)))
          fruit_flag = true;
      }
      if (vars.fruit.equals(vars.head))
        fruit_flag = true;
    } while (fruit_flag);
  }

  public void keyPressed(KeyEvent e) {
    if (e.getKeyChar() == 'w' && vars.last_direction != "down") {
      vars.direction = "up";
      if (!worker.isAlive())
        worker.start();
    }
    if (e.getKeyChar() == 's' && vars.last_direction != "up") {
      vars.direction = "down";
      if (!worker.isAlive())
        worker.start();
    }
    if (e.getKeyChar() == 'a' && vars.last_direction != "right") {
      vars.direction = "left";
      if (!worker.isAlive())
        worker.start();
    }
    if (e.getKeyChar() == 'd' && vars.last_direction != "left") {
      vars.direction = "right";
      if (!worker.isAlive())
        worker.start();
    }
  }

  public void keyTyped(KeyEvent e) {

  }

  public void keyReleased(KeyEvent e) {

  }

  public void run() {
    while (true) {
      for (int i = vars.snake.size() - 1; i > 0; i--) {
        vars.snake.get(i).x = vars.snake.get(i - 1).x;
        vars.snake.get(i).y = vars.snake.get(i - 1).y;
      }
      vars.snake.get(0).x = vars.head.x;
      vars.snake.get(0).y = vars.head.y;


      if (flag) {
        vars.snake.add(vars.last);
        flag = false;
      }

      if (vars.direction == "up")
        vars.head.y -= 20;
      else if (vars.direction == "down")
        vars.head.y += 20;
      else if (vars.direction == "left")
        vars.head.x -= 20;
      else
        vars.head.x += 20;
      for (int i = 0; i < vars.snake.size(); i++) {
        if (vars.head.equals(vars.snake.get(i))) {
          System.out.println("1");
        }
      }
      if (vars.head.equals(vars.fruit)) {
        move_fruit();
        flag = true;
        vars.last =
            new Point(vars.snake.get(vars.snake.size() - 1).x,
                vars.snake.get(vars.snake.size() - 1).y);
      }

      parent.repaint();

      vars.last_direction = vars.direction;

      try {
        Thread.sleep(200);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
