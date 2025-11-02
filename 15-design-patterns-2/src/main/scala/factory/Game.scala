package cl.uchile.dcc
package factory

object Game:
  def battle(factory: factories.EnemyFactory): Unit =
    val enemy = factory.make()
    println(s"Fighting $enemy")

@main def factoryExample(): Unit =
  val vampireFactory = new factories.VampireFactory
  Game.battle(vampireFactory) // hp = 100; armor = 10
  vampireFactory.hp = 150
  Game.battle(vampireFactory) // hp = 150; armor = 10
