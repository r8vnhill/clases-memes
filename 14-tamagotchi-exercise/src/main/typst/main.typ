#import "@preview/grape-suite:3.1.0": exercise
#import exercise: example, project, subtask, task, todo

#show: project.with(
  title: "Clase 14: Ejercicio - Tamagotchi",

  university: [Universidad de Chile],
  faculty: [Facultad de Ciencias Físicas y Matemáticas],
  institute: [Departamento de Ciencias de la Computación],
  seminar: [CC3002 - Metodologías de Diseño y Programación],

  semester: [2025 Primavera],
  author: [Ignacio Slater y Matías Toro],

  show-solutions: false,

  abstract: [
    *Propósito:* practicar los patrones _State_ y _Observer_ modelando un Tamagotchi
    con estados de ánimo/necesidad y un sistema de notificaciones de eventos.
  ],
)

#set raw(lang: "scala")
#show raw: set text(font: "Fira Code")
#show figure: set figure(supplement: [Figura])

= Contexto

Construirás un *Tamagotchi* con tres estados de ánimo/necesidad y un ciclo simple
de vida. El *patrón _State_* representará los estados (p. ej., `Hungry`, `Sleepy`,
`Happy`) y definirá cómo responde a acciones del usuario
(`feed()`, `sleep()`, `play()`).
El *patrón _Observer_* permitirá que observadores externos se suscriban a eventos
del Tamagotchi (cambios de estado, acciones válidas/ inválidas, etc.).

= Objetivos de aprendizaje

- Aplicar _State_ para eliminar condicionales y encapsular comportamientos por estado.
- Aplicar _Observer_ para desacoplar la emisión de eventos de sus receptores.
- Diseñar interfaces claras para _contexto_, _estado_, _sujeto_ y _observador_.
- Escribir reglas de transición y validación como *comportamiento de los estados*.
- Practicar pruebas unitarias de transiciones y notificaciones.

= Requisitos

== Parte 1: Modelar estados con el patrón State

En esta primera parte implementarás la *lógica interna del Tamagotchi*
usando el patrón de diseño *State* para representar su comportamiento
dependiente del estado.

Tu objetivo es modelar los estados `Happy`, `Hungry` y `Sleepy` y sus transiciones tal
como indica la figura, sin usar condicionales en el contexto.

#figure(
  image("tamagotchi.png", width: 50%),
  caption: [
    Diagrama de estados del Tamagotchi. Cualquier acción no listada para un estado debe *fallar*.
  ],
)

El Tamagotchi *siempre inicia* en `Happy`.

== Parte 2: Notificaciones con _Observer_

En esta parte integrarás un *controller* y dos *observers* para escuchar eventos
del Tamagotchi. El objetivo es *mostrar la estructura del patrón _Observer_*,
manteniendo la lógica simple y sin inspección de tipos.

=== Estructura objetivo

- `Tamagotchi` actúa como *emisor* de dos flujos de eventos:
  - `ValidTransition` (transición válida).
  - `InvalidTransition` (transición inválida).
- `Controller` posee *dos observadores*:
  - `Observer[ValidTransition]` → maneja transiciones válidas.
  - `Observer[InvalidTransition]` → maneja transiciones inválidas.
- Cada acción (`feed`, `play`, `sleep`) se ejecuta en el estado actual.  
  Si la acción es válida, se notifica `ValidTransition`; si el estado la rechaza
  lanzando `InvalidTransitionException`, se notifica `InvalidTransition`.
