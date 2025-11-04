package cl.uchile.dcc
package library.predicate

import library.model.Item
import library.visitor.PredicateVisitor

trait Predicate:
  def accept(visitor: PredicateVisitor, item: Item): Unit
