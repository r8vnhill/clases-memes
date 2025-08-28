package cl.uchile.dcc
package tree

trait Comparable:
  def compareTo(other: Any): Option[Int]
