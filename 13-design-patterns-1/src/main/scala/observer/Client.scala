package cl.uchile.dcc
package observer

import observer.observers.ConsoleObserver
import observer.signals.ExitSignal
import observer.subjects.ConsoleSubject

@main def run(): Unit =
  val subject = new ConsoleSubject
  val handler = new ConsoleObserver
  subject.attach(handler)
  try subject.run()
  catch
    case _: ExitSignal =>
      println("See you space cowboy...")
