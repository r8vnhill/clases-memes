package cl.uchile.dcc
package collections

trait List:
  def get(index: Int): Option[Any]
  def contains(value: Any): Boolean
  def indexOf(value: Any): Int
  def getSize: Int
  def lastIndexOf(value: Any): Int
  // ...

trait MutableList extends List:
  def add(index: Int, value: Any): Unit
  def add(value: Any): Unit
  def addAll(index: Int, values: Seq[Any]): Unit
  def remove(index: Int): Unit
  // ...
