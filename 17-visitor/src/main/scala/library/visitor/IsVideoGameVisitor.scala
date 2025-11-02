package cl.uchile.dcc
package library.visitor

import library.model.{Book, SteamGame}

/**
 * Visitor concreto que detecta si un Item es un VideoGame.
 *
 * En este ejemplo, cualquier SteamGame es un VideoGame; Book no lo es.
 */
class IsVideoGameVisitor extends ItemVisitor:
  private var result: Boolean = false

  override def visitBook(book: Book): Unit = result = false

  override def visitSteamGame(game: SteamGame): Unit = result = true

  def isVideoGame: Boolean = result
