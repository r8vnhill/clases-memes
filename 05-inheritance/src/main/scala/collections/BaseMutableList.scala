package cl.uchile.dcc
package collections

class BaseMutableList extends MutableList:
  override def add(value: Any): Unit =
    add(getSize, value)

  override def addAll(index: Int, values: Seq[Any]): Unit =
    for value <- values.reverse do add(index, value)

  override def add(index: Int, value: Any): Unit = ???
  override def remove(index: Int): Unit = ???
  // ...
  override def get(index: Int): Option[Any] = ???
  override def contains(value: Any): Boolean = ???
  override def indexOf(value: Any): Int = ???
  override def getSize: Int = ???
  override def lastIndexOf(value: Any): Int = ???
