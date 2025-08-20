package cl.uchile.dcc
package domain.people

import java.time.LocalDate

class Student(val name: String, val id: String, val enrollmentDate: LocalDate):
  // Auxiliary constructor: fills in the current date
  def this(name: String, id: String) =
    this(name, id, LocalDate.now())
    println("[INFO] Enrollment date defaulted to today.")
  // Auxiliary constructor: generates a basic ID based on name
  def this(name: String) =
    this(name, name.take(3).toUpperCase + "-000")
    println(s"[INFO] Generated ID from name.")
