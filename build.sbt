/*
 * =====================================================================================
 * build.sbt — Archivo de configuración principal para un proyecto Scala con SBT.
 *
 * Este archivo define:
 *   - La versión de Scala y la organización (namespace base de los paquetes).
 *   - Configuración y dependencias comunes que se aplican a todos los subproyectos.
 *   - Subproyectos individuales (módulos), cada uno ubicado en su propia carpeta.
 *   - Un proyecto raíz (root) que agrupa a todos los subproyectos.
 *   - Comandos de conveniencia para simplificar el trabajo diario.
 *
 * Este proyecto está pensado como multi-módulo para el curso CC3002,
 * lo que permite separar los contenidos de cada clase en proyectos distintos.
 * =====================================================================================
 */

// -----------------------------------------------------------------------------
// Definiciones generales del proyecto
// -----------------------------------------------------------------------------

// Versión de Scala que usarán los subproyectos del curso
val scala3 = "3.7.3"

// Nombre de la organización (se usará como prefijo de paquetes y metadatos)
val orgId = "cl.uchile.dcc"
ThisBuild / organization := orgId

// Versión del proyecto completo (no confundir con la versión de Scala)
ThisBuild / version := "0.1.0"

// Establece la versión de Scala de manera global para todos los subproyectos
ThisBuild / scalaVersion := scala3

// -----------------------------------------------------------------------------
// Gestión de versiones de dependencias
// -----------------------------------------------------------------------------

// Aquí centralizamos las versiones de librerías externas. De esta forma,
// si queremos actualizar una dependencia, basta con cambiar el valor aquí.
lazy val V = new {
  val munit = "1.2.1" // versión de MUnit (framework de testing)
  val junit = "5.13.4" // versión estable de JUnit 5
}

// -----------------------------------------------------------------------------
// Configuración común para todos los subproyectos
// -----------------------------------------------------------------------------

// "commonSettings" agrupa ajustes que se aplicarán automáticamente
// a todos los subproyectos definidos en este build.
lazy val commonSettings = Seq(
    // Opciones para el compilador Scala. Sirven para mostrar advertencias útiles.
    scalacOptions ++= Seq(
        "-deprecation", // Advertir si usamos APIs obsoletas
        "-feature", // Advertir si usamos características avanzadas
        "-Wunused:imports,locals,privates,params", // Detectar código sin usar
        "-Wvalue-discard" // Advertir si se descartan valores sin usarlos
    ),

    // Ejecutar los tests en un proceso separado para aislarlos del compilador
    Test / fork := true,

    // Dependencia de MUnit, usada para escribir tests en Scala de forma simple
    libraryDependencies += "org.scalameta" %% "munit" % V.munit % Test,

    // Indica explícitamente a sbt que usaremos MUnit como framework de tests
    testFrameworks += new TestFramework("munit.Framework"),

    // Configuración para el IDE (ej. IntelliJ) que ayuda a reconocer el proyecto
    // Necesita el plugin sbt-ide-settings para funcionar correctamente (ver project/plugins.sbt)
    idePackagePrefix := Some(orgId)
)

// Aplica "commonSettings" automáticamente a todos los proyectos de este build
inThisBuild(commonSettings)

// -----------------------------------------------------------------------------
// Definición de subproyectos
// Cada subproyecto representa el código de una clase del curso
// -----------------------------------------------------------------------------

// Subproyecto correspondiente a la clase 01: tipado estático
lazy val staticTyping = project
  .in(file("01-static-typing")) // Carpeta donde vive este módulo
  .settings(
      name := "01-static-typing", // Nombre del proyecto
      moduleName := "static-typing" // Nombre del artefacto compilado
  )

// Subproyecto correspondiente a la clase 02: introducción a la OOP
lazy val introOOP = project
  .in(file("02-intro-oop"))
  .settings(
      name := "02-intro-oop",
      moduleName := "intro-oop"
  )

// Subproyecto correspondiente a la clase 03: testing
lazy val testing = project
  .in(file("03-testing"))
  .settings(
      name := "03-testing",
      moduleName := "testing",
      // Dependencia adicional: JUnit 5 (útil si queremos ejemplos de tests mixtos)
      libraryDependencies += "org.junit.jupiter" % "junit-jupiter-api" % V.junit % Test
  )

