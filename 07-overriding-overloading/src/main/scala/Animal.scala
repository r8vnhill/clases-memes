package cl.uchile.dcc

trait Animal:
  def getName: String
  def greet(a: Animal): String
  def talk(s: String): String

abstract class AbstractAnimal(val name: String) extends Animal:
  override def greet(a: Animal): String = s"Hola ${a.getName}, soy $name"
  override def talk(s: String): String = s"$name dice: $s"

class Cat(name: String) extends AbstractAnimal(name):
  override def getName: String = name
  override def talk(s: String): String = s"$name maúlla: $s"

@main def main(): Unit =
  val c1: Cat = new Cat("Sakamoto")
  println(c1.talk("Mi plato está vacío"))
  val c2: Animal = new Cat("Jiji")
  println(c2.talk("Estoy hambriento"))
