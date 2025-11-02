package cl.uchile.dcc
package geometry

/**
 * Represents the idea (or contract) of a Rectangle.
 *
 * A trait in Scala is like an interface in other languages: it defines the
 * <b>methods</b> that any class claiming to be a Rectangle must provide.
 *
 * Important:
 *   - A trait does not give an implementation here. It only says "any Rectangle
 *     must be able to do these things."
 *   - Later, classes that implement this trait will be responsible for
 *     providing the concrete code for each method.
 *
 * Key concepts illustrated:
 *   - **Abstraction**: We describe *what* a Rectangle can do (move and compute
 *     area), not *how* it does it.
 *   - **Encapsulation of behavior**: By depending on the trait instead of a
 *     specific class, we gain flexibility in OOP.
 */
trait IRectangle:

  /**
   * Moves the rectangle by a given offset in the 2D plane.
   *
   * Note: This does not say *how* the rectangle is moved— that’s up to the
   * class implementing this trait.
   *
   * @param x
   *   how much to move along the x-axis
   * @param y
   *   how much to move along the y-axis
   * @return
   *   a new IRectangle that represents the moved rectangle
   */
  def move(x: Int, y: Int): IRectangle

  /**
   * Computes the area of the rectangle.
   *
   * Again: the trait does not explain *how* the area is calculated. That will
   * depend on the implementation.
   *
   * @return
   *   the area as an Int
   */
  def area(): Int
