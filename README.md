# CC3002 – Código Complementario
### Metodologías de Diseño y Programación

Universidad de Chile – Departamento de Ciencias de la Computación

[![License: CC BY 4.0](https://img.shields.io/badge/License-CC%20BY%204.0-555555.svg)](https://creativecommons.org/licenses/by/4.0/)
[![Scala](https://img.shields.io/badge/Scala-3.7.3-red?logo=scala)](https://www.scala-lang.org)
[![sbt](https://img.shields.io/badge/build-sbt-blue)](https://www.scala-sbt.org)
[![Tests](https://img.shields.io/badge/tests-MUnit%20%7C%20JUnit-green)](https://scalameta.org/munit/)
![Status](https://img.shields.io/badge/status-educational-7E57C2)

> [!NOTE]
> Este repositorio contiene ejemplos prácticos utilizados en el curso **CC3002 – Metodologías de Diseño y Programación**.  
> Cada módulo ilustra un concepto distinto, desde tipado estático y herencia hasta patrones de diseño y pruebas automatizadas.

---

## 📚 Contenido
- [CC3002 – Código Complementario](#cc3002--código-complementario)
    - [Metodologías de Diseño y Programación](#metodologías-de-diseño-y-programación)
  - [📚 Contenido](#-contenido)
  - [🧩 Descripción general](#-descripción-general)
  - [⚙️ Requisitos](#️-requisitos)
  - [🧠 Instalación y uso básico](#-instalación-y-uso-básico)
  - [▶️ Cómo ejecutar ejemplos (entry points)](#️-cómo-ejecutar-ejemplos-entry-points)
  - [🧰 Scripts y tareas definidas](#-scripts-y-tareas-definidas)
  - [🏗️ Estructura del proyecto](#️-estructura-del-proyecto)
  - [⚠️ Importante](#️-importante)
  - [🪪 Licencia](#-licencia)

---

## 🧩 Descripción general

**Lenguaje y stack:**
- Scala 3 (3.7.3) sobre SBT (1.11.x)
- JDK compatible con Scala 3.7 (Java 17+ recomendado)
- Framework de pruebas: MUnit (global) y JUnit 5 (solo en `03-testing`)
- Plugin: JetBrains `sbt-ide-settings`

**Estructura:**
- Proyecto multi-módulo: cada carpeta numerada es un subproyecto SBT.  
  El proyecto raíz agrega todos los subproyectos para compilar y testear en conjunto.

> [!TIP]
> Cada módulo es **autónomo y puede contener ejecutables**, permitiendo enfocarse en conceptos aislados (OOP, patrones, pruebas, etc.).  
> Puedes usar `sbt "project <nombre>" run` para explorar cada uno.

---

## ⚙️ Requisitos

- **Java:** 17 o superior (probado con Azul JDK 25)
- **SBT:** 1.11.x (probado localmente con 1.11.4)
- **Git**
- **IDE recomendado:** IntelliJ IDEA con plugin Scala (`sbt-ide-settings` incluido)

> [!NOTE]
> No se requieren variables de entorno para compilar o ejecutar los ejemplos.

---

## 🧠 Instalación y uso básico

1. **Clonar el repositorio**
   ```bash
   # HTTPS
   git clone https://github.com/r8vnhill/clases-memes.git
   # SSH
   git clone git@github.com:r8vnhill/clases-memes.git
   ```

2. **Cambiar a la rama del semestre**
   ```bash
   git checkout 2025/2
   ```
   > [!IMPORTANT]
   > El nombre de la rama usa **una barra (`/`)** en lugar de un guion.  
   > Ejemplo: `2025/2` → semestre Primavera 2025.

3. **Compilar**
   ```bash
   sbt compile
   sbt "project testing" compile
   ```

4. **Probar**
   ```bash
   sbt test
   sbt "project testing" test
   sbt "project testing" "testOnly cl.uchile.dcc.CalculatorTest"
   sbt "project testing" "testOnly *Calculator*"
   ```

---

## ▶️ Cómo ejecutar ejemplos (entry points)

Muchos módulos contienen funciones con anotación `@main` o aplicaciones simples de demostración.

```bash
# Seleccionar el módulo por ID y ejecutar
sbt "project designPatterns2" run

# Ejecutar un main específico (por FQCN)
sbt "project designPatterns2" "runMain <paquete.ClaseOMétodoMain>"

# Descubrir las clases con @main detectadas por SBT
sbt "project designPatterns2" "show discoveredMainClasses"
```

> [!NOTE]
> Algunos módulos imprimen resultados o trazas en consola; no requieren argumentos adicionales.

---

## 🧰 Scripts y tareas definidas

- **Alias SBT:**
    - `ci` → `;clean;compile;test`
- **Frameworks de prueba:**
    - MUnit (todos los módulos)
    - JUnit 5 (solo en `03-testing`)

> [!TIP]
> MUnit ofrece soporte nativo para **test filtering**, por lo que puedes ejecutar un subconjunto de pruebas fácilmente con `testOnly`.

---

## 🏗️ Estructura del proyecto

El proyecto raíz (`root`) agrega los siguientes módulos:

```
01-static-typing
02-intro-oop
03-testing
04-programming-to-abstractions
05-inheritance
06-media-player-exercise
07-overriding-overloading
08-encapsulation-and-liskov
09-double-dispatch
10-exceptions
11-polymorphism
13-design-patterns-1
14-tamagotchi-exercise
15-design-patterns-2
```

**Convenciones:**
- Paquete base: `cl.uchile.dcc`
- Scala 3 con indentación significativa
- Tests en `<módulo>/src/test/scala` extendiendo `munit.FunSuite`

---

## ⚠️ Importante

> [!WARNING]
> El `build.sbt` contiene configuraciones más avanzadas que las vistas en clase.  
> **No se recomienda usarlo como referencia directa para proyectos de producción.**

**Documentación:**
- La mayoría del código incluye comentarios breves, no documentación formal.
- No se usa formato Scaladoc de producción.

---

## 🪪 Licencia

> [!NOTE]
> Este material se distribuye bajo la licencia **Atribución 4.0 Internacional (CC BY 4.0)**.  
> Consulta el texto legal completo en [creativecommons.org/licenses/by/4.0](https://creativecommons.org/licenses/by/4.0/).

> [!IMPORTANT]
> El contenido tiene fines exclusivamente educativos y puede incluir **simplificaciones deliberadas** respecto a prácticas de producción.
