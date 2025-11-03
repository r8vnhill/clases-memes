package cl.uchile.dcc
package library.visitor

import library.model.Item
import library.predicate.ByName

class EvaluatePredicateVisitor extends PredicateVisitor:
  private var _result = false
  
  def result: Boolean = _result
  
  override def visitByName(predicate: ByName, item: Item): Unit =
    _result = predicate.name == item.name  
