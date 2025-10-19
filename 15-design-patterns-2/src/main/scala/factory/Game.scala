package cl.uchile.dcc
package factory

import cl.uchile.dcc.factory.factories.{EnemyFactory, VampireFactory, ZombieFactory}
import enemies.{Enemy, Ghoul, Vampire, Zombie}

object Game:
  def battle(factory: EnemyFactory): Unit =
    val enemy: Enemy = factory.make()
    println(s"Fighting $enemy")

@main def client(): Unit =
  val vampireFactory = new VampireFactory
  Game.battle(vampireFactory) // hp = 100; armor = 10
  vampireFactory.hp = 150
  Game.battle(vampireFactory) // hp = 150; armor = 10
