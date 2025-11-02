package cl.uchile.dcc
package transaction

import visitor.TransactionVisitor

class Purchase(override val amount: Double, val item: String)
    extends Transaction:
  override def accept(visitor: TransactionVisitor): Unit =
    visitor.visitPurchase(this)
