package cl.uchile.dcc

package collections

/**
 * Implementación de [[MutableList]] utilizando un arreglo como
 * representación interna.
 *
 * La lista mantiene dos conceptos distintos:
 *
 *   - `size`: cantidad de elementos que pertenecen actualmente a la lista;
 *   - `data.length`: cantidad de posiciones disponibles en el arreglo.
 *
 * Por ejemplo, una lista puede tener:
 *
 * {{{
 * size        = 3
 * data.length = 10
 *
 * data:
 * [ a ][ b ][ c ][   ][   ][   ][   ][   ][   ][   ]
 *   0    1    2
 * }}}
 *
 * Las posiciones desde `0` hasta `size - 1` contienen los elementos de la
 * lista. Las posiciones restantes corresponden a capacidad disponible.
 *
 * Cuando el arreglo se llena, esta implementación crea uno nuevo con el
 * doble de capacidad y copia los elementos existentes. En cambio, insertar
 * o eliminar en una posición intermedia requiere desplazar elementos
 * dentro del arreglo.
 *
 * [[AbstractMutableList]] implementa las operaciones cuyo comportamiento
 * puede compartirse entre distintas representaciones. Esta clase
 * implementa las operaciones que dependen específicamente del
 * almacenamiento en un arreglo.
 *
 * @param capacity
 *   capacidad inicial del arreglo. Si es menor que `1`, se utiliza una
 *   capacidad inicial de `1`.
 */
class ArrayList(capacity: Int = 10) extends AbstractMutableList:

  /**
   * Cantidad de elementos almacenados actualmente.
   *
   * No debe confundirse con `data.length`, que representa la capacidad del
   * arreglo.
   */
  var size: Int = 0

  /**
   * Arreglo donde se almacenan los elementos.
   *
   * La capacidad mínima es `1`. Esto permite que, cuando el arreglo se
   * llene, podamos aumentar su capacidad multiplicándola por `2`.
   */
  var data: Array[Any] =
    new Array[Any](if capacity < 1 then 1 else capacity)

  /**
   * Inserta `value` en la posición `index`.
   *
   * Los índices válidos van desde `0` hasta `size`, inclusive. En
   * particular, `index == size` representa una inserción al final.
   *
   * La operación tiene dos pasos importantes: (1) si el arreglo está
   * lleno, aumenta su capacidad; (2) desplaza hacia la derecha los
   * elementos desde `index` para dejar una posición libre.
   *
   * Por ejemplo:
   *
   * {{{
   * antes de add(1, "b"):
   *
   * [ a ][ c ][   ][   ]
   *
   * después:
   *
   * [ a ][ b ][ c ][   ]
   * }}}
   *
   * Si el índice es inválido, la lista no se modifica.
   *
   * @param index
   *   posición donde insertar el valor.
   * @param value
   *   valor que queremos insertar.
   */
  override def add(index: Int, value: Any): Unit =
    if index < 0 || index > size then println("Index out of bounds")
    else

      // Si no queda espacio, creamos un arreglo con el doble de capacidad.
      if size == data.length then
        val newData = new Array[Any](data.length * 2)

        // Conservamos los elementos que ya pertenecían a la lista.
        for i <- 0 until size do newData(i) = data(i)

        data = newData

      // Desplazamos desde el final hacia la derecha. Hacerlo en este orden
      // evita sobrescribir elementos que todavía necesitamos mover.
      for i <- size - 1 to index by -1 do data(i + 1) = data(i)

      data(index) = value
      size += 1

  /**
   * Obtiene el elemento almacenado en `index`.
   *
   * Solo las posiciones entre `0` y `size - 1` corresponden a elementos de
   * la lista. Que una posición exista dentro de `data` no significa
   * necesariamente que pertenezca a la lista.
   *
   * @param index
   *   posición que queremos consultar.
   * @return
   *   `Some(value)` si el índice es válido y `None` en caso contrario.
   */
  override def get(index: Int): Option[Any] =
    if index < 0 || index >= size then None
    else Some(data(index))

  /**
   * Indica si la lista contiene al menos una aparición de `value`.
   *
   * Reutilizamos [[indexOf]]: si existe una primera aparición, su índice
   * será distinto de `-1`.
   */
  override def contains(value: Any): Boolean =
    indexOf(value) != -1

  /**
   * Busca la primera aparición de `value`.
   *
   * La búsqueda considera solamente las primeras `size` posiciones del
   * arreglo, porque las posiciones restantes representan capacidad
   * disponible y no elementos de la lista.
   *
   * `result` comienza en `-1` y cambia cuando encontramos la primera
   * coincidencia. A partir de ese momento, la condición del `while`
   * termina la búsqueda.
   *
   * @param value
   *   valor que queremos buscar.
   * @return
   *   el índice de la primera aparición o `-1` si no está presente.
   */
  override def indexOf(value: Any): Int =
    var index = 0
    var result = -1

    while index < size && result == -1 do
      if data(index) == value then result = index

      index += 1

    result

  /**
   * Elimina el elemento ubicado en `index`.
   *
   * Para evitar dejar un espacio vacío en medio de la lista, todos los
   * elementos posteriores se desplazan una posición hacia la izquierda:
   *
   * {{{
   * antes de remove(1):
   *
   * [ a ][ b ][ c ][ d ]
   *
   * después:
   *
   * [ a ][ c ][ d ][   ]
   * }}}
   *
   * Después del desplazamiento, la antigua última posición se limpia y
   * `size` disminuye en uno.
   *
   * Si el índice es inválido, la lista no se modifica.
   *
   * @param index
   *   posición del elemento que queremos eliminar.
   */
  override def remove(index: Int): Unit =
    if index < 0 || index >= size then println("Index out of bounds")
    else
      var current = index

      // Cada elemento posterior ocupa la posición de su predecesor.
      while current < size - 1 do
        data(current) = data(current + 1)
        current += 1

      // La antigua última posición ya no corresponde a un elemento de la lista.
      data(size - 1) = null
      size -= 1

  /**
   * Retorna la cantidad de elementos que pertenecen actualmente a la
   * lista.
   *
   * Este valor puede ser menor que `data.length`.
   */
  override def getSize: Int =
    size

  /**
   * Busca la última aparición de `value`.
   *
   * La búsqueda comienza en el último elemento de la lista y avanza hacia
   * el comienzo. Por esto, la primera coincidencia que encontramos ya
   * corresponde a la última aparición del valor.
   *
   * @param value
   *   valor que queremos buscar.
   * @return
   *   el índice de la última aparición o `-1` si no está presente.
   */
  override def lastIndexOf(value: Any): Int =
    var index = size - 1
    var result = -1

    while index >= 0 && result == -1 do
      if data(index) == value then result = index

      index -= 1

    result
