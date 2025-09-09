package cl.uchile.dcc
package battle.attack

import battle.armor.Armor

class Ice extends Attack:
  override def vs(armor: Armor): Double =
    armor.defendFromIce(this)
