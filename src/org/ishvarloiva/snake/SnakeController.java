package org.ishvarloiva.snake;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.ishvarloiva.snake.GameField.SnakeStatus;

public class SnakeController implements ActionListener {

  public static final String[] commands = {"UP", "DOWN", "LEFT", "RIGHT", "W", "S", "A", "D"};

  private final ApplicationPanel panel;
  private final GameField gameField;
  private final Snake snake;
  private ScheduledExecutorService scheduledExecutorService;

  private Direction nextDirection;
  private int counter;
  private boolean willGrow;

  private boolean stoped;


  public SnakeController(ApplicationPanel parent, GameField gameField, Snake snake) {
    this.gameField = gameField;
    this.snake = snake;
    this.panel = parent;
    init();
  }


  private void init() {
    counter = 0;
    willGrow = false;
    stoped = false;
    snake.reset();
    gameField.reset();
    nextDirection = snake.getDirection();
  }

  public void start() {
    scheduledExecutorService = Executors.newScheduledThreadPool(1);
    scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
      @Override
      public void run() {
        try {
          doTurn();
        } catch (Exception e) {
          e.printStackTrace();
          stopGame();
        }
        // We MUST call repaint from Swing UI thread!
        SwingUtilities.invokeLater(new Runnable() {
          @Override
          public void run() {
            panel.repaint();
          }
        });
      }
    }, 0, 300, TimeUnit.MILLISECONDS);
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    String commandName = e.getActionCommand();
    switch (commandName) {
      case "UP":
      case "W":
        nextDirection = Direction.UP;
        break;

      case "DOWN":
      case "S":
        nextDirection = Direction.DOWN;
        break;

      case "LEFT":
      case "A":
        nextDirection = Direction.LEFT;
        break;

      case "RIGHT":
      case "D":
        nextDirection = Direction.RIGHT;
        break;

      default:
        break;
    }
  }

  private void doTurn() {

    if (stoped) {
      return;
    }

    snake.changeDirection(nextDirection);
    try {
      snake.move(willGrow);
      willGrow = false;
    } catch (SnakeEatItselfException e) {
      stopGame();
      return;
    }

    SnakeStatus check = gameField.check(snake);
    switch (check) {
      case FRUIT_EATEN:
        willGrow = true;
        break;

      case OUT_OF_FIELD:
        stopGame();
        return;

      default:
        break;
    }

    if (counter == 10) {
      gameField.generateFruit(snake);
      counter = 0;
    } else {
      counter++;
    }
  }



  private void stopGame() {
    stoped = true;
    scheduledExecutorService.shutdown();
    int dialogResult =
        JOptionPane.showConfirmDialog(panel, "Would you like repeat?", "RIP",
            JOptionPane.YES_NO_OPTION);
    if (dialogResult == JOptionPane.YES_OPTION) {
      init();
      start();
    } else {
      System.exit(0);
    }
  }
}
