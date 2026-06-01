package cl.uchile.dcc
package polymorphism

class Pair[L, R](val left: L, val right: R):
  def swap(): Pair[R, L] = new Pair(right, left)

trait LookupResult[T]:
  def isFound: Boolean
  def get: T
  def getOrElse(default: T): T

class Found[T](val value: T) extends LookupResult[T]:
  override def isFound: Boolean = true
  override def get: T = value
  override def getOrElse(default: T): T = value

class Missing[T] extends LookupResult[T]:
  override def isFound: Boolean = false
  override def get: T = throw new NoSuchElementException("Missing.get")
  override def getOrElse(default: T): T = default

trait ComparableTo[T]:
  def compareTo(other: T): Int

class Person(val name: String, var age: Int) extends ComparableTo[Person]:
  override def compareTo(other: Person): Int =
    if age < other.age then -1
    else if age > other.age then 1
    else 0

trait Sorter[T <: ComparableTo[T]]:
  def sort(values: List[T]): List[T]
