package cl.uchile.dcc
package battle.attack

import battle.armor.Armor

// Ataque de tipo Fuego.
// Implementa el primer dispatch y delega en Armor (segundo dispatch).
class Fire extends Attack:
  override def vs(armor: Armor): Double =
    armor.defendFromFire(this)
