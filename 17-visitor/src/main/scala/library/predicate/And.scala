package cl.uchile.dcc
package library.predicate

import library.visitor.PredicateVisitor

/** Predicado compuesto que requiere que ambos hijos sean verdaderos. */
class And(left: Predicate, right: Predicate) extends Predicate:
  private val l: Predicate = left
  private val r: Predicate = right

  def getLeft: Predicate = l

  def getRight: Predicate = r

  override def accept(visitor: PredicateVisitor, item: library.model.Item): Unit =
    visitor.visitAnd(this, item)
