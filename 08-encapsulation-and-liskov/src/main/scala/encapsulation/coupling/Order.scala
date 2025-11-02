package cl.uchile.dcc
package encapsulation.coupling

import scala.collection.mutable

class Order:
  private val items = mutable.ListBuffer[String]()

  def addItem(item: String): Unit = items += item

@main def orderMain(): Unit =
  val order = new Order
  // order.items += "Pizza"   <-- High coupling
  order.addItem("Pizza") //  <-- Low coupling
