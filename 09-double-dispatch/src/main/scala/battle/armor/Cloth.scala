package cl.uchile.dcc
package battle.armor

import battle.attack.{Fire, Ice, Poison}

class Cloth extends BaseArmor:
  override def defendFromFire(attack: Fire): Double = 1.2
  override def defendFromIce(attack: Ice): Double = 0.8
