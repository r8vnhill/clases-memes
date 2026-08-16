# 01 — Tipado estático

Este módulo es una primera aproximación al **tipado estático en Scala**. Los ejemplos son deliberadamente pequeños: la
idea es observar qué información conoce el compilador, cómo se expresa mediante tipos y qué problemas puede detectar
antes de ejecutar el programa.

También compararemos algunas construcciones con Python para distinguir entre un lenguaje donde el chequeo de tipos forma
parte del proceso normal de compilación y otro donde las anotaciones de tipo son opcionales.

## Qué aprenderás

Al terminar este módulo deberías poder:

- identificar los tipos de los parámetros y del resultado de una función;
- interpretar una firma simple como `(Int, Int) => Boolean`;
- distinguir entre **chequeo estático** y **chequeo dinámico**;
- reconocer cuándo Scala puede **inferir un tipo** sin que lo escribamos explícitamente;
- distinguir el uso de `val` y `var`;
- comparar una solución con estado mutable con una variante recursiva;
- interpretar algunos mensajes básicos del compilador relacionados con tipos.

## Antes de empezar

Basta con manejar conceptos básicos de programación, como:

- variables;
- expresiones;
- condicionales;
- ciclos;
- funciones.

Los ejemplos comparativos asumen familiaridad básica con Python.

No necesitas conocer todavía programación orientada a objetos, testing, pattern matching avanzado ni programación
funcional.

## Recorrido del módulo

El módulo contiene estos ejemplos principales:

| Ejemplo                                               | Qué observar                                                                                |
| ----------------------------------------------------- | ------------------------------------------------------------------------------------------- |
| [`Equals.scala`](src/main/scala/Equals.scala)         | Tipos de parámetros y retorno, firmas de funciones y chequeo de tipos                       |
| [`equals.py`](src/main/python/equals.py)              | Una función equivalente en Python y el papel de sus anotaciones de tipo                     |
| [`Hailstones.scala`](src/main/scala/Hailstones.scala) | `var`, estado mutable, `while`, recursión y descarte explícito de un resultado con `: Unit` |

No necesitas entender cada detalle inmediatamente. Una buena estrategia es **leer, ejecutar, modificar y volver a
compilar** los ejemplos para observar qué acepta o rechaza el compilador.

---

## Ejemplo 1: tipos de una función

### `Equals.scala`

El archivo define una función con esta firma:

```scala
def equals(
    a: Int,
    b: Int
): Boolean
```

Podemos leerla como:

> `equals` recibe dos valores de tipo `Int` y produce un valor de tipo `Boolean`.

Otra forma de representar su tipo es:

```text
(Int, Int) => Boolean
```

Por ejemplo:

```scala
val toxicityCode = 3
val mezmerizeCode = 5

equals(toxicityCode, mezmerizeCode)
```

es una llamada compatible con la firma porque ambos argumentos tienen tipo `Int`.

En cambio:

```scala
val toxicityCode = 3
val albumName = "Mezmerize"

equals(toxicityCode, albumName)
```

no satisface la firma de `equals`: el segundo argumento tiene tipo `String`, pero la función requiere un `Int`.

Scala puede detectar esta incompatibilidad durante la compilación, antes de ejecutar el programa.

### El resultado de una función

El cuerpo de `equals` es:

```scala
println("equals called")
a == b
```

La llamada a `println` imprime un mensaje, mientras que:

```scala
a == b
```

produce un `Boolean`.

En Scala no es necesario escribir `return` en este caso: el valor de la última expresión del cuerpo determina el
resultado de la función.

Por lo tanto, una misma llamada puede tener dos aspectos observables:

1. **produce un efecto**, al imprimir `"equals called"`;
2. **produce un valor**, `true` o `false`.

Por ahora basta con reconocer esta diferencia; más adelante estudiaremos con mayor profundidad cómo los efectos y los
valores influyen en el diseño de programas.

---

## Comparación con Python

`src/main/python/equals.py` contiene una función equivalente:

```python
def equals(a: int, b: int) -> bool:
    ...
```

Las anotaciones:

```text
a: int
b: int
-> bool
```

expresan información sobre los tipos esperados.

Sin embargo, Python no hace cumplir estas anotaciones automáticamente durante la ejecución. Pueden ser utilizadas por
herramientas externas —por ejemplo, chequeadores estáticos e IDEs—, pero el runtime de Python no las utiliza como una
restricción obligatoria (Python Software Foundation, s. f.).

Esta diferencia es importante:

- en **Scala**, el chequeo de tipos forma parte del proceso normal de compilación;
- en **Python**, las anotaciones de tipo son opcionales y su chequeo requiere herramientas adicionales.

Esto no significa que Python no pueda analizarse estáticamente. La diferencia está en **cuándo y cómo se realiza ese
chequeo**.

---

## Inferencia de tipos

Usar tipado estático no significa que tengamos que escribir manualmente el tipo de cada valor.

Por ejemplo:

```scala
val band = "System of a Down"
val favoriteAlbum = "Toxicity"
val listeningSessions = 3
val currentlyPlaying = true
```

