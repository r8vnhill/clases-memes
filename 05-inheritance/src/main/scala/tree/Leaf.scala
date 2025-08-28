package cl.uchile.dcc
package tree

class Leaf(val value: Int) extends AbstractTree:
  override def sum: Int = value
  override def min: Int = value
  override def max: Int = value
