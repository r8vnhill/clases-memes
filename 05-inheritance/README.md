# 05 — Herencia y clases abstractas

En este módulo estudiaremos cómo relacionar clases que comparten un contrato y parte de su implementación.

Partiremos de dos listas con representaciones muy distintas: una respaldada por un arreglo y otra construida con nodos
enlazados. Ambas deben ofrecer las mismas operaciones, pero algunas pueden implementarse exactamente de la misma forma.
Esto nos llevará a introducir una **clase abstracta** para compartir esa implementación sin perder las diferencias entre
ambas representaciones.

La progresión principal es:

```text
SimpleList / MutableList
        ↓
ArrayList y LinkedList
        ↓
implementación repetida
        ↓
AbstractMutableList
        ↓
herencia, especialización y refinamiento
        ↓
mixins y composición
        ↓
constructores
```

---

## Qué aprenderás

Al terminar este módulo deberías poder:

- distinguir un **contrato** (`trait`), una **implementación parcial compartida** (`abstract class`) y una
  **implementación concreta** (`class`);
- reconocer que `extends` establece una relación de subtipado y, entre clases, también permite heredar implementación;
- identificar comportamiento común que puede factorizarse en una superclase;
- comparar dos representaciones que satisfacen el mismo contrato observable;
- distinguir la interfaz pública de una abstracción de sus detalles de representación;
- reconocer un _mixin_ como una capacidad adicional que puede atravesar distintas jerarquías;
- identificar composición como una relación fuerte entre un todo y sus partes;
- reconocer limitaciones de diseño que todavía no podemos resolver correctamente sin genéricos.

---

## Antes de empezar

Conviene conocer:

- `class`;
- `trait`;
- `extends`;
- métodos;
- `Option`;
- arreglos y estructuras enlazadas básicas;
- testing básico con MUnit.

No necesitas conocer todavía sobrescritura, sobrecarga, genéricos, pattern matching, modificadores de visibilidad ni
programación funcional.

---

## Tres roles distintos

En este módulo aparecen tres construcciones con responsabilidades distintas:

| Construcción     | Rol en nuestros ejemplos                                                            |
| ---------------- | ----------------------------------------------------------------------------------- |
| `trait`          | Define un **contrato**: qué operaciones debe ofrecer un objeto.                     |
| `abstract class` | Proporciona **implementación común**, pero puede dejar operaciones sin implementar. |
| `class`          | Completa la implementación y permite crear objetos.                                 |

Scala permite que los traits también contengan implementación concreta; sin embargo, **en este curso los usaremos por
ahora solo como contratos**, para mantener separadas las ideas que estamos estudiando. Esta es una decisión pedagógica
del curso, no una limitación del lenguaje: Scala admite miembros abstractos y concretos en traits (Scala Documentation,
n.d.-a).

---

## De un contrato a distintas representaciones

`SimpleList` define las operaciones básicas de consulta:

```scala
trait SimpleList:
  def get(index: Int): Option[Any]
  def contains(value: Any): Boolean
  def indexOf(value: Any): Int
  def getSize: Int
  def lastIndexOf(value: Any): Int
```

`MutableList` especializa ese contrato agregando operaciones que modifican la colección:

```scala
trait MutableList extends SimpleList:
  def add(index: Int, value: Any): Unit
  def add(value: Any): Unit
  def addAll(index: Int, values: List[Any]): Unit
  def remove(index: Int): Unit
```

Por lo tanto:

```text
MutableList <: SimpleList
```

Toda `MutableList` puede utilizarse donde se espera una `SimpleList`, pero agrega operaciones que no forman parte del
contrato más general.

`ArrayList` y `LinkedList` satisfacen ese mismo contrato utilizando representaciones distintas:

```text
ArrayList                 LinkedList

[a][b][c][ ][ ]           a <-> b <-> c
    arreglo                    nodos
```

Esto significa que un cliente puede trabajar con:

```scala
val list: MutableList = new ArrayList
```

o:

```scala
val list: MutableList = new LinkedList
```

sin necesitar conocer cómo se almacenan internamente los elementos.

---

## ¿Por qué aparece `AbstractMutableList`?

Las representaciones son distintas, pero no **todas** las operaciones dependen de esa diferencia.

