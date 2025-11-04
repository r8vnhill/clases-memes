package cl.uchile.dcc
package patternmatching

import scala.util.Random

/** Ejemplo de coincidencia de literales/variables */
@main def pmLiteralsExample(): Unit =
  /** Imprime el nombre de un número aleatorio */
  val x = Random.nextInt(4)
  x match
    case 0 => println("zero")
    case 1 => println("one")
    case 2 => println("two")
    case 3 => println("three")
    case _ => println("other")
