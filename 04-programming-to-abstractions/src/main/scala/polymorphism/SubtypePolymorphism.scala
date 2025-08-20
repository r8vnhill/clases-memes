package cl.uchile.dcc
package polymorphism

class Duck:
  def m1(): Unit = ???
  def m2(): Int = ???
  def m3(): String = ???

class Witch:
  def m1(): Unit = ???
  def m2(): Int = ???
  def m3(): String = ???

def foo(d: Duck): Unit =
  d.m1()
