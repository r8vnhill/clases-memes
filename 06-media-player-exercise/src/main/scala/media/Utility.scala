//noinspection NoTailRecursionAnnotation
package cl.uchile.dcc
package media

object Utility:

  def gcd(a: Int, b: Int): Int =
    if b == 0 then a else gcd(b, a % b)

  def clamp(value: Int, min: Int, max: Int): Int =
    Math.clamp(value, min, max)
