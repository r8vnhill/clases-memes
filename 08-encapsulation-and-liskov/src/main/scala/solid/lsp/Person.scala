package cl.uchile.dcc
package solid.lsp

class Person(val name: String)
class Student(name: String, val specialty: String) extends Person(name)
class PhDStudent(name: String, specialty: String)
    extends Student(name, specialty)
