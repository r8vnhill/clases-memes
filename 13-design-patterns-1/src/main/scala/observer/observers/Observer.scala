package cl.uchile.dcc
package observer.observers

import observer.subjects.Subject

trait Observer[T]: // o `Listener` o `Subscriber`
  def update(subject: Subject[T], response: T): Unit
