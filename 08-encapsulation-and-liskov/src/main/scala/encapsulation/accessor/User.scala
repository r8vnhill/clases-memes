package cl.uchile.dcc
package encapsulation.accessor

class User(private var name: String, private var age: Int):
  // getters
  def getName: String = name
  def getAge: Int = age

  // setters (con validación)
  def setName(n: String): Unit =
    if n.nonEmpty then name = n

  def setAge(a: Int): Unit =
    if a >= 0 then age = a
