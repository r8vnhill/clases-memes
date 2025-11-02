package cl.uchile.dcc
package library.visitor

import library.model.NullItem
import library.predicate.*

/** Visitor que genera una representación legible del árbol de `Predicate`. */
class PrettyPrintVisitor extends AbstractBinaryPredicateVisitor[String]:
  private var _acc: String = ""

  /** Reinicia el acumulador. */
  def reset(): Unit = _acc = ""

  /** Retorna la representación acumulada. */
  def result: String = _acc

  /** Genera el pretty print de un predicado completo. */
  def pretty(p: Predicate): String =
    reset()
    p.accept(visitor = this, item = NullItem)
    result

  override def visitByName(p: ByName, item: library.model.Item): Unit =
    _acc = s"name == \"${p.getExpectedName}\""

  override def visitByYear(p: ByYear, item: library.model.Item): Unit =
    _acc = s"year == ${p.getExpectedYear}"

  override def visitByPrefix(p: ByPrefix, item: library.model.Item): Unit =
    _acc = s"startsWith(\"${p.getExpectedPrefix}\")"

  override def visitByVideoGame(p: ByVideoGame,
                                item: library.model.Item
  ): Unit =
    _acc = "isVideoGame"

  override def visitNeg(p: Neg, item: library.model.Item): Unit =
    p.getInner.accept(visitor = this, item = item)
    val inner = _acc
    _acc = s"NOT($inner)"

  // Requisitos de la clase base (acceso/actualización del acumulador)
  protected def acc: String = _acc
  protected def acc_=(value: String): Unit = _acc = value
  protected def combineAnd(left: String, right: String): String =
    s"($left AND $right)"
  protected def combineOr(left: String, right: String): String =
    s"($left OR $right)"

  // visitAnd y visitOr provienen de la clase base