Aunque no escribimos los tipos explícitamente, Scala puede inferir:

```scala
val band: String = "System of a Down"
val favoriteAlbum: String = "Toxicity"
val listeningSessions: Int = 3
val currentlyPlaying: Boolean = true
```

El compilador sigue conociendo y comprobando esos tipos.

Scala combina, por lo tanto, **tipado estático** con **inferencia de tipos**: los tipos pueden estar presentes y ser
verificados aunque no siempre tengamos que escribirlos (Scala Documentation, s. f.-a).

En este módulo algunas anotaciones que Scala podría inferir se mantienen deliberadamente para hacer visible la
información que estamos estudiando.

---

## `val` y `var`

Scala distingue entre referencias que pueden reasignarse y referencias que no.

Por ejemplo:

```scala
val band = "System of a Down"
var currentAlbum = "Toxicity"

currentAlbum = "Mezmerize"
```

La reasignación de `currentAlbum` es válida porque fue declarada con `var`.

En cambio:

```scala
band = "Scars on Broadway"
```

no compila: `band` fue declarada con `val` y, por lo tanto, esa referencia no puede reasignarse.

En términos simples:

```text
val → no puede reasignarse
var → puede reasignarse
```

Scala recomienda preferir `val` cuando no necesitamos reasignar una referencia y utilizar `var` cuando el estado
realmente debe cambiar (Scala Documentation, s. f.-b).

Esta distinción será importante en el siguiente ejemplo.

---

## Ejemplo 2: estado mutable y recursión

### `Hailstones.scala`

Este archivo contiene dos implementaciones de una secuencia de Hailstone.

A partir de un entero positivo `n`, se aplican repetidamente estas reglas:

- si `n` es par, el siguiente valor es `n / 2`;
- si `n` es impar, el siguiente valor es `3 * n + 1`.

Por ejemplo, comenzando en `3`:

```text
3 → 10 → 5 → 16 → 8 → 4 → 2 → 1
```

Esta transformación está asociada a la **conjetura de Collatz**, que afirma que este proceso alcanza `1` para cualquier
entero positivo. Esta propiedad no ha sido demostrada en general (Lagarias, 1985).

En este módulo no intentaremos estudiar la conjetura: solo utilizaremos la secuencia como un ejemplo pequeño que permite
comparar dos formas de organizar un cálculo.

### `itHailstone`: mantener el estado con `var`

La primera implementación comienza con:

```scala
var r: Int = n
```

Durante la ejecución, `r` representa el término actual de la secuencia.

Por ejemplo:

```text
r = 3
r = 10
r = 5
r = 16
...
```

En cada iteración del `while`, el programa:

1. observa el valor actual;
2. calcula el siguiente;
3. reemplaza `r` por ese nuevo valor.

Podemos pensar en `r` como el **estado actual del cálculo**.

Esta implementación necesita `var` porque la referencia `r` se reasigna repetidamente.

### `recHailstone`: pasar el estado como argumento

La segunda implementación no mantiene el término actual en una variable mutable.

En cambio, cada llamada:

```scala
recHailstone(n)
```

recibe como argumento el término que debe procesar.

Cuando necesitamos continuar, hacemos una nueva llamada con el término siguiente:

```scala
recHailstone(n / 2)
```

o:

```scala
recHailstone(3 * n + 1)
```

La condición:

```scala
n == 1
```

es el **caso base**: cuando se cumple, la función deja de llamarse recursivamente.

Podemos resumir la diferencia así:

```text
itHailstone
    ↓
el término actual vive en una variable mutable

recHailstone
    ↓
el término actual se pasa como argumento
```

Ambas implementaciones describen esencialmente el mismo proceso, pero organizan de manera distinta la información
necesaria para continuar el cálculo.

Por ahora no buscamos concluir que una forma sea siempre mejor que la otra. El objetivo es reconocer ambas estrategias y
poder razonar sobre sus diferencias.

---

## ¿Qué significa `: Unit`?

El entry point del ejemplo termina con:

```scala
recHailstone(3): Unit
```

Esto puede resultar extraño al principio porque `recHailstone` retorna un `Int`:

```scala
def recHailstone(n: Int): Int
```

Sin embargo, en `hailstones` no nos interesa utilizar ese resultado. Solo queremos ejecutar la función y observar los
valores que imprime.

El entry point está declarado como:

```scala
@main def hailstones(): Unit =
```

`Unit` representa que esta función no entrega un resultado significativo a quien la invoca.

La expresión:

```scala
recHailstone(3): Unit
```

hace explícito que **queremos descartar el `Int` producido por `recHailstone`**.

No modifica la ejecución de `recHailstone` ni transforma el entero mediante una operación como `toDouble`. Simplemente
indica que su resultado no será utilizado.

Sin `: Unit`, el compilador puede advertir:

```text
discarded non-Unit value of type Int.
Add `: Unit` to discard silently.
```

El mensaje nos está indicando que calculamos un valor y luego lo ignoramos. Agregar `: Unit` hace explícito que ese
descarte es intencional.

Este es un pequeño ejemplo de cómo los tipos también pueden ayudarnos a hacer visibles ciertas decisiones del programa.

