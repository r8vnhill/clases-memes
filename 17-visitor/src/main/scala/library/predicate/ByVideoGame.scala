package cl.uchile.dcc
package library.predicate

import library.visitor.PredicateVisitor

/** Predicado que selecciona solo ítems que son videojuegos. */
class ByVideoGame extends Predicate:
  override def accept(visitor: PredicateVisitor, item: library.model.Item): Unit =
    visitor.visitByVideoGame(this, item)
