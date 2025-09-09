package cl.uchile.dcc
package battle.armor

import battle.attack.{Fire, Ice, Poison}

class BaseArmor protected extends Armor:
  override def defendFromFire(attack: Fire): Double = 1.0
  override def defendFromIce(attack: Ice): Double = 1.0
  override def defendFromPoison(attack: Poison): Double = 1.0
