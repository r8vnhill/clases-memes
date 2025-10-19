package cl.uchile.dcc
package composite

import scala.collection.mutable

class Directory(override val name: String) extends FileSystemItem:
  private val items: mutable.ListBuffer[FileSystemItem] =
    mutable.ListBuffer.empty

  def add(item: FileSystemItem): Unit =
    items += item

  def remove(item: FileSystemItem): Unit =
    items -= item

  override def tree(indent: String): String =
    var result = s"${indent}+ $name\n"
    val childIndent = indent + "  "
    for item <- items do result += item.tree(childIndent)
    result

@main def runTreeDemo(): Unit =
  val root = new Directory("root")
  val src = new Directory("src")
  val main = new Directory("main")
  val fileA = new File("A.scala")
  val fileB = new File("B.scala")

  main.add(fileA)
  src.add(main)
  root.add(src)
  root.add(fileB)

  println(root.tree())
