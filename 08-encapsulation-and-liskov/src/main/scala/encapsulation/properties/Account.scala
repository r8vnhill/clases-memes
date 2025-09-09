package cl.uchile.dcc
package encapsulation.properties

class Account(private var _balance: Int):
  def balance: Int = _balance            // getter

  def balance_=(value: Int): Unit =      // setter validado
    if value >= 0 then _balance = value

@main def testAccount =
  val account = new Account(100)
  account.balance = 200
  println(account.balance)  // 200
  account.balance = -100
  println(account.balance)  // 200
