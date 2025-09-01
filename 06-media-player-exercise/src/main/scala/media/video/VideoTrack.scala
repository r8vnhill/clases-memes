package cl.uchile.dcc
package media.video

import media.AbstractMedia

class VideoTrack(override val resolution: Resolution, title: String, duration: Int)
    extends AbstractMedia(title, duration)
    with Video:
  override val description: String = "video track"

  override def play(): Unit =
    println(s"Playing video track: $title at $resolution [$currentPosition/$duration]")
