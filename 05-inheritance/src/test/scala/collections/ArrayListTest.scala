package cl.uchile.dcc.collections

/**
 * Pruebas específicas de la representación interna de [[ArrayList]].
 *
 * [[AbstractMutableListTest]] verifica el comportamiento que debe
 * satisfacer cualquier implementación de [[MutableList]]. Esta clase
 * complementa esas pruebas comprobando propiedades que dependen
 * específicamente de la representación elegida por [[ArrayList]]:
 * almacenar los elementos en un arreglo de capacidad finita.
 *
 * Una implementación puede imponer propiedades sobre sus datos internos
 * que deben mantenerse durante toda la vida del objeto. Estas propiedades
 * forman su '''invariante de representación''' (Miller & Goldman, 2022).
 *
 * Para [[ArrayList]], son relevantes propiedades como:
 *
 *   - `size` representa la cantidad de elementos almacenados;
 *   - siempre se cumple `0 <= size && size <= data.length`;
 *   - los primeros `size` espacios de `data` representan, en el mismo
 *     orden, los elementos de la lista;
 *   - insertar un elemento en una posición intermedia debe preservar el
 *     orden de los demás elementos;
 *   - eliminar un elemento debe cerrar el espacio que deja dentro del
 *     arreglo;
 *   - cuando el arreglo no tiene espacio para una nueva inserción, la
 *     implementación aumenta su capacidad sin perder los elementos
 *     existentes.
 *
 * Estas propiedades pertenecen a la '''implementación''' y no al contrato
 * general de [[MutableList]]. Por ejemplo, una lista enlazada puede
 * satisfacer exactamente el mismo contrato sin poseer capacidad ni
 * desplazar elementos dentro de un arreglo.
 *
 * El test de crecimiento inspecciona deliberadamente `data`, por lo que
 * está acoplado incluso a la estrategia concreta de crecimiento de esta
 * implementación. En particular, comprueba que una capacidad de 2 aumenta
 * a 4. Cambiar la estrategia de crecimiento podría hacer fallar este test
 * aunque el comportamiento público de [[MutableList]] continuara siendo
 * correcto.
 *
 * Los tests de inserción y eliminación, en cambio, comprueban mediante la
 * interfaz pública las consecuencias observables de operaciones que, dada
 * la representación de [[ArrayList]], requieren reorganizar los elementos
 * del arreglo.
 *
 * Separar estas pruebas de [[AbstractMutableListTest]] permite distinguir
 * dos preguntas diferentes: (1) ¿El objeto satisface el contrato de
 * [[MutableList]]? (2) ¿[[ArrayList]] mantiene correctamente las
 * decisiones propias de su representación?
 *
 * Esta separación ayuda a preservar la independencia de representación:
 * quienes utilizan el tipo abstracto no deberían depender de cómo se
 * almacenan sus valores, mientras que la implementación debe asegurar que
 * su representación concreta permanezca válida (Miller & Goldman, 2022).
 *
 * ==Referencias==
 *
 * Miller, R., & Goldman, M. (2022). ''Abstraction functions & rep
 * invariants'' (Reading 11). MIT 6.031: Software Construction,
 * Massachusetts Institute of Technology.
 * https://web.mit.edu/6.031/www/sp22/classes/11-abstraction-functions-rep-invariants/
 *
 * @see
 *   [[AbstractMutableListTest]] para las pruebas reutilizables del
 *   contrato de `MutableList`.
 */
class ArrayListTest extends AbstractMutableListTest:

  override def makeList: MutableList = new ArrayList(2)

  test("aumenta su capacidad cuando el arreglo está lleno"):
    val arrayList = new ArrayList(2)

    arrayList.add("Emma")
    arrayList.add("Norman")

    assertEquals(arrayList.data.length, 2)

    arrayList.add("Ray")

    assertEquals(arrayList.data.length, 4)
    assertEquals(arrayList.getSize, 3)

  test("desplaza elementos hacia la derecha al insertar"):
    val arrayList = new ArrayList(2)

    arrayList.add("Emma")
    arrayList.add("Ray")
    arrayList.add(1, "Norman")

    assertEquals(arrayList.get(0), Some("Emma"))
    assertEquals(arrayList.get(1), Some("Norman"))
    assertEquals(arrayList.get(2), Some("Ray"))

  test("desplaza elementos hacia la izquierda al eliminar"):
    val arrayList = new ArrayList(2)

    arrayList.addAll(
        0,
        List[Any]("Emma", "Norman", "Ray")
    )

    arrayList.remove(1)

    assertEquals(arrayList.getSize, 2)
    assertEquals(arrayList.get(0), Some("Emma"))
    assertEquals(arrayList.get(1), Some("Ray"))
