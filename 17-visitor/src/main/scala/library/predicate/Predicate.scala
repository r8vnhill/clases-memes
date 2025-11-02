package cl.uchile.dcc
package library.predicate

import library.visitor.PredicateVisitor
import library.model.Item

/** Contrato base para todos los predicados utilizados por la biblioteca. */
trait Predicate:
  /** Acepta un visitor para evaluar este predicado sobre un ítem dado. */
  def accept(visitor: PredicateVisitor, item: Item): Unit
