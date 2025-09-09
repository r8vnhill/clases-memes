package cl.uchile.dcc
package cachipun.play

import cachipun.result.{Draw, GameResult, Win}

// Ejemplo de jugada: Piedra.
// Aplica double dispatch en playWith, delegando en el otro objeto.
// Cumple OCP porque no necesita if/else ni modificar código existente.
class Stone extends Hand:
  override def playWith(other: Hand): GameResult =
    other.playWithStone(this) // Double dispatch

  override def playWithPaper(other: Paper): GameResult =
    new Win(other)

  override def playWithScissors(other: Scissors): GameResult =
    new Win(this)

  override def playWithStone(other: Stone): GameResult =
    Draw

  override def playWithLizard(other: Lizard): GameResult =
    new Win(this)
