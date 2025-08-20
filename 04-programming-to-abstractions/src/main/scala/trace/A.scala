package cl.uchile.dcc
package trace

class A(val x: Int):
  print("a")

  def this(x: String) =
    this(x.toInt)
    print("b")

  def this() =
    this("0")
    print("c")

  print("d")

@main def main(): Unit =
  new A(): Unit
