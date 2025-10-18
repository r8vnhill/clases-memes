package cl.uchile.dcc
package template

class MyTestSuite extends AbstractTestSuite:
  override protected def beforeEach(): Unit =
    println("Before each test")
  override protected def afterEach(): Unit =
    println("After each test")
  addTest("Test 1", () => 1 + 1 == 2)
  addTest("Test 2", () => 1 + 1 == 3)

@main def runTests(): Unit =
  new MyTestSuite().run()
