package cl.uchile.dcc

class A extends Exception
class B extends Exception
class C extends Exception

def baz(): Nothing = throw new C

def bar(): Nothing =
  try baz()
  catch case a: A => ???

def foo(): Nothing =
  try bar()
  catch case b: B => ???

@main def callStackExample(): Unit =
  foo()
