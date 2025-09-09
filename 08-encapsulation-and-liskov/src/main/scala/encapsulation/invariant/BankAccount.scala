package cl.uchile.dcc
package encapsulation.invariant

class BankAccount(private var balance: Int):
  def deposit(amount: Int): Unit =
    if amount > 0 then balance += amount

  def withdraw(amount: Int): Unit =
    if amount > 0 && amount <= balance then balance -= amount

  def getBalance: Int = balance
