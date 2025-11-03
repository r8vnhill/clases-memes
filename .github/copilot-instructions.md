# Instrucciones para Agentes de IA - CC3002

## Contexto del Proyecto

Este es un repositorio **educativo** con material complementario para el curso universitario CC3002 (Metodologías de Diseño y Programación) de la Universidad de Chile. Está dirigido a estudiantes undergraduate que vienen de Python y están aprendiendo OOP clásica en Scala por primera vez.

## Restricciones de Lenguaje y Conceptos

### Progresión Pedagógica (CRÍTICO)

Los estudiantes aprenden gradualmente, por lo que **NO USES** estos conceptos avanzados de Scala en módulos tempranos:

- ❌ `case class` / `sealed trait` / `enum`
- ❌ `final` modificador
- ❌ Pattern matching (`match`/`case`)
- ❌ For comprehensions
- ❌ Programación funcional pura (inmutabilidad estricta, monads)
- ❌ Implicits o given/using

**SÍ USA** conceptos clásicos de OOP:

- ✅ `class` / `trait` / `abstract class` / `object` (companion)
- ✅ Herencia simple con `extends`
- ✅ Polimorfismo y sobrescritura (`override`)
- ✅ Encapsulación (private, getters/setters explícitos)
- ✅ Constructores con parámetros

### Idioma del Código

- **Código en inglés**: nombres de clases, métodos, variables, strings
- **Comentarios y documentación en español**: explicaciones, docstrings, mensajes educativos
- **Ejemplo correcto**:
  ```scala
  class Point(val x: Int, val y: Int):
    /** Mueve el punto por un desplazamiento dado */
    def moveBy(dx: Int, dy: Int): Point = 
      new Point(x + dx, y + dy)
  ```

## Arquitectura Multi-Módulo

### Estructura SBT

El proyecto usa **subproyectos SBT** (uno por clase del semestre). Cada carpeta numerada es independiente:

```
01-static-typing/          # Clase 1: tipado estático
02-intro-oop/              # Clase 2: intro a OOP
03-testing/                # Clase 3: testing con MUnit
...
13-design-patterns-1/      # Clase 13: patrones Observer, State, etc.
15-design-patterns-2/      # Clase 15: patrones Singleton, Factory, etc.
17-visitor/                # Clase 17: patrón Visitor
```

**Lecciones faltantes** (12, 16, etc.) están en otro repo: https://github.com/dcc-cc3002/clases-2025-2/tree/main

### Organización del Código

```
<módulo>/
  src/
    main/scala/           # Código de ejemplo
      <paquete>/          # Organizado por concepto (geometry/, observer/, etc.)
    test/scala/           # Tests con MUnit
```

- Paquete base: `cl.uchile.dcc`
- Subpaquetes organizan conceptos: `geometry`, `observer`, `state`, `singleton`, etc.
- **No hay cátedras incluidas** (solo código de soporte)

## Comandos de Desarrollo

### Compilar y Ejecutar

```bash
# Compilar todo el proyecto
sbt compile

# Compilar un módulo específico
sbt "project testing" compile

# Ejecutar un módulo con @main
sbt "project designPatterns2" run

# Ver todos los entry points disponibles
sbt "project <nombre>" "show discoveredMainClasses"
```

### Testing

```bash
# Ejecutar todos los tests
sbt test

# Tests de un módulo
sbt "project testing" test

# Test específico
sbt "project testing" "testOnly cl.uchile.dcc.CalculatorTest"

# Test con patrón
sbt "project testing" "testOnly *Calculator*"
```

### Alias Útiles

- `sbt ci` → `;clean;compile;test` (integración continua)

## Convenciones de Código

### Testing con MUnit

Los tests extienden `munit.FunSuite` y usan:

```scala
class CalculatorTest extends munit.FunSuite:
  var calculator: Option[Calculator] = None

  override def beforeEach(context: BeforeEach): Unit =
    calculator = Some(new Calculator())

  test("descripción del test") {
    assertEquals(expected, actual, "mensaje de fallo opcional")
  }
```

**Nota**: Los tests incluyen documentación **solo con fines pedagógicos** (en producción no se documentan tests).

