package cl.uchile.dcc
package library.predicate

import library.visitor.PredicateVisitor

/** Predicado que filtra por coincidencia exacta de nombre. */
class ByName(name: String) extends Predicate:
  private val expectedName: String = name

  def getExpectedName: String = expectedName

  override def accept(visitor: PredicateVisitor, item: library.model.Item): Unit =
    visitor.visitByName(this, item)
