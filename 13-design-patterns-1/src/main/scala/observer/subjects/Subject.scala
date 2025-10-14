package cl.uchile.dcc
package observer.subjects

import cl.uchile.dcc.observer.observers.Observer

trait Subject[T]: // o `Publisher` o `Observable`
  def attach(observer: Observer[T]): Unit
  def detach(observer: Observer[T]): Unit
  def notifyObservers(notification: T): Unit
