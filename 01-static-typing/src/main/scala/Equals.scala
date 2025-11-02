package cl.uchile.dcc

/**
 * Compares two integers for equality.
 *
 * This function checks if the two integer values `a` and `b` are equal. Before
 * performing the comparison, it prints "equals called" to the console.
 *
 * @param a
 *   the first integer to compare
 * @param b
 *   the second integer to compare
 * @return
 *   true if `a` and `b` are equal, false otherwise
 * @example
 *   {{{
 *   equals(3, 3)   // prints "equals called" and returns true
 *   equals(2, 5)   // prints "equals called" and returns false
 *   }}}
 */
def equals(a: Int, b: Int): Boolean =
  println("equals called")
  a == b
