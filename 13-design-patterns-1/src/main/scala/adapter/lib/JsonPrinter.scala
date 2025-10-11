package cl.uchile.dcc
package adapter.lib

class JsonPrinter:
  def printJson(data: String): Unit =
    println(s"{\"message\": \"$data\"}")
