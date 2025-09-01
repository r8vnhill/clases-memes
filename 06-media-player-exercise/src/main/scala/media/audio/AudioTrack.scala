//noinspection ScalaWeakerAccess
package cl.uchile.dcc
package media.audio

import media.AbstractMedia

class AudioTrack(title: String, duration: Int)
    extends AbstractMedia(title, duration)
    with Audio:
  override val description: String = "audio track"

  override def play(): Unit =
    println(s"Playing audio track: $title [$currentPosition/$duration]")
