package cl.uchile.dcc
package observer.subjects

class ConsoleSubject extends BaseSubject[String]:
  def run(): Unit =
    while true do
      val response = scala.io.StdIn.readLine()
      notifyObservers(response)
