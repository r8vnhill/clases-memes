package cl.uchile.dcc
package battle.attack

import battle.armor.Armor

trait Attack:
  def vs(armor: Armor): Double
