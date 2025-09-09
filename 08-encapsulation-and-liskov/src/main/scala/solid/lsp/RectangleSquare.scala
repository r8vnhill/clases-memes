package cl.uchile.dcc
package solid.lsp

trait RectangleView:
  def width: Int
  def height: Int
  def area: Int

class Rectangle(private var _width: Int,
                private var _height: Int)
    extends RectangleView:
  override def width: Int =
    _width
  override def height: Int =
    _height
  override def area: Int =
    _width * _height
  def width_=(width: Int): Unit =
    _width = width
  def height_=(height: Int): Unit =
    _height = height

class Square(_side: Int)
    extends RectangleView:
  // En lugar de heredar, delegamos a Rectangle
  private val rect = Rectangle(_side, _side)
  def side: Int = rect.width
  def side_=(side: Int): Unit =
    rect.height = side
    rect.width = side
  override def width: Int = rect.width
  override def height: Int = rect.height
  override def area: Int = rect.area

def resizeRectangle(r: Rectangle): Unit =
  r.width = 5
  r.height = 10
  assert(r.width * r.height == 50)
