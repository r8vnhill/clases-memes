package cl.uchile.dcc
package composite

trait FileSystemItem:
  val name: String
  def tree(indent: String = ""): String
