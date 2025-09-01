package cl.uchile.dcc
package player

import media.Media

abstract class AbstractPlayer extends MultimediaPlayer:
  def beforePlaying(m: Media): Unit
  def afterPlaying(m: Media): Unit
  def beforeStopping(m: Media): Unit
  def afterStopping(m: Media): Unit
  def beforeSeeking(m: Media, ms: Int): Unit
  def afterSeeking(m: Media, ms: Int): Unit

  override def play(m: Media): Unit =
    beforePlaying(m)
    m.play()
    afterPlaying(m)

  override def stop(m: Media): Unit =
    beforeStopping(m)
    m.stop()
    afterStopping(m)

  override def seekTo(m: Media, ms: Int): Unit =
    beforeSeeking(m, ms)
    m.seekTo(ms)
    afterSeeking(m, ms)
