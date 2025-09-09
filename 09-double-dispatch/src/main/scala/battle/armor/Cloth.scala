package cl.uchile.dcc
package battle.armor

import battle.attack.{Fire, Ice, Poison}

class Cloth extends BaseArmor:
  // Sobreescribimos solo los métodos que son distintos de los por defecto
  override def defendFromFire(attack: Fire): Double = 1.2
  override def defendFromIce(attack: Ice): Double = 0.8
