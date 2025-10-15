package cl.uchile.dcc
package tamagotchi.states

import tamagotchi.Tamagotchi

class Hungry extends TamagotchiState:
  override def feed(context: Tamagotchi): Unit =
    context.state = new Sleepy
