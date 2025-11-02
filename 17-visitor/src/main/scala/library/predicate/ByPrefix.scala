package cl.uchile.dcc
package library.predicate

import library.visitor.PredicateVisitor

/** Predicado que busca nombres que comienzan con un prefijo dado. */
class ByPrefix(prefix: String) extends Predicate:
  private val expectedPrefix: String = prefix

  def getExpectedPrefix: String = expectedPrefix

  override def accept(visitor: PredicateVisitor, item: library.model.Item): Unit =
    visitor.visitByPrefix(this, item)
