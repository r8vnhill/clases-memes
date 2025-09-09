package cl.uchile.dcc
package battle.armor

import battle.attack.{Fire, Ice, Poison}

trait Armor:
  // Métodos especializados de 2do dispatch
  // Cada subtipo de Armor define su multiplicador frente a un ataque específico
  def defendFromFire(attack: Fire): Double
  def defendFromIce(attack: Ice): Double
  def defendFromPoison(attack: Poison): Double
