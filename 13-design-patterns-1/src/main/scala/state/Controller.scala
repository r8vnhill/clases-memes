package cl.uchile.dcc
package state

import state.exceptions.InvalidTransitionException
import state.states.{GameState, Start}

class Controller:
  private var state: GameState = new Start

  def setState(state: GameState): Unit =
    this.state = state

  def play(): Unit =
    safeRun(() => state.play(this))

  def start(): Unit =
    safeRun(() => state.start(this))

  def end(): Unit =
    safeRun(() => state.end(this))

  private def safeRun(op: () => Unit): Unit =
    try op()
    catch case e: InvalidTransitionException => println(e.getMessage)
