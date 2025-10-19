package cl.uchile.dcc
package flyweight

import scala.collection.mutable.ListBuffer
import scala.io.Source

@main def textEditor(): Unit =
  val path = "15-design-patterns-2/src/main/resources/quijote.txt"

  //noinspection SourceNotClosed
  // Nota: asumimos que el archivo siempre puede abrirse correctamente.
  // En un programa real deberíamos cerrar el archivo con try-finally o Using.
  val text = Source.fromFile(path).mkString

  val document = ListBuffer.empty[Character]
  var position = 0

  val flyweight = new GlyphFlyweight("Times New Roman", 12)

  while position < text.length do
    val c = text.charAt(position)
    val character = new Character(
      flyweight.getOrCreate(c),
      position)
    document += character
    position += 1

  println(s"Total de caracteres: ${document.length}") // 2135404
  println(s"Total de Glyphs creados: ${flyweight.length}") // 92
