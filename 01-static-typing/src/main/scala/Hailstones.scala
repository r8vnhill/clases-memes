/*
 * Este archivo presenta dos implementaciones de la misma transformación: una basada en estado mutable y otra basada en 
 * recursión. El objetivo es comparar ambas formas de expresar un proceso iterativo en Scala.
 *
 * La secuencia de Hailstone, asociada al problema 3x + 1, se construye aplicando repetidamente estas reglas a un 
 * entero positivo:
 *
 *   - si n es par, el siguiente valor es n / 2;
 *   - si n es impar, el siguiente valor es 3 * n + 1.
 *
 * Por ejemplo, comenzando en 3:
 *
 *   3 → 10 → 5 → 16 → 8 → 4 → 2 → 1
 *
 * La conjetura de Collatz afirma que este proceso alcanza 1 para cualquier entero positivo, pero esta propiedad no ha 
 * sido demostrada en general (Lagarias, 1985). En este módulo usamos únicamente valores pequeños cuyo comportamiento 
 * podemos observar directamente.
 *
 * Referencia:
 * Lagarias, J. C. (1985). The 3x + 1 problem and its generalizations. The American Mathematical Monthly, 92(1), 3–23.
 * https://doi.org/10.1080/00029890.1985.11971528
 */
package cl.uchile.dcc

/**
 * Recorre una secuencia de Hailstone usando estado mutable.
 *
 * `r` representa el valor actual de la secuencia. En cada iteración del `while`, se imprime ese valor y luego se 
 * reemplaza por el siguiente. Esto permite observar una solución imperativa en la que el progreso del cálculo se 
 * mantiene mediante una variable mutable.
 *
 * Esta función supone, con fines didácticos, que `n` es positivo y que la secuencia alcanza `1` sin que las 
 * operaciones excedan el rango de `Int`. La implementación no comprueba estas condiciones.
 *
 * @param n
 *   valor inicial de la secuencia
 * @return
 *   `1` si el recorrido alcanza ese valor
 */
def itHailstone(n: Int): Int =
  var r: Int = n
  while r != 1 do
    println(r)
    if r % 2 == 0 then r = r / 2
    else r = 3 * r + 1
  println(r)
  r

/**
 * Recorre una secuencia de Hailstone usando recursión.
 *
 * En lugar de mantener el valor actual en una variable mutable, cada llamada recibe un término de la secuencia como 
 * argumento:
 *
 *   - `n == 1` es el caso base;
 *   - en cualquier otro caso se calcula el siguiente término y se continúa mediante una nueva llamada a `recHailstone`.
 *
 * La impresión de cada término permite comparar directamente este recorrido con el producido por [[itHailstone]].
 *
 * Esta función supone, con fines didácticos, que `n` es positivo y que la secuencia alcanza `1` sin que las 
 * operaciones excedan el rango de `Int`. La implementación no comprueba estas condiciones.
 *
 * @param n
 *   término actual de la secuencia
 * @return
 *   `1` si el recorrido alcanza el caso base
 */
//noinspection NoTailRecursionAnnotation
def recHailstone(n: Int): Int =
  if n == 1 then
    println(n)
    n
  else
    println(n)
    if n % 2 == 0 then recHailstone(n / 2)
    else recHailstone(3 * n + 1)

/**
 * Ejecuta ambas implementaciones sobre el mismo valor inicial.
 *
 * El ejemplo comienza en `3`, cuya secuencia es:
 *
 * `3 → 10 → 5 → 16 → 8 → 4 → 2 → 1`
 *
 * Primero se ejecuta la versión con estado mutable y luego la versión recursiva, de modo que sus recorridos puedan 
 * compararse directamente.
 *
 * Ambas funciones retornan un `Int`, pero en este ejemplo solo nos interesa observar los valores que imprimen. Como 
 * `hailstones` retorna `Unit`, el resultado de la última llamada no se utiliza.
 *
 * La anotación `: Unit` al final de `recHailstone(3)` hace explícito que queremos descartar ese valor. Sin ella, Scala 
 * puede advertir que se está descartando implícitamente un valor de tipo `Int`:
 *
 * {{{
 * discarded non-Unit value of type Int. Add `: Unit` to discard silently.
 * }}}
 *
 * En otras palabras, `recHailstone(3): Unit` no cambia la ejecución de `recHailstone`: solo indica explícitamente que 
 * ignoramos su valor de retorno.
 */
@main def hailstones(): Unit =
  itHailstone(3)
  println("----")
  recHailstone(3): Unit
