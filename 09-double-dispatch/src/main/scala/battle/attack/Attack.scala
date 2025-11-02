package cl.uchile.dcc
package battle.attack

import battle.armor.Armor

// Jerarquía de ataques.
// Primer dispatch: Attack recibe el mensaje vs(armor).
// Luego delega al Armor correspondiente para el segundo dispatch.
trait Attack:
  def vs(armor: Armor): Double
