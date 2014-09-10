package org.ishvarloiva.snake;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * This class contains initiated by size of game field, it contains array collections of fruits. In
 * future we also can add collection of stones
 */
public class GameField {
  public enum SnakeStatus {
    OUT_OF_FIELD, FRUIT_EATEN, OK
  }

  private final int width;
  private final int height;

  private final LinkedList<SPoint> fruits = new LinkedList<>();

  /**
   * Create new GameField. Valid snake coordinates in range 0x0 - (width-1)x(height-1)
   * 
   * @param width
   * @param height
   */
  public GameField(int width, int height) {
    this.width = width;
    this.height = height;
  }
  
  public void reset(){
    fruits.clear();
  }

  /*
   * we need snake to generate fruit to avoid case when fruit generated inside snake
   */
  public void generateFruit(Snake snake) {
    SPoint point;
    do {
      point = getRandomPoint();
    } while (snake.contains(point));
    fruits.add(point);
  }

  private SPoint getRandomPoint() {
    int widthStep = (width) / SPoint.STEP_SIZE;
    int heightStep = (height) / SPoint.STEP_SIZE;
    return new SPoint((int) Math.round(Math.random() * (widthStep)) * SPoint.STEP_SIZE,
        (int) Math.round(Math.random() * (heightStep)) * SPoint.STEP_SIZE);
  }

  public SnakeStatus check(Snake snake) {
    SPoint head = snake.getHead();
    if (head.x < 0 || head.y < 0 || head.x >= width || head.y >= height) {
      return SnakeStatus.OUT_OF_FIELD;
    }

    if (fruits.contains(head)) {
      fruits.remove(head);
      return SnakeStatus.FRUIT_EATEN;
    }

    return SnakeStatus.OK;
  }

  public Iterator<SPoint> getFruitsIterator() {
    return fruits.iterator();
  }


}
