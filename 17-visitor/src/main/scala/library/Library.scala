package cl.uchile.dcc
package library

import library.model.*
import library.predicate.*
import library.visitor.EvaluatePredicateVisitor

import scala.collection.mutable

/** Implementa una colección sencilla de `Item` con búsquedas por predicados. */
class Library:
  private val items: mutable.Set[Item] = mutable.Set.empty

  /** Indica si la biblioteca no contiene elementos. */
  def isEmpty: Boolean = items.isEmpty

  /** Agrega un elemento a la biblioteca. */
  def add(item: Item): Unit = items += item

  /** Filtra los elementos utilizando un `Predicate` y un visitor evaluador. */
  def searchByPredicate(p: Predicate): mutable.Set[Item] =
    items.filter { item =>
      val evaluator = new EvaluatePredicateVisitor()
      p.accept(evaluator, item)
      evaluator.getResult
    }
