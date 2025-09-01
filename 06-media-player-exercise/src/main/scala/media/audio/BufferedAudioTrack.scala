package cl.uchile.dcc
package media.audio

import media.AbstractBufferedMedia

class BufferedAudioTrack(title: String, duration: Int)
    extends AbstractBufferedMedia(title, duration)
    with Audio:
  override val description: String = "buffered audio track"

  override def play(): Unit =
    println(s"Playing buffered audio track: $title [$currentPosition/$duration]")
