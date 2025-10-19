package cl.uchile.dcc
package factory.enemies

class Vampire(val hp: Int, val armor: Int) extends Enemy:
  override def toString: String =
    s"Vampire(hp=$hp, armor=$armor)"
