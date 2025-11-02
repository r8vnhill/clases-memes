package cl.uchile.dcc
package library.visitor

import library.model._
import library.predicate._

/** Visitor que evalúa un árbol de Predicate acumulando el resultado. */
class EvaluatePredicateVisitor extends AbstractBinaryPredicateVisitor[Boolean]:
  private var result: Boolean = false

  def getResult: Boolean = result

  // Implementación requerida por la clase base
  protected def acc: Boolean = result
  protected def acc_=(value: Boolean): Unit = result = value
  protected def combineAnd(left: Boolean, right: Boolean): Boolean = left && right
  protected def combineOr(left: Boolean, right: Boolean): Boolean = left || right

  // visitAnd/visitOr heredados desde la clase base

  override def visitByName(p: ByName, item: Item): Unit =
    result = item.name == p.getExpectedName

  override def visitByYear(p: ByYear, item: Item): Unit =
    result = item.year == p.getExpectedYear

  override def visitByPrefix(p: ByPrefix, item: Item): Unit =
    result = item.name.startsWith(p.getExpectedPrefix)

  // (sin overrides de visitAnd/visitOr)

  override def visitNeg(p: Neg, item: Item): Unit =
    p.getInner.accept(this, item)
    result = !result

  override def visitByVideoGame(p: ByVideoGame, item: Item): Unit =
    // Delegamos en un visitor de Item para consultar el tipo concreto
    val detector = new IsVideoGameVisitor
    item.accept(detector)
    result = detector.isVideoGame
