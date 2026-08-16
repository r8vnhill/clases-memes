# Instrucciones para Agentes de IA — CC3002

## Propósito

Este repositorio contiene material **educativo** de apoyo para el curso universitario **CC3002: Metodologías de Diseño y
Programación** de la Universidad de Chile.

El material está dirigido a estudiantes de **pregrado** que vienen de Python y están aprendiendo **programación
orientada a objetos clásica en Scala** por primera vez.

El objetivo de estas instrucciones es asegurar que cualquier contenido generado o modificado por un agente de IA:

- respete la **progresión pedagógica** del curso;
- use un nivel de complejidad adecuado para estudiantes principiantes en Scala;
- mantenga coherencia con el estilo y las convenciones del repositorio;
- no introduzca abstracciones o técnicas que el curso aún no ha enseñado.

---

## Prioridad de decisión

Cuando haya tensión entre dos criterios, usa este orden de prioridad:

1. **Respetar la progresión pedagógica del curso.**
2. **Favorecer claridad didáctica por sobre sofisticación técnica.**
3. **Mantener coherencia con el estilo ya presente en el repositorio.**
4. **Usar Scala idiomático solo cuando no choque con los puntos anteriores.**

En particular:

- no optimices un ejemplo a costa de volverlo menos enseñable;
- no introduzcas abstracciones avanzadas “porque son mejores” si el curso aún no las cubre;
- no trates este repositorio como si fuera una librería de producción.

### Regla estricta de lenguaje respetuoso

No uses lenguaje que pueda interpretarse como ofensivo, discriminatorio, capacitista o violento en código,
documentación, ejemplos, tests, nombres, mensajes o material educativo. Evita especialmente términos como **violación**,
**abortar**, **lista negra** y **sanity check**, incluso cuando se usen como metáforas técnicas. Prefiere alternativas
neutrales y precisas, por ejemplo: **incumplimiento**, **cancelar o detener**, **lista de bloqueo** y **verificación
básica**.

Esta regla también aplica al texto heredado que se reutilice o adapte: reemplaza expresiones problemáticas cuando no
sean necesarias para identificar literalmente una cita, una API externa o un término oficial que deba conservarse.

---

## Alcance del repositorio

Este repositorio contiene:

- código de ejemplo;
- ejercicios y material complementario;
- tests con propósito didáctico;
- implementaciones simplificadas de conceptos y patrones.

Este repositorio **no** contiene las cátedras del curso.\
Si falta material, parte de él puede estar en otro repositorio:

`https://github.com/dcc-cc3002/clases-2025-2/tree/main`

---

## Restricciones pedagógicas de Scala

### Regla principal

Los estudiantes aprenden de forma gradual.\
Por lo tanto, en módulos tempranos **no debes introducir** características avanzadas de Scala que aún no forman parte de
la progresión del curso.

### No usar en etapas tempranas

- `case class`
- `sealed trait`
- `enum`
- `final`
- pattern matching (`match` / `case`)
- for comprehensions
- programación funcional pura como foco principal
  - inmutabilidad estricta como dogma
  - monads
- `given` / `using`
- implicits

### Sí usar

Prioriza conceptos clásicos de OOP:

- `class`
- `trait`
- `abstract class`
- `object` y companions
- herencia simple con `extends`
- polimorfismo y sobrescritura con `override`
- encapsulamiento
- constructores con parámetros
- getters y setters explícitos cuando tengan valor pedagógico

### Criterio de simplificación

Prefiere siempre:

- ejemplos pequeños;
- nombres concretos y legibles;
- una idea nueva por vez;
- código que sirva para enseñar, no para impresionar.

---

## Idioma del código y la documentación

Usa esta convención de forma consistente:

- **Código en inglés**
  - nombres de clases;
  - nombres de métodos;
  - nombres de variables;
  - strings del ejemplo, cuando corresponda.
- **Comentarios y documentación en español**
  - explicaciones;
  - docstrings;
  - mensajes educativos;
  - instrucciones para estudiantes.

### Ejemplo

```scala
class Point(val x: Int, val y: Int):
  /** Mueve el punto por un desplazamiento dado */
  def moveBy(deltaX: Int, dy: Int): Point =
    new Point(x + deltaX, y + dy)
```

