package cl.uchile.dcc
package battle.armor

import battle.attack.{Fire, Ice, Poison}

class Steel extends BaseArmor:
  override def defendFromFire(attack: Fire): Double = 0.5
  override def defendFromPoison(attack: Poison): Double = 0.2
