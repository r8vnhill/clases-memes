package cl.uchile.dcc
package library.visitor

import library.model.Item
import library.predicate._

/** Visitor para recorrer y evaluar nodos de Predicate. */
trait PredicateVisitor:
  def visitByName(p: ByName, item: Item): Unit
  def visitByYear(p: ByYear, item: Item): Unit
  def visitByPrefix(p: ByPrefix, item: Item): Unit
  def visitAnd(p: And, item: Item): Unit
  def visitOr(p: Or, item: Item): Unit
  def visitNeg(p: Neg, item: Item): Unit
  def visitByVideoGame(p: ByVideoGame, item: Item): Unit
