package cl.uchile.dcc
package tree

abstract class AbstractTree extends Tree:
  override def compareTo(other: Any): Option[Int] =
    if other.isInstanceOf[Tree] then
      val tree = other.asInstanceOf[Tree]
      if sum < tree.sum then Some(-1)
      else if sum > tree.sum then Some(1)
      else Some(0)
    else None
