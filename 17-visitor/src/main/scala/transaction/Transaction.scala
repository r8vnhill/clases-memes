package cl.uchile.dcc
package transaction

import transaction.visitor.TransactionVisitor

trait Transaction:
  val amount: Double
  def accept(visitor: TransactionVisitor): Unit
