package cl.uchile.dcc
package trycatch

def readPort(env: Map[String, String]): Option[Int] =
  try Some(env.getOrElse("PORT", "8080").toInt)
  catch
    case e: NumberFormatException =>
      println("Error: PORT must be an integer")
      println(s"Cause: ${e.getClass} - ${e.getMessage}")
      None

@main def main(): Unit =
  val env = Map("PORT" -> "notAnInt")
  println(readPort(env))