---

## Organización del proyecto

### Estructura general

El proyecto está organizado como una colección de **subproyectos SBT**, típicamente uno por clase o unidad temática del
semestre.

Cada carpeta numerada es independiente.

```text
01-static-typing/          # Clase 1: tipado estático
02-intro-oop/              # Clase 2: introducción a OOP
03-testing/                # Clase 3: testing con MUnit
...
05x-media-player-exercise/ # Material extra u opcional
12-design-patterns-1/      # Observer, State, etc.
14-design-patterns-2/      # Singleton, Factory, etc.
15-visitor/                # Visitor
```

### Estructura interna típica de un módulo

```text
<módulo>/
  src/
    main/scala/
      <paquete>/
    test/scala/
```

### Convenciones de paquetes

- paquete base: `cl.uchile.dcc`
- subpaquetes por concepto, por ejemplo:
  - `geometry`
  - `observer`
  - `state`
  - `singleton`

No asumas una arquitectura de producción.\
La organización existe para apoyar el aprendizaje.

---

## Comandos de desarrollo

### Compilar

```bash
# Compilar todo el proyecto
sbt compile

# Compilar un módulo específico
sbt "project testing" compile
```

### Ejecutar

```bash
# Ejecutar un módulo con @main
sbt "project designPatterns2" run

# Ver entry points disponibles
sbt "project <nombre>" "show discoveredMainClasses"
```

### Tests

```bash
# Ejecutar todos los tests
sbt test

# Tests de un módulo
sbt "project testing" test

# Test específico
sbt "project testing" "testOnly cl.uchile.dcc.CalculatorTest"

# Tests por patrón
sbt "project testing" "testOnly *Calculator*"
```

### Alias útil

- `sbt ci` → `;clean;compile;test`

---

## Convenciones de código

### Estilo general

- usa la sintaxis de **indentación significativa** de Scala 3;
- evita complejidad incidental;
- usa `val` por defecto cuando sea natural;
- usa `var` cuando tenga valor pedagógico o sea necesario para el ejemplo;
- usa companions cuando ayuden a enseñar patrones como Singleton o Factory;
- usa getters y setters explícitos cuando la mutabilidad sea parte de lo que se quiere enseñar.

### Formato

- el repositorio usa `scalafmt`;
- la configuración privilegia líneas cortas, pensadas también para slides;
- no introduzcas estilos de formato que contradigan lo ya usado en el proyecto.

---

## Testing

### Framework principal

Los tests usan principalmente **MUnit**.

Patrón esperado:

```scala
class CalculatorTest extends munit.FunSuite:
  var calculator: Option[Calculator] = None

  override def beforeEach(context: BeforeEach): Unit =
    calculator = Some(new Calculator())

  test("descripción del test"):
    assertEquals(expected, actual, "mensaje de fallo opcional")
```

### Criterio pedagógico

En este repositorio, los tests pueden incluir comentarios o explicaciones con fines didácticos.

No asumas estándares de una base de código industrial.\
Aquí los tests también enseñan.

---

## Entry points ejecutables

Usa `@main def ...` para crear ejemplos ejecutables cuando eso ayude a explorar un concepto.

Ejemplo:

```scala
@main def visitorExample(): Unit =
  val transactions = List(new Purchase(100), new Refund(50))
  val feeCalc = new FeeCalculatorVisitor
  transactions.foreach(_.accept(feeCalc))
  println(s"Total fees: ${feeCalc.totalFee}")
```

Prioriza entry points pequeños, autocontenidos y fáciles de correr en clase o en laboratorio.

---

## Implementación de patrones de diseño

Los patrones en este repositorio deben entenderse como implementaciones **didácticas**, no como plantillas de
producción.

### Observer

Referencia: `12-design-patterns-1/src/main/scala/observer/`

Características esperadas:

- `Subject[T]` como `trait`;
- operaciones como `attach`, `detach` y `notifyObservers`;
- `BaseSubject` puede usar `mutable.LinkedHashSet` para preservar orden;
- observers con un método del estilo: `update(subject: Subject[T], notification: T)`.

### State