---

## Cómo ejecutar los ejemplos

Los siguientes comandos se ejecutan desde la raíz del repositorio.

### Compilar el módulo

```bash
sbt "project staticTyping" compile
```

Este comando selecciona el subproyecto `staticTyping` y comprueba que su código compile.

Compilar **no significa necesariamente ejecutar el programa**: el compilador analiza y traduce el código, pero el
ejemplo principal solo se ejecutará cuando se lo solicitemos.

### Consultar los entry points

Puedes consultar los programas ejecutables disponibles mediante:

```bash
sbt "project staticTyping" "show discoveredMainClasses"
```

Actualmente el módulo contiene:

```text
cl.uchile.dcc.hailstones
```

### Ejecutar Hailstone

```bash
sbt "project staticTyping" "runMain cl.uchile.dcc.hailstones"
```

El repositorio utiliza un build formado por múltiples subproyectos. sbt permite seleccionar uno de ellos mediante el
comando `project`; en este caso, su identificador es `staticTyping` (sbt, s. f.).

---

## Para explorar

Una buena forma de aprender qué información conoce el compilador es modificar los ejemplos y observar sus mensajes.

### 1. Provoca un error de tipos

Prueba:

```scala
val album = "Hypnotize"

equals(3, album)
```

Luego compila nuevamente.

- ¿Qué tipo esperaba `equals`?
- ¿Qué tipo recibió?
- ¿En qué momento se detecta el problema?
- ¿El programa llega a ejecutarse?

### 2. Observa la inferencia de tipos

Considera:

```scala
val band = "System of a Down"
val album = "Steal This Album!"
val repetitions = 4
```

- ¿Qué tipo infiere Scala para cada valor?
- ¿Necesitamos escribir explícitamente `String` o `Int`?
- Si no escribimos el tipo, ¿significa que el compilador deja de conocerlo?

Puedes comprobar tus hipótesis agregando anotaciones explícitas:

```scala
val album: String = "Steal This Album!"
```

### 3. Experimenta con `val` y `var`

En `itHailstone`, cambia:

```scala
var r: Int = n
```

por:

```scala
val r: Int = n
```

e intenta compilar.

- ¿Dónde aparece el primer problema?
- ¿Qué operación intenta realizar el programa?
- ¿Por qué la implementación actual necesita que `r` pueda reasignarse?

### 4. Compara las dos implementaciones

Ejecuta:

```scala
itHailstone(3)
recHailstone(3)
```

Observa:

- ¿producen la misma secuencia?;
- ¿dónde se mantiene el término actual en cada implementación?;
- ¿qué condición hace terminar cada una?;
- ¿cuál utiliza estado mutable?

### 5. Descarta un resultado

Elimina temporalmente `: Unit` de:

```scala
recHailstone(3): Unit
```

y vuelve a compilar.

- ¿Qué advertencia entrega el compilador?
- ¿Qué tipo retorna `recHailstone`?
- ¿Qué tipo debe retornar `hailstones`?
- ¿Qué decisión hacemos explícita al volver a escribir `: Unit`?

---

## Alcance educativo

Los ejemplos de este módulo priorizan **claridad y progresión pedagógica** por sobre generalidad, robustez o
rendimiento.

En particular:

- algunas firmas contienen anotaciones que Scala podría inferir;
- usamos ejemplos muy pequeños para que los tipos sean fáciles de identificar;
- las implementaciones de Hailstone suponen entradas positivas y no validan esa precondición;
- usamos `Int`, por lo que valores suficientemente grandes podrían exceder su rango;
- el ejemplo recursivo sirve para comparar dos formas de organizar el cálculo, no como una implementación general de la
  conjetura de Collatz.

Estas simplificaciones son intencionales. El objetivo es poder concentrarnos en **tipos, chequeo estático, inferencia y
mutabilidad** antes de introducir abstracciones más complejas.

## Referencias

Lagarias, J. C. (1985). The 3x + 1 problem and its generalizations. _The American Mathematical Monthly, 92_(1), 3–23.
[https://doi.org/10.1080/00029890.1985.11971528](https://doi.org/10.1080/00029890.1985.11971528)

Python Software Foundation. (s. f.). _typing — Support for type hints_. _Python 3 Documentation_.
[https://docs.python.org/3/library/typing.html](https://docs.python.org/3/library/typing.html)

Scala Documentation. (s. f.-a). _Why Scala 3?_ _Scala 3 Book_.
[https://docs.scala-lang.org/scala3/book/why-scala-3.html](https://docs.scala-lang.org/scala3/book/why-scala-3.html)

Scala Documentation. (s. f.-b). _Variables and data types_. _Scala 3 Book_.
[https://docs.scala-lang.org/scala3/book/taste-vars-data-types.html](https://docs.scala-lang.org/scala3/book/taste-vars-data-types.html)

sbt. (s. f.). _Multi-project builds_. _sbt Reference Manual_.
[https://www.scala-sbt.org/1.x/docs/Multi-Project.html](https://www.scala-sbt.org/1.x/docs/Multi-Project.html)
