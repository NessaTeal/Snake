package org.ishvarloiva.snake;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Snake - class which represent main object in our program</br> Snake contains list of points of
 * its body, direction to move
 */

public class Snake {
  // we use LinkedList because we will add one element into head and remove one from tail when snake
  // moves to one cell. LinkedList is a most appropriate collection implementation for this case.

  private final LinkedList<SPoint> body = new LinkedList<SPoint>();

  private final int x;

  private final int y;

  private Direction direction = Direction.LEFT;

  public Snake(int x, int y) {
    this.x = x;
    this.y = y;
    reset();
  }

  public void reset() {
    body.clear();
    body.add(new SPoint(x, y));
    try {
      move(true);
    } catch (SnakeEatItselfException e) {
      // should be never
      e.printStackTrace();
    }
  }

  public void changeDirection(Direction direction) {
    if (this.direction.isSameCoordinateLine(direction)) {
      return;
    }
    this.direction = direction;
  }

  /**
   * moves snake to one cell forward in current direction.
   * 
   * @param grow - if true snake will grow for one element
   * @throws EatItselfException if snake is eating itself
   */

  public void move(boolean grow) throws SnakeEatItselfException {
    SPoint head = body.getFirst();
    // create new point, set coordinate according to direction
    SPoint newHead = new SPoint(head);
    newHead.move(direction);

    // remove last point if we will not grow
    if (!grow) {
      body.removeLast();
    }

    if (body.contains(newHead)) {
      // body already contains point with coordinates of new head
      throw new SnakeEatItselfException();
    }
    // move head to new coordinate
    body.addFirst(newHead);
  }

  public SPoint getHead() {
    return body.getFirst();
  }

  public Direction getDirection() {
    return direction;
  }


  public boolean contains(SPoint point) {
    return body.contains(point);
  }

  public Iterator<SPoint> getBodyIterator() {
    return body.iterator();
  }

}
