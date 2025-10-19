package cl.uchile.dcc
package factory.factories

import factory.enemies.Vampire

class VampireFactory extends EnemyFactory:
  var hp: Int = 100
  var armor: Int = 10

  override def make(): Vampire = new Vampire(hp, armor)
