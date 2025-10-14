package cl.uchile.dcc
package state.states
import state.Controller
import state.exceptions.InvalidTransitionException

// El paréntesis vacío es necesario porque `protected :` no es sintaxis válida en Scala 3; desactivamos el warning, ya
// que es un falso positivo.
//noinspection RedundantClassParam
class GameState protected ():
  // Cambio de estado
  def changeState(context: Controller, state: GameState): Unit =
    context.setState(state)
  // Acciones
  def play(context: Controller): Unit = error("play")
  def start(context: Controller): Unit = error("start")
  def end(context: Controller): Unit = error("end")
  private def error(action: String): Nothing =
    throw InvalidTransitionException(action, this.getClass.getSimpleName)
  // ¡SOLO PARA TESTEAR!
  def isStart: Boolean = false
  def isPlayerTurn: Boolean = false
  def isEnemyTurn: Boolean = false
