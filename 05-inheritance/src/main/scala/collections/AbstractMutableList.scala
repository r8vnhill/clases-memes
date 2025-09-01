package cl.uchile.dcc
package collections

abstract class AbstractMutableList extends MutableList:
  override def add(index: Int, value: Any): Unit
  override def get(index: Int): Option[Any]

  override def add(value: Any): Unit =
    add(getSize, value)

  override def addAll(index: Int, values: Seq[Any]): Unit =
    for value <- values.reverse do add(index, value)

  // ...
