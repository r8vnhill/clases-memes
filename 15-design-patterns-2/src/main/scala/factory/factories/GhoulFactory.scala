package cl.uchile.dcc
package factory.factories

import factory.enemies.Ghoul

class GhoulFactory extends EnemyFactory:
  override def make(): Ghoul = new Ghoul
