package cl.uchile.dcc

package geometry

import java.awt.Color

/**
 * Contrato mínimo para un objeto capaz de dibujar puntos.
 *
 * [[Point]] necesita mostrar una posición en algún destino, pero no
 * necesita conocer cómo se realiza concretamente esa operación. En lugar
 * de depender de una implementación particular ---por ejemplo, una ventana
 * gráfica o la consola--- depende de la abstracción [[Screen]].
 *
 * De esta forma, cualquier objeto que satisfaga este contrato puede
 * utilizarse para mostrar un [[Point]]. La implementación concreta queda
 * fuera de la responsabilidad de `Point`.
 *
 * En este curso utilizamos los traits como contratos: especifican qué
 * operaciones deben estar disponibles, pero no proporcionan su
 * implementación.
 */
trait Screen:

  /**
   * Dibuja un punto en las coordenadas `(x, y)`.
   *
   * La forma concreta de representar el punto depende de la implementación
   * de [[Screen]].
   *
   * @param x
   *   coordenada horizontal del punto.
   * @param y
   *   coordenada vertical del punto.
   */
  def drawPoint(x: Int, y: Int): Unit

/**
 * Representa un punto bidimensional.
 *
 * Un `Point` mantiene dos coordenadas, `x` e `y`, y proporciona
 * operaciones relacionadas directamente con su posición.
 *
 * Como ambas coordenadas son `val`, la posición de un `Point` no cambia
 * después de construirlo. Por esta razón, [[moveBy]] no modifica el objeto
 * actual: crea y retorna un nuevo punto con las coordenadas desplazadas.
 *
 * El método [[display]] tampoco conoce los detalles de cómo dibujar el
 * punto. Delega esa responsabilidad al [[Screen]] recibido como argumento.
 *
 * @param x
 *   coordenada horizontal del punto.
 * @param y
 *   coordenada vertical del punto.
 */
class Point(val x: Int, val y: Int):

  /**
   * Retorna la posición de este punto.
   *
   * @return
   *   un par `(x, y)` con sus coordenadas actuales.
   */
  def getPosition: (Int, Int) =
    (x, y)

  /**
   * Crea un nuevo punto desplazado respecto de este.
   *
   * Esta operación no modifica el punto original.
   *
   * Si este punto se encuentra en `(x, y)`, el nuevo punto se encontrará
   * en `(x + dx, y + dy)`.
   *
   * @param dx
   *   desplazamiento horizontal.
   * @param dy
   *   desplazamiento vertical.
   * @return
   *   un nuevo punto con la posición desplazada.
   */
  def moveBy(dx: Int, dy: Int): Point =
    new Point(x + dx, y + dy)

  /**
   * Muestra este punto utilizando el [[Screen]] recibido.
   *
   * `Point` decide '''qué''' debe mostrarse ---sus coordenadas---,
   * mientras que `Screen` decide '''cómo''' realizar el dibujo.
   *
   * @param screen
   *   objeto encargado de dibujar el punto.
   */
  def display(screen: Screen): Unit =
    screen.drawPoint(x, y)

/**
 * Especialización de [[Point]] que agrega un color.
 *
 * `ColorPoint` mantiene todas las propiedades de un [[Point]] y agrega una
 * nueva: [[color]]. En términos de herencia, `ColorPoint` es una subclase
 * de `Point` y, por lo tanto, también es un subtipo de `Point`.
 *
 * La clase no solo agrega estado: también '''refina''' comportamiento
 * heredado. En particular, redefine [[display]] mediante `override`.
 *
 * En Scala, el modificador `override` debe utilizarse al reemplazar un
 * método concreto heredado. Esto hace explícita la intención de
 * sobrescribir una implementación existente y permite al compilador
 * detectar sobrescrituras accidentales (Scala Documentation, n.d.-a).
 *
 * El ejemplo mantiene deliberadamente el comportamiento original de
 * [[Point.display]] y agrega un paso adicional. Para ello, la nueva
 * implementación llama primero a `super.display(screen)`.
 *
 * '''Importante:''' este ejemplo está simplificado para estudiar herencia
 * y refinamiento. `ColorPoint` no dibuja realmente el punto utilizando ese
 * color, porque el contrato [[Screen]] solo permite dibujar coordenadas.
 * El `println` se utiliza únicamente para hacer visible el comportamiento
 * adicional.
 *
 * ==Referencias==
 *
 * Scala Documentation. (n.d.-a). ''A Scala tutorial for Java
 * programmers''.
 * https://docs.scala-lang.org/tutorials/scala-for-java-programmers.html
 *
 * Odersky, M., et al. (n.d.-b). ''Expressions''. Scala language
 * specification, version 3.4.
 * https://www.scala-lang.org/files/archive/spec/3.4/06-expressions.html
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
   * Refina la forma en que este punto se muestra.
   *
   * La implementación realiza dos pasos: (1) reutiliza el comportamiento
   * heredado de [[Point]] para dibujar sus coordenadas; (2) agrega el
   * comportamiento específico de [[ColorPoint]], mostrando su color.
   *
   * La expresión `super.display(screen)` permite acceder a una
   * implementación heredada que ha sido sobrescrita. En esta jerarquía de
   * herencia simple, corresponde al `display` definido por [[Point]]
   * (Odersky et al., n.d.-b).
   *
   * Por ahora, puede interpretarse `super.display(screen)` como:
   *
   * {{{
   * "ejecuta primero el comportamiento que heredé y luego continúa con el mío"
   * }}}
   *
   * Esta interpretación es suficiente para la herencia simple utilizada en
   * este curso. En jerarquías que combinan traits con implementaciones, la
   * resolución de `super` puede depender de reglas adicionales como la
   * linearización; esos casos están fuera del alcance de este ejemplo.
   *
   * @param screen
   *   objeto encargado de dibujar las coordenadas del punto.
   */
  override def display(screen: Screen): Unit =
    super.display(screen)
    println(s"Color: $color")

/**
 * Demuestra extensión, herencia y refinamiento de comportamiento.
 *
 * El ejemplo crea dos objetos:
 *
 *   - `p1` es un [[Point]] y utiliza directamente la implementación de
 *     [[Point.display]];
 *   - `p2` es un [[ColorPoint]], por lo que hereda el comportamiento de
 *     [[Point]] y refina `display` para agregar una acción relacionada con
 *     su color.
 *
 * `thisSlide` proporciona una implementación explícita de [[Screen]]. De
 * esta forma, el ejemplo muestra directamente cómo una clase puede cumplir
 * el contrato definido por un `trait`.
 *
 * El ejemplo permite observar además el orden de ejecución de
 * [[ColorPoint.display]]: primero se ejecuta el comportamiento heredado y
 * después el comportamiento agregado por la subclase.
 *
 * ==Referencias==
 *
 * Odersky, M., et al. (n.d.). ''Expressions''. Scala language
 * specification, version 3.4.
 * https://www.scala-lang.org/files/archive/spec/3.4/06-expressions.html
 */
@main def testPoint(): Unit =
  val thisSlide: Screen = new Screen:
    override def drawPoint(x: Int, y: Int): Unit =
      println(s"Drawing point at ($x, $y)")

  val p1 = new Point(10, 20)
  p1.display(thisSlide)

  val p2 = new ColorPoint(30, 40, Color.RED)
  p2.display(thisSlide)
