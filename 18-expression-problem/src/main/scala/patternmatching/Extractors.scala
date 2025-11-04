package cl.uchile.dcc
package patternmatching

import java.util.UUID

/** Ejemplo de objeto extractor (apply/unapply) */
object CustomerId:
  def apply(name: String): String = s"$name--${UUID.randomUUID}"
  def unapply(customerId: String): Option[(String, String)] =
    val parts = customerId.split("--")
    if parts.length == 2 then Some((parts(0), parts(1))) else None

@main def pmExtractorExample(): Unit =
  val customerId = CustomerId("Dusty Springfield")
  customerId match
    case CustomerId(name, uuid) => println(s"Name: $name, UUID: $uuid")
    case _                      => println("Could not extract a name")
