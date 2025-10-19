package cl.uchile.dcc
package nullobject

object NullLogger extends Logger:
  override def log(message: String): Unit = ()
