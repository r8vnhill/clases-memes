package cl.uchile.dcc
package expression.fp

/** Enfoque FP: las variantes se modelan con case classes y pattern matching.
  * Agregar nuevas operaciones es barato (nuevas funciones que hacen match).
  */

sealed trait FExpr
case class FConst(v: Int) extends FExpr
case class FAdd(left: FExpr, right: FExpr) extends FExpr
case class FSub(left: FExpr, right: FExpr) extends FExpr

/** Evalúa usando pattern matching */
def eval(e: FExpr): Int =
  e match
    case FConst(v)     => v
    case FAdd(l, r)    => eval(l) + eval(r)
    case FSub(l, r)    => eval(l) - eval(r)

/** Pretty print en notación infija */
def pretty(e: FExpr): String =
  e match
    case FConst(v)  => v.toString
    case FAdd(l, r) => s"(${pretty(l)} + ${pretty(r)})"
    case FSub(l, r) => s"(${pretty(l)} - ${pretty(r)})"

@main def fpExpressionExample(): Unit =
  val e: FExpr = FAdd(FConst(1), FSub(FConst(5), FConst(2)))
  println(s"eval=${eval(e)}, pretty=${pretty(e)}")
