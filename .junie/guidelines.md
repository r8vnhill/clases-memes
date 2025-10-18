Project: clases-memes (Scala 3, SBT multi-module)

This document captures build, testing, and development notes that are specific to this repository to help future contributors work efficiently.

1) Build and configuration
- Toolchain
  - Scala: 3.7.3 (set globally via ThisBuild / scalaVersion).
  - SBT: 1.11.x (tested locally with 1.11.4).
  - Java: any modern JDK compatible with Scala 3.7 (Java 17+ recommended; local runs observed on Azul JDK 25).

- Multi-module layout
  - Each numbered folder (e.g., 03-testing, 07-overriding-overloading, etc.) is a separate SBT subproject.
  - Subprojects are defined in build.sbt with lazy val names (e.g., testing for 03-testing).
  - The root project aggregates a subset of modules for convenience builds. Not all modules are included in the root aggregate; if you need to operate on a non-aggregated module, switch to it explicitly with project <moduleId>.
    - Aggregated in root: staticTyping, introOOP, testing, programmingToAbstractions, overridingOverloading, mediaPlayerExercise, encapsulationAndLiskov, doubleDispatch, exceptions, polymorphism, designPatterns1.
    - Defined but not aggregated: inheritance (05-inheritance), tamagotchiExercise (14-tamagotchi-exercise). Use project inheritance or project tamagotchiExercise when needed.

- Global settings
  - Organization/package prefix: cl.uchile.dcc (organization set via ThisBuild / organization).
  - Compiler flags (scalacOptions): -deprecation, -feature, -Wunused:imports,locals,privates,params, -Wvalue-discard.
  - Tests run in a forked JVM: Test / fork := true (useful to isolate test class loaders).
  - IDE: sbt-ide-settings plugin is enabled (project/plugins.sbt). idePackagePrefix is set to cl.uchile.dcc (it may be ignored by SBT itself but helps IDE syncs).

- Dependencies
  - MUnit is configured globally for all modules: libraryDependencies += "org.scalameta" %% "munit" % V.munit % Test and testFrameworks += new TestFramework("munit.Framework").
  - Module 03-testing also adds JUnit 5 API for mixed examples: "org.junit.jupiter" % "junit-jupiter-api" % V.junit % Test.

- Convenience alias
  - ci alias runs the typical clean, compile, test pipeline: sbt ci expands to ;clean;compile;test.

- Typical commands
  - Compile everything aggregated by root: sbt compile
  - Run all tests across aggregated modules: sbt test
  - Switch to a specific module: sbt "project testing" (module IDs are the lazy val names in build.sbt)
  - Then compile/test that module only: compile, test

2) Testing
- Frameworks and layout
  - Primary test framework: MUnit for Scala 3. Test classes should extend munit.FunSuite.
  - Tests live under each module at: <module>/src/test/scala. Keep package cl.uchile.dcc to match the organization.
  - JUnit 5 is available only in the testing module (03-testing) for illustrative purposes. Prefer MUnit for new tests unless there is a strong reason to use JUnit.

- Running tests
  - All aggregated modules: sbt test
  - Single module: sbt "project testing" test
  - Single test class (by fully qualified name):
    - sbt "project testing" "testOnly cl.uchile.dcc.CalculatorTest"
  - Filter by glob (MUnit/testOnly supports globs):
    - sbt "project testing" "testOnly *Calculator*"

- Lifecycle hooks and assertions (MUnit)
  - beforeEach/afterEach are available by overriding the corresponding methods to set up/tear down fresh state for each test.
  - Common assertions: assert, assertEquals(expected, actual), intercept[ExceptionType] { ... }.

- Adding a new MUnit test (example)
  - File: <module>/src/test/scala/MyFeatureTest.scala
  - Contents (Scala 3):
    package cl.uchile.dcc
    class MyFeatureTest extends munit.FunSuite:
      override def beforeEach(context: BeforeEach): Unit =
        () // optional setup
      test("does what it should") {
        assertEquals(2 + 2, 4)
      }

- Verified test example (performed during this documentation task)
  - A temporary MUnit test was added under 03-testing to verify the pipeline:
    - File: 03-testing/src/test/scala/DemoSanityTest.scala
    - Command executed: sbt "project testing" "testOnly cl.uchile.dcc.DemoSanityTest"
    - Result: Passed 1 test.
  - The temporary file was removed afterwards to keep the repo clean, as per task instructions.

3) Additional development notes
- Code organization
  - Modules are intentionally small and thematic (one module per class/topic). Prefer adding new examples/tests within the corresponding module to keep coherence.
  - Root aggregation is partial by design. If you add a new module and want it to be included in root-wide tasks, also add it to root.aggregate in build.sbt. Otherwise, expect that sbt test from root will skip it.

- Style and conventions
  - Use Scala 3 syntax (significant indentation). Existing code uses indented syntax and top-level definitions. Keep consistency.
  - Prefer small, focused test names. When helpful, include human-readable clues/messages in assertions to ease debugging.
  - Package: cl.uchile.dcc as a base. Subpackages should mirror the folder/topic when applicable.

- Debugging tips
  - To run only failing or recently changed tests quickly, use testOnly with class or glob patterns as shown above.
  - When investigating classpath issues, remember tests are forked (Test / fork := true). You can temporarily disable for debugging with set Test / fork := false in the SBT shell.
  - If IDE cannot resolve packages, run sbt reload; clean; update; compile to refresh metadata. The sbt-ide-settings plugin helps IntelliJ sync with proper package prefix.

- Mixed frameworks
  - While JUnit 5 is available in 03-testing, standardize on MUnit for new work to avoid inconsistencies. If you must add JUnit tests elsewhere, declare the dependency in that module and ensure testFrameworks remains configured for MUnit unless you intentionally switch.

- CI and repeatability
  - Prefer the ci alias for local pre-commit checks: sbt ci
  - If you add a module, consider whether it should be aggregated by root so that ci covers it.

- Known warnings
  - SBT may warn about idePackagePrefix being unused by settings/tasks; this is expected and safe. It is consumed by IDE tooling.