// Subproyecto correspondiente a la clase 04: Programming to Abstractions
lazy val programmingToAbstractions = project
  .in(file("04-programming-to-abstractions"))
  .settings(
      name := "04-programming-to-abstractions",
      moduleName := "programming-to-abstractions"
  )

// Subproyecto correspondiente a la clase 05: Inheritance and Abstract Classes
lazy val inheritance = project
  .in(file("05-inheritance"))
  .settings(
      name := "05-inheritance",
      moduleName := "inheritance"
  )

// Subproyecto correspondiente a la clase 06: Ejercicio - Reproductor multimedia
lazy val mediaPlayerExercise = project
  .in(file("06-media-player-exercise"))
  .settings(
      name := "media-player-exercise",
      moduleName := "media-player-exercise"
  )

// Subproyecto correspondiente a la clase 07: Sobrescritura, Sobrecarga y Búsqueda de Métodos
lazy val overridingOverloading = project
  .in(file("07-overriding-overloading"))
  .settings(
      name := "07-overriding-overloading",
      moduleName := "overriding-overloading"
  )

// Subproyecto correspondiente a la clase 08: Encapsulamiento y principio de Liskov
lazy val encapsulationAndLiskov = project
  .in(file("08-encapsulation-and-liskov"))
  .settings(
      name := "08-encapsulation-and-liskov",
      moduleName := "encapsulation-and-liskov"
  )

// Subproyecto correspondiente a la clase 09: Double Dispatch
lazy val doubleDispatch = project
  .in(file("09-double-dispatch"))
  .settings(
      name := "09-double-dispatch",
      moduleName := "double-dispatch"
  )

// Subproyecto correspondiente a la clase 10: Excepciones
lazy val exceptions = project
  .in(file("10-exceptions"))
  .settings(
      name := "10-exceptions",
      moduleName := "exceptions"
  )

// Subproyecto correspondiente a la clase 11: Polimorfismo revisitado
lazy val polymorphism = project
  .in(file("11-polymorphism"))
  .settings(
      name := "11-polymorphism",
      moduleName := "polymorphism"
  )

// Subproyecto correspondiente a la clase 13: Patrones de Diseño I: Adapter, Proxy, Observer y State
lazy val designPatterns1 = project
  .in(file("13-design-patterns-1"))
  .settings(
      name := "13-design-patterns-1",
      moduleName := "design-patterns-1"
  )

// Subproyecto correspondiente a la clase 14: Ejercicio Tamagotchi
lazy val tamagotchiExercise = project
  .in(file("14-tamagotchi-exercise"))
  .dependsOn(designPatterns1)
  .settings(
      name := "14-tamagotchi-exercise",
      moduleName := "tamagotchi-exercise"
  )

// Subproyecto correspondiente a la clase 15: Patrones de Diseño II - Template, Composite, Null, Factory, Singleton y Flyweight
lazy val designPatterns2 = project
  .in(file("15-design-patterns-2"))
  .settings(
      name := "15-design-patterns-2",
      moduleName := "design-patterns-2"
  )

// Subproyecto correspondiente a la clase 17: Patrones de Diseño III - Visitor
lazy val visitor = project
  .in(file("17-visitor"))
  .settings(
      name := "17-visitor",
      moduleName := "visitor"
  )

// -----------------------------------------------------------------------------
// Proyecto raíz
// -----------------------------------------------------------------------------

// "root" es un proyecto que no contiene código propio,
// pero agrupa a todos los subproyectos para compilar/testear en conjunto.
lazy val root = project
  .in(file("."))
  .aggregate(
      staticTyping,
      introOOP,
      testing,
      programmingToAbstractions,
      inheritance,
      overridingOverloading,
      mediaPlayerExercise,
      encapsulationAndLiskov,
      doubleDispatch,
      exceptions,
      polymorphism,
      designPatterns1,
      tamagotchiExercise,
      designPatterns2,
      visitor
  ) // Agrega los subproyectos
  .settings(
      name := "clases-memes",
      publish / skip := true // Evita que sbt intente publicar este módulo
  )

// -----------------------------------------------------------------------------
// Comandos de conveniencia
// -----------------------------------------------------------------------------

// Definimos un alias llamado "ci" que ejecuta una serie de comandos seguidos:
// limpia el proyecto, compila y corre los tests.
// Esto facilita repetir el flujo típico de integración continua.
addCommandAlias("ci", ";clean;compile;test")
