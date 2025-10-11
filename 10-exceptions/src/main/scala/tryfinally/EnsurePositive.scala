package cl.uchile.dcc
package tryfinally

def ensurePositive(x: Int): Int =
  try
    if x > 0 then x
    else throw new IllegalArgumentException(x.toString)
  finally println("siempre se imprime")

@main def main(): Unit =
  println(ensurePositive(1))
  println(ensurePositive(-1))
