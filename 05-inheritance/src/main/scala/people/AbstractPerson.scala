package cl.uchile.dcc
package people

abstract class AbstractPerson(val name: String,
                              val age: Int,
                              val email: String):
  println("Creating a person...")

  def this(name: String, age: Int) = this(name, age, "")

class Student(name: String,
              age: Int,
              val studentId: String)
    extends AbstractPerson(name, age):

  def this(name: String, age: Int) = this(name, age, "")

  println("Creating a Student...")

class PhDStudent(name: String,
                 age: Int,
                 val researchArea: String)
    extends Student(name, age):
  println("Creating a PhD Student...")
