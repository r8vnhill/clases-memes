package cl.uchile.dcc
package observer.observers

import observer.signals.ExitSignal
import observer.subjects.Subject

class ConsoleObserver extends Observer[String]:
  override def update(subject: Subject[String], response: String): Unit =
    if response.equalsIgnoreCase("exit") then throw new ExitSignal
    else println(s"Received: $response")
