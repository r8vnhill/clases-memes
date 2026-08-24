package cl.uchile.dcc.collections

/**
 * Pruebas específicas de la representación interna de [[LinkedList]].
 *
 * [[AbstractMutableListTest]] verifica el comportamiento que debe
 * satisfacer cualquier implementación de [[MutableList]]. Esta clase
 * complementa esas pruebas verificando propiedades que dependen
 * específicamente de la representación elegida por [[LinkedList]]: una
 * lista doblemente enlazada.
 *
 * Una representación concreta puede imponer propiedades que deben
 * mantenerse durante toda la vida de un objeto. Estas propiedades forman
 * su '''invariante de representación''' (Miller & Goldman, 2022).
 *
 * En [[LinkedList]], el invariante incluye que:
 *
 *   - si la lista está vacía, `first` y `last` son `None`;
 *   - si la lista no está vacía, `first.prev == None`;
 *   - si la lista no está vacía, `last.next == None`;
 *   - si un nodo `a` tiene a `b` como siguiente, `b` debe tener a `a` como
 *     anterior;
 *   - `first` y `last` identifican los extremos reales de la cadena de
 *     nodos.
 *
 * Las operaciones que modifican la estructura deben preservar estas
 * propiedades. Una estructura puede producir temporalmente los valores
 * esperados mediante su interfaz pública y, aun así, contener enlaces
 * internos inconsistentes que provoquen errores en operaciones posteriores
 * (Miller & Goldman, 2022).
 *
 * Por esta razón, estos tests acceden intencionalmente a detalles internos
 * como `first`, `last` y `getNode`. Su objetivo no es verificar nuevamente
 * el contrato público de [[MutableList]], sino comprobar que
 * [[LinkedList]] mantiene correctamente su representación concreta.
 *
 * En consecuencia, estas pruebas están acopladas a la implementación de
 * [[LinkedList]] y no deberían reutilizarse para otras implementaciones de
 * [[MutableList]]. Las pruebas reutilizables del contrato corresponden a
 * [[AbstractMutableListTest]].
 *
 * ==Referencias==
 *
 * Miller, R., & Goldman, M. (2022). ''Abstraction functions & rep
 * invariants'' (Reading 11). MIT 6.031: Software Construction,
 * Massachusetts Institute of Technology.
 * https://web.mit.edu/6.031/www/sp22/classes/11-abstraction-functions-rep-invariants/
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
