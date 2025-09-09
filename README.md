# Código Complementario | CC3002 – Metodologías de Diseño y Programación

**Universidad de Chile**

[![License: CC BY 4.0](https://img.shields.io/badge/License-CC%20BY%204.0-lightgrey.svg)](https://creativecommons.org/licenses/by/4.0/)
[![Scala](https://img.shields.io/badge/Scala-3.7.2-red?logo=scala)](https://www.scala-lang.org)
[![sbt](https://img.shields.io/badge/build-sbt-blue)](https://www.scala-sbt.org)
[![Tests](https://img.shields.io/badge/tests-MUnit%20%7C%20JUnit-green)](https://scalameta.org/munit/)
![Status](https://img.shields.io/badge/status-educational-blueviolet)

Este repositorio reúne ejemplos y fragmentos de código utilizados como **material complementario** en las cátedras del curso CC3002.
Su objetivo es apoyar las explicaciones de clase, **no** servir como base de proyectos de producción.

---

## 🚀 Uso

Clona el repositorio:

```bash
git clone https://github.com/r8vnhill/clases-memes.git
```

Cámbiate a la rama correspondiente al semestre.
Por ejemplo, para el semestre Primavera 2025 (2025-2):

```bash
git checkout 2025/2
```

> [!WARNING]
> Ten en cuenta que la rama se llama `2025/2` (con `/`), **no** `2025-2` ni `20252`.

Durante el semestre, puedes hacer `git pull` para actualizar el repositorio a medida que se vayan publicando nuevas versiones.

---

## 📌 Importante

### Documentación

* La mayoría del código **no está documentado formalmente**: se incluyen comentarios breves (`// ...`) solo para acompañar la explicación en clases.
* No se utiliza estilo **Scaladoc** (`/** ... */`) ni estándares de documentación de producción.

> [!CAUTION]
> Por lo tanto, este repositorio **no es referencia de buenas prácticas de documentación**.

### Estructura de directorios

* El repositorio está organizado en múltiples **módulos de SBT**.
* El `build.sbt` incluye configuraciones **avanzadas** que van más allá de lo enseñado en el curso.

> [!CAUTION]
> Durante el curso se mostrará un ejemplo de configuración más simple, por lo que este repositorio **no debe tomarse como referencia de configuración de SBT**.

---

## 📖 Licencia

Este material se distribuye bajo la licencia **[Atribución 4.0 Internacional (CC BY 4.0)](https://creativecommons.org/licenses/by/4.0/deed.es)**.