Por ejemplo, tanto en `ArrayList` como en `LinkedList`, agregar un valor al final puede expresarse como:

```scala
override def add(value: Any): Unit =
  add(getSize, value)
```

Del mismo modo, `addAll` puede expresarse utilizando únicamente operaciones del contrato.

Si copiáramos estas implementaciones en ambas clases tendríamos:

```text
ArrayList                  LinkedList
    │                           │
    ├── add(value)              ├── add(value)
    └── addAll(...)             └── addAll(...)
          ↑                           ↑
          └──── misma implementación ┘
```

`AbstractMutableList` concentra ese comportamiento común:

```text
       MutableList
            ↑
   AbstractMutableList
      ↙           ↘
ArrayList       LinkedList
```

La clase abstracta puede implementar operaciones completas utilizando otras operaciones que todavía permanecen
abstractas. Por ejemplo:

```text
add(value)
    ↓
getSize + add(index, value)

addAll(index, values)
    ↓
add(index, value)
```

Esta combinación de un contrato con una clase abstracta que proporciona una implementación parcial reutilizable se
conoce habitualmente como una **implementación esquelética** (_skeletal implementation_) (Bloch, 2018, Item 20).

La reutilización es útil, pero no basta por sí sola para justificar una relación de herencia: la superclase también debe
representar una abstracción coherente y más general que sus subclases.

---

## Mapa de ejemplos

| Archivo                                                                                         | Concepto                      | Qué observar                                                                                        |
| ----------------------------------------------------------------------------------------------- | ----------------------------- | --------------------------------------------------------------------------------------------------- |
| [`collections/SimpleList.scala`](src/main/scala/collections/SimpleList.scala)                   | Contratos                     | `SimpleList` define consultas; `MutableList` especializa el contrato agregando mutaciones.          |
| [`collections/AbstractMutableList.scala`](src/main/scala/collections/AbstractMutableList.scala) | Implementación compartida     | Algunos métodos pueden implementarse sin conocer la representación concreta.                        |
| [`collections/ArrayList.scala`](src/main/scala/collections/ArrayList.scala)                     | Implementación concreta       | Diferencia entre tamaño y capacidad; redimensionamiento y desplazamiento de elementos.              |
| [`collections/LinkedList.scala`](src/main/scala/collections/LinkedList.scala)                   | Implementación concreta       | Nodos, extremos y enlaces `prev` / `next`.                                                          |
| [`geometry/Point.scala`](src/main/scala/geometry/Point.scala)                                   | Extensión y refinamiento      | `ColorPoint` hereda, agrega `color` y refina `display`.                                             |
| [`mixins/Printable.scala`](src/main/scala/mixins/Printable.scala)                               | Mixin                         | Una capacidad adicional puede aparecer en clases pertenecientes a jerarquías distintas.             |
| [`tree/`](src/main/scala/tree/)                                                                 | Limitación previa a genéricos | `Comparable` utiliza `Any` porque todavía no podemos parametrizar el contrato por el tipo aceptado. |
| [`car/Car.scala`](src/main/scala/car/Car.scala)                                                 | Composición                   | `Car` crea y posee su `Engine`.                                                                     |
| [`people/AbstractPerson.scala`](src/main/scala/people/AbstractPerson.scala)                     | Constructores                 | Delegación mediante `this(...)` e inicialización de superclases.                                    |
| [`MutableListTest.scala`](src/test/scala/collections/MutableListTest.scala)                     | Contrato observable           | Las mismas expectativas se aplican a ambas implementaciones.                                        |
| [`ArrayListTest.scala`](src/test/scala/collections/ArrayListTest.scala)                         | Invariantes de representación | Capacidad y desplazamiento del arreglo.                                                             |
| [`LinkedListTest.scala`](src/test/scala/collections/LinkedListTest.scala)                       | Invariantes de representación | Coherencia entre `first`, `last`, `prev` y `next`.                                                  |

---

## Contrato observable de las listas

`SimpleList` es la abstracción definida por este módulo. No debe confundirse con `List` de la biblioteca estándar de
Scala.

Las colecciones estándar sí pueden aparecer como datos auxiliares. Por ejemplo:

```scala
list.addAll(2, List[Any]("a", "b"))
```

utiliza la `List` provista por Scala para entregar varios valores a nuestra propia lista.

