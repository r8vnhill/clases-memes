package cl.uchile.dcc
package cachipun.play

import cachipun.result.{Draw, GameResult, Win}

// Ejemplo de jugada: Papel.
// Cada implementación usa métodos especializados para resolver el resultado.
// Extender el juego con nuevas variantes no rompe este código.
class Paper extends AbstractHand:
  override def playWith(other: Hand): GameResult =
    other.playWithPaper(this)

  override def playWithPaper(other: Paper): GameResult =
    Draw

  override def playWithScissors(other: Scissors): GameResult =
    new Win(other)

  override def playWithStone(other: Stone): GameResult =
    new Win(this)

  override def playWithLizard(other: Lizard): GameResult =
    new Win(other)
