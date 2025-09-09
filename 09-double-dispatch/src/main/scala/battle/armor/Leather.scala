package cl.uchile.dcc
package battle.armor

import battle.attack.{Fire, Ice, Poison}

class Leather extends BaseArmor:
  // Sobreescribimos solo los métodos que son distintos de los por defecto
  override def defendFromIce(attack: Ice): Double = 1.1
  override def defendFromPoison(attack: Poison): Double = 1.3
