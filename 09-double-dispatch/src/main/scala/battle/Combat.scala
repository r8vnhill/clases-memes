package cl.uchile.dcc
package battle

import attack.Attack
import armor.Armor

// Calcula el resultado entre un ataque y una armadura.
// Primer dispatch: se envía el mensaje a Attack (attack.vs).
// Attack delega al Armor → segundo dispatch especializado.
// De esta forma, la decisión final depende de ambos tipos dinámicos.
def outcome(attack: Attack, armor: Armor): Double =
  attack.vs(armor)
