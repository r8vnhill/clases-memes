package cl.uchile.dcc
package tamagotchi.states

import state.exceptions.InvalidTransitionException
import tamagotchi.Tamagotchi

class TamagotchiState protected ():
  def feed(context: Tamagotchi): Unit =
    error("feed", context)

  def play(context: Tamagotchi): Unit =
    error("play", context)

  def sleep(context: Tamagotchi): Unit =
    error("sleep", context)

  private def error(action: String, context: Tamagotchi): Nothing =
    throw new InvalidTransitionException(action,
                                         context.state.getClass.getSimpleName
    )
