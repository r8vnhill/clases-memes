package cl.uchile.dcc
package battle.attack

import battle.armor.Armor

class Poison extends Attack:
  override def vs(armor: Armor): Double =
    armor.defendFromPoison(this)
