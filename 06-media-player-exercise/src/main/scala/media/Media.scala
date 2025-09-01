package cl.uchile.dcc
package media

trait Media:
  val title: String
  val duration: Int // in milliseconds
  def play(): Unit
  def stop(): Unit
  def seekTo(ms: Int): Unit
