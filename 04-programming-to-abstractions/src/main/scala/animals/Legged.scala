package cl.uchile.dcc
package animals

trait Legged:
  val numLegs: Int // abstract: must be provided by the class

  def walk(): Unit // abstract: must be provided by the class
