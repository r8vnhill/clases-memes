package cl.uchile.dcc

/**
 * Compara dos enteros e imprime un mensaje cada vez que se ejecuta.
 *
 * Este ejemplo permite observar la firma de una función en Scala:
 *
 * {{{
 * equals: (Int, Int) => Boolean
 * }}}
 *
 * La función recibe dos valores de tipo `Int` y produce un valor de tipo `Boolean`. En Scala, cuando no se utiliza 
 * `return`, el valor de la última expresión del cuerpo es el resultado de la función. En este caso, esa expresión es 
 * `a == b`.
 *
 * La llamada a `println` produce además un efecto observable: cada vez que se evalúa `equals`, se imprime
 * `"equals called"`. Este efecto es independiente del valor `Boolean` que retorna la función.
 *
 * @param a
 *   primer entero que se comparará
 * @param b
 *   segundo entero que se comparará
 * @return
 *   `true` si `a` y `b` representan el mismo valor; `false` en caso contrario
 * @example
 *   {{{
 *   equals(3, 3) // imprime "equals called" y retorna true
 *   equals(2, 5) // imprime "equals called" y retorna false
 *   }}}
 */
def equals(
    a: Int,
    b: Int
): Boolean =
  println("equals called")
  a == b
