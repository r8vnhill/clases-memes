package cl.uchile.dcc
package collections

class LinkedList extends BaseMutableList:
  var size: Int = 0
  var first: Option[Node] = None
  var last: Option[Node] = None

  override def add(index: Int, value: Any): Unit =
    if index < 0 || index > size then
      println("Index out of bounds")
      return
    val newNode = new Node(value)
    val after = getNode(index)
    if after.isDefined then
      newNode.next = after
      newNode.prev = after.get.prev
      if after.get.prev.isEmpty then first = Some(newNode)
      else after.get.prev.get.next = Some(newNode)
      after.get.prev = Some(newNode)
      size += 1
    else ???

  override def get(index: Int): Option[Any] =
    val node = getNode(index)
    if node.isDefined then Some(node.get.value)
    else None

  override def contains(value: Any): Boolean = ???

  override def indexOf(value: Any): Int = ???

  override def remove(index: Int): Unit = ???

  override def getSize: Int = ???

  override def lastIndexOf(value: Any): Int = ???

  def getNode(index: Int): Option[Node] = ???

class Node(val value: Any):
  var next: Option[Node] = None
  var prev: Option[Node] = None
