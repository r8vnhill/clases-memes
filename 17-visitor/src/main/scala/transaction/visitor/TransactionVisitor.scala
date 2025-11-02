package cl.uchile.dcc
package transaction.visitor

import transaction.{Purchase, Refund}

trait TransactionVisitor:
  def visitPurchase(purchase: Purchase): Unit
  def visitRefund(refund: Refund): Unit
