package cl.uchile.dcc
package filesystem

import scala.collection.mutable.ListBuffer

/** Sistema de archivos simplificado para ilustrar pattern matching. */
sealed trait FileSystemEntry
case class Directory(name: String) extends FileSystemEntry:
  /** Hijos del directorio (mutable para simplicidad didáctica) */
  val children: ListBuffer[FileSystemEntry] = ListBuffer.empty
case class File(name: String, size: Int) extends FileSystemEntry

class FileSystem:
  /** Directorio raíz */
  val root = Directory("/")
  /** Número de archivos en todo el árbol */
  def numberOfFiles: Int = numberOfFilesIn(root)
  /** Número de directorios en todo el árbol */
  def numberOfDirectories: Int = numberOfDirectoriesIn(root)
  /** Listado plano de nombres de archivos */
  def listing: String = listingOf(root)

/** Cuenta archivos en el sub-árbol dado */
def numberOfFilesIn(item: FileSystemEntry): Int =
  item match
    case d: Directory => d.children.map(numberOfFilesIn).sum
    case _: File      => 1

/** Cuenta directorios en el sub-árbol dado (incluye el directorio actual) */
def numberOfDirectoriesIn(item: FileSystemEntry): Int =
  item match
    case d: Directory => 1 + d.children.map(numberOfDirectoriesIn).sum
    case _: File      => 0

/** Retorna un listado plano (solo nombres de archivos) */
def listingOf(item: FileSystemEntry): String =
  item match
    case d: Directory => d.children.map(listingOf).mkString("\n")
    case f: File      => f.name

@main def fileSystemExample(): Unit =
  val fs = new FileSystem
  val etc = Directory("etc"); val home = Directory("home")
  fs.root.children += etc; fs.root.children += home
  etc.children += File("hosts", 42)
  home.children += File("notes.txt", 10)
  println(s"files=${fs.numberOfFiles}, dirs=${fs.numberOfDirectories}")
  println(fs.listing)
