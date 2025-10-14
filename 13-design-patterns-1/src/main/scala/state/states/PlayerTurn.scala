package cl.uchile.dcc
package state.states
import state.Controller

class PlayerTurn extends GameState:
  override def play(context: Controller): Unit =
    context.setState(new EnemyTurn)
  override def end(context: Controller): Unit =
    context.setState(new Start)
  override def isPlayerTurn: Boolean = true