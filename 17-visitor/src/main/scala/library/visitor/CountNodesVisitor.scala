package cl.uchile.dcc
package library.visitor

import library.predicate._
import library.model.NullItem

/** Visitor que recorre el árbol de `Predicate` y cuenta sus nodos. */
class CountNodesVisitor extends PredicateVisitor:
  private var _count: Int = 0

  /** Reinicia el contador a 0. */
  def reset(): Unit = _count = 0

  /** Retorna el total de nodos visitados. */
  def count: Int = _count

  /** Ejecuta el conteo sobre un predicado y retorna el total. */
  def countNodes(predicate: Predicate): Int =
    reset()
    // usamos NullItem como Null Object cuando no hay un Item real
    predicate.accept(visitor = this, item = NullItem)
    _count

  override def visitByName(node: ByName, item: library.model.Item): Unit =
    _count += 1

  override def visitByYear(node: ByYear, item: library.model.Item): Unit =
    _count += 1

  override def visitByPrefix(node: ByPrefix, item: library.model.Item): Unit =
    _count += 1

  override def visitByVideoGame(node: ByVideoGame, item: library.model.Item): Unit =
    _count += 1

  override def visitNeg(node: Neg, item: library.model.Item): Unit =
    _count += 1
    node.getInner.accept(visitor = this, item = item)

  override def visitAnd(node: And, item: library.model.Item): Unit =
    _count += 1
    node.getLeft.accept(visitor = this, item = item)
    node.getRight.accept(visitor = this, item = item)

  override def visitOr(node: Or, item: library.model.Item): Unit =
    _count += 1
    node.getLeft.accept(visitor = this, item = item)
    node.getRight.accept(visitor = this, item = item)
