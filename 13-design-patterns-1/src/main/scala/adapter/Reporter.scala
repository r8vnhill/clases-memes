package cl.uchile.dcc
package adapter

class Reporter(printer: Printer):
  def show(): Unit =
    printer.print("Annual Report")
