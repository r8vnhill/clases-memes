package cl.uchile.dcc
package battle.attack

import battle.armor.Armor

// Ataque de tipo Veneno.
// Aplica el mismo esquema de double dispatch que Fire e Ice.
class Poison extends Attack:
  override def vs(armor: Armor): Double =
    armor.defendFromPoison(this)
