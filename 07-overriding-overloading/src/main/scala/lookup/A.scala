package cl.uchile.dcc
package lookup

class A:
  def test1: Boolean =
    super.equals(this)

  def self: A =
    this

  def test: Boolean =
    super.getClass == this.getClass

class B extends A:
  def test2: Boolean =
    super.self == this

  def test3: Boolean =
    super.equals(super.self)

@main def testLookup(): Unit =
  println(new B().test1)
  println(new B().test2)
  println(new B().test3)
  println(new B().test)
