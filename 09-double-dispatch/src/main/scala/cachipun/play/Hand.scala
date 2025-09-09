package cl.uchile.dcc
package cachipun.play

import cachipun.result.GameResult

// Interfaz principal. Define el contrato para jugar contra otra mano.
// Incluye métodos especializados (doble dispatch) para cada tipo.
// Esto evita usar if/isInstanceOf y cumple OCP.
trait Hand:
  def playWith(other: Hand): GameResult
  // Métodos especializados de 2do dispatch
  def playWithPaper(other: Paper): GameResult
  def playWithScissors(other: Scissors): GameResult
  def playWithStone(other: Stone): GameResult
  def playWithLizard(other: Lizard): GameResult
