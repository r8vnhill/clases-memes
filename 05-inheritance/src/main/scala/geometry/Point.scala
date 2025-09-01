package cl.uchile.dcc
package geometry

import java.awt.Color

trait Screen:
  def drawPoint(x: Int, y: Int): Unit

class Point(val x: Int, val y: Int):
  def getPosition: (Int, Int) = (x, y)
  def moveBy(dx: Int, dy: Int): Point = new Point(x + dx, y + dy)
  def display(screen: Screen): Unit = screen.drawPoint(x, y)

class ColorPoint(x: Int, y: Int, val color: Color) extends Point(x, y)

@main def testPoint(): Unit =
  val thisSlide: Screen = (x: Int, y: Int) => println(s"Drawing point at ($x, $y)")
  val p1 = new Point(10, 20)
  p1.display(thisSlide)
  val p2 = new ColorPoint(30, 40, Color.RED)
  p2.display(thisSlide)
