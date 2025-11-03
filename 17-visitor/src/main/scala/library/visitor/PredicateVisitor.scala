package cl.uchile.dcc
package library.visitor

import library.predicate.ByName

import cl.uchile.dcc.library.model.Item

trait PredicateVisitor:
  def visitByName(predicate: ByName, item: Item): Unit
