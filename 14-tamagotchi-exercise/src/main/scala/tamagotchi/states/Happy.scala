package cl.uchile.dcc
package tamagotchi.states

import tamagotchi.Tamagotchi

class Happy extends TamagotchiState:
  override def play(context: Tamagotchi): Unit =
    context.state = new Hungry
