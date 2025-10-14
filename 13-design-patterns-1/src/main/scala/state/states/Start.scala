package cl.uchile.dcc
package state.states
import state.Controller

class Start extends GameState:
  override def play(context: Controller): Unit = ()
  override def start(context: Controller): Unit =
    context.setState(new PlayerTurn)
  override def isStart: Boolean = true
