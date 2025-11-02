package cl.uchile.dcc
package transaction.visitor

import transaction.{Purchase, Refund}

/**
 * Visitor que determina si una transacción requiere auditoría.
 */
class AuditVisitor extends TransactionVisitor:
  private var _requiresAudit: Boolean = false

  def requiresAudit: Boolean = _requiresAudit

  override def visitPurchase(purchase: Purchase): Unit = _requiresAudit =
    purchase.amount > 5000

  override def visitRefund(refund: Refund): Unit = _requiresAudit =
    refund.amount > 2000
