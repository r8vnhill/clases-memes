package cl.uchile.dcc

package car

/**
 * Representa un auto compuesto por un [[Engine]].
 *
 * Este ejemplo ilustra una relación de '''composición''': `Car` representa
 * el objeto completo y `Engine` una de sus partes. La composición se
 * utiliza para modelar relaciones parte-todo con una propiedad más fuerte
 * que una asociación común (Fowler, 2003).
 *
 * En este diseño, el motor se crea dentro del propio `Car`:
 *
 * {{{
 * val engine = new Engine
 * }}}
 *
 * Esto hace explícita la intención de que el motor '''pertenece al
 * auto''': no se recibe desde afuera y, en este ejemplo, tampoco se
 * comparte con otros autos.
 *
 * Podemos representar la relación de forma conceptual como:
 *
 * {{{
 * Car ◆──── Engine
 * todo       parte
 * }}}
 *
 * El rombo relleno (`◆`) es la notación UML utilizada para representar
 * composición.
 *
 * ==Referencias==
 *
 * Fowler, M. (2003, May 17). ''Aggregation and composition''.
 * MartinFowler.com.
 * https://martinfowler.com/bliki/AggregationAndComposition.html
 */
class Car:

  /**
   * Motor que forma parte de este auto.
   *
   * A diferencia de una dependencia recibida desde afuera, `Car` crea
   * directamente esta instancia como parte de su propia representación.
   */
  val engine = new Engine

  /**
   * Enciende el auto utilizando su motor.
   *
   * `Car` no implementa nuevamente la operación de encendido: delega esa
   * responsabilidad al objeto que representa el motor.
   *
   * @return
   *   el resultado de encender el motor.
   */
  def start(): String =
    engine.start()
