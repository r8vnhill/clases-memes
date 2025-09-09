package cl.uchile.dcc
package battle.armor

import battle.attack.{Fire, Ice, Poison}

class Steel extends BaseArmor:
  // Sobreescribimos solo los métodos que son distintos de los por defecto
  override def defendFromFire(attack: Fire): Double = 0.5
  override def defendFromPoison(attack: Poison): Double = 0.2
