package cl.uchile.dcc
package singleton

class DatabaseConnectionTest extends munit.FunSuite:
  test("Singleton instance is created only once"):
    assert(
        DatabaseConnection.getInstance eq
          DatabaseConnection.getInstance
    )
