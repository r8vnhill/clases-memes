package cl.uchile.dcc
package battle.attack

import battle.armor.Armor

// Ataque de tipo Hielo.
// Cada subtipo define cómo delega en Armor para resolver el resultado.
class Ice extends Attack:
  override def vs(armor: Armor): Double =
    armor.defendFromIce(this)
