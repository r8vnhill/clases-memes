package cl.uchile.dcc
package geometry

class Rectangle(private val origin: Point, private val height: Int, private val width: Int) extends IRectangle:
    override def move(x: Int, y: Int): IRectangle =
        new Rectangle(origin.move(x, y), height, width)

    override def area(): Int = height * width
