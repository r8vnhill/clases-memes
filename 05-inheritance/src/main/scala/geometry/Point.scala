package cl.uchile.dcc

package geometry

import java.awt.Color

/**
 * Contrato mínimo para un objeto capaz de dibujar puntos.
 *
 * `Point` no necesita conocer cómo se dibuja realmente un punto: solamente
 * depende de esta operación.
 */
trait Screen:

  /**
   * Dibuja un punto en las coordenadas `(x, y)`.
   */
  def drawPoint(x: Int, y: Int): Unit

/**
 * Representa un punto bidimensional.
 *
 * @param x
 *   coordenada horizontal del punto.
 * @param y
 *   coordenada vertical del punto.
 */
class Point(val x: Int, val y: Int):

  /**
   * Retorna la posición actual del punto.
   */
  def getPosition: (Int, Int) = (x, y)

  /**
   * Crea un nuevo punto desplazado respecto del actual.
   *
   * @param dx
   *   desplazamiento horizontal.
   * @param dy
   *   desplazamiento vertical.
   * @return
   *   un nuevo punto en `(x + dx, y + dy)`.
   */
  def moveBy(dx: Int, dy: Int): Point =
    new Point(x + dx, y + dy)

  /**
   * Muestra este punto utilizando el `Screen` recibido.
   */
  def display(screen: Screen): Unit =
    screen.drawPoint(x, y)

/**
 * Especialización de [[Point]] que agrega un color.
 *
 * Además de incorporar el nuevo estado `color`, esta clase '''refina''' el
 * comportamiento heredado de `display`. En Scala, `override` indica
 * explícitamente que estamos reemplazando la implementación de un método
 * concreto definido por una superclase (Scala Documentation, n.d.-a).
 *
 * En este ejemplo no queremos perder el comportamiento original de
 * `Point`: primero dibujamos el punto y luego agregamos el comportamiento
 * relacionado con el color.
 *
 * ==Referencias==
 *
 * Scala Documentation. (n.d.-a). ''A Scala tutorial for Java
 * programmers''. Scala Documentation.
 *
 * Scala Documentation. (n.d.-b). ''Expressions''. The Scala language
 * specification, version 3.4.
 *
 * @param x
 *   coordenada horizontal del punto.
 * @param y
 *   coordenada vertical del punto.
 * @param color
 *   color asociado al punto.
 */
class ColorPoint(x: Int, y: Int, val color: Color) extends Point(x, y):

  /**
   * Refina la forma en que se muestra un punto.
   *
   * `super` permite acceder a una implementación heredada. En esta
   * jerarquía simple, `super.display(screen)` ejecuta primero el `display`
   * definido en [[Point]] (Scala Documentation, n.d.-b).
   *
   * Por ahora basta con interpretar `super` como:
   *
   * {{{
   * "ejecuta la implementación que heredé antes de agregar la mía"
   * }}}
   *
   * Más adelante pueden estudiarse casos de herencia donde la resolución
   * de `super` requiere mayor detalle.
   */
  override def display(screen: Screen): Unit =
    super.display(screen)
    println(s"Color: $color")

/**
 * Ejemplo de extensión y refinamiento.
 *
 * `p1` utiliza directamente el comportamiento definido por [[Point]].
 *
 * `p2` es un [[ColorPoint]]: hereda las operaciones de `Point`, agrega el
 * estado `color` y refina `display`.
 */
@main def testPoint(): Unit =

  val thisSlide: Screen = (x: Int, y: Int) =>
    println(s"Drawing point at ($x, $y)")

  val p1 = new Point(10, 20)
  p1.display(thisSlide)

  val p2 = new ColorPoint(30, 40, Color.RED)
  p2.display(thisSlide)
