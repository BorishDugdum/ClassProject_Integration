
/**
 * @author Warren Smith.
 * 
 */
public abstract class Clothes {

  protected boolean bodyCovered = false;
  protected boolean legsCovered = false;
  protected boolean feetCovered = false;

  /**
   * Constructor.
   */
  public Clothes() {
    bodyCovered = false;
    legsCovered = false;
    feetCovered = false;
  }

  /**
   * @return Whether body is covered.
   */
  public boolean isBodyCovered() {
    return bodyCovered;
  }

  /**
   * @return Whether legs are covered.
   */
  public boolean areLegsCovered() {
    return legsCovered;
  }

  /**
   * @return Whether feet are covered.
   */
  public boolean areFeetCovered() {
    return feetCovered;
  }
}
