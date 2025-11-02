package cl.uchile.dcc
package tamagotchi

import state.exceptions.InvalidTransitionException
import tamagotchi.events.{InvalidTransition, ValidTransition}
import tamagotchi.publishers.{
  InvalidTransitionPublisher,
  ValidTransitionPublisher
}
import tamagotchi.states.{Happy, TamagotchiState}

class Tamagotchi:
  private var _state: TamagotchiState = new Happy
  val validTransitionPublisher = new ValidTransitionPublisher
  val invalidTransitionPublisher = new InvalidTransitionPublisher

  def state: TamagotchiState =
    _state

  def state_=(state: TamagotchiState): Unit =
    _state = state

  def sleep(): Unit =
    performAction(_state.sleep)

  def play(): Unit =
    performAction(_state.play)

  def feed(): Unit =
    performAction(_state.feed)

  private def performAction(action: Tamagotchi => Unit): Unit =
    try
      action(this)
      validTransitionPublisher.notifyObservers(new ValidTransition)
    catch
      case e: InvalidTransitionException =>
        invalidTransitionPublisher.notifyObservers(new InvalidTransition)
