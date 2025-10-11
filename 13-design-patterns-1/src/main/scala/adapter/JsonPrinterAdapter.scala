package cl.uchile.dcc
package adapter

import adapter.lib.JsonPrinter

class JsonPrinterAdapter extends Printer:
  private val adaptee = new JsonPrinter

  override def print(message: String): Unit =
    adaptee.printJson(message)

@main def jsonPrinterAdapterMain(): Unit =
  val printer: Printer = new JsonPrinterAdapter
  val reporter = new Reporter(printer)
  reporter.show()
