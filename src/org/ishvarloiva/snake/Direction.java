package org.ishvarloiva.snake;

/**
 * enum which contains direction for movement
 * we add horizontal to simplify detect coordinate line
 */
public enum Direction {
  UP(false), DOWN(false), LEFT(true), RIGHT(true);
  
  private final boolean horizontal;
  

  Direction(boolean horizontal){
    this.horizontal = horizontal;
  }

  public boolean isSameCoordinateLine(Direction direction) {
    return horizontal == direction.horizontal;
  }

  public boolean isHorizontal() {
    return horizontal;
  }
}
