package cl.uchile.dcc
package flyweight

import scala.collection.mutable

class GlyphFlyweight(private val font: String, private val size: Int):
  private val cache = mutable.Map.empty[Char, Glyph]

  def getOrCreate(char: Char): Glyph =
    cache.getOrElseUpdate(char, new Glyph(char, font, size))

  def length: Int = cache.size
