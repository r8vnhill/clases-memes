package cl.uchile.dcc

package collections

/**
 * Implementación abstracta común para [[MutableList]].
 *
 * `AbstractMutableList` ocupa una posición intermedia entre el contrato
 * [[MutableList]] y sus implementaciones concretas:
 *
 * {{{
 * MutableList
 *      ^
 *      |
 * AbstractMutableList
 *      ^
 *      |
 * +----+----+
 * |         |
 * ArrayList LinkedList
 * }}}
 *
 * [[MutableList]] especifica las operaciones que debe ofrecer una lista
 * mutable, pero no contiene sus implementaciones. En cambio, esta clase
 * puede implementar aquellas operaciones cuyo comportamiento es
 * independiente de cómo se almacenan los elementos.
 *
 * Por ejemplo, agregar un elemento al final siempre puede expresarse como
 * "insertarlo en la posición `getSize`". Esto es cierto tanto para una
 * lista basada en un arreglo como para una lista enlazada.
 *
 * Las operaciones que dependen de la representación concreta permanecen
 * abstractas y deben ser implementadas por las subclases. Una clase
 * abstracta puede combinar miembros sin implementación con miembros
 * completamente implementados (Scala Documentation, n.d.).
 *
 * Esta organización corresponde a una '''implementación esquelética''':
 * mantenemos el contrato en una interfaz y concentramos comportamiento
 * común en una clase abstracta, evitando duplicarlo en cada implementación
 * concreta (Bloch, 2018, Item 20).
 *
 * ==Referencias==
 *
 * Bloch, J. (2018). ''Effective Java'' (3rd ed.), Item 20: "Prefer
 * interfaces to abstract classes". Addison-Wesley Professional.
 *
 * Scala Documentation. (n.d.). ''OOP modeling''. Scala 3 Book.
 * https://docs.scala-lang.org/scala3/book/domain-modeling-oop.html
 */
abstract class AbstractMutableList extends MutableList:

  /**
   * Inserta un elemento en una posición determinada.
   *
   * Esta operación permanece abstracta porque su implementación depende de
   * la representación utilizada por cada subclase. Por ejemplo,
   * `ArrayList` necesita desplazar posiciones de un arreglo, mientras que
   * `LinkedList` necesita modificar enlaces entre nodos.
   *
   * La ausencia de un cuerpo indica que el método sigue siendo abstracto
   * (Scala Documentation, n.d.).
   */
  override def add(index: Int, value: Any): Unit

  /**
   * Obtiene el elemento ubicado en una posición.
   *
   * También permanece abstracto porque localizar el elemento depende de la
   * representación concreta de la lista.
   */
  override def get(index: Int): Option[Any]

  /**
   * Agrega `value` al final de la lista.
   *
   * Esta operación sí puede implementarse sin conocer la representación
   * interna: la posición inmediatamente posterior al último elemento es
   * precisamente [[getSize]].
   *
   * {{{
   * add(value)
   *
   * equivale a
   *
   * add(getSize, value)
   * }}}
   *
   * La implementación concreta de `add(index, value)` será decidida por la
   * subclase.
   *
   * @param value
   *   elemento que queremos agregar.
   */
  override def add(value: Any): Unit =
    add(getSize, value)

  /**
   * Inserta todos los elementos de `values` comenzando en `index`.
   *
   * Esta implementación también puede compartirse porque utiliza
   * únicamente operaciones definidas por el contrato de la lista.
   *
   * Los valores se recorren en orden inverso porque todos se insertan en
   * la misma posición. Por ejemplo:
   *
   * {{{
   * lista inicial:
   * [a, d]
   *
   * queremos insertar:
   * [b, c]
   *
   * primero insertamos c:
   * [a, c, d]
   *
   * luego insertamos b en la misma posición:
   * [a, b, c, d]
   * }}}
   *
   * De esta forma, el resultado conserva el orden original de `values`.
   *
   * Si `index` no corresponde a una posición válida de inserción, la lista
   * no se modifica.
   *
   * @param index
   *   posición donde comienza la inserción.
   * @param values
   *   elementos que queremos insertar.
   */
  override def addAll(index: Int, values: List[Any]): Unit =
    if index < 0 || index > getSize then println("Index out of bounds")
    else for value <- values.reverse do add(index, value)
