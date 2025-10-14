package cl.uchile.dcc
package observer.subjects

import observer.observers.Observer

import scala.collection.mutable

class BaseSubject[T] protected extends Subject[T]:
  private val observers: mutable.Set[Observer[T]] =
    mutable.LinkedHashSet.empty[Observer[T]]
  override def attach(observer: Observer[T]): Unit =
    observers += observer
  override def detach(observer: Observer[T]): Unit =
    observers -= observer
  override def notifyObservers(notification: T): Unit =
    for observer <- observers do observer.update(this, notification)
