package cl.uchile.dcc
package template

import scala.collection.mutable

abstract class AbstractTestSuite:
  private val tests: mutable.Set[TestCase] = mutable.Set()

  def run(): Unit =
    val failedTests = mutable.Set[TestCase]()
    for test <- tests do
      beforeEach()
      if !test.test() then failedTests += test
      afterEach()
    report(failedTests)

  protected def beforeEach(): Unit

  protected def afterEach(): Unit

  protected def addTest(name: String, test: () => Boolean): Unit =
    tests += new TestCase(name, test)

  private def report(failedTests: collection.Set[TestCase]): Unit =
    val passedTests = tests.diff(failedTests)
    println(s"Passed tests: ${passedTests.size}/${tests.size}")
    println(passedTests.mkString("\n"))
    println(s"Failed tests: ${failedTests.size}/${tests.size}")
    println(failedTests.mkString("\n"))
