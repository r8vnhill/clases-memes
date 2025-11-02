package cl.uchile.dcc
package transaction.visitor

import transaction.{Purchase, Refund}

/**
 * Visitor que calcula la tarifa (fee) de cada tipo de transacción.
 */
class FeeCalculatorVisitor extends TransactionVisitor:
  private var _totalFee: Double = 0

  def totalFee: Double = _totalFee

  override def visitPurchase(purchase: Purchase): Unit =
    _totalFee += purchase.amount * 0.025

  override def visitRefund(refund: Refund): Unit = _totalFee += 0
