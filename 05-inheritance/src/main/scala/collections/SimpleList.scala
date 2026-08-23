package cl.uchile.dcc

package collections

/**
 * Contrato básico para una lista.
 *
 * Una `SimpleList` representa una secuencia ordenada de elementos sobre la
 * que podemos consultar posiciones y buscar valores. El trait define
 * '''qué operaciones están disponibles''', pero no determina cómo deben
 * almacenarse los elementos.
 *
 * Distintas clases pueden implementar este mismo contrato usando
 * representaciones diferentes. Por ejemplo, una implementación podría
 * utilizar un arreglo y otra una secuencia de nodos enlazados.
 *
 * En este punto del curso utilizamos `Any` como tipo de los elementos para
 * permitir que las listas almacenen distintos tipos de valores. Esta es
 * una simplificación deliberada: más adelante veremos cómo los
 * '''generics''' permiten expresar de forma más precisa qué tipo de
 * elementos contiene una lista.
 *
 * Los índices comienzan en `0`. Por lo tanto, para una lista de tamaño
 * `n`, los índices de elementos existentes van desde `0` hasta `n - 1`.
 */
trait SimpleList:

  /**
   * Obtiene el elemento ubicado en `index`.
   *
   * Utilizamos [[Option]] para representar que una posición puede no
   * contener un elemento:
   *
   *   - `Some(value)` si `index` corresponde a una posición válida;
   *   - `None` si el índice está fuera de la lista.
   *
   * @param index
   *   posición que queremos consultar.
   * @return
   *   el elemento de esa posición, si existe.
   */
  def get(index: Int): Option[Any]

  /**
   * Indica si la lista contiene al menos una aparición de `value`.
   *
   * @param value
   *   valor que queremos buscar.
   * @return
   *   `true` si el valor está presente y `false` en caso contrario.
   */
  def contains(value: Any): Boolean

  /**
   * Busca la primera aparición de `value`.
   *
   * @param value
   *   valor que queremos buscar.
   * @return
   *   el índice de su primera aparición, o `-1` si no está presente.
   */
  def indexOf(value: Any): Int

  /**
   * Retorna la cantidad de elementos almacenados actualmente.
   */
  def getSize: Int

  /**
   * Busca la última aparición de `value`.
   *
   * Esta operación se diferencia de [[indexOf]] cuando un mismo valor
   * aparece más de una vez en la lista.
   *
   * @param value
   *   valor que queremos buscar.
   * @return
   *   el índice de su última aparición, o `-1` si no está presente.
   */
  def lastIndexOf(value: Any): Int

/**
 * Especialización de [[SimpleList]] que agrega operaciones de
 * modificación.
 *
 * Como `MutableList` extiende `SimpleList`, toda lista mutable también
 * ofrece las operaciones de consulta definidas por `SimpleList`:
 *
 * {{{
 * MutableList <: SimpleList
 * }}}
 *
 * Sin embargo, una `MutableList` agrega además la capacidad de insertar y
 * eliminar elementos. Por esto, podemos usar una lista mutable donde se
 * espera una `SimpleList`, pero no necesariamente al revés.
 *
 * Este trait continúa definiendo solamente un '''contrato''': no contiene
 * la implementación de las operaciones. Más adelante distintas clases,
 * como `ArrayList` y `LinkedList`, podrán satisfacer este mismo contrato
 * utilizando representaciones internas diferentes.
 */
trait MutableList extends SimpleList:

  /**
   * Inserta `value` en la posición `index`.
   *
   * Los elementos que estaban desde esa posición en adelante deben
   * desplazarse para dejar espacio al nuevo valor.
   *
   * Como también es posible insertar después del último elemento, los
   * índices válidos para esta operación van desde `0` hasta [[getSize]],
   * inclusive.
   *
   * @param index
   *   posición donde insertar el nuevo elemento.
   * @param value
   *   elemento que queremos insertar.
   */
  def add(index: Int, value: Any): Unit

  /**
   * Agrega `value` al final de la lista.
   *
   * Esta operación puede entenderse como una forma conveniente de insertar
   * un elemento en la posición [[getSize]].
   *
   * @param value
   *   elemento que queremos agregar.
   */
  def add(value: Any): Unit

  /**
   * Inserta varios valores comenzando en la posición `index`.
   *
   * Los valores deben quedar en el mismo orden en que aparecen en
   * `values`. Por ejemplo:
   *
   * {{{
   * // lista: [a, d]
   * list.addAll(1, List("b", "c"))
   * // resultado: [a, b, c, d]
   * }}}
   *
   * `List[Any]` se refiere aquí a la clase `List` provista por Scala, no a
   * [[SimpleList]].
   *
   * @param index
   *   posición donde comienza la inserción.
   * @param values
   *   elementos que queremos insertar.
   */
  def addAll(index: Int, values: List[Any]): Unit

  /**
   * Elimina el elemento ubicado en `index`.
   *
   * Los elementos posteriores deben ocupar las posiciones que correspondan
   * después de la eliminación.
   *
   * @param index
   *   posición del elemento que queremos eliminar.
   */
  def remove(index: Int): Unit
