package cl.uchile.dcc
package transaction

import transaction.visitor.TransactionVisitor

class Refund(override val amount: Double, val reason: String)
    extends Transaction:
  override def accept(visitor: TransactionVisitor): Unit =
    visitor.visitRefund(this)
