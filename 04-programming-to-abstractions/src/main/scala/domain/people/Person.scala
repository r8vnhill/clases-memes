package cl.uchile.dcc
package domain.people

class Person(val firstName: String, val lastName: Int):
  println("Initialization begins.")

  val fullName = s"$firstName $lastName"

  def printFullName(): Unit = println(fullName)

  println("Initialization ends.")
