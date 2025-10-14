package cl.uchile.dcc
package state.states
import state.Controller

class EnemyTurn extends GameState:
  override def play(context: Controller): Unit =
    context.setState(new PlayerTurn)
  override def end(context: Controller): Unit =
    context.setState(new Start)
  override def isEnemyTurn: Boolean = true
