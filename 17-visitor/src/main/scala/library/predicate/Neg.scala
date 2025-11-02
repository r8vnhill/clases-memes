package cl.uchile.dcc
package library.predicate

import library.visitor.PredicateVisitor

/** Predicado unario que invierte el resultado de su hijo. */
class Neg(p: Predicate) extends Predicate:
  private val inner: Predicate = p

  def getInner: Predicate = inner

  override def accept(visitor: PredicateVisitor, item: library.model.Item): Unit =
    visitor.visitNeg(this, item)
