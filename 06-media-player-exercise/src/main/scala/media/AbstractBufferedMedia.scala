//noinspection ScalaWeakerAccess
package cl.uchile.dcc
package media

abstract class AbstractBufferedMedia(title: String, duration: Int)
    extends AbstractMedia(title, duration)
    with BufferedMedia:
  var buffered: Int = 0

  override def seekTo(ms: Int): Unit =
    currentPosition = Utility.clamp(ms, 0, currentPosition + buffered)
