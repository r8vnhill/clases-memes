package cl.uchile.dcc
package template

class TestCase(val name: String, val test: () => Boolean):
  override def toString: String = name
