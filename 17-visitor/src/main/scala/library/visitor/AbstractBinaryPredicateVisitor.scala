package cl.uchile.dcc
package library.visitor

import library.model.Item
import library.predicate.*

/**
 * Clase base para visitantes de `Predicate` que comparten la misma lógica de
 * recorrido binario (AND/OR): visitar izquierda, capturar, visitar derecha,
 * capturar y combinar.
 *
 * Parametrizamos por el tipo del acumulador `A` para reutilizar el patrón tanto
 * en evaluadores (Boolean) como en pretty-printers (String).
 */
abstract class AbstractBinaryPredicateVisitor[A] extends PredicateVisitor:
  /** Lee el acumulador actual. */
  protected def acc: A

  /** Escribe el acumulador actual. */
  protected def acc_=(value: A): Unit

  /** Combina resultados para AND. */
  protected def combineAnd(left: A, right: A): A

  /** Combina resultados para OR. */
  protected def combineOr(left: A, right: A): A

  /** Implementación genérica de AND sobre el acumulador. */
  override def visitAnd(p: And, item: Item): Unit =
    traverseBinary(p.getLeft, p.getRight, item, combineAnd)

  /** Implementación genérica de OR sobre el acumulador. */
  override def visitOr(p: Or, item: Item): Unit =
    traverseBinary(p.getLeft, p.getRight, item, combineOr)

  /**
   * Recorre y combina dos subpredicados binarios de forma genérica.
   */
  private def traverseBinary(left: Predicate,
                             right: Predicate,
                             item: Item,
                             combine: (A, A) => A
  ): Unit =
    left.accept(this, item)
    val leftAcc = acc
    right.accept(this, item)
    val rightAcc = acc
    acc = combine(leftAcc, rightAcc)
