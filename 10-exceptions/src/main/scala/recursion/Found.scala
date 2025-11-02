package cl.uchile.dcc
package recursion

import scala.util.control.NoStackTrace

// Modelo simple de árbol binario
class Node(val value: Int,
           val left: Option[Node] = None,
           val right: Option[Node] = None
)

object SearchWithoutThrowable:
  def contains(root: Option[Node], target: Int): Boolean =
    if root.isEmpty then false
    else if root.get.value == target then true
    else
      // Debemos revisar ambos subárboles incluso si ya encontramos el valor
      contains(root.get.left, target)
      || contains(root.get.right, target)

object Found extends Throwable
object SearchWithThrowable:
  def contains(root: Option[Node], target: Int): Boolean =
    try
      visit(root, target)
      false
    catch case Found => true

  private def visit(node: Option[Node], target: Int): Unit =
    if node.isEmpty then ()
    else if node.get.value == target then throw Found
    else
      visit(node.get.left, target)
      visit(node.get.right, target)
