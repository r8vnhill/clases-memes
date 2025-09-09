package cl.uchile.dcc
package encapsulation.accessor

import scala.collection.mutable

class Order:
  private val items = mutable.ListBuffer[String]()

  def getItems: List[String] =
    // Evita filtrar la estructura mutable al exterior
    items.toList

  def addItem(i: String): Unit =
    if i.nonEmpty then items += i
