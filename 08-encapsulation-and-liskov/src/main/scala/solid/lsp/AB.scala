package cl.uchile.dcc
package solid.lsp

class A:
  private def foo(): Unit =
    println("A")

class B extends A:
  protected def foo(): Unit =
    println("B")

class C extends B:
  override def foo(): Unit =
    println("C")

def foo(a: A): Unit = ???

@main def testLsp(): Unit =
  foo(new A)
  foo(new B)
