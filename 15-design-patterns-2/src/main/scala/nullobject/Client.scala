package cl.uchile.dcc
package nullobject

class Client(logger: Logger = NullLogger):
  def foo(): Unit = logger.log("foo")
  def bar(): Unit = logger.log("bar")
  def baz(): Unit = logger.log("baz")

@main def nullObjectExample(): Unit =
  println("Null logger:")
  new Client().foo()
  println("Simple logger:")
  new Client(new RealLogger).foo()
