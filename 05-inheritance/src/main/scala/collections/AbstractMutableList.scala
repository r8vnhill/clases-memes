package cl.uchile.dcc
package collections

abstract class AbstractMutableList extends MutableList:
  override def add(index: Int, value: Any): Unit
  override def get(index: Int): Option[Any]
