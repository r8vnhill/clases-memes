package cl.uchile.dcc
package lookup

class Animal:
  def talk(s: String): String =
    s

  def isSameAnimal(a: Animal): Boolean =
    this.talk(s"Comparing $this with $a")
    compare(this, a)

  def compare(a: Animal, b: Animal): Boolean =
    a.getClass == b.getClass

class Cat extends Animal:
  override def talk(s: String): String =
    super.talk(s) + " meow"

class Persian extends Cat:
  override def talk(s: String): String =
    "purr " + super.talk(s)

  def compareToSelf: Boolean =
    this.isSameAnimal(this)
