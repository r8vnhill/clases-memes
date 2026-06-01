package cl.uchile.dcc
package singleton

class AppConfigTest extends munit.FunSuite:
  test("Singleton instance is created only once"):
    assert(
        AppConfig.getInstance eq
          AppConfig.getInstance
    )