| Operación            | Comportamiento esperado                                |
| -------------------- | ------------------------------------------------------ |
| `get(i)`             | `Some(value)` si `0 <= i < size`; `None` en otro caso. |
| `add(i, value)`      | Inserta cuando `0 <= i <= size`.                       |
| `add(value)`         | Agrega al final.                                       |
| `addAll(i, values)`  | Inserta los valores conservando su orden original.     |
| `contains(value)`    | Indica si existe al menos una aparición.               |
| `indexOf(value)`     | Primera posición o `-1` si no existe.                  |
| `lastIndexOf(value)` | Última posición o `-1` si no existe.                   |
| `remove(i)`          | Elimina el elemento en `i`.                            |
| `getSize`            | Cantidad lógica de elementos almacenados.              |

Observa una diferencia importante:

```text
get(i)         requiere 0 <= i < size
add(i, value)  permite   0 <= i <= size
```

`add(size, value)` es precisamente una inserción al final.

Para las mutaciones con índices inválidos usamos una política deliberadamente sencilla: se informa el problema y la
estructura queda sin cambios. No introducimos excepciones todavía porque no son el concepto que queremos estudiar en
este módulo.

---

## Contrato observable vs. representación

Los tests distinguen dos preguntas diferentes.

`MutableListTest` pregunta:

> ¿Observa un cliente el comportamiento prometido por `MutableList`?

Por eso sus pruebas trabajan solamente con valores de tipo:

```scala
MutableList
```

y pueden ejecutarse tanto sobre `ArrayList` como sobre `LinkedList`.

En cambio, `ArrayListTest` y `LinkedListTest` conocen deliberadamente detalles de cada representación.

Para `ArrayList` verificamos, entre otras cosas:

```text
size != data.length

size         → elementos lógicos
data.length  → capacidad disponible
```

Para `LinkedList` verificamos invariantes como:

```text
lista vacía:
  first == None
  last  == None

lista no vacía:
  first.prev == None
  last.next   == None

nodos vecinos:
  a.next == Some(b)
  b.prev == Some(a)
```

Dos implementaciones pueden tener estructuras internas completamente diferentes y, aun así, cumplir el mismo contrato
observable.

---

## Herencia y refinamiento

Cuando una clase extiende otra aparecen dos relaciones relacionadas, pero conceptualmente distintas:

```text
class A extends B

A hereda implementación de B
A es subtipo de B
```

En `Point.scala`, `ColorPoint` no solo agrega un nuevo dato:

```scala
val color: Color
```

también refina un comportamiento heredado:

```scala
override def display(screen: Screen): Unit =
  super.display(screen)
  println(s"Color: $color")
```

Para este ejemplo basta entender `super.display(...)` como:

> ejecuta la implementación heredada de `display` antes de agregar el comportamiento de `ColorPoint`.

No estudiaremos todavía casos más complejos de resolución de `super`.

---

## Mixin: una capacidad adicional

En este módulo usamos _mixin_ en un sentido deliberadamente acotado: un trait representa una **capacidad adicional** que
una clase puede incorporar además de su jerarquía principal.

Por ejemplo:

```text
Student  <: Person
Student  <: Printable

Teacher  <: Person

Document <: Printable
```

`Student` y `Document` no pertenecen a la misma jerarquía principal, pero ambos pueden ofrecer la capacidad `Printable`.

La pregunta importante es:

> ¿Por qué `Printable` no debería agregarse a `Person` solamente para que `Student` la herede?

Porque ser una persona y ser imprimible representan relaciones distintas. La capacidad `Printable` puede aparecer
también en tipos que no son personas.

Scala utiliza traits como una de sus herramientas principales para componer comportamiento; en este curso, por ahora,
limitamos esa composición a **contratos sin implementación** (Scala Documentation, n.d.-a).

---

## `Comparable`: una limitación intencional

En `tree/` aparece este contrato:

```scala
trait Comparable:
  def compareTo(other: Any): Option[Int]
```

`Any` es el supertipo de todos los tipos de Scala, por lo que valores muy diferentes pueden utilizarse donde se espera
un `Any` (Scala Documentation, n.d.-b).

Eso permite escribir:

```scala
tree.compareTo("Emma")
```

aunque conceptualmente queramos comparar un árbol únicamente con otro árbol.

