package cl.uchile.dcc
package geometry

/**
 * Represents a point on the 2D plane using coordinates (x, y).
 *
 * This version of the class is immutable in its public interface: The function
 * `move` does not change the internal state of the object but instead returns a
 * <b>new Point</b> with the updated coordinates.
 *
 * Key concepts illustrated here:
 *   - `private val`: the variables `x` and `y` are private attributes, meaning
 *     they can only be accessed from inside the class. This is part of the
 *     <b>encapsulation</b> principle in OOP.
 *   - `def move(dx, dy)`: a method that takes how much to move in x and y.
 *     Instead of modifying the current object, it creates a new one (concept of
 *     immutability).
 *
 * @param x
 *   the x-coordinate of the point
 * @param y
 *   the y-coordinate of the point
 */
class Point(private val x: Int, private val y: Int):

  /**
   * Moves this point by a given offset in x and y.
   *
   * @param dx
   *   how much to move along the x-axis
   * @param dy
   *   how much to move along the y-axis
   * @return
   *   a new Point object, with the new coordinates (x + dx, y + dy)
   * @example
   *   {{{
   * val p1 = new Point(2, 3)
   * val p2 = p1.move(1, -2)
   * // p2 is the point (3, 1)
   * // Note: `p1` remains unchanged at (2, 3).
   *   }}}
   */
  def move(dx: Int, dy: Int): Point =
    new Point(x + dx, y + dy)
