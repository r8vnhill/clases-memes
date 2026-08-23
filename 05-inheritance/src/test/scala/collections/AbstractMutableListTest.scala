package cl.uchile.dcc.collections

/**
 * Pruebas del contrato observable de [[MutableList]].
 *
 * `ArrayList` y `LinkedList` tienen representaciones internas distintas,
 * pero deberían comportarse de la misma forma cuando se utilizan a través
 * de la abstracción `MutableList`.
 *
 * Cada test comienza con una lista nueva. `beforeEach` prepara nuevamente
 * el objeto antes de ejecutar cada caso, evitando que las modificaciones
 * de un test afecten a los siguientes (Scalameta, n.d.).
 *
 * Las suites concretas solo deben implementar `makeList`. Así, esta clase
 * abstracta permite reutilizar las expectativas del contrato sin
 * repetirlas.
 *
 * ==Referencias==
 *
 * Scalameta. (n.d.). ''Using fixtures''. MUnit.
 * https://scalameta.org/munit/docs/fixtures.html
 */
abstract class AbstractMutableListTest extends munit.FunSuite:
  var list: MutableList = makeList

  def makeList: MutableList

  override def beforeEach(context: BeforeEach): Unit =
    list = makeList

  def assertEmpty(): Unit =
    assertEquals(list.getSize, 0)
    assertEquals(list.get(0), None)
    assertEquals(list.indexOf("missing"), -1)
    assertEquals(list.lastIndexOf("missing"), -1)

  def assertIndexedInsertion(): Unit =
    list.add(0, "b")
    list.add(0, "a")
    list.add(2, "d")
    list.add(2, "c")

    assertEquals(list.getSize, 4)
    assertEquals(list.get(0), Some("a"))
    assertEquals(list.get(1), Some("b"))
    assertEquals(list.get(2), Some("c"))
    assertEquals(list.get(3), Some("d"))

  def assertAppend(): Unit =
    list.add("a")
    list.add("b")

    assertEquals(list.getSize, 2)
    assertEquals(list.get(0), Some("a"))
    assertEquals(list.get(1), Some("b"))

  def assertAddAll(): Unit =
    list.add("a")
    list.add("d")
    list.addAll(1, List[Any]("b", "c"))

    assertEquals(list.getSize, 4)
    assertEquals(list.get(0), Some("a"))
    assertEquals(list.get(1), Some("b"))
    assertEquals(list.get(2), Some("c"))
    assertEquals(list.get(3), Some("d"))

  def assertSearch(): Unit =
    list.addAll(0, List[Any]("a", "x", "b", "x"))

    assert(list.contains("x"))
    assert(!list.contains("missing"))
    assertEquals(list.indexOf("x"), 1)
    assertEquals(list.lastIndexOf("x"), 3)
    assertEquals(list.indexOf("missing"), -1)
    assertEquals(list.lastIndexOf("missing"), -1)

  def assertRemoval(): Unit =
    list.addAll(0, List[Any]("a", "b", "c", "d", "e"))

    list.remove(0)
    list.remove(list.getSize - 1)
    list.remove(1)

    assertEquals(list.getSize, 2)
    assertEquals(list.get(0), Some("b"))
    assertEquals(list.get(1), Some("d"))

  def assertInvalidOperations(): Unit =
    list.addAll(0, List[Any]("a", "b", "c"))

    list.add(-1, "ignored")
    list.add(list.getSize + 1, "ignored")
    list.addAll(-1, List[Any]("ignored"))
    list.remove(-1)
    list.remove(list.getSize)

    assertEquals(list.getSize, 3)
    assertEquals(list.get(0), Some("a"))
    assertEquals(list.get(1), Some("b"))
    assertEquals(list.get(2), Some("c"))

  test("una lista nueva está vacía"):
    assertEmpty()

  test("add(index, value) inserta al comienzo, medio y final"):
    assertIndexedInsertion()

  test("add(value) agrega al final"):
    assertAppend()

  test("addAll preserva el orden de los elementos"):
    assertAddAll()

  test("las operaciones de búsqueda respetan el mismo contrato"):
    assertSearch()

  test("remove elimina elementos en distintas posiciones"):
    assertRemoval()

  test("las operaciones con índices inválidos no modifican la lista"):
    assertInvalidOperations()
