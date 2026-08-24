package cl.uchile.dcc.collections

/**
 * Suite reutilizable de pruebas para el contrato observable de
 * [[MutableList]].
 *
 * [[MutableList]] define qué operaciones debe ofrecer una lista mutable y
 * qué resultados pueden observar quienes la utilizan. Sus implementaciones
 * concretas pueden representar los datos de maneras completamente
 * distintas: por ejemplo, [[ArrayList]] utiliza un arreglo, mientras que
 * [[LinkedList]] utiliza nodos enlazados.
 *
 * Estas diferencias de representación no deberían afectar el
 * comportamiento prometido por el contrato. Por esta razón, los tests de
 * esta clase utilizan únicamente operaciones disponibles a través de
 * [[MutableList]] y no inspeccionan detalles internos de una
 * implementación concreta.
 *
 * ==Una misma suite para distintas implementaciones==
 *
 * Esta clase es abstracta porque no decide qué implementación concreta
 * probar. Las subclases proporcionan esa decisión implementando
 * [[makeList]]:
 *
 * {{{
 * class ArrayListTest extends AbstractMutableListTest:
 *   override def makeList: MutableList = new ArrayList
 *
 * class LinkedListTest extends AbstractMutableListTest:
 *   override def makeList: MutableList = new LinkedList
 * }}}
 *
 * De esta manera, las mismas expectativas se ejecutan sobre distintas
 * implementaciones. Si ambas suites pasan, obtenemos evidencia de que
 * ambas implementaciones satisfacen el comportamiento comprobado por estos
 * tests.
 *
 * Esto no demuestra que las implementaciones sean internamente iguales ni
 * que el contrato se satisfaga para todos los casos posibles: el testing
 * ejecuta casos concretos y entrega evidencia sobre ellos, no una
 * demostración general de ausencia de errores.
 *
 * ==Fixture e independencia entre tests==
 *
 * La variable [[list]] forma parte del '''fixture''' de prueba: el estado
 * necesario para ejecutar cada caso.
 *
 * Antes de cada test, [[beforeEach]] reemplaza esa lista por una instancia
 * nueva creada mediante [[makeList]]. Esto evita la '''contaminación entre
 * tests''': las modificaciones realizadas por un caso no afectan el estado
 * inicial de los siguientes (Scalameta, n.d.).
 *
 * Por ejemplo, aunque un test agregue o elimine elementos, el siguiente
 * vuelve a comenzar con una lista nueva y vacía. Así, el resultado de un
 * test no debería depender del orden en que MUnit ejecute la suite.
 *
 * ==Qué comprueba esta suite==
 *
 * Los casos cubren distintos aspectos observables del contrato:
 *
 *   - estado inicial de una lista nueva;
 *   - inserción en distintas posiciones;
 *   - inserción al final;
 *   - inserción de múltiples elementos;
 *   - búsqueda de elementos presentes y ausentes;
 *   - eliminación desde distintas posiciones;
 *   - comportamiento frente a índices inválidos.
 *
 * Los métodos `assert...` agrupan las aserciones correspondientes a cada
 * uno de estos comportamientos. Las aserciones actúan como oráculos de
 * prueba: comparan el resultado observado con el esperado.
 *
 * Las propiedades específicas de una representación concreta no pertenecen
 * a esta suite. Por ejemplo, el crecimiento del arreglo de [[ArrayList]]
 * debe probarse en [[ArrayListTest]], mientras que la consistencia de los
 * enlaces `next` y `prev` corresponde a [[LinkedListTest]].
 *
 * ==Referencias==
 *
 * Scalameta. (n.d.). ''Using fixtures''. MUnit.
 * https://scalameta.org/munit/docs/fixtures.html
 *
 * @see
 *   [[ArrayListTest]] para pruebas específicas de la representación basada
 *   en un arreglo.
 * @see
 *   [[LinkedListTest]] para pruebas específicas de la representación
 *   doblemente enlazada.
 */
abstract class AbstractMutableListTest extends munit.FunSuite:

  /**
   * Lista utilizada como fixture por el test que se está ejecutando.
   *
   * [[beforeEach]] reemplaza su valor antes de cada caso para evitar
   * compartir estado mutable entre tests.
   */
  var list: MutableList = makeList

  /**
   * Crea una instancia nueva de la implementación de [[MutableList]] que
   * debe someterse a esta suite de pruebas.
   *
   * Las subclases implementan este método para elegir la representación
   * concreta sin modificar los tests del contrato.
   *
   * @return
   *   una lista nueva, inicialmente vacía.
   */
  def makeList: MutableList

  /**
   * Prepara el fixture antes de cada test.
   *
   * Crear una lista nueva para cada caso mantiene los tests
   * independientes: ningún cambio realizado por un test queda disponible
   * para el siguiente (Scalameta, n.d.).
   *
   * @param context
   *   información proporcionada por MUnit sobre el test que está por
   *   ejecutarse.
   */
  override def beforeEach(context: BeforeEach): Unit =
    list = makeList

  /** Comprueba el comportamiento observable de una lista recién creada. */
  def assertEmpty(): Unit =
    assertEquals(list.getSize, 0)
    assertEquals(list.get(0), None)
    assertEquals(list.indexOf("missing"), -1)
    assertEquals(list.lastIndexOf("missing"), -1)

  /**
   * Comprueba inserciones al comienzo, al medio y al final de la lista.
   */
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

  /**
   * Comprueba que `add(value)` agregue el elemento al final de la lista.
   */
  def assertAppend(): Unit =
    list.add("a")
    list.add("b")

    assertEquals(list.getSize, 2)
    assertEquals(list.get(0), Some("a"))
    assertEquals(list.get(1), Some("b"))

  /**
   * Comprueba que `addAll` inserte todos los elementos desde la posición
   * indicada y preserve su orden.
   */
  def assertAddAll(): Unit =
    list.add("a")
    list.add("d")
    list.addAll(1, List[Any]("b", "c"))

    assertEquals(list.getSize, 4)
    assertEquals(list.get(0), Some("a"))
    assertEquals(list.get(1), Some("b"))
    assertEquals(list.get(2), Some("c"))
    assertEquals(list.get(3), Some("d"))

  /**
   * Comprueba las operaciones de búsqueda tanto para elementos presentes
   * como ausentes, incluyendo valores repetidos.
   */
  def assertSearch(): Unit =
    list.addAll(0, List[Any]("a", "x", "b", "x"))

    assert(list.contains("x"))
    assert(!list.contains("missing"))
    assertEquals(list.indexOf("x"), 1)
    assertEquals(list.lastIndexOf("x"), 3)
    assertEquals(list.indexOf("missing"), -1)
    assertEquals(list.lastIndexOf("missing"), -1)

  /**
   * Comprueba eliminaciones al comienzo, al medio y al final, verificando
   * que los elementos restantes conserven su orden relativo.
   */
  def assertRemoval(): Unit =
    list.addAll(0, List[Any]("a", "b", "c", "d", "e"))

    list.remove(0)
    list.remove(list.getSize - 1)
    list.remove(1)

    assertEquals(list.getSize, 2)
    assertEquals(list.get(0), Some("b"))
    assertEquals(list.get(1), Some("d"))

  /**
   * Comprueba que las operaciones con índices fuera del rango permitido no
   * modifiquen el contenido observable de la lista.
   *
   * Esta expectativa forma parte del contrato utilizado en este proyecto:
   * otras APIs podrían elegir estrategias distintas, como lanzar una
   * excepción.
   */
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
