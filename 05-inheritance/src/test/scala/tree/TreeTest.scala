package cl.uchile.dcc.tree

/**
 * Pruebas del contrato de comparación de [[Tree]].
 *
 * Los árboles se ordenan según la suma de sus nodos:
 *
 *   - `Some(-1)` indica que el árbol receptor tiene una suma menor;
 *   - `Some(0)` indica que ambos árboles tienen la misma suma;
 *   - `Some(1)` indica que el árbol receptor tiene una suma mayor;
 *   - `None` indica que el valor recibido no puede compararse con un
 *     árbol.
 *
 * ==Una limitación intencional==
 *
 * En este punto del curso, `Comparable` recibe un parámetro de tipo `Any`.
 * Como `Any` es el supertipo de todos los tipos de Scala, el compilador
 * permite llamadas con valores que no son árboles, por ejemplo:
 *
 * {{{
 * tree.compareTo("Emma")
 * }}}
 *
 * Por esta razón, nuestra implementación debe detectar durante la
 * ejecución si el valor recibido es realmente un `Tree`. Esto pierde una
 * de las ventajas del tipado estático: el error no puede descartarse
 * únicamente mediante el chequeo de tipos (Scala Documentation, n.d.-a).
 *
 * Este diseño es deliberadamente imperfecto. Más adelante veremos cómo los
 * parámetros de tipo permiten expresar abstracciones que conservan
 * información sobre los tipos con los que operan (Scala Documentation,
 * n.d.-b).
 *
 * Por ahora, interesa reconocer tanto la utilidad de `Comparable` como
 * mixin como la limitación que introduce el uso de `Any`.
 *
 * ==Referencias==
 *
 * Scala Documentation. (n.d.-a). ''A first look at types''. Scala 3 Book.
 * https://docs.scala-lang.org/scala3/book/first-look-at-types.html
 *
 * Scala Documentation. (n.d.-b). ''Generics''. Scala 3 Book.
 * https://docs.scala-lang.org/scala3/book/types-generics.html
 */
class TreeTest extends munit.FunSuite:

  test("los árboles se comparan por su suma natural"):
    val tree1: Tree = new Leaf(3)
    val tree2: Tree = new InternalNode(1, new Leaf(2), new Leaf(4))

    assertEquals(tree1.sum, 3)
    assertEquals(tree2.sum, 7)

    // 3 < 7, por lo que tree1 precede a tree2.
    assertEquals(tree1.compareTo(tree2), Some(-1))

    // 7 > 3, por lo que tree2 sucede a tree1.
    assertEquals(tree2.compareTo(tree1), Some(1))

  test(
      "Comparable acepta Any pero rechaza otros tipos en tiempo de ejecución"
  ):
    val tree: Tree = new Leaf(3)

    // Esta llamada compila porque String también es subtipo de Any.
    // La implementación debe detectar durante la ejecución que no recibió
    // otro Tree y representar ese resultado mediante None.
    assertEquals(tree.compareTo("Emma"), None)
