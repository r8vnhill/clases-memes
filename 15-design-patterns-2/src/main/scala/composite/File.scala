package cl.uchile.dcc
package composite

class File(override val name: String) extends FileSystemItem:
  override def tree(indent: String): String =
    s"${indent}- $name\n"
