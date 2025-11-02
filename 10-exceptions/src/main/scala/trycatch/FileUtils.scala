package cl.uchile.dcc
package trycatch

import java.io.IOException
import java.nio.file.{Files, NoSuchFileException, Paths}

def loadText(path: String): String =
  try Files.readString(Paths.get(path))
  catch
    case _: NoSuchFileException =>
      "file not found: using default content"
    case e: IOException =>
      throw new RuntimeException(s"I/O Error in $path", e)
