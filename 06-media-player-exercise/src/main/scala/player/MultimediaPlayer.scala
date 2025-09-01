package cl.uchile.dcc
package player

import media.Media

trait MultimediaPlayer:
  def play(m: Media): Unit
  def stop(m: Media): Unit
  def seekTo(m: Media, ms: Int): Unit
