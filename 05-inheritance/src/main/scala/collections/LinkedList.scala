package cl.uchile.dcc

package collections

/**
 * Implementación de [[MutableList]] mediante una lista doblemente
 * enlazada.
 *
 * A diferencia de `ArrayList`, los elementos no se almacenan en posiciones
 * consecutivas de un arreglo. Cada elemento se guarda en un [[Node]] que
 * mantiene referencias al nodo anterior y al siguiente.
 *
 * La lista mantiene tres datos principales:
 *
 *   - `size`: cantidad de elementos almacenados;
 *   - `first`: primer nodo de la lista;
 *   - `last`: último nodo de la lista.
 *
 * La representación debe mantener las siguientes propiedades:
 *
 * {{{
 * lista vacía:
 *   first == None
 *   last  == None
 *   size  == 0
 *
 * lista no vacía:
 *   first.prev == None
 *   last.next   == None
 *
 * nodos vecinos:
 *   a.next == Some(b)
 *   b.prev == Some(a)
 * }}}
 *
 * [[AbstractMutableList]] ya implementa las operaciones cuyo
 * comportamiento puede compartirse entre distintas listas. Esta clase
 * implementa las operaciones que dependen específicamente de la
 * representación enlazada.
 */
class LinkedList extends AbstractMutableList:

  /** Cantidad de elementos almacenados actualmente. */
  var size: Int = 0

  /** Primer nodo de la lista, o `None` si está vacía. */
  var first: Option[Node] = None

  /** Último nodo de la lista, o `None` si está vacía. */
  var last: Option[Node] = None

  /**
   * Inserta `value` en la posición `index`.
   *
   * Insertar en una lista enlazada requiere modificar los enlaces de los
   * nodos vecinos. Existen cuatro casos relevantes:
   *
   *   1. insertar el primer elemento; 2. insertar al comienzo; 3. insertar
   *      al final; 4. insertar entre dos nodos existentes.
   *
   * Los índices válidos van desde `0` hasta `size`, inclusive. En
   * particular, `index == size` representa una inserción al final.
   *
   * Si el índice es inválido, la lista no se modifica.
   */
  override def add(index: Int, value: Any): Unit =
    if index < 0 || index > size then println("Index out of bounds")
    else
      val newNode = new Node(value)

      if size == 0 then
        // El primer nodo es simultáneamente el inicio y el final.
        first = Some(newNode)
        last = Some(newNode)
      else if index == 0 then
        // El nuevo nodo queda antes del antiguo `first`.
        newNode.next = first
        first.get.prev = Some(newNode)
        first = Some(newNode)
      else if index == size then
        // El nuevo nodo queda después del antiguo `last`.
        newNode.prev = last
        last.get.next = Some(newNode)
        last = Some(newNode)
      else
        // El nuevo nodo se conecta entre `before` y `after`.
        val after = getNode(index).get
        val before = after.prev.get

        newNode.prev = Some(before)
        newNode.next = Some(after)

        before.next = Some(newNode)
        after.prev = Some(newNode)

      size += 1

  /**
   * Obtiene el valor almacenado en `index`.
   *
   * [[getNode]] se encarga de localizar el nodo correspondiente. Si la
   * posición no existe, retorna `None`.
   */
  override def get(index: Int): Option[Any] =
    val node = getNode(index)

    if node.isDefined then Some(node.get.value)
    else None

  /**
   * Indica si la lista contiene al menos una aparición de `value`.
   *
   * Podemos expresar esta operación reutilizando [[indexOf]]: si la
   * búsqueda devuelve un índice distinto de `-1`, el valor está presente.
   */
  override def contains(value: Any): Boolean =
    indexOf(value) != -1

  /**
   * Busca la primera aparición de `value`.
   *
   * La búsqueda comienza en [[first]] y sigue los enlaces `next` hasta
   * encontrar el valor o llegar al final de la lista.
   *
   * @return
   *   el índice de la primera aparición o `-1` si no existe.
   */
  override def indexOf(value: Any): Int =
    var current = first
    var index = 0
    var result = -1

    while current.isDefined && result == -1 do
      if current.get.value == value then result = index

      current = current.get.next
      index += 1

    result

  /**
   * Elimina el nodo ubicado en `index`.
   *
   * Para quitar un nodo debemos reconectar sus vecinos:
   *
   * {{{
   * antes:
   *
   * before <-> node <-> after
   *
   * después:
   *
   * before <--------> after
   * }}}
   *
   * Si el nodo eliminado es el primero o el último, también debemos
   * actualizar [[first]] o [[last]], respectivamente.
   *
   * Finalmente se eliminan los enlaces del nodo retirado para que deje de
   * formar parte de la estructura.
   *
   * Si el índice es inválido, la lista no se modifica.
   */
  override def remove(index: Int): Unit =
    if index < 0 || index >= size then println("Index out of bounds")
    else
      val node = getNode(index).get

      if node.prev.isDefined then node.prev.get.next = node.next
      else
        // Si no hay nodo anterior, estamos eliminando `first`.
        first = node.next

      if node.next.isDefined then node.next.get.prev = node.prev
      else
        // Si no hay nodo siguiente, estamos eliminando `last`.
        last = node.prev

      // El nodo eliminado deja de estar conectado a la lista.
      node.next = None
      node.prev = None

      size -= 1

  /**
   * Retorna la cantidad de elementos almacenados actualmente.
   */
  override def getSize: Int =
    size

  /**
   * Busca la última aparición de `value`.
   *
   * A diferencia de [[indexOf]], la búsqueda continúa después de encontrar
   * una coincidencia. `result` recuerda el índice de la aparición más
   * reciente.
   *
   * @return
   *   el índice de la última aparición o `-1` si el valor no existe.
   */
  override def lastIndexOf(value: Any): Int =
    var current = first
    var index = 0
    var result = -1

    while current.isDefined do
      if current.get.value == value then result = index

      current = current.get.next
      index += 1

    result

  /**
   * Busca el nodo ubicado en `index`.
   *
   * Comienza en [[first]] y avanza siguiendo los enlaces `next` hasta
   * alcanzar la posición solicitada.
   *
   * Este método es un helper de la implementación enlazada: el contrato
   * público de [[SimpleList]] trabaja con valores, mientras que aquí
   * necesitamos acceder a los nodos para modificar sus enlaces.
   *
   * @param index
   *   posición del nodo buscado.
   * @return
   *   el nodo correspondiente, o `None` si el índice es inválido.
   */
  def getNode(index: Int): Option[Node] =
    if index < 0 || index >= size then None
    else
      var current = first
      var currentIndex = 0

      while currentIndex < index do
        current = current.get.next
        currentIndex += 1

      current

/**
 * Nodo de una [[LinkedList]] doblemente enlazada.
 *
 * Cada nodo almacena un valor y referencias opcionales a sus dos vecinos:
 *
 * {{{
 * prev <-> este nodo <-> next
 * }}}
 *
 * `None` indica que el vecino correspondiente no existe. Por ejemplo, el
 * primer nodo tiene `prev == None` y el último tiene `next == None`.
 *
 * @param value
 *   valor almacenado en este nodo.
 */
class Node(val value: Any):

  /** Nodo siguiente, o `None` si este es el último nodo. */
  var next: Option[Node] = None

  /** Nodo anterior, o `None` si este es el primer nodo. */
  var prev: Option[Node] = None
