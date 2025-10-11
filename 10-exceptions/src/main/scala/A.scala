package cl.uchile.dcc

import scala.io.Source
import scala.util.Using

def readFirstLine(path: String): Option[String] =
  Using(Source.fromFile(path)) { src =>
    src.getLines().take(1).mkString
  }.toOption // Colapsa todas las excepciones en None
