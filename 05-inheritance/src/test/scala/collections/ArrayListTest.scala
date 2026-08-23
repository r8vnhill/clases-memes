package cl.uchile.dcc.collections

/**
 * Pruebas de la representación interna de [[ArrayList]].
 *
 * A diferencia de [[AbstractMutableListTest]], estos tests conocen que
 * `ArrayList` utiliza un arreglo y verifican propiedades específicas de
 * esa representación.
 */
class ArrayListTest extends AbstractMutableListTest:
  override def makeList: MutableList = new ArrayList(2)

  test("aumenta su capacidad cuando el arreglo está lleno"):
    val arrayList = new ArrayList(2)
    arrayList.add("a")
    arrayList.add("b")

    assertEquals(arrayList.data.length, 2)

    arrayList.add("c")

    assertEquals(arrayList.data.length, 4)
    assertEquals(arrayList.getSize, 3)

  test("desplaza elementos hacia la derecha al insertar"):
    val arrayList = new ArrayList(2)
    arrayList.add("a")
    arrayList.add("c")

    arrayList.add(1, "b")

    assertEquals(arrayList.get(0), Some("a"))
    assertEquals(arrayList.get(1), Some("b"))
    assertEquals(arrayList.get(2), Some("c"))

  test("desplaza elementos hacia la izquierda al eliminar"):
    val arrayList = new ArrayList(2)
    arrayList.addAll(0, List[Any]("a", "b", "c"))

    arrayList.remove(1)

    assertEquals(arrayList.getSize, 2)
    assertEquals(arrayList.get(0), Some("a"))
    assertEquals(arrayList.get(1), Some("c"))
