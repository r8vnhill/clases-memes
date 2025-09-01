//noinspection ScalaWeakerAccess
package cl.uchile.dcc
package media.video

import media.Utility

class Resolution(val width: Int,
                 val height: Int,
                 val refreshRate: Int = 60):
  def aspectRatio: String =
    val g = Utility.gcd(width, height)
    s"${width / g}:${height / g}"

  override def toString: String =
    s"${width}x$height@$refreshRate"
