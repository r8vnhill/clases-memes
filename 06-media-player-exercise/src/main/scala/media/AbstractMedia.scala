package cl.uchile.dcc
package media

abstract class AbstractMedia(override val title: String, override val duration: Int) extends Media:
  var currentPosition: Int = 0
  val description: String // abstract

  override def stop(): Unit =
    println(s"Stopped $description: $title")

  override def seekTo(ms: Int): Unit =
    currentPosition = Utility.clamp(ms, 0, duration)
