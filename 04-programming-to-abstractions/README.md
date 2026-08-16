# 04 - Programming to Abstractions

Este módulo reúne ejemplos introductorios sobre cómo construir objetos, definir contratos con traits y usar polimorfismo
de subtipos en Scala.

## Temas cubiertos

- Constructores e inicialización
- Inicialización incompleta
- Constructores auxiliares y parámetros por defecto
- Traits como contratos
- Polimorfismo explícito en Scala
- Contraste con duck typing en Python
- Árboles binarios como abstracción con múltiples implementaciones

## Mapa de ejemplos

### Constructores e inicialización

- `src/main/scala/domain/people/Musician.scala` Muestra creación de objetos, acceso a campos y mutación de `var`.
- `src/main/scala/domain/people/Person.scala` Muestra lógica de inicialización en el cuerpo de la clase.
- `src/main/scala/domain/geometry/ColorPoint.scala` Muestra un caso de inicialización incompleta.
- `src/main/scala/domain/people/Student.scala` Muestra constructores auxiliares.
- `src/main/scala/domain/network/Socket.scala` Muestra parámetros por defecto y argumentos con nombre.

### Traits como contratos

- `src/main/scala/animals/Legged.scala`
- `src/main/scala/animals/Tailed.scala`
- `src/main/scala/animals/AkitaInu.scala`
- `src/main/scala/phone/SmartPhone.scala`

### Polimorfismo y abstracciones

- `src/main/scala/polymorphism/SubtypePolymorphism.scala` Contrasta subtipado explícito en Scala con clases que solo “se
  parecen”.
- `src/main/scala/tree/Tree.scala`
- `src/main/scala/tree/Leaf.scala`
- `src/main/scala/tree/InternalNode.scala` Muestran una abstracción `Tree` con implementaciones concretas distintas.

### Material complementario en otros lenguajes

- `src/main/c/musician.c` Paralelo en C entre reserva de memoria e inicialización.
- `src/main/python/polymorphism/subtype_polymorphism.py` Paralelo en Python para mostrar duck typing y el rol limitado
  de las anotaciones de tipo.

## Cómo compilar y listar ejemplos Scala

Desde la raíz del repo:

```bash
sbt "project programmingToAbstractions" compile
sbt "project programmingToAbstractions" "show discoveredMainClasses"
```

## Ejemplos recomendados

Desde la raíz del repo:

```bash
sbt "project programmingToAbstractions" "runMain cl.uchile.dcc.domain.people.musicianExample"
sbt "project programmingToAbstractions" "runMain cl.uchile.dcc.domain.people.personExample"
sbt "project programmingToAbstractions" "runMain cl.uchile.dcc.domain.network.socketExample"
sbt "project programmingToAbstractions" "runMain cl.uchile.dcc.polymorphism.subtypePolymorphismExample"
sbt "project programmingToAbstractions" "runMain cl.uchile.dcc.tree.treeExample"
```

También hay otros `@main` útiles como:

- `cl.uchile.dcc.animals.akitaInuExample`
- `cl.uchile.dcc.phone.smartphoneExample`
- `cl.uchile.dcc.domain.geometry.colorPointExample`
- `cl.uchile.dcc.trace.main`

## Ejemplos no Scala

### C

El ejemplo `src/main/c/musician.c` no forma parte del build SBT.

- Con compilador manual (`clang` o `gcc`), sigue las instrucciones del propio archivo.
- Con CMake, el módulo tiene un `CMakeLists.txt` aislado que no interfiere con SBT.

Ejemplo de flujo con CMake:

```bash
cmake -S 04-programming-to-abstractions -B 04-programming-to-abstractions/cmake-build
cmake --build 04-programming-to-abstractions/cmake-build
```

### Python

El ejemplo `src/main/python/polymorphism/subtype_polymorphism.py` tampoco forma parte del build SBT.

Se puede ejecutar directamente con:

```bash
python 04-programming-to-abstractions/src/main/python/polymorphism/subtype_polymorphism.py
```

## Nota

Este módulo está pensado como material didáctico pequeño y progresivo. Los ejemplos priorizan claridad conceptual por
sobre generalidad o robustez de producción.
