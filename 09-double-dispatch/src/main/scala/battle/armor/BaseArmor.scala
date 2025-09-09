package cl.uchile.dcc
package battle.armor

import battle.attack.{Fire, Ice, Poison}

class BaseArmor protected extends Armor:
  // Comportamiento por defecto: multiplicador neutro (1.0)
  // Constructor protegido → no se crean instancias directas
  override def defendFromFire(attack: Fire): Double = 1.0
  override def defendFromIce(attack: Ice): Double = 1.0
  override def defendFromPoison(attack: Poison): Double = 1.0
