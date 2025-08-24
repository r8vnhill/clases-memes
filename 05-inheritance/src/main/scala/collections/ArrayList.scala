package cl.uchile.dcc
package collections

class ArrayList(capacity: Int = 10) extends BaseMutableList:
  var size: Int = 0
  var data: Array[Any] = new Array[Any](capacity)

  override def add(index: Int, value: Any): Unit =
    if index < 0 || index > size then
      println("Index out of bounds")
      return
    if size == data.length then
      val newData = new Array[Any](data.length * 2)
      for i <- 0 until size do newData(i) = data(i)
      data = newData
    for i <- size - 1 to index by -1 do data(i + 1) = data(i)
    data(index) = value
    size += 1

  override def get(index: Int): Option[Any] =
    if index < 0 || index >= size then None
    else Some(data(index))

  override def contains(value: Any): Boolean = ???
  override def indexOf(value: Any): Int = ???
  override def remove(index: Int): Unit = ???
  override def getSize: Int = ???
  override def lastIndexOf(value: Any): Int = ???
