package cl.uchile.dcc
package cachipun.result

import cachipun.play.Hand

// Caso de victoria. Guarda la mano que ganó.
// Permite mostrar qué jugada resultó vencedora.
class Win(val hand: Hand) extends GameResult:
  override def toString: String = s"Win: $hand"
