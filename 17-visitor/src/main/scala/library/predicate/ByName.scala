package cl.uchile.dcc
package library.predicate

import library.model.Item
import library.visitor.PredicateVisitor

class ByName(val name: String) extends Predicate:
  override def accept(visitor: PredicateVisitor, item: Item): Unit =
    visitor.visitByName(this, item)
