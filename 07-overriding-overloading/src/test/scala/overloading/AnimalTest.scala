package cl.uchile.dcc
package overloading

class AnimalTest extends munit.FunSuite:
  test("Equality is symmetric"):
    // Igualdad es simétrica...
    val c1 = new Cat("Snowbell")
    val c2 = new Cat("Snowbell")
    assertEquals(c1, c2)
    assertEquals(c2, c1)
    // ... Desigualdad también
    val c3 = new Persian("Snowbell")
    assertNotEquals(c1, c3)
    assertNotEquals(c3, c1)
