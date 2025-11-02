package cl.uchile.dcc
package transaction

import transaction.visitor.{AuditVisitor, FeeCalculatorVisitor}

@main def visitorExample(): Unit =
  val transactions: List[Transaction] = List(
      new Purchase(1500.0, "Notebook"),
      new Purchase(250.0, "Mouse"),
      new Refund(3000.0, "Producto defectuoso"),
      new Purchase(6000.0, "Monitor 4K"),
      new Refund(500.0, "Talla incorrecta")
  )
  val feeVisitor = new FeeCalculatorVisitor
  for (t <- transactions) do t.accept(feeVisitor)
  println(s"Fees total: ${feeVisitor.totalFee}")

  for (t <- transactions) do
    val auditVisitor = new AuditVisitor
    t.accept(auditVisitor)
    val status = if auditVisitor.requiresAudit then "YES" else "NO"
    println(t.toString)
    println(s"Amount: ${t.amount} Audit: $status")
