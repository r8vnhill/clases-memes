package cl.uchile.dcc
package junit

import cl.uchile.dcc.Calculator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.{BeforeEach, DisplayName, Test}

/**
 * Unit tests for the [[Calculator]] class.
 *
 * ⚠️ Note for students:
 *
 * In professional projects, test classes are usually <b>not documented</b>
 * because:
 *   - Their purpose should be obvious from their names (e.g., `CalculatorTest`
 *     tests `Calculator`).
 *   - The test methods themselves are expected to be self-explanatory if well
 *     written.
 *
 * Here we include documentation and comments explicitly because this is for
 * **learning purposes**. The goal is to help you understand both *how tests are
 * structured* and *why we write them*.
 */
class CalculatorTest:

  /**
   * A reference to the `Calculator` that we will test.
   *
   *   - We declare it as `var` because its value will change (a new Calculator
   *     for each test).
   *   - We wrap it in `Option` to represent that it might not yet be
   *     initialized.
   */
  var calculator: Option[Calculator] = None

  /**
   * The [[BeforeEach]] annotation means:
   *   - This method runs <b>before every test method</b> in this class.
   *   - It ensures we start with a fresh `Calculator` for each test, so tests
   *     do not interfere.
   */
  @BeforeEach
  def setUp(): Unit =
    calculator = Some(new Calculator())

  /**
   * A test method: checks that addition works correctly.
   *
   *   - [[Test]] marks this as a JUnit test.
   *   - [[DisplayName]] gives the test a readable description when shown in
   *     reports or IDEs.
   *
   * What it does:
   *   - Calls `calculator.add(2, 3)`.
   *   - Verifies that the result is `5`.
   *   - If the result is not `5`, the test fails and shows the message `"2 +
   *     3"`.
   */
  @Test
  @DisplayName("Test addition of two numbers")
  def testAddition(): Unit =
    assertEquals(5, calculator.get.add(2, 3), "2 + 3")
