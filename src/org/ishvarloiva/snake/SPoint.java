package org.ishvarloiva.snake;

public class SPoint {
  public static final int STEP_SIZE = 20;
  public int x;
  public int y;

  public SPoint() {}

  public SPoint(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public SPoint(SPoint point) {
    this.x = point.x;
    this.y = point.y;
  }

  
  /**
   * helper method allows move point to one unit by direction
   * 
   * @param direction - move direction
   */
  public void move(Direction direction) {
    switch (direction) {
    /*
     * Y axis in swing from up to down.
     */
      case UP:
        y -= STEP_SIZE;
        break;
      case DOWN:
        y += STEP_SIZE;
        break;
      /*
       * X axis as usually from left to right
       */
      case LEFT:
        x -= STEP_SIZE;
        break;
      case RIGHT:
        x += STEP_SIZE;
        break;
    }

  }

  /*
   * We need equals to find point in collections To generate correct equals and hasCode methods we
   * use standard Eclipse refactoring (Source->Generate hasCode and equals)
   * 
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    SPoint other = (SPoint) obj;
    if (x != other.x)
      return false;
    if (y != other.y)
      return false;
    return true;
  }


  /*
   * We MUST override hashCode if we override equals
   * 
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + x;
    result = prime * result + y;
    return result;
  }



}
