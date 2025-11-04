package cl.uchile.dcc
package expression.oop

/** Enfoque OOP con Composite (sin Visitor):
  * - Agregar nuevas variantes (tipos) es barato: se definen nuevas clases que implementan la interfaz.
  * - Agregar nuevas operaciones puede ser costoso: hay que modificar todas las clases.
  * Este archivo modela expresiones aritméticas con métodos `eval` y `pretty`.
  */

// "Dominio" de expresiones aritméticas (Composite)
trait OExpr:
  /** Evalúa la expresión */
  def eval: Int
  /** Imprime en notación infija */
  def pretty: String

class OConst(val v: Int) extends OExpr:
  def eval: Int = v
  def pretty: String = v.toString

class OAdd(val left: OExpr, val right: OExpr) extends OExpr:
  def eval: Int = left.eval + right.eval
  def pretty: String = s"(${left.pretty} + ${right.pretty})"

class OSub(val left: OExpr, val right: OExpr) extends OExpr:
  def eval: Int = left.eval - right.eval
  def pretty: String = s"(${left.pretty} - ${right.pretty})"

@main def oopExpressionExample(): Unit =
  val expr: OExpr = new OAdd(new OConst(1), new OSub(new OConst(5), new OConst(2)))
  println(s"eval=${expr.eval}, pretty=${expr.pretty}")
