package cl.uchile.dcc
package battle.armor

import battle.attack.{Fire, Ice, Poison}

trait Armor:
  def defendFromFire(attack: Fire): Double
  def defendFromIce(attack: Ice): Double
  def defendFromPoison(attack: Poison): Double
