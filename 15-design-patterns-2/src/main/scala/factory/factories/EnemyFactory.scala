package cl.uchile.dcc
package factory.factories

import factory.enemies.Enemy

trait EnemyFactory:
  def make(): Enemy
