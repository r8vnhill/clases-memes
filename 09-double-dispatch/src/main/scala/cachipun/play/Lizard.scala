package cl.uchile.dcc
package cachipun.play

import cachipun.result.{Draw, GameResult, Win}

// Ejemplo de jugada: Lagarto.
// El método playWith delega en el oponente → double dispatch.
// Cada método especializado indica el resultado contra otra jugada.
class Lizard extends AbstractHand:
  override def playWith(other: Hand): GameResult =
    other.playWithLizard(this)

  override def playWithLizard(other: Lizard): GameResult =
    Draw // Todos los empates son iguales → singleton

  override def playWithPaper(other: Paper): GameResult =
    new Win(this)

  override def playWithScissors(other: Scissors): GameResult =
    new Win(other)

  override def playWithStone(other: Stone): GameResult =
    new Win(other)
