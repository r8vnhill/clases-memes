package cl.uchile.dcc
package nullobject

class RealLogger extends Logger:
  override def log(message: String): Unit =
    println(s"[LOG] $message")
