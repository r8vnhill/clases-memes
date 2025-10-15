package cl.uchile.dcc
package tamagotchi.states

import tamagotchi.Tamagotchi

class Sleepy extends TamagotchiState:
  override def sleep(context: Tamagotchi): Unit =
    context.state = new Happy
