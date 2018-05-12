
/**
 * @author Warren Smith This Class is used to retain coordinates for 2D Array.
 */
public class Vector2 {
  private int valueX;
  private int valueY;

  /**
   * Constructor.
   * @param x The X coordinate.
   * @param y The Y coordinate.
   */
  public Vector2(int x, int y) {
    valueX = x;
    valueY = y;
  }

  /**
   * Sets the X and Y values to zero.
   */
  public void zero() {
    valueX = valueY = 0;
  }

  /**
   * Does a print line of both the X and Y values.
   */
  public void print() {
    System.out.println("( " + valueX + ", " + valueY + " )");
  }
}
