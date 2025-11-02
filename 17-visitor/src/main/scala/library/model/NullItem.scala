package cl.uchile.dcc
package library.model

import library.visitor.ItemVisitor

/**
 * Null Object para `Item`.
 *
 * Se usa cuando un visitor necesita un `Item` para iniciar el recorrido pero
 * no existe uno lógico que proporcionar (evita crear dummies cada vez).
 */
object NullItem extends AbstractItem("", 0):
  /** No hace nada — el visitor de `Item` puede ignorarlo. */
  def accept(visitor: ItemVisitor): Unit = ()
