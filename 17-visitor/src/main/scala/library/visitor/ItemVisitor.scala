package cl.uchile.dcc
package library.visitor

import library.model.{Book, SteamGame}

/**
 * Visitor para operar sobre distintos tipos concretos de Item.
 *
 * Se usa para desacoplar operaciones de las clases concretas (Book, SteamGame).
 */
trait ItemVisitor:
  def visitBook(book: Book): Unit
  def visitSteamGame(game: SteamGame): Unit