Nuestra implementación debe entonces comprobar el tipo recibido **durante la ejecución** y retornar `None` cuando la
comparación no tiene sentido.

Esto es una limitación deliberada del ejemplo. Queremos construir una capacidad de comparación reutilizable, pero
todavía no conocemos una forma de decir:

> este `Comparable` compara específicamente valores de cierto tipo.

Los parámetros de tipo permiten parametrizar clases y traits con otro tipo y son la herramienta que utilizaremos más
adelante para expresar esta relación con mayor precisión (Scala Documentation, n.d.-c).

Por ahora, la comparación entre árboles utiliza su suma natural:

```text
suma menor  → Some(-1)
suma igual  → Some(0)
suma mayor  → Some(1)
otro tipo   → None
```

No interpretes el uso de `Any` y la comprobación de tipos en tiempo de ejecución como una recomendación para código de
producción: son precisamente la **limitación que queremos poder reconocer**.

---

## Composición y asociación

Una asociación expresa que objetos mantienen alguna relación entre sí. La **composición** representa una relación
parte–todo más fuerte; en UML se representa mediante un rombo negro en el extremo correspondiente al todo (Fowler, 2004,
Chapter 5). La distinción es especialmente relevante cuando queremos expresar propiedad fuerte de las partes.

Tenemos dos ejemplos útiles.

### `Car` y `Engine`

```text
Car ◆──── Engine
todo       parte
```

`Car` crea internamente su `Engine`:

```scala
class Car:
  val engine = new Engine
```

Esto hace visible la intención de que el motor pertenece al auto en el modelo del ejemplo.

### `LinkedList` y `Node`

```text
LinkedList ◆──── Node
    todo           parte
```

Los nodos existen como parte de la representación interna de la lista y sus enlaces deben mantenerse coherentes con
ella.

En cambio, una relación como `Library`–`Book` puede modelarse mediante asociación cuando ambos objetos pueden tener
existencia independiente.

---

## Constructores

En `AbstractPerson.scala` aparecen dos mecanismos que conviene distinguir.

Un constructor auxiliar utiliza `this(...)` para delegar en otro constructor de **la misma clase**:

```text
this(...)
    ↓
otro constructor de la misma clase
```

Scala define los constructores auxiliares mediante métodos llamados `this`, y su cadena de delegación debe terminar
alcanzando el constructor primario (Scala Documentation, n.d.-d).

La herencia introduce además una cadena de inicialización:

```text
constructor de la subclase
        ↓
inicialización de la superclase
        ↓
inicialización de la subclase
```

Los constructores participan en este proceso, pero **no se heredan como métodos normales**; la especificación de Scala
lo establece explícitamente (Scala Documentation, n.d.-d).

---

## Recorrido recomendado

1. Lee `SimpleList.scala` e identifica qué operaciones pertenecen a `SimpleList` y cuáles aparecen recién en
   `MutableList`.
2. Compara `ArrayList.scala` y `LinkedList.scala`: identifica qué partes necesariamente dependen de sus
   representaciones.
3. Lee `AbstractMutableList.scala` y explica por qué `add(value)` y `addAll(...)` pueden compartirse.
4. Ejecuta `MutableListTest`: observa que las mismas expectativas se aplican a ambas clases concretas.
5. Revisa `ArrayListTest` y `LinkedListTest`: ahora identifica qué tests dependen de detalles particulares de
   representación.
6. Revisa `Point.scala` para observar herencia, extensión y refinamiento.
7. Revisa `Printable.scala` para estudiar una capacidad que atraviesa jerarquías.
8. Revisa `Car.scala` y compara composición con una asociación común.
9. Revisa `AbstractPerson.scala` para seguir las cadenas de construcción.
10. Termina con `tree/`: intenta explicar exactamente qué información de tipos falta en nuestro `Comparable`.

---

## Simplificaciones deliberadas

Este módulo contiene varias decisiones que privilegian la claridad pedagógica por sobre un diseño de producción:

- La jerarquía usa `Any` en lugar de `SimpleList[T]`.
- `Any` permite almacenar valores de tipos diferentes, pero pierde información que el compilador podría utilizar para
  detectar errores. `Any` es el tipo superior de la jerarquía de Scala (Scala Documentation, n.d.-b).
