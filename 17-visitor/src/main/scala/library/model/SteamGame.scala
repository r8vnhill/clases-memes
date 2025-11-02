package cl.uchile.dcc
package library.model

import library.visitor.ItemVisitor

/** Representa un videojuego de Steam almacenado en la biblioteca. */
class SteamGame(name: String, year: Int)
    extends AbstractItem(name, year)
    with VideoGame:
  /** Implementa el método de aceptación del visitor para SteamGame. */
  def accept(visitor: ItemVisitor): Unit =
    visitor.visitSteamGame(this)
