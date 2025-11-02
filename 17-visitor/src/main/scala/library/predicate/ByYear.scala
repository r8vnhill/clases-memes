package cl.uchile.dcc
package library.predicate

import library.visitor.PredicateVisitor

/** Predicado que compara un año exacto de publicación. */
class ByYear(year: Int) extends Predicate:
  private val expectedYear: Int = year

  def getExpectedYear: Int = expectedYear

  override def accept(visitor: PredicateVisitor, item: library.model.Item): Unit =
    visitor.visitByYear(this, item)
