package cl.uchile.dcc
package tree

class InternalNode(val value: Int,
                   val left: Tree,
                   val right: Tree) extends Tree:
  override def sum: Int = value + left.sum + right.sum
  override def min: Int = math.min(value, math.min(left.min, right.min))
  override def max: Int = math.max(value, math.max(left.max, right.max))
