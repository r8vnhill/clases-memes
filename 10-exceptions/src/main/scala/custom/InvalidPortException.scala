package cl.uchile.dcc
package custom

class InvalidPortException(message: String, cause: Throwable = null)
  extends Exception(message, cause)

def validatePort(port: Int): Unit =
  if port < 1024 || port > 65535 then
    throw new InvalidPortException(s"Puerto fuera de rango: $port")
