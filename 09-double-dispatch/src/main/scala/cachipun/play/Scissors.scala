package cl.uchile.dcc
package cachipun.play

import cachipun.result.{Draw, GameResult, Win}

// Ejemplo de jugada: Tijeras.
// Demuestra la simetría del double dispatch: cada clase conoce
// cómo reaccionar frente a cada otra jugada.
class Scissors extends Hand:
  override def playWith(other: Hand): GameResult =
    other.playWithScissors(this)

  override def playWithPaper(other: Paper): GameResult =
    new Win(this)

  override def playWithScissors(other: Scissors): GameResult =
    Draw

  override def playWithStone(other: Stone): GameResult =
    new Win(other)

  override def playWithLizard(other: Lizard): GameResult =
    new Win(this)
