package cl.uchile.dcc
package player

import media.Media

class VerbosePlayer extends BasicPlayer:
  override def beforePlaying(m: Media): Unit =
    println(s"Playing media: ${m.title}")
  override def afterPlaying(m: Media): Unit =
    println(s"Finished playing media: ${m.title}")
  override def beforeStopping(m: Media): Unit =
    println(s"Stopping media: ${m.title}")
  override def afterStopping(m: Media): Unit =
    println(s"Finished stopping media: ${m.title}")
  override def beforeSeeking(m: Media, ms: Int): Unit =
    println(s"Seeking media: ${m.title} to $ms ms")
  override def afterSeeking(m: Media, ms: Int): Unit =
    println(s"Finished seeking media: ${m.title} to $ms ms")