- `ArrayList` deja visibles `size` y `data` para poder observar su representación.
- `LinkedList` deja visibles `first`, `last`, sus nodos y sus enlaces.
- No utilizamos modificadores de visibilidad todavía.
- Las operaciones inválidas imprimen un mensaje en vez de introducir excepciones.
- `LinkedList` prioriza una implementación sencilla y explícita por sobre optimizaciones de recorrido.
- `Comparable` acepta `Any` y utiliza `Option[Int]` para hacer visible una limitación previa a genéricos.
- Los traits se mantienen como contratos, aunque Scala permite que también contengan implementación.
- La implementación compartida se concentra en `AbstractMutableList`.
- Estudiaremos solamente herencia simple de clases en este tramo del curso.

Quedan fuera de alcance de este módulo:

```text
genéricos
implementación en traits
linearización de traits
herencia múltiple de implementación
encapsulación mediante modificadores de visibilidad
un Comparable con mayor seguridad estática
```

---

## Errores comunes

- Confundir `SimpleList` con `List` de la biblioteca estándar.
- Pensar que `size` y `data.length` representan lo mismo.
- Permitir `add(size + 1, value)`: la posición inmediatamente posterior al último elemento es exactamente `size`.
- Desplazar el arreglo en la dirección incorrecta y sobrescribir datos antes de copiarlos.
- Actualizar solo uno de los enlaces de dos nodos vecinos.
- Olvidar actualizar `first` o `last` al eliminar los extremos de `LinkedList`.
- Pensar que una clase abstracta debe implementar todos sus métodos.
- Pensar que reutilizar código es, por sí solo, una razón suficiente para crear una relación de herencia.
- Pensar que los constructores se heredan.
- Interpretar `tree.compareTo("Emma")` como un buen diseño en lugar de como una limitación intencional.

---

## Cómo ejecutarlo

Desde la raíz del repositorio:

```bash
sbt "project inheritance" compile
sbt "project inheritance" test
```

Para descubrir los ejemplos ejecutables:

```bash
sbt "project inheritance" "show discoveredMainClasses"
```

---

## Para explorar

- Cambia la capacidad inicial de `ArrayList` y predice en qué inserción deberá crecer el arreglo antes de ejecutar el
  código.
- Inserta un elemento en medio de un `ArrayList` y dibuja qué posiciones deben copiarse y en qué orden.
- Dibuja los enlaces de un `LinkedList` antes y después de eliminar el primer, último y único nodo.
- Agrega valores repetidos y predice el resultado de `indexOf` y `lastIndexOf`.
- Explica qué parte de `ColorPoint.display` es heredada y qué parte agrega la subclase.
- Propón otra capacidad que, como `Printable`, pueda pertenecer a clases de jerarquías distintas.
- Intenta diseñar **un único** `Comparable` reutilizable que permita a cada clase decidir qué tipo acepta, pero sin
  utilizar genéricos. Identifica exactamente dónde te faltan herramientas del lenguaje.

---

## Referencias

Bloch, J. (2018). _Effective Java_ (3rd ed., Item 20, “Prefer interfaces to abstract classes”). Addison-Wesley
Professional.

Fowler, M. (2004). _UML distilled: A brief guide to the standard object modeling language_ (3rd ed., Chapter 5, “Class
diagrams: Advanced concepts”). Addison-Wesley Professional. El capítulo 5 incluye específicamente agregación,
composición, interfaces y clases abstractas.

Scala Documentation. (n.d.-a). _OOP modeling_. _Scala 3 Book_.
[OOP Modeling — Scala 3 Book](https://docs.scala-lang.org/scala3/book/domain-modeling-oop.html)

Scala Documentation. (n.d.-b). _Unified types_. _Tour of Scala_.
[Unified Types — Tour of Scala](https://docs.scala-lang.org/tour/unified-types.html)

Scala Documentation. (n.d.-c). _Generics_. _Scala 3 Book_.
[Generics — Scala 3 Book](https://docs.scala-lang.org/scala3/book/types-generics.html)

Scala Documentation. (n.d.-d). _Classes & objects_. _Scala 3 language specification_.
[Classes & Objects — Scala 3 Specification](https://www.scala-lang.org/files/archive/spec/3.4/05-classes-and-objects.html)
