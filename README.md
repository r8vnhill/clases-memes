# CC3002 — Código complementario

## **Metodologías de Diseño y Programación**

### Universidad de Chile · Departamento de Ciencias de la Computación

[![License: CC BY 4.0](https://img.shields.io/badge/License-CC%20BY%204.0-555555.svg)](https://creativecommons.org/licenses/by/4.0/)
[![Scala](https://img.shields.io/badge/Scala-3.7.3-red?logo=scala)](https://www.scala-lang.org)
[![sbt](https://img.shields.io/badge/build-sbt-blue)](https://www.scala-sbt.org)
[![Tests](https://img.shields.io/badge/tests-MUnit%20%7C%20JUnit-green)](https://scalameta.org/munit/)
![Status](https://img.shields.io/badge/status-educational-7E57C2)

> [!NOTE]
> Este repositorio contiene **ejemplos, ejercicios y código complementario** de CC3002.
>
> Los ejemplos están pensados especialmente para quienes vienen de Python y están comenzando a trabajar con Scala,
> tipado estático, programación orientada a objetos y testing.

## ¿Qué encontrarás aquí?

El repositorio está organizado siguiendo aproximadamente la progresión del curso.

Cada carpeta numerada, como:

```text
01-static-typing/
02-intro-oop/
03-testing/
...
```

corresponde a un **subproyecto SBT** que contiene ejemplos relacionados con un tema.

Los ejemplos son deliberadamente pequeños. Su objetivo es hacer visibles conceptos y decisiones de diseño, no servir
como implementaciones de producción.

> [!IMPORTANT]
> Las slides y demás material oficial de las clases no necesariamente están incluidos en este repositorio. Este código
> debe entenderse como **material complementario**.

---

## Inicio rápido

Necesitas:

- un **JDK 17 o superior**;
- **SBT**;
- opcionalmente, **IntelliJ IDEA** o **VS Code con Metals**.

### Opción A — Clonar con Git

```bash
git clone https://github.com/dcc-cc3002/codigo-slides.git
cd codigo-slides
```

Luego cambia a la rama correspondiente a tu semestre:

```bash
git switch <año>/<semestre>
```

Por ejemplo:

```bash
git switch 2026/1
```

Finalmente, comprueba que el proyecto compile:

```bash
sbt compile
```

### Opción B — Descargar un ZIP

1. Selecciona la rama correspondiente a tu semestre en GitHub.
2. Presiona **Code → Download ZIP**.
3. Descomprime el archivo.
4. Abre una terminal en la carpeta raíz.
5. Ejecuta:

```bash
sbt compile
```

> [!TIP]
> Si usas IntelliJ IDEA o VS Code con Metals, abre **la carpeta raíz de este repositorio**, no una carpeta de módulo
> individual.
>
> Espera a que termine la importación de SBT antes de ejecutar ejemplos o tests.

---

## Cómo está organizado el proyecto

Este repositorio es un **build SBT con múltiples subproyectos**.

Por ejemplo, la carpeta:

```text
03-testing/
```

corresponde al proyecto SBT:

```text
testing
```

Por eso, para ejecutar sus tests usamos:

```bash
sbt "project testing" test
```

El nombre de la carpeta y el identificador de SBT **no tienen por qué ser iguales**.

Por ejemplo:

```text
Carpeta:       18-for-comprehensions/
Proyecto SBT:  forComprehensions
```

Por lo tanto:

```bash
sbt "project forComprehensions" compile
```

y no:

```bash
sbt "project 18-for-comprehensions" compile
```

---

## Ejecutar ejemplos y tests

### Compilar todo el repositorio

```bash
sbt compile
```

Esto comprueba que los módulos puedan compilar, pero no necesariamente ejecuta sus ejemplos.

### Compilar un módulo

```bash
sbt "project testing" compile
```

### Ejecutar los tests de un módulo

```bash
sbt "project testing" test
```

### Ejecutar todos los tests

```bash
sbt test
```

También existe el alias:

```bash
sbt ci
```

que ejecuta:

```text
clean → compile → test
```

---

## Ejecutar programas `@main`

Algunos módulos contienen funciones anotadas con `@main` que pueden ejecutarse como programas.

Para consultar los entry points disponibles en un módulo:

```bash
sbt "project forComprehensions" "show discoveredMainClasses"
```

Por ejemplo, si aparece:

```text
cl.uchile.dcc.forcomprehensions.basics.basicsRunner
```

puedes ejecutarlo con:

```bash
sbt "project forComprehensions" \
  "runMain cl.uchile.dcc.forcomprehensions.basics.basicsRunner"
```

> [!TIP]
> Si esperas encontrar un `@main` y no aparece:
>
> 1. comprueba que estás en la rama correcta del semestre;
> 2. ejecuta `sbt compile`;
> 3. vuelve a consultar `discoveredMainClasses`.

---

## Índice de módulos

|  Nº | Carpeta                                                              | Tema principal                                                 |
| --: | -------------------------------------------------------------------- | -------------------------------------------------------------- |
|  01 | [`01-static-typing/`](01-static-typing/)                             | Tipos, chequeo estático, inferencia y mutabilidad              |
|  02 | [`02-intro-oop/`](02-intro-oop/)                                     | Primera aproximación a objetos, responsabilidades e interfaces |
|  03 | [`03-testing/`](03-testing/)                                         | Testing automatizado con MUnit y JUnit                         |
|  04 | [`04-programming-to-abstractions/`](04-programming-to-abstractions/) | Programar contra abstracciones                                 |
|  05 | [`05-inheritance/`](05-inheritance/)                                 | Herencia y clases abstractas                                   |
| 05x | [`05x-media-player-exercise/`](05x-media-player-exercise/)           | Ejercicio complementario de OOP                                |
|  06 | [`06-overriding-overloading/`](06-overriding-overloading/)           | Overriding, sobrecarga y selección de métodos                  |
|  07 | [`07-encapsulation-and-liskov/`](07-encapsulation-and-liskov/)       | Encapsulación y sustitución de Liskov                          |
|  08 | [`08-double-dispatch/`](08-double-dispatch/)                         | Doble despacho                                                 |
|  09 | [`09-exceptions/`](09-exceptions/)                                   | Excepciones y manejo de errores                                |
|  10 | [`10-polymorphism/`](10-polymorphism/)                               | Polimorfismo                                                   |
|  12 | [`12-design-patterns-1/`](12-design-patterns-1/)                     | Primer conjunto de patrones de diseño                          |
|  13 | [`13-tamagotchi-exercise/`](13-tamagotchi-exercise/)                 | Ejercicio aplicado: Tamagotchi                                 |
|  14 | [`14-design-patterns-2/`](14-design-patterns-2/)                     | Segundo conjunto de patrones de diseño                         |
|  15 | [`15-visitor/`](15-visitor/)                                         | Patrón Visitor                                                 |
|  16 | [`16-expression-problem/`](16-expression-problem/)                   | Expression Problem y extensibilidad                            |
|  17 | [`17-intro-functional/`](17-intro-functional/)                       | Introducción a programación funcional                          |
|  18 | [`18-for-comprehensions/`](18-for-comprehensions/)                   | `for`, `map`, `flatMap` y `withFilter`                         |

> [!NOTE]
> La numeración sigue la organización del material docente, por lo que puede haber números que no correspondan a una
> carpeta de este repositorio.

---

## ¿Cómo estudiar los ejemplos?

Una forma útil de trabajar con este repositorio es:

1. **Lee el ejemplo** e intenta anticipar qué hará.
2. **Compílalo y ejecútalo**.
3. **Modifica algo pequeño**.
4. Vuelve a compilar.
5. Observa qué cambió o qué mensaje entrega el compilador.
6. Intenta explicar por qué ocurrió.

Por ejemplo, en los primeros módulos puedes experimentar cambiando tipos, reemplazando `var` por `val` o modificando los
argumentos de una función.

> [!TIP]
> Los errores del compilador también son parte del material de aprendizaje.
>
> Cuando un ejemplo deje de compilar después de modificarlo, intenta primero interpretar el mensaje antes de revertir el
> cambio.

---

## Sobre los ejemplos

Algunas decisiones del código son **simplificaciones pedagógicas intencionales**.

Esto significa que un ejemplo puede:

- omitir validaciones que serían necesarias en una aplicación real;
- utilizar una representación sencilla para hacer visible un concepto;
- repetir código temporalmente antes de introducir una abstracción;
- mostrar primero una solución que luego mejoraremos;
- evitar funcionalidades avanzadas de Scala aunque permitan escribir menos código.

En otras palabras:

> El objetivo no es mostrar desde el principio la solución más sofisticada, sino hacer visible **por qué una solución
> puede necesitar evolucionar**.

Si encuentras algo que parece mejorable, pregúntate primero si esa limitación puede ser precisamente parte del ejemplo.

---

## Trabajar con la rama correcta

El código puede cambiar entre semestres. Usa siempre la rama indicada para tu versión del curso.

Para actualizar las ramas conocidas por Git:

```bash
git fetch origin
```

Para cambiar de rama:

```bash
git switch <año>/<semestre>
```

Para actualizarla:

```bash
git pull --ff-only origin <año>/<semestre>
```

Si no recuerdas los nombres disponibles:

```bash
git branch -r --list "origin/*/*"
```

---

## Si algo no funciona

Antes de asumir que el código está incorrecto, comprueba:

- que estás en la **rama correcta**;
- que tienes un **JDK compatible**;
- que abriste la **raíz del repositorio**;
- que la importación de SBT terminó correctamente;
- que seleccionaste el **id SBT correcto** del módulo.

Puedes comprobar la versión de Java con:

```bash
java -version
```

y abrir una sesión interactiva de SBT con:

```bash
sbt
```

Dentro de SBT puedes listar los proyectos disponibles con:

```text
projects
```

---

## Versiones y herramientas

| Herramienta | Versión / uso                          |
| ----------- | -------------------------------------- |
| Scala       | `3.7.3`                                |
| SBT         | `1.11.x`                               |
| JDK         | `17+`                                  |
| MUnit       | Framework principal de testing         |
| JUnit 5     | Usado como comparación en `03-testing` |

---

## Material adicional

Algunos contenidos complementarios o de semestres anteriores pueden encontrarse en:

- [dcc-cc3002/clases-2025-2](https://github.com/dcc-cc3002/clases-2025-2/tree/main)
- [Programa histórico del curso](docs/CC3002-programa-primavera-2021-transcripcion.md)

Cuando un módulo tenga documentación propia, encontrarás un `README.md` dentro de su carpeta. Por ejemplo:

- [`01-static-typing/README.md`](01-static-typing/README.md)
- [`04-programming-to-abstractions/README.md`](04-programming-to-abstractions/README.md)

---

## Licencia

Este material se distribuye bajo la licencia **Creative Commons Attribution 4.0 International (CC BY 4.0)**.

Puedes compartir y adaptar el material, siempre que entregues la atribución correspondiente.

Texto completo de la licencia:

[https://creativecommons.org/licenses/by/4.0/](https://creativecommons.org/licenses/by/4.0/)
