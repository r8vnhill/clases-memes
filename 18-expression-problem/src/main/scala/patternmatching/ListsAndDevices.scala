package cl.uchile.dcc
package patternmatching

/** Ejemplos adicionales de pattern matching: listas. */

/** Suma recursiva de una lista usando patrones */
def sumOfInts(list: List[Int]): Int =
  list match
    case Nil     => 0
    case x :: xs => x + sumOfInts(xs)

@main def pmSumOfInts(): Unit =
  val numbers = List(1, 2, 3, 4, 5)
  println(sumOfInts(numbers)) // 15