Referencia: `12-design-patterns-1/src/main/scala/state/`\
Ejemplo más completo: `13-tamagotchi-exercise/`

Características esperadas:

- estado abstracto con comportamiento por defecto para transiciones inválidas;
- estados concretos que sobrescriben solo transiciones válidas;
- controlador con referencia mutable al estado actual;
- integración con Observer cuando ayude a mostrar cambios de estado.

### Singleton

Referencia: `14-design-patterns-2/src/main/scala/singleton/DatabaseConnection.scala`

Características esperadas:

- constructor `private`;
- `object` companion;
- inicialización diferida mediante `Option[T]` o mecanismo equivalente simple.

### Factory

Referencia: `14-design-patterns-2/src/main/scala/factory/`

Características esperadas:

- `trait` de fábrica con un método como `make(): T`;
- factorías concretas que encapsulan la lógica de construcción.

### Visitor

Referencia: `15-visitor/src/main/scala/`

Características esperadas:

- elementos con `accept(visitor: Visitor)`;
- visitor con un método `visit*` por tipo concreto.

---

## Manejo de errores

En algunos ejemplos, especialmente en State, se usan **excepciones** para representar transiciones inválidas.

Ejemplo:

```scala
def invalidTransition(): Unit =
  throw new InvalidTransitionException("Transición no permitida")
```

y luego:

```scala
private def safeRun(op: () => Unit): Unit =
  try op()
  catch case e: InvalidTransitionException => println(e.getMessage)
```

Esto está permitido porque sirve al objetivo pedagógico del ejemplo.

No reemplaces automáticamente este enfoque por alternativas más sofisticadas si eso complica la enseñanza.

---

## Dependencias y herramientas

- **Scala**: 3.7.3
- **SBT**: 1.11.x
- **JDK**: Java 17 o superior
- **Testing**:
  - MUnit en todos los módulos;
  - JUnit 5 solo en `03-testing`
- **IDE recomendado**: IntelliJ IDEA

---

## Perfil de estudiantes

Asume este perfil al generar o modificar contenido:

- vienen de cursos previos con Python;
- manejan algoritmos y estructuras de datos;
- están viendo por primera vez:
  - tipado estático;
  - compilación;
  - tooling profesional;
  - OOP en Scala;
- su punto de partida técnico puede ser relativamente básico.

### Implicancias prácticas

Por eso debes:

- evitar saltos conceptuales bruscos;
- explicar con ejemplos concretos;
- no asumir familiaridad con jerga de Scala;
- preferir una progresión incremental.

---

## Qué hacer cuando modifiques contenido

Si agregas o editas código, asegúrate de que:

- el ejemplo siga siendo coherente con el módulo donde vive;
- no introduzca conceptos aún no enseñados;
- los nombres sean claros;
- el ejemplo sea lo bastante pequeño como para discutirse en clase;
- el estilo coincida con el resto del repositorio.

Si agregas o editas tests, asegúrate de que:

- refuercen la idea pedagógica del módulo;
- no dependan de técnicas avanzadas innecesarias;
- usen MUnit salvo que el módulo ya indique otra cosa.

Si escribes documentación o comentarios, asegúrate de que:

- estén en español;
- expliquen decisiones o conceptos relevantes;
- no conviertan el repositorio en una librería sobredocumentada.

---

## Qué no hacer

- no introducir características avanzadas antes de tiempo;
- no tratar este repositorio como código de producción;
- no referenciar contenido de cátedra que no esté disponible aquí;
- no sobre-ingenierizar ejemplos;
- no reemplazar una solución simple y enseñable por una más sofisticada solo por ser más idiomática;
- no documentar exhaustivamente como si esto fuera una API pública industrial.

---

## Referencias internas útiles

Antes de hacer cambios, revisa especialmente:

- `build.sbt`
- `README.md`
- `docs/CC3002-programa-primavera-2021-transcripcion.md`

Úsalos para entender contexto y convenciones, no como plantillas de producción.

---

## Resumen operativo

Si tienes dudas, recuerda esta regla:

> En CC3002, la mejor solución no es la más avanzada, sino la que mejor enseña el concepto correcto en el momento
> correcto.
