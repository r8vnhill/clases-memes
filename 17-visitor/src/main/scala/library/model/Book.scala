package cl.uchile.dcc
package library.model

import library.visitor.ItemVisitor

/** Representa un libro contenido en la biblioteca. */
class Book(name: String, year: Int) extends AbstractItem(name, year):
  /** Implementa el método de aceptación del visitor para Books. */
  def accept(visitor: ItemVisitor): Unit =
    visitor.visitBook(this)
