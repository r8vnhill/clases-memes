package cl.uchile.dcc
package battle.attack

import battle.armor.Armor

class Fire extends Attack:
  override def vs(armor: Armor): Double =
    armor.defendFromFire(this)
