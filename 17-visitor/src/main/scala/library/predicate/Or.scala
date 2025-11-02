package cl.uchile.dcc
package library.predicate

import library.visitor.PredicateVisitor

/** Predicado compuesto que acepta si al menos un hijo es verdadero. */
class Or(left: Predicate, right: Predicate) extends Predicate:
  private val l: Predicate = left
  private val r: Predicate = right

  def getLeft: Predicate = l

  def getRight: Predicate = r

  override def accept(visitor: PredicateVisitor, item: library.model.Item): Unit =
    visitor.visitOr(this, item)
