package cl.uchile.dcc
package factory.factories

import factory.enemies.Zombie

class ZombieFactory extends EnemyFactory:
  override def make(): Zombie = new Zombie
