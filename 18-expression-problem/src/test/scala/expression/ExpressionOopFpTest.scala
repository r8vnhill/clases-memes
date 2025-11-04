package cl.uchile.dcc
package expression

import expression.oop.*
import expression.fp as fp
import munit.FunSuite

class ExpressionOopFpTest extends FunSuite:
  test("OOP eval (Composite)"):
    val expr: OExpr = new OAdd(new OConst(1), new OSub(new OConst(5), new OConst(2)))
    assertEquals(expr.eval, 4)

  test("FP pretty"):
    val e: fp.FExpr = fp.FAdd(fp.FConst(1), fp.FSub(fp.FConst(5), fp.FConst(2)))
    assertEquals(fp.pretty(e), "(1 + (5 - 2))")
