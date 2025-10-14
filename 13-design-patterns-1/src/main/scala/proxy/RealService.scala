package cl.uchile.dcc
package proxy

class RealService extends Service:
  override def request(): Unit =
    println("Ejecutando la operación real.")
