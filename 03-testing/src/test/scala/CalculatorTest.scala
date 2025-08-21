package cl.uchile.dcc

/**
 * Unit tests for the [[Calculator]] class, written with the MUnit testing framework.
 *
 * ⚠️ Note for students:
 *
 * In professional projects, test classes are usually **not documented** because:
 *   - The purpose of a test is normally clear from its class and method names.
 *   - Good tests should be self-explanatory.
 *
 * Here we add documentation for **teaching purposes**, to explain how tests are structured and why
 * they are written this way.
 */
class CalculatorTest extends munit.FunSuite:

  /**
   * A reference to the `Calculator` we will test.
   *
   *   - Declared as `var` so we can reset it before each test.
   *   - It starts as `None`, but is initialized fresh before every test run.
   */
  var calculator: Option[Calculator] = None

  /**
   * `beforeEach` is a lifecycle hook provided by MUnit.
   *
   *   - This method is automatically executed <b>before each test case</b>.
   *   - Ensures that each test starts with a new, clean `Calculator` instance.
   *   - Prevents one test from accidentally affecting another.
   */
  override def beforeEach(context: BeforeEach): Unit =
    calculator = Some(new Calculator())

  /**
   * A test case that checks if addition works correctly.
   *
   *   - `test("...")` defines a single test with a descriptive name.
   *   - Inside, we use `assertEquals(expected, actual, clue)`:
   *     - `expected`: the result we *want* (5).
   *     - `actual`: the result from running the code (`calculator.add(2, 3)`).
   *     - `clue`: an optional message shown if the test fails, useful for debugging.
   */
  test("Test addition of two numbers") {
    assertEquals(5, calculator.get.add(2, 3), "2 + 3")
  }
