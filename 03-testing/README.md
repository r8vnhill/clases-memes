# 03 - Testing

Este módulo introduce testing en Scala con ejemplos pequeños y progresivos. El foco principal está en `MUnit`, que es el
framework de pruebas usado en el repo, y en `JUnit 5` como contraste para mostrar otra forma común de organizar tests.

## Temas cubiertos

- Primeros tests en Scala sobre clases pequenas
- Estructura basica de un test con `MUnit`
- Uso de fixtures con `beforeEach`
- Contraste entre `MUnit` y `JUnit 5`
- Importancia de `equals` y `toString` para tests legibles
- Contaminacion entre tests cuando hay estado mutable

## Mapa de ejemplos

### Primer test: `Calculator`

- `src/main/scala/Calculator.scala` Clase mínima para mostrar un comportamiento fácil de probar.
- `src/test/scala/CalculatorTest.scala` Ejemplo básico de `MUnit` con `munit.FunSuite`, fixture con `beforeEach` y un
  caso de prueba simple.
- `src/test/scala/junit/CalculatorTest.scala` Ejemplo equivalente en `JUnit 5` usando anotaciones como `@BeforeEach` y
  `@Test`.

### Aislamiento y fixtures: `Counter`

- `src/main/scala/Counter.scala` Objeto mutable usado para ilustrar por qué no conviene compartir estado entre tests.
- `src/test/scala/CounterTest.scala` Muestra cómo `beforeEach` entrega un fixture nuevo para cada prueba y evita que el
  resultado dependa del orden de ejecución.

### Tests sobre objetos pequeños: `Point`

- `src/main/scala/Point.scala` Clase sencilla con `moveBy`, `toString` y `equals`.
- `src/test/scala/PointTest.scala` Práctica tests sobre movimiento, igualdad por estado y representación textual.

### Caso de estudio: `Money` y `MoneyBag`

- `src/main/scala/money/Money.scala` Introduce una clase de dominio pequeña donde los tests empujan decisiones de diseño
  como redefinir `equals` y `toString`.
- `src/main/scala/money/MoneyBag.scala` Agrupa montos por divisa y permite discutir fixtures más ricos y comparación
  entre objetos compuestos.
- `src/test/scala/money/MoneyTest.scala` Verifica igualdad y suma de montos en una misma divisa.
- `src/test/scala/money/MoneyBagTest.scala` Revisa propiedades de igualdad y muestra un caso pendiente que el curso deja
  para más adelante.

## Cómo ejecutar los tests

Desde la raiz del repo:

```bash
sbt "project testing" test
sbt "project testing" "testOnly cl.uchile.dcc.CalculatorTest"
sbt "project testing" "testOnly cl.uchile.dcc.junit.CalculatorTest"
sbt "project testing" "testOnly cl.uchile.dcc.money.*"
```

## Dependencias relevantes

- `MUnit` es el framework principal del modulo y del resto del repo.
- `JUnit 5` aparece solo como ejemplo complementario dentro de `03-testing`.

## Nota

Este modulo esta pensado como material didactico. Los ejemplos priorizan claridad conceptual y tamano pequeno por sobre
cobertura exhaustiva o practicas de testing de produccion. Parte del contenido esta alineado con slides especificas del
curso para acompanar la introduccion gradual a las pruebas en Scala.