### Patrones de Diseño

Los patrones se implementan de forma **didáctica** para enseñar OOP clásica:

- **Observer**: Ver `13-design-patterns-1/src/main/scala/observer/`
  - Usa `Subject[T]` trait con `attach`/`detach`/`notifyObservers`
  - `BaseSubject` usa `mutable.LinkedHashSet` para mantener orden
  - Observers implementan `update(subject: Subject[T], notification: T)`
- **State**: Ver `13-design-patterns-1/src/main/scala/state/` y ejemplo completo en `14-tamagotchi-exercise/`
  - Estado abstracto define métodos que lanzan `InvalidTransitionException` por defecto
  - Estados concretos sobrescriben solo transiciones válidas
  - Controller mantiene referencia mutable al estado actual
  - Ejemplo Tamagotchi combina State + Observer para notificar transiciones
- **Singleton**: Ver `15-design-patterns-2/src/main/scala/singleton/DatabaseConnection.scala`
  - Usa `private` constructor + `object` companion
  - `var instance: Option[T]` con lazy initialization en `getInstance`
- **Factory**: Ver `15-design-patterns-2/src/main/scala/factory/`
  - Factory trait con método `make(): T`
  - Factories concretas encapsulan lógica de construcción
- **Visitor**: Ver `17-visitor/src/main/scala/`
  - Elementos tienen `accept(visitor: Visitor)`
  - Visitor define `visit*` para cada tipo concreto

### Estilo de Código

- **Indentación significativa** de Scala 3 (sin llaves)
- Scalafmt configurado (`.scalafmt.conf`): max 80 columnas (optimizado para slides), align enabled
- Companion objects para singletons/factories
- Inmutabilidad preferida pero **no forzada** (`val` over `var` cuando sea natural)
- Getters/setters explícitos cuando se necesita mutabilidad: `def state_=(s: T): Unit`

## Entry Points Ejecutables

Usa `@main def <nombre>(): Unit` para crear ejemplos ejecutables:

```scala
@main def visitorExample(): Unit =
  val transactions = List(new Purchase(100), new Refund(50))
  val feeCalc = new FeeCalculatorVisitor
  transactions.foreach(_.accept(feeCalc))
  println(s"Total fees: ${feeCalc.totalFee}")
```

## Manejo de Errores en Patrones

El proyecto usa excepciones para validar transiciones inválidas en State:

```scala
// En estado concreto
def invalidTransition(): Unit =
  throw new InvalidTransitionException("Transición no permitida")

// En controller
private def safeRun(op: () => Unit): Unit =
  try op()
  catch case e: InvalidTransitionException => println(e.getMessage)
```

Combinar con Observer permite notificar errores sin terminar la ejecución (ver `14-tamagotchi-exercise`).

## Dependencias y Herramientas

- **Scala**: 3.7.3 (última versión estable)
- **SBT**: 1.11.x
- **JDK**: Java 17+ (probado con Azul JDK 25)
- **Testing**: MUnit (todos los módulos) + JUnit 5 (solo `03-testing`)
- **IDE**: IntelliJ IDEA recomendado (plugin `sbt-ide-settings` configurado)

## Nivel de los Estudiantes

- **Background**: Algoritmos y estructuras de datos en Python (CC3001)
- **Primera vez con**: tipado estático, compiladores, IDEs profesionales
- **Mínimo común denominador**: notebooks de Jupyter
- **Objetivo**: Aprender OOP clásica con ejemplos prácticos y tests

## Qué NO Hacer

- ❌ Usar características avanzadas de Scala antes de tiempo (ver sección de restricciones)
- ❌ Asumir que es código de producción (tiene simplificaciones deliberadas)
- ❌ Referenciar contenido de las cátedras (no están en este repo)
- ❌ Documentar exhaustivamente (es material didáctico, no librería pública)

## Referencias Clave

- **build.sbt**: Configuración completa con comentarios (NO usar como referencia de producción)
- **README.md**: Instrucciones de uso y estructura general
- **docs/CC3002-programa-primavera-2021-transcripcion.md**: Programa histórico del curso
