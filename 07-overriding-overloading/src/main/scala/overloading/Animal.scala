//noinspection TypeCheckCanBeMatch
package cl.uchile.dcc
package overloading

class Animal(val name: String):
  def greet(a: Animal): Unit =
    println(s"Good morning, ${a.name}")

  /**
   * IMPORTANTE: Usamos getClass para acceder al nombre de la clase y mostrarlo getClass NO DEBE
   * USARSE para comparar objetos, úsalo **solamente** para propósitos de "display", nunca para
   * inspeccionar
   *
   * <h1>Ejemplos de mal uso</h1>
   * {{{
   *   if a.getClass == b.getClass then ...
   *   if a.toString == "SomeClassName" then ...
   *   if a.getClass.getSimpleName == "SomeClassName" then ...
   *   ...
   * }}}
   */
  override def toString: String =
    s"${getClass.getSimpleName}($name)"

class Cat(name: String) extends Animal(name):
  def greet(a: Cat): Unit =
    println("Hello fellow cat")

  override def equals(obj: Any): Boolean =
    if getClass == obj.getClass then  // <-- NO HACER FUERA DE EQUALS
      val other = obj.asInstanceOf[Cat]
      name == other.name
    else false

class Dog(name: String) extends Animal(name)

class Persian(name: String) extends Cat(name)

@main def main(): Unit =
  val cat = new Cat("Snowbell")
  val persian = new Persian("Snowbell")
  println(persian.equals(cat))
