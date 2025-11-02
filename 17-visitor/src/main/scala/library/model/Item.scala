package cl.uchile.dcc
package library.model

import library.visitor.ItemVisitor

/** Contrato para un item en la biblioteca. */
trait Item:
  val name: String
  val year: Int

  /** Acepta un visitor para operar sobre el tipo concreto de Item */
  def accept(visitor: ItemVisitor): Unit
