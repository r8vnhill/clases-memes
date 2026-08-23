package cl.uchile.dcc.collections

/**
 * Pruebas de la representación interna de [[LinkedList]].
 *
 * Estos tests verifican propiedades propias de una lista doblemente
 * enlazada: `first` no tiene un nodo anterior, `last` no tiene un nodo
 * siguiente y los nodos vecinos se referencian mutuamente.
 */
class LinkedListTest extends AbstractMutableListTest:
  override def makeList: MutableList = new LinkedList

  test("una inserción inicializa ambos extremos"):
    val linkedList = new LinkedList
    linkedList.add("a")

    assertEquals(linkedList.first.get.value, "a")
    assertEquals(linkedList.last.get.value, "a")
    assertEquals(linkedList.first.get.prev, None)
    assertEquals(linkedList.last.get.next, None)

  test("insertar al comienzo actualiza first"):
    val linkedList = new LinkedList
    linkedList.add("b")
    linkedList.add(0, "a")

    assertEquals(linkedList.first.get.value, "a")
    assertEquals(linkedList.first.get.prev, None)
    assertEquals(linkedList.first.get.next.get.value, "b")
    assertEquals(linkedList.first.get.next.get.prev, linkedList.first)

  test("insertar al final actualiza last"):
    val linkedList = new LinkedList
    linkedList.add("a")
    linkedList.add("b")

    assertEquals(linkedList.last.get.value, "b")
    assertEquals(linkedList.last.get.next, None)
    assertEquals(linkedList.last.get.prev.get.value, "a")
    assertEquals(linkedList.last.get.prev.get.next, linkedList.last)

  test("insertar al medio conecta el nuevo nodo con ambos vecinos"):
    val linkedList = new LinkedList
    linkedList.addAll(0, List[Any]("a", "b", "d"))

    linkedList.add(2, "c")

    val middle = linkedList.getNode(2).get

    assertEquals(middle.value, "c")
    assertEquals(middle.prev.get.value, "b")
    assertEquals(middle.next.get.value, "d")
    assertEquals(middle.prev.get.next, Some(middle))
    assertEquals(middle.next.get.prev, Some(middle))

  test("eliminar el único nodo vacía ambos extremos"):
    val linkedList = new LinkedList
    linkedList.add("only")

    linkedList.remove(0)

    assertEquals(linkedList.first, None)
    assertEquals(linkedList.last, None)
    assertEquals(linkedList.getSize, 0)

  test("eliminar el primer nodo actualiza first"):
    val linkedList = new LinkedList
    linkedList.addAll(0, List[Any]("a", "b", "c"))

    linkedList.remove(0)

    assertEquals(linkedList.first.get.value, "b")
    assertEquals(linkedList.first.get.prev, None)

  test("eliminar el último nodo actualiza last"):
    val linkedList = new LinkedList
    linkedList.addAll(0, List[Any]("a", "b", "c"))

    linkedList.remove(2)

    assertEquals(linkedList.last.get.value, "b")
    assertEquals(linkedList.last.get.next, None)

  test("eliminar un nodo intermedio reconecta sus vecinos"):
    val linkedList = new LinkedList
    linkedList.addAll(0, List[Any]("a", "b", "c", "d"))

    linkedList.remove(1)

    assertEquals(linkedList.get(0), Some("a"))
    assertEquals(linkedList.get(1), Some("c"))

    val first = linkedList.first.get
    val second = first.next.get

    assertEquals(second.prev, Some(first))
